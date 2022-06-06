package entity;

import com.alibaba.fastjson.annotation.JSONField;
import entity.sql.UserInterImpl;
import entity.sql.ChatSessionInterImpl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;

public class Msg implements Serializable {

    @JSONField(name = "senderId")
    private String fromUserId;

    @JSONField(name = "receiverId")
    private String toSessionId;

    @JSONField(name = "sendTime")
    private Date sendTime;

    @JSONField(name = "content")
    private String content;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Msg)) return false;
        Msg msg = (Msg) o;
        return getFromUserId().equals(msg.getFromUserId()) && getToSessionId().equals(msg.getToSessionId()) && Objects.equals(getSendTime(), msg.getSendTime()) && getContent().equals(msg.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFromUserId(), getToSessionId(), getSendTime(), getContent());
    }

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

