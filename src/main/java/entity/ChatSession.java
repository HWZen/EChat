package entity;

import java.io.Serializable;
import java.util.List;

public class ChatSession implements Serializable {
    private String id;
    private String sessionName;
    private String ownerId;
    private List<User> sessionMembers;

    public ChatSession(String id, String sessionName, String ownerId, List<User> sessionMembers) {
        this.id = id;
        this.sessionName = sessionName;
        this.ownerId = ownerId;
        this.sessionMembers = sessionMembers;
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

    public String getOwnerId() {return ownerId;}

    public void setOwnerId(String ownerId) {this.ownerId = ownerId;}

    public List<User> getSessionMembers() {
        return sessionMembers;
    }

    public void setSessionMembers(List<User> sessionMembers) {
        this.sessionMembers = sessionMembers;
    }
}
