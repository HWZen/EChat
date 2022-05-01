package server;

import entity.*;
import entity.sql.ChatSessionInterImpl;
import entity.sql.UserInterImpl;

import java.sql.SQLException;
import java.util.Arrays;
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

    static public List<ChatSession> getChatSessions(String cookie) throws SQLException {
        User user = AuthorityManager.GetUser(new Cookie(cookie));
        if(user == null)
            return null;
        return chatSessionInter.getAllSessions(user.getId());
    }

    static public ChatSession addFriend(String cookie, String friendId) throws SQLException {
        User user = AuthorityManager.GetUser(new Cookie(cookie));
        if(user == null)
            return null;
        User friend = userInter.byId(friendId);
        if(friend == null)
            return null;
        List<String> members = Arrays.asList(user.getId(), friend.getId());
        User admin = userInter.byId("admin");
        String sessionId = chatSessionInter.createSession("", admin, members);
        if(sessionId == null)
            return null;
        return chatSessionInter.byId(sessionId);
    }

    static public boolean deleteFriend(String cookie, String chatSessionId) throws SQLException {
        User user = AuthorityManager.GetUser(new Cookie(cookie));
        if(user == null)
            return false;
        ChatSession session = chatSessionInter.byId(chatSessionId);
        if(session == null)
            return false;
        if(!session.getSessionMemberIds().contains(user.getId()) && !session.getOwnerId().contains("admin"))
            return false;
        return chatSessionInter.deleteSession(chatSessionId);
    }

    static public boolean signUp(String userId, String nickname, String passwd) throws SQLException {
        if(userInter.byId(userId) != null)
            return false;
        User user = new User(userId, nickname, passwd);
        return userInter.createUser(user);
    }

    static public boolean createGroup(String cookie, String groupName, List<String> userIds, String id) throws SQLException {
        User user = AuthorityManager.GetUser(new Cookie(cookie));
        if(user == null)
            return false;
        if(id == null)
            chatSessionInter.createSession(groupName, user, userIds);
        else
            chatSessionInter.createSession(groupName, user, userIds, id);
        return true;
    }

    static public boolean updateGroupName(String cookie, String groupId, String groupName) throws SQLException {
        User user = AuthorityManager.GetUser(new Cookie(cookie));
        if (user == null)
            return false;
        ChatSession session = chatSessionInter.byId(groupId);
        if(session == null)
            return false;
        if(!session.getOwnerId().contains(user.getId()))
            return false;
        return chatSessionInter.updateSessionName(groupId, groupName);
    }

    static public boolean updateGroupMember(String cookie, String groupId, List<String> userIds) throws SQLException {
        User user = AuthorityManager.GetUser(new Cookie(cookie));
        if (user == null)
            return false;
        ChatSession session = chatSessionInter.byId(groupId);
        if(session == null)
            return false;
        if(!session.getOwnerId().contains(user.getId()))
            return false;
        return chatSessionInter.updateSessionMember(groupId, userIds);
    }

    static public boolean moveGroupOwner(String cookie, String groupId, String newOwnerId) throws SQLException {
        User user = AuthorityManager.GetUser(new Cookie(cookie));
        if (user == null)
            return false;
        ChatSession session = chatSessionInter.byId(groupId);
        if(session == null)
            return false;
        if(!session.getOwnerId().contains(user.getId()))
            return false;
        return chatSessionInter.updateSessionOwner(groupId, newOwnerId);
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
