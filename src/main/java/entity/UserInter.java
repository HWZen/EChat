package entity;

import java.sql.SQLException;

public interface UserInter {

    User byId(String id)throws SQLException;

    boolean addUser(User user)throws SQLException;

    boolean updateUser(User user)throws SQLException;

    boolean deleteUser(User user)throws SQLException;

    boolean leaveSession(User user,String sessionId)throws SQLException;
}
