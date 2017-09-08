package cn.qhjys.mall.controller.system;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.github.pagehelper.Page;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.CouponsVirify;
import cn.qhjys.mall.entity.LotteryDish;
import cn.qhjys.mall.entity.StoreLottery;
import cn.qhjys.mall.service.system.ActivityService;
import cn.qhjys.mall.util.SessionUtil;

/**
 * 
 * 类名称:ActivityController
 * 类描述:抽奖活动管理
 * 创建人:JiangXiaoPing
 * 创建时间:2016年4月23日下午2:26:42
 * 修改人
 * 修改时间:
 * 修改备注:
 */
@Controller
@RequestMapping("/managermall/systemmall/activity")
public class ActivityController extends Base{

	@Autowired
	private ActivityService activityService;
	
	
	//抽奖活动列表
	@RequestMapping("/list")
	public ModelAndView listRebate(
			
			@RequestParam(value = "page",required =false)Integer pageNum ,Integer pageSize) throws Exception{
		ModelAndView view = new ModelAndView("/system/activity/activity_list");
		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		Page<StoreLottery> page = activityService.queryStoreLotteryByList(pageNum, pageSize);
		view.addObject("page", page);
		return view;
	}
	//添加活动
	@RequestMapping("/addpage")
	public ModelAndView  addpage(
			HttpSession session
			) throws Exception{
		ModelAndView view = new ModelAndView("/system/activity/activity_add");
		SessionUtil.setSession(session, "activityAddToken", UUID.randomUUID().toString());
		return view;
	}
	
	@RequestMapping("/add")
	public  Object add(
			HttpSession session,
			@RequestParam(value = "storeId",required  = true) Long storeId,
			@RequestParam(value = "lotteryName",required  = true) String lotteryName,
			@RequestParam(value = "lotteryContent",required  = true) String lotteryContent,
			@RequestParam(value = "startDate",required  = true) String startDate,
			@RequestParam(value = "endDate",required  = true) String endDate,
			@RequestParam(value = "status",required  = true) Integer status,
			@RequestParam(value = "token",required  = true) String token
			) throws Exception{
		String stringToken = SessionUtil.getSession(session, "activityAddToken").toString();
		SessionUtil.removeSession(session, "activityAddToken");
		if(null==stringToken||!stringToken.equals(token)){
			return super.goUrl("/managermall/systemmall/activity/list.do", "请不要重复提交");
		}
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
		
		int i = activityService.insertStoreLotteryBy(storeId,lotteryName,lotteryContent,format.parse(startDate),format.parse(endDate),status);
		if(i==1){
			return super.goUrl("/managermall/systemmall/activity/list.do", "添加成功");
		}else{
			return super.goUrl("/managermall/systemmall/activity/list.do", "添加失败");
		}
	}
	
	//修改活动
	@RequestMapping("/updatepage")
	public ModelAndView  addupdate(
			@RequestParam(value="id",required = true)Long id,
			HttpSession session
			) throws Exception{
		ModelAndView view = new ModelAndView("/system/activity/activity_update");
		SessionUtil.setSession(session, "activityUpdateToken", UUID.randomUUID().toString());
		StoreLottery storeLottery = activityService.getStoreLotteryById(id);
		view.addObject("storeLottery",storeLottery);
		return view;
	}
	
	@RequestMapping("/update")
	public  Object update(
			HttpSession session,
			@RequestParam(value = "id",required  = true) Long id,
			@RequestParam(value = "storeId",required  = true) Long storeId,
			@RequestParam(value = "lotteryName",required  = true) String lotteryName,
			@RequestParam(value = "lotteryContent",required  = true) String lotteryContent,
			@RequestParam(value = "startDate",required  = true) String startDate,
			@RequestParam(value = "endDate",required  = true) String endDate,
			@RequestParam(value = "status",required  = true) Integer status,
			@RequestParam(value = "token",required  = true) String token
			) throws Exception{
		
		String stringToken = SessionUtil.getSession(session, "activityUpdateToken").toString();
		SessionUtil.removeSession(session, "activityUpdateToken");
		if(null==stringToken||!stringToken.equals(token)){
			return super.goUrl("/managermall/systemmall/activity/list.do", "请不要重复提交");
		}
		if(status==1){
			//判断 是否有4个必须奖项...
			Boolean boolean1 = lotteryDishStatus(id);
			if(!boolean1){
				return super.goUrl("/managermall/systemmall/activity/list.do", "修改失败,请检查菜品是否符合活动条件.4个唯一的奖项");
			}
		}
		
		
		
		
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
		
		int i = activityService.updateStoreLotteryBy(id,storeId,lotteryName,lotteryContent,format.parse(startDate),format.parse(endDate),status);
		if(i==1){
			return super.goUrl("/managermall/systemmall/activity/list.do", "修改成功");
		}else{
			return super.goUrl("/managermall/systemmall/activity/list.do", "修改失败");
		}
	}
	
	
	private Boolean lotteryDishStatus(Long id) throws Exception {
		List<LotteryDish> lotteryDishs = activityService.getLotteryDishByLotteryId(id);
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < lotteryDishs.size(); i++) {
			LotteryDish	lotteryDish  = lotteryDishs.get(i);
			buffer.append(lotteryDish.getRankLevel().toString());
		}
		String string = buffer.toString();
		if(string.length()!=4||!string.contains("1")||!string.contains("2")||!string.contains("3")||!string.contains("4")){
			return false;
		}
		return true;
	}
	//--------------------------------------------------------------
	//抽奖活动菜品列表  dish
	@RequestMapping("/dish_list")
	public ModelAndView dish_list(
			
			@RequestParam(value = "page",required =false)Integer pageNum ,Integer pageSize) throws Exception{
		ModelAndView view = new ModelAndView("/system/activity/dish_list");
		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		Page<LotteryDish> page = activityService.queryLotteryDishByList(pageNum, pageSize);
		List<StoreLottery> lotterys = activityService.getStoreLotteryByStatus(null);
		view.addObject("lotterys",lotterys);
		view.addObject("page", page);
		return view;
	}
	
	
	//添加活动菜品
	@RequestMapping("/add_dish_page")
	public ModelAndView add_dish_page(
			HttpSession session
			) throws Exception{
		SessionUtil.setSession(session, "addDishPageToken",UUID.randomUUID().toString());
		ModelAndView view = new ModelAndView("/system/activity/add_dish_page");
		//List<StoreLottery> lotterys = activityService.getStoreLotteryByStatus(1);
		List<StoreLottery> lotterys = activityService.getStoreLotteryByStatus(null);
		view.addObject("lotterys",lotterys);
		return view;
	}
	@RequestMapping("/add_dish")
	public Object add_dish(
			@RequestParam(value="lotteryId",required = true)Long  lotteryId,
			@RequestParam(value="token",required = true)String token,
			@RequestParam(value="limitNum",required = true)Integer limitNum,
			@RequestParam(value="rankLevel",required = true)Integer rankLevel,
			@RequestParam(value="dishName",required = true)String dishName,
			@RequestParam(value="dishImage",required = true)String dishImage,
			@RequestParam(value="dishInfo",required = true)String dishInfo,
			@RequestParam(value="beginTime",required = true)String beginTime,
			HttpSession session
			) throws Exception{
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
		 String stringToken = SessionUtil.getSession(session, "addDishPageToken").toString();
			SessionUtil.removeSession(session, "addDishPageToken");
			if(null==stringToken||!stringToken.equals(token)){
				return super.goUrl("/managermall/systemmall/activity/dish_list.do", "请不要重复提交");
			}
		 
		int i = activityService.insertLotteryDishBy(lotteryId,rankLevel,dishName,dishImage,dishInfo,limitNum,format.parse(beginTime));
		if(i==1){
			return super.goUrl("/managermall/systemmall/activity/dish_list.do", "添加成功");
		}else{
			return super.goUrl("/managermall/systemmall/activity/dish_list.do", "添加失败");
		}
	}
	
	
	//修改活动菜品
	@RequestMapping("/update_dish_page")
	public ModelAndView update_dish_page(
			@RequestParam(value="id",required = true)Long id,
			HttpSession session
			) throws Exception{
		
		SessionUtil.setSession(session, "update_dish_page",UUID.randomUUID().toString());
		ModelAndView view = new ModelAndView("/system/activity/update_dish_page");
		LotteryDish dish = activityService.getLotteryDishById(id);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
		if (dish.getBeginTime() != null) {
			String dater= format.format(dish.getBeginTime());
			 view.addObject("dater",dater);
		}
		view.addObject("dish",dish);
		
		//List<StoreLottery> lotterys = activityService.getStoreLotteryByStatus(1);
		List<StoreLottery> lotterys = activityService.getStoreLotteryByStatus(null);
		view.addObject("lotterys",lotterys);
		return view;
	}
	
	@RequestMapping("/update_dish")
	public Object update_dish(
			@RequestParam(value="id",required = true)Long  id,
			@RequestParam(value="lotteryId",required = true)Long  lotteryId,
			@RequestParam(value="token",required = true)String token,
			@RequestParam(value="limitNum",required = true)Integer limitNum,
			@RequestParam(value="rankLevel",required = true)Integer rankLevel,
			@RequestParam(value="dishName",required = true)String dishName,
			@RequestParam(value="dishImage",required = true)String dishImage,
			@RequestParam(value="dishInfo",required = true)String dishInfo,
			@RequestParam(value="beginTime",required = true)String beginTime,
			HttpSession session
			) throws Exception{
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
		 String stringToken = SessionUtil.getSession(session, "update_dish_page").toString();
			SessionUtil.removeSession(session, "update_dish_page");
			if(null==stringToken||!stringToken.equals(token)){
				return super.goUrl("/managermall/systemmall/activity/dish_list.do", "请不要重复提交");
			}
		 
		int i = activityService.updateLotteryDishBy(id,lotteryId,rankLevel,dishName,dishImage,dishInfo,limitNum,format.parse(beginTime));
		if(i==1){
			return super.goUrl("/managermall/systemmall/activity/dish_list.do", "修改成功");
		}else{
			return super.goUrl("/managermall/systemmall/activity/dish_list.do", "修改失败");
		}
	}
	//----------------------------end----------------------------------
	
	
	//---------------------------------活动商家验证优惠卷
	
	@RequestMapping("/virify_list")
	public ModelAndView virify_list(
			
			@RequestParam(value = "page",required =false)Integer pageNum ,Integer pageSize) throws Exception{
		ModelAndView view = new ModelAndView("/system/activity/virify_list");
		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		Page<CouponsVirify> page = activityService.queryVirifyByList(pageNum, pageSize);
		//List<StoreLotteryVo> storeLotteryVoByStatus = activityService.getStoreLotteryVoByStatus(1);
		view.addObject("page", page);
		return view;
	}
	
}
