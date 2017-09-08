<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% 
if(null!=request.getHeader("user-agent")&&((request.getHeader("user-agent").toLowerCase().indexOf("iphone")!=-1 || request.getHeader("user-agent").toLowerCase().indexOf("android")!=-1
|| request.getHeader("user-agent").toLowerCase().indexOf("ipad")!=-1
|| request.getHeader("user-agent").toLowerCase().indexOf("linux")!=-1))) 
{ 

}else{

	return ;
}  
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-itunes-app"/>
<meta name="apple-mobile-web-app-capable" content="yes">
<title>购物车</title>
<link href="/css/zhifu.css" type="text/css" rel="stylesheet" />
<link href="/css/form.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/js/jquery.cookie.js"></script>
<script type="text/javascript" src="/js/formcheck.js"></script>
<script src="/js/laydate/laydate.js"></script>
<script type="text/javascript" src="/js/wxstore/confirmcart.js"></script>
</head>
<body>
<div class="infomod">
	<!--用餐人数-->
	<div class="infomodbox">
		<div class="left">用餐人数</div>
    	<div class="right">
            <div id="div1" style="display:block">
                <ul class="weishu">
                    <li>1-2位</li>
                    <li>3-4位</li>
                    <li>4-6位</li>
                    <li>6-8位</li>
                    <li>8-10位</li>
                    <li id="btn"><span class="icon"></span>其他</li>
                </ul>
            </div> 
            <!--点击其他的时候显示输入框,用餐人数列表隐藏-->
            <div id="div2" style="display:none"><input class="weishuinput" name="" id="peopleNum" type="text" value=""></div> 
          <!--  <input type="hidden" id="peopleNum" value=""> -->
           <input type="hidden" id="yongcantime" value="">
           <input type="hidden" id="nums" value="${nums }">
           <input type="hidden" id="ids" value="${ids }">
           <input type="hidden" id="storeId" value="${store.id }">
           <input type="hidden" id="userId" value="${userId }">
        </div>
        <div class="clear"></div>
    </div>
    <!--用餐时间-->
    <div class="infomodbox">
		<div class="left"><p>用餐时间</p><p style="font-size:0.9em;" class="red"><input readonly class="layinput dateinput" id="hello1"></p></div>
    	<div class="right">
        	<ul class="date">
            	<div class="datebg" onClick="laydate({elem: '#hello1'});">
                	<p class="icon"></p>
                    <p>日历</p>
                </div> 
            	<li style="margin:0 0 0 25%;"><p class="riqi">${today }</p><p>今日</p></li>
                <li><p class="riqi">${tomorrow }</p><p>明日</p></li>
                <li class="noline"><p class="riqi">${thridday }</p><p>后日</p></li>
                <div class="clear"></div>
                               
            </ul>
        </div>
        <div class="clear"></div>
    </div>
    <!--用餐时间-->
    <div class="infoshijianbox">
		<div class="left">
        	<div id="tagsarea">  
                <ul id="tags">  
                  <li class="selectTag"><a onClick="selectTag('tagContent0',this)" href="javascript:void(0)">早上</a></li>  
                  <li><a onClick="selectTag('tagContent1',this)" href="javascript:void(0)">中午</a></li>
                  <li style="margin-bottom:-5px;"><a onClick="selectTag('tagContent2',this)" href="javascript:void(0)">晚上</a></li> 
                </ul>        
        	</div>
        </div>
    	<div class="right">
        	<div id="tagContent"> 
            	 <!--早上--> 
                <div class="tagContent selectTag" id="tagContent0">
                    <ul class="shijian">
                    	<c:if test="${store.trafficBeginTime <'06:00' && '06:00' < store.trafficEndTime}"><li>06:00</li></c:if>
                       	<c:if test="${store.trafficBeginTime <'06:30' && '06:30' < store.trafficEndTime}"><li>06:30</li></c:if>
                       	<c:if test="${store.trafficBeginTime <'07:00' && '07:00' < store.trafficEndTime}"><li>07:00</li></c:if>
                       	<c:if test="${store.trafficBeginTime <'07:30' && '07:30' < store.trafficEndTime}"><li>07:30</li></c:if>
                       	<c:if test="${store.trafficBeginTime <'08:00' && '08:00' < store.trafficEndTime}"><li>08:00</li></c:if>
                       	<c:if test="${store.trafficBeginTime <'08:30' && '08:30' < store.trafficEndTime}"><li>08:30</li></c:if>
                        <c:if test="${store.trafficBeginTime <'09:00' && '09:00' < store.trafficEndTime}"><li>09:00</li></c:if>
                       	<c:if test="${store.trafficBeginTime <'09:30' && '09:30' < store.trafficEndTime}"><li>09:30</li></c:if>
                       	<c:if test="${store.trafficBeginTime <'10:00' && '10:00' < store.trafficEndTime}"><li>10:00</li></c:if>
                    </ul>
                </div>  
                    <!--中午-->
                    <div class="tagContent" id="tagContent1">
                        <ul class="shijian">
                        	<c:if test="${store.trafficBeginTime <'10:30' && '10:30' < store.trafficEndTime}"><li>10:30</li></c:if>
                            <c:if test="${store.trafficBeginTime <'11:00' && '11:00' < store.trafficEndTime}"><li>11:00</li></c:if>
                            <c:if test="${store.trafficBeginTime <'11:30' && '11:30' < store.trafficEndTime}"><li>11:30</li></c:if>
                            <c:if test="${store.trafficBeginTime <'12:00' && '12:00' < store.trafficEndTime}"><li>12:00</li></c:if>
                            <c:if test="${store.trafficBeginTime <'12:30' && '12:30' < store.trafficEndTime}"><li>12:30</li></c:if>
                           	<c:if test="${store.trafficBeginTime <'13:00' && '13:00' < store.trafficEndTime}"><li>13:00</li></c:if>
                            <c:if test="${store.trafficBeginTime <'14:30' && '14:30' < store.trafficEndTime}"><li>14:30</li></c:if>
                            <c:if test="${store.trafficBeginTime <'15:00' && '15:00' < store.trafficEndTime}"><li>15:00</li></c:if>
                            <c:if test="${store.trafficBeginTime <'15:30' && '15:30' < store.trafficEndTime}"><li>15:30</li></c:if>
                        </ul>
                    </div> 
                    <!--晚上-->
                    <div class="tagContent" id="tagContent2">
                    	<ul class="shijian">
                            <c:if test="${store.trafficBeginTime <'16:00' && '16:00' < store.trafficEndTime}"><li>16:00</li></c:if>
                            <c:if test="${store.trafficBeginTime <'16:30' && '16:30' < store.trafficEndTime}"><li>16:30</li></c:if>
                            <c:if test="${store.trafficBeginTime <'17:00' && '17:00' < store.trafficEndTime}"><li>17:00</li></c:if>
                            <c:if test="${store.trafficBeginTime <'17:30' && '17:30' < store.trafficEndTime}"><li>17:30</li></c:if>
                            <c:if test="${store.trafficBeginTime <'18:00' && '18:00' < store.trafficEndTime}"><li>18:00</li></c:if>
                            <c:if test="${store.trafficBeginTime <'18:30' && '18:30' < store.trafficEndTime}"><li>18:30</li></c:if>
                            <c:if test="${store.trafficBeginTime <'19:30' && '19:30' < store.trafficEndTime}"><li>19:30</li></c:if>
                            <c:if test="${store.trafficBeginTime <'20:00' && '20:00' < store.trafficEndTime}"><li>20:00</li></c:if>
                            <c:if test="${store.trafficBeginTime <'20:30' && '20:30' < store.trafficEndTime}"><li>20:30</li></c:if>
                            <c:if test="${store.trafficBeginTime <'21:00' && '21:00' < store.trafficEndTime}"><li>21:00</li></c:if>
                            <c:if test="${store.trafficBeginTime <'21:30' && '21:30' < store.trafficEndTime}"><li>21:30</li></c:if>
                            <c:if test="${store.trafficBeginTime <'22:00' && '22:00' < store.trafficEndTime}"><li>22:00</li></c:if>
                            <c:if test="${store.trafficBeginTime <'22:30' && '22:30' < store.trafficEndTime}"><li>22:30</li></c:if>
                            <c:if test="${store.trafficBeginTime <'23:00' && '23:00' < store.trafficEndTime}"><li>23:00</li></c:if>
                            <c:if test="${store.trafficBeginTime <'23:30' && '23:30' < store.trafficEndTime}"><li>23:30</li></c:if>
                        </ul>
                    </div>  
                </div>
        </div>
        <div class="clear"></div>
    </div>
    <!--联系人姓名-->
    <div class="infomodbox">
		<div class="left"><p>联系人姓名</p></div>
    	<div class="right">
        	<input class="inputname" name="" id="contacts" type="text" value="">
            <form accept-charset="utf-8" method="get" action="#" style="text-align:right; margin-right:8px;">
                    <fieldset class="radios">
                        <label for="radio-01" class="label_radio">
                            <input name="sample-radio" type="radio" id="radio-01" value="1" checked="checked" />先生
                      </label>
                        <label for="radio-02" class="label_radio">
                            <input type="radio" value="2" id="radio-02" name="sample-radio" />女士
                        </label>
                    </fieldset>
                </form>
        </div>
        <div class="clear"></div>
    </div>
    <!--联系人电话-->
    <div class="infomodbox">
		<div class="left"><p>联系人电话</p></div>
    	<div class="right">
        	<input class="inputname" name="" id="contactsnum" type="text" value="">
        </div>
        <div class="clear"></div>
    </div>
    <!--桌号-->
    <div class="infomodbox">
		<div class="left1"><p>桌 号</p></div>
    	<div class="right1">
        	<input class="inputname" name="" id="deskNo" type="text" value="根据您的桌号填写,在线预约无须填写">
        </div>
        <div class="clear"></div>
    </div>
    <div class="orderbottombox">
		<div class="paybutton"><a href="javascript:topay();">确认</a></div>
	</div>
</div>
<script type=text/javascript>   
function selectTag(showContent,selfObj){   
    // 操作标签   
    var tag = document.getElementById("tags").getElementsByTagName("li");   
    var taglength = tag.length;   
    for(i=0; i<taglength; i++){   
        tag[i].className = "";   
    }   
    selfObj.parentNode.className = "selectTag";   
    // 操作内容   
    for(i=0; j=document.getElementById("tagContent"+i); i++){   
        j.style.display = "none";   
    }   
    document.getElementById(showContent).style.display = "block";   
}   
</script>
<script>
!function(){
laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
laydate({elem: '#demo'});//绑定元素
}();
//日期范围限制
var start = {
    elem: '#start',
    format: 'YYYY-MM-DD',
    min: laydate.now(), //设定最小日期为当前日期
    max: '2099-06-16', //最大日期
    istime: true,
    istoday: false,
    choose: function(datas){
         end.min = datas; //开始日选好后，重置结束日的最小日期
         end.start = datas //将结束日的初始值设定为开始日
    }
};
var end = {
    elem: '#end',
    format: 'YYYY-MM-DD',
    min: laydate.now(),
    max: '2099-06-16',
    istime: true,
    istoday: false,
    choose: function(datas){
        start.max = datas; //结束日选好后，充值开始日的最大日期
    }
};
laydate(start);
laydate(end);
//自定义日期格式
laydate({
    elem: '#test1',
    format: 'YYYY年MM月DD日',
    festival: true, //显示节日
    choose: function(datas){ //选择日期完毕的回调
        alert('得到：'+datas);
    }
});
//日期范围限定在昨天到明天
laydate({
    elem: '#hello3',
    min: laydate.now(-1), //-1代表昨天，-2代表前天，以此类推
    max: laydate.now(+1) //+1代表明天，+2代表后天，以此类推
});
</script>
  <script>
	 	var oBtn = document.getElementById('btn');
		var oDiv1 = document.getElementById('div1');
		var oDiv2 = document.getElementById('div2');
		oBtn.onclick = function(){
			if(oDiv1.style.display == 'block'){
				oDiv1.style.display = 'none';
				oDiv2.style.display = 'block';
				$("#peopleNum").val("");
			}else{
				oDiv2.style.display = 'none';
				oDiv1.style.display = 'block';
				$("#peopleNum").val("");
			}
		};
	 </script>
</body>
</html>