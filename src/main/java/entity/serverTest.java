package entity;

import java.io.IOException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/websocketTest/{userId}")
public class serverTest {
    private String userId;

    // new Connection
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.userId = userId;
        System.out.println("Connected to user " + userId);
    }

    // close Connection
    @OnClose
    public void onClose(Session session) {
        System.out.println("Disconnected from user " + userId);
    }

    // receive message
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Received message: " + message);
    }

    // error
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("User: " + userId);
        System.out.println("Error: " + error.getMessage());
    }

}
