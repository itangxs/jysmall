<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="/css/seller/A-list.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<script src="/common/My97DatePicker/WdatePicker.js"></script>
<script src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/system/product/manage_list.js"></script>


<title>飞券网平台管理中心</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="2" /></jsp:include>
<div class="membercontent">
	<jsp:include page="/WEB-INF/jsp/system/product/uLeft.jsp" flush="true" ><jsp:param name="val" value="1" /></jsp:include>
		<div class="memberright">
     	<h3 style="padding-left:10px; margin-bottom:25px;">A卡券列表</h3>
        <input type="button" value="新建活动" class="tanchubutton">
    		<form id="from" name="from"  class="zcform1" method="post">
		        <p class="clearfix">
		        	<label class="one" for="con-email4" >活动名称：</label>
		        	<input class="con-email4" >
		            <label class="one" for="con-email4" >店铺名称：</label>
		        	<input class="con-email4">
                    <input type="submit" value="查询" class="submit8" style="width:86px; border:none">
		        </p>

        	</form>
         
          	<div class="member_myorder">
            
         	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                   <th class="td130">活动名称</th>
                    <th class="td130">店铺名称</th>
                    <th class="td200">活动时间</th>
                    <th class="td130">状态</th>  
                    <th class="td80">其他</th>
                    <th class="td130">操作</th>
                </tr>
				  <tr>
                    <td class="td130">小肥羊A券1</td>
                    <td class="td130">小肥羊</td>
                    <td class="td200">2016年8月18日-2016年8月31日</td>
                    <td class="td130">
                    	<div class="status">
                        	<div class="start">
                             <img class="start-btn1" src="/images/start-btn1.png">
                           		<img class="start-btn2" src="/images/start-btn2.png">
                               
                            </div>
                            <div class="pause">
                            	<img class="pause-btn1" src="/images/pause-btn1.png">
        	               		<img class="pause-btn2" src="/images/pause-btn2.png">
                                
                            </div>
                            <div class="end">
								<img class="end-btn1" src="/images/end-btn1.png">
                                <img class="end-btn2" src="/images/end-btn2.png">
           
                            </div>
                            <div class="clearfix"></div>
                        </div>
                    </td>  
                    <td class="td80">详情</td>
                    <td class="td130">
                    	<input type="button" value="删除" id="delete">
                    </td>
                </tr>
              </table>
            </div>
          
	</div>
	<div class="clear"></div>
</div>

<!--状态切换-->
<script>
	
	$(".start-btn2").click(
		function(){
			$(".start-btn2").hide()
			$(".start-btn1").show()
			$(".pause-btn2").show()
			$(".end-btn2").show()
		
		}
	)
	$(".pause-btn2").click(
		function(){
			$(".pause-btn2").hide()
			$(".pause-btn1").show()
			$(".start-btn2").show()
			$(".end-btn2").show()
		
		}
	)
	
	$(".end-btn2").click(
		function(){
			$(".end-btn2").hide()
			$(".end-btn1").show()
			$(".start-btn2").show()
			$(".pause-btn2").show()
		
		}
	)
</script>
<jsp:include page="/WEB-INF/jsp/system/uFooter.jsp" flush="true" />
</body>
</html>