$(function() { 
	$("input[type='checkbox']").click(function(){
		var num = $("input[type='checkbox']:checked").length;
		var max = $("#maxAnswer").val();
		if (max == 1) {
			$("input[type='checkbox']").removeAttr("checked");  
			this.checked = true;
		}else if(num > max){
			this.checked = false;
			alert("此题最多选择"+max+"选项");
		}
	})
}); 

function submit1(){
	if ($("#questionType").val()== 1 && $("#answerType").val()==0) {
		var num = $("input[type='checkbox']:checked").length;
		if (num >0) {
			$("#wjform").submit();
		}else{
			alert("请选择答案");
		}
	}else{
		$("#wjform").submit();
	}
	
}
