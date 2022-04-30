import entity.*;
import entity.sql.ChatSessionInterImpl;
import entity.sql.UserInterImpl;
import org.testng.annotations.Test;
import server.CommandManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Test
public class GroupTest {

    private final ChatSessionInter chatSessionInter = new ChatSessionInterImpl();
    private final UserInter userInter = new UserInterImpl();
    private final String cookie = "TEST_COOKIE";


    public GroupTest() throws SQLException {
    }

    public void createGroup() throws SQLException {
        System.out.println("createGroup");
        UserInter userInter = new UserInterImpl();
        User[] users = new User[3];

        for (int i = 0; i < 3; i++) {
            users[i] = userInter.byId("test" + i + "@qq.com");
            if (users[i] == null) {
                users[i] = new User("test" + i + "@qq.com", "test" + i + "Account", "123456");
                userInter.createUser(users[i]);
            }
        }

        List<String> members = new ArrayList<>();
        members.add("test0@qq.com");
        members.add("test1@qq.com");
        members.add("test2@qq.com");


        assert CommandManager.login("test0@qq.com", "123456", cookie);

        ChatSession cs = chatSessionInter.byId("-1");
        if(cs != null){
            chatSessionInter.deleteSession("-1");
        }

        assert CommandManager.createGroup(cookie, "testGroup", members, "-1");

        ChatSession chatSession = chatSessionInter.byId("-1");
        assert chatSession != null;
        assert chatSession.getSessionMembers().size() == 3;
        assert chatSession.getOwnerId().equals(users[0].getId());
    }

    public void deleteGroup() throws SQLException {
        System.out.println("deleteGroup");
        ChatSessionInter chatSessionInter = new ChatSessionInterImpl();
        ChatSession chatSession = chatSessionInter.byId("-1");
        assert chatSession != null;
        assert CommandManager.deleteGroup(cookie, "-1");
        assert chatSessionInter.byId("-1") == null;


        User user = userInter.byId(chatSession.getOwnerId());
        assert user != null;

        chatSessionInter.createSession(chatSession.getSessionName(), user, chatSession.getSessionMemberIds(),"-1");

    }

    public void updateGroup() throws SQLException {
        System.out.println("editGroup");
        ChatSession cs = chatSessionInter.byId("-1");
        assert cs != null;

        assert chatSessionInter.updateSessionName("-1", "testGroup_rename");

        assert  chatSessionInter.byId("-1").getSessionName().equals("testGroup_rename");

        assert chatSessionInter.updateSessionName("-1", "testGroup");

        assert chatSessionInter.byId("-1").getSessionName().equals("testGroup");

        List<String> members = cs.getSessionMemberIds();
        String tmp = members.get(2);
        members.remove(tmp);
        assert chatSessionInter.updateSessionMember(cs.getId(), members);
        assert chatSessionInter.byId("-1").getSessionMemberIds().size() == 2;

        members.add(tmp);

        assert chatSessionInter.updateSessionMember(cs.getId(), members);

    }


}