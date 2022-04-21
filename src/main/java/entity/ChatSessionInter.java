package entity;

import java.util.List;

import entity.sql.DatabaseStructure;

public interface ChatSessionInter {

    ChatSession byId(String id);

    void createSession(String name, User owner, List<String> membersId);

    String getSessionOwner(String id);

    boolean updateSession(User owner, List<String> members);

    boolean updateSession(User owner, String newName);

    boolean deleteSession(User owner, String id);
}
