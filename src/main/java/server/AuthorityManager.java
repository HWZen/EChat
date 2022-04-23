package server;

import entity.Cookie;
import entity.User;
import entity.UserInter;
import entity.sql.UserInterImpl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AuthorityManager {

    private static UserInter userGetor;

    static {
        try {
            userGetor = new UserInterImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static final Map<User, Cookie> authenticatedLogin = new HashMap<>();

    public static boolean isLogin(User user) {
        return authenticatedLogin.containsKey(user);
    }

    public static boolean addAuthenticatedLogin(String userName, String password, String cookie) throws SQLException {
        User user = userGetor.byId(userName);
        if (user == null) {
            return false;
        }
        authenticatedLogin.put(user, new Cookie(cookie));
        return true;
    }

    public static void removeAuthenticatedLogin(User user) {
        authenticatedLogin.remove(user);
    }

    public static Cookie getCookie(User user) {
        return authenticatedLogin.getOrDefault(user, null);
    }


    public static User GetUser(Cookie cookie) {
        for (Map.Entry<User, Cookie> entry : authenticatedLogin.entrySet()) {
            if (entry.getValue().equals(cookie)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
