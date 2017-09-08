<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网商家后台中心</title>
<link type="text/css" rel="stylesheet" href="/css/uploadify.css" />
<link type="text/css" rel="stylesheet" href="/common/formValidator4.0.1/style/validator.css" />
<link type="text/css" rel="stylesheet" href="/umeditor/themes/default/css/umeditor.css">
<script type="text/javascript" src="/common/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="/common/formValidator4.0.1/js/formValidator-4.0.1.js"></script>
<script type="text/javascript" src="/common/formValidator4.0.1/js/formValidatorRegex.js"></script>
<script type="text/javascript" src="/common/formValidator4.0.1/js/DateTimeMask.js"></script>
<script type="text/javascript" src="/umeditor/umeditor.config.js"></script>
<script type="text/javascript" src="/umeditor/umeditor.js"></script>
<script type="text/javascript" src="/umeditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="/js/seller/commoditymanage/add_page.js"></script>
<script type="text/javascript" src="/js/seller/commoditymanage/add_page_v.js"></script>
<script>window.UMEDITOR_HOME_URL = "/umeditor/"</script>
<style type="text/css">.uploadify {margin-left: 50px;}</style>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
<!---------------top--------------------->
<div class="membercontent">
	<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="9" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
            <h3 style="padding-left:10px;">商品管理——添加商品</h3>
          <div class="member_myorder">   
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr><td style="text-align:right;"><a href="/managermall/seller/commoditymanage/list.do" class="fontred">返回商品列表</a></td></tr>
         </table>
           <table width="100%" border="0" cellspacing="0" cellpadding="0">
           <tr>    
          <th style="text-align:left;background:#fffef5;">
        <form id="signupForm" method="post" action="/managermall/seller/commoditymanage/add.do" class="zcform" style="margin-left:150px;"> 
        <p class="clearfix">
        	<label class="one" for="contactname">商品名称：</label>
        	<input id="contactname" class="con-email" name="spmc" >
        	<span id="contactnameTip"></span>
        </p>
        <p class="clearfix">
        	<c:forEach items="${array}" var="y">
        		<c:if test="${y.id==sx}">
        			<c:if test="${y.id==3}"> <input type="hidden"name="splb" value="2"> </c:if>
            	</c:if>
            </c:forEach>
         </p>
          <p class="clearfix">
        	<label class="one" for="contactname">商品类：</label>
        	<select name="spsx" id="spsx" >
        		<option value="">------请选择类别-----</option> 
        		<c:forEach items="${array}" var="y">
	              <%--  <option disabled="disabled">${y.text}</option> --%>
	              <c:if test="${y.id==sx}">
	                <c:forEach items="${y.childs}" var="e">
	                	<option value="${e.id}">${e.text}</option>
	                </c:forEach>
	                </c:if>
                </c:forEach>
            </select>
              <span id="spsxTip"></span>
         </p>
          <p class="clearfix">
        	<label class="one" for="contactname">商品描述：</label>
        	<input id="spms" class="con-email" name="spms" >
        	<span id="spmsTip"></span>
         </p>
         <p class="clearfix">
        	<label class="one" for="contactname">服务承诺：</label>
        	<!-- 
        	<label class="one5"><input name="fwcr" type="checkbox" value="1 " style="margin-top:10px;"/>随时退换</label>
            <label class="one5"><input name="fwcr" type="checkbox" value="2"  style="margin-top:10px;"/>过期退换</label>
             -->
            <label class="one5"><input name="fwcr" type="checkbox" value="3"  style="margin-top:10px;"/>支持代金卷</label>
            <label class="one5"><input name="fwcr" type="checkbox" value="4"  style="margin-top:10px;"/>接受预定</label>
         </p>
               
         <p class="clearfix">
        	<label class="one" for="con-telphone">开始日期：</label>
        	<input id="kssj" name="kssj"   type="text" readonly="readonly"  />
        	<img onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,el:'kssj'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"> 
          	<span id="kssjTip"></span>
         </p>
        <p class="clearfix">
        	<label class="one" for="con-email">结束日期：</label>
        	<input id="jssj" name="jssj"   type="text" readonly="readonly"  />
        	<img onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,el:'jssj'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"> 
           	<span id="jssjTip"></span>
         </p>
        <p class="clearfix">
        	<label class="one" for="con-email">下架日期：</label>
        	<input id="xjsj" name="xjsj" type="text" readonly="readonly"  />
        	<img onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,el:'xjsj'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"> 
           	<span id="xjsjTip"></span>
         </p>
         <p class="clearfix">
         	<label class="one" for="con-email3">市场价：</label>
         	<input id="scj" name="scj" class="con-email3" value="${p.origPrice}"> 元
         	<span id="scjTip"></span>
         </p>
         <p class="clearfix">
         	<label class="one" for="con-email3">折扣价：</label>
         	<input id="unit" name="unit" class="con-email3" value="${p.unitPrice}"> 元
         	<span id="unitTip"></span>
         </p>
          <p class="clearfix">
        	<label class="one" for="con-email3">可售数量：</label>
        	<input id="kssl" name="kssl" class="con-email3" >个
        	<span id="ksslTip"></span>
         </p>
          <p class="clearfix">
        	<label class="one" for="con-email3">每人限购：</label>
        	<input id="mrxg" name="mrxg" class="con-email3" >个
        	<span id="mrxgTip"></span>
         </p>
         <p class="clearfix">
        	<label class="one" for="con-email">商品图片：</label>
        	<input type="hidden" id="sptp" name="sptp"/>
     		<div style="position:relative;display: inline-block;">
	        	<img  id="img1"  src="" width="150" height="120" alt="" />
	        	<input type="hidden" id="img1u" name="imgs" value=" "/>
	        	  <input  class="uploadify" type="file" value="上传" id="update1"/></p> 
        	</div>
        	<div style="position:relative;display: inline-block;">
	        	<img  id="img2"  src="" width="150" height="120" alt="" />
	        	<input type="hidden" id="img2u" name="imgs" value=" "/>
	        	 <input  class="uploadify" type="file" value="上传" id="update2"/></p> 
        	</div>
        	<div style="position:relative;display: inline-block;">
	        	<img  id="img3"  src="" width="150" height="120" alt="" />
	        	<input type="hidden" id="img3u" name="imgs" value=" " />
	        	<input  class="uploadify" type="file" value="上传" id="update3"/></p>
        	</div>
        	<div style="position:relative;display: inline-block;">
		       	<img  id="img4"  src="" width="150" height="120" alt="" />
		       	<input type="hidden" id="img4u" name="imgs" value=" " />
		       	 <input  class="uploadify" type="file" value="上传" id="update4"/></p> 
        	</div>
            <p class="clearfix"></p>
            <label class="one" for="con-email"></label>
            <span id="sptpTip"></span>
           	<p class="clearfix">
       			<label class="one" for="con-email" >购买须知：</label>
       	    </p>
       	    <p class="clearfix">
				<textarea id="gmxz1" style="display: none;"></textarea>
				<script type="text/plain" id="gmxz" name="gmxz"></script>
				<script type="text/javascript">
					//实例化编辑器
					var um = UM.getEditor('gmxz',{initialFrameHeight : 329,initialFrameWidth : 533,autoHeightEnabled : false});
				</script>
			</p>
             <p class="clearfix">
        		<label class="one" for="con-email">产品详情：</label>
        	 </p>
        	 <p class="clearfix">
        	 	<textarea id="cpxq1" style="display: none;"></textarea>
				<script type="text/plain" id="cpxq" name="cpxq"></script>
				<script type="text/javascript">
					//实例化编辑器
					var um = UM.getEditor('cpxq',{initialFrameHeight : 329,initialFrameWidth : 533,autoHeightEnabled : false});
				</script>
			</p>
            <br />
       	<p class="clearfix">
         <label class="one" for="con-email"></label>
        <input class="submit10" type="submit" value="提交"/></p>
        <input type="hidden" name="token" value="${token}" />   
    </form>
         </th></tr>
          </table>
         </div>
    	</div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" flush="true" />
<!--底部-e-->
</body>
</html>
