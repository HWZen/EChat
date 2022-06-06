$(function(){
	$("div.contactlist").each(function(){
		var li = $(this).find("li");
		if(parseInt(li.css("height")) > parseInt($(this).css("height")))
			li.find("span.more").show();
		else
			li.find("span.more").hide();
	})
})

/**
 * 验证购买人信息
 */
function validBuyer(){
	var name;
	var phone;
	var email;
	
	var flag = true;
	$("#user_info dd").each(function(e){
		if(e == 0)
			name = $(this).find("span").eq(1).find("label");
		if(e == 1)
			phone = $(this).find("span").eq(1).find("label");
		if(e == 2)
			email = $(this).find("span").eq(1).find("label");
	})
	if($("#u_name").val() == ""){
		name.text("请先到个人中心设置真实姓名");
		name.parent().css("display","inline-block");
		flag = false;
	} else{
		name.hide();
	}
	if($("#u_phone").val() == ""){
		phone.text("请先到个人中心设置手机号码");
		phone.parent().css("display","inline-block");
		flag = false;
	} else{
		phone.hide();
	}
	if($("#u_email").val() == ""){
		email.text("请先到个人中心设置邮箱地址");
		email.parent().css("display","inline-block");
		flag = false;
	} else{
		email.hide();
	}
	return flag;
}

/* 验证开始 */
function valid() {
	var flag1 = true;
	var flag2 = true;
	var flag3 = true;
	var flag4 = true;
	var flag5 = true;
	
	flag5 = validBuyer();
	/**
	 * 验证真实姓名输入框
	 */
	$(".realname").each(function() {
		var realname = $(this).val();
		if (!validNull(realname)) {
			$(this).parent().find(".error_text").css("display",
					"inline-block");
			flag1 = false;
		} else {
			$(this).parent().find(".error_text").hide();
		}
	});
	/**
	 * 验证手机号码输入框
	 */
	$(".phone").each(function() {
		var phone = $(this).val();
		if (!validPhone(phone)) {
			$(this).parent().find(".error_text").css("display",
					"inline-block");
			$(this).parent().find(".prompt_text").hide();
			flag2 = false;
		} else {
			$(this).parent().find(".error_text").hide();
		}
	});
	/**
	 * 验证邮箱地址输入框
	 */
	$(".email").each(function() {
		var email = $(this).val();
		if (!validEmail(email)) {
			$(this).parent().find(".error_text").css("display",
					"inline-block");
			$(this).parent().find(".prompt_text").hide();
			flag3 = false;
		} else {
			$(this).parent().find(".error_text").hide();
		}
	});
	/**
	 * 验证省份证输入框
	 */
	$(".identity").each(function() {
		var identity = $(this).val();
		if (!validIdentity(identity)) {
			$(this).parent().find(".error_text").css("display",
					"inline-block");
			$(this).parent().find(".prompt_text").hide();
			flag4 = false;
		} else {
			$(this).parent().find(".error_text").hide();
		}
	});
	/**
	 * 如果验证都通过则进行下单操作
	 */
	if (flag1 && flag2 && flag3 && flag4 && flag5) {
		$("#putOrderForm").submit();
	}
	/**
	 * 否则返回false
	 */
	return false;
};

/**
 * 非空验证
 * @param txt 验证字符串
 * @returns {Boolean} 
 */
function validNull(txt) {
	if (txt.trim() == "")
		return false;
	return true;
};

/**
 * 手机号码格式验证
 * @param phone 手机号码
 * @returns {Boolean}
 */
function validPhone(phone) {
	var regex = /^1[34578]\d{9}$/;
	if (!phone.match(regex))
		return false;
	return true;
};

/**
 * 邮箱地址格式验证
 * @param email 邮箱地址
 * @returns {Boolean}
 */
function validEmail(email) {
	var regex = /^\w+@\w+(.\w+){1,3}$/;
	if (!email.match(regex))
		return false;
	return true;
};

/**
 * 身份证号码格式验证
 * @param identity 身份证号码
 * @returns {Boolean}
 */
function validIdentity(identity) {
	var regex = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
	if (!identity.match(regex))
		return false;
	return true;
};

/**
 * 支付方式的样式切换
 */
$(".order-tabnav li").click(function() {
	var index = $(this).index();
	$(".order-tabnav li").removeClass("selected");
	$(this).addClass("selected");
	$(".tabcon").removeClass("selected");
	$(".tabcon").eq(index).addClass("selected");
});

// 复选框输入限制
limitSelect();

// 紧急联系人的点击选中事件
function selectEmContact(state, name, phone) {
	var val = $('.contactlist input:radio[name="emer_id"]:checked').val();
	if (val == null) {
		return false;
	} else {
		// 将紧急联系人的数据填写至文本输入框
		$('#em_id').val(val);
		$('#em_name').val(name);
		$('#em_phone').val(phone);
	}
}

// 获取当前游玩人的人数
var total_num = $('#pl_contactlist ul').eq(0).attr('id').substring(5);

var i = 0;
// 控制复选框进程的数组
var array1 = new Array();
// 控制复选框是否选中的数组
var array2 = new Array();
// 数组初始化
for (var i = 0; i < total_num; i++) {
	array1[i] = i + 1;
	array2[i] = false;
}

/**
 * 复选框选中事件
 * 
 * @param state
 *            点击对象的this
 * @param num
 *            总的游玩人的数目
 * @param name
 *            选中的游玩人的姓名
 * @param phone
 *            选中的游玩人的电话
 * @param cardno
 *            选中的游玩人的身份证号码
 */
function selectPlContact(state, num, name, phone, cardno) {
	if ($(state).is(':checked')) {
		for (var i = 0; i < num; i++) {
			// 如果当前输入框没有填写数据
			if (array2[i] == false) {
				// 在当前选中的对象上添加一个class属性，属性值为文本输入区域的序号
				$(state).attr("class", array1[i]);
				// 将选中信息填写至文本输入框之中
				$('#w_id' + array1[i]).val($(state).val()); // 隐藏id
				$('#w_name' + array1[i]).val(name); // 游玩人的name
				$('#w_phone' + array1[i]).val(phone); // 游玩人的phone
				$('#w_cardno' + array1[i]).val(cardno); // 游玩人的cardno
				// 设置当前的文本框集合为 已填写
				array2[i] = true;
				break;
			}
		}
	} else {
		// 当前为取消选中事件
		// 取消选中时，获取当前点击取消的复选框对象的class属性值
		var classVar = $(state).attr('class');
		for (var j = 0; j < num; j++) {
			// 对比class值和文本框集合的序号一致时，清空输入框数据
			if (classVar == array1[j]) {
				// 清空输入框数据
				$('#w_id' + classVar).val("-1");
				$('#w_name' + classVar).val("");
				$('#w_phone' + classVar).val("");
				$('#w_cardno' + classVar).val("");
				$(state).removeClass(classVar);
				array2[j] = false;
				break;
			}
		}
	}
}

// 多选框限制
function limitSelect() {
	$('input[type="checkbox"]').click(function() {
		checkboxable();
	});

}

// 输入框内容发生改变时执行
function checkEdit(index){
	var value = $("#w_id"+index).val();
	if(value != -1)
		canelChecked(value,'checkbox');
	// 把游玩人id置-1
	$("#w_id"+index).val("-1");
	
	// 遍历当前游玩人的输入框
	var flag = false;
	$(".player"+index).each(function(){
		if($(this).val().trim() != ""){
			flag = true;
		}
	})
	
	// 如果全未填写则标识为false，否则标识为true
	if(flag){
		array2[index-1] = true;
	}else{
		array2[index-1] = false;
	}
	
	// 控制多选框是否可选
	checkboxable();
}

function checkRadio(){
	var value = $("#em_id").val();
	if(value != -1)
		canelChecked(value,'radio');
	$("#em_id").val("-1");
}

// 取消多选框的选择
function canelChecked(value, type){
	
	if(type="checkbox")
		$("input[name='player_id']").each(function(){
			if($(this).val() == value){
				$(this).attr("checked",false);
				return;
			}
		});
	if(type="radio")
		$("input[name='emer_id']").each(function(){
			if($(this).val() == value){
				$(this).attr("checked",false);
				return;
			}
		});
}

// 控制多选框是否可用
function checkboxable(){
	var flag = false;
	for(var i=0; i<array2.length; i++){
		if(array2[i] == false){
			flag = true;
			break;
		}
	}

	var checkbox = $("input[name='player_id']");
	var checked = $("input[name='player_id']:checked");
	if(flag){
		checkbox.attr('disabled', false);
	}else{
		checkbox.attr('disabled', true);
		checked.attr('disabled', false);
	}
}

// 紧急联系人、游玩人的更多选择下拉框
function getAllContact(type) {
	if(type == 'em'){
		var e = $("div.contactlist").eq(0).find("ul");
		var li = e.find("li");
		var more = e.find("span.more a");
		if(parseInt(e.css("height")) < parseInt(li.css("height"))){
			e.css("max-height",e.find("li").css("height"));
			more.text("收起");
		}else{
			e.css("max-height","32px");
			more.text("更多");
		}
	}
	
	if(type == 'pl'){
		var e = $("div.contactlist").eq(1).find("ul");
		var li = e.find("li");
		var more = e.find("span.more a");
		if(parseInt(e.css("height")) < parseInt(li.css("height"))){
			e.css("max-height",e.find("li").css("height"));
			more.text("收起");
		}else{
			e.css("max-height","32px");
			more.text("更多");
		}
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
