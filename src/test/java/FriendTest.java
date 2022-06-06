import entity.*;
import entity.sql.ChatSessionInterImpl;
import entity.sql.MsgInterImpl;
import entity.sql.UserInterImpl;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Test
public class FriendTest {

    private final ChatSessionInter chatSessionInter = new ChatSessionInterImpl();
    private final UserInter userInter = new UserInterImpl();
    private final MsgInter msgInter = new MsgInterImpl();
    private final String cookie = "TEST_COOKIE";

    public FriendTest() throws SQLException {
    }

    @Test
    public void addAndDeleteFriendTest() throws SQLException {
        System.out.println("addFriendTest");
        User user = userInter.byId("test@qq.com");
        if (user == null) {
            user = new User("test@qq.com", "TestAccount", "123456");
            userInter.createUser(user);

        }

        User friend = new User("friend@qq.com", "YourFriend", "123456");
        if (userInter.byId("friend@qq.com") != null) {
            userInter.deleteUser(friend);
        }
        userInter.createUser(friend);

        List<String> members = new ArrayList<>(Arrays.asList(user.getId(), friend.getId()));
        User admin = userInter.byId("admin");
        String id = chatSessionInter.createSession("", admin, members);
        assert id != null;
        ChatSession session = chatSessionInter.byId(id);
        assert session != null;
        assert session.getSessionMemberIds().size() == 2;
        assert session.getOwnerId().equals("admin");
        assert session.getSessionName().equals("");

        userInter.deleteUser(friend);

        session = chatSessionInter.byId(id);
        assert session == null;
    }
}
