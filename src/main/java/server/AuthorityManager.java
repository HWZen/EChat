package server;

import entity.Cookie;
import entity.User;
import java.util.HashMap;
import java.util.Map;

public class AuthorityManager {

    private static final Map<User, Cookie> authenticatedLogin = new HashMap<>();

    public static boolean isLogin(User user) {
        return authenticatedLogin.containsKey(user);
    }

    public static boolean addAuthenticatedLogin(User user, String cookie) {
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
            if (entry.getValue().str.equals(cookie.str)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
