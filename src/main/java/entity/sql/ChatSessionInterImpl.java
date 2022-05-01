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
        SQLConnection = SQLDriver.SQLConnection;
    }

    public ChatSessionInterImpl(Connection SQLConnection) throws SQLException {
        this.SQLConnection = SQLConnection;
    }

    private int countMembers(String sessionId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + DatabaseStructure.TABLE_SESSION_MEMBER +
                " WHERE " + DatabaseStructure.COLUMN_SESSION_ID + " = ?";
        PreparedStatement preparedStatement = SQLConnection.prepareStatement(sql);
        preparedStatement.setString(1, sessionId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
            return resultSet.getInt(1);
        return 0;
    }

    private int deleteMembersById(String sessionId) throws SQLException {
        String sql = "DELETE FROM " + DatabaseStructure.TABLE_SESSION_MEMBER +
                " WHERE " + DatabaseStructure.COLUMN_SESSION_ID + " = ?";
        PreparedStatement preparedStatement = SQLConnection.prepareStatement(sql);
        preparedStatement.setString(1, sessionId);
        return preparedStatement.executeUpdate();
    }

    private int[] insertSessionMembers(String sessionId, List<String> membersIds) throws SQLException {
        String sql = "INSERT INTO " + DatabaseStructure.TABLE_SESSION_MEMBER +
                " (" + DatabaseStructure.COLUMN_SESSION_ID + ", " + DatabaseStructure.COLUMN_USER_ID + ") " +
                "VALUES (?, ?)";
        PreparedStatement preparedStatement = SQLConnection.prepareStatement(sql);
        for (String memberId : membersIds) {
            preparedStatement.setString(1, sessionId);
            preparedStatement.setString(2, memberId);
            preparedStatement.addBatch();
        }
        return preparedStatement.executeBatch();
    }

    @Override
    public ChatSession byId(String id) throws SQLException {
        String sessionName = "";
        String ownerId = "";
        List<String> sessionMembers = new ArrayList<>();

        {
            String sql = "SELECT * " +
                    " FROM " + DatabaseStructure.TABLE_SESSION +
                    " WHERE " + DatabaseStructure.COLUMN_SESSION_ID + " = ?";
            PreparedStatement preparedStatement = SQLConnection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                sessionName = resultSet.getString(DatabaseStructure.COLUMN_SESSION_NAME);
                ownerId = resultSet.getString(DatabaseStructure.COLUMN_OWNER_ID);
            } else
                return null;
        }

        {
            String sql = "SELECT " + DatabaseStructure.COLUMN_USER_ID +
                    " FROM " + DatabaseStructure.TABLE_SESSION_MEMBER +
                    " WHERE " + DatabaseStructure.COLUMN_SESSION_ID + " = ?";
            PreparedStatement preparedStatement = SQLConnection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                sessionMembers.add(resultSet.getString(DatabaseStructure.COLUMN_USER_ID));
            }
        }

        return new ChatSession(id, sessionName, ownerId, sessionMembers);
    }

    @Override
    public String createSession(String name, User owner, List<String> membersIds) throws SQLException {
        {
            String sql = "INSERT INTO " + DatabaseStructure.TABLE_SESSION +
                    " (" + DatabaseStructure.COLUMN_SESSION_NAME + ", " +
                    DatabaseStructure.COLUMN_OWNER_ID + ") " +
                    " VALUES (?, ?)";
            PreparedStatement preparedStatement = SQLConnection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, owner.getId());
            preparedStatement.executeUpdate();
        }

        String id = null;
        {
            String sql = "select @@IDENTITY";
            PreparedStatement preparedStatement = SQLConnection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getString(1);
                insertSessionMembers(id, membersIds);
            }
        }
        return id;

    }

    @Override
    public boolean createSession(String sessionName, User owner, List<String> memberIds, String id) throws SQLException {
        {
            String sql = "INSERT INTO " + DatabaseStructure.TABLE_SESSION +
                    " (" + DatabaseStructure.COLUMN_SESSION_ID + ", " +
                    DatabaseStructure.COLUMN_SESSION_NAME + ", " +
                    DatabaseStructure.COLUMN_OWNER_ID + ") " +
                    " VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = SQLConnection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, sessionName);
            preparedStatement.setString(3, owner.getId());
            preparedStatement.executeUpdate();
        }

        {
            insertSessionMembers(id, memberIds);
        }
        return true;
    }

    @Override
    public boolean updateSessionMember(String sessionId, List<String> memberIds) throws SQLException {
        return countMembers(sessionId) == deleteMembersById(sessionId) &
                insertSessionMembers(sessionId, memberIds).length == memberIds.size();
    }

    @Override
    public boolean updateSessionName(String sessionId, String sessionName) throws SQLException {
        String sql = "UPDATE " + DatabaseStructure.TABLE_SESSION +
                " SET " + DatabaseStructure.COLUMN_SESSION_NAME + " = ?" +
                " WHERE " + DatabaseStructure.COLUMN_SESSION_ID + " = ?";
        PreparedStatement preparedStatement = SQLConnection.prepareStatement(sql);
        preparedStatement.setString(1, sessionName);
        preparedStatement.setString(2, sessionId);
        return preparedStatement.executeUpdate() == 1;
    }

    @Override
    public boolean updateSessionOwner(String sessionId, String ownerId) throws SQLException {
        String sql = "UPDATE " + DatabaseStructure.TABLE_SESSION +
                " SET " + DatabaseStructure.COLUMN_OWNER_ID + " = ?" +
                " WHERE " + DatabaseStructure.COLUMN_SESSION_ID + " = ?";
        PreparedStatement preparedStatement = SQLConnection.prepareStatement(sql);
        preparedStatement.setString(1, ownerId);
        preparedStatement.setString(2, sessionId);
        return preparedStatement.executeUpdate() == 1;
    }

    @Override
    public boolean deleteSession(String sessionId) throws SQLException {
//        if (deleteMembersById(sessionId) != countMembers(sessionId))
//            return false;
        String sql = "DELETE FROM " + DatabaseStructure.TABLE_SESSION +
                " WHERE " + DatabaseStructure.COLUMN_SESSION_ID + " = ?";
        PreparedStatement preparedStatement = SQLConnection.prepareStatement(sql);
        preparedStatement.setString(1, sessionId);
        return preparedStatement.executeUpdate() == 1;
    }

    @Override
    public boolean isSessionPrivate(String sessionId) throws SQLException {
        String sql = "SELECT " + DatabaseStructure.COLUMN_OWNER_ID +
                " FROM " + DatabaseStructure.TABLE_SESSION +
                " WHERE " + DatabaseStructure.COLUMN_SESSION_ID + " = ?";
        PreparedStatement preparedStatement = SQLConnection.prepareStatement(sql);
        preparedStatement.setString(1, sessionId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
            return resultSet.getString(1) == null;
        return false;
    }

    @Override
    public List<String> getAllSessionsIds(String userId) throws SQLException {
        List<String> sessionIds = new ArrayList<>();
        String sql = "SELECT " + DatabaseStructure.COLUMN_SESSION_ID +
                " FROM " + DatabaseStructure.TABLE_SESSION_MEMBER +
                " WHERE " + DatabaseStructure.COLUMN_USER_ID + " = ?";
        PreparedStatement preparedStatement = SQLConnection.prepareStatement(sql);
        preparedStatement.setString(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            sessionIds.add(resultSet.getString(1));
        }
        return sessionIds;
    }

    @Override
    public List<ChatSession> getAllSessions(String userId) throws SQLException {
        List<String> sessionIds = getAllSessionsIds(userId);
        List<ChatSession> sessions = new ArrayList<>();
        for (String sessionId : sessionIds){
            sessions.add(byId(sessionId));
        }
        return sessions;
    }
}
