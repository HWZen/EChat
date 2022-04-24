package server;

import entity.*;
import entity.sql.ChatSessionInterImpl;
import entity.sql.UserInterImpl;

import javax.websocket.Session;
import java.sql.SQLException;
import java.util.List;

public class CommandManager {

    private static UserInter userInter;
    private static ChatSessionInter chatSessionInter;

    static {
        try {
            userInter = new UserInterImpl();
            chatSessionInter = new ChatSessionInterImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    static public boolean logout(String userID, String cookie) throws SQLException {
        User user = userInter.byId(userID);
        if (AuthorityManager.isLogin(user)) {
            Cookie c = AuthorityManager.getCookie(user);
            if(c != null && c.str.equals(cookie)) {
                AuthorityManager.removeAuthenticatedLogin(user);
            }else {
                System.out.println("cookie not match");
                return false;
            }
        } else{
            System.out.println("User is not login");
            return false;
        }
        return true;

    }

    static public boolean login(String userId, String password, String cookie) throws SQLException {
        User user = userInter.byId(userId);
        if(user == null || !user.getPassword().equals(password))
            return false;
        if(AuthorityManager.isLogin(user)) {
            Cookie c = AuthorityManager.getCookie(user);
            if (ConnectManager.getSession(c) != null)
                return false;
        }
        return AuthorityManager.addAuthenticatedLogin(user, cookie);
    }

    static public boolean signUp(String userId, String nickname, String passwd) throws SQLException {
        User user = new User(userId, nickname, passwd);
        return userInter.addUser(user);
    }

    static public boolean createGroup(String cookie, String groupName, List<String> userIds) throws SQLException {
        User user = AuthorityManager.GetUser(new Cookie(cookie));
        if(user == null)
            return false;
        chatSessionInter.createSession(groupName, user, userIds);
        return true;
    }

    static public boolean deleteGroup(String cookie, String groupId) throws SQLException {
        User user = AuthorityManager.GetUser(new Cookie(cookie));
        if(user == null)
            return false;
        ChatSession group = chatSessionInter.byId(groupId);
        if(group.getOwnerId().equals(user.getId())) {
            return chatSessionInter.deleteSession(groupId);
        }
        else return false;
    }
}
