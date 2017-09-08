<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="/css/uploadify.css" />
<link type="text/css" rel="stylesheet" href="/umeditor/themes/default/css/umeditor.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="/js/system/systask/add_answer.js"></script>
<script type="text/javascript" src="/umeditor/umeditor.config.js"></script>
<script type="text/javascript" src="/umeditor/umeditor.js"></script>
<script type="text/javascript" src="/umeditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js "></script>
<script>window.UMEDITOR_HOME_URL = "/umeditor/"</script>
<link type="text/css" rel="stylesheet" href="/css/system/renwu.css">

</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="12" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/task/uLeft.jsp" flush="true" ><jsp:param name="val" value="3" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
            <h3 class="renwutitle">答案编辑</h3>
            <form id="form1" name="form1"  class="zcform1" action="/managermall/systemmall/taskAnswer/saveAnswer.do" method="post">
            <input type="hidden" name="qyestionId" id="qyestionId" value="${questionId }"/>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型:</label>
        	<select  name="answerType" id="answerType">
        		<option value="">请选择</option>
        		<option value="0">文本</option>
        		<option value="1">图片</option>
        	</select>
          </p>          
          <p class="clearfix" id="answerC"  style="display: none;">
        	<label class="textw100" for="con-email4" >答案:</label>
        	<input class="con-email" name="answerContent" id="answerContent"/>
          </p>
          <p class="clearfix" id="imageUrl"  style="display: none;">
        	<label class="textw100" for="con-email4" >图片:</label>
     		<div id="upimgdiv" class="textareabox"   style="display: none;">
	        	<input type="text" id="info1" readonly="readonly">
	        	<input type="hidden" id="img2u" name="image" value=""/>
	        	  <input  class="uploadify" type="file" value="上传" id="update1"/>
        	</div>
          </p>  
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >是否正确:</label>
        	<select  name="isCorrect" id="isCorrect">
        		<option value="0">正确</option>
        		<option value="1">不正确</option>
        	</select>
          </p>     
           <p class="renwuanniubox2">
             <input type="submit" value="确认" class="renwuanniublue"><input type="reset" value="取消" class="renwuanniublue">          
           </p>   
        
        </form>
	</div>
	</div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/uFooter.jsp" flush="true" />
<!--底部-e-->
</body>
</html>
