package entity;

import entity.sql.UserInterImpl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class ChatSession implements Serializable {
    private String id;
    private String sessionName;
    private String ownerId;
    private List<String> sessionMemberIds;

    public ChatSession(String id, String sessionName, String ownerId, List<String> sessionMemberids) {
        this.id = id;
        this.sessionName = sessionName;
        this.ownerId = ownerId;
        this.sessionMemberIds = sessionMemberids;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public List<String> getSessionMemberIds() {
        return sessionMemberIds;
    }

    public List<User> getSessionMembers() {
        List<User> sessionMembers = new ArrayList<>();
        for (String memberId : sessionMemberIds) {
            try {
                sessionMembers.add(new UserInterImpl().byId(memberId));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return sessionMembers;
    }

    public void setSessionMemberIds(List<String> sessionMemberids) {
        this.sessionMemberIds = sessionMemberids;
    }
}
