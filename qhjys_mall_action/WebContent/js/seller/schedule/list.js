$(document).ready(function(){ajaxScheduleDate(0)});function ajaxScheduleDate(b){var c="dateadd="+b;var a="/managermall/seller/schedule/productListAjax.do";$.ajax({type:"post",url:a,data:c,success:function(f){$("#scheduleDateList").empty();var e="";if(null!=f){for(var d=0;d<f.length;d++){e+='<table width="100%" border="0" cellspacing="0" cellpadding="0">';e+="<tr>";e+='<td class="td160">'+(f[d].productTitle)+"</td>";if(f[d].day1==0){e+='<td class="td160"><input type="submit" value="预约已满" class="submit8"></td>'}else{e+='<td class="td160"><input type="submit" value="可预约'+f[d].day1+'" class="submit6"></td>'}if(f[d].day2==0){e+='<td class="td160"><input type="submit" value="预约已满" class="submit8"></td>'}else{e+='<td class="td160"><input type="submit" value="可预约'+f[d].day2+'" class="submit6"></td>'}if(f[d].day3==0){e+='<td class="td160"><input type="submit" value="预约已满" class="submit8"></td>'}else{e+='<td class="td160"><input type="submit" value="可预约'+f[d].day3+'" class="submit6"></td>'}if(f[d].day4==0){e+='<td class="td160"><input type="submit" value="预约已满" class="submit8"></td>'}else{e+='<td class="td160"><input type="submit" value="可预约'+f[d].day4+'" class="submit6"></td>'}if(f[d].day5==0){e+='<td class="td160"><input type="submit" value="预约已满" class="submit8"></td>'}else{e+='<td class="td160"><input type="submit" value="可预约'+f[d].day5+'" class="submit6"></td>'}if(f[d].day6==0){e+='<td class="td160"><input type="submit" value="预约已满" class="submit8"></td>'}else{e+='<td class="td160"><input type="submit" value="可预约'+f[d].day6+'" class="submit6"></td>'}if(f[d].day7==0){e+='<td class="td160"><input type="submit" value="预约已满" class="submit8"></td>'}else{e+='<td class="td160"><input type="submit" value="可预约'+f[d].day7+'" class="submit6"></td>'}e+='<td class="td80">&nbsp;</td>';e+="</tr>";e+="</table>"}document.getElementById("scheduleDateList").innerHTML=e}},error:function(){}})}function setTab(a,d){var c=document.getElementById("menu"+a).getElementsByTagName("li");var b=document.getElementById("main"+a).getElementsByTagName("ul");for(i=0;i<c.length;i++){c[i].className=i==d?"hover":"";b[i].style.display=i==d?"block":"none"}};