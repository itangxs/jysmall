function subimt1(productId) {
	if (confirm("确定发表评论吗?")) {
		var urlval = '/managermall/account/evaluate/addEc.do';
		/* alert(urlval+"-----------"); */
		$.ajax({
			type : 'post',// 可选get
			url : urlval,// 这里是接收数据的后台程序
			data : $("#myForm").serialize(),// 传给后台的数据，多个参数用&连接
			success : function(msg) {
				if (msg == 'success') {
					alert("发表评论成功");
					window.location.href = "/getProdDetail.do?prodId="+productId;
				}else if(msg=='timeout'){
	    			window.location.href= "/account/login.do";
	    		} else {
					alert("发表评论失败");
				}
				// 这里是ajax提交成功后，java程序返回的数据处理函数。msg是返回的数据，数据类型在dataType参数里定义！如果是josn,就可以直接点属性出来，列如：msg.name,msg.url
				// 等等
			},
			error : function() {
				alert("服务器忙"); 
				// ajax提交失败的处理函数！
			}
		});
	} else {
		// alert("删除失败");
	}
}

window.onload = function () {
	var oStar = document.getElementById("star");
	var pf = document.getElementById("pf");
	var aLi = oStar.getElementsByTagName("li");
	var oUl = oStar.getElementsByTagName("ul")[0];
	var oSpan = oStar.getElementsByTagName("span")[1];
	var oP = oStar.getElementsByTagName("div")[0];
	var i = iScore = iStar = 0;
	var aMsg = [
				"很不满意|差得太离谱，与卖家描述的严重不符，非常不满",
				"不满意|部分有破损，与卖家描述的不符，不满意",
				"一般|质量一般，没有卖家描述的那么好",
				"满意|质量不错，与卖家描述的基本一致，还是挺满意的",
				"非常满意|质量非常好，与卖家描述的完全一致，非常满意"
				];
	for (i = 1; i <= aLi.length; i++) {
		aLi[i - 1].index = i;
		//鼠标移过显示分数
		aLi[i - 1].onmouseover = function() {
			fnPoint(this.index);
			// 浮动层显示
			oP.style.display = "block";
			// 计算浮动层位置
			oP.style.left = oUl.offsetLeft + this.index * this.offsetWidth - 10 + "px";
			// 匹配浮动层文字内容
			oP.innerHTML = "<em><b>" + this.index + "</b> 分 "
					+ aMsg[this.index - 1].match(/(.+)\|/)[1] + "</em>"
					+ aMsg[this.index - 1].match(/\|(.+)/)[1];
		};
		// 鼠标离开后恢复上次评分
		aLi[i - 1].onmouseout = function() {
			fnPoint();
			// 关闭浮动层
			oP.style.display = "none";
		};
		// 点击后进行评分处理
		aLi[i - 1].onclick = function() {
			iStar = this.index;
			oP.style.display = "none";
			oSpan.innerHTML = "<strong>" + (this.index) + " 分</strong> ("
					+ aMsg[this.index - 1].match(/\|(.+)/)[1] + ")";
			pf.value = this.index;
		};
	};
	//评分处理
	function fnPoint(iArg)
	{
		//分数赋值
		iScore = iArg || iStar;
		for (i = 0; i < aLi.length; i++) aLi[i].className = i < iScore ? "on" : "";	
	}
};

$(function() {
	uploadFile($('#update1'), $("#img1"), $("#img1u"));
	uploadFile($('#update2'), $("#img2"), $("#img2u"));
	uploadFile($('#update3'), $("#img3"), $("#img3u"));
	uploadFile($('#update4'), $("#img4"), $("#img4u"));
	
});

/**
 * @param butn 上传按钮
 * @param image 图片显示控件
 * @param hidd 图片地址保存控件
 */
function uploadFile(butn, image, hidd) {
	butn.uploadify({
		height : 35,
		width : 80,
		multi : false,
		method : 'post',
		buttonText : '上传',
		fileObjName : 'file',
		fileSizeLimit : '1MB',
		removeCompleted : true,
		swf : '/css/uploadify.swf',
		uploader : '/fileUpload.do;jsessionid=' + getCookie(),
		cancelImg : '/images/cancel.png',
		fileTypeExts : '*.jpg;*.png;*.gif',
		onUploadStart : function(file) {
			var data = {};
			data.delPath = image.attr('src');
			butn.uploadify('settings', 'formData', data);
		},
		onUploadSuccess : function(file, data, response) {
			data = eval('(' + data + ')');
			if (data.flag == 0) {
				image.attr('src', data.src);
				hidd.val(data.src);
			} else
				alert(data.state);
		}
	});
}

function getCookie() {
	var c_name = 'JSESSIONID';
	if (document.cookie.length > 0) {
		c_start = document.cookie.indexOf(c_name + "=")
		if (c_start != -1) {
			c_start = c_start + c_name.length + 1
			c_end = document.cookie.indexOf(";", c_start)
			if (c_end == -1)
				c_end = document.cookie.length
			return unescape(document.cookie.substring(c_start, c_end));
		}
	}
}