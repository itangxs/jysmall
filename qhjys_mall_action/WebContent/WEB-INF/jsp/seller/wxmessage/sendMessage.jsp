<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="/css/uploadify.css" />
<link type="text/css" rel="stylesheet" href="/umeditor/themes/default/css/umeditor.css">
<link type="text/css" rel="stylesheet" href="/common/formValidator4.0.1/style/validator.css">
<script src="/common/formValidator4.0.1/js/formValidator-4.0.1.js" type="text/javascript" charset="UTF-8"></script>
<script src="/common/formValidator4.0.1/js/formValidatorRegex.js" charset="UTF-8"></script>
<script language="javascript" src="/common/formValidator4.0.1/js/DateTimeMask.js" type="text/javascript"></script>
<script src="/common/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="/umeditor/umeditor.config.js"></script>
<script type="text/javascript" src="/umeditor/umeditor.js"></script>
<script type="text/javascript" src="/umeditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="/js/seller/sendmessage2.js"></script>
<script>
	window.UMEDITOR_HOME_URL = "/umeditor/"
</script>
<title>飞券网商家后台中心</title>
</head>
<script>
</script>
<body>
<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
<!---------------top--------------------->
<div class="membercontent">
	<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="34" /></jsp:include>
	<!--------------右侧------------------>
	<div class="memberright">
	<h3 style=" padding-bottom:20px;">群发图文信息</h3>
    <div class="push-nav">
       		<p class="nav01 img-text01" href="">图文消息</p>
            <p class="nav02 txt" href="">文字</p>
            <p class="nav03 img" href="img">图片</p>
            
            <span style="text-align:center;line-height:32px; font-size:14px; margin-left:60px">本月还剩<span class="textyellow">${storeinfo.messageNum }次</span>群发机会</span>
               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  本店共有<span class="textyellow">${openidnum }</span>名会员
    </div>
		<div class="member_myorder">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<th style="text-align: left; background: #fffef5; border:none;">
							<form id="form1" name="form1" method="post">
                            
                            <!--图文消息-->
                           <div class="img-text-content">
                           	    <div class="img-text-left message">
									<p class="cover-title">
									<label class="one" for="con-email">封面:<span>900像素*500像素</span></label>
                                    <input type="hidden" id="sptp" name="sptp" />
                                    </p>
									<div style="position: relative;" class="img-show">
										<img id="img1" src="" width="190" height="106" alt="" /> 
										<input type="hidden" id="img1u" name="fileName" value="" />
                                        <input class="uploadify" type="file" value="上传" id="update1" />
									</div>
                          
									<p class="clearfix">
                                		<input type="text" value="标题" id="title"  class="cover-title02">
                                	</p>
                            	  </div>                              
                            	<div class="img-text-left">
										<p class="cover-title01">
											<label class="one" for="cpxx">内容：<span><a target="_blank" href="http://edit.newrank.cn/">排版工具</a>编辑完成后，复制粘贴至下方文档</span></label>
										</p>
										 <p class="clearfix">
										 <script type="text/plain" id="content" name="content"></script>
										 <script type="text/javascript">
											//实例化编辑器
											var um = UM.getEditor('content', {
												initialFrameHeight : 350,
												initialFrameWidth : 400,
												autoHeightEnabled : false,
												toolbar:[
											            'source | undo redo | bold italic underline strikethrough | superscript subscript | forecolor backcolor | removeformat |',
											            'insertorderedlist insertunorderedlist | selectall cleardoc paragraph | fontfamily fontsize' ,
											            '| justifyleft justifycenter justifyright justifyjustify |',
											            'link unlink | image '
											            
											        ]
										});
											</script>
										</p>
										<p class="clearfix" style=" margin-left:67px">
											<label class="one" for="con-email"></label> 
											<input onclick="yulanshuju()" class="submit106" type="button" value="预览" />
											<!-- <input onclick="document.getElementById('tjshts').style.display='block';document.getElementById('fade03').style.display='block'" class="submit106" type="button" value="提交审核" /> -->
											<input onclick="submshuju()" class="submit106" type="button" value="提交审核" />
										</p>
                            		</div>
								<div id="word-weixin">
									<p>内容示例：</p>
									<img src="/images/seller/weixin-message.jpg">
								</div>
                            </div>
                            
                            <!--文本-->
                            <div class="txt-content" style="display:none;">
                            	<textarea id="contenttext" style="width:446px; height:246px; max-width:548px; max-height:260px;border:1px #d4d4d4 solid;margin-top:25px;"></textarea>
                            	<p class="clearfix" style=" text-align:right; margin-top:12px">
									<label class="one" for="con-email"></label>
                                    <input onclick="document.getElementById('super-link').style.display='block';" class="submitgray106" type="button" value="插入超链接" /> 
									<input onclick="submshuju1()" class="submit106" type="button" value="提交审核" />
								</p>
                                <!--插入超链接弹窗-->
								<div id="super-link" class="wx_white_content" >
									<p class="close">插入超链接
        							<a href="javascript:void(0)" class="wx-close-btn" onclick="document.getElementById('super-link').style.display='none';"><img src="/images/seller/wx-close-btn.png"></a>
        						    </p>
									<div class="nr06">
										<p>文本：<input type="text" id="hreftext" value=""></p>
            							<p>链接：<input type="text" id="hrefurl" value=""></p>
            							<input onclick="addhref()" class="submityellow106" type="button" value="插入" />
									</div>
								</div>
								<div id="word-weixin1">
									<p>内容示例：</p>
									<img src="/images/seller/weixin-word.jpg">
								</div>
                            </div>
                            
                            <!--图片-->
                            <div class="img-content" style="position:relative; margin-top: 25px;">
                        		<div style="position: relative; display:inline-block" class="img-show02">
										<img id="img2" src="" width="452" height="251" alt="" /> 
										<input type="hidden" id="img2u" name="fileName" value="" />
                                        <input class="uploadify" type="file" value="上传" id="update2" />
        								<p class="clearfix" style=" text-align:center; margin-top:12px">
											<label class="one" for="con-email"></label>
											<input onclick="submshuju2()" class="submit106" type="button" value="提交审核" />
										</p>

									</div>
									<div id="word-weixin2">
										<p>内容示例：</p>
										<img src="/images/seller/weixin-pic.jpg">
									</div>
                            </div>
						</form>
					</th>
				</tr>
			</table>
		</div>
	</div>
	<div class="clear"></div>
</div>

 
<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" flush="true" />

   <script type="text/javascript">
		$(".nav01").click(
			function(){
				$(".img-text-content").show();
				$(".txt-content").hide();
				$(".img-content").hide();
				$(".nav03").removeClass("img01")
				$(".nav02").removeClass("txt01")
				$(".nav02").addClass("txt")
				$(".nav03").addClass("img")
				$(".nav01").addClass("img-text01")
				
			}
		)
	
		$(".nav02").click(
			function(){
				$(".txt-content").show();
				$(".img-text-content").hide();
				$(".img-content").hide();
				$(".nav01").removeClass("img-text01")
				$(".nav03").removeClass("img01")
				$(".nav01").addClass("img-text")
				$(".nav02").addClass("txt01")
				$(".txt-content").css("display","inline-block");
				
			}
		)
		
		$(".nav03").click(
			function(){
				$(".img-content").show();
				$(".img-text-content").hide();
				$(".txt-content").hide();
				$(".nav01").removeClass("img-text01")
				$(".nav02").removeClass("txt01")
				$(".nav01").addClass("img-text")
				$(".nav02").addClass("txt")
				$(".nav03").addClass("img01")
				
			}
		)
		
	</script>
<!--群发剩余次数提示弹窗-->
	<div id="qfcsts" class="white_content" style=" display:block;">
		<p class="close02">提示</p>
		<div class="nr nr03">
			<div class="kaquan_list">
				<p style="line-height:40px; font-size:24px">本月还剩<span class="textyellow">${storeinfo.messageNum }次</span>群发机会！</p>
			</div>
		</div>
		<div class="kqanniu01">
		<c:if test="${storeinfo.messageNum <1}">
		<a href="/managermall/seller/wxmessage/list.do" style="background-color: #ff8b00;">我知道了</a>
		</c:if>
		<c:if test="${storeinfo.messageNum >0}">
			<a href="javascript:void(0)" style="background-color: #ff8b00;"
				onclick="document.getElementById('qfcsts').style.display='none';document.getElementById('fade03').style.display='none';">我知道了</a></c:if>
		</div>
	</div>
    
      <!--提交审核提示弹窗-->
	<div id="tjshts" class="white_content">
		<p class="close02">提示</p>
		<div class="nr nr03">
			<div class="kaquan_list">
				<p style="line-height:40px; font-size:24px">确定发送后大约<span class="textyellow">2分钟内</span>发送至用户！</p>
			</div>
		</div>
		<div class="kqanniu01">
        	<a href="javascript:void(0)" style="background-color: #ccc;"
				onclick="document.getElementById('tjshts').style.display='none';document.getElementById('fade03').style.display='none'">取消</a>
			<a href="javascript:void(0)" style="background-color: #ff8b00;">确认</a>
		</div>
	</div>
    
<!--弹出幕布-->
	<div id="fade03" class="black_overlay03"></div>
</body>
</html>
