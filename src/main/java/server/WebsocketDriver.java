package server;

import com.alibaba.fastjson.JSONObject;
import entity.*;
import entity.sql.ChatSessionInterImpl;
import entity.sql.UserInterImpl;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.sql.SQLException;


@ServerEndpoint("/chat/{Cookie}")
public class WebsocketDriver {

    private static ChatSessionInter chatSessionInter;
    private static UserInter userInter;

    static {
        try {
            chatSessionInter = new ChatSessionInterImpl();
            userInter = new UserInterImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static MsgInter msgInter;

    @OnOpen
    // 接受一个Cookie, 作为一台电脑（浏览器）的标识， 后端会记录Cookie和登录用户的关系
    public void onOpen(Session session, @PathParam("Cookie") String cookie) throws IOException {
        System.out.println("Connected: " + session.getId());
        if(!ConnectManager.addConnect(session, new Cookie(cookie))) {
            session.getAsyncRemote().sendText("Cookie is not verify");
            session.close();
        }
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Disconnected: " + session.getId());
        ConnectManager.removeConnect(session);
    }

    /*
    * 消息制式：JSON
    *   {
    *      "from": "发送者ID",
    *      "to": "发送对象ID",
    *      "message": "消息内容"
    *   }
    * */
    @OnMessage
    public void onMessage(String message, Session session){
        JSONObject object = JSONObject.parseObject(message);
        User user = userInter.byId(object.getString("from"));
        if(user == null){
            session.getAsyncRemote().sendText("\"from\" is not exist");
            throw new RuntimeException("User not found: " + object.getString("from"));
        }
        ChatSession chatSession = chatSessionInter.byId(object.getString("to"));
        Msg msg = new Msg(user, chatSession, object.getString("message"));
        MessageManager.recvMessage(msg);
    }

    public static void sendMessage(Msg msg, User receiver){
        Cookie cookie = AuthorityManager.getCookie(receiver);
        if(cookie == null) {
            throw new RuntimeException("Cookie not found, User: " + receiver.getNickname());
        }
        Session session = ConnectManager.getSession(cookie);
        if(session == null){
            // out of line
            return;
        }
        if(session.getAsyncRemote() == null){
            throw new RuntimeException("Session.getAsyncRemote() is null, User: " + receiver.getNickname());
        }
        JSONObject object = new JSONObject();
        object.put("from", msg.getFrom().getId());
        object.put("to", msg.getTo().getId());
        object.put("message", msg.getMsg());
        session.getAsyncRemote().sendText(object.toJSONString());
    }
}
