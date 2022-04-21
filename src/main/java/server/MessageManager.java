package server;


import entity.*;


public class MessageManager {

    private static MsgInter msgInter;

    static public void recvMessage(Msg msg) {
        // TODO: 处理一些系统指令，比如关闭连接、登出
        if(msg.getTo() != null) {
            msgInter.add(msg);
            // TODO: 处理群发
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
