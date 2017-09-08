<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_uheader.jsp" flush="true" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="/css/public.css" rel="stylesheet" type="text/css" />
<link href="/css/member.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/compatible.js"></script>
<script type="text/javascript" src="/js/account/setup_address.js"></script>
<script type="text/javascript" src="/js/geo.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
<div class="membercontent">
    <!--------------左侧------------->
	<jsp:include  page="/WEB-INF/jsp/account/navigation1.jsp"><jsp:param name="position" value="9"/></jsp:include>
    <!--------------右侧------------->
	<div class="memberright">
    	<div class="memberright_title">收货地址</div>
        <div class="member_setaddress">
        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <th class="td90">收货人</th>
                <th>地址/邮编</th>
                <th class="td130">电话/手机</th>
                <th class="td130">操作</th>
              </tr>
              <c:forEach var="page" items="${list}">
              <tr> 	
                <td>${page.recipient}</td>
                <td>${page.address},${page.zip}</td>
                <td>${page.phone}</td>
                <td ><a href="javascript:;" onclick="del(${page.id})">删除</a> <a href="javascript:;" onclick="update(${page.id})">修改</a></td>
              </tr>
              </c:forEach>
            </table>            
        </div>
        <div class="member_setaddaddress">
        		<h1><a href="javascript:;" onclick="setupAddress()">添加地址</a></h1>
            <form id="setupAddressForm" action="" method="post">
            	<div id="setAddress" style="width:800px;display: none">
            	<input class="input500" id="id" name="id" type="hidden" />
            	<ul>
                	<li><span><i>*</i>所在地区：</span>
						<select id="province" name="province" class="easyui-combobox" style="width:130px;height:32px"></select>
						<select id="cityId" name="city" class="easyui-combobox" style="width:130px;height:32px"></select>
						<select id="area" name="area" class="easyui-combobox" style="width:130px;height:32px"></select>
						<input type='hidden' id="hideDistrict"/>
                        <div class="clear"></div>
                	</li>
                	<li>
                    	<span><i>*</i>街道地址：</span>
                        <input class="input500" id="address" name="address" type="text" />
                        <div class="clear"></div>
                    </li>
                    <li>
                    	<span><i>*</i>邮政编码：</span>
                        <input  class="input200" id="zip" name="zip" type="text" />
                        <div class="clear"></div>
                    </li>
                    <li>
                    	<span><i>*</i>收货人姓名：</span>
                        <input class="input200" id="recipient" name="recipient" type="text" />
                        <div class="clear"></div>
                    </li>
                    <li>
                    	<span><i>*</i>电话号码：</span>
                        <input class="input200" id="phone" name="phone" type="text" />
                        <div class="clear"></div>
                    </li>
                    <li>
                    	<span></span>
                    	<input class="button_red" type="submit" name="button" id="button" value="保存" />
                    	<a href="javascript:;" onclick="closeWindow()"><span>取消</span></a>
                        <div class="clear"></div>
                    </li>
                </ul>
                </div>
            </form>
        </div>
    </div>
	<div class="clear"></div>
</div>
<!----------------底部-------------->
<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
</body>
</html>