package server;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/websocketTest/{userId}")
public class serverTest {
    private static final Map<Session, String> users = new HashMap<>();
    // new Connection
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        serverTest.users.put(session, userId);
        System.out.println("Connected to user " + userId);
    }

    // close Connection
    @OnClose
    public void onClose(Session session) {
        System.out.println("Disconnected from user " + serverTest.users.get(session));
        serverTest.users.remove(session);
    }

    // receive message
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Received message from " + serverTest.users.get(session) + ": " + message);
        session.getAsyncRemote().sendText("Received: " + message);
    }

    // error
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("Error: " + error.getMessage());
    }
}
