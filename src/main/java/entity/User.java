package entity;

import java.io.Serializable;

public class User implements Serializable {

    private String userId;
    private String nickname;
    private String password;

    public User(String userId, String nickname, String password) {
        this.userId=userId;
        this.nickname=nickname;
        this.password=password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return userId;
    }

    public void setUserId(String id) {
        this.userId = id;
    }
}
