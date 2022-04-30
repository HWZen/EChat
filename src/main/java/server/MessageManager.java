package server;


import entity.*;
import entity.sql.MsgInterImpl;

import java.sql.SQLException;


public class MessageManager {

    private static final MsgInter msgInter = new MsgInterImpl();

    static public void recvMessage(Msg msg) throws SQLException {
        msgInter.add(msg);
        if(msg.getTo() != null) {
            msgInter.add(msg);
            ChatSession group = msg.getTo();
            for(User user : group.getSessionMembers()) {
                if(!user.equals(msg.getFrom())) {
                    if(AuthorityManager.isLogin(user)){
                        WebsocketDriver.sendMessage(msg, user);
                    }
                }
            }
        }
    }




}
