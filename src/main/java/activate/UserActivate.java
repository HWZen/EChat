package activate;

import entity.User;
import server.CommandManager;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

public class UserActivate extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("user activate");
        try{
            String requireType = req.getParameter("requireType");
            if (requireType == null || requireType.equals("init")) {
                init(req, resp);
            }
            else if(requireType.equals("setContent")) {
                setContent(req,resp);
            }
            else if(requireType.equals("ChangePwd")) {
                ChangePwd(req, resp);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void init(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        String browser_uid = null;
        Cookie cookies[] = req.getCookies();
        for (Cookie c : cookies) {
            if(c.getName().equals("browser_uid")){
                browser_uid = c.getValue();
                break;
            }
        }
        User user= CommandManager.getUser(browser_uid);
        if(user == null){
            req.setAttribute("loginMessage","请重新登录");
            resp.sendRedirect("/EChat_Web_exploded");
            return;
        }
        req.setAttribute("uid",user.getId());
        req.getRequestDispatcher("/WEB-INF/pages/user.jsp").forward(req,resp);
    }

    protected void setContent(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        String picked = req.getParameter("picked");
        if(picked.equals("userContent")) {
            String browser_uid = null;
            for (Cookie c : req.getCookies()) {
                if(c.getName().equals("browser_uid")){
                    browser_uid = c.getValue();
                    break;
                }
            }
            assert browser_uid != null;
            User user = CommandManager.getUser(browser_uid);
            req.setAttribute("userName",user.getId());
        }
    }

    protected void ChangePwd(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        String oldpwd = req.getParameter("old");
        String newpwd = req.getParameter("new");

        assert oldpwd != null;
        assert newpwd != null;

        String browser_uid = req.getParameter("browser_uid");
        browser_uid = CommandManager.getAndCheckCookie(req, browser_uid);
        User user = CommandManager.getUser(browser_uid);

        if(user == null){
            System.out.println("user is null");
            resp.sendRedirect("EChat_Web_exploded");
            return;
        }
        if(!user.getPassword().equals(oldpwd)){
            System.out.println("old passwd error");
            init(req, resp);
        }

        user.setPassword(newpwd);
        CommandManager.updateUser(browser_uid, user);
        init(req, resp);
    }
}
