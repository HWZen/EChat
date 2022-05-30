package activate;

import entity.ChatSession;
import entity.Msg;
import entity.User;
import server.CommandManager;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static server.CommandManager.login;

public class BaseChatActivate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String requireType = req.getParameter("requireType");

            if (requireType == null || requireType.equals("init")) {
                init(req, resp);
            } else if (requireType.equals("getChatSessionMsg")) {
                getChatSessionMsg(req, resp);
            } else if (requireType.equals("addFriend")) {
                addFriend(req, resp);
            } else if (requireType.equals("logout")) {
                logout(req, resp);
            } else {
                throw new RuntimeException("unknown requireType: " + requireType);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void init(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ServletException {
        Cookie cookies[] = req.getCookies();
        if(cookies == null || cookies.length < 2){
            resp.sendRedirect("/EChat_Web_exploded");
            return;
        }
        String browser_uid = null;
        for (Cookie c : cookies) {
            if(c.getName().equals("browser_uid")){
                browser_uid = c.getValue();
                break;
            }
        }
        assert browser_uid != null;
        User user = CommandManager.getUser(browser_uid);
        if(user == null){
            resp.sendRedirect("/EChat_Web_exploded");
            return;
        }
        List<ChatSession> chatSessionList = CommandManager.getChatSessions(browser_uid);


        ChatSession selectChatSession = null;
        List<Msg> msgs = null;
        if(chatSessionList.size() > 0){
            selectChatSession = chatSessionList.get(0);
            msgs = CommandManager.getSessionMsg(browser_uid, selectChatSession.getId());
        }
        if(selectChatSession == null)
            req.setAttribute("noActiveSession", Boolean.TRUE);
        else
            req.setAttribute("noActiveSession", Boolean.FALSE);
        req.setAttribute("user", user);
        req.setAttribute("chatSessionList", chatSessionList);
        req.setAttribute("activeSession", selectChatSession);
        req.setAttribute("messages", msgs);
        req.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(req, resp);

    }

    public void getChatSessionMsg(HttpServletRequest req, HttpServletResponse resp){
        try {
            String sessionId = req.getParameter("sessionId");
            String browser_uid = null;
            for (Cookie c : req.getCookies()) {
                if(c.getName().equals("browser_uid")){
                    browser_uid = c.getValue();
                    break;
                }
            }
            assert browser_uid != null;
            ChatSession selectSession = CommandManager.getChatSession(browser_uid, sessionId);
            assert selectSession != null;
            List<Msg> msgs = CommandManager.getSessionMsg(browser_uid, sessionId);
            req.setAttribute("messages", msgs);
            req.setAttribute("activeSession", selectSession);
            setBaseData(req, resp);
            req.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(req, resp);
        }
        catch (SQLException | IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }

    public void addFriend(HttpServletRequest req, HttpServletResponse resp){
        try {
            String browser_uid = null;
            for (Cookie c : req.getCookies()) {
                if(c.getName().equals("browser_uid")){
                    browser_uid = c.getValue();
                    break;
                }
            }
            assert browser_uid != null;
            String friend_uid = req.getParameter("friendId");
            User user = CommandManager.getUser(browser_uid);
            ChatSession friendSession = CommandManager.addFriend(browser_uid, friend_uid);
            if(user == null || friendSession == null){
                resp.sendRedirect("/");
                return;
            }
            setBaseData(req, resp);
            req.setAttribute("activeSession", friendSession);
            req.setAttribute("messages", new ArrayList<>());
            req.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(req, resp);
        }
        catch (SQLException | IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }

    public void setBaseData(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        Cookie cookies[] = req.getCookies();
        if(cookies == null || cookies.length < 2){
            resp.sendRedirect("/EChat_Web_exploded");
            return;
        }
        String browser_uid = null;
        for (Cookie c : cookies) {
            if(c.getName().equals("browser_uid")){
                browser_uid = c.getValue();
                break;
            }
        }
        assert browser_uid != null;
        User user = CommandManager.getUser(browser_uid);
        if(user == null){
            resp.sendRedirect("/EChat_Web_exploded");
            return;
        }
        List<ChatSession> chatSessionList = CommandManager.getChatSessions(browser_uid);

        req.setAttribute("user", user);
        req.setAttribute("chatSessionList", chatSessionList);
        req.setAttribute("noActiveSession", Boolean.FALSE);
    }

    public void logout(HttpServletRequest req, HttpServletResponse resp){
        try {
            String browser_uid = null;
            for (Cookie c : req.getCookies()) {
                if(c.getName().equals("browser_uid")){
                    browser_uid = c.getValue();
                    break;
                }
            }
            assert browser_uid != null;
            CommandManager.logout(browser_uid);
            resp.sendRedirect("/EChat_Web_exploded");
        }
        catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
