<%--
  Created by IntelliJ IDEA.
  User: 10414
  Date: 2022/6/1
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="user" scope="request" type="entity.User"/>
<jsp:useBean id="chatSession" scope="request" type="entity.ChatSession"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EChat Group Setting</title>
</head>
<body>
<h1>EChat group setting - ${chatSession.sessionName}</h1> <br/>
<button onclick="window.location.href='message.jhtml'">Back to chat</button>
<button onclick="window.location.href='personalSetting.jhtml?requireType=init'">Back to Personal Setting</button>
<br/>
<br/>

<h2>Change group name</h2>
<input type="text" id="groupName" value="${chatSession.sessionName}"/> <button onclick="changeGroupName()">Change</button><br/>
<br/>
<br/>

<h2>Members</h2>
<ul>
<c:forEach var="member" items="${chatSession.getSessionMembers()}">
    <li>${member.nickname}</li>
    <c:if test="${member.getId() ne user.getId()}">
        <button onclick="delMember('${member.getId()}')">del</button>
    </c:if>
</c:forEach>
    <li><input type="text" id="newMemberId" value="new member(input member id)"></li>
    <button onclick="addMember()">add</button>
</ul>
<script type="text/javascript">
    function changeGroupName() {
        let groupName = document.getElementById("groupName").value;
        let form = document.createElement("form");
        form.method = "post";
        form.action = "groupSetting.jhtml?requireType=changeGroupName&groupId=${chatSession.getId()}&groupName=" + groupName;
        document.body.appendChild(form);
        form.submit();
        document.body.removeChild(form);
    }

    function addMember() {
        let newMemberId = document.getElementById("newMemberId").value;
        let form = document.createElement("form");
        form.method = "post";
        form.action = "groupSetting.jhtml?requireType=addMember&groupId=${chatSession.getId()}&memberId=" + newMemberId;
        document.body.appendChild(form);
        form.submit();
        document.body.removeChild(form);
    }

    function delMember(userId) {
        let form = document.createElement("form");
        form.method = "post";
        form.action = "groupSetting.jhtml?requireType=deleteMember&groupId=${chatSession.getId()}&memberId=" + userId;
        document.body.appendChild(form);
        form.submit();
        document.body.removeChild(form);
    }
</script>
</body>
</html>
