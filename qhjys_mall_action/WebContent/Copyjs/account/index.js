$(function() {
	var hour = new Date().getHours();
	var say = $('#say');
	if (hour < 6) {
		say.text("凌晨好！")
	} else if (hour < 9) {
		say.text("早上好！")
	} else if (hour < 12) {
		say.text("上午好！")
	} else if (hour < 14) {
		say.text("中午好！")
	} else if (hour < 17) {
		say.text("下午好！")
	} else if (hour < 19) {
		say.text("傍晚好！")
	} else if (hour < 22) {
		say.text("晚上好！")
	} else {
		say.text("夜里好！")
	} 
	// 循环广告
	$(".banner").slide({
		titCell : ".num ul",
		mainCell : ".bannerpic",
		effect : "fold",
		autoPlay : true,
		delayTime : 700,
		autoPage : true
	});
	$(".banner").hover(function() {
		$(this).find(".prev,.next").fadeTo("show", 0.1);
	}, function() {
		$(this).find(".prev,.next").hide();
	})
	// 分类显示
	var category = $("#cearchCate");
	$("span.search_choose").hover(function() {
		category.css("display", "block");
	}, function() {
		category.css("display", "none");
	});
	/* 鼠标移过某个按钮 高亮显示 */
	$(".prev,.next").hover(function() {
		$(this).fadeTo("show", 0.7);
	}, function() {
		$(this).fadeTo("show", 0.1);
	})
	$("div.alltypenav").mouseover(function() {
		$(this).next().css("display", "block");
		$("a", this).addClass("_hover");
	}).mouseout(function() {
		$(this).next().css("display", "none");
		$("a", this).removeClass("_hover");
	});
	$("div.alltypenavxiala").mouseover(function() {
		$(this).css("display", "block");
		$("div.alltypenav>a").addClass("_hover");
	}).mouseout(function() {
		$(this).css("display", "none");
		$("div.alltypenav>a").removeClass("_hover");
	});
	var menu = $("#menu_pop"), mendiv = null;
	$("#menu li").mouseover(function() {
		$(this).addClass("current");
		menu.children().css("display", "none");
		mendiv = $("div[lang=" + this.lang + "]", menu).css("display", "block");
		menu.css("display", "block");
	}).mouseout(function() {
		$(this).removeClass("current");
		menu.css("display", "none");
	});
	menu.mouseover(function() {
		var lang = mendiv.attr("lang");
		$("#menu li[lang=" + lang + "]").addClass("current");
		$(this).css("display", "block");
	}).mouseout(function() {
		var lang = mendiv.attr("lang");
		$("#menu li[lang=" + lang + "]").removeClass("current");
		$(this).css("display", "none");
	});
	$("#username").click(function() {
		if (this.value == "手机号/用户名/邮箱")
			this.value = "";
	}).blur(function() {
		if (this.value == "")
			this.value = "手机号/用户名/邮箱";
	});
	$("#login").submit(function() {
		var username = $("#username").val();
		var password = $("#password").val();
		$.ajax({
			async : true,
			type : "POST",
			url : "/account/loginUser.do",
			data : {
				"username" : username,
				"password" : md5(password)
			},
			success : function(data) {
				if (data.message == "登录成功")
					window.location.href = '/index.do';
				else
					alert(data.message);
			}
		});
		return false;
	});
	$('#signIn').on('click', function() {
		var $el = $(this).attr('disabled', "true");
		$.ajax({
			async : true,
			type : "POST",
			url : "/signIn.do",
			success : function(data) {
				if (data == 'logOut')
					alert("用户登录超时！");
				else if (data == false) {
					alert("用户已签到！");
					return;
				} else if (data == true)
					;
				else
					alert("用户签到异常，请稍后重试！");
				window.location.reload(true);
			}
		});
	});
});

var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?805d0b681fc08c561f037870e73c0967";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
