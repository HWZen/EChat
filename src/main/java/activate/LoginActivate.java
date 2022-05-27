package activate;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import constant.Globle;
import static server.CommandManager.*;

public class LoginActivate extends HttpServlet {

    private boolean isCorrectCode(String code) {
        if(code == null)
            return false;

        if(Globle.getCode() == null)
            return false;

        if(!Globle.getCode().equalsIgnoreCase(code)){
            return false;
        }
        return true;
    }

    private boolean isCorrectUser(String id, String pwd, String cookie) {
        try {
            if(!login(id, pwd, cookie)) {
                return false;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("login get");
        String id = request.getParameter("name");
        String pwd = request.getParameter("password");
        String code = request.getParameter("code");
        String cookie = id;
        Cookie[] cookies = request.getCookies();
        if(cookies == null) {
            Cookie uid_cookie = new Cookie("uid", id);
            uid_cookie.setMaxAge(24*60*60);    //一天
            response.addCookie(uid_cookie);
        }
        if(!isCorrectCode(code)) {
            request.setAttribute("loginMessage","验证码错误");
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
            return;
        }
        else if(!isCorrectUser(id,pwd,cookie)){
            request.setAttribute("loginMessage","用户名/密码错误");
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
            return;
        }
        else {
            request.setAttribute("userID", id);
            request.setAttribute("userID",id);
            request.getRequestDispatcher("/message.jhtml").forward(request, response);
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("login post");
        doGet(request, response);
    }
}

