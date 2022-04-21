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


    static public void logout(String userID) {
        User user = userInter.byId(userID);
        if (AuthorityManager.isLogin(user)) {
            AuthorityManager.removeAuthenticatedLogin(user);
        } else
            System.out.println("User is not login");

    }

    boolean login(String userName, String password, String cookie){
        return AuthorityManager.addAuthenticatedLogin(userName, password, cookie);
    }

    boolean createGroup(Session session, String groupName, List<String> userIds){
        Cookie c = ConnectManager.getCookie(session);
        User user = AuthorityManager.GetUser(c);
        if(user == null)
            return false;
        chatSessionInter.createSession(groupName, user, userIds);
        return true;
    }

    boolean deleteGroup(Session session, String groupId){
        Cookie c = ConnectManager.getCookie(session);
        User user = AuthorityManager.GetUser(c);
        if(user == null)
            return false;
        chatSessionInter.deleteSession(user, groupId);
        return true;
    }

}
