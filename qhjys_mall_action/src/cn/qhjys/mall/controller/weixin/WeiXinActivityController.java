package cn.qhjys.mall.controller.weixin;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.CouponsInfo;
import cn.qhjys.mall.entity.StoreLottery;
import cn.qhjys.mall.entity.WeixinUserinfo;
import cn.qhjys.mall.service.WeixinUserinfoService;
import cn.qhjys.mall.service.system.ActivityService;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.vo.system.CouponsVirifyVo;
import cn.qhjys.mall.vo.system.StoreLotteryVo;
import cn.qhjys.mall.weixin.util.GetWeiXinCode;
import cn.qhjys.mall.weixin.util.WeiXinUtil;

/**
 * 
 * 类名称:WeiXinActivityController
 * 类描述:微信活动
 * 创建人:JiangXiaoPing
 * 创建时间:2016年4月26日下午4:26:15
 * 修改人
 * 修改时间:
 * 修改备注:
 * 
 */
@Controller
@RequestMapping("/wxActivity")
public class WeiXinActivityController  extends Base{
	@Autowired 
	private ActivityService  activityService;
	@Autowired
	private WeixinUserinfoService weixinUserinfoService;
	
	//活动的列表
	@RequestMapping("/wxProductlist")
	public ModelAndView  wxProductlist(Integer fromwhere) throws Exception{
		ModelAndView view = new ModelAndView("/weixin/activity/wxProductlist");
		List<StoreLotteryVo> list  = activityService.getStoreLotteryVoByStatus(1);
		view.addObject("list",list);
		if (fromwhere == null) {
			fromwhere = -1;
		}
		view.addObject("fromwhere",fromwhere);
		return view;
		
	}
	
	
	
	
	
	
	//用户优惠券查询
	/**
	 * 发起微信授权
	 * @return 回调到砍价页面
	 */
	@RequestMapping("/mycouponsInfo")
	public String getBargain(HttpSession session){
		String openId = (String) session.getAttribute("openId");
		String request ;
		if (StringUtils.isEmpty(openId) || "NULL".equals(openId.toUpperCase())) {
			String url = ConstantsConfigurer.getProperty("get_couponsInfo_url");
			 request = GetWeiXinCode.getCodeRequestByUserInfo(ConstantsConfigurer.getProperty("weixin_appid"),url);
		}else{
			 request = ConstantsConfigurer.getProperty("get_couponsInfo_url");
		}
		return "redirect:"+request;
	}
	
	@RequestMapping ("/getmycouponsInfo")
	public ModelAndView  getmycouponsInfo(
			HttpSession session,HttpServletRequest request,String code
			) throws Exception{
		
		String url = request.getScheme()+"://"+ request.getServerName()+request.getRequestURI()+(StringUtils.isEmpty(request.getQueryString())?"":"?"+request.getQueryString());
		/*logger.info("--url-"+url);*/
		String openId = (String) session.getAttribute("openId");
		if (StringUtils.isEmpty(openId) || "NULL".equals(openId.toUpperCase())) {
			String currentOpenIdurl = GetWeiXinCode.getCurrentOpenId(code,ConstantsConfigurer.getProperty("weixin_appid"),ConstantsConfigurer.getProperty("weixin_appsecret"));
			JSONObject httpRequest = WeiXinUtil.httpRequest(currentOpenIdurl, "GET", null);
			 openId = (String) httpRequest.get("openid");
			String access_token = (String) httpRequest.get("access_token");
			String currentUserInfourl = GetWeiXinCode.getCurrentUserInfo(access_token,openId);
			JSONObject httpRequest1 = WeiXinUtil.httpRequest(currentUserInfourl, "GET", null);
			logger.info("---httpRequest1-------"+httpRequest1);
			String nickname = httpRequest1.optString("nickname");
			String headimgurl = httpRequest1.optString("headimgurl");
			session.setAttribute("nickname", nickname);
			session.setAttribute("headimgurl", headimgurl);
			session.setAttribute("openId", openId);
		}
		ModelAndView view = new ModelAndView("/weixin/activity/myCouponsInfo");
		List<CouponsInfo> list=getUserCouponsInfo(openId);
		List<StoreLotteryVo> lotterys = activityService.getStoreLotteryVoByStatus(1);
		view.addObject("lotterys",lotterys);
		view.addObject("list",list);
		return view;
		
	}
	
	public List<CouponsInfo> getUserCouponsInfo(String openid) throws Exception{
		 return activityService.getCouponsInfoByOpenId(openid);
	}
	
	//商家扫码验证
	/**
	 *  http://jysmaill.ngrok.natapp.cn/wxActivity/businessesScan.do?storeId=storeId1&couponsId=couponsId2
	 * businessesScan
	 * @param storeopenId 商家的openId
	 * @param couponsId 优惠卷ID  
	 * @return
	 * @throws Exception 
	 * 授权
	 */
	@RequestMapping("/businessesScan")
	public Object businessesScan(
			HttpSession session,
			@RequestParam(value = "storeId",required = false)String storeId,
			@RequestParam(value = "couponsId",required = true)Long couponsId
			) throws Exception{
			String url = ConstantsConfigurer.getProperty("get_businessesScan_url")+"?couponsId="+couponsId;
			String request = GetWeiXinCode.getCodeRequestByBase(ConstantsConfigurer.getProperty("weixin_appid"),url);
		return "redirect:"+request;
	}
	
	@RequestMapping ("/getbusinessesScan")
	public Object  getbusinessesScan(
			HttpSession session,HttpServletRequest request,String code,Long couponsId
			) throws Exception{
		
			String url = request.getScheme()+"://"+ request.getServerName()+request.getRequestURI()+(StringUtils.isEmpty(request.getQueryString())?"":"?"+request.getQueryString());
			/*logger.info("--url-"+url);*/
			String currentOpenIdurl = GetWeiXinCode.getCurrentOpenId(code,ConstantsConfigurer.getProperty("weixin_appid"),ConstantsConfigurer.getProperty("weixin_appsecret"));
			JSONObject httpRequest = WeiXinUtil.httpRequest(currentOpenIdurl, "GET", null);
			String openId = (String) httpRequest.get("openid");
			ModelAndView view = new ModelAndView("/weixin/activity/msg");
			//通过 storeId获取到微信WeixinUserinfo
		   /* WeixinUserinfo WeixinUserinfo=   weixinUserinfoService.getWeixinUserinfoByStoreId(storeId); 
			if(null!=WeixinUserinfo.getStoreId()&&WeixinUserinfo.getStoreId()>0&&!StringUtils.isEmpty(WeixinUserinfo.getStoreId())&&!StringUtils.isEmpty(WeixinUserinfo.getOpenId())&&WeixinUserinfo.getOpenId().equals(openId)){//是这个店铺的
*/			
		    WeixinUserinfo WeixinUserinfo=   weixinUserinfoService.getWeixinUserinfo(openId);
		    logger.info("--openId-"+openId);
		    logger.info("--WeixinUserinfo-"+WeixinUserinfo);
		    CouponsInfo info =  activityService.getCouponsInfoById(couponsId);
		    Boolean IsTime =activityService.updateIsTime( info.getLotteryId(),info.getRank());
		    if(IsTime||info.getEndTime().getTime()< System.currentTimeMillis()){
			    StoreLottery lottery = activityService.getStoreLotteryById(info.getLotteryId());
			    logger.info("--WeixinUserinfo.getStoreId()-"+WeixinUserinfo.getStoreId());
			    logger.info("--null!=WeixinUserinfo-"+(null!=WeixinUserinfo));
			    logger.info("-null!=WeixinUserinfo.getStoreId()-"+(null!=WeixinUserinfo.getStoreId()));
			    logger.info("--lottery.getStoreId()-"+lottery.getStoreId());
			    logger.info("--WeixinUserinfo.getStoreId().equals(lottery.getStoreId())-"+WeixinUserinfo.getStoreId().equals(lottery.getStoreId()));
			    if(null!=WeixinUserinfo&&null!=WeixinUserinfo.getStoreId()&&(WeixinUserinfo.getStoreId().equals(lottery.getStoreId()))){
			    	logger.info("---couponsId--"+couponsId);
			    	Boolean bl  = 	activityService.insertCouponsByStoreOpenIdAndCouponsId(lottery.getStoreId(),couponsId);
				   /* Boolean bl  = 	activityService.getCouponsByStoreOpenIdAndCouponsId(storeId,couponsId);*/
						if(bl){//状态为0 修改成1.添加记录
							//验证成功
							view.setViewName("redirect:/wxActivity/businesseslist.do?openId="+openId);
						}else{
							//已经消费过了
							view.addObject("msg","已经消费过了");
						}
			    }else{
			    	//商家未绑定
			    	view.addObject("msg","商家未绑定");
			    }
			   }else{
				 //校验时间不对
			    	view.addObject("msg","消费卷不在使用期限");
			   }
		    
			return view;
		}
	
	//商家验证记录查询
	
	@RequestMapping("/businesseslist")
	public ModelAndView businesseslist (
			@RequestParam(value = "openId",required = true)String openId
			) throws Exception{
		List<CouponsVirifyVo>	list1=activityService.getCouponsVirifyVoByOpenId(openId);
		ModelAndView view = new ModelAndView("/weixin/activity/businesseslist");
		List<CouponsVirifyVo>	list = new ArrayList<>();
		for (CouponsVirifyVo cvf:list1){
			cvf.setNickName(URLDecoder.decode(cvf.getNickName()));
			list.add(cvf);
		}
		view.addObject("list",list);
		return view;
	}
	
}	
	
	
	
	
	
	
	
	
	
	
	
	
