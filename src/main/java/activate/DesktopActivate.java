package activate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import entity.ChatSession;
import entity.Msg;
import entity.User;
import server.CommandManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

public class DesktopActivate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            JSONObject json = (JSONObject) req.getAttribute("json");
            if(json == null){
                InputStreamReader in = new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(in);
                StringBuilder sb = new StringBuilder();
                String jsonStr = null;
                while((jsonStr = reader.readLine()) != null) {
                    sb.append(jsonStr);
                }
                reader.close();
                jsonStr = sb.toString();
                json = JSON.parseObject(jsonStr);
            }
            if(json == null){
                resp.getWriter().write("{\"res\":\"no json\"}");
                return;
            }

            req.setAttribute("json", json);

            String requireType = json.getString("requireType");
            if(requireType == null)
                requireType = "init";
            switch (requireType){
                case "init":
                case "login":
                    init(req, resp);
                    break;
                case "getSessionMsgs":
                    getChatSessionMsgs(req, resp);
                    break;
                default:
                    throw new RuntimeException("unknown requireType: " + requireType);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void init(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        JSONObject json = (JSONObject) req.getAttribute("json");
        assert json != null;
        String browser_uid = json.getString("browser_uid");
        if(browser_uid == null){
            resp.getWriter().write("{\"res\":\"no uid\"}");
            return;
        }
        User user = CommandManager.getUser(browser_uid);
        if(user == null){
            resp.getWriter().write("{\"res\":\"no login\"}");
            return;
        }
        List<ChatSession> sessions = CommandManager.getChatSessions(browser_uid);
        List<Msg> msgs;
        if(sessions == null || sessions.size() == 0)
            msgs = null;
        else
            msgs = CommandManager.getSessionMsg(browser_uid, sessions.get(0).getId());

        JSONObject res = new JSONObject();
        res.put("res", "success");
        res.put("user", user);
        res.put("chatSessionList", sessions);
        res.put("msgs", msgs);
        System.out.println(res.toJSONString());
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write(res.toJSONString());
    }

    public void getChatSessionMsgs(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        JSONObject json = (JSONObject) req.getAttribute("json");
        assert json != null;
        String browser_uid = json.getString("browser_uid");
        if(browser_uid == null){
            resp.getWriter().write("{\"res\":\"no uid\"}");
            return;
        }
        User user = CommandManager.getUser(browser_uid);
        if(user == null){
            resp.getWriter().write("{\"res\":\"no login\"}");
            return;
        }
        String session_id = json.getString("sessionId");
        if(session_id == null){
            resp.getWriter().write("{\"res\":\"no session_id\"}");
            return;
        }
        List<Msg> msgs = CommandManager.getSessionMsg(browser_uid, session_id);
        JSONObject res = new JSONObject();
        res.put("res", "success");
        res.put("msgs", msgs);
        System.out.println(res.toJSONString());
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write(res.toJSONString());
    }
}
