package entity;

import java.io.Serializable;

public class Msg implements Serializable {

    private User from;

    private ChatSession to;

    public Msg(User user, ChatSession group, String message) {
        from = user;
        to = group;
        msg = message;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public ChatSession getTo() {
        return to;
    }

    public void setTo(ChatSession to) {
        this.to = to;
    }

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

