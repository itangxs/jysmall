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
<script type="text/javascript" src="/js/system/systask/add_question.js"></script>
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
            <form id="form1" name="form1"  class="zcform1" action="/managermall/systemmall/taskQuestion/saveQuestion.do" method="post">
           <p class="clearfix">
        	<label class="textw100" for="con-email4" >题干内容:</label>
        	<input class="con-email" name="questionTitle" id="questionTitle" value=""/>
        	<input type="hidden" name="taskId" value="${task.id }">
        	<input type="hidden" name="questionNo" value="${questionNo }">
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >形&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;式:</label>
        	<select  name="questionType" id="questionType">
        		<option value="">请选择...</option>
        		<option value="1">文本</option>
        		<option value="2">图片</option>
        		<option value="3">视频</option>
        	</select>
          </p>          
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >题目时长:</label>
        	<input class="con-email3" name="questionTime" id="questionTime"/>&nbsp;&nbsp;秒
          </p>
          <p class="clearfix" id="imageUrl"  style="display: none;">
        	<label class="textw100" for="con-email4" >视频/图片:</label>
     		<div id="upimgdiv" class="textareabox"   style="display: none;">
	        	<input type="text" id="info1" readonly="readonly">
	        	<input type="hidden" id="img2u" name="questionContent" value=""/>
	        	  <input  class="uploadify" type="file" value="上传" id="update1"/>
        	</div>
          </p>  
          <p class="clearfix" id="timuleixing"  style="display: none;">
        	<label class="textw100" for="con-email4" >题目类型:</label>
        	<select  name="answerType" id="answerType">
        		<option value="">请选择...</option>
        		<option value="0">选择</option>
        		<option value="1">填空</option>
        	</select>
          </p> 
          <p class="clearfix" id="answernum" style="display: none;">
        	<label class="textw100" for="con-email4" >可选答案数量:</label>
        	<select  name="maxAnswer" id="maxAnswer">
        		<option value="">请选择...</option>
        		<option value="1">1</option>
        		<option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
        	</select>
          </p>       
           <p class="renwuanniubox2">
             <input type="submit" value="确认" class="renwuanniublue"><input type="reset" value="取消" class="renwuanniublue">          
           </p>   
         <!--  <h3 class="renwutitle">答案区</h3>
          <div class="member_myorder">
             <div class="renwuanniubox3"><input type="submit" value="+增加答案" class="renwuanniublue"></div>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<th class="td80"></th>
                        <th class="td120">类型</th>
						<th class="tdauto" style="padding-left:100px;">答案内容</th>    
	                    <th class="td160">操作</th>
					</tr>
					<tr>
                        <td class="td80">题目1</td>
                        <td class="td120">文本类型</td>
						<td class="tdauto"><input class="con-email" name="taskName" id="taskName" value="请输入"/></td>
						<td class="td80"><a href="">删除</a></td>
					</tr>
                    <tr>
						<td class="td80">题目2</td>
                        <td class="td120">图片类型</td>
						<td class="tdauto"><input class="con-email" name="taskName" id="taskName" value="请选择"/><input name="" type="submit" value="上传" class="renwuanniugray"></td>
						<td class="td80"><a href="">删除</a></td>
					</tr>
                    <tr>
						<td class="td80">题目3</td>
                        <td class="td120">文本类型</td>
						<td class="tdauto"><input class="con-email" name="taskName" id="taskName" value="请输入"/></td>
						<td class="td80"><a href="">删除</a></td>
					</tr>
                    <tr>
						<td class="td80">题目4</td>
                        <td class="td120">文本类型</td>
						<td class="tdauto"><input class="con-email" name="taskName" id="taskName" value="请输入"/></td>
						<td class="td80"><a href="">删除</a></td>
					</tr>		
				</table>
			</div>
                
           <p class="renwuanniubox">
             <input type="submit" value="确认" class="renwuanniublue"><input type="submit" value="取消" class="renwuanniublue">          
           </p> -->
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
