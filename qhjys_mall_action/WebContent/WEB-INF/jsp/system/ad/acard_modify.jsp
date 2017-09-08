<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>
<link href="/qhjys_mall/seller-manage/css/A-newcreate.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/js/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="/js/system/ad/acard_modify.js"></script>
</head>
<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="11" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/message/uLeft.jsp" flush="true" ><jsp:param name="position" value="" /></jsp:include>
	<!--------------右侧------------------>
	<div class="memberright">
     <h3 style="padding-left:10px; margin-bottom:25px;">修改商家卡券</h3>
            <div class="content">
            <form id="from" method="post" action="/managermall/systemmall/ad/acard_update.do">
					<div class="contact-box">
                    	<div class="left-part">
  							<label>活动名称：</label>
                            <div class="clearfix"></div>
           		  		</div>
                        <div class="right-part">
                         	<input class="activity" type="text" name="acardName" value="${acard.acardName }">
                        </div>
                        <div class="clearfix"></div>
    				</div>
       			 	<div class="contact-box">
                    	<div class="left-part">
    						<label>商家ID：</label>
                            <div class="clearfix"></div>
            			</div>
                        <div class="right-part">
                         	<input class="bussiness-id" type="text" name="storeId" 
                         	readonly="readonly" value="${acard.storeId }">
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
                         	<input id="begin" name="beginDate" class="easyui-datebox con-time" 
                         	style="height:33px; width:140px;" value="<fmt:formatDate value="${acard.beginDate }" pattern="yyyy-MM-dd"/>">
                            <label class="to">至</label>
                            <input id="ending" name="endDate" class="easyui-datebox con-time" 
                            style="height:33px; width:140px;" value="<fmt:formatDate value="${acard.endDate }" pattern="yyyy-MM-dd"/>">
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
           						    <c:forEach items="${times }" var="time" varStatus="status">
           						    <tr>
           						    	<td><input type="text" name="startTime" 
           						    	value="<fmt:parseNumber integerOnly="true" value="${time.startTime/60}" />:${time.startTime%60}"
           						    	readonly="readonly"/></td>
           						    	<td><input type="text" name="endTime" 
           						    	value="<fmt:parseNumber integerOnly="true" value="${time.stopTime/60}" />:${time.stopTime%60}"
           						    	readonly="readonly"/></td>
           						    	<td><input type="button" value="删除" onclick="deletetime('${time.id }',this)"/>
           						    	<input type="hidden" value="${time.id }" name="timeIds"/>
           						    	</td>
           						    </tr>
           						    </c:forEach>
         						    </tbody>
      						  </table>
                        </div>
                        <div class="clearfix"></div> 
    				</div>
                     <div class="contact-box1">
                         <div class="right-part">
                         	<div class="lottery">
         						<span>单一微信账号每天可推送次数为</span>
          					    <input class="number" type="text" name="pushNum" value="${acard.pushNum }"
          					    onkeyup="value=value.replace(/[^(\d)]/g,'')" />
           					    <span>次</span>
							</div>
                         </div> 
                         <div class="clearfix"></div>
                     </div> 
                     
                    <c:forEach items="${prizes }" var="prize" varStatus="status">
   		 			<div class="contact-box1">
                    	<div class="left-part">
    						<h2>卡券${status.count}</h2>
             			</div>
                        <div class="clearfix"></div>
                    </div>                     
                     <div class="contact-box">
                    	 <div class="left-part">
    						 <label>名称：</label>
                             <div class="clearfix"></div>
            			 </div>
                         <div class="right-part">
                         	 <input class="name" onkeydown="www_zzjs_net(this.form.memo,this.form.remLen,15)" 
                         	 onkeyup="www_zzjs_net(this.form.memo,this.form.remLen,15)" value="${prize.prizeName }"
                         	 name="prizeName" cols="45" rows="8" wrap="on"><span class="notice">注：(最多不可超过15字)</span>
                        </div>
                        <div class="clearfix"></div>
                     </div> 
                     <p class="clearfix" style="margin:-10px 0 20px 0;">
                <label class="con-email120" for="con-email4" >使用规则</label>
                消费满&nbsp;&nbsp;<input class="con-email3" maxlength="15" name="prizeLine" value="${prize.prizeLine }" >
                &nbsp;&nbsp;元即可使用<span class="colorgray">&nbsp;&nbsp;注：(金额必须填整数)</span>    
                 </p>                       
                      <div class="contact-box">
                    	 <div class="left-part">    
    				    	 <label>描述：</label>
                             <div class="clearfix"></div>
                         </div>
                         <div class="right-part">
                         	  <textarea class="description" onkeydown="www_zzjs_net(this.form.memo,this.form.remLen,60)" 
                         	  onkeyup="www_zzjs_net(this.form.memo,this.form.remLen,60)" 
                         	  name="prizeDesc" cols="45" rows="8" wrap="on">${prize.prizeInfo }</textarea>
                         	  <span class="notice">注：(最多不可超过60字)</span>
                         </div>
                         <div class="clearfix"></div>
                      </div>           		 
                     <div class="contact-box2">
                    	<div class="right-part">
    						<img  id="img${status.count }" src="${prize.prizeImage }" width="348" height="224" alt="" />
                    		<input type="hidden" id="imgu${status.count }" name="imgs" value="${prize.prizeImage }"/>
             			</div>
             			<input type="file" value="上传图片" id="update${status.count }" />
                        <div class="clearfix"></div>
                      </div>
                      <input type="hidden" value="${prize.id }" name="prizeIds">
                     </c:forEach>
                      
            		 <div class="contact-box">
                    	 <div class="left-part">    
    				    	 <h2>概率设定</h2>
                             <div class="clearfix"></div>
                         </div>
                         <div class="right-part">
                         	   <div class="setting1">
                         	   	<c:forEach items="${prizes }" var="prize" varStatus="status">
   									<div class="set01">
   										<label>卡券${status.count }概率：</label>
                                        <input name="probability" type="text" value="${prize.probability }" 
                                        onkeyup="if(isNaN(value))execCommand('undo')" 
                                        onafterpaste="if(isNaN(value))execCommand('undo')"><span>%</span>
     							    </div>
     							    </c:forEach>
       							    <div class="clearfix"></div>     
  							   </div>
 							   <div class="tips">
   									<p>温馨提示：各卡券概率之和必须为100%</p>
  							   </div>
                          </div>
                          <div class="clearfix"></div>
            	     </div>
                    <input id="token" type="hidden" name="token" value="${token}"/>
                    <input type="hidden" value="${acardId }" name="acardId">
            		<input type="hidden" value="${acard.storeName }" name="storeName">
                    <div class="contact-box3"> 
                    	   <div class="right-part">
                     		 <input id="affirm" type="submit" value="确认">
   					  		 <input id="cancel" type="button" value="取消" onclick="quxiao()">
                     	  </div>
                    </div>
       </form>
    </div>
	</div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
<!--底部-e-->

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
             var startElement = document.createElement("input");
             startElement.readOnly = true;
             startElement.name = "startTimeNew";
             startElement.value = username;
             var endElement = document.createElement("input");
             endElement.readOnly=true;
             endElement.name = "endTimeNew";
             endElement.value = email;
             //将时间箱添加到td元素
             td1Element.appendChild(startElement);
             td2Element.appendChild(endElement);
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
