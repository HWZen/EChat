package activate;

import entity.Msg;
import entity.ChatSession;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
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
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("message post");
        System.out.println(topic);
        request.setAttribute("messages", msgs);
        request.setAttribute("TopicList", topics);
        request.setAttribute("topicName",topic);
        request.getRequestDispatcher("/WEB-INF/Pages/chat.jsp").forward(request, response);
    }
}
