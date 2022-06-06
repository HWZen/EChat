<%--
  Created by IntelliJ IDEA.
  User: 10414
  Date: 2022/6/1
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="user" scope="request" type="entity.User"/>
<jsp:useBean id="chatSessions" scope="request" type="java.util.List"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EChat Personal Setting</title>
</head>
<body>
<h1>Echat setting - ${user.nickname}</h1><br/>
<button onclick="window.location.href='message.jhtml'">back</button>

<h2>Change your name</h2>
<input type="text" id="name" value="${user.nickname}"/> <button onclick="changeName()">Change name</button><br/>
<br/>
<br/>
<h2>Change password</h2>
Old password: <input type="text" name="oldPasswd" id="opwd" class="text"/><br/>
New password: <input type="text" name="newPasswd" id="npwd" class="text"/><br/>
<button onclick="changePasswd()">Change password</button>
<br/>
<br/>
<h2>Add Friend</h2>
<input id="newFriendId" type="text">
<button onclick="addFriend()">Add Friend</button>
<br/>
<br/>

<h2>Create group</h2>
<div>New group name: </div><input id="newGroupName" type="text">
<button onclick="createGroup()">Create Group</button>
<br/>
<br/>

<h2>My groups</h2>
<c:forEach items="${chatSessions}" var="group">
    <c:if test="${group.getOwnerId() eq user.getId()}">
        <ul>
            <li>
                <div>${group.sessionName} </div> <button onclick="groupSetting(${group.getId()})">Edit</button>
            </li>
        </ul>
    </c:if>
</c:forEach>


<script type="text/javascript">
    function changeName(){
        let name = document.getElementById("name").value;
        let form = document.createElement("form");
        form.action = "personalSetting.jhtml?requireType=changeName&name="+name;
        form.method = "post";
        document.body.appendChild(form);
        form.submit();
        document.body.removeChild(form);
    }

    function changePasswd(){
        let opwd = document.getElementById("opwd").value;
        let npwd = document.getElementById("npwd").value;
        let form = document.createElement("form");
        form.action = "personalSetting.jhtml?requireType=changePasswd&opwd="+opwd+"&npwd="+npwd;
        form.method = "post";
        document.body.appendChild(form);
        form.submit();
        document.body.removeChild(form);
    }

    function groupSetting(groupId){
        let form = document.createElement("form");
        form.action = "groupSetting.jhtml?requireType=init&groupId="+groupId;
        form.method = "post";
        document.body.appendChild(form);
        form.submit();
        document.body.removeChild(form);
    }

    function addFriend(){
        let friendId = document.getElementById('newFriendId').value;
        let form = document.createElement("form");
        form.action = "personalSetting.jhtml?requireType=addFriend&friendId="+friendId;
        form.method = "post";
        document.body.appendChild(form);
        form.submit();
        document.body.removeChild(form);
    }
</script>
</body>
</html>
