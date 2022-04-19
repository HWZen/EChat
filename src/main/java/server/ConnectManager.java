package server;

import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConnectManager {
    private static final Map<Session, String> connectMap = new HashMap<>();


    public static boolean addConnect(Session session, String cookie) {
        if(AuthorityManager.getCookie(cookie).equals(cookie)) {
            connectMap.put(session, cookie);
            return true;
        }
        else{
            System.out.println("cookie error");
            return false;
        }

    }

    public static void removeConnect(Session session) {
        connectMap.remove(session);
    }

    public static String getUserName(Session session) {
        return connectMap.get(session);
    }

    public static boolean isConnect(Session session) {
        return connectMap.containsKey(session);
    }

    public static Session getSession(String userName) {
        for (Map.Entry<Session, String> entry : connectMap.entrySet()) {
            if (entry.getValue().equals(userName)) {
                return entry.getKey();
            }
        }
        return null;
    }

}
