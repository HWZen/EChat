package activate;

import server.CommandManager;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RegisterActivate extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter("name");
            String pwd = request.getParameter("password");
            if(!CommandManager.signUp(id, id, pwd)) {
                System.out.println("sign up failed");
                response.sendRedirect("/EChat_Web_exploded");
            }
            Cookie cookies[] = request.getCookies();
            String browser_uid = null;
            String JSESSIONID = null;
            for(Cookie c : cookies) {
                if(c.getName().equals("browser_uid"))
                    browser_uid = c.getValue();
                if(c.getName().equals("JSESSIONID"))
                    JSESSIONID = c.getValue();
            }
            if(browser_uid == null) {
                browser_uid = JSESSIONID;
                Cookie uid_cookie = new Cookie("browser_uid", browser_uid);
                uid_cookie.setMaxAge(24*60*60);    //一天
                response.addCookie(uid_cookie);
            }
            if(!CommandManager.login(id, pwd, browser_uid))
                throw new RuntimeException("login failed");
            request.getRequestDispatcher("/message.jhtml?requireType=init&browser_uid="+browser_uid).forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
