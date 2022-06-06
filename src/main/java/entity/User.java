package entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    @JSONField(name = "userId")
    private String userId;

    @JSONField(name = "nickName")
    private String nickname;

    @JSONField(name = "password")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return userId.equals(user.userId) && getNickname().equals(user.getNickname()) && getPassword().equals(user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, getNickname(), getPassword());
    }
}
