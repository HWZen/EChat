package entity;

import java.security.acl.Group;
import java.util.List;

public interface MsgInter {
    List<Msg> byGroup(Group group);

    void add(Msg msg);
}
