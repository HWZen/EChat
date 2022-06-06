<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>UserContents</title>
    <script type="text/javascript" src="js/5.085e1b9be61708ceb19b.js"></script>
    <script type="text/javascript" src="js/10.0c21917d73b51de9759b.js"></script>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jsencrypt.min.js"></script>
    <script type="text/javascript" src="js/header.js"></script>
    <script type="text/javascript" src="script/jquery-1.7.2.min.js"></script>
    <meta http-equiv="Content-Type" con tent="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link type="text/css" rel="stylesheet" href="css/app.26dd445c5a4992587eb35774ac43f575.css"/>
    <style type="text/css">
        .top-header[data-v-305800a0]{width:100%;height:106px;background:url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAIAAABWCAYAAADlqUfFAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAK6wAACusBgosNWgAAABx0RVh0U29mdHdhcmUAQWRvYmUgRmlyZXdvcmtzIENTNui8sowAAAAbSURBVCiRY2RYcOM/AwMDAxMDFIwyRhkjjQEAc4gDI5wD87YAAAAASUVORK5CYII=) repeat-x;z-index:10}
        .top-img[data-v-305800a0]{width:980px;height:106px;margin:0 auto;background:url(https://s1.hdslb.com/bfs/static/account-fe/static/img/rl_top.35edfde.png) no-repeat}
    </style>
    <style type="text/css">
        .security-left[data-v-91ce59ac]{float:left;width:150px;height:100%;overflow:hidden}
        .security-title[data-v-91ce59ac]{display:block;width:150px;height:50px;text-align:center;line-height:50px;font-size:16px;color:#99a2aa;cursor:default;border-bottom:1px solid #e1e2e5}
        .security-ul[data-v-91ce59ac]{border-bottom:1px solid #e1e2e5}
        .security-list[data-v-91ce59ac]{width:150px;height:48px;line-height:48px}
        .security-list[data-v-91ce59ac]:hover{background:#e1e4ea;cursor:pointer}
        .security-icon[data-v-91ce59ac]{vertical-align:middle;display:inline-block;width:18px;height:36px;margin-left:16px;background:url(https://s1.hdslb.com/bfs/static/account-fe/static/img/icons_m_2.57e5263.png)}
        .security-left .on[data-v-91ce59ac]{background-color:#00a1d7!important}
        .security-left .on
        .security-nav-name[data-v-91ce59ac]{color:#fff}
        .security-nav-name[data-v-91ce59ac]{margin-left:12px;font-size:14px;color:#222}
        .security-list-link[data-v-91ce59ac]{display:block;height:100%}
        .security-list-link-jump[data-v-91ce59ac]{display:block;height:100%;color:#222;font-size:16px;text-align:center;line-height:43px}
        .icon-3[data-v-91ce59ac]{background-position:-23px -80px}
    </style>
    <style type="text/css">
        .el-form-item:after,
        .el-form-item:before,
        .el-form-item__content:after,
        .el-form-item__content:before{display:table;content:""}
        .el-breadcrumb:after,.el-button-group:after,
        .el-color-dropdown__main-wrapper:after,
        .el-dialog__header:after,
        .el-form-item:after,
        .el-form-item__content:after,
        .el-menu:after,
        .el-picker-panel__body-wrapper:after,
        .el-picker-panel__body:after,
        .el-row:after,.el-slider:after,
        .el-tabs{clear:both}
        .el-form-item__content{display:inline-block;vertical-align:top}
        .el-form-item__content{display:block}.el-form-item{margin-bottom:22px}
        .el-form-item__content{line-height:36px;position:relative;font-size:14px}
        .el-form-item__content{line-height:30px}
        .el-form-item__content{line-height:30px}.user-my-btn,.user-my-sign,.user-nick-name{float:left;width:789px}
        .user-setting-warp{padding:20px 20px 0;position:relative}
        .el-form-item__label{width:95px;text-align:right;margin-right:20px;float:left;line-height:30px;padding:0}.user-nick-name .el-input,.user-nick-name
        .el-input__inner{float:left;width:225px;height:30px;margin-right:40px}.nick-name-not{color:#aaa}.user-my-sign .el-textarea,.user-my-sign

    </style>
    <style type="text/css">
        .el-button--primary{width:110px;position:absolute;top:50%;left:50%;-webkit-transform:translate(-50%,-50%);transform:translate(-50%,-50%);background:#00a1d6!important}
        .el-button--primary:hover{background-color:#00b5e5!important}
        .el-button--primary:first-child{border-right-color:hsla(0,0%,100%,.5)}
        .el-button--primary:last-child{border-left-color:hsla(0,0%,100%,.5)}
        .el-button--primary:not(:first-child):not(:last-child){border-left-color:hsla(0,0%,100%,.5);border-right-color:hsla(0,0%,100%,.5)}
        .el-button--primary{color:#fff;background-color:#20a0ff;border-color:#20a0ff}
        .el-button--primary:focus,.el-button--primary:hover{background:#4db3ff;border-color:#4db3ff;color:#fff}
        .el-button--primary.is-active,.el-button--primary:active{background:#1d90e6;border-color:#1d90e6;color:#fff}
        .el-button--primary:active{outline:0}
        .el-button--primary.is-plain{background:#fff;border:1px solid #bfcbd9;color:#1f2d3d}
        .el-button--primary.is-plain:focus,.el-button--primary.is-plain:hover{background:#fff;border-color:#20a0ff;color:#20a0ff}
        .el-button--primary.is-plain:active{background:#fff;border-color:#1d90e6;color:#1d90e6;outline:0}
    </style>
    <style>
        .el-input{width:110px}
        .el-input input{padding-right:25px;border-radius:2px;height:28px}
        el-pagination__sizes .el-input .el-input__inner{font-size:13px;border-color:#d1dbe5}
        .el-pagination__sizes .el-input .el-input__inner:hover{border-color:#20a0ff}
    </style>
</head>
<body>
<div data-v-305800a0="" class="top-header">
    <div data-v-305800a0="" class="top-img">
    </div></div>
<div id="internationalHeader" class="international-header report-wrap-module">
    <div class="mini-header m-header mini-type">
        <div class="mini-header__content mini-header--login">
            <div class="nav-link">
                <ul class="nav-link-ul mini">
                    <li class="nav-link-item">
                        <a onclick="gotoChat()">聊天</a>
                    </li>
                    <li class="nav-link-item">
                        <a onclick="gotoUserPage()">个人中心</a>
                    </li>
                    <li class="nav-link-item">
                        <a onclick="gotoGroupPage()">群聊管理</a>
                    </li>
                    <li class="nav-link-item">
                        <a onclick="logout()">登出</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="security_content">
    <div data-v-91ce59ac="" class="security-left">
        <span data-v-91ce59ac="" class="security-title">个人中心</span>
        <ul data-v-91ce59ac="" id="ser-ul" class="security-ul">
            <li data-v-91ce59ac="" class="security-list">
                <span data-v-91ce59ac="" class="security-nav-name" id="changeName" onclick="pick(this.id)">修改用户名</span>
            </li>
            <li data-v-91ce59ac="" class="security-list">
                <span data-v-91ce59ac="" class="security-nav-name" id="changePasswd" onclick="pick(this.id)">修改密码</span>
            </li>
            <li data-v-91ce59ac="" class="security-list">
                <span data-v-91ce59ac="" class="security-nav-name" id="addFriend" onclick="pick(this.id)">添加好友</span>
            </li>
            <li data-v-91ce59ac="" class="security-list">
                <span data-v-91ce59ac="" class="security-nav-name" id="deleteFriend" onclick="pick(this.id)">删除好友</span>
            </li>
        </ul>
    </div>
    <div class="security-right">
        <c:if test="${selected eq 'changeName'}">
        <div data-v-d88496a2="" class="security-right-title">
            <span data-v-d88496a2="" class="security-right-title-icon"></span>
            <span data-v-d88496a2="" class="security-right-title-text">修改用户名</span>
                <!---->
        </div>
        <div class="user-setting-warp">
            <div>
                <div class="el-form-item user-nick-name">
                    <label class="el-form-item__label">修改用户名:</label>
                    <div class="el-form-item__content">
                        <div class="el-input">
                            <input name="nick-name" id="newName" placeholder="${user.getNickname()}" type="text" rows="2" maxlength="16" class="el-input__inner">
                        </div>
                    </div>
                </div>
                <div class="el-form-item user-my-btn">
                    <div class="el-form-item__content">
                        <div class="padding-dom"></div>
                        <div class="user-my-btn-warp">
                            <button type="button" class="el-button el-button--primary" onclick="ChangeName()">
                                <span>保存</span>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </c:if>
        <c:if test="${selected eq 'changePasswd'}">
            <div data-v-d88496a2="" class="security-right-title">
                <span data-v-d88496a2="" class="security-right-title-icon"></span>
                <span data-v-d88496a2="" class="security-right-title-text">修改密码</span>
            </div>
            <div class="user-setting-warp">
                <div>
                    <div class="el-form-item user-my-sign">
                        <label class="el-form-item__label">修改密码：</label>
                        <div class="el-form-item__content">
                            <div class="el-textarea">
                                <input id="old-pass-word" placeholder="旧密码" type="text" rows="2" maxlength="16" class="el-input__inner">
                            </div>
                            <div class="el-textarea">
                                <input id="new-pass-word" placeholder="新的密码" type="text" rows="2" maxlength="16" class="el-input__inner">
                            </div>
                        </div>
                    </div>
                    <div class="el-form-item user-my-btn">
                        <div class="el-form-item__content">
                            <div class="padding-dom"></div>
                            <div class="user-my-btn-warp">
                                <button type="button" class="el-button el-button--primary" onclick="ChangePwd()">
                                    <span>保存</span>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${selected eq 'addFriend'}">
            <div data-v-d88496a2="" class="security-right-title">
                <span data-v-d88496a2="" class="security-right-title-icon"></span>
                <span data-v-d88496a2="" class="security-right-title-text">添加好友</span>
            </div>
            <div class="user-setting-warp">
                <div>
                    <div class="el-form-item user-my-sign">
                        <label class="el-form-item__label">好友ID：</label>
                        <div class="el-form-item__content">
                            <div class="el-textarea">
                                <input id="friendID" placeholder="输入好友ID" type="text" rows="2" maxlength="16" class="el-input__inner">
                            </div>
                        </div>
                    </div>
                    <div class="el-form-item user-my-btn">
                        <div class="el-form-item__content">
                            <div class="padding-dom"></div>
                            <div class="user-my-btn-warp">
                                <button type="button" class="el-button el-button--primary" onclick="addFriend()">
                                    <span>添加</span>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${selected eq 'deleteFriend'}">
            <div data-v-d88496a2="" class="security-right-title">
                <span data-v-d88496a2="" class="security-right-title-icon"></span>
                <span data-v-d88496a2="" class="security-right-title-text">删除好友</span>
            </div>
            <div class="user-setting-warp">
            <c:forEach items="${chatSessions}" var="Session">
                <c:choose>
                    <c:when test="${Session.ownerId eq 'admin'}">
                        <c:choose>
                            <c:when test="${Session.sessionMemberIds[0] eq user.getId()}">
                                <li data-v-91ce59ac="" class="security-list">
                                    <span data-v-91ce59ac="" class="security-nav-name" id="${Session.getId()}">${Session.getSessionMembers()[1].nickname}</span>
                                </li>
                                <li>
                                    <button id="${Session.getId()}" onclick=deleteFriend(this.id)>删除好友</button>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li data-v-91ce59ac="" class="security-list">
                                    <span data-v-91ce59ac="" class="security-nav-name" id="${Session.getId()}">${Session.getSessionMembers()[0].nickname}</span>
                                </li>
                                <li>
                                    <button id="${Session.getId()}" onclick=deleteFriend(this.id)>删除好友</button>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                </c:choose>
            </c:forEach>
            </div>
        </c:if>
    </div>
</div>
<script>
    function pick(part) {
        let form = document.createElement("form");
        form.action = "personalSetting.jhtml?requireType=pick&picked="+part;
        form.method = "post";
        document.body.appendChild(form);
        form.submit();
        document.body.removeChild(form);
    }

    function ChangePwd(){
        let oldpwd = document.getElementById("old-pass-word").value;
        let newpwd = document.getElementById("new-pass-word").value;
        let form = document.createElement("form");
        form.action = "personalSetting.jhtml?requireType=changePasswd&opwd="+oldpwd+"&npwd="+newpwd;
        form.method = "post";
        document.body.appendChild(form);
        form.submit();
        document.body.removeChild(form);
    }

    function ChangeName() {
        let newName = document.getElementById("newName").value;
        let form = document.createElement("form");
        form.action = "personalSetting.jhtml?requireType=changeName&name="+newName;
        form.method = "post";
        document.body.appendChild(form);
        form.submit();
        document.body.removeChild(form);
    }

    function addFriend() {
        let friendID = document.getElementById("friendID").value;
        let form = document.createElement("form");
        form.action = "personalSetting.jhtml?requireType=addFriend&friendId="+friendID;
        form.method = "post";
        document.body.appendChild(form);
        form.submit();
        document.body.removeChild(form);
    }

    function deleteFriend(uid) {
        let form = document.createElement("form");
        form.action = "personalSetting.jhtml?requireType=deleteFriend&sessionId="+uid;
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

    function gotoChat() {
        let form = document.createElement("form");
        form.action = "message.jhtml?requireType=init";
        form.method = "post";
        document.body.appendChild(form);
        form.submit();
        document.body.removeChild(form);
    }

    function gotoUserPage() {
        let form = document.createElement("form");
        form.action = "userContent.jhtml?requireType=init";
        form.method = "post";
        document.body.appendChild(form);
        form.submit();
        document.body.removeChild(form);
    }

    function gotoGroupPage() {
        let form = document.createElement("form");
        form.action = "groupSetting.jhtml?requireType=init";
        form.method = "post";
        document.body.appendChild(form);
        form.submit();
        document.body.removeChild(form);
    }
</script>
</body>
</html>
