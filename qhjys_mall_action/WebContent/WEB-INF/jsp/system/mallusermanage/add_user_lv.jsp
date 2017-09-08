<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="/WEB-INF/jsp/public_header.jsp" flush="true"/>

<link rel="stylesheet" type="text/css" href="/css/uploadify.css" />
<script src="/common/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="/js/system/mallusermanage/add_user_lv.js"></script>
<link type="text/css" rel="stylesheet" href="/common/formValidator4.0.1/style/validator.css"></link>
<script src="/common/formValidator4.0.1/js/formValidator-4.0.1.js" type="text/javascript" charset="UTF-8"></script>
<script src="/common/formValidator4.0.1/js/formValidatorRegex.js" charset="UTF-8"></script>
<script language="javascript" src="/common/formValidator4.0.1/js/DateTimeMask.js" type="text/javascript"></script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>

</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp" flush="true" ><jsp:param name="val" value="4" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/mallusermanage/uLeft.jsp" flush="true" />
    <!--------------右侧------------------>
	<div class="memberright">
     <h3 style="padding-left:10px; margin-bottom:25px;">会员管理——添加会员等级</h3>
          <div class="member_myorder">
          <form action="/managermall/systemmall/malluser/addUserLv.do"  id="signupForm" method="post">
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th class="td160">等级名称</th>
                    <th class="td160">等级图片</th>
                    <th class="td160">最小积分</th>
                    <th class="td160">最大积分</th>  
                   <!--  <th class="td160">是否默认</th> -->
                </tr>
                <tr>
                    </td>
                    <td class="td160">
                    	<input name="name" id="name"><span id="nameTip"></span>
                    </td>
                     <td class="td160">
                    	<img  id="img1"  src="" width="150" height="120" alt="" />
			        	<input type="hidden" id="img1u" name="imgs" />
			        	<input  class="uploadify" type="file" value="上传" id="update1"/></p> 
			        	<span id="imgsTip"></span>
                    </td>
                    
                     <td class="td160">
                    	<input name="min" id="min">
                    	<span id="minTip"></span>
                    </td>
                    
                     <td class="td160">
                    	<input name="max" id="min">
                    	<span id="minTip"></span>
                    </td>
                    
                     <!-- <td class="td160">
                    	<select name="status" id="status">
                    		<option value="0">否</option>
                    		<option value="1">是</option>
                    	</select>
                    	<span id="statusTip"></span>
                    </td> -->
                   
                </tr>  
              </table>
               <div class="td160">
                  	  <input type="submit" value="提交" class="submit8">
                  	  <input type="hidden" name="token" value="${addLvToken}">
                    </div> 
             </form>
            </div>
          
	</div>
	<div class="clear"></div>
</div>

<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
<!--底部-e-->
</body>
</html>
