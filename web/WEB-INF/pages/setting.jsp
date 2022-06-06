<%--
  Created by IntelliJ IDEA.
  User: 10414
  Date: 2022/5/30
  Time: 23:34
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="user" scope="request" type="entity.User"/>
<jsp:useBean id="chatSessions" scope="request" type="java.util.List"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Echat setting</title>
</head>
<body>
<h1>Echat setting - ${user.nickname}</h1>

<br/>
<br/>
<h2>Group Setting</h2>

<c:forEach items="${chatSessions}" var="group">
    <c:if test="${group.ownerId eq user.id}">
        <h3>${group.sessionName}</h3>
        <div>Change name</div> <input type="text" id="groupName${group.id}" value="${group.sessionName}"/> <button onclick="changeGroupName(${group.id})">Change group name</button><br/>
        <h4>Members:</h4>
        <ul>
        <c:forEach items="${group.getSessionMembers()}" var="member">
            <li>
                <div>${member.nickname}</div>
                <button onclick="deleteMember('${group.id}','${member.id}')">del</button>
            </li>
        </c:forEach>
        </ul>
    </c:if>
</c:forEach>



<script type="text/javascript">


    function deleteMember(groupId,memberId){
        console.log("deleteMember: groupId="+groupId+",memberId="+memberId);
        let form = document.createElement("form");
        form.action = "setting.jhtml?requireType=deleteMember&groupId="+groupId+"&memberId="+memberId;
        form.method = "post";
        document.body.appendChild(form);
        form.submit();
        document.body.removeChild(form);
    }

    function changeGroupName(groupId){
        let name = document.getElementById("groupName"+groupId).value;
        let form = document.createElement("form");
        form.action = "setting.jhtml?requireType=changeGroupName&groupId="+groupId+"&name="+name;
        form.method = "post";
        document.body.appendChild(form);
        form.submit();
        document.body.removeChild(form);
    }
</script>
</body>
</html>
