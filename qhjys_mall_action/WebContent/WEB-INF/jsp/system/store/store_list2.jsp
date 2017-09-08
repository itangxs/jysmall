<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp" flush="true"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/system/mallusermanage/list.js"></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js "></script>
<script type="text/javascript" src="/js/system/store/store_list.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp" flush="true" ><jsp:param name="val" value="5" /></jsp:include>
<!--------------------------我的账户-------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/store/uLeft.jsp"><jsp:param name="position"  value="1"/></jsp:include>
	<!-- end -->
		<div class="memberright">
             <h3 style="padding-left:10px; margin-bottom:25px;">店铺列表</h3>
        <form id="from"  class="zcform1" method="post" action="list.do">
         <input id="page" name="pageNum" type="hidden">
           <p class="clearfix">
        	<label class="one" for="con-email4" >商家注册名：</label>
        	<input class="con-email4" name="username" value="${username}">
            <label class="one" for="con-email4" >店铺名称：</label>
        	<input class="con-email4" name="storeName" value="${storeName}">
        	<label class="one" for="con-email4" >店铺ID：</label>
        	<input class="con-email4" name="storeId" value="${storeId}">
           </p>
           
           <p class="clearfix">   
        	
          <!--   <select name="xian">
             <option>商圈</option>
            </select>     -->   
        	 </p>
         
           <p class="clearfix">
        	<label class="one" for="con-email4" >创建时间：</label>
        	<input id="begin" name="createStart" class="easyui-datebox con-time" value="${createStart}">
			<label class="one" for="con-email2">-</label>
			<input id="ending" name="createEnd" class="easyui-datebox con-time" value="${createEnd}">
             
             <label class="one" for="con-email4" >审核状态：</label> 
            <select name="status">
             <option value="">全部</option>
             <option value="0"<c:if test="${status ==0 }">selected="selected"</c:if>>未审核</option>
             <option value="1" <c:if test="${status ==1 }">selected="selected"</c:if>>审核未通过</option>
             <option value="2" <c:if test="${status ==2 }">selected="selected"</c:if>>审核通过</option>
            </select>
             <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
       	  </p>
       	  <p>
       	  	<label class="one" for="con-email4" >收银台状态：</label> 
            <select name="cashierStatus">
		        <option value="">全部</option>
		        <option value="0"<c:if test="${cashierStatus ==0 }">selected="selected"</c:if>>关闭</option>
		        <option value="1" <c:if test="${cashierStatus ==1 }">selected="selected"</c:if>>开启</option>
            </select>
            <label class="one" for="con-email4" >点餐状态：</label> 
            <select name="orderStatus">
	            <option value="">全部</option>
	            <option value="0"<c:if test="${orderStatus ==0 }">selected="selected"</c:if>>不开通</option>
	            <option value="1" <c:if test="${orderStatus ==1 }">selected="selected"</c:if>>开通</option>
            </select>
            <label class="one" for="con-email4" >费率状态：</label> 
            <select name="rateStatus">
             	<option value="">全部</option>
             	<c:forEach items="${fqStoreRates }" var="fsr">
		        	<option value="${fsr.rateName }" <c:if test="${rateStatus == fsr.rateName }">selected="selected"</c:if>>${fsr.rateName }</option>
		        </c:forEach>
            </select>
       	  </p>
       	  <p class="clearfix">
          	<div class="diqubox">
            	<ul>
                	<li>城市<br>
            			<select name="">
        	  				<option>深圳市</option>
        				</select>
            		</li>
                    <li>行政区<br>
            			<select name="">
        	  				<option>福田区</option>
        				</select>
            		</li>
                    <li>商圈<br>
            			<select name="">
        	  				<option>华强北</option>
        				</select>
            		</li>
                    <li>大行业<br>
                        <select name="">
                            <option>餐饮</option>
                            <option>非餐饮</option>
                        </select>
                    </li>
                    <li>细分行业<br>
                        <select name="">
                            <option>休闲娱乐</option>
                            <option>丽人</option>
                            <option>生活服务</option>
                            <option>爱车</option>
                            <option>宠物</option>
                            <option>培训</option>
                            <option>运动健身</option>
                        </select>
                    </li>
                </ul>
            	<br><input type="submit" value="查询" class="submit8">
            </div>
             
       	  </p>
          
           <p class="clearfix">
           <!--   <input type="submit" value="管理店铺" class="submit9">
             <label for="select">&nbsp;&nbsp;&nbsp;</label>        -->
             <input type="button" value="审核通过" class="submit9" onclick="updateStauts(2)">
              <label for="select">&nbsp;&nbsp;&nbsp;</label>       
             <input type="button" value="审核不通过" class="submit9" onclick="updateStauts(1)">
         <!--     <label for="select">&nbsp;&nbsp;&nbsp;</label>       
             <input type="submit" value="修改密码" class="submit9"> -->
              <label for="select">&nbsp;&nbsp;&nbsp;</label>       
             <input type="button" value="需授权" class="submit9" onclick="updateScope(1)">
              <label for="select">&nbsp;&nbsp;&nbsp;</label>       
             <input type="button" value="无需授权" class="submit9" onclick="updateScope(2)">
             
             <label for="select">&nbsp;&nbsp;&nbsp;</label>       
             <input type="button" value="开启收银台" class="submit9" onclick="updateCashier(1)">
              <label for="select">&nbsp;&nbsp;&nbsp;</label>       
             <input type="button" value="关闭收银台" class="submit9" onclick="updateCashier(0)">
             
             <label for="select">&nbsp;&nbsp;&nbsp;</label>       
             <input type="button" value="开通收点餐" class="submit9" onclick="updateOrder(1)">
              <label for="select">&nbsp;&nbsp;&nbsp;</label>       
             <input type="button" value="不开通点餐" class="submit9" onclick="updateOrder(0)">
           </p>
           
        </form>
        
        <div class="member_myorder">
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th class="td40">
                    <input id="quan" type="checkbox" name="quan" onclick="xuan('qxx')"/>
                    <th class="td50">店铺ID</th>
                    <th class="td80">注册名</th>
                    <th class="td80">店铺名称</th>
                   <!--  <th class="td160">评分</th>
                    <th class="td160">推荐店铺</th>   -->
                    <th class="td50">邀请码</th>
                    <th class="td50 th20">审核<br>状态</th>
                    <th class="td60 th20">微信授<br>权状态</th>
                    <th class="td50 th20">收银台<br>状态</th>
                    <th class="td50 th20">点餐<br>状态</th>
                    <!--<th class="td100">基础费率</th>
                    <th class="td100">支付费率</th> -->
                    <th class="td80">创建时间</th>        
                    <th class="td50">操作</th>
                    <th class="td80">费率设置</th>
                    <th class="td80">行业类型</th>
                    <th class="td80">渠道绑定</th>
                </tr>
              
                <tr>
                    <td class="td40">
                   	 <input name="ids" type="checkbox" value="${s.id}" onclick="xuan('dx')" />
                   	 </td>
                    <td class="td50 tdfont">${s.id}</td>
                    <td class="td80 tdfont">${s.sellerName }</td>
                    <td class="td80 th20">${s.name }</td>
                     <td class="td50 tdfont">${s.invite }</td>
                    <td class="td50 th20">
                    	<c:if test="${s.status == 0  }">未审核</c:if>
                    	<c:if test="${s.status == 1  }">审核<br>失败</c:if>
                    	<c:if test="${s.status == 2  }">审核<br>通过</c:if>
                    </td>
                    <td class="td60 th20">
                        <c:if test="${s.scope != 2  }">需确认<br>授权</c:if>
                    	<c:if test="${s.scope == 2  }">无需确认<br>授权</c:if>
                    	
                    </td>
                    <td class="td50">
                        <c:if test="${s.openCashier == 0  }">关闭</c:if>
                    	<c:if test="${s.openCashier == 1  }">开启</c:if>
                    </td>
                    <td class="td50">
                        <c:if test="${s.openOrder == 0  }">不开通</c:if>
                    	<c:if test="${s.openOrder == 1  }">开通</c:if>
                    </td>
 
                    <td class="td80 tdfont"><fmt:formatDate value="${s.createDate}" pattern="yyyy-MM-dd "/></td> 
                    <td class="td50">
                    <button onclick="javascript:window.location.href='getStoreDetail.do?id=${s.id}'">查看 </button>
                    </td>
                    <td class="td80">
                    	<c:if test="${!empty s.rateName || s.rateName != ''}">${s.rateName }</c:if>
                    	<c:if test="${empty s.rateName || s.rateName=='' }">-</c:if>
                    	<p><button onclick="getId(${s.id})">设置 </button></p>
                        
                    </td> 
                    <td class="td80">
                    	<p>餐饮 | 自助餐</p>
                    	<p><button onclick="document.getElementById('hangyelx').style.display='block'; document.getElementById('fade').style.display='block'">设置 </button></p>
                        
                    </td>
                    <td class="td80">
                    	<p>-</p>
                    	<p><button onclick = "document.getElementById('qdbd').style.display='block';document.getElementById('fade').style.display='block'">绑定</button></p>
                        
                    </td>
                </tr>
                
                 <tr>
                    <td class="td40">
                   	 <input name="ids" type="checkbox" value="${s.id}" onclick="xuan('dx')" />
                   	 </td>
                    <td class="td50 tdfont">${s.id}</td>
                    <td class="td80 tdfont">${s.sellerName }</td>
                    <td class="td80 th20">${s.name }</td>
                     <td class="td50 tdfont">${s.invite }</td>
                    <td class="td50 th20">
                    	<c:if test="${s.status == 0  }">未审核</c:if>
                    	<c:if test="${s.status == 1  }">审核<br>失败</c:if>
                    	<c:if test="${s.status == 2  }">审核<br>通过</c:if>
                    </td>
                    <td class="td60 th20">
                        <c:if test="${s.scope != 2  }">需确认<br>授权</c:if>
                    	<c:if test="${s.scope == 2  }">无需确认<br>授权</c:if>
                    	
                    </td>
                    <td class="td50">
                        <c:if test="${s.openCashier == 0  }">关闭</c:if>
                    	<c:if test="${s.openCashier == 1  }">开启</c:if>
                    </td>
                    <td class="td50">
                        <c:if test="${s.openOrder == 0  }">不开通</c:if>
                    	<c:if test="${s.openOrder == 1  }">开通</c:if>
                    </td>
 
                    <td class="td80 tdfont"><fmt:formatDate value="${s.createDate}" pattern="yyyy-MM-dd "/></td> 
                    <td class="td50">
                    <button onclick="javascript:window.location.href='getStoreDetail.do?id=${s.id}'">查看 </button>
                    </td>
                    <td class="td80">
                    	<c:if test="${!empty s.rateName || s.rateName != ''}">${s.rateName }</c:if>
                    	<c:if test="${empty s.rateName || s.rateName=='' }">-</c:if>
                    	<p><button onclick="getId(${s.id})">设置 </button></p>
                        
                    </td> 
                    <td class="td80">
                    	<p>餐饮 | 自助餐</p>
                    	<p><button onclick="document.getElementById('hangyelx').style.display='block'; document.getElementById('fade').style.display='block'">设置 </button></p>
                        
                    </td>
                    <td class="td80">
                    	<p>-</p>
                    	<p><button onclick = "document.getElementById('qdgb').style.display='block';document.getElementById('fade').style.display='block'">改绑</button></p>
                        
                    </td>
                </tr>
                
                
              </table>
            </div>
	     <!--上一页下一页-->
        <div class="page">
            	<c:if test="${page.getPageNum()>1}">
            	<a href="javascript:previousPage(${page.getPageNum()-1},'from','page')" class="prev">上一页</a>
            	</c:if>
            	<c:choose>
            	<c:when test="${page.getPages()<7}">
            		<c:forEach var="i" begin="1" end="${page.getPages()}">
            			<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            	</c:when>
            	<c:when test="${page.getPages()>6}">
            		<c:forEach var="i" begin="1" end="3">
            			<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            		<c:if test="${page.getPageNum()>4}"><em>...</em></c:if>
            		<c:forEach var="i" begin="4" end="${page.getPages()-3}">
            			<c:if test="${i==page.getPageNum()}"><em class="current">${i}</em></c:if>
            		</c:forEach>
            		<c:if test="${page.getPageNum()<page.getPages()-3}"><em>...</em></c:if>
            		<c:forEach var="i" begin="${page.getPages()-2}" end="${page.getPages()}">
            			<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            	</c:when>
            	</c:choose>
            	<c:if test="${page.getPages()>page.getPageNum()}"><a href="javascript:previousPage(${page.getPageNum()+1},'from','page')" class="next">下一页</a></c:if>
               <span>共${page.getPages()}页</span><span>到第</span>
               <input class="input1" id="jumPage" name="pageNum" type="text" value="${page.getPageNum()}" onkeydown="if(event.keyCode==13){previousPage(this.value,'from','page')}"/><span>页</span>
        </div>
	</div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
<!--底部-e-->

<!--渠道绑定设置弹出层-->
<div id="qdbd" class="white_content">
	<p class="close03">绑定</p>
    <div class="nr nr01">
    	<div class="bangd_detail">
          <div class="bangd_left">
             <p class="clearfix">
            		<label class="con-email130" for="con-email4"><span class="textred">*</span> 区域：</label>
             </p>
             <p class="clearfix">
                    <label class="one" for="con-email4" ><span class="textred">*</span> 公司：</label>        
        	 </p>
              <p class="clearfix">
                    <label class="one" for="con-email4" ><span class="textred">*</span> 团队长：</label>        
        	 </p>
              <p class="clearfix">
                    <label class="one" for="con-email4" ><span class="textred">*</span> 业务员：</label>        
        	 </p>
          </div>
          <div class="bangd_right">
          		<p class="clearfix">
            		 <select>
                    	<option>请选择</option>
                    </select>
                    <select>
                    	<option>请选择</option>
                    </select>
           	 	</p>
                
                <p class="clearfix">
                	 <select class="w180">
                    	<option>请选择</option>
                    </select>
                </p>
                <p class="clearfix">
            		 <select class="w180">
                    	<option>请选择</option>
                    </select>
           	 	</p>
                
                <p class="clearfix">
                	 <select class="w180">
                    	<option>请选择</option>
                    </select>
                </p>
          </div>
        </div>
    </div>
    <div class="kqanniu01">
    	<a href = "javascript:void(0)" style=" background-color:#ccc;" onclick = "document.getElementById('qdbd').style.display='none';document.getElementById('fade').style.display='none'">取消</a>
        <a href = "javascript:void(0)" style=" background-color:#64a1ce;" onclick = "">确认</a>
                                                        
     </div>   
</div>

<!--渠道改绑设置弹出层-->
<div id="qdgb" class="white_content">
	<p class="close03">改绑</p>
    <div class="nr nr01">
    	<div class="bangd_detail">
          <div class="bangd_left">
             <p class="clearfix">
            		<label class="con-email130" for="con-email4"><span class="textred">*</span> 区域：</label>
             </p>
             <p class="clearfix">
                    <label class="one" for="con-email4" ><span class="textred">*</span> 公司：</label>        
        	 </p>
              <p class="clearfix">
                    <label class="one" for="con-email4" ><span class="textred">*</span> 团队长：</label>        
        	 </p>
              <p class="clearfix">
                    <label class="one" for="con-email4" ><span class="textred">*</span> 业务员：</label>        
        	 </p>
          </div>
          <div class="bangd_right">
          		<p class="clearfix">
            		 <select>
                    	<option>广东省</option>
                    </select>
                    <select>
                    	<option>深圳市</option>
                    </select>
           	 	</p>
                
                <p class="clearfix">
                	 <select class="w180">
                    	<option>飞券</option>
                    </select>
                </p>
                <p class="clearfix">
            		 <select class="w180">
                    	<option>李四</option>
                    </select>
           	 	</p>
                
                <p class="clearfix">
                	 <select class="w180">
                    	<option>张三</option>
                    </select>
                </p>
          </div>
        </div>
    </div>
    <div class="kqanniu01">
    	<a href = "javascript:void(0)" style=" background-color:#ccc;" onclick = "document.getElementById('qdgb').style.display='none';document.getElementById('fade').style.display='none'">取消</a>
        <a href = "javascript:void(0)" style=" background-color:#64a1ce;" onclick = "">确认</a>
                                                        
     </div>   
</div>


<!--费率设置弹出层-->
<div id="feilvset" class="white_content_new1">	
    <div class="m-windowbox400">
    	<div class="title">费率设置<span class="close"><a href="javascript:void(0)" onclick="document.getElementById('feilvset').style.display='none';document.getElementById('fade').style.display='none'">
×</a></span></div>
		<div class="nr">
			<input type="hidden" id="storeId" value="${storeId }" />
            <p>店铺结算周期：
            	T+ <input style="width:40px;" name="" type="text"> 天
            </p><br>
        	<p>设置提现费率：
        		<select id="rate">
        			<option value="">无费率</option>
		        	<c:forEach items="${fqStoreRates }" var="fsr">
		        		<option value="${fsr.id }">${fsr.rateName }</option>
		        	</c:forEach>
                </select></p><br>
            <p><input  class="submit8" name="" type="submit" value="确认" onclick="return setRate();"/>
            	<input  class="submit8" style="margin:0 0 0 20px;" name="" type="reset" value="取消" /></p>
        </div> 
    </div>
</div>
<!--行业类型弹出层-->
<div id="hangyelx" class="white_content_new1">	
    <div class="m-windowbox400">
    	<div class="title">店铺类型设置<span class="close"><a href="javascript:void(0)" onclick="document.getElementById('hangyelx').style.display='none';document.getElementById('fade').style.display='none'">
×</a></span></div>
		<div class="nr">
        	<p>大 行 业：
        		<select id="dahangye">
		        	<option value="">餐饮</option>
                    <option value="">非餐饮</option>
                </select></p><br>
                <p>细分行业：
        		<select id="xifenhangye">
		        	<option value="">粤菜</option>
                    <option value="">自助餐</option>
                </select></p><br>
            <p><input  class="submit8" name="" type="submit" value="确认" onclick="return setRate();"/>
            	<input  class="submit8" style="margin:0 0 0 20px;" name="" type="reset" value="取消" /></p>
        </div> 
    </div>
</div>




<div id="fade" class="black_overlay"></div>
</body>

</html>
