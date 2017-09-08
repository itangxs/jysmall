package cn.qhjys.mall.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.UserInfo;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.HtmlUtil;

@Controller
public class TopLeftMenuAccount extends Base {

	/**
	 * 会员中心头部 参考 /WEB-INF/jsp/account/uHeader.jsp
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/managermall/account/top")
	public void top(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		StringBuffer html = new StringBuffer("");
		html.append("<div class='topmenu'>");
		html.append("<div class='topmenu1'>");
		html.append("<div class='left-city'>");
		html.append("		深圳");
		html.append("		<h4>");
		html.append("			<a id='switchCity' href='javascript:;'>切换城市<span class='arrow-down-logo'></span></a>");
		html.append("		</h4>");
		html.append("		<div id='citys' class='city-list' style='display:none;'>");
		html.append("			<h3>热门城市</h3>");
		html.append("			<ul>");
		html.append("				<li><a href='javascript:;'>深圳</a></li>");
		html.append("				<li><a href='javascript:;'>佛山</a></li>");
		html.append("			</ul>");
		html.append("		</div>");
		html.append("	</div>");
		html.append("	<h2>");
		html.append("		<ul>");
		html.append("			<li>");
		if (null == user) {
			html.append("<i><a href='/account/login.do/>登陆</a>|<a href='/account/registration.do'>注册</a></i>");
		}
		if (null != user) {
			html.append("<i><em>hi，");
			html.append(StringUtils.isBlank(user.getRealname()) ? user.getUsername() : user.getRealname());
			html.append("</em><a href='javascript:;'>退出</a>|</i>");

		}
		html.append("			</li>");
		html.append("			<li><a href='/account/order/myOrderForm.do'>我的订单</a><span class='line'></span></li>");
		html.append("			<li><a href='/cart.do'>购物车</a><span class='line'></span></li>");
		html.append("			<li>");
		html.append("				<a href='javascript:;'>服务中心<span class='arrow-down-logo'></span></a><span class='line'></span>");
		html.append("				<div class='servicexiala' style='display:none;'>");
		html.append("					<a href='javascript:;'>购物指南</a><a href='javascript:;'>售后服务</a><a href='javascript:;'>支付方式</a><a href='javascript:;'>关于我们</a>");
		html.append("				</div>");
		html.append("			</li>");
		html.append("			<li>");
		html.append("				<a href='javascript:;' class='menuyes'>微信公众号");
		html.append("				<span class='arrow-down-logo'></span></a><span class='line'></span>");
		html.append("				<div class='weixinxiala' style='display: none'>");
		html.append("					<p>飞券网：jysmall</p>");
		html.append("					<P>");
		html.append("						<img src='/images/weixin.jpg' />");
		html.append("					</P>");
		html.append("					<p>扫一扫，关注金钥匙公众号</p>");
		html.append("				</div></li>");
		html.append("		</ul>");
		html.append("	</h2>");
		html.append("</div>");
		html.append("</div>");
		html.append("<div class='wrapper'>");
		// top广告
		html.append("<div class='top_adv'>");
		html.append("	<a href='javascript:;'><img src='/images/index_top_adv.jpg' /></a>");
		html.append("</div>");
		html.append("<div class='top_center'>");
		html.append("	<h1>");
		html.append("		<a href='/'><img src='/images/logo.png' /></a>");
		html.append("	</h1>");
		// top搜索
		html.append("	<div class='topsearch'>");
		html.append("		<div class='topsearch_xxk'>");
		html.append("			<a href='javascript:;' name='pord' class='xxkcur'>搜商品</a>");
		html.append("			<a href='javascript:;' name=;stor;>搜商铺</a>");
		html.append("			<div class='clear'></div>");
		html.append("		</div>");
		html.append("		<input id='keywork' name='keywork' onkeydown='javascript:if(event.keyCode==13) search();' class='inputsearch' placeholder='请输入搜索文字'/>");
		html.append("		<input name='' type='submit' value='搜索' class='inputanniu' onclick='search();' />");
		html.append("		<p><a href='javascript:;'>酸奶心情吧</a><a href='javascript:;'>我家家宴</a><a href='javascript:;'>爆米花</a><a href='javascript:;'>KTV</a></p>");
		// top搜索-e
		html.append("	</div>");
		html.append("	<div id='myCart'>");
		html.append("		<div class='indextop_jiesuan_cishu'>0</div>");
		html.append("		<div class='indextop_jiesuan'><a href='javascript:void(0)'>去购物车兑换</a></div>");
		html.append("		<div class='indexgouwuche_tanchu' style='display:none;'><ul id='cartDet'></ul></div>");
		html.append("	</div>");
		html.append("</div>");
		html.append("</div>");
		// 主导航条
		html.append("<div class='top_nav'>");
		html.append("<div class='wrapper'>");
		html.append("	<h2>");
		html.append("		<a href='javascript:;' class='bg2'>商家入驻</a>");
		html.append("	</h2>");
		html.append("	<h1>");
		html.append("		<a href='/' class='top_nav_current'>首页</a>");
		/*
		 * <!--<a href="javascript:;">换购</a> <a href="javascript:;">VI专区</a> <a
		 * href="javascript:;">理财</a>-->
		 */
		html.append("		<div class='clear'></div>");
		html.append("	</h1>");
		html.append("	<div class='clear'></div>");
		html.append("</div>");
		html.append("</div>");
		HtmlUtil.writerHtml(response, html.toString());
	}

	/**
	 * 前端页面底部 参考 /WEB-INF/jsp/account/uFooter.jsp
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/managermall/account/footer")
	public void footer(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// UserInfo user = (UserInfo)
		// session.getAttribute(ConstantsConfigurer.getUser());
		StringBuffer html = new StringBuffer("");
		html.append("<div class='footer'>");
		html.append("	<div class='bottom1'>");
		html.append("		<ul>");
		html.append("			<li>");
		html.append("				<h1 class='bottom_navbg1'>购物指南</h1>");
		html.append("				<p>");
		html.append("					<a href='#'>购物流程</a>");
		html.append("				</p>");
		html.append("				<p>");
		html.append("					<a href='#'>商城规则</a>");
		html.append("				</p>");
		html.append("				<p>");
		html.append("					<a href='#'>会员介绍</a>");
		html.append("				</p>");
		html.append("				<p>");
		html.append("					<a href='#'>常见问题</a>");
		html.append("				</p>");
		html.append("			</li>");
		html.append("			<li>");
		html.append("				<h1 class='bottom_navbg2'>付款方式</h1>");
		html.append("				<p>");
		html.append("					<a href='#'>支付平台</a>");
		html.append("				</p>");
		html.append("				<p>");
		html.append("					<a href='#'>换购抵用</a>");
		html.append("				</p>");
		html.append("			</li>");
		html.append("			<li>");
		html.append("				<h1 class='bottom_navbg3'>");
		html.append("					<a href='#'>物流配送</a>");
		html.append("				</h1>");
		html.append("				<h1 class='bottom_navbg4'>");
		html.append("					<a href='#'>售后服务</a>");
		html.append("				</h1>");
		html.append("				<h1 class='bottom_navbg5'>");
		html.append("					<a href='#'>法律声明</a>");
		html.append("				</h1>");
		html.append("			</li>");
		html.append("			<li>");
		html.append("				<h1 class='bottom_navbg6'>关于我们</h1>");
		html.append("				<p>");
		html.append("					<a href='#'>公司简介</a>");
		html.append("				</p>");
		html.append("				<p>");
		html.append("					<a href='#'>企业文化</a>");
		html.append("				</p>");
		html.append("				<p>");
		html.append("					<a href='#'>企业历程</a>");
		html.append("				</p>");
		html.append("				<p>");
		html.append("					<a href='#'>广告服务</a>");
		html.append("				</p>");
		html.append("				<p>");
		html.append("					<a href='#'>联系我们</a>");
		html.append("				</p>");
		html.append("			</li>");
		html.append("			<div class='bottom_service'>");
		html.append("				<h3>客服热线</h3>");
		html.append("				<h2>400-888-0683</h2>");
		html.append("				<p>E-mail: service@jysp2p.com</p>");
		html.append("				<p>(周一至周五，09:00-18:00)</p>");
		html.append("			</div>");
		html.append("			<div class='clear'></div>");
		html.append("		</ul>");
		html.append("	</div>");
		html.append("</div>");
		html.append("<div class='bottom'>");
		html.append("	<h1>");
		html.append("		<span>");
		html.append("			<a href='#'>关于我们</a>|");
		html.append("			<a href='#'>联系我们</a>|");
		html.append("			<a href='#'>诚聘英才</a>|");
		html.append("			<a href='#'>供应商入驻</a>|");
		html.append("			<a href='#'>友情链接</a>|");
		html.append("			<a href='#'>法律申明</a>");
		html.append("		</span>");
		html.append("		<a href='#'>首页</a>");
		/*
		 * <!--<a href="#">换购</a> <a href="#">VIP专区</a> <a href="#">理财</a>-->
		 */
		html.append("	</h1>");
		html.append("	<h2>");
		html.append("		粤ICP备14023008号-1 版权所有©2013-2015 深圳市前海金钥匙电子商务有限公司 <a href='#'>站长统计</a>");
		html.append("	</h2>");
		html.append("	<ul>");
		html.append("		<li><img src='/images/bottom1.jpg' /></li>");
		html.append("		<li><img src='/images/bottom1.jpg' /></li>");
		html.append("		<li><img src='/images/bottom1.jpg' /></li>");
		html.append("		<li><img src='/images/bottom1.jpg' /></li>");
		html.append("		<li><img src='/images/bottom1.jpg' /></li>");
		html.append("		<li><img src='/images/bottom1.jpg' /></li>");
		html.append("		<li><img src='/images/bottom1.jpg' /></li>");
		html.append("		<div class='clear'></div>");
		html.append("	</ul>");
		html.append("</div>");
		HtmlUtil.writerHtml(response, html.toString());
	}

	/**
	 * 会员中心左边菜单 参考/WEB-INF/jsp/account/navigation.jsp
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/managermall/account/left")
	public void left(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// UserInfo user = (UserInfo)
		// session.getAttribute(ConstantsConfigurer.getUser());
		StringBuffer html = new StringBuffer("");
		HtmlUtil.writerHtml(response, html.toString());
	}

}
