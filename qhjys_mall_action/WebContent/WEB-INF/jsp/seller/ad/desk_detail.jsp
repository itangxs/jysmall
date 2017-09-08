<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Pragma" content="no-cache" />   
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<title>飞券网商家后台中心-飞券移动支付智慧收银功能上线</title>
<style>
/*reset*/
body,div,ol,ul,h1,h2,h3,h4,h5,h6,p,th,td,dl,dd,form,iframe,input,textarea,select,label,article,aside,footer,header,menu,nav,section,time,audio,video {
  margin:0;  padding:0;}
body {font-size: 100%; font-family:"微软雅黑",arial,Helvetica,STHeiti,Droid Sans Fallback; -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%;   -webkit-tap-highlight-color:rgba(0,0,0,0); }
.global{width: 1200px; margin:0 auto;}
.imgbox img{ width:100%; height:auto;}
.tel{ background:#fb784b; color:#fff; font-size:20px; text-align:center; padding:10px; margin:45px 0; height:50px; line-height:50px;}
table{ width:100%; margin:0px auto; font-size:16px;}
table td{ width:500px; padding:10px 50px; text-align:center;}
</style>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
<!---------------top--------------------->
<div class="global"> 
	<div class="imgbox">    	
      <img src="/images/seller/shouyintaijs_pc01.jpg">
      <img src="/images/seller/shouyintaijs_pc02.jpg">
      <img src="/images/seller/shouyintaijs_pc03.jpg">
      <img src="/images/seller/shouyintaijs_pc04.jpg">
    </div>
    <table width="90%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>飞券扫码器扫码支付流程</td>
        <td>飞券扫码枪扫码支付流程</td>
      </tr>
      <tr>
        <td><video width="500px" controls="controls">
          <source src="/images/seller/saomaqi.ogg" type="video/ogg">
          <source src="/images/seller/saomaqi.mp4" type="video/mp4">
        您的浏览器不支持视频播放.
        </video></td>
        <td><video width="500px" controls="controls">
          <source src="/images/seller/saomaqiang.ogg" type="video/ogg">
          <source src="/images/seller/saomaqiang.mp4" type="video/mp4">
        您的浏览器不支持视频播放.
        </video></td>
      </tr>
    </table>

    <div class="tel">如需开通收银台请联系客服热线400-6333-088咨询，谢谢！</div> 
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" flush="true" />
<!--底部-e-->
</body>
</html>