/**
 * 选择类型
 */
function submitForm(){
	var form = document.getElementById("from");
	form.submit();
}
function submitForm1(){
	var form1 = document.getElementById("from1");
	form1.submit();
}
function submitForm2(){
	var form2 = document.getElementById("from2");
	form2.submit();
}

function Excel() {
	/*var b = $("#from2").serialize();*/
	var a = "/managermall/systemmall/cardcoupon/valiDataListExcel.do";
	window.open(a);
};

function submitDate2(obj){
	var date=$("#aRecent7DaysDemo3").val();
	var selectValue = obj.options[obj.selectedIndex].value;
	if(selectValue == -1){
		var form2 = document.getElementById("from2");
		$("#startTimeinp2").attr("value","");
		$("#endTimeinp2").attr("value","");
		form2.submit();
	}else if(selectValue == 7){
		var dateRange = new pickerDateRange('date_demo3', {
			aRecent7Days : 'aRecent7DaysDemo3',
			isTodayValid : false,
			defaultText : ' 至 ',
			inputTrigger : 'input_trigger_demo3',
			theme : 'ta',
			success : function(obj) {
				   $("#startTimeinp2").val(obj.startDate);
				   $("#endTimeinp2").val(obj.endDate);
				   var form2 = document.getElementById("from2");
				   form2.submit();
			}
		});
	}else if(selectValue==14){
		var dateRange = new pickerDateRange('date_demo3', {
			aRecent14Days : 'aRecent7DaysDemo3',
			isTodayValid : false,
			defaultText : ' 至 ',
			inputTrigger : 'input_trigger_demo3',
			theme : 'ta',
			success : function(obj) {
				$("#startTimeinp2").val(obj.startDate);
				$("#endTimeinp2").val(obj.endDate);
				var form2 = document.getElementById("from2");
				form2.submit();
			}
		});
	}else if(selectValue==30){
		var dateRange = new pickerDateRange('date_demo3', {
			aRecent30Days : 'aRecent7DaysDemo3',
			isTodayValid : false,
			defaultText : ' 至 ',
			inputTrigger : 'input_trigger_demo3',
			theme : 'ta',
			success : function(obj) {
				$("#startTimeinp2").val(obj.startDate);
				$("#endTimeinp2").val(obj.endDate);
				  var form2 = document.getElementById("from2");
				  form2.submit();
			}
		});
	}
}
function submitDate1(obj){
	var selectValue = obj.options[obj.selectedIndex].value;
	if(selectValue == -1){
		var form1 = document.getElementById("from1");
		$("#startTimeinp1").attr("value","");
		$("#endTimeinp1").attr("value","");
		form1.submit();
	}else if(selectValue==7){
		var dateRange = new pickerDateRange('date_demo3', {
			aRecent7Days : 'aRecent7DaysDemo3',
			isTodayValid : false,
			defaultText : ' 至 ',
			inputTrigger : 'input_trigger_demo3',
			theme : 'ta',
			success : function(obj) {
				   $("#startTimeinp1").val(obj.startDate);
				   $("#endTimeinp1").val(obj.endDate);
				   var form1 = document.getElementById("from1");
				   form1.submit();
			}
		});
	}else if(selectValue==14){
		var dateRange = new pickerDateRange('date_demo3', {
			aRecent14Days : 'aRecent7DaysDemo3',
			isTodayValid : false,
			defaultText : ' 至 ',
			inputTrigger : 'input_trigger_demo3',
			theme : 'ta',
			success : function(obj) {
				$("#startTimeinp1").val(obj.startDate);
				$("#endTimeinp1").val(obj.endDate);
				var form1 = document.getElementById("from1");
				form1.submit();
			}
		});
	}else if(selectValue==30){
		var dateRange = new pickerDateRange('date_demo3', {
			aRecent30Days : 'aRecent7DaysDemo3',
			isTodayValid : false,
			defaultText : ' 至 ',
			inputTrigger : 'input_trigger_demo3',
			theme : 'ta',
			success : function(obj) {
				$("#startTimeinp1").val(obj.startDate);
				$("#endTimeinp1").val(obj.endDate);
				  var form1 = document.getElementById("from1");
				  form1.submit();
			}
		});
	}
}
function submitDate(obj){
	var selectValue = obj.options[obj.selectedIndex].value;
	if(selectValue == -1){
		var form = document.getElementById("from");
		$("#startTimeinp").attr("value","");
		$("#endTimeinp").attr("value","");
		form.submit();
	}else if(selectValue==7){
		var dateRange = new pickerDateRange('date_demo3', {
			aRecent7Days : 'aRecent7DaysDemo3',
			isTodayValid : false,
			defaultText : ' 至 ',
			inputTrigger : 'input_trigger_demo3',
			theme : 'ta',
			success : function(obj) {
				   $("#startTimeinp").val(obj.startDate);
				   $("#endTimeinp").val(obj.endDate);
				   var form = document.getElementById("from");
				   form.submit();
			}
		});
	}else if(selectValue==14){
		var dateRange = new pickerDateRange('date_demo3', {
			aRecent14Days : 'aRecent7DaysDemo3',
			isTodayValid : false,
			defaultText : ' 至 ',
			inputTrigger : 'input_trigger_demo3',
			theme : 'ta',
			success : function(obj) {
				$("#startTimeinp").val(obj.startDate);
				$("#endTimeinp").val(obj.endDate);
				var form = document.getElementById("from");
				form.submit();
			}
		});
	}else if(selectValue==30){
		var dateRange = new pickerDateRange('date_demo3', {
			aRecent30Days : 'aRecent7DaysDemo3',
			isTodayValid : false,
			defaultText : ' 至 ',
			inputTrigger : 'input_trigger_demo3',
			theme : 'ta',
			success : function(obj) {
				$("#startTimeinp").val(obj.startDate);
				$("#endTimeinp").val(obj.endDate);
				  var form = document.getElementById("from");
				  form.submit();
			}
		});
	}
}

$(function(){
	var dateRange = new pickerDateRange('date_demo', {
		startDate:$("#startTimeinp").val(),
		endDate:$("#endTimeinp").val(),
		isTodayValid : false,
		defaultText : ' 至 ', 
		inputTrigger : 'input_trigger_demo',
		theme : 'ta',
		success : function(obj) {
			$("#startTimeinp").val(obj.startDate);
			$("#endTimeinp").val(obj.endDate);
			var form = document.getElementById("from");
			form.submit();
		}
	});
	var dateRange = new pickerDateRange('date_demo1',{
		startDate:$("#startTimeinp1").val(),
		endDate:$("#endTimeinp1").val(),
		isTodayValid : false,
		defaultText : ' 至 ', 
		inputTrigger : 'input_trigger_demo1',
		theme : 'ta',
		success : function(obj) {
			$("#startTimeinp1").val(obj.startDate);
			$("#endTimeinp1").val(obj.endDate);
			var form1 = document.getElementById("from1");
			form1.submit();
		}
	});
	var dateRange = new pickerDateRange('date_demo2',{
		startDate:$("#startTimeinp2").val(),
		endDate:$("#endTimeinp2").val(),
		isTodayValid : false,
		defaultText : ' 至 ', 
		inputTrigger : 'input_trigger_demo2',
		theme : 'ta',
		success : function(obj) {
			$("#startTimeinp2").val(obj.startDate);
			$("#endTimeinp2").val(obj.endDate);
			var form2 = document.getElementById("from2");
			form2.submit();
		}
	});
});







