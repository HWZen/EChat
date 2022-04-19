package entity.sql;


import entity.ChatGroup;
import entity.GetChatGroup;

public class GetChatGroupImpl extends SQLDriver implements GetChatGroup
{
    @Override
    public ChatGroup byId(int id) {
        return null;
    }

    @Override
    public ChatGroup byName(String name) {
        return null;
    }
}
