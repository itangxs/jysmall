<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!------------------底部---------------->
<div class="footer">
	<div class="bottom1">
       <ul>
             <li>
					<!-- <h1 class="bottom_navbg1">网站协议</h1>
					<p>
						<a href="/mallcms/info2.do?id=17">用户服务协议</a>
					</p>
					<p>
						<a href="/mallcms/info2.do?id=16">商户服务协议</a>
					</p>
			
				</li>-->
				<li>
					<!--<h1 class="bottom_navbg2">使用指南</h1>
					<p>
						<a href="/mallcms/info3.do?id=24">飞券手机端</a>
					</p>
					-->
				</li>
				<li>
					<!--<h1 class="bottom_navbg5">法律声明</h1>
				<p>		
                        <a href="/mallcms/info4.do?id=6">法律声明</a>
				</p>	-->
				</li>
				<li>
					<!--<h1 class="bottom_navbg6">关于我们</h1>
					<p>
						<a href="/mallcms/info.do?id=5">公司简介</a>
					</p>
					<p>
						<a href="/mallcms/info.do?id=1">联系我们</a>
					</p>
					-->
				</li>
              <div class="bottom_service">
            	<h3>客服热线</h3>
                <h2>400-6333-088</h2>
                <p>(周一至周五，09:00-18:00)</p>
            </div>    
            <div class="clear"></div>       
        </ul>        
    </div>    
    </div>    
</div>
<div class="bottom2">   
  <h3>

			粤ICP备14023008号 版权所有©2013-2015 深圳市前海金钥匙控股集团有限公司 <a href="www.jysp2p.com"></a>

		</h3>
</div>
<!--底部-e-->

<!--点餐信息通知弹窗-->
<div id="diancan" class="white_content">
	<p class="close02">通知</p>
    <div class="nr nr03">
    	<div class="toufang_list">
            <p class="zj-step03">您有 <span style="color:#44b5df; font-size:28px;">1<span>个</span></span> 新的点餐信息！<p>
		</div>
    </div>
    <div class="dcanniu">
    	<a href="/managermall/seller/fqorder/orderList.do" style=" background-color:#f93;">查看详情</a>
    	<a href="javascript:void(0)" style=" background-color:#ccc;"  onclick = "document.getElementById('diancan').style.display='none';document.getElementById('fade02').style.display='none'">取消</a>    </div>    
</div>
<!--弹出幕布-->
<div id="fade02" class="black_overlay"></div>