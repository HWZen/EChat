package entity;

import java.io.Serializable;

public class Msg implements Serializable {

    public enum AgentType{
        USER,
        GROUP,
        Server

        }


    private String from;
    private AgentType fromType;

    private String to;
    private AgentType toType;

    private String msg;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public AgentType getFromType() {
        return fromType;
    }

    public void setFromType(AgentType fromType) {
        this.fromType = fromType;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public AgentType getToType() {
        return toType;
    }

    public void setToType(AgentType toType) {
        this.toType = toType;
    }
}

