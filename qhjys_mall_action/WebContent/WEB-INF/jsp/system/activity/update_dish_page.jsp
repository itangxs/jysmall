<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<link href="/qhjys_mall/seller-manage/css/membernew.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/system/activity/disp_list.js"></script>
<script type="text/javascript" src="/js/system/activity/list.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js "></script>
<link type="text/css" rel="stylesheet" href="/css/uploadify.css" />
<script type="text/javascript" src="/js/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="/umeditor/umeditor.config.js"></script>
<script type="text/javascript" src="/umeditor/umeditor.js"></script>
<script type="text/javascript" src="/umeditor/lang/zh-cn/zh-cn.js"></script>



<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>

</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="2" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/product/uLeft.jsp" flush="true" ><jsp:param name="val" value="6" /></jsp:include>
    <!--------------右侧------------------>
	<!--------------右侧------------------>
	<div class="memberright">
     <h3 style="padding-left:10px; margin-bottom:25px;">活动管理——修改菜品</h3> 
          <div class="member_myorder">
          	<form id="addform" action="/managermall/systemmall/activity/update_dish.do" method="post">
                	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td class="td100">活动ID：</td>
                        <td>
			                   <select name="lotteryId">
			                   			<c:forEach items="${lotterys}" var="store">
			                   			
			                   			
			                   				<option value="${store.id}" <c:if test="${dish.lotteryId==store.id }">selected="selected"</c:if>>${store.lotteryName}</option>
			                   			</c:forEach>
			                   </select>	     
                        </td>
                      </tr>
                      <tr>
                        <td class="td100">奖项等级：</td>
                        <td>
                        	<select name="rankLevel">
			                   				<option value="1" <c:if test="${dish.rankLevel==1 }">selected="selected"</c:if>>1</option>
			                   				<option value="2" <c:if test="${dish.rankLevel==2 }">selected="selected"</c:if>>2</option>
			                   				<option value="3" <c:if test="${dish.rankLevel==3 }">selected="selected"</c:if>>3</option>
			                   				<option value="4" <c:if test="${dish.rankLevel==4 }">selected="selected"</c:if>>4</option>
			                   </select>	   
                        </td>
                      </tr>
                      <tr>
                        <td class="td100">菜品名称：</td>
                        <td><input class="con-email5" id="dishName" name="dishName" value="${dish.dishName }" ></td>
                      </tr>
               
                      <tr>
                        <td class="td100">菜品图片：</td>
                        <td>
                        
                   <!--      <input class="con-email5" id="dishImage" name="dishImage" value="" > -->
                        <img  id="img1"  src="${dish.dishImage}" width="150" height="120" alt="" />
	        			<input type="hidden" id="img1u" name="dishImage" value="${dish.dishImage}"/>
	        		  <input  class="uploadify" type="file" value="上传" id="update1"/>
                        
                        </td>
                      </tr>
                      
                	 <tr>
                        <td class="td100">菜品说明：</td>
                        <td><input class="con-email5" id="dishInfo" name="dishInfo" value="${dish.dishInfo}"></td>
                      </tr>
	        	
               
                <tr>
                        <td class="td100">兑换限制人数：</td>
                        <td>
                        	<select name="limitNum">
                        		<option value="1"     <c:if test="${dish.limitNum==1}"> selected="selected"</c:if>>1</option>
                        		<option value="2"     <c:if test="${dish.limitNum==2}"> selected="selected"</c:if>>2</option>
                        		<option value="3"     <c:if test="${dish.limitNum==3}"> selected="selected"</c:if>>3</option>
                        		<option value="4"     <c:if test="${dish.limitNum==4}"> selected="selected"</c:if>>4</option>
                        		<option value="5"     <c:if test="${dish.limitNum==5}"> selected="selected"</c:if>>5</option>
                        		<option value="6"     <c:if test="${dish.limitNum==6}"> selected="selected"</c:if>>6</option>
                        		<option value="7"     <c:if test="${dish.limitNum==7 }"> selected="selected"</c:if>>7</option>
                        		<option value="8"     <c:if test="${dish.limitNum==8 }"> selected="selected"</c:if>>8</option>
                        		<option value="9"     <c:if test="${dish.limitNum==9 }"> selected="selected"</c:if>>9</option>
                        		<option value="10"    <c:if test="${dish.limitNum==10 }"> selected="selected"</c:if>>10</option>
                        		<option value="11"    <c:if test="${dish.limitNum==11 }"> selected="selected"</c:if>>11</option>
                        		<option value="12"    <c:if test="${dish.limitNum==12 }"> selected="selected"</c:if>>12</option>
                        		<option value="13"    <c:if test="${dish.limitNum==13 }"> selected="selected"</c:if>>13</option>
                        		<option value="14"    <c:if test="${dish.limitNum==14 }"> selected="selected"</c:if>>14</option>
                        		<option value="15"    <c:if test="${dish.limitNum==15 }"> selected="selected"</c:if>>15</option>
                        		<option value="16"    <c:if test="${dish.limitNum==16 }"> selected="selected"</c:if>>16</option>
                        		<option value="17"    <c:if test="${dish.limitNum==17 }"> selected="selected"</c:if>>17</option>
                        		<option value="18"    <c:if test="${dish.limitNum==18 }"> selected="selected"</c:if>>18</option>
                        		<option value="19"    <c:if test="${dish.limitNum==19 }"> selected="selected"</c:if>>19</option>
                        		<option value="20"    <c:if test="${dish.limitNum==20 }"> selected="selected"</c:if>>20</option>
                        		<option value="21"    <c:if test="${dish.limitNum==21 }"> selected="selected"</c:if>>21</option>
                        		<option value="22"    <c:if test="${dish.limitNum==22 }"> selected="selected"</c:if>>22</option>
                        		<option value="23"    <c:if test="${dish.limitNum==23 }"> selected="selected"</c:if>>23</option>
                        		<option value="24"    <c:if test="${dish.limitNum==24 }"> selected="selected"</c:if>>24</option>
                        		<option value="25"    <c:if test="${dish.limitNum==25 }"> selected="selected"</c:if>>25</option>
                        		<option value="26"    <c:if test="${dish.limitNum==26 }"> selected="selected"</c:if>>26</option>
                        		<option value="27"    <c:if test="${dish.limitNum==27 }"> selected="selected"</c:if>>27</option>
                        		<option value="28"    <c:if test="${dish.limitNum==28 }"> selected="selected"</c:if>>28</option>
                        		<option value="29"    <c:if test="${dish.limitNum==29 }"> selected="selected"</c:if>>29</option>
                        		<option value="30"    <c:if test="${dish.limitNum==30 }"> selected="selected"</c:if>>30</option>
                        		<option value="31"    <c:if test="${dish.limitNum==31 }"> selected="selected"</c:if>>31</option>
                        		<option value="32"    <c:if test="${dish.limitNum==32 }"> selected="selected"</c:if>>32</option>
                        		<option value="33"    <c:if test="${dish.limitNum==33 }"> selected="selected"</c:if>>33</option>
                        		<option value="34"    <c:if test="${dish.limitNum==34 }"> selected="selected"</c:if>>34</option>
                        		<option value="35"    <c:if test="${dish.limitNum==35 }"> selected="selected"</c:if>>35</option>
                        		<option value="36"    <c:if test="${dish.limitNum==36 }"> selected="selected"</c:if>>36</option>
                        		<option value="37"    <c:if test="${dish.limitNum==37 }"> selected="selected"</c:if>>37</option>
                        		<option value="38"    <c:if test="${dish.limitNum==38 }"> selected="selected"</c:if>>38</option>
                        		<option value="39"    <c:if test="${dish.limitNum==39 }"> selected="selected"</c:if>>39</option>
                        		<option value="40"    <c:if test="${dish.limitNum==40 }"> selected="selected"</c:if>>40</option>
                        		<option value="41"    <c:if test="${dish.limitNum==41 }"> selected="selected"</c:if>>41</option>
                        		<option value="42"    <c:if test="${dish.limitNum==42 }"> selected="selected"</c:if>>42</option>
                        		<option value="43"    <c:if test="${dish.limitNum==43 }"> selected="selected"</c:if>>43</option>
                        		<option value="44"    <c:if test="${dish.limitNum==44 }"> selected="selected"</c:if>>44</option>
                        		<option value="45"    <c:if test="${dish.limitNum==45 }"> selected="selected"</c:if>>45</option>
                        		<option value="46"    <c:if test="${dish.limitNum==46 }"> selected="selected"</c:if>>46</option>
                        		<option value="47"    <c:if test="${dish.limitNum==47 }"> selected="selected"</c:if>>47</option>
                        		<option value="48"    <c:if test="${dish.limitNum==48 }"> selected="selected"</c:if>>48</option>
                        		<option value="49"    <c:if test="${dish.limitNum==49 }"> selected="selected"</c:if>>49</option>
                        		<option value="50"    <c:if test="${dish.limitNum==50 }"> selected="selected"</c:if>>50</option>
                        		<option value="51"    <c:if test="${dish.limitNum==51 }"> selected="selected"</c:if>>51</option>
                        		<option value="52"    <c:if test="${dish.limitNum==52 }"> selected="selected"</c:if>>52</option>
                        		<option value="53"    <c:if test="${dish.limitNum==53 }"> selected="selected"</c:if>>53</option>
                        		<option value="54"    <c:if test="${dish.limitNum==54 }"> selected="selected"</c:if>>54</option>
                        		<option value="55"    <c:if test="${dish.limitNum==55 }"> selected="selected"</c:if>>55</option>
                        		<option value="56"    <c:if test="${dish.limitNum==56 }"> selected="selected"</c:if>>56</option>
                        		<option value="57"    <c:if test="${dish.limitNum==57 }"> selected="selected"</c:if>>57</option>
                        		<option value="58"    <c:if test="${dish.limitNum==58 }"> selected="selected"</c:if>>58</option>
                        		<option value="59"    <c:if test="${dish.limitNum==59 }"> selected="selected"</c:if>>59</option>
                        		<option value="60"    <c:if test="${dish.limitNum==60 }"> selected="selected"</c:if>>60</option>
                        	</select>
                        </td>
                      </tr>
               
                    <tr>
                        <td class="td100">校验时间：</td>
                     	<td><input id="begin" name="beginTime" class="easyui-datebox con-time" value="${dater}"></td>
                      </tr>
                      
                      <tr>
                        <td class="td100"></td>
                        <input  name="id" type="hidden" value="${dish.id}"/>
                        <input  name="token" type="hidden" value="${update_dish_page}"/>
                        <td><input id="toaddreb" type="submit" value="确定" class="submit9" >&nbsp;&nbsp;</td>
                      </tr>
                    </table></form>
            </div>
            <!--上一页下一页-->
       
	</div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
<!--底部-e-->
</body>
</html>
