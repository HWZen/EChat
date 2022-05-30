package server;

import entity.Cookie;

import javax.websocket.Session;
import java.util.HashMap;
import java.util.Map;

public class ConnectManager {
    private static final Map<Session, Cookie> connectMap = new HashMap<>();


    public static boolean addConnect(Session session, Cookie cookie) {
        if(AuthorityManager.GetUser(cookie) != null) {
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

    public static Cookie getCookie(Session session) {
        return connectMap.get(session);
    }

    public static boolean isConnect(Session session) {
        return connectMap.containsKey(session);
    }

    public static Session getSession(Cookie cookie) {
        if(cookie == null)
            return null;
        for (Map.Entry<Session, Cookie> entry : connectMap.entrySet()) {
            if (entry.getValue().str.equals(cookie.str)) {
                return entry.getKey();
            }
        }
        return null;
    }

}
