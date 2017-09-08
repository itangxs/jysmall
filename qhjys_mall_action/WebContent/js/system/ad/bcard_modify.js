$(function() {
//	getCity();
//	getDistrict(null);
//	getArea(null);
//	industryChange();
	
	var rulesize = $("#rulesize").val();
	for (var i = 1; i <= rulesize; i++) {
		industryChange('11'+i);
		cityChange('11'+i);
		districtChange('11'+i);
		areaChange('11'+i);
	}
	
	uploadFile($('#update1'), $("#img1"), $("#imgu1"));
	uploadFile($('#update2'), $("#img2"), $("#imgu2"));
	uploadFile($('#update3'), $("#img3"), $("#imgu3"));
	uploadFile($('#update4'), $("#img4"), $("#imgu4"));
});

function changeDistrict(n,o){
	var pId = this.id;
	$("#dist"+pId).combobox({
		url:'/managermall/systemmall/ad/bcard_query_district.do?cityId='+n, 
		valueField : "id",
		textField : "district",
		editable : false,
		onChange : changeArea
	});
}

function changeArea(n,o){
	var pId = this.id;
	$("#area"+pId).combobox({
		url:'/managermall/systemmall/ad/bcard_query_area.do?districtId='+n, 
		valueField : "id",
		textField : "area",
		editable : false,
	});
}

// 初始化用
function districtChange(id){
	$("#dist"+id).combobox({
		url:'/managermall/systemmall/ad/bcard_query_district.do?cityId=', 
		valueField : "id",
		textField : "district",
		editable : false,
		onChange : changeArea
	});
}

//初始化用
function areaChange(id){
	$("#areadist"+id).combobox({
		url:'/managermall/systemmall/ad/bcard_query_area.do?districtId=', 
		valueField : "id",
		textField : "area",
		editable : false,
	});
}

function cityChange(id){
	$("#"+id).combobox({
		url:'/managermall/systemmall/ad/bcard_query_city.do', 
		valueField : "id",
		textField : "city",
		editable : false,
		onChange : changeDistrict
	});
}

function industryChange(id){
	var select = $("#industryselect"+id).val();
	$("#industryDetail"+id).combobox({
		url : "/managermall/systemmall/ad/bcard_query_industry.do?parentId="+select,
		valueField : "id",
		textField : "industry",
		editable : false
	});
}

function quxiao(){
	window.location.href = "/managermall/systemmall/ad/bcard_list.do";
}

var getCity = function(){
	$.ajax({
		async : true,
		type : "POST",
		url : "/managermall/systemmall/ad/bcard_query_city.do",
		success : function(e) {
			$("#city").combobox({
				data : e,
				valueField : "id",
				textField : "city",
				editable : false,
				onChange : getDistrict
			});
//			.combobox("setValue", $("#city").attr("value"))
		}
	});
}

var getDistrict = function(a) {
	$.ajax({
		async : true,
		type : "POST",
		url : "/managermall/systemmall/ad/bcard_query_district.do",
		data : {
			cityId : a
		},
		success : function(b) {
			$("#district").combobox({
				data : b,
				valueField : "id",
				textField : "district",
				editable : false,
				onChange : getArea
			});
//			.combobox("setValue", $("#district").attr("value"))
		}
	});
};
var getArea = function(a) {
	$.ajax({
		async : true,
		type : "POST",
		url : "/managermall/systemmall/ad/bcard_query_area.do",
		data : {
			districtId : a
		},
		success : function(b) {
			$("#area").combobox({
				data : b,
				valueField : "id",
				textField : "area",
				editable : false
			});
//			.combobox("setValue", $("#area").attr("value"))
		}
	});
};

var rulenew = 1110;
function add_tr(obj) {
    var dd = $(obj).parent().parent();
    rulenew += 1;
    var html = 	'<dd>'+
                    '<div class="title_gz">规则</div>'+
                  '<div class="diqubox">'+
                  '<ul>'+
                      '<li>城市<br>'+
                          '<input id="'+rulenew+'" class="easyui-combobox" name="cityNew" value="" type="text" style="width:120px;height:32px;">'+
                      '</li>'+
                      '<li>行政区<br>'+
                          '<input id="dist'+rulenew+'" class="easyui-combobox" name="distNew" value="" type="text" style="width:120px;height:32px;">'+
                      '</li>'+
                      '<li>商圈<br>'+
                          '<input id="areadist'+rulenew+'" class="easyui-combobox" name="areaNew" value="" type="text" style="width:120px;height:32px;">'+
                      '</li>'+
                      '<li>大行业<br>'+
                         '<select id="industryselect'+rulenew+'" name="induNew" onchange="industryChange('+rulenew+')">'+
                          	'<option value="1" >餐饮</option>'+
              				'<option value="2" >非餐饮</option>'+
                          '</select>'+
                      '</li>'+
                      '<li>细分行业<br>'+
                          '<input id="industryDetail'+rulenew+'" class="easyui-combobox" name="indudeNew" value="" type="text" style="width:120px;height:32px;">'+
                      '</li>'+
                  '</ul>'+
                  '<br>'+ 
                '<input type="button"   class="submit8"  value="增加" id="addTable" onclick="add_tr(this)"/>'+
                '<input type="button"  class="submit8"   value="删除" id="deleteTable" onclick="del_tr(this,2,0)"/>'+
                '</div>'+
            '</dd>';
    dd.after(html);
    industryChange(rulenew);
	cityChange(rulenew);
}

function del_tr(obj,type,id) {
	if (type == 1) {
		var b = "/managermall/systemmall/ad/bcard_delete_rule.do";
		var a = "ruleId=" + id;
		$.ajax({
			async : false,
			type : "post",
			url : b,
			data : a,
			success : function(g) {
				if (g == "success") {
					$(obj).parent().parent().remove();
				} else {
					alert("删除失败");
				}
			},
			error : function() {
				alert("服务器忙")
			}
		});
		
	}else if (type == 2) {
		$(obj).parent().parent().remove();
	}
}

function descript_limit(obj){
	obj.value=obj.value.substring(0, 60)
}

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
		cancelImg : '/images/cancel.png',
		fileTypeExts : '*.jpg;*.png;*.gif',
		uploader : '/fileUpload.do;jsessionid=' + getCookie(),
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
