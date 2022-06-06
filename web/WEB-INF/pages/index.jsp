<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
<jsp:useBean id="chatSessionList" scope="request" type="java.util.List"/>
<jsp:useBean id="user" scope="request" type="entity.User"/>
<jsp:useBean id="noActiveSession" scope="request" type="java.lang.Boolean"/>
<c:if test="${noActiveSession eq false}">
    <jsp:useBean id="activeSession" scope="request" type="entity.ChatSession"/>
    <jsp:useBean id="messages" scope="request" type="java.util.List"/>
</c:if>
<img src="<c:url value="/resoure/logo1.png"/>" alt="logo"><br/>
EChat Demo<br />
<div>Login: ${user.nickname}</div>
<button onclick="logout()">Logout</button> <button onclick="setting()">Setting</button>
<div>Chat Session: ${chatSessionList.size()} Session</div>
<c:forEach items="${chatSessionList}" var="Session">
    <c:choose>
        <c:when test="${Session.ownerId ne 'admin'}">
            <button onclick="selectChatSession('${Session.id}')">${Session.sessionName}(id:${Session.id})</button>
        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${Session.sessionMemberIds[0] eq user.getId()}">
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


<br/>
<br/>
<br/>
<c:if test="${noActiveSession eq false}">
<c:choose>
    <c:when test="${activeSession.ownerId ne 'admin'}">
        <div>Now chat in ${activeSession.sessionName}</div>
    </c:when>
    <c:otherwise>
        <c:choose>
            <c:when test="${activeSession.sessionMemberIds[0] eq user.getId()}">    <!--如果 -->
                <div>Now chat with ${activeSession.getSessionMembers()[1].nickname}</div>
            </c:when>
            <c:otherwise>  <!--否则 -->
                <div>Now chat with ${activeSession.getSessionMembers()[0].nickname}</div>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>

<div>Send Message</div>
<input id="sendMsg" type="text" />
<button onclick="send()"> Send </button><br/>
<div id="message">
    <c:forEach items="${messages}" var="msg">
        <fmt:formatDate value="${msg.sendTime}" type="both" dateStyle="long" timeStyle="long"/> <br/>
        ${msg.fromUserId} : ${msg.content} <br/>
        <br/>
    </c:forEach>
</div>
</c:if>

<script type="text/javascript">
    function getFmtDate() {
        const date = new Date();
        const year = date.getFullYear();
        const month = date.getMonth() + 1;
        const day = date.getDate();
        const hour = date.getHours();
        const minute = date.getMinutes();
        const second = date.getSeconds();
        return year + "年" + month + "月" + day + "日 " + hour + "时" + minute + "分" + second + "秒";
    }
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
    if('WebSocket' in window && ${noActiveSession eq false}){
        const hostname = window.document.domain;
        websocket = new WebSocket("ws://"+hostname+":8080/EChat_Web_exploded/chat/"+getCookie("browser_uid"));
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
            let msg = JSON.parse(event.data);
            if(msg['to'] != '${activeSession.id}'){
                return;
            }
            let message = getFmtDate() + "<br/>" + msg['from'] + ":" + msg['message'] + "<br/><br/>";
            setMessageInnerHTML(message);
        }

        //连接关闭的回调方法
        websocket.onclose = function(){
            setMessageInnerHTML("close");
        }

        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
        window.onbeforeunload = function(){
            websocket.close();
        }
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
        let message = "{\"from\":\"${user.id}\",\"to\":\"${activeSession.id}\",\"message\":\""+document.getElementById('sendMsg').value+"\"}";
        websocket.send(message);
        let jsonMsg = JSON.parse(message);
        let message2 = getFmtDate() + "<br/>" + jsonMsg['from'] + ":" + jsonMsg['message'] + "<br/><br/>";
        setMessageInnerHTML(message2);
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

    function logout(){
        let form = document.createElement("form");
        form.action = "message.jhtml?requireType=logout";
        form.method = "post";
        document.body.appendChild(form);
        form.submit();
        document.body.removeChild(form);
    }

    function setting(){
        let form = document.createElement("form");
        form.action = "personalSetting.jhtml?requireType=init";
        form.method = "post";
        document.body.appendChild(form);
        form.submit();
        document.body.removeChild(form);
    }

</script>

</body>
</html>