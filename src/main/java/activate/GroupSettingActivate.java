package activate;

import entity.ChatSession;
import entity.User;
import server.CommandManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupSettingActivate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            String groupId = req.getParameter("groupId");
            assert groupId != null;
            String requireType = req.getParameter("requireType");
            if(requireType == null || requireType.equals("init"))
                init(req, resp);
            else if(requireType.equals("pick")) {
                String reqID = req.getParameter("requireID");
                if(reqID.equals("createNewGroup")){
                    req.setAttribute("action","create");
                    init(req,resp);
                }
                else {
                    getGroupData(req, resp);
                }
            }
            else if(requireType.equals("changeGroupName"))
                changeGroupName(req, resp);
            else if(requireType.equals("deleteMember"))
                deleteMember(req, resp);
            else if (requireType.equals("addMember"))
                addMember(req, resp);
            else if(requireType.equals("createGroup"))
                createGroup(req, resp);
            else if(requireType.equals("deleteGroup"))
                deleteGroup(req, resp);
            else
                throw new RuntimeException("unknown requireType: " + requireType);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void init(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        String broswer_uid = req.getParameter("browser_uid");
        broswer_uid = CommandManager.getAndCheckCookie(req, broswer_uid);
        User user = CommandManager.getUser(broswer_uid);
        if(user == null){
            resp.sendRedirect("/ECChat_Web_exploded");
            return;
        }
        List<ChatSession> chatSessions = CommandManager.getChatSessions(broswer_uid);
        req.setAttribute("user", user);
        req.setAttribute("chatSessions", chatSessions);
        req.getRequestDispatcher("/WEB-INF/pages/group.jsp").forward(req, resp);
    }

    protected  void changeGroupName(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        String groupid = req.getParameter("groupId");
        String newName = req.getParameter("newName");
        assert newName != null;
        assert groupid != null;
        String browser_uid = req.getParameter("browser_uid");
        browser_uid = CommandManager.getAndCheckCookie(req, browser_uid);
        User user = CommandManager.getUser(browser_uid);
        if(user == null){
            resp.sendRedirect("/EChat_Web_exploded");
            return;
        }
        ChatSession group = CommandManager.getChatSession(browser_uid, groupid);
        assert group != null;
        CommandManager.updateGroupName(browser_uid, groupid, newName);
        init(req, resp);
    }

    protected void createGroup(HttpServletRequest req, HttpServletResponse resp) throws ServletException, SQLException, IOException {
        System.out.println("activated createGroup");
        String groupName = req.getParameter("createGroupName");
        assert groupName != null;
        String browser_uid = req.getParameter("browser_uid");
        browser_uid = CommandManager.getAndCheckCookie(req, browser_uid);
        User user = CommandManager.getUser(browser_uid);
        List<String> userIds = new ArrayList<String>();
        userIds.add(user.getId());
        CommandManager.createGroup(browser_uid,groupName,userIds,null);
        init(req,resp);
    }

    protected  void deleteMember(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        String groupid = req.getParameter("groupId");
        String deleteId = req.getParameter("memberId");
        assert deleteId != null;
        assert groupid != null;
        String browser_uid = req.getParameter("browser_uid");
        browser_uid = CommandManager.getAndCheckCookie(req, browser_uid);
        User user = CommandManager.getUser(browser_uid);
        if(user == null){
            resp.sendRedirect("/EChat_Web_exploded");
            return;
        }
        if(user.getId().equals(deleteId)){
            System.out.println("delete id error");
            init(req, resp);
        }
        ChatSession group = CommandManager.getChatSession(browser_uid, groupid);
        assert group != null;
        List<String> members = group.getSessionMemberIds();
        members.remove(deleteId);
        if(CommandManager.updateGroupMember(browser_uid, groupid, members))
            System.out.println("delete member success");
        init(req, resp);
    }

    public void addMember(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        String browser_uid = req.getParameter("browser_uid");
        browser_uid = CommandManager.getAndCheckCookie(req, browser_uid);
        User user = CommandManager.getUser(browser_uid);
        String groupId = req.getParameter("groupId");
        String memberId = req.getParameter("memberId");
        if(user == null){
            resp.sendRedirect("/EChat_Web_exploded");;
            return;
        }
        ChatSession groupSession = CommandManager.getChatSession(browser_uid, groupId);
        if(groupSession == null){
            resp.sendRedirect("");
            return;
        }
        List<String> groupUsers = groupSession.getSessionMemberIds();
        groupUsers.add(memberId);
        if(CommandManager.updateGroupMember(browser_uid, groupId, groupUsers))
            System.out.println("add member success");
        init(req, resp);
    }

    public void getGroupData(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        String groupID = req.getParameter("requireID");
        String broswer_uid = req.getParameter("browser_uid");
        broswer_uid = CommandManager.getAndCheckCookie(req, broswer_uid);
        User user = CommandManager.getUser(broswer_uid);
        if(user == null){
            resp.sendRedirect("/EChat_Web_exploded");
            return;
        }
        ChatSession group = CommandManager.getChatSession(broswer_uid, groupID);
        System.out.println(group.getSessionName());
        req.setAttribute("action", "showgroup");
        req.setAttribute("groupChat", group);
        init(req,resp);
    }

    public void deleteGroup(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        String groupID = req.getParameter("groupId");
        String broswer_uid = req.getParameter("browser_uid");
        broswer_uid = CommandManager.getAndCheckCookie(req, broswer_uid);
        User user = CommandManager.getUser(broswer_uid);
        if(user == null){
            resp.sendRedirect("/EChat_Web_exploded");
            return;
        }
        ChatSession group = CommandManager.getChatSession(broswer_uid, groupID);
        CommandManager.deleteGroup(broswer_uid,groupID);
        init(req,resp);
    }
}
