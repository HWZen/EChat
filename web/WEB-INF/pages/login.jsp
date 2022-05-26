<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>EChat</title>
    <link type="text/css" rel="stylesheet" href="css/login.css" />
    <script type="text/javascript" src="js/jquery/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="js/pages/login/login.js"></script>
</head>
<body>
<div class="backgrounds" id="backgrounds">
    <div class="content" id="div_position">
        <div class="login_framework">
            <form id="log_reg_form" method="post" name="loginForm" action="/EChat_Web_exploded/login.jhtml">
                <div class="content2">
                    <div id="contents" class="contents">${message}</div>
                    <ul>
                        <li><input id="tid" name="name" type="text" class="text"
                                   value="您的邮箱账号/手机号" /><br /></li>
                    </ul>
                    <ul id="pwd_p">
                        <li><input type="text" name="password" id="pwd" class="text"
                                   value="您的密码" /></li>
                    </ul>
                    <ul id="identfy_all">
                        <li>
								<span class="identifying">
									<img src="code.jhtml" width="120px" height="40px"
                                         onclick="javascript:this.src='code.jhtml?id='+new Date().getMilliseconds()">
								</span>
                            <input type="text" name="code" id="ident" class="text identify" value="验证码" />
                        </li>
                    </ul>
                    <span class="rem_password"> <a href="#" class="texts">&nbsp</a>
						</span> <input class="login_button" id="button" value="登  录"
                                       type="button"/>
                </div>
            </form>
        </div>
        <div class="regist" id="regesits">
            还没有帐号?<span class="texts cursors">马上注册</span>
        </div>
        <div class="logins" id="logins" style="display: none;">
            已经注册?<span class="texts cursors">马上登录</span>
        </div>
    </div>
</div>
<!-- 背景  结束 -->
<script type="text/javascript">
    if('${init}' == 'reg'){
        $("#third_part").show();
        $("#logins").show();
        $("#pwd_p").hide();
        $("#regesits").hide();
        $("#identfy_all").hide();
        $(":button").val("注 册");
        $("#logo_img").css("display", "block");
        $("#button").click(function() {
            register('loginForm');
        });
    }
</script>
</body>
</html>