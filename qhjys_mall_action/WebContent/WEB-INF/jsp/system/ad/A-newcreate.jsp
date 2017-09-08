<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<link href="/qhjys_mall/seller-manage/css/A-newcreate.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="/css/uploadify.css" />
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/system/activity/list.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js "></script>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>

</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="11" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/message/uLeft.jsp" flush="true" ><jsp:param name="val" value="3" /></jsp:include>
    <!--------------右侧------------------>
	<!--------------右侧------------------>
	<div class="memberright">
     <h3 style="padding-left:10px; margin-bottom:25px;">新建商家卡券</h3> 
 
        
            <div class="content">
                
					<div class="contact-box">
                    	<div class="left-part">
  							<label>活动名称：</label>
                            <div class="clearfix"></div>
           		  		</div>
                        <div class="right-part">
                         	<input class="activity" type="text">
                        </div> 
                        <div class="clearfix"></div>
    				</div>
       			 	<div class="contact-box">
                    	<div class="left-part">
    						<label>商家ID：</label>
                            <div class="clearfix"></div>
            			</div>
                        <div class="right-part">
                         	<input class="bussiness-id" type="text">
                        </div> 
                        <div class="clearfix"></div>
    				</div>
       		
        			<div class="contact-box">
                    	<div class="left-part">
    						<label>活动时间：</label>
                            <div class="clearfix"></div>
            			</div>
                        <div class="right-part">
                          <div class="period">	
                         	<input id="begin" name="startDate" class="easyui-datebox con-time" value="${startDate}" style="height:33px; width:140px;">
                            <label class="to">至</label>
                            <input id="ending" name="endDate" class="easyui-datebox con-time" value="${endDate}" style="height:33px; width:140px;">
                          </div>
                            
                        </div> 
                        <div class="clearfix"></div>
    				</div>
       		
        			<div class="contact-box">
                    	<div class="left-part">
    						<label>每日活动时段：</label>
                            <div class="clearfix"></div>
            			</div>
                        <div class="right-part">
                         	 <div class="quantum">
  					  			 <input type="text" name="datepicker" id="datetimepicker1" />
   								 <label class="to">
    							 至
   	   							 </label>
   								 <input type="text" name="datepicker" id="datetimepicker2" />
   				                 <button id="add-quantum" type="button">新增时段</button>
        					    <div class="clearfix"></div>	
 							 </div>
                             <table id="tableID" border="1px red solid" align="center" width="300px">
           							<tr>    
              						  <th>开始</th>
               						  <th>结束</th>
               						  <th>操作</th>
           						    </tr>
           						    <tbody id="tbodyID">                        
         						    </tbody>
      						  </table>                           
                        </div>
                        <div class="clearfix"></div> 
    				</div>
        			
                     <div class="contact-box1">
                         <div class="right-part">
                         	<div class="lottery">
         						<span>单一微信账号每天可推送次数为</span>
          					    <input class="number" type="text" onkeyup="value=value.replace(/[^(\d)]/g,'')" />
           					    <span>次</span>
							</div>
                         </div> 
                         <div class="clearfix"></div>
                     </div> 
   		 			<div class="contact-box1">
                    	<div class="left-part">
    						<h2>卡券1</h2>
             			</div>
                        <div class="clearfix"></div>
                    </div>                     
                     <div class="contact-box">
                    	<form name="form" action="" method="post">
                    	 <div class="left-part">   
    						 <label>名称：</label>
                             <div class="clearfix"></div>
            			 </div>
                         <div class="right-part">
                         	 <input class="name" onkeydown="www_zzjs_net(this.form.memo,this.form.remLen,15)" onkeyup="www_zzjs_net(this.form.memo,this.form.remLen,15)" name="memo" cols="45" rows="8" wrap="on"><span class="notice">注：(最多不可超过15字)</span>
                        </div>
                        <div class="clearfix"></div>
                        </form>
                     </div> 
                     <p class="clearfix" style="margin:-10px 0 20px 0;">
                <label class="con-email120" for="con-email4" >使用规则</label>
                消费满&nbsp;&nbsp;<input class="con-email3" maxlength="15"  name="rebateName" value="${rebateName }" >&nbsp;&nbsp;元即可使用<span class="colorgray">&nbsp;&nbsp;注：(金额必须填整数)</span>    
                 </p>                       
                      <div class="contact-box">
                       <form name="form" action="" method="post">
                    	 <div class="left-part">    
    				    	 <label>描述：</label>
                             <div class="clearfix"></div>
                         </div>
                         <div class="right-part">
                         	  <textarea class="description" onkeydown="www_zzjs_net(this.form.memo,this.form.remLen,60)" onkeyup="www_zzjs_net(this.form.memo,this.form.remLen,60)" name="memo" cols="45" rows="8" wrap="on"></textarea><span class="notice">注：(最多不可超过60字)</span>
                         </div>
                         <div class="clearfix"></div>
                        </form>
                      </div>           		 
                     <div class="contact-box2">
                    	<div class="right-part">
    						<img width="348" height="224" alt="" />
             			</div>
             			<button type="submit">上传</button>
                        <input id="contact-butn" type="file" multiple>                        
                        <div class="clearfix"></div>
                      </div> 
                     
                     
            		 <div class="contact-box1">
                    	<div class="left-part">
    						<h2>卡券2</h2>
             			</div>
                        <div class="clearfix"></div>             
                      </div>                                          
                     <div class="contact-box">
                      <form name="form" action="" method="post">
                    	<div class="left-part">   
    						 <label>名称：</label>
                             <div class="clearfix"></div>
            			</div>
                         <div class="right-part">
                         	 <input class="name" onkeydown="www_zzjs_net(this.form.memo,this.form.remLen,15)" onkeyup="www_zzjs_net(this.form.memo,this.form.remLen,15)" name="memo" cols="45" rows="8" wrap="on"><span class="notice">
                             <span class="notice">注：(最多不可超过15字)</span>
                        </div>
                        <div class="clearfix"></div>
                       </form> 
                     </div> 
                     <p class="clearfix" style="margin:-10px 0 20px 0;">
                    <label class="con-email120" for="con-email4" >使用规则</label>
                    消费满&nbsp;&nbsp;<input class="con-email3" maxlength="15"  name="rebateName" value="${rebateName }" >&nbsp;&nbsp;元即可使用<span class="colorgray">&nbsp;&nbsp;注：(金额必须填整数)</span>    
                     </p>                    
                      <div class="contact-box">
                       <form name="form" action="" method="post">
                    	<div class="left-part">    
    				    	 <label>描述：</label>
                             <div class="clearfix"></div>
                        </div>
                        
                         <div class="right-part">
                         	  <textarea class="description" onkeydown="www_zzjs_net(this.form.memo,this.form.remLen,60)" onkeyup="www_zzjs_net(this.form.memo,this.form.remLen,60)" name="memo" cols="45" rows="8" wrap="on"></textarea><span class="notice">注：(最多不可超过60字)</span>
                        </div>
                        <div class="clearfix"></div>
                       </form>
                      </div>
                       <div class="contact-box2">
                    	<div class="right-part">
    						<img width="348" height="224" alt="" />
             			</div>
             			<button type="submit">上传</button>
                        <input id="contact-butn" type="file" multiple>                       
                        <div class="clearfix"></div>
                      </div> 
                      
                      
                       
                        <div class="contact-box1">
                    		<div class="left-part">
    							<h2>卡券3</h2>
             				</div>
                            <div class="clearfix"></div>              
                        </div>                               
                     <div class="contact-box">
                      <form name="form" action="" method="post">
                    	<div class="left-part">   
    						 <label>名称：</label>
                             <div class="clearfix"></div>
            			</div>
                         <div class="right-part">
                         	 <input class="name" onkeydown="www_zzjs_net(this.form.memo,this.form.remLen,15)" onkeyup="www_zzjs_net(this.form.memo,this.form.remLen,15)" name="memo" cols="45" rows="8" wrap="on"><span class="notice"><span class="notice">注：(最多不可超过15字)</span>
                        </div>
                        <div class="clearfix"></div>
                        </form>
                      </div> 
                      <p class="clearfix" style="margin:-10px 0 20px 0;">
                <label class="con-email120" for="con-email4" >使用规则</label>
                消费满&nbsp;&nbsp;<input class="con-email3" maxlength="15"  name="rebateName" value="${rebateName }" >&nbsp;&nbsp;元即可使用<span class="colorgray">&nbsp;&nbsp;注：(金额必须填整数)</span>    
                 </p>                        
                      <div class="contact-box">
                       <form name="form" action="" method="post">
                    	<div class="left-part">    
    				    	 <label>描述：</label>
                             <div class="clearfix"></div>
                        </div>
                         <div class="right-part">
                         	  <textarea class="description" onkeydown="www_zzjs_net(this.form.memo,this.form.remLen,60)" onkeyup="www_zzjs_net(this.form.memo,this.form.remLen,60)" name="memo" cols="45" rows="8" wrap="on"></textarea><span class="notice">注：(最多不可超过60字)</span>
                        </div>
                        <div class="clearfix"></div>
                       </form>
                       </div> 
                        <div class="contact-box2">
                    	<div class="right-part">
    						<img width="348" height="224" alt="" />
             			</div>
             			<button type="submit">上传</button>
                        <input id="contact-butn" type="file" multiple>
                        
                        <div class="clearfix"></div>
                      </div> 
                       
                        <div class="contact-box1">
                    		<div class="left-part">
    							<h2>卡券4</h2>
             				</div>
                            <div class="clearfix"></div>
                        </div>                                          
                     <div class="contact-box">
                      <form name="form" action="" method="post">
                    	<div class="left-part">   
    						 <label>名称：</label>
                             <div class="clearfix"></div>
            			</div>
                         <div class="right-part">
                         	 <input class="name" onkeydown="www_zzjs_net(this.form.memo,this.form.remLen,15)" onkeyup="www_zzjs_net(this.form.memo,this.form.remLen,15)" name="memo" cols="45" rows="8" wrap="on"><span class="notice"><span class="notice">注：(最多不可超过15字)</span>
                        </div>
                        <div class="clearfix"></div>
                        </form>
                     </div>
                     <p class="clearfix" style="margin:-10px 0 20px 0;">
                <label class="con-email120" for="con-email4" >使用规则</label>
                消费满&nbsp;&nbsp;<input class="con-email3" maxlength="15"  name="rebateName" value="${rebateName }" >&nbsp;&nbsp;元即可使用<span class="colorgray">&nbsp;&nbsp;注：(金额必须填整数)</span>    
                 </p>                                             
                      <div class="contact-box">
                       <form name="form" action="" method="post">
                    	<div class="left-part">    
    				    	 <label>描述：</label>
                             <div class="clearfix"></div>
                        </div>
                         <div class="right-part">
                         	  <textarea class="description" onkeydown="www_zzjs_net(this.form.memo,this.form.remLen,60)" onkeyup="www_zzjs_net(this.form.memo,this.form.remLen,60)" name="memo" cols="45" rows="8" wrap="on"></textarea><span class="notice">注：(最多不可超过60字)</span>
                        </div>
                        <div class="clearfix"></div>
                       </form>
            		  </div>
            		 <div class="contact-box2">
                    	<div class="right-part">
    						<img width="348" height="224" alt="" />
             			</div>
             			<button type="submit">上传</button>
                        <input id="contact-butn" type="file" multiple>                        
                        <div class="clearfix"></div>
                      </div> 
                      
            		 <div class="contact-box">
                    	 <div class="left-part">    
    				    	 <h2>概率设定</h2>
                             <div class="clearfix"></div>
                         </div>
                         <div class="right-part">
                         	   <div class="setting1">
   									<div class="set01">
   										<label>卡券1概率：</label>
                                        <input type="text" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"><span>%</span>
     							    </div>
      							    <div class="set03">
   										<label>卡券3概率：</label>
                                        <input type="text" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"><span>%</span>
       							    </div>  
       							    <div class="clearfix"></div>     
  							   </div>  
  							   <div class="setting2">
   									<div class="set02">
   										<label>卡券2概率：</label>
                                        <input type="text" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"><span>%</span>
       							    </div>
        
        							<div class="set04">
   										<label>卡券4概率：</label>
                                        <input type="text" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"><span>%</span>
      							    </div> 
      							    <div class="clearfix"></div> 
  							   </div>
 							   <div class="tips">
   									<p>温馨提示：各卡券概率之和必须为100%</p>
  							   </div>
                          </div>
                          <div class="clearfix"></div>
            	     </div>
                     
                    <div class="contact-box3"> 
                    	   <div class="right-part">
                     		 <input id="affirm" type="button" value="确认">
   					  		 <input id="cancel" type="button" value="取消">
                     	  </div>
                     </div>
                     
                     
            
    </div>        

            
	</div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
<!--底部-e-->


<!--输入框只能输入数字-->
<script type="text/javascript">
 function SumbitBatch(form,fun){
 }
</script>


<!--输入框字数限制-->
<script language="JavaScript">
function www_zzjs_net(field, countfield, maxlimit) {
if (field.value.length > maxlimit)
field.value = field.value.substring(0,maxlimit);
else
countfield.value = maxlimit - field.value.length;
}
</script>



<!--每日活动时段-->
<script src="/js/jquery-1.7.2.min.js"></script>
<script src="/A-kaquan/selectUi.js" type="text/javascript"></script>
<script src="/A-kaquan/lq.datetimepick.js" type="text/javascript"></script>
<script type="text/javascript">
$(function (){
	$("#datetimepicker1").on("click",function(e){
		e.stopPropagation();
		$(this).lqdatetimepicker({
			css : 'datetime-hour'
		});
	});
	$("#datetimepicker2").on("click",function(e){
		e.stopPropagation();
		$(this).lqdatetimepicker({
			css : 'datetime-hour'
		});
	});	
});
//动态增加和删除表格行的内容
       document.getElementById("add-quantum").onclick=function(){
         //获取时间
         var username = document.getElementById("datetimepicker1").value;
         var email = document.getElementById("datetimepicker2").value;
			
		
			 if(username!="" && email!=""){
             //创建tr元素
             var trElemnet = document.createElement("tr");
             //创建td元素
             var td1Element = document.createElement("td");
            var td2Element = document.createElement("td");
             var td3Element = document.createElement("td");
             //将时间箱添加到td元素
            td1Element.innerHTML = username;
             td2Element.innerHTML = email;
             //创建按钮
             var delElement = document.createElement("input");
             delElement.type="button";
             delElement.value="删除";
             //为按钮添加单击事件
             delElement.onclick=function(){
                 //删除按钮所在的tr对象
                 trElemnet.parentNode.removeChild(trElemnet);                
             }
             td3Element.appendChild(delElement);
             //将td元素添加到tr元素中
             trElemnet.appendChild(td1Element);
             trElemnet.appendChild(td2Element);
             trElemnet.appendChild(td3Element);
            //将tr元素添加到tbody元素中
             document.getElementById("tbodyID").appendChild(trElemnet);
             //清空文本框中的值
             document.getElementById("datetimepicker1").value="";
             document.getElementById("datetimepicker2").value="";
         }
		 
		 else{
			 alert("不可为空");
    
			 }
}
</script>

</body>
</html>
