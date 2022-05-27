package activate;

import entity.Msg;
import entity.ChatSession;
import server.CommandManager;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageActivate extends HttpServlet {
    private boolean flag=false;
    private String topic="";
    private List<Msg> msgs = new ArrayList<Msg>();
    private List<ChatSession> topics = new ArrayList<ChatSession>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("message get");
        String tmp = (String)request.getParameter("topicName");
        if(tmp != null) topic=tmp;
        String new_msg = (String)request.getParameter("send");
        if(new_msg != null) {
            Msg cur = new Msg("1", "1", new Date(), new_msg);
            msgs.add(cur);
            request.setAttribute("messages", msgs);
        }
//        doPost(request,response);
        System.out.println(topic);
        request.setAttribute("messages", msgs);
        request.setAttribute("TopicList", topics);
        request.setAttribute("topicName",topic);
        request.getRequestDispatcher("/WEB-INF/pages/chat.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Boolean isFirst = (Boolean)request.getAttribute("firstLogin");
        if(isFirst == null || isFirst){
            Cookie[] cookies = request.getCookies();
            String browser_uid = null;
            for (Cookie c : cookies) {
                if(c.getName().equals("browser_uid")) {
                    browser_uid = c.getValue();
                    break;
                }
            }
            if(browser_uid == null) {
                throw new ServletException("No browser_uid");
            }
            try {
                topics = CommandManager.getChatSessions(browser_uid);
                assert topics != null;
                if(topics.size() > 0){
                    topic = topics.get(0).getSessionName();
                    if(topic.equals("")){

                        topic = topics.get(0).getSessionMemberIds().get(0) == request.getAttribute("userId") ? topics.get(0).getSessionMemberIds().get(1) : topics.get(0).getSessionMemberIds().get(0);
                        msgs = CommandManager.getSessionMsg(browser_uid, topics.get(0).getSessionName());
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute("firstLogin", false);
        }
        System.out.println("message post");
        System.out.println(topic);
        request.setAttribute("messages", msgs);
        request.setAttribute("TopicList", topics);
        request.setAttribute("topicName",topic);
        request.getRequestDispatcher("/WEB-INF/pages/chat.jsp").forward(request, response);
    }
}


