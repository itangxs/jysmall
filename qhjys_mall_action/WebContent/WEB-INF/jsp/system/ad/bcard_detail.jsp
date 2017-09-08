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
<script type="text/javascript" src="/js/system/ad/bcard_detail.js"></script>
</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp" flush="true" ><jsp:param name="val" value="11" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/message/uLeft.jsp" flush="true" ><jsp:param name="position" value="" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
        <h3 style="padding-left:0px; margin-bottom:25px;">B卡券详情</h3>
             <p class="clearfix">
            <label class="con-email120" for="con-email4" >商家ID</label>${bcard.storeId}（${bcard.storeName }）
        	 </p>
            <p class="clearfix" style="margin-top:8px;">
        	<label class="con-email120" for="con-email4" >活动周期</label>       
        	<fmt:formatDate value="${bcard.beginDate}" pattern="yyyy-MM-dd"/>至
        	<fmt:formatDate value="${bcard.endDate}" pattern="yyyy-MM-dd"/>
         </p>
             <div class="kq_miaoshubox">
            	<label class="con-email120" for="con-email4" >商家描述</label>
            	<div class="kq_miaoshu_info">
            		${bcard.bcardInfo}
            	</div>
        	 </div>
             <p class="clearfix"><label class="con-email120" for="con-email4" ></label>
             <b class="fontred" style="font-size:16px;">单一微信账号每天可推送次数为${bcard.pushNum }次</b></p>
             <div class="title_bggray">商家自荐卡券</div>
             <!--卡券1-->
             <c:forEach items="${prizes }" var="prize" varStatus="status">
             <div class="kaquan_zijianbox">
                 <div class="kaquantitle">卡券${status.count}</div>
                 <p class="clearfix"><label class="con-email120" for="con-email4" >名称</label>${prize.prizeName }</p>
                 <p class="clearfix"><label class="con-email120" for="con-email4" >使用规则</label>消费满 <b>${prize.prizeLine }</b> 元即可使用</p>
                 <div class="kq_miaoshubox">
                    <label class="con-email120" for="con-email4" >描述</label>
                    <div class="kq_miaoshu_info">${prize.prizeInfo }</div>
                 </div>
                 <p class="clearfix">
                    <label class="con-email120" for="con-email4" ></label>
                    <img class="form_img1"  src="${prize.prizeImage }" alt="图片" />
                 </p>
             </div>
             </c:forEach>
             <div class="title_bggray">商家被荐卡券</div>
             <p class="clearfix">
             <div class="guizetable">
             	<table width="100%" border="0" cellspacing="0" cellpadding="0">
             	<c:forEach items="${ruleNames }" var="rule" varStatus="status">
                  <tr>
                    <td><b>规则${status.count }</b></td>
                    <td>${rule.cityName }</td>
                    <td>${rule.districtName }</td>
                    <td>${rule.areaName }</td>
                    <td>${rule.industryName }</td>
                    <td>${rule.industryDetailName }</td>
                  </tr>
                  </c:forEach>
                </table>
              </div>
        	 </p>
        <!--活动展现人数-->
        <div class="hongbaodetail">
        	<div class="hbtop"><span class="title">活动展现报表</span>活动展现人数：5300个&nbsp;&nbsp;&nbsp;&nbsp;<input name="button" type="button" class="submitblue120 rightanniu" value="导出至EXCEL"></div>
            <div class="hongbaotablist">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0"  class="hongbaotable" style="height:100px; overflow:auto;">
              <tr>
                <th>昵称</th>
                <th>头像</th>
                <th>卡券名称</th>
                <th>展现时间</th>
              </tr>
              <tr>
                <td>wary</td>
                <td><img class="img1"  src="" alt="图片" /></td>
                <td>三等奖</td>
                <td>016年9月15日  15：23</td>
              </tr>
              <tr>
                <td>wary</td>
                <td><img class="img1" src="" alt="图片" /></td>
                <td>三等奖</td>
                <td>016年9月15日  15：23</td>
              </tr>
              <tr>
                <td>wary</td>
                <td><img class="img1"  src="" alt="图片" /></td>
                <td>三等奖</td>
                <td>016年9月15日  15：23</td>
              </tr>
              <tr>
                <td>wary</td>
                <td><img class="img1" src="" alt="图片" /></td>
                <td>三等奖</td>
                <td>016年9月15日  15：23</td>
              </tr>
              <tr>
                <td>wary</td>
                <td><img class="img1"  src="" alt="图片" /></td>
                <td>三等奖</td>
                <td>016年9月15日  15：23</td>
              </tr>
              <tr>
                <td>wary</td>
                <td><img class="img1" src="" alt="图片" /></td>
                <td>三等奖</td>
                <td>016年9月15日  15：23</td>
              </tr>
            </table>
            </div>   
        </div>
        <!--活动抽奖参与人数-->
        <div class="hongbaodetail">
        	<div class="hbtop"><span class="title">活动抽奖报表</span>活动抽奖参与人数：5300个&nbsp;&nbsp;&nbsp;&nbsp;<input name="button" type="button" class="submitblue120 rightanniu" value="导出至EXCEL"></div>
            <div class="hongbaotablist">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0"  class="hongbaotable" style="height:100px; overflow:auto;">
              <tr>
                <th>昵称</th>
                <th>头像</th>
                <th>卡券名称</th>
                <th>抽奖时间</th>
              </tr>
              <tr>
                <td>wary</td>
                <td><img class="img1"  src="" alt="图片" /></td>
                <td>三等奖</td>
                <td>016年9月15日  15：23</td>
              </tr>
              <tr>
                <td>wary</td>
                <td><img class="img1" src="" alt="图片" /></td>
                <td>三等奖</td>
                <td>016年9月15日  15：23</td>
              </tr>
              <tr>
                <td>wary</td>
                <td><img class="img1"  src="" alt="图片" /></td>
                <td>三等奖</td>
                <td>016年9月15日  15：23</td>
              </tr>
              <tr>
                <td>wary</td>
                <td><img class="img1" src="" alt="图片" /></td>
                <td>三等奖</td>
                <td>016年9月15日  15：23</td>
              </tr>
              <tr>
                <td>wary</td>
                <td><img class="img1"  src="" alt="图片" /></td>
                <td>三等奖</td>
                <td>016年9月15日  15：23</td>
              </tr>
              <tr>
                <td>wary</td>
                <td><img class="img1" src="" alt="图片" /></td>
                <td>三等奖</td>
                <td>016年9月15日  15：23</td>
              </tr>
            </table>
            </div>
        </div>
        <!--活动转发人数-->
        <div class="hongbaodetail">
        	<div class="hbtop"><span class="title">活动转发报表</span>活动转发人数：5300个&nbsp;&nbsp;&nbsp;&nbsp;<input name="button" type="button" class="submitblue120 rightanniu" value="导出至EXCEL"></div>
            <div class="hongbaotablist">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0"  class="hongbaotable" style="height:100px; overflow:auto;">
              <tr>
                <th>昵称</th>
                <th>头像</th>
                <th>卡券名称</th>
                <th>转发时间</th>
              </tr>
              <tr>
                <td>wary</td>
                <td><img class="img1"  src="" alt="图片" /></td>
                <td>三等奖</td>
                <td>016年9月15日  15：23</td>
              </tr>
              <tr>
                <td>wary</td>
                <td><img class="img1" src="" alt="图片" /></td>
                <td>三等奖</td>
                <td>016年9月15日  15：23</td>
              </tr>
              <tr>
                <td>wary</td>
                <td><img class="img1"  src="" alt="图片" /></td>
                <td>三等奖</td>
                <td>016年9月15日  15：23</td>
              </tr>
              <tr>
                <td>wary</td>
                <td><img class="img1" src="" alt="图片" /></td>
                <td>三等奖</td>
                <td>016年9月15日  15：23</td>
              </tr>
              <tr>
                <td>wary</td>
                <td><img class="img1"  src="" alt="图片" /></td>
                <td>三等奖</td>
                <td>016年9月15日  15：23</td>
              </tr>
              <tr>
                <td>wary</td>
                <td><img class="img1" src="" alt="图片" /></td>
                <td>三等奖</td>
                <td>016年9月15日  15：23</td>
              </tr>
            </table>
            </div>
        </div>
        <!--奖券领取人数-->
        <div class="hongbaodetail">
        	<div class="hbtop"><span class="title">奖券领取报表</span>奖券领取人数：5300个&nbsp;&nbsp;&nbsp;&nbsp;<input name="button" type="button" class="submitblue120 rightanniu" value="导出至EXCEL"></div>
            <div class="hongbaotablist">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0"  class="hongbaotable" style="height:100px; overflow:auto;">
              <tr>
                <th>昵称</th>
                <th>头像</th>
                <th>卡券名称</th>
                <th>领取时间</th>
              </tr>
              <tr>
                <td>wary</td>
                <td><img class="img1"  src="" alt="图片" /></td>
                <td>三等奖</td>
                <td>016年9月15日  15：23</td>
              </tr>
              <tr>
                <td>wary</td>
                <td><img class="img1" src="" alt="图片" /></td>
                <td>三等奖</td>
                <td>016年9月15日  15：23</td>
              </tr>
              <tr>
                <td>wary</td>
                <td><img class="img1"  src="" alt="图片" /></td>
                <td>三等奖</td>
                <td>016年9月15日  15：23</td>
              </tr>
              <tr>
                <td>wary</td>
                <td><img class="img1" src="" alt="图片" /></td>
                <td>三等奖</td>
                <td>016年9月15日  15：23</td>
              </tr>
              <tr>
                <td>wary</td>
                <td><img class="img1"  src="" alt="图片" /></td>
                <td>三等奖</td>
                <td>016年9月15日  15：23</td>
              </tr>
              <tr>
                <td>wary</td>
                <td><img class="img1" src="" alt="图片" /></td>
                <td>三等奖</td>
                <td>016年9月15日  15：23</td>
              </tr>
            </table>
            </div>
        </div>
        <!--奖券核销人数-->
        <div class="hongbaodetail">
        	<div class="hbtop"><span class="title">奖券核销报表</span>奖券核销人数：5300个&nbsp;&nbsp;&nbsp;&nbsp;<input name="button" type="button" class="submitblue120 rightanniu" value="导出至EXCEL"></div>
            <div class="hongbaotablist">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0"  class="hongbaotable" style="height:100px; overflow:auto;">
              <tr>
                <th>昵称</th>
                <th>头像</th>
                <th>卡券名称</th>
                <th>核销时间</th>
              </tr>
              <tr>
                <td>wary</td>
                <td><img class="img1"  src="" alt="图片" /></td>
                <td>三等奖</td>
                <td>016年9月15日  15：23</td>
              </tr>
              <tr>
                <td>wary</td>
                <td><img class="img1" src="" alt="图片" /></td>
                <td>三等奖</td>
                <td>016年9月15日  15：23</td>
              </tr>
              <tr>
                <td>wary</td>
                <td><img class="img1"  src="" alt="图片" /></td>
                <td>三等奖</td>
                <td>016年9月15日  15：23</td>
              </tr>
              <tr>
                <td>wary</td>
                <td><img class="img1" src="" alt="图片" /></td>
                <td>三等奖</td>
                <td>016年9月15日  15：23</td>
              </tr>
              <tr>
                <td>wary</td>
                <td><img class="img1"  src="" alt="图片" /></td>
                <td>三等奖</td>
                <td>016年9月15日  15：23</td>
              </tr>
              <tr>
                <td>wary</td>
                <td><img class="img1" src="" alt="图片" /></td>
                <td>三等奖</td>
                <td>016年9月15日  15：23</td>
              </tr>
            </table>
            </div>
        </div>
        <!--奖券到期人数-->
        <div class="hongbaodetail">
        	<div class="hbtop"><span class="title">奖券到期报表</span>奖券到期人数：5300个&nbsp;&nbsp;&nbsp;&nbsp;<input name="button" type="button" class="submitblue120 rightanniu" value="导出至EXCEL"></div>
            <div class="hongbaotablist">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0"  class="hongbaotable" style="height:100px; overflow:auto;">
              <tr>
                <th>昵称</th>
                <th>头像</th>
                <th>卡券名称</th>
                <th>到期时间</th>
              </tr>
              <tr>
                <td>wary</td>
                <td><img class="img1"  src="" alt="图片" /></td>
                <td>三等奖</td>
                <td>016年9月15日  15：23</td>
              </tr>
              <tr>
                <td>wary</td>
                <td><img class="img1" src="" alt="图片" /></td>
                <td>三等奖</td>
                <td>016年9月15日  15：23</td>
              </tr>
              <tr>
                <td>wary</td>
                <td><img class="img1"  src="" alt="图片" /></td>
                <td>三等奖</td>
                <td>016年9月15日  15：23</td>
              </tr>
              <tr>
                <td>wary</td>
                <td><img class="img1" src="" alt="图片" /></td>
                <td>三等奖</td>
                <td>016年9月15日  15：23</td>
              </tr>
              <tr>
                <td>wary</td>
                <td><img class="img1"  src="" alt="图片" /></td>
                <td>三等奖</td>
                <td>016年9月15日  15：23</td>
              </tr>
              <tr>
                <td>wary</td>
                <td><img class="img1" src="" alt="图片" /></td>
                <td>三等奖</td>
                <td>016年9月15日  15：23</td>
              </tr>
            </table>
            </div>
        </div>
   		<p class="clearfix">
        	 <label class="con-email120"></label>
             <center><input type="button" value="修改" class="submitred120" onclick="xiugai('${bcard.id}')"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       		<input name="取消" type="button" class="submitblue120" value="取消" onclick="quxiao()"></center>      
     </p>
	</div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
<!--底部-e-->
</body>
</html>
