package entity;

import java.security.acl.Group;
import java.sql.SQLException;
import java.util.List;

public interface MsgInter {
    List<Msg> bySessionId(String sessionId) throws SQLException;

    List<Msg> bySession(ChatSession session) throws SQLException;

    boolean add(Msg msg) throws SQLException;
}
