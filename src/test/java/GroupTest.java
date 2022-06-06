import entity.*;
import entity.sql.ChatSessionInterImpl;
import entity.sql.MsgInterImpl;
import entity.sql.UserInterImpl;
import org.testng.annotations.Test;
import server.CommandManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Test
public class GroupTest {

    private final ChatSessionInter chatSessionInter = new ChatSessionInterImpl();
    private final UserInter userInter = new UserInterImpl();
    private final MsgInter msgInter = new MsgInterImpl();
    private final String cookie = "TEST_COOKIE";


    public GroupTest() throws SQLException {
    }

    @Test
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

        assert CommandManager.createGroup(cookie, "testGroup", members, "-1") != null;

        ChatSession chatSession = chatSessionInter.byId("-1");
        assert chatSession != null;
        assert chatSession.getSessionMembers().size() == 3;
        assert chatSession.getOwnerId().equals(users[0].getId());
    }

    @Test
    public void groupChatTest() throws SQLException {
        int size = msgInter.bySessionId("-1").size();
        Msg msg = new Msg("test0@qq.com", "-1", new Date(), "Hello, I'm test0");
        Msg msg2 = new Msg("test1@qq.com", "-1", new Date(), "Hello, I'm test1");
        assert msgInter.add(msg);
        assert msgInter.add(msg2);

        List<Msg> msgs = msgInter.bySessionId("-1");

        assert msgs.size() == 2 + size;



    }

    @Test
    public void deleteGroup() throws SQLException {
        System.out.println("deleteGroup");
        ChatSessionInter chatSessionInter = new ChatSessionInterImpl();
        ChatSession chatSession = chatSessionInter.byId("-1");
        assert chatSession != null;
        msgInter.add(new Msg("test0@qq.com", "-1", new Date(), "Hello, I'm test0 in delete"));
        assert CommandManager.deleteGroup(cookie, "-1");
        assert chatSessionInter.byId("-1") == null;
        assert msgInter.bySessionId("-1").size() == 0;


        User user = userInter.byId(chatSession.getOwnerId());
        assert user != null;

        chatSessionInter.createSession(chatSession.getSessionName(), user, chatSession.getSessionMemberIds(),"-1");

    }

    @Test
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
