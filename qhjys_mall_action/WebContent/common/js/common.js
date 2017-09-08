
/*  start  */
//全部选中或取消 复选框
function selectAll(oCBox,name){
	var isCheck = $(oCBox).attr("checked");
	if(name == null){
		name = "cbox_tab";
	}
	$("input[name="+name+"][type=checkbox]").each(function() {
		$(this).attr("checked",isCheck);
	});
}

//单个选中或取消
function selectCheckBox(oThis,oCBox){
	var isCheck = $(oThis).attr("checked");
	if(isCheck == true){
		var count = 0;
		var name = oThis.attr("name");
		var oCheckBoxlist = $("input[name="+name+"][type=checkbox]");
		oCheckBoxlist.each(function() {
			if($(this).attr("checked")){
				count++;
			}
			if(count == oCheckBoxlist.size()){
				oCBox.attr("checked",true);
			}
			if(count == 0){
				oCBox.attr("checked",false);
			}
		});
	}else{
		oCBox.attr("checked",false);
	}
	return ;
}

//全部选中或取消 复选框  取值
function getSelect(name){
	var oCheckBoxlist = $("input[name="+name+"][type=checkbox]");
	var slist = "";
	oCheckBoxlist.each(function() {
		if($(this).attr("checked")){
			var sId = $(this).attr("id");
			slist += sId.split("_")[0] + "_";
		}
	});
	return slist;
}
/*  end  */

/* 消息提示 */
$(document).ready(
		function (){
			$("#clew_message").click(
					function() {
						hidden_message();
					}
			);
			if($("#message").val() == "1"){
				show_message();
				$("#message").val("");
			};
		}
);

function hidden_message(){
	$("div.pagenotice-message").hide();
}

function show_message(){
	$("div.pagenotice-message").show();
	setTimeout(hidden_message,10000);
}

/*    end   */

function gotoForm(id){
	var id = "#show_"+id;
	$("#gotoForm").attr("action",$(id).attr("name"));
	$("#gotoForm").submit();
}

function back(){
	$("#gotoForm").submit();
}

function searchForm() {
	$("#targetpage").val("1");
	$("#dataForm").submit();
}



