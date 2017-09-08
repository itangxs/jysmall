<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="/css/uploadify.css" />
<link type="text/css" rel="stylesheet" href="/umeditor/themes/default/css/umeditor.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="/js/system/task/add_task.js"></script>
<script type="text/javascript" src="/umeditor/umeditor.config.js"></script>
<script type="text/javascript" src="/umeditor/umeditor.js"></script>
<script type="text/javascript" src="/umeditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js "></script>
<script>window.UMEDITOR_HOME_URL = "/umeditor/"</script>
<link type="text/css" rel="stylesheet" href="/common/formValidator4.0.1/style/validator.css"></link>
<script src="/common/formValidator4.0.1/js/formValidator-4.0.1.js" type="text/javascript" charset="UTF-8"></script>
<script src="/common/formValidator4.0.1/js/formValidatorRegex.js" charset="UTF-8"></script>
<script type="text/javascript">
$(document).ready(function() {
	 $.formValidator.initConfig({formID:"form1",autoTip:true,onError:function(msg){alert(msg);},inIframe:true});
	 $("#step").formValidator({onShow:"",onFocus:"输入中...",onCorrect:""}).regexValidator({regExp:"intege1",dataType:"enum",onError:"步骤格式不正确"});

});

</script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>

</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="12" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/task/uLeft.jsp" flush="true" ><jsp:param name="val" value="2" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
            <h3 style="padding-left:10px; margin-bottom:25px;">添加说明</h3>  
            <form id="form1" name="form1"  class="zcform1" action="/managermall/systemmall/task/saveTaskExpand.do" method="post">
           
          <p class="clearfix">
        	<label class="one" for="con-email4" >步&nbsp;&nbsp;	骤   &nbsp;&nbsp;:</label>
        	<input class="con-email4" name="step" id="step"/>(请填写数字,比如1 2 3 ..)
          </p>
          
          <p class="clearfix">
        	<label class="one" for="con-email4" >奖励要求:</label>
        	<textarea class="con-email4" name="requirements" id="requirements" cols="70" rows="4"></textarea>
          </p>
          
          <p class="clearfix">
        	<label class="one" for="con-email4" >奖励元宝:</label>
        	<input class="con-email4" name="prize" id="prize"/>
          </p>
          	<input type="hidden" name="taskId" value="${taskId}">
             <input type="submit" value="增加" class="submit10">          
        </form>
	</div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/uFooter.jsp" flush="true" />
<!--底部-e-->
</body>
</html>
