package entity;

public interface UserInter {

    User byId(String id);

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(User user);
}
