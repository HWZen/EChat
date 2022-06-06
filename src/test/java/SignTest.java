import entity.User;
import entity.UserInter;
import entity.sql.UserInterImpl;
import org.testng.annotations.Test;
import server.CommandManager;

import java.sql.SQLException;

@Test
public class SignTest {

    public void signUp() throws SQLException {
        UserInter userInter = new UserInterImpl();

        User user = userInter.byId("test@qq.com");

        if(user != null){
            userInter.deleteUser(user);
        }
        System.out.println("Sign up test");
        boolean err = CommandManager.signUp("test@qq.com", "TestAccount", "123456");
        assert err;

        user = userInter.byId("test@qq.com");
        assert user != null;
        assert user.getId().equals("test@qq.com");
        assert user.getPassword().equals("123456");
        assert user.getNickname().equals("TestAccount");

        err = CommandManager.signUp("test@qq.com", "TestAccount", "123456");
        assert !err;
    }

    public void removeUser() throws SQLException {
        UserInter userInter = new UserInterImpl();

        User user = userInter.byId("test@qq.com");
        assert user != null;

        userInter.deleteUser(user);

        User user2 = userInter.byId("test@qq.com");
        assert user2 == null;

        boolean err = userInter.createUser(user);
        assert err;
    }

}
