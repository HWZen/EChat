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
            if(requireType == null || requireType.equals("init") || requireType.equals("pick"))
                init(req, resp);
            else if(requireType.equals("changeName"))
                changeName(req, resp);
            else if(requireType.equals("changePasswd"))
                changePasswd(req, resp);
            else if (requireType.equals("addFriend"))
                addFriend(req, resp);
            else if(requireType.equals("deleteFriend"))
                deleteFriend(req, resp);
            else
                throw new RuntimeException("unknown requireType: " + requireType);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void init(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        System.out.println("init");
        String part = req.getParameter("picked");
        System.out.println(part);
        String browser_uid = req.getParameter("browser_uid");
        browser_uid = CommandManager.getAndCheckCookie(req, browser_uid);
        User user = CommandManager.getUser(browser_uid);
        if(user == null)
            resp.sendRedirect("EChat_Web_exploded");
        else {
            List<ChatSession> chatSessions = CommandManager.getChatSessions(browser_uid);
            req.setAttribute("user", user);
            req.setAttribute("chatSessions", chatSessions);
            System.out.println(chatSessions.size());
            req.setAttribute("selected",part);
            req.getRequestDispatcher("/WEB-INF/pages/user.jsp").forward(req, resp);
        }
    }

    protected void changeName(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        System.out.println("change Name");
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
        req.setAttribute("picked","changeName");
        init(req, resp);
    }

    protected void changePasswd(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        System.out.println("change pwd");
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
            System.out.println("old passwd error"+user.getPassword()+" "+oldPasswd);
            init(req, resp);
        }

        user.setPassword(newPasswd);
        CommandManager.updateUser(browser_uid, user);
        init(req, resp);
    }

    public void addFriend(HttpServletRequest req, HttpServletResponse resp){
        System.out.println("add friend");
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
    public void deleteFriend(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        String friendSessionId = req.getParameter("sessionId");
        System.out.println(friendSessionId);
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
