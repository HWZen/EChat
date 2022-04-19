package entity.sql;

import entity.GetUser;
import entity.User;

public class GetUserImpl extends SQLDriver implements GetUser{

    @Override
    public User byId(int id) {
        return null;
    }

    @Override
    public User byName(String name) {
        return null;
    }

    @Override
    public User byEmail(String email) {
        return null;
    }
}
