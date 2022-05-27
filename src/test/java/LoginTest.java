import entity.User;
import entity.UserInter;
import entity.sql.UserInterImpl;
import org.testng.annotations.Test;
import server.AuthorityManager;
import server.CommandManager;
import server.MessageManager;
import server.WebsocketDriver;

import java.sql.SQLException;
import java.util.Date;

@Test
public class LoginTest {
    static String cookie = "TEST_COOKIE";

    @Test
    public void logintest() throws SQLException {
        UserInter userInter = new UserInterImpl();
        User user = userInter.byId("test@qq.com");
        assert user != null;

        assert CommandManager.login("test@qq.com", "123456", cookie);
        assert AuthorityManager.isLogin(user);

    }

    @Test
    public void logouttest() throws SQLException {
        UserInter userInter = new UserInterImpl();
        User user = userInter.byId("test@qq.com");
        assert user != null;

        boolean hasLogin = AuthorityManager.isLogin(user);
        if (!hasLogin) {
            boolean err = CommandManager.login(user.getId(), user.getPassword(), cookie);
            assert err;
            assert AuthorityManager.isLogin(user);
        }

        boolean err = CommandManager.logout("test@qq.com", cookie);
        assert err;
        assert !AuthorityManager.isLogin(user);
    }
}
