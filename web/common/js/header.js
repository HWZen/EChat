document.writeln("<div class=\"topbar_js_box\">");
document.writeln("    <div class=\"topbar_box\">");
document.writeln("        <ul class=\"top_link\">");
document.writeln("            <!-- 一系列的链接，比如，我的资料，积分商城，帮助，微信，微博，电话，等等 -->");


document.writeln("            <li class=\"lv_link\"><a rel=\"nofollow\" href=\"#\"></a><i class=\"icon_arrow\"></i>");
document.writeln("				<div id=\"showtime\">");
document.writeln("				</div>");
document.writeln("				</li>");

document.writeln("            <li id=\"person\" class=\"dropdown\"><a rel=\"nofollow\" class=\"lv_link\" href=\"#\">个人中心</a><i");
document.writeln("                    class=\"icon_arrow\"></i>");
document.writeln("				<div class=\"personlist\">");
document.writeln("					<ul class=\"p_ul\">");
document.writeln("						<li><a href=\"../personal/personal_myOrder.html\">我的订单</a></li>");
document.writeln("						<li><a href=\"../personal/personal_information.html\">个人资料</a></li>");
document.writeln("						<li><a href=\"../personal/personal_passwordChange.html\">重置密码</a></li>");
document.writeln("						<li>我的优惠券</li>");
document.writeln("					</ul>");
document.writeln("				</div>");
document.writeln("				</li>");
document.writeln("            <li><a class=\"lv_link\" rel=\"nofollow\" href=\"#\">积分商城</a></li>");
document.writeln("            <li><a class=\"lv_link\" rel=\"nofollow\" href=\"#\">帮助</a></li>");
document.writeln("            <li class=\"dropdown\"><a rel=\"nofollow\" class=\"lv_link wx\" href=\"#\"><i class=\"lv_icon icon_wx\"></i>微信 </a></li>");
document.writeln("            <li class=\"dropdown\"><a rel=\"nofollow\" class=\"lv_link wb href=\"#\"><i");
document.writeln("                    class=\"lv_icon icon_wb\"></i>微博</a></li>");
document.writeln("            <li><a rel=\"nofollow\" class=\"lv_link\" href=\"#\">010-12345678</a></li>");
document.writeln("        </ul>");
document.writeln("        <div class=\"topbar_login\">");
document.writeln("            <!-- 登录，注册链接 -->");
document.writeln("            <div><a href=\"login.jsp\">请登录</div>");
document.writeln("        </div>");


document.writeln("        <div class=\"topbar_login\">");
document.writeln("            <!-- 当前页面停留时间 -->");
document.writeln("            <div>当前页面停留时间：<span id=\"timeAll\" >0时0分0秒</span></div>");
document.writeln("        </div>");


document.writeln("    </div>");
document.writeln("</div>");
document.writeln("<div class=\"header\">");
document.writeln("    <div class=\"inner_herder\">");
document.writeln("        <a href=\"#\">");
document.writeln("			<div class=\"lv_logo\">");
document.writeln("				<img src=\"common/img/here.png\" />");
document.writeln("				<span>去哪玩旅游网</span>");
document.writeln("			</div>");
document.writeln("		</a>");
document.writeln("        <ul class=\"lv_baozhang\">");
document.writeln("            <li><i class=\"lv_icon icon_bz1\"></i> 价格保证</li>");
document.writeln("            <li><i class=\"lv_icon icon_bz2\"></i> 退订保障</li>");
document.writeln("            <li><i class=\"lv_icon icon_bz3\"></i> 救援保障</li>");
document.writeln("            <li><i class=\"lv_icon icon_bz4\"></i> 24时服务</li>");
document.writeln("        </ul>");
document.writeln("        <div class=\"search_div\">");
document.writeln("            <div class=\"search_box\">");
document.writeln("				<div id=\"product\" class=\"pointer search_items search_product\"><span>所有产品</span><i class=\"search_ico search_ico_r\"></i>");
document.writeln("					<div class=\"products dropdownlist\">");
document.writeln("						<ul>");
document.writeln("							<li class=\"item\"><span><a href=\"../trip/search.html\">自驾游</a></span></li>");
document.writeln("							<li class=\"item\"><span><a href=\"../trip/search.html\">国内游</a></span></li>");
document.writeln("							<li><span><a href=\"../trip/search.html\">出境游</a></span></li>");
document.writeln("						</ul>");
document.writeln("					</div>");
document.writeln("				</div>");
document.writeln("                <div id=\"setout\" class=\"pointer search_items search_city\"><span>出发地点</span><i class=\"search_ico search_ico_g\"></i>");
document.writeln("					<div class=\"citys dropdownlist\">");
document.writeln("						<ul>");
document.writeln("							<li><span>热门出发城市</span></li>");
document.writeln("							<li class=\"item\">");
document.writeln("								<p>");
document.writeln("									<span><a href=\"../trip/search.html\">北京</a></span>");
document.writeln("									<span><a href=\"../trip/search.html\">上海</a></span>");
document.writeln("									<span><a href=\"../trip/search.html\">天津</a></span>");
document.writeln("									<span><a href=\"../trip/search.html\">重庆</a></span>");
document.writeln("									<span><a href=\"../trip/search.html\">桂林</a></span>");
document.writeln("									<span><a href=\"../trip/search.html\">香格里拉</a></span>");
document.writeln("									<span><a href=\"../trip/search.html\">丽江</a></span>");
document.writeln("									<span><a href=\"../trip/search.html\">乌镇</a></span>");
document.writeln("									<span><a href=\"../trip/search.html\">西塘</a></span>");
document.writeln("									<span><a href=\"../trip/search.html\">昆明</a></span>");
document.writeln("								</p>");
document.writeln("							</li>");
document.writeln("						</ul>");
document.writeln("					</div>");
document.writeln("				</div>");
document.writeln("                <div class=\"search_input\">");
document.writeln("                    <form class=\"form_search\">");
document.writeln("                        <input type=\"text\" id=\"search\" autocomplete=\"off\" value=\"目的地、主题、签证或景区名称\" x-webkit-speech=\"\"");
document.writeln("                               class=\"search w_260\" num=\"0\">");
document.writeln("						<input type=\"hidden\" id=\"type\" name=\"type\" value=\"\"/>");
document.writeln("						<input type=\"hidden\" id=\"city\" name=\"city\" value=\"\"/>");
document.writeln("                    </form>");
document.writeln("                </div>");
document.writeln("                <span class=\"pointer search_button\"></span>");
document.writeln("			</div>");
document.writeln("        </div>");
document.writeln("    </div>");
document.writeln("</div>");
document.writeln("<div class=\"lv_nav_bg\">");
document.writeln("    <div class=\"lv_nav\">");
document.writeln("        <ul class=\"menu\">");
document.writeln("            <li id=\"home\"><a href=\"../index/index.html\"    target=\"_blank\">首页</a></li>");
document.writeln("            <li id=\"ticket\"><a href=\"../trip/search.html\"  target=\"_blank\">自驾游<i class=\"icon_sbx\"></i></a></li>");
document.writeln("            <li id=\"liner\"><a href=\"../trip/search.html\"   target=\"_blank\">国内游</a></li>");
document.writeln("            <li id=\"zijia\"><a href=\"../trip/search.html\"   target=\"_blank\">出境游</a><i class=\"icon_no1\"></i></li>");
document.writeln("            <li id=\"lvyue\"><a href=\"../trip/search.html\"   target=\"_blank\">亲子游</a><i class=\"icon_ly\"></i></li>");
document.writeln("            <li id=\"tuangou\"><a href=\"../trip/search.html\" target=\"_blank\">特卖会</a><i class=\"icon_tg\"></i></li>");
document.writeln("            <li id=\"custom\"><a href=\"../trip/search.html\"  target=\"_blank\">定制游</a></li>");
document.writeln("        </ul>");
document.writeln("    </div>");
document.writeln("</div>");


//  场景 PRJ-WTP-WEB-030 首页时间显示  设置当前页面停留时间的js 【4/5  START】
var sec =0;
var minu=0;
var hou=0;
//每隔一秒刷新一次
window.setTimeout("updateTime()",1000);

function updateTime()
{
	sec++;
	if(sec==60)
	{
		sec =0;
		minu +=1;
	}
	if(minu==60)
	{
		minu=0;
		hou+=1;
	}
	var ss04 =hou+ "时"+minu+"分"+sec+"秒";
	document.getElementById('timeAll').innerHTML=ss04;
	window.setTimeout("updateTime()",1000);//每隔一秒刷新一次
}
//  场景 PRJ-WTP-WEB-030 首页时间显示  设置当前页面停留时间的js  【4/5  START】




//  场景 PRJ-WTP-WEB-030 首页时间显示 加载时间获取时当函数 【1/5  START】
function time(){
	//获得显示时间的div
	t_div = document.getElementById('showtime');
	var now=new Date()
	//替换div内容
	t_div.innerHTML = "现在是"+now.getFullYear()
	+"年"+(now.getMonth()+1)+"月"+now.getDate()
	+"日"+now.getHours()+"时"+now.getMinutes()
	+"分"+now.getSeconds()+"秒";
	//等待一秒钟后调用time方法，由于settimeout在time方法内，所以可以无限调用
	setTimeout(time,1000);
}
//  场景 PRJ-WTP-WEB-030 首页时间显示 加载时间获取时当函数 【1/5  END】



// JavaScript Document
$("#search").focus(function(e) {
	if ($("#search").val()=="目的地、主题、签证或景区名称"){
    	$("#search").val("");
	}
});
$("#search").blur(function(e) {
	if ($("#search").val()==""){
		$("#search").val("目的地、主题、签证或景区名称");
	}
});
$("#menu1").hover(function(e) {
	$(this).addClass("bgcolor");
	Show("detail1");
});
$("#menu1").mouseleave(function(e) {
	$(this).removeClass("bgcolor");
});
$("#menu_div1").mouseleave(function(e) {
	$(this).removeClass("bgcolor");
    Hide("detail1");
});
$("#menu2").hover(function(e) {
	$(this).addClass("bgcolor");
	Show("detail2");
});
$("#menu2").mouseleave(function(e) {
	$(this).removeClass("bgcolor");
});
$("#menu_div2").mouseleave(function(e) {
	$(this).removeClass("bgcolor");
    Hide("detail2");
});
$("#menu3").hover(function(e) {
	$(this).addClass("bgcolor");
	Show("detail3");
});
$("#menu3").mouseleave(function(e) {
	$(this).removeClass("bgcolor");
});
$("#menu_div3").mouseleave(function(e) {
	$(this).removeClass("bgcolor");
    Hide("detail3");
});
$("#menu4").hover(function(e) {
	$(this).addClass("bgcolor");
	Show("detail4");
});
$("#menu4").mouseleave(function(e) {
	$(this).removeClass("bgcolor");
});
$("#menu_div4").mouseleave(function(e) {
    Hide("detail4");
});

function Show(e1){
	document.getElementById(e1).style.display="block";
}
function Hide(e2){
	document.getElementById(e2).style.display="none";
}

$(".top_link a.wx").hover(function(){
	$(this).find("i").css("background-position", "-20px -90px")
}, function(){
	$(this).find("i").css("background-position", "0px -90px")
});

$(".top_link a.wb").hover(function(){
	$(this).find("i").css("background-position", "-20px -110px")
}, function(){
	$(this).find("i").css("background-position", "0px -110px")
})

$("#person").hover(function(){
	$("#person .personlist").show();
}, function(){
	$("#person .personlist").hide();
});

$(".p_ul li").hover(function(){
	$(this).css("color","orange");
}, function(){
	$(this).css("color","black");
});

$("#product li").hover(function(){
	$(this).css("background","#fef2f9");
}, function(){
	$(this).css("background","white");
});

$("#product").click(function(event){
	$("#setout .dropdownlist").hide();
	var flag = $("#product .dropdownlist").css("display");
	if(flag == 'block')
		$("#product .dropdownlist").hide();
	else
		$("#product .dropdownlist").show();
	event.stopPropagation();
});

$("#setout").click(function(event){
	$("#product .dropdownlist").hide();
	var flag = $("#setout .dropdownlist").css("display");
	if(flag == 'block')
		$("#setout .dropdownlist").hide();
	else
		$("#setout .dropdownlist").show();
	event.stopPropagation();
});

$(document).click(function(){
	$(".dropdownlist").hide();
});

$(".products li").click(function(){
	$("#type").attr("value",$(this).find("span").text());
	$("#product").find("span").eq(0).text($(this).find("span").text());
});

$(".citys li span").click(function(){
	$("#city").attr("value",$(this).text());
	$("#setout").find("span").eq(0).text($(this).text());
});
