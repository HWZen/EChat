package entity.sql;

import entity.ChatSession;
import entity.ChatSessionInter;
import entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class ChatSessionInterImpl implements ChatSessionInter {
    private Connection SQLConnection;

    public ChatSessionInterImpl() throws SQLException {
        SQLDriver driver = new SQLDriver();
        SQLConnection = driver.getConnection();
    }

    @Override
    public ChatSession byId(String id) {
        String sessionName = "";
        String ownerId = "";
        List<User> sessionMembers = new ArrayList<>();

        String sql_1 = "SELECT * " +
                " FROM " + DatabaseStructure.TABLE_SESSION +
                " WHERE " + DatabaseStructure.COLUMN_SESSION_ID + " = ?";
        try {
            PreparedStatement preparedStatement = SQLConnection.prepareStatement(sql_1);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                sessionName = resultSet.getString(DatabaseStructure.COLUMN_SESSION_NAME);
                ownerId = resultSet.getString(DatabaseStructure.COLUMN_OWNER_ID);
            } else
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql_2 = "SELECT " + DatabaseStructure.COLUMN_USER_ID +
                " FROM " + DatabaseStructure.TABLE_SESSION_MEMBER +
                " WHERE " + DatabaseStructure.COLUMN_SESSION_ID + " = ?";
        try {
            PreparedStatement preparedStatement = SQLConnection.prepareStatement(sql_2);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserInterImpl userInter = new UserInterImpl();
                User user = userInter.byId(resultSet.getString(DatabaseStructure.COLUMN_USER_ID));
                sessionMembers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ChatSession(id, sessionName, ownerId, sessionMembers);
    }

    @Override
    public void createSession(String name, User owner, List<String> membersIds) {
        String sql_1 = "INSERT INTO " + DatabaseStructure.TABLE_SESSION +
                " (" + DatabaseStructure.COLUMN_SESSION_NAME + ", " +
                DatabaseStructure.COLUMN_OWNER_ID + ") " +
                " VALUES (?, ?)";
        try {
            PreparedStatement preparedStatement = SQLConnection.prepareStatement(sql_1);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, owner.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql_2 = "IDENT_CURRENT('" + DatabaseStructure.TABLE_SESSION + "')";
        try {
            PreparedStatement preparedStatement = SQLConnection.prepareStatement(sql_2);
            preparedStatement.
        } catch (SQLException e) {
            e.printStackTrace();
        }


        String sql_3 = "INSERT INTO " + DatabaseStructure.TABLE_SESSION_MEMBER +
                " (" + DatabaseStructure.COLUMN_SESSION_ID + ", " +
                DatabaseStructure.COLUMN_USER_ID + ") " +
                " VALUES (?, ?)";
        for (String membersId : membersIds) {
            try {
                PreparedStatement preparedStatement = SQLConnection.prepareStatement(sql_3);
                preparedStatement.setString(1, );
                preparedStatement.setString(2, membersId);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean updateSession(User owner, ChatSession session) {
        if (owner.getId().equals(getSessionOwner(session.getId()))) {
            deleteSession(owner, session.getId());
            createSession(session);
            return true;
        } else
            return false;
    }

    @Override
    public boolean deleteSession(User owner, String id) {
        if (owner.getId().equals(getSessionOwner(id))) {
            String sql_1 = "DELETE FROM " + DatabaseStructure.TABLE_SESSION_MEMBER +
                    " WHERE " + DatabaseStructure.COLUMN_SESSION_ID + " = ?";
            try {
                PreparedStatement preparedStatement = SQLConnection.prepareStatement(sql_1);
                preparedStatement.setString(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String sql_2 = "DELETE FROM " + DatabaseStructure.TABLE_SESSION +
                    " WHERE " + DatabaseStructure.COLUMN_SESSION_ID + " = ?";
            try {
                PreparedStatement preparedStatement = SQLConnection.prepareStatement(sql_2);
                preparedStatement.setString(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return true;
        } else
            return false;
    }

    @Override
    public String getSessionOwner(String id) {
        String sql = "SELECT " + DatabaseStructure.COLUMN_OWNER_ID +
                " FROM " + DatabaseStructure.TABLE_SESSION +
                " WHERE " + DatabaseStructure.COLUMN_SESSION_ID + " = ?";
        try {
            PreparedStatement preparedStatement = SQLConnection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(DatabaseStructure.COLUMN_OWNER_ID);
            } else
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
