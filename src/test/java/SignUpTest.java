import org.testng.annotations.Test;
import server.CommandManager;

import java.sql.SQLException;

@Test
public class SignUpTest {

    public void signUp() throws SQLException {
        System.out.println("Sign up test");
        CommandManager.signUp("1041453977@qq.com", "HWZen", "123456");
    }


}
