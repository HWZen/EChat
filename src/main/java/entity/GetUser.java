package entity;

public interface GetUser {
    User byId(int id);

    User byName(String name);

    User byEmail(String email);
}
