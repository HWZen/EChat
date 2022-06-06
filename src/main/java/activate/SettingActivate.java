package activate;

import entity.ChatSession;
import entity.User;
import server.CommandManager;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SettingActivate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("in setting activate");
        try {
            String requireType = req.getParameter("requireType");
            if(requireType == null || requireType.equals("init"))
                init(req, resp);
            else
                throw new RuntimeException("unknown requireType: " + requireType);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void init(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String browser_uid = req.getParameter("browser_uid");
        for(Cookie c : req.getCookies()) {
            if(c.getName().equals("browser_uid"))
                browser_uid = c.getValue();
        }
        assert browser_uid != null;
        User user = CommandManager.getUser(browser_uid);
        assert user != null;
        List<ChatSession> chatSessions = CommandManager.getChatSessions(browser_uid);
        req.setAttribute("user", user);
        req.setAttribute("chatSessions", chatSessions);
        req.getRequestDispatcher("/WEB-INF/pages/setting.jsp").forward(req, resp);
    }
}
