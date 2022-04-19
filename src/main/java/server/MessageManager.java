package server;


import entity.Msg;

import static entity.Msg.AgentType;

public class MessageManager {
    static public void recvMessage(Msg msg) {
        // TODO: 处理一些系统指令，比如关闭连接、登出
        if(msg.getFromType() != AgentType.USER)
            return;
        switch (msg.getToType()){
            case USER:
            case GROUP:
                // TODO: 检查发送对象是否在线，在线发送，否则取消；如果是GROUP还要群发
                break;
            case Server:
            default:
                break;
        }
    }

}
