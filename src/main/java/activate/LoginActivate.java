package activate;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import constant.Globle;
import server.CommandManager;

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
        String requireType = request.getParameter("requireType");
        if(requireType != null && requireType.equals("desktop")){
            desktopRequest(request, response);
            return;
        }
        String id = request.getParameter("name");
        String pwd = request.getParameter("password");
        String code = request.getParameter("code");
        String JSESSIONID = null;
        Cookie[] cookies = request.getCookies();
        String browser_uid = null;
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
        if(browser_uid == null)
            throw new RuntimeException("no browser_uid and create failed");

        if(!isCorrectCode(code)) {
            System.out.println("code error");
            request.setAttribute("loginMessage","验证码错误");
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
            return;
        }
        else if(!isCorrectUser(id,pwd,browser_uid)) {
            System.out.println("user error");
            request.setAttribute("loginMessage","用户名/密码错误");
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
            return;
        }
        else {
            System.out.println("login success");
            request.getRequestDispatcher("/message.jhtml?requireType=init&browser_uid="+browser_uid).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("login post");
        doGet(request, response);
    }

    protected void desktopRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("login desktop");
        if(!request.getHeader("content-type").equals("application/json")) {
            System.out.println("content-type error");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        InputStreamReader in = new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(in);
        StringBuilder sb = new StringBuilder();
        String jsonStr = null;
        while((jsonStr = reader.readLine()) != null) {
            sb.append(jsonStr);
        }
        reader.close();
        jsonStr = sb.toString();
        if(jsonStr == null) {
            System.out.println("desktop login error");
            response.setStatus(400);
            return;
        }
        System.out.println(jsonStr);
        JSONObject json = JSON.parseObject(jsonStr);
        String id = json.getString("username");
        String pwd = json.getString("password");
        System.out.println(id + " " + pwd);
        String browser_uid = json.getString("browser_uid");
        if(browser_uid == null) {
            System.out.println("desktop login error");
            response.setStatus(400);
            response.getWriter().write("{\"res\":\"no uid\"}");
            return;
        }
        if(!isCorrectUser(id,pwd,browser_uid)) {
            System.out.println("desktop login error");
            response.setStatus(400);
            response.getWriter().write("{\"res\":\"id or passwd error\"}");
            return;
        }
        System.out.println("desktop login success");
        request.setAttribute("json", json);
        request.getRequestDispatcher("/desktop.jhtml").forward(request, response);
    }
}

