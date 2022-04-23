package entity;

import java.security.acl.Group;
import java.util.List;

public interface MsgInter {
    List<Msg> bySessionId(String sessionId);

    List<Msg> bySession(ChatSession session);

    void add(Msg msg);
}
