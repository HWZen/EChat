package server;

import entity.GetUser;
import entity.sql.GetUserImpl;

import java.util.HashMap;
import java.util.Map;

public class AuthorityManager {
    private static final Map<String, String> authenticatedLogin = new HashMap<>();

    public static boolean isLogin(String userName) {
        return authenticatedLogin.containsKey(userName);
    }

    public static void addAuthenticatedLogin(String userName, String password, String cookie) {
        GetUser userGetor = new GetUserImpl();
        if (userGetor.byName(userName).getPassword().equals(password)) {
            authenticatedLogin.put(userName, cookie);
        }
    }

    public static void removeAuthenticatedLogin(String login) {
        authenticatedLogin.remove(login);
    }

    public static String getCookie(String login) {
        return authenticatedLogin.getOrDefault(login, null);
    }

}
