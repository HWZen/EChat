package entity;

import entity.sql.UserInterImpl;
import entity.sql.ChatSessionInterImpl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;

public class Msg implements Serializable {

    private String fromUserId;
    private String toSessionId;
    private Date sendTime;
    private String content;



    public Msg(String fromUserId, String toSessionId, Date sendTime, String content) {
        this.fromUserId = fromUserId;
        this.toSessionId = toSessionId;
        this.sendTime = sendTime;
        this.content = content;
    }

    public User getFrom() {
        try {
            return new UserInterImpl().byId(fromUserId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getFromUserId(){
        return this.fromUserId;
    }

    public ChatSession getTo() {
        try {
            return new ChatSessionInterImpl().byId(toSessionId);
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getToSessionId(){
        return this.toSessionId;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public String getContent() {
        return content;
    }
}

