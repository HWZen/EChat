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
import java.util.Collections;
import java.util.List;

public class PersonalSettingActivate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            String requireType = req.getParameter("requireType");
            if(requireType == null || requireType.equals("init"))
                init(req, resp);
            else if(requireType.equals("changeName"))
                changeName(req, resp);
            else if(requireType.equals("changePasswd"))
                changePasswd(req, resp);
            else if (requireType.equals("addFriend"))
                addFriend(req, resp);
            else if (requireType.equals("createGroup"))
                CreateGroup(req, resp);
            else if(requireType.equals("delGroup"))
                delGroup(req, resp);
            else if(requireType.equals("deleteFriend"))
                deleteFriend(req, resp);
            else
                throw new RuntimeException("unknown requireType: " + requireType);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void init(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String browser_uid = req.getParameter("browser_uid");
        browser_uid = CommandManager.getAndCheckCookie(req, browser_uid);
        User user = CommandManager.getUser(browser_uid);
        if(user == null)
            resp.sendRedirect("EChat_Web_exploded");
        else {
            List<ChatSession> chatSessions = CommandManager.getChatSessions(browser_uid);
            req.setAttribute("user", user);
            req.setAttribute("chatSessions", chatSessions);
            req.getRequestDispatcher("/WEB-INF/pages/personalSetting.jsp").forward(req, resp);
        }
    }

    protected void changeName(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {

        String newName = req.getParameter("name");
        if(newName == null){
            System.out.println("newName is null");
            init(req, resp);
        }
        String browser_uid = req.getParameter("browser_uid");
        browser_uid = CommandManager.getAndCheckCookie(req, browser_uid);
        User user = CommandManager.getUser(browser_uid);
        if(user == null){
            System.out.println("user is null");
            resp.sendRedirect("EChat_Web_exploded");
            return;
        }
        user.setNickname(newName);
        CommandManager.updateUser(browser_uid, user);
        init(req, resp);

    }

    protected void changePasswd(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {

        String oldPasswd = req.getParameter("opwd");
        String newPasswd = req.getParameter("npwd");
        assert oldPasswd != null;
        assert newPasswd != null;

        String browser_uid = req.getParameter("browser_uid");
        browser_uid = CommandManager.getAndCheckCookie(req, browser_uid);
        User user = CommandManager.getUser(browser_uid);
        if(user == null){
            System.out.println("user is null");
            resp.sendRedirect("EChat_Web_exploded");
            return;
        }
        if(!user.getPassword().equals(oldPasswd)){
            System.out.println("old passwd error");
            init(req, resp);
        }

        user.setPassword(newPasswd);
        CommandManager.updateUser(browser_uid, user);
        init(req, resp);
    }

    public void addFriend(HttpServletRequest req, HttpServletResponse resp){
        try {
            String friend_uid = req.getParameter("friendId");
            String browser_uid = req.getParameter("browser_uid");
            browser_uid = CommandManager.getAndCheckCookie(req, browser_uid);
            User user = CommandManager.getUser(browser_uid);
            if(user == null){
                System.out.println("user is null");
                resp.sendRedirect("EChat_Web_exploded");
                return;
            }
            ChatSession friendSession = CommandManager.addFriend(browser_uid, friend_uid);
            assert friendSession != null;
            init(req, resp);
        }
        catch (SQLException | IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }

    public void CreateGroup(HttpServletRequest req, HttpServletResponse resp){
        try {
            String groupName = req.getParameter("groupName");
            String browser_uid = req.getParameter("browser_uid");
            browser_uid = CommandManager.getAndCheckCookie(req, browser_uid);
            User user = CommandManager.getUser(browser_uid);
            if(user == null){
                System.out.println("user is null");
                resp.sendRedirect("/EChat_Web_exploded");
                return;
            }
            String groupId = CommandManager.createGroup(browser_uid, groupName, Collections.singletonList(user.getId()),null);
            assert groupId != null;
            init(req, resp);
        }
        catch (SQLException | IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }

    public  void delGroup(HttpServletRequest req, HttpServletResponse resp){
        try {
            String groupId = req.getParameter("groupId");
            String browser_uid = req.getParameter("browser_uid");
            browser_uid = CommandManager.getAndCheckCookie(req, browser_uid);
            User user = CommandManager.getUser(browser_uid);
            if(user == null){
                System.out.println("user is null");
                resp.sendRedirect("/EChat_Web_exploded");
                return;
            }
            if(!CommandManager.deleteGroup(browser_uid, groupId))
                System.out.println("delete group failed");
            init(req, resp);
        }
        catch (SQLException | IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteFriend(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {

        String friendSessionId = req.getParameter("sessionId");
        String browser_uid = req.getParameter("browser_uid");
        browser_uid = CommandManager.getAndCheckCookie(req, browser_uid);
        User user = CommandManager.getUser(browser_uid);
        if(user == null){
            System.out.println("user is null");
            resp.sendRedirect("EChat_Web_exploded");
            return;
        }
        if(!CommandManager.deleteFriend(browser_uid, friendSessionId))
            System.out.println("delete friend failed");
        init(req, resp);
    }
}
