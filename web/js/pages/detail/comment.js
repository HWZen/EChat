/**
 * 文件注释：
 * 旅游产品详情中，评论分页查询与加载的js文件
 * 2015.12
 */

/**
 * 初始化加载默认页（第一页）的评论数据
 */
var userid;
$(document).ready(function() {
	loadComment(GetQueryString("id"), 1);
	userid = $("#userid").text();
});

/**
 * 获取地址栏的参数对应的值
 * 
 * @param name
 *            地址栏参数名称
 * @returns 地址栏参数值
 */
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}

/**
 * 点击评论中的 ‘赞’，会调用本方法发送Ajax请求，以更新赞的数量
 * @param com_id 点击的对应的评论id
 */
function good(com_id) {
	
	// 禁用对应评论赞的点击事件
	$("#usefull_" + com_id).attr("disabled", true);
	// 发送Ajax更新数据
	$.ajax({
		type : "POST",
		async : true,
		url : "tripDetail.jhtml",
		data : {
			id : com_id,
			isUsefull : true,
			type : 'goodOrBad',
		},
		dataType : "json",
		// Ajax成功响应的回调函数，num为赞的当前数量
		success : function(num) {
			if(num == -1)
				alert("您已经点评过了");
			else{
				// 启用对应评论赞的点击事件
				$("#usefull_" + com_id).attr("disabled", false);
				$("#usefull_" + com_id).text("赞 (" + num + ")");
			}
		},
		error : function(XMLResponse) {
			alert(html(XMLResponse.responseText))
		}
	});
}

/**
 * 点击评论中的 ‘踩’，会调用本方法发送Ajax请求，以更新踩的数量
 */
function bad(com_id) {
	
	$("#useless_" + com_id).attr("disabled", true);
	$.ajax({
		type : "POST",
		async : true,
		url : "tripDetail.jhtml",
		data : {
			id : com_id,
			isUsefull : false,
			type : 'goodOrBad',
		},
		dataType : "json",

		success : function(num) {
			if(num == -1)
				alert("您已经点评过了");
			else{
				$("#useless_" + com_id).attr("disabled", false);
				$("#useless_" + com_id).text("踩 (" + num + ")");
			}
		},
		error : function(XMLResponse) {
			alert(html(XMLResponse.responseText))
		}
	});
}

/**
 * 获取目标页数的评论信息
 * 
 * @param trip_id
 *            产品的ID
 * @param page
 *            评论所属页数
 */
function loadComment(trip_id, page) {
	if (trip_id == null || trip_id == "" || page == "" || page == null)
		return;

	$
			.ajax({
				type : "POST",
				async : true,
				url : "tripDetail.jhtml",
				data : {
					id : trip_id,
					page : page,
					type : 'comment',
				},
				dataType : "json",

				// 成功返回调用的函数
				success : function(comments) {
					var html = '';
					$
							.each(
									comments,
									function(i, com) {
										var p_score = com.place;
										var p_string = getStringByScore(p_score);
										var t_score = com.traffic;
										var t_string = getStringByScore(t_score);
										var h_score = com.hotel;
										var h_string = getStringByScore(h_score);
										var s_score = com.service;
										var s_string = getStringByScore(s_score);
										var aver = (p_score + t_score + h_score + s_score) / 4;
										// 评论基础信息
										html += '<div class="feature_spoc"><div class="comments_level_level"><strong class="star star'
												+ aver
												+ '"></strong><span class="span_comment"><em>景点</em><i>&nbsp;'
												+ p_score
												+ '（'
												+ p_string
												+ '）</i></span> <span class="span_comment"><em>交通</em><i>&nbsp;'
												+ t_score
												+ '（'
												+ t_string
												+ '）</i></span> <span class="span_comment"><em>酒店</em><i>&nbsp;'
												+ h_score
												+ '（'
												+ h_string
												+ '）</i></span> <span class="span_comment"><em>服务</em><i>&nbsp;'
												+ s_score
												+ '（'
												+ s_string
												+ '）</i></span></div><div class="comments_level_content"><p>'
												+ com.content
												+ '</p></div><div>';
										// 显示图片集
										if (com.pictures.length > 0) {
											html += '<div class="DB_gallery DB_galleryb"><div class="DB_imgSet big_small" style="display: none;"><div class="DB_imgWin  DB_imgWinb ">'
													+ '<img src="img/img1.jpg" /></div><div class="DB_prevBtn  DB_prevBtnb"></div><div class="DB_nextBtn DB_nextBtnb"></div>'
													+ '</div><div class="DB_thumSet DB_thumSetb"><ul class="DB_thumMove">';
											for (var i = 0; i < com.pictures.length; i++) {
												html += '<li><a href="'
														+ getRootPath()
														+ '/image_cache/'
														+ com.pictures[i].name
														+ '"><img src="'
														+ getRootPath()
														+ '/image_cache/'
														+ com.pictures[i].name
														+ '" /></a></li>';
											}
											html += '</ul><div class="DB_thumLine" style=""></div><div class="DB_prevPageBtn tourist_detail_review_pre">'
													+ '<img src="img/prev_page.png" alt="上一页" /></div><div class="DB_nextPageBtn tourist_detail_review_next">'
													+ '<img src="img/next_page.png" alt="下一页" /></div></div></div><span class="stop text_color_blue">收起 <span'
													+ 'class="icon_retract"> </span></span>';
										}
										// 评论尾部信息
										html += '<div class="comment"><p><a href="javascript:;" hidefocus="false">'
												+ com.user.name
												+ '</a> 对 <span class="com-proTit"> <a href="javascript:;" title="'
												+ $('.product-name h1').html()
												+ '" hidefocus="false">”'
												+ $(".product-name h1").html()
												+ '</a></span> ” 发表点评 <em>'
												+ com.timeStr
												+ '</em> <a class="com-user-app" href="javascript:;" hidefocus="false"> <i class="iconcom iconcom-mobiles"></i>'
												+ '</a></p></div><div class="comment_status"><ul><li><span class="span_text "  id="usefull_'
												+ com.id
												+ '" onclick="good('
												+ com.id
												+ ')"  style="cursor:pointer;">赞 ('
												+ com.usefull
												+ ')</span></li><li><span class="span_text " id="useless_'
												+ com.id
												+ '" onclick="bad('
												+ com.id
												+ ')" style="cursor:pointer;">踩 ('
												+ com.useless
												+ ')</span></li><li><span class="span_text gray">已点评</span></li></ul></div></div></div>';
									});
					// 加载HTML
					$('#comments_detail').empty();
					$('#comments_detail').append(html);

					// 初始化下一页组件
					var pageCount = Math.ceil($('#comment_num').html() / 5);
					var pageHtml = fenye(trip_id, page, pageCount,
							"loadComment");
					$('.Pages').html(pageHtml);

				},
				complete : function() {
					/*
					 * 设置相册效果
					 */
					$('.DB_galleryb').DB_gallery({
						thumWidth : 83,
						thumGap : 8,
						thumMoveStep : 5,
						moveSpeed : 300,
						fadeSpeed : 500
					});
					// 评论画廊的收起按钮
					$(".DB_thumMove").click(function() {
						$(this).parent().parent().next().fadeIn();
					});
					$(".stop").click(function() {
						$(".big_small").fadeOut(1000);
						$(".stop").fadeOut();

						// 当点击收起时，关闭上一页、下一页按钮
						$(".tourist_detail_review_pre").hide();
						$(".tourist_detail_review_next").hide();
					});
					// 当未展开相册时 ，隐藏下一页按钮
					$(".tourist_detail_review_pre").hide();
					$(".tourist_detail_review_next").hide();

				},
				error : function(XMLResponse) {
					alert(html(XMLResponse.responseText))
				}
			});
}

// 动态创建页数组件
function fenye(t_id, page, pageCount, onclickEvent) {
	if (pageCount == 0)
		return;

	var next_page = (page < pageCount) ? (page + 1) : pageCount;
	var pre_page = (page > 1) ? (page - 1) : 1;

	var pageHtml = '<a href="javascript:void(0);" onclick="' + onclickEvent
			+ '(' + t_id + ',' + pre_page
			+ ')" title="上一页" class="PrevPage">上一页</a>';
	for (var i = 1; i <= pageCount; i++) {
		if (page == i) {
			pageHtml += '<a href="javascript:void(0);" class="pagesel" style="background-color:#DF1A7A;color:white" id="orderPage'
					+ i
					+ '" onclick="'
					+ onclickEvent
					+ '('
					+ t_id
					+ ','
					+ i
					+ ')">' + i + '</a>';
		} else {
			pageHtml += '<a href="javascript:void(0);" class="pagesel" id="orderPage'
					+ i
					+ '" onclick="'
					+ onclickEvent
					+ '('
					+ t_id
					+ ','
					+ i
					+ ')">' + i + '</a>';
		}
	}
	pageHtml += '<a href="javascript:void(0);" onclick="' + onclickEvent + '('
			+ t_id + ',' + next_page
			+ ')" title="下一页" class="NextPage">下一页</a>';
	return pageHtml;
}

// 通过评分值获取文字描述
function getStringByScore(score) {
	switch (score) {
	case 1:
		return '失望';
	case 2:
		return '不满';
	case 3:
		return '一般';
	case 4:
		return '满意';
	case 5:
		return '推荐';
	default:
		return '';
	}
}

// js获取项目根路径，如： http://localhost:8083/uimcardprj
function getRootPath() {
	// 获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
	var curWwwPath = window.document.location.href;
	// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	// 获取主机地址，如： http://localhost:8083
	var localhostPaht = curWwwPath.substring(0, pos);
	// 获取带"/"的项目名，如：/uimcardprj
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	return (localhostPaht + projectName);
}
