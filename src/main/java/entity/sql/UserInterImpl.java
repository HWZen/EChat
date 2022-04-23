package entity.sql;

import entity.UserInter;
import entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class UserInterImpl implements UserInter {
    private Connection SQLConnection;

    public UserInterImpl() throws SQLException {
        SQLConnection = SQLDriver.SQLConnection;
    }

    public UserInterImpl(Connection SQLConnection) throws SQLException {
        this.SQLConnection = SQLConnection;
    }

    @Override
    public User byId(String id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id=?";
        PreparedStatement preparedStatement = SQLConnection.prepareStatement(sql);
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            String nickname = resultSet.getString(DatabaseStructure.COLUMN_NICKNAME);
            String password = resultSet.getString(DatabaseStructure.COLUMN_PASSWORD);
            return new User(id, nickname, password);
        }
        return null;
    }

    @Override
    public boolean addUser(User user) throws SQLException {
        String sql = "INSERT INTO " + DatabaseStructure.TABLE_USER +
                " (" + DatabaseStructure.COLUMN_USER_ID + ", " + DatabaseStructure.COLUMN_NICKNAME + ", " + DatabaseStructure.COLUMN_PASSWORD + ") " +
                "VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = SQLConnection.prepareStatement(sql);
        preparedStatement.setString(1, user.getId());
        preparedStatement.setString(2, user.getNickname());
        preparedStatement.setString(3, user.getPassword());
        return preparedStatement.executeUpdate() == 1;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        String sql = "UPDATE " + DatabaseStructure.TABLE_USER +
                " SET " + DatabaseStructure.COLUMN_NICKNAME + "=?, " + DatabaseStructure.COLUMN_PASSWORD + "=? " +
                "WHERE " + DatabaseStructure.COLUMN_USER_ID + "=?";
        PreparedStatement preparedStatement = SQLConnection.prepareStatement(sql);
        preparedStatement.setString(1, user.getNickname());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getId());
        return preparedStatement.executeUpdate() == 1;
    }

    @Override
    public boolean deleteUser(User user) throws SQLException {
        // 删除用户拥有的Session
        {
            String sql = "SELECT " + DatabaseStructure.COLUMN_SESSION_ID +
                    " FROM " + DatabaseStructure.TABLE_SESSION +
                    " WHERE " + DatabaseStructure.COLUMN_USER_ID + "=?";
            PreparedStatement preparedStatement = SQLConnection.prepareStatement(sql);
            preparedStatement.setString(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String sessionId = resultSet.getString(DatabaseStructure.COLUMN_SESSION_ID);
                new ChatSessionInterImpl(SQLConnection).deleteSession(sessionId);
            }
        }

        // 退出用户加入的 Session
        {
            String sql = "DELETE FROM " + DatabaseStructure.TABLE_SESSION_MEMBER +
                    " WHERE " + DatabaseStructure.COLUMN_USER_ID + "=?";
            PreparedStatement preparedStatement = SQLConnection.prepareStatement(sql);
            preparedStatement.setString(1, user.getId());
            preparedStatement.executeUpdate();
        }

        // 删除用户
        {
            String sql = "DELETE FROM " + DatabaseStructure.TABLE_USER +
                    " WHERE " + DatabaseStructure.COLUMN_USER_ID + "=?";
            PreparedStatement preparedStatement = SQLConnection.prepareStatement(sql);
            preparedStatement.setString(1, user.getId());
            return preparedStatement.executeUpdate() == 1;
        }
    }

    @Override
    public boolean leaveSession(User user, String sessionId) throws SQLException {
        String sql = "DELETE FROM " + DatabaseStructure.TABLE_SESSION_MEMBER +
                " WHERE " + DatabaseStructure.COLUMN_SESSION_ID + "=? AND " + DatabaseStructure.COLUMN_USER_ID + "=?";
        PreparedStatement preparedStatement = SQLConnection.prepareStatement(sql);
        preparedStatement.setString(1, sessionId);
        preparedStatement.setString(2, user.getId());
        return preparedStatement.executeUpdate() == 1;
    }
}
