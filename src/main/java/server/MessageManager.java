package server;


import entity.*;


public class MessageManager {

    private static MsgInter msgInter;

    static public void recvMessage(Msg msg) {
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
