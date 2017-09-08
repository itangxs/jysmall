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
<script type="text/javascript" src="/js/pagingUtil.js"></script>


<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/system/statistics/sales_discount_list.js"></script>

</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp" flush="true" ><jsp:param name="val" value="11" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/message/uLeft.jsp" flush="true" ><jsp:param name="position" value="7" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
        <h3 style="padding-left:0px; margin-bottom:25px;">新建B卡券</h3>
           <form id="from" method="post" action="/managermall/systemmall/ad/hongbao_list.jsp">
    			<input type="hidden" id="page" name="pageNum" value="${page.getPageNum()}">
				<input type="hidden" id="pageSize" name="pageSize" value="${page.getPageSize()}">
                <p class="clearfix">
            <label class="con-email120" for="con-email4" >商家ID</label>
        	<input class="con-email5" name="rebateName" value="${rebateName }" >    
        	 </p>
            <p class="clearfix" style="margin-top:8px;">
        	<label class="con-email120" for="con-email4" >活动时间</label>       
        	<input id="begin" name="startDate" class="easyui-datebox con-time" value="${benginTime}">
			<label class="one" for="con-email2">至</label>
			<input id="ending" name="endDate" class="easyui-datebox con-time" value="${endTime}">
            <label class="one" for="con-email4" >
         </p>
         <p class="clearfix">
            <label class="con-email120" for="con-email130" >有效周期</label>
        	<input class="con-email3" name="rebateName" value="${rebateName }" >&nbsp;天&nbsp;&nbsp;&nbsp;<span class="colorgray">注：(7天/14天/21天 可填)</span>
        	 </p>
             <p class="clearfix">
            <label class="con-email120" for="con-email4" >商家描述</label>
            <textarea class="textarea_top" onchange="this.value=this.value.substring(0, 60)" onkeydown="this.value=this.value.substring(0, 60)" onkeyup="this.value=this.value.substring(0, 60)"></textarea>&nbsp;&nbsp;<span class="colorgray">注：(字数不超过60字)</span>
        	 </p>
             <div class="title_bggray">商家自荐卡券</div>
             <div class="kaquan_zijianbox">
                 <div class="kaquantitle">卡券1</div>
                 <p class="clearfix">
                <label class="con-email120" for="con-email4" >奖品名称</label>
                <input class="con-email5" maxlength="15"  name="rebateName" value="${rebateName }" >&nbsp;&nbsp;<span class="colorgray">注：(字数不超过15字)</span>    
                 </p>
                 <p class="clearfix">
                <label class="con-email120" for="con-email4" >奖品描述</label>
                <textarea class="textarea_top" onchange="this.value=this.value.substring(0, 60)" onkeydown="this.value=this.value.substring(0, 60)" onkeyup="this.value=this.value.substring(0, 60)"></textarea>&nbsp;&nbsp;<span class="colorgray">注：(字数不超过60字)</span>
                 </p>
                 <p class="clearfix">
                    <label class="con-email120" for="con-email4" ></label>
                    <img  id="" class="form_img1"  src="" alt="图片" />
                    <input type="hidden" id="imgupload" name="dishImage" value=" "/>
                    <input  class="uploadify" type="file" value="上传" id="update1"/>
                 </p>
             </div>
             <div class="kaquan_zijianbox">
                 <div class="kaquantitle">卡券2</div>
                 <p class="clearfix">
                <label class="con-email120" for="con-email4" >奖品名称</label>
                <input class="con-email5" maxlength="15" name="rebateName" value="${rebateName }" >&nbsp;&nbsp;<span class="colorgray">注：(字数不超过15字)</span>    
                 </p>
                 <p class="clearfix">
                <label class="con-email120" for="con-email4" >奖品描述</label>
                <textarea class="textarea_top"  onchange="this.value=this.value.substring(0, 60)" onkeydown="this.value=this.value.substring(0, 60)" onkeyup="this.value=this.value.substring(0, 60)"></textarea>&nbsp;&nbsp;<span class="colorgray">注：(字数不超过60字)</span>
                 </p>
                 <p class="clearfix">
                    <label class="con-email120" for="con-email4" ></label>
                    <img  id="" class="form_img1"  src="" alt="图片" />
                    <input type="hidden" id="imgupload" name="dishImage" value=" "/>
                    <input  class="uploadify" type="file" value="上传" id="update1"/>
                 </p>
             </div>
             <div class="kaquan_zijianbox">
                 <div class="kaquantitle">卡券3</div>
                 <p class="clearfix">
                <label class="con-email120" for="con-email4" >奖品名称</label>
                <input class="con-email5" maxlength="15" name="rebateName" value="${rebateName }" >&nbsp;&nbsp;<span class="colorgray">注：(字数不超过15字)</span>    
                 </p>
                 <p class="clearfix">
                <label class="con-email120" for="con-email4" >奖品描述</label>
                <textarea class="textarea_top"  onchange="this.value=this.value.substring(0, 60)" onkeydown="this.value=this.value.substring(0, 60)" onkeyup="this.value=this.value.substring(0, 60)"></textarea>&nbsp;&nbsp;<span class="colorgray">注：(字数不超过60字)</span>
                 </p>
                 <p class="clearfix">
                    <label class="con-email120" for="con-email4" ></label>
                    <img  id="" class="form_img1"  src="" alt="图片" />
                    <input type="hidden" id="imgupload" name="dishImage" value=" "/>
                    <input  class="uploadify" type="file" value="上传" id="update1"/>
                 </p>
             </div>
             <div class="kaquan_zijianbox">
                 <div class="kaquantitle">卡券4</div>
                 <p class="clearfix">
                <label class="con-email120" for="con-email4" >奖品名称</label>
                <input class="con-email5" maxlength="15" name="rebateName" value="${rebateName }" >&nbsp;&nbsp;<span class="colorgray">注：(字数不超过15字)</span>    
                 </p>
                 <p class="clearfix">
                <label class="con-email120" for="con-email4" >奖品描述</label>
                <textarea class="textarea_top"  onchange="this.value=this.value.substring(0, 60)" onkeydown="this.value=this.value.substring(0, 60)" onkeyup="this.value=this.value.substring(0, 60)"></textarea>&nbsp;&nbsp;<span class="colorgray">注：(字数不超过60字)</span>
                 </p>
                 <p class="clearfix">
                    <label class="con-email120" for="con-email4" ></label>
                    <img  id="" class="form_img1"  src="" alt="图片" />
                    <input type="hidden" id="imgupload" name="dishImage" value=" "/>
                    <input  class="uploadify" type="file" value="上传" id="update1"/>
                 </p>
             </div>
             <div class="title_bggray">商家被荐卡券</div>
             	
          
          <div class="addguizebox" id="addguize">
          	<p class="clearfix">
             		<h3>推荐范围及规则</h3>                
       	  	   </p>
          	<dl>
            
            
            	<dd>
                
                    <div class="title_gz">规则</div>
                    
                    
                  <div class="diqubox"> 
                                     
                  <ul>
                  		
                      <li>城市<br>
                          <select name="">
                              <option>深圳市</option>
                          </select>
                      </li>
                      <li>行政区<br>
                          <select name="">
                              <option>福田区</option>
                          </select>
                      </li>
                      <li>商圈<br>
                          <select name="">
                              <option>华强北</option>
                          </select>
                      </li>
                      <li>大行业<br>
                          <select name="">
                              <option>餐饮</option>
                              <option>非餐饮</option>
                          </select>
                      </li>
                      <li>细分行业<br>
                          <select name="">
                              <option>休闲娱乐</option>
                              <option>丽人</option>
                              <option>生活服务</option>
                              <option>爱车</option>
                              <option>宠物</option>
                              <option>培训</option>
                              <option>运动健身</option>
                          </select>
                      </li>
                      
                  </ul>
                  
                  <br>
                 <div>
                	<input type="button"   class="submit12"  value="增加" id="addTable" onclick="add_tr(this)"/>
               		<input type="button"  class="submit8"   value="删除" id="deleteTable" onclick="del_tr(this)"/>
                </div>
                </div>
                
            </dd>
            
            </dl>
            
           
            
              
          </div>
          
                   <div class="addguizebox" id="addguize">
          	<p class="clearfix">
             		<h3>推荐店铺</h3>                
       	  	   </p>
          	<dl>
            
            
            	<dd>
                
                    <div class="title_gz">店铺</div>
                    
                    
                  <div class="diqubox diqubox1"> 
                                     
                  <ul>
                  		
                      <li>
                      	  <p>店铺ID</p>
                          <input class="bussiness_num" value="1648">
                          <p style="color:#999">( <span class="store_id">永和豆浆</span> )</p>
                      </li>
                      
                      
                  </ul>
                  
        
                 <div>
                	<input type="button"   class="submit12"  value="增加" id="addTable" onclick="add_tr(this)"/>
               		<input type="button"  class="submit8"   value="删除" id="deleteTable" onclick="del_tr(this)"/>
                </div>
                </div>
                
            </dd>
            
            </dl>
            
           
            
              
          </div>
          
                   
<script>
  function add_tr(obj) {
    var dd = $(obj).parent().parent();
    dd.after(dd.clone());

  }
  function del_tr(obj) {
    $(obj).parent().parent().remove();
  }
</script>
        </form>
   		<p class="clearfix last_btn">
        	 <label class="con-email120"></label>   	
             <input type="button" value="确认" class="submitred120" > &nbsp;&nbsp;
       <input name="取消" type="reset" class="submitblue120" value="取消"> 
      <!--  <input type="submit" value="导出Html" class="submit9">  -->         
     </p>
	</div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
<!--商家列表弹出层-->
<div id="shangjielist" class="white_content">
	<p class="close"><a href="javascript:void(0)" onclick="document.getElementById('shangjielist').style.display='none';">
×</a></p>
	<h1>商家列表<span><input name="" type="checkbox" value=""> 全选</span></h1>
    <div class="nr">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><input name="" type="checkbox" value=""> 千岁馆西川水合作社</td>
            <td><input name="" type="checkbox" value=""> 千岁馆西产专业合作社</td>
            <td><input name="" type="checkbox" value=""> 千岁馆西川水产专业合作社</td>
          </tr>
          <tr>
            <td><input name="" type="checkbox" value=""> 千岁馆西川产专业合作社</td>
            <td><input name="" type="checkbox" value=""> 千岁馆西川业合作社</td>
            <td><input name="" type="checkbox" value=""> 千岁馆西专业合作社</td>
          </tr>
          <tr>
            <td><input name="" type="checkbox" value=""> 千岁馆西川水产专业合作社</td>
            <td><input name="" type="checkbox" value=""> 千岁馆西川水产专业合作社</td>
            <td><input name="" type="checkbox" value=""> 千岁馆西川水产专业合作社</td>
          </tr>
          <tr>
            <td><input name="" type="checkbox" value=""> 千岁馆西川水产专业合作社</td>
            <td><input name="" type="checkbox" value=""> 千岁馆西川水产专业合作社</td>
            <td><input name="" type="checkbox" value=""> 千岁馆西川水产专业合作社</td>
          </tr>
          <tr>
            <td><input name="" type="checkbox" value=""> 千岁馆西川水产专业合作社</td>
            <td><input name="" type="checkbox" value=""> 千岁馆西川水产专业合作社</td>
            <td><input name="" type="checkbox" value=""> 千岁馆西川水产专业合作社</td>
          </tr>
          <tr>
            <td><input name="" type="checkbox" value=""> 千岁馆西川水产专业合作社</td>
            <td><input name="" type="checkbox" value=""> 千岁馆西川水产专业合作社</td>
            <td><input name="" type="checkbox" value=""> 千岁馆西川水产专业合作社</td>
          </tr>
          <tr>
            <td><input name="" type="checkbox" value=""> 千岁馆西川水产专业合作社</td>
            <td><input name="" type="checkbox" value=""> 千岁馆西川水产专业合作社</td>
            <td><input name="" type="checkbox" value=""> 千岁馆西川水产专业合作社</td>
          </tr>
          <tr>
            <td><input name="" type="checkbox" value=""> 千岁馆西川水产专业合作社</td>
            <td><input name="" type="checkbox" value=""> 千岁馆西川水产专业合作社</td>
            <td><input name="" type="checkbox" value=""> 千岁馆西川水产专业合作社</td>
          </tr>
          <tr>
            <td><input name="" type="checkbox" value=""> 千岁馆西川水产专业合作社</td>
            <td><input name="" type="checkbox" value=""> 千岁馆西川水产专业合作社</td>
            <td><input name="" type="checkbox" value=""> 千岁馆西川水产专业合作社</td>
          </tr>
          <tr>
            <td><input name="" type="checkbox" value=""> 千岁馆西川水产专业合作社</td>
            <td><input name="" type="checkbox" value=""> 千岁馆西川水产专业合作社</td>
            <td><input name="" type="checkbox" value=""> 千岁馆西川水产专业合作社</td>
          </tr>
     	</table>
    </div>
    <p class="clearfix">
        	 <label class="con-email120"></label>   	
             <center><input type="button" value="确认" class="submitred120" > &nbsp;&nbsp;
      		 <input name="取消" type="reset" class="submitblue120" value="取消"> </center>
      <!--  <input type="submit" value="导出Html" class="submit9">  -->         
     </p>
</div>
<!--底部-e-->
</body>
</html>
