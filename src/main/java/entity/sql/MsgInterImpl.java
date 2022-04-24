package entity.sql;

import entity.ChatSession;
import entity.Msg;
import entity.MsgInter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MsgInterImpl implements MsgInter {
    private Connection SQLConnection;

    public MsgInterImpl() {
        SQLConnection = SQLDriver.SQLConnection;
    }

    public MsgInterImpl(Connection SQLConnection) {
        this.SQLConnection = SQLConnection;
    }

    @Override
    public List<Msg> bySessionId(String sessionId) throws SQLException {
        List<Msg> msgs = new ArrayList<>();
        String sql = "SELECT *" +
                " FROM " + DatabaseStructure.TABLE_LOG +
                " WHERE " + DatabaseStructure.COLUMN_SESSION_ID + " = ?";
        PreparedStatement preparedStatement = SQLConnection.prepareStatement(sql);
        preparedStatement.setString(1, sessionId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String fromUserId = resultSet.getString(DatabaseStructure.COLUMN_USER_ID);
            String toSessionId = resultSet.getString(DatabaseStructure.COLUMN_SESSION_ID);
            Date sendTime = resultSet.getDate(DatabaseStructure.COLUMN_LOG_TIME);
            String content = resultSet.getString(DatabaseStructure.COLUMN_LOG_CONTENT);
            Msg msg = new Msg(fromUserId, toSessionId, sendTime, content);
            msgs.add(msg);
        }
        return msgs;
    }

    @Override
    public List<Msg> bySession(ChatSession session) throws SQLException {
        return bySessionId(session.getId());
    }

    @Override
    public boolean add(Msg msg) throws SQLException {
        String sql = "INSERT INTO " + DatabaseStructure.TABLE_LOG +
                " (" + DatabaseStructure.COLUMN_USER_ID + ", " +
                DatabaseStructure.COLUMN_SESSION_ID + ", " +
                DatabaseStructure.COLUMN_LOG_TIME + ", " +
                DatabaseStructure.COLUMN_LOG_CONTENT + ") " +
                "VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = SQLConnection.prepareStatement(sql);
        preparedStatement.setString(1, msg.getFromUserId());
        preparedStatement.setString(2, msg.getToSessionId());
        preparedStatement.setDate(3, new java.sql.Date(msg.getSendTime().getTime()));
        preparedStatement.setString(4, msg.getContent());
        return preparedStatement.executeUpdate() == 1;
    }
}
