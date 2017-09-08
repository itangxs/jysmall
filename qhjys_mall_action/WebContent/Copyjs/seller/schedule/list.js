$(document).ready(function() {
	ajaxScheduleDate(0);
});

function ajaxScheduleDate(dateadd){
	var param = 'dateadd='+dateadd;
	var urlval = '/managermall/seller/schedule/productListAjax.do';
   $.ajax({
    	type:'post',//可选get
    	url:urlval,//这里是接收数据的后台程序
    	data:param,//传给后台的数据，多个参数用&连接
    	success:function(data){
    		$("#scheduleDateList").empty();
	    	var tempHtml = "";
	    	if (null!=data){
	    		  		  
	    		for(var i=0; i<data.length; i++)
    		    {
	    			tempHtml += "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";  
	    			tempHtml += "<tr>";
		    		tempHtml += "<td class=\"td160\">"+(data[i].productTitle)+"</td>";
		    		if (data[i].day1==0){
		    			tempHtml += "<td class=\"td160\"><input type=\"submit\" value=\"预约已满\" class=\"submit8\"></td>";
		    		}else{
		    			tempHtml += "<td class=\"td160\"><input type=\"submit\" value=\"可预约"+data[i].day1+"\" class=\"submit6\"></td>";
		    		}
		    		if (data[i].day2==0){
		    			tempHtml += "<td class=\"td160\"><input type=\"submit\" value=\"预约已满\" class=\"submit8\"></td>";
		    		}else{
		    			tempHtml += "<td class=\"td160\"><input type=\"submit\" value=\"可预约"+data[i].day2+"\" class=\"submit6\"></td>";
		    		}
		    		if (data[i].day3==0){
		    			tempHtml += "<td class=\"td160\"><input type=\"submit\" value=\"预约已满\" class=\"submit8\"></td>";
		    		}else{
		    			tempHtml += "<td class=\"td160\"><input type=\"submit\" value=\"可预约"+data[i].day3+"\" class=\"submit6\"></td>";
		    		}
		    		if (data[i].day4==0){
		    			tempHtml += "<td class=\"td160\"><input type=\"submit\" value=\"预约已满\" class=\"submit8\"></td>";
		    		}else{
		    			tempHtml += "<td class=\"td160\"><input type=\"submit\" value=\"可预约"+data[i].day4+"\" class=\"submit6\"></td>";
		    		}
		    		if (data[i].day5==0){
		    			tempHtml += "<td class=\"td160\"><input type=\"submit\" value=\"预约已满\" class=\"submit8\"></td>";
		    		}else{
		    			tempHtml += "<td class=\"td160\"><input type=\"submit\" value=\"可预约"+data[i].day5+"\" class=\"submit6\"></td>";
		    		}
		    		if (data[i].day6==0){
		    			tempHtml += "<td class=\"td160\"><input type=\"submit\" value=\"预约已满\" class=\"submit8\"></td>";
		    		}else{
		    			tempHtml += "<td class=\"td160\"><input type=\"submit\" value=\"可预约"+data[i].day6+"\" class=\"submit6\"></td>";
		    		}
		    		if (data[i].day7==0){
		    			tempHtml += "<td class=\"td160\"><input type=\"submit\" value=\"预约已满\" class=\"submit8\"></td>";
		    		}else{
		    			tempHtml += "<td class=\"td160\"><input type=\"submit\" value=\"可预约"+data[i].day7+"\" class=\"submit6\"></td>";
		    		}
		    		
		    		tempHtml += "<td class=\"td80\">&nbsp;</td>";
		    		tempHtml += "</tr>";
		    		tempHtml += "</table>";
    		    }
	    		
	    		document.getElementById('scheduleDateList').innerHTML = tempHtml;
	    	}
    	//这里是ajax提交成功后，java程序返回的数据处理函数。msg是返回的数据，数据类型在dataType参数里定义！如果是josn,就可以直接点属性出来，列如：msg.name,msg.url 等等
    	},
    	error:function(){
    	//ajax提交失败的处理函数！
    	}
    });
}

function setTab(m,n){  
  
 var tli=document.getElementById("menu"+m).getElementsByTagName("li");  
  
 var mli=document.getElementById("main"+m).getElementsByTagName("ul");  
  
 for(i=0;i<tli.length;i++){  
  
  tli[i].className=i==n?"hover":"";  
  
  mli[i].style.display=i==n?"block":"none";  
  
 }  
} 
