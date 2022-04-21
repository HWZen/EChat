package entity.sql;

import entity.UserInter;
import entity.User;

import java.sql.Connection;
import java.sql.SQLException;

public class UserInterImpl implements UserInter {
    private Connection SQLConnection;

    public UserInterImpl() throws SQLException {
        SQLDriver driver = new SQLDriver();
        SQLConnection = driver.getConnection();
    }

    @Override
    public User byId(String id) {
        return null;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(User user) {

    }
}
