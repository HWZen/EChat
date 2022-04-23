package entity;

import java.sql.SQLException;
import java.util.List;

import entity.sql.DatabaseStructure;

import javax.websocket.Session;

public interface ChatSessionInter {

    ChatSession byId(String id) throws SQLException;

    void createSession(String sessionName, User owner, List<String> memberIds) throws SQLException;

    boolean updateSessionMember(String sessionId ,List<String> memberIds) throws SQLException;

    boolean updateSessionName(String sessionId ,String sessionName) throws SQLException;

    boolean deleteSession(String sessionId) throws SQLException;

    boolean isSessionPrivate(String sessionId) throws SQLException;
}
