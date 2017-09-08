<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网商家后台中心	-卡券核销</title>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<style>
.td80 img{width:30px;height:30px; margin:0 auto; }
</style>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/seller/cardcoupon/cardcoupon_validate.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
	<div class="membercontent">
		<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="30" /></jsp:include>
		<!--------------右侧------------------>
		<div class="memberright">
        	<h3 style="padding-left:10px; margin-bottom:25px;">卡券核销</h3>

        <form id="from"  class="zcform1" method="post" action="">
      
        
        <div class="renzheng">
        	 <p class="clearfix">
				<input id="page" name="pageNum" type="hidden" value="${page.getPageNum() }">
                	 <label class="one" for="con-email4" >认证编号：</label>
		        	<input id="code" class="con-email4" name="storeName" value="请输入卡券认证编号" >	           
                     <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
					<input  value="查询" class="submit6"  href = "javascript:void(0)" onclick = "ifIdentify()">
            	     
             </p>
           
         	  <div class="image-text">
           			<img src="/images/seller/example01.jpg">
                
                <div class="step">
          		<div class="step01">
                	<h1>搜索卡券</h1>
                    <p>请顾客出示卡券，输入优惠券号或会员卡号</p>
                </div>
                <div class="step02">
                	<h1>校验</h1>
                    <p>微信提供有效期验证，其他信息请自行核对</p>
                </div>
                 <div class="step03">
                	<h1>使用完成</h1>
                    <p>查看使用结果，顾客将同时收到优惠券核销或<br>会员卡使用通知</p>
                </div>
                
                </div>
           	</div>
          </div>
           
         </form>
     
		</div>
		<div class="clear"></div>
	</div>
	<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" />
    
    
  <!--卡券未认证弹窗-->
<div id="kaquan01" class="white_content">
	<p class="close02">提示</p>
    <div class="nr nr03">
    	<div class="kaquan_list">
    		<p style="line-height:40px; font-size:24px">是否认证此卡券编号？
       	 <p>
        </div>

    </div>
    <div class="kqanniu01">
    	<a href = "javascript:void(0)" style=" background-color:#ccc;" onclick = "document.getElementById('kaquan01').style.display='none';document.getElementById('fade').style.display='none'">取消</a>
          <a href = "javascript:identify()" style=" background-color:#ff8b00;" >确认</a>
                                                  
     </div>   
</div>

 <!--卡券已认证弹窗-->
<div id="kaquan02" class="white_content">
	<p class="close02">提示</p>
    <div class="nr nr03">
    	<div class="kaquan_list">
    		<p style="line-height:40px; font-size:24px">此卡券编号已经认证！
       	 <p>
        </div>

    </div>
    <div class="kqanniu01">
    	<a href = "javascript:void(0)" style=" background-color:#ccc;" onclick = "document.getElementById('kaquan02').style.display='none';document.getElementById('fade').style.display='none'">返回</a>
          
                                                  
     </div>   
</div>


<!--弹出幕布-->
<div id="fade" class="black_overlay"></div>
</body>
</html>