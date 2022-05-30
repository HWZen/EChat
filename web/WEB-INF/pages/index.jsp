<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: HWZ
  Date: 2022/4/19
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>EChat</title>
</head>
<body>


<jsp:useBean id="activeSession" scope="request" type="entity.ChatSession"/>
<jsp:useBean id="chatSessionList" scope="request" type="java.util.List"/>
<jsp:useBean id="user" scope="request" type="entity.User"/>
<jsp:useBean id="messages" scope="request" type="java.util.List"/>

EChat Demo<br />
<div>Login: ${user.nickname}</div>
<c:forEach items="${chatSessionList}" var="Session">
    <script type="text/javascript">
        console.log(${Session.id});
        console.log("${Session.getSessionMembers()[0].nickname}")
        console.log("${Session.getSessionMembers()[1].nickname}");
        console.log("${Session.sessionMemberIds[0]}");
        console.log("${user.getId()}");
    </script>
    <c:choose>
        <c:when test="${Session.ownerId ne 'admin'}">
            <button onclick="selectChatSession('${Session.id}')">${Session.sessionName}</button>
        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${Session.sessionMemberIds[0] eq user.getId()}">    <!--如果 -->
                    <button onclick="selectChatSession('${Session.id}')">${Session.getSessionMembers()[1].nickname}</button>
                </c:when>
                <c:otherwise>  <!--否则 -->
                    <button onclick="selectChatSession('${Session.id}')">${Session.getSessionMembers()[0].nickname}</button>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
</c:forEach>


<br/>
<br/>
<br />

<input id="text" type="text" />



<button onclick="send()"> Send </button>
<button   onclick="closeWebSocket()"> Close </button>
<div id="message">
    <c:forEach items="${messages}" var="msg">
        ${msg.fromUserId} : ${msg.content} <br/>
    </c:forEach>
</div>

<script type="text/javascript">
    function getCookie(cname)
    {
        const name = cname + "=";
        const ca = document.cookie.split(';');
        for(let i=0; i<ca.length; i++)
        {
            const c = ca[i].trim();
            if (c.indexOf(name)==0) return c.substring(name.length,c.length);
        }
        return "";
    }
    //判断当前浏览器是否支持WebSocket
    let websocket;
    if('WebSocket' in window){
        websocket = new WebSocket("ws://localhost:8080/EChat_Web_exploded/chat/"+getCookie("browser_uid"));
        setMessageInnerHTML("Connect success");
        console.log("link success")

        //连接发生错误的回调方法
        websocket.onerror = function(){
            setMessageInnerHTML("error");
        };

        //连接成功建立的回调方法
        websocket.onopen = function(event){
            setMessageInnerHTML("open");
        }
        console.log("-----")
        //接收到消息的回调方法
        websocket.onmessage = function(event){
            setMessageInnerHTML(event.data);
        }

        //连接关闭的回调方法
        websocket.onclose = function(){
            setMessageInnerHTML("close");
        }

        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
        window.onbeforeunload = function(){
            websocket.close();
        }
    }else{
        setMessageInnerHTML("当前浏览器不支持websocket");
        alert('Not support websocket')
    }


    function connect2server(){

    }

    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML){
        document.getElementById('message').innerHTML += innerHTML + '<br/>';
    }

    //关闭连接
    function closeWebSocket(){
        websocket.close();
    }

    //发送消息
    function send(){
        var message = "{\"from\":\"${user.id}\",\"to\":\"${activeSession.id}\",\"message\":\""+document.getElementById('text').value+"\"}";
        websocket.send(message);
        setMessageInnerHTML(message);
    }

    function selectChatSession(sessionId){
        window.document.getElementById('message').innerHTML = "";
        let form = document.createElement("form");
        form.action = "message.jhtml?requireType=getChatSessionMsg&sessionId="+sessionId;
        form.method = "post";
        document.body.appendChild(form);
        form.submit();
        document.body.removeChild(form);
    }

</script>

</body>
</html>