/**
 * 
 */
	function openadddiv(){
		$("#adddiv").css('display','block');
		$(".datebox").width('143px');
		
		/*$("#adddiv").show();*/
	}
	function closeadddiv(){
		$("#adddiv").css('display','none'); 
		/*$("#adddiv").hide();*/
		$("#addform")[0].reset();
		$("#storename").html("");
	}
	$.fn.datebox.defaults.formatter=function(b){var e=b.getFullYear();var a=b.getMonth()+1;var c=b.getDate();return e+"-"+(a<10?("0"+a):a)+"-"+(c<10?("0"+c):c)};$.fn.datebox.defaults.parser=function(c){if(!c){return new Date()}var b=c.split("-");var f=parseInt(b[0],10);var a=parseInt(b[1],10);var e=parseInt(b[2],10);if(!isNaN(f)&&!isNaN(a)&&!isNaN(e)){return new Date(f,a-1,e)}else{return new Date()}};
	function getstorename(){
		 var storeid = $("#storeId").val();
		 $.ajax({
				async : false,
				type : "POST",
				url : '/managermall/systemmall/rebate/getStoreName.do',
				data : {"storeId" : storeid},
				success : function(data) {
					$("#storename").html(data);
					
				}
			});
		}


	function addrebate(){
		var storename =  $("#storename").html();
		if (storename.length >0) {
			if(window.confirm('你确定要提交吗？')){
                //alert("确定");
				addform.submit();
                return true;
             }else{
                //alert("取消");
                return false;
            }
		
		}else{
			alert("请先确定商家ID是否存在");
		}
	}