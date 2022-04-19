package server;

import com.alibaba.fastjson.JSONObject;
import entity.Msg;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


@ServerEndpoint("/chat/{Cookie}")
public class WebsocketDriver {
    @OnOpen
    // 接受一个Cookie, 作为一台电脑（浏览器）的标识， 后端会记录Cookie和登录用户的关系
    public void onOpen(Session session, @PathParam("Cookie") String cookie) throws IOException {
        System.out.println("Connected: " + session.getId());
        if(!ConnectManager.addConnect(session, cookie)){
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
    *       "to": "发送对象",
    *       "message": "消息内容"
    *   }
    * */
    @OnMessage
    public void onMessage(String message, Session session){
        JSONObject object = JSONObject.parseObject(message);

    }

    public void sendMessage(Msg msg){
        // TODO: 找到msg.to的session，把消息封装成json，向session发送json

    }
}
