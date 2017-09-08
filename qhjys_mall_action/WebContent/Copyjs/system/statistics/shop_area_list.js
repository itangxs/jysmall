/**
 * 
 */


$(function() {
	$.ajax({
		async : false,
		type : "POST",
		url : '/queryProvinceInfo.do',
		success : function(province) {
			$('#licenseProvince').combobox({
				data : province.provinceList,
				valueField : 'id',
				textField : 'name',
				editable : false,
				onChange:function(record){
					var provinceId = record;
					$.ajax({
						async : false,
						type : "POST",
						data: {"provinceId": provinceId},
						url : '/queryCityInfo.do',
						success : function(city) {
							$('#licenseCity').combobox({
								data : city.cityInfoList,
								valueField : 'id',
								textField : 'name',
								editable : false,
								onChange:function(record){
									var cityId = record;
									$.ajax({
										async : false,
										type : "POST",
										data: {"cityId": cityId},
										url : '/queryDistrictInfo.do',
										success : function(district) {
											$('#licenseArea').combobox({
												data : district.districtInfoList,
												valueField : 'id',
												textField : 'name',
												editable : false
											});
										}
									});
								}
							});
						}
					});
				}
			});
		}
	});
	
	$('#licenseProvince').combobox('setValue',$("#province1").val());
	$('#licenseCity').combobox('setValue',$("#city1").val());
	$('#licenseArea').combobox('setValue',$("#area1").val());
});

$.fn.datebox.defaults.formatter = function(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d);
};

$.fn.datebox.defaults.parser = function(s) {
	if (!s)
		return new Date();
	var ss = s.split('-');
	var y = parseInt(ss[0], 10);
	var m = parseInt(ss[1], 10);
	var d = parseInt(ss[2], 10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
		return new Date(y, m - 1, d);
	} else {
		return new Date();
	}
};

function Excel() {
	var ser = $("#from").serialize();
	var urlval = '/managermall/systemmall/statistics/shopAreaListExcel.do?'+ser;
	 window.open(urlval);
  /* $.ajax({
    	type:'post',//可选get
    	url:urlval,//这里是接收数据的后台程序
    	data:$("#from").serialize(),//传给后台的数据，多个参数用&连接
    	success:function(msg){
    		if (msg.state=='scuess'){
    			alert("成功");
    		}
			if (msg.state=='error'){
				alert("失败");
    		}
    	//这里是ajax提交成功后，java程序返回的数据处理函数。msg是返回的数据，数据类型在dataType参数里定义！如果是josn,就可以直接点属性出来，列如：msg.name,msg.url 等等
    	},
    	error:function(){
    	//ajax提交失败的处理函数！
    	}
    });*/
}



