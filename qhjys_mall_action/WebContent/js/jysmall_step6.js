$(function(){
	$.ajax({
		async : false,
		type : "POST",
		url : '/queryCategoryInfo.do',
		success : function(province) {
			$('#categoryId2').combobox({
				data : province.categoryInfoList,
				valueField : 'id',
				textField : 'name',
				editable : false,
				onChange:function(record){
					var parentid = record;
					$.ajax({
						async : false,
						type : "POST",
						data: {"parentid": parentid},
						url : '/queryCategoryInfo1.do',
						success : function(city) {
							$('#categoryId').combobox({
								data : city.categoryInfoList,
								valueField : 'id',
								textField : 'name',
								editable : false,
								 onSelect:function(){
									 $("#signupForm label[class=one1]").remove();
								    }
							});
						}
					});
				}
			});
		}
	});
	
	$("#signupForm").submit(function() {
		var Avalue =   $("#categoryId").combobox('getValue');
		var Bvalue =   $("#categoryId").combobox('getText');
		$("#signupForm label[class=one1]").remove();
		if(Avalue.trim() == ""){
			var html = $('<label class="one1" style="width:120px;">请输入主营类目</label>');
			$("#categoryId").parent().append(html);
		}
		if($("#signupForm label[class=one1]").length > 0){
			return false;
		}
		$.ajax({
			async : false,
			type : "POST",
			url : '/managermall/seller/addCategory.do',
			data : {'categoryId':Avalue,'categoryDetails':Bvalue},
			success : function(data) {
				 window.location.href = '/managermall/seller/addBankInfo.do';
			},
			error : function(e) {
				alert("error");
				return false;
			}
		});
		return false;
	});
	});