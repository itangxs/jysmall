var getCity = function(e) {
	$.ajax({
		async : false,
		type : "POST",
		url : '/queryCity.do',
		data : {
			provId : e
		},
		success : function(data) {
			$('#city').combobox({
				data : data,
				valueField : 'id',
				textField : 'name',
				editable : false,
				onChange : getArea
			}).combobox('setValue', $('#city').attr("value"));
		}
	});
};

var getArea = function(e) {
	$.ajax({
		async : false,
		type : "POST",
		url : '/queryArea.do',
		data : {
			cityId : e
		},
		success : function(data) {
			$('#area').combobox({
				data : data,
				valueField : 'id',
				textField : 'name',
				editable : false
			}).combobox('setValue', $('#area').attr("value"));
		}
	});
};

$(function() {
	$.ajax({
		async : false,
		type : "POST",
		url : '/queryProv.do',
		success : function(data) {
			$('#province').combobox({
				data : data,
				valueField : 'id',
				textField : 'name',
				editable : false,
				onChange : getCity
			}).combobox('setValue', $('#province').attr("value"));
		}
	});
	getCity(null);
	getArea(null);
	$('#form').submit(function() {
		var prov = $('#province').combobox('getValue');
		var city = $('#city').combobox('getValue');
		var area = $('#area').combobox('getValue');
		if (prov == "" || city == "" || area == "")
			return false;
	});
});