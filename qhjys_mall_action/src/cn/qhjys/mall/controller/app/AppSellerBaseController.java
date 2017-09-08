package cn.qhjys.mall.controller.app;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.AdInfo;
import cn.qhjys.mall.entity.AppVersion;
import cn.qhjys.mall.entity.CashAccount;
import cn.qhjys.mall.entity.CashLog;
import cn.qhjys.mall.entity.CashSaveWithdraw;
import cn.qhjys.mall.entity.Feedback;
import cn.qhjys.mall.entity.FqCuisine;
import cn.qhjys.mall.entity.FqFinancialSupport;
import cn.qhjys.mall.entity.FqLocation;
import cn.qhjys.mall.entity.FqOrder;
import cn.qhjys.mall.entity.FqOrderDetail;
import cn.qhjys.mall.entity.FqProduct;
import cn.qhjys.mall.entity.FqProductType;
import cn.qhjys.mall.entity.FqPushInfo;
import cn.qhjys.mall.entity.FqSellerStatement;
import cn.qhjys.mall.entity.FqServiceApply;
import cn.qhjys.mall.entity.FqStore;
import cn.qhjys.mall.entity.FqStoreApply;
import cn.qhjys.mall.entity.FqStoreCheck;
import cn.qhjys.mall.entity.FqStoreRate;
import cn.qhjys.mall.entity.FqThirdPay;
import cn.qhjys.mall.entity.FqUserInfo;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.entity.StoreCheck;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.service.AdInfoService;
import cn.qhjys.mall.service.CashAccountService;
import cn.qhjys.mall.service.CashLogService;
import cn.qhjys.mall.service.CashSaveWithdrawService;
import cn.qhjys.mall.service.FeedbackService;
import cn.qhjys.mall.service.FqPushInfoService;
import cn.qhjys.mall.service.FqServiceApplyService;
import cn.qhjys.mall.service.FqThirdPayService;
import cn.qhjys.mall.service.MessageInfoService;
import cn.qhjys.mall.service.PhoneCodeService;
import cn.qhjys.mall.service.SellerAssignedService;
import cn.qhjys.mall.service.SellerService;
import cn.qhjys.mall.service.StoreService;
import cn.qhjys.mall.service.VolumeService;
import cn.qhjys.mall.service.app.SellerUserCountService;
import cn.qhjys.mall.service.fq.FqOrderService;
import cn.qhjys.mall.service.fq.FqProductService;
import cn.qhjys.mall.service.fq.FqSellerStatementService;
import cn.qhjys.mall.service.fq.FqStoreApplyService;
import cn.qhjys.mall.service.fq.FqStoreCheckService;
import cn.qhjys.mall.service.fq.FqStoreService;
import cn.qhjys.mall.service.fq.FqUserInfoService;
import cn.qhjys.mall.service.fq.SellerWXService;
import cn.qhjys.mall.service.seller.SellerVoService;
import cn.qhjys.mall.service.system.AppVersionService;
import cn.qhjys.mall.util.AppResult;
import cn.qhjys.mall.util.Base64;
import cn.qhjys.mall.util.Base64Util;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.DateUtils;
import cn.qhjys.mall.util.FileState;
import cn.qhjys.mall.util.FileUtil;
import cn.qhjys.mall.util.HtmlUtil;
import cn.qhjys.mall.util.MessageUtil;
import cn.qhjys.mall.util.ServletUtils;
import cn.qhjys.mall.util.SessionUtil;
import cn.qhjys.mall.vo.AdVo;
import cn.qhjys.mall.vo.SellerUserCountVo;
import cn.qhjys.mall.vo.SellerUserInfoVo;
import cn.qhjys.mall.vo.seller.SellerBankVo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;

@Controller
@RequestMapping("/appSeller")
public class AppSellerBaseController extends Base{
	
	@Autowired
	private SellerService sellerService;
	@Autowired
	private CashAccountService cashAccountService;
	@Autowired
	private SellerVoService sellerVoService;
	@Autowired
	private VolumeService volumeService;
	@Autowired
	private MessageInfoService messageService;
	
	@Autowired
	private StoreService storeService;
	@Autowired
	private FeedbackService feedbackService;
	@Autowired
	private SellerAssignedService sellerAssignedService;
	@Autowired
	private PhoneCodeService phoneCodeService;
	@Autowired
	private FqSellerStatementService fqSellerStatementService;
	@Autowired
	private CashSaveWithdrawService cashSaveWithdrawService;
	@Autowired
	private FqThirdPayService fqThirdPayService;
	@Autowired
	private SellerUserCountService sellerUserCountService;
	@Autowired
	private FqServiceApplyService fqServiceApplyService;
	@Autowired
	private FqPushInfoService fqPushInfoService;
	@Autowired
	private AdInfoService adInfoService;
	@Autowired
	private FqUserInfoService fqUserInfoService;
	@Autowired
	private CashLogService cashLogService;
	
	@Autowired 
	private FqStoreService fqStoreService;
	@Autowired
	private SellerWXService sellerWXService;
	@Autowired
	private FqProductService fqProductService;
	@Autowired
	private FqOrderService fqOrderService;
	@Autowired
	private FqStoreApplyService fqStoreApplyService;
	@Autowired
	private FqStoreCheckService fqStoreCheckService;
	@Autowired
	private AppVersionService appVersionService;
	
	@RequestMapping("/fileUpload")
	@ResponseBody
	public AppResult fileUpload(HttpSession session,Long sellerId,String file,HttpServletResponse response, HttpServletRequest request) throws Exception{
		AppResult result = new AppResult();
		verifyUser(response, result, sellerId);
		if (file.length()<10) {
			 result.setFlag(0);
             result.setReason("图片异常");
             return result; 
		}
		// 定义变量存储图片地址
        String imagePath = "";
        String path = session.getServletContext().getRealPath("") + FileUtil.path +"app/"+sellerId+"/";
        JSONObject json = new JSONObject();
        String src="";
        // 接收图片数据   （base64）
        try{
              // 定义图片输入流
        	path = FileUtil.getDoPath(path);
        	FileUtil.mkDir(path);
        	String [] files = file.split(",");
        	for (int i = 0; i < files.length; i++) {
        		imagePath="";
        		String fileName = FileUtil.getNumberName("a.png");
        		Base64 base = new Base64();
                byte[] decode = base.getBytesBASE64(files[i]);
                // 图片输出路径
                imagePath = path+fileName;
                // 写文件
                InputStream fin = new ByteArrayInputStream(decode);
                // 定义图片输出流
                FileOutputStream fout=new FileOutputStream(imagePath);
                byte[] b=new byte[1024];
                int length=0;
                while((length=fin.read(b))>0){
                    
                    fout.write(b, 0, length);
                }
                fin.close();
                fout.close();
                if (i>0) {
                	src +=",";
				}
                src +=FileUtil.path +"app/"+sellerId+"/"+fileName;
               
			}
            // 将base64 转 字节数组
            // 关闭数据流
          
            result.setFlag(1);
            result.setReason("上传成功");
            if (!StringUtils.isEmpty(src)) {
            	 json.put("src", src);
			}
            result.setData(json);
        }catch(Exception e){
        	 result.setFlag(0);
             result.setReason("上传失败");
            e.printStackTrace();
        }
		return result; 
	}
	
	@RequestMapping("/appFileUpload")
	public void appFileUpload(HttpSession session,Long sellerId,String file, MultipartHttpServletRequest multipartRequest,
			HttpServletResponse response,String delPath) throws Exception {
		AppResult result = new AppResult();
//		verifyUser(response, result, sellerId);
		logger.info("-------进入上传-------------");
		JSONObject json = new JSONObject();
		String match = "app/", path = session.getServletContext().getRealPath("") + FileUtil.path;
		path += match+sellerId;
		logger.info("-------path-------------"+path);
		logger.info("-------file-------------"+file);
		List<MultipartFile> files = multipartRequest.getFiles("file");
		System.out.println(files.size());
		MultipartFile file1 = multipartRequest.getFile("file");
		if (file1.isEmpty()) {
			result.setFlag(0);
			result.setReason(FileState.UPLOAD_ZEROSIZE.getState());
			
		} else {
			logger.info(file1.getOriginalFilename());
			String fileName = FileUtil.getNumberName(file1.getOriginalFilename());
			try {
				FileState state = FileUtil.upload4Stream(fileName, path, file1.getInputStream());
				// 替换文件时，删除原文件
				if (!BaseUtil.judgeNull(delPath) && delPath.indexOf(match) > -1) {
					String delFile = delPath.substring(delPath.lastIndexOf("/") + 1);
					FileUtil.removeFile(path + delFile);
				}
				result.setFlag(1);
				result.setReason(state.getState());
				json.put("src", FileUtil.path + match + fileName);
				result.setData(json);
				logger.info("文件上传成功, path:" + path + fileName);
			} catch (Exception e) {
				result.setFlag(0);
				result.setReason(FileState.UPLOAD_FAILURE.getState());
				logger.error("保存文件异常!", e);
			}
		}
		HtmlUtil.writerJson(response, json);
	}
	
	@RequestMapping("/pushList")
	@ResponseBody
	public AppResult pushList(HttpServletResponse response, HttpServletRequest request,Long sellerId,Integer pageNum,Integer pageSize,String loginDate,Integer type){
		AppResult result = new AppResult();
		try {
			verifyUser(response, result, sellerId);
			if (pageNum == null || pageNum <0 ) {
				pageNum = 1;
			}
			if (pageSize == null || pageSize <0 ) {
				pageSize = 10;
			}
			Page<FqPushInfo> pushs = fqPushInfoService.queryFqPushInfo(sellerId, pageNum, pageSize,loginDate,type);
			result.setFlag(0);
			result.setReason("查询成功");
			JSONObject json = new JSONObject();
			json.put("list", pushs.getResult());
			json.put("ishasmore",pageNum<5&&pushs.getPages()>pageNum);
			result.setData(json);
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setFlag(1);
			result.setReason("查询失败");
			return result;
		}
		
	}
	
	@RequestMapping("/storeRate")
	@ResponseBody
	public AppResult storeRate(HttpServletResponse response, HttpServletRequest request,Long sellerId){
		AppResult result = new AppResult();
		try {;
			verifyUser(response, result, sellerId);
			StoreInfo store = storeService.queryStoreInfoBySeller(sellerId);
			if (store.getRateId()!= null && store.getRateId()>0) {
				FqStoreRate storeRate = storeService.getFqStoreRateById(store.getRateId());
				result.setFlag(0);
				result.setReason("查询成功");
				JSONObject jsonObj = (JSONObject) JSON.toJSON(storeRate); 
				result.setData(jsonObj);
			}else{
				result.setFlag(0);
				result.setReason("查询成功");
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setFlag(1);
			result.setReason("查询失败");
			return result;
		}
		
	}
	
	
	/**
	 * 是否更新
	 * @param response
	 * @param request
	 * @param version
	 * @param osType IOS  1  android  2
	 * @return
	 */
	@RequestMapping("/appUpdate")
	@ResponseBody
	public AppResult appUpdate(HttpServletResponse response, HttpServletRequest request,String version,Integer osType){
		AppResult result = new AppResult();
		try {
			result.setFlag(0);
			result.setReason("查询成功");
			JSONObject json = new JSONObject();
			if (osType != null) {
			
				AppVersion appversion = appVersionService.getLastAppVersion(osType);
				
			
				if (appversion == null) {
					json.put("isUpdate", 0);
				}else{
					json.put("isUpdate", appversion.getVersionName().compareTo(version)>0?1:0);
				}
			
			}else{
				json.put("isUpdate", 0);
			}
			result.setData(json);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setFlag(1);
			result.setReason("查询失败");
			return result;
		}
		
	}
	
	@RequestMapping("/pushInfo")
	@ResponseBody
	public AppResult pushInfo(HttpServletResponse response, HttpServletRequest request,Long sellerId,String loginDate){
		AppResult result = new AppResult();
		try {
			Page<FqPushInfo> pushs = fqPushInfoService.queryFqPushInfo(sellerId, 1, 5,loginDate,2);
			List<FqPushInfo> list = pushs.getResult();
			String pushinfo = "";
			for (int i = 0; i < list.size(); i++) {
				pushinfo += list.get(i).getTitle()+";       ";
			}
			result.setFlag(0);
			result.setReason("查询成功");
			JSONObject json = new JSONObject();
			json.put("pushinfo", pushinfo);
//			if (sellerId ==1922L || sellerId ==1923L || sellerId ==2524L || sellerId ==2946L ) {
				json.put("kaiquanstatus", 1);
//			}else{
//				json.put("kaiquanstatus", 0);
//			}
			
			result.setData(json);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setFlag(1);
			result.setReason("查询失败");
			return result;
		}
	}
	@RequestMapping("/applyList")
	@ResponseBody
	public AppResult applyList(HttpServletResponse response, HttpServletRequest request,Long sellerId,Integer pageNum,Integer pageSize){
		AppResult result = new AppResult();
		try {
			verifyUser(response, result, sellerId);
			Map<String, Object> map = new HashMap<>();
			map.put("sellerId", sellerId);
			map.put("pageNum", pageNum);
			map.put("pageSize", pageSize);
			Page<FqServiceApply> applys = fqServiceApplyService.queryFqServiceApply(map);
			result.setFlag(0);
			result.setReason("查询成功");
			JSONObject json = new JSONObject();
			json.put("list", applys.getResult());
			json.put("ishasmore", applys.getPages()>applys.getPageNum());
			result.setData(json);
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setFlag(1);
			result.setReason("查询失败");
			return result;
		}
		
	}
	
	@RequestMapping("/insertApply")
	@ResponseBody
	public AppResult insertApply(HttpServletResponse response, HttpServletRequest request,Long sellerId,Integer applyType,
			Integer applyStyleId,String applyStyle,String applyInfo) throws Exception{
		AppResult result = new AppResult();
		verifyUser(response, result, sellerId);
		
		StoreInfo store = storeService.queryStoreInfoBySeller(sellerId);
		if (store == null) {
			result.setFlag(1);
			result.setReason("暂无店铺信息");
		}else if (applyType == null||applyType <1 || applyType >6) {
			result.setFlag(1);
			result.setReason("申请类型错误");
		}else{
			FqServiceApply apply = new FqServiceApply();
			apply.setApplyInfo(applyInfo);
			apply.setApplyStyle(applyStyle);
			apply.setApplyStyleId(applyStyleId);
			apply.setApplyType(applyType);
			apply.setCreateTime(new Date());
			apply.setSellerId(sellerId);
			apply.setStatus(1);
			apply.setStoreId(store.getId());
			apply.setStoreName(store.getName());
			int res = fqServiceApplyService.insertFqServiceApply(apply);
			if (res >0) {
				result.setFlag(0);
				result.setReason("申请成功！");
			}else{
				result.setFlag(1);
				result.setReason("申请失败！");
			}
		}
		return result;	
	}
	
	@RequestMapping("/insertSupport")
	@ResponseBody
	public AppResult insertSupport(HttpServletRequest request,HttpServletResponse response,Long sellerId,String businessImages,
			String idcardImages,String storeImages,String address,String phoneNum,String useInfo)throws Exception{
		AppResult result = new AppResult();
		verifyUser(response, result, sellerId);
		StoreInfo store = storeService.queryStoreInfoBySeller(sellerId);
		if(store==null){
			result.setFlag(1);
			result.setReason("暂无店铺信息");
		}else{
			FqServiceApply apply = new FqServiceApply();
			apply.setApplyInfo("");
			apply.setApplyStyle("金融支持");
			apply.setApplyStyleId(0);
			apply.setApplyType(4);
			apply.setCreateTime(new Date());
			apply.setSellerId(sellerId);
			apply.setStatus(1);
			apply.setStoreId(store.getId());
			apply.setStoreName(store.getName());


			FqFinancialSupport support = new FqFinancialSupport();
			support.setBusinessImages(businessImages);
			support.setIdcardImages(idcardImages);
			support.setStoreImages(storeImages);
			support.setAddress(address);
			support.setPhoneNum(phoneNum);
			support.setUseInfo(useInfo);

			int selective = fqServiceApplyService.insertFinancialSupport(apply,support);
			if(selective>0){
				result.setFlag(0);
				result.setReason("申请成功");
			}else{
				result.setFlag(1);
				result.setReason("申请失败");
			}
		}
		return result;
	}
	
	
	@RequestMapping("/getPayQRcode")
	@ResponseBody
	public AppResult getPayQRcode(HttpServletResponse response, HttpServletRequest request,Long sellerId) throws Exception{
		AppResult result = new AppResult();
		verifyUser(response, result, sellerId);
		if (sellerId == null) {
			result.setFlag(1);
			result.setReason("参数不能为空!");
		}
		StoreInfo store = storeService.queryStoreInfoBySeller(sellerId);
		if (store == null) {
			result.setFlag(1);
			result.setReason("暂无店铺信息");
		}else{
			result.setFlag(0);
			result.setReason("获取信息成功！");
			JSONObject json = new JSONObject();
			json.put("payUrl","http://"+ConstantsConfigurer.getProperty("web_url")+"/wxMall/goRebateDetail.do?storeId="+store.getId());
			result.setData(json);
		}
		return result;	
	}
	
	
	private SellerInfo verifyUser(HttpServletResponse response, AppResult result, Long sellerId) throws Exception{
		if (sellerId == null) {
			result.setFlag(1);
			result.setReason("商户编号错误！");
			this.logger.warn("商户编号错误！");
			HtmlUtil.writerJson(response, result);
			return null;
		}
		SellerInfo seller = sellerService.getSellerById(sellerId);
		if (seller == null) {
			result.setFlag(1);
			result.setReason("商户不存在！");
			this.logger.warn("商户不存在！");
			HtmlUtil.writerJson(response, result);
			return null;
		}
		if (seller.getEnabled() != 1) {
			result.setFlag(1);
			result.setReason("商户已停用！");
			this.logger.warn("商户已停用！");
			HtmlUtil.writerJson(response, result);
		}
		return seller;
	}
	
	@RequestMapping("/login")
	public void login(HttpServletResponse response, HttpServletRequest request, String username, String password) throws Exception{
		AppResult result = new AppResult();
		if (BaseUtil.judgeNull(username)) {
			result.setFlag(1);
			result.setReason("帐号不能为空！");
		}else{
		SellerInfo seller = sellerService.getSeller(username);
		if (seller == null) {
			result.setFlag(1);
			result.setReason("商户不存在！");
			logger.warn("商户不存在！");
		} else if (!seller.getPassword().equals(password)) {
			result.setFlag(1);
			result.setReason("密码错误！");
			logger.warn("密码错误！");
		}else if (seller.getEnabled() != 1) {
			result.setFlag(1);
			result.setReason("商户已停用！");
			this.logger.warn("商户已停用！");
			HtmlUtil.writerJson(response, result);
		}else{
			JSONObject data = new JSONObject();
			CashAccount cash = cashAccountService.queryCashAccount(seller.getId(), null);
			StoreInfo store = sellerVoService.queryStore(seller.getId());
			if (!StringUtils.isEmpty(store.getStatus())&&store.getStatus().equals(2)) {
				if (!StringUtils.isEmpty(store.getImages())) {
					String[] images = store.getImages().split(",");
					if (images!=null && images.length >0) {
						data.put("storeImage", images[0]);
					}
				}
				data.put("sellerId", seller.getId());
				data.put("realName", seller.getRealname());
				data.put("storeName", store.getName());
				data.put("storeId", store.getId());
				data.put("balance", cash.getBalance());
				data.put("channelValidation", store.getTxShow() == 1 ? 1 : store.getChannelValidation());
				data.put("openOrder", store.getOpenOrder());
				FqPushInfo fqPushInfo = new FqPushInfo();
				fqPushInfo.setContent("欢迎登录飞券商家APP！使用APP您可随时随地进行扫码收款、提现和查看流水、非常便捷；同时还可管理会员、申请营销活动、品牌包装、形象设计等服务！赶快去试试吧~");
				fqPushInfo.setPushTime(new Date());
				fqPushInfo.setSellerId(seller.getId());
				fqPushInfo.setTitle("欢迎登录飞券商家APP！");
				fqPushInfo.setType(2);
				fqPushInfoService.insertWelcomeFqPushInfo(fqPushInfo);
				result.setFlag(0);
				result.setReason("登录成功！");
				result.setData(data);
			}else{
				result.setFlag(1);
				result.setReason("店铺信息未审核!");
				logger.warn("店铺信息未审核!");
			}
			
		}
		}
		HtmlUtil.writerJson(response, result);
	}
	@RequestMapping("/getInfoById")
	public void getInfoById(HttpServletResponse response, HttpServletRequest request, Long sellerId) throws Exception{
		AppResult result = new AppResult();
		SellerInfo seller = verifyUser(response, result, sellerId);
			if (seller == null) {
				result.setFlag(1);
				result.setReason("商户不存在！");
				logger.warn("商户不存在！");
			} else if (seller.getEnabled() != 1) {
				result.setFlag(1);
				result.setReason("商户已停用！");
				this.logger.warn("商户已停用！");
				HtmlUtil.writerJson(response, result);
			}else{
				JSONObject data = new JSONObject();
				CashAccount cash = cashAccountService.queryCashAccount(seller.getId(), null);
				StoreInfo store = sellerVoService.queryStore(seller.getId());
				if (!StringUtils.isEmpty(store.getStatus())&&store.getStatus().equals(2)) {
				data.put("sellerId", seller.getId());
				data.put("realName", seller.getRealname());
				data.put("storeName", store.getName());
				data.put("balance", cash.getBalance());
				data.put("storeId", store.getId());
				data.put("channelValidation", store.getTxShow() == 1 ? 1 : store.getChannelValidation());
				data.put("openOrder", store.getOpenOrder());
				result.setFlag(0);
				result.setReason("获取信息成功！");
				result.setData(data);
				}else{
					result.setFlag(1);
					result.setReason("店铺信息未审核!");
					logger.warn("店铺信息未审核!");
				}
			}
		
		HtmlUtil.writerJson(response, result);
	}
	@RequestMapping("/getInfoByUsername")
	public void getInfoByUsername(HttpServletResponse response, HttpServletRequest request, String username) throws Exception{
		AppResult result = new AppResult();
		SellerInfo seller = sellerService.getSeller(username);
		
		if (seller == null) {
			result.setFlag(1);
			result.setReason("商户不存在！");
			logger.warn("商户不存在！");
		} else if (seller.getEnabled() != 1) {
			result.setFlag(1);
			result.setReason("商户已停用！");
			this.logger.warn("商户已停用！");
			HtmlUtil.writerJson(response, result);
		}else{
			JSONObject data = new JSONObject();
			data.put("sellerId", seller.getId());
			data.put("phone", seller.getPhone());
			result.setFlag(0);
			result.setReason("获取信息成功！");
			result.setData(data);
		}
		
		HtmlUtil.writerJson(response, result);
	}
	
	
	@RequestMapping("/sendSMSC")
	@ResponseBody
	public AppResult sendSMSC(HttpServletRequest request,String phone){
		AppResult result = new AppResult();
		if (!BaseUtil.isMobile(phone)) {
			result.setFlag(1);
			result.setReason("请输入正确的手机号码");
			return result;
		}
		boolean canDo;
		try {
			canDo = phoneCodeService.canDoPhoneCode(phone);
			if (!canDo) {
				result.setFlag(1);
				result.setReason("发送短信太过频繁");
				return result;
			}
			String captcha = BaseUtil.numRandom(6);
			Map<String, String> map = new HashMap<String, String>();
			map.put("captcha", captcha);
			boolean a = MessageUtil.SendMessage(phone, map, MessageUtil.COMMCAPTCHA);
			String ip = ServletUtils.getIpAddr(request);
			phoneCodeService.insertPhondCode(phone, captcha, ip);
			result.setFlag(0);
			result.setReason("短信发送成功");
			JSONObject json = new JSONObject();
			json.put("captcha", captcha);
			result.setData(json);
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setFlag(1);
			result.setReason("短信发送失败");
			return result;
		}
		
	}
	
	@RequestMapping("/changePass")
	public void changePass(HttpServletRequest request, HttpServletResponse response,Long sellerId,String newPass){
		AppResult result = new AppResult();
		
		try {
			SellerInfo seller = verifyUser(response, result, sellerId);
			if (seller != null) {
				seller.setPassword(newPass);
				sellerService.updateSellerById(seller);
				result.setFlag(0);
				result.setReason("修改成功！");
			}else{
				result.setFlag(1);
				result.setReason("商户不存在！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setFlag(1);
			result.setReason("商户不存在！");
		}
		HtmlUtil.writerJson(response, result);
	}
	
	
	
	@RequestMapping("/getBank")
	public void getBank(HttpServletRequest request, HttpServletResponse response,Long sellerId) throws Exception{
		AppResult result = new AppResult();
		verifyUser(response, result, sellerId);
		Page<SellerBankVo> bankVos = sellerVoService.querysellerBankPage(sellerId, 1, 1);
		if (bankVos.size()<1) {
			result.setFlag(0);
			result.setReason("查询成功！");
		}else{
			JSONObject json = new JSONObject();
			json.put("bankInfo", bankVos.get(0));
			result.setFlag(0);
			result.setReason("查询成功！");
			result.setData(json);
		}
		HtmlUtil.writerJson(response, result);
	}
	
	@RequestMapping("/overview")
	@ResponseBody
	public AppResult overview(HttpServletRequest request, HttpServletResponse response,Long sellerId,Integer pageNum,Integer pageSize) throws Exception{
		AppResult result = new AppResult();
		verifyUser(response, result, sellerId);
		try {
			CashAccount cash = cashAccountService.queryCashAccount(sellerId, null);
			Page<FqSellerStatement> statements = fqSellerStatementService.querySellerStatementBySellerId(sellerId, pageNum, pageSize);
			result.setFlag(0);
			result.setReason("查询成功");
			JSONObject json = new JSONObject();
			json.put("accountBalance", cash.getBalance());
			json.put("list", statements.getResult());
			json.put("ishasmore", statements.getPages()>pageNum);
			result.setData(json);
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setFlag(1);
			result.setReason("查询失败");
			return result;
		}
		
	}
	
	@RequestMapping("/withdraws")
	public void withdraws(HttpServletRequest request, HttpServletResponse response,Long sellerId,Long bankId,String money) throws Exception{
		AppResult appresult = new AppResult();
		SellerInfo seller = verifyUser(response, appresult, sellerId);
		if (seller.getEnabled() != 1) {
			appresult.setFlag(1);
			appresult.setReason("商户已停用！");
			this.logger.warn("商户已停用！");
			HtmlUtil.writerJson(response, appresult);
		}else{
		
		//每天凌晨00:00:00 到 00:01:00 之间不允许提现 ， 定时器正在结算商家余额  并发极小概率冲突导致 金额错误
		boolean isRun = true;
		int hour = DateUtils.getHour(new Date());
		if(hour==0){//当前是0点
			int minute = DateUtils.getMinute(new Date());
			if(minute==0){
				isRun = false;
				appresult.setFlag(1);
				appresult.setReason("系统正在出账中,请稍后1分钟!");
				HtmlUtil.writerJson(response, appresult);
			}
		}
		if(isRun){
			int result = -1;
			try {
				result = fqSellerStatementService.insertWithdraw(sellerId, money);
			} catch (Exception e) {
				e.printStackTrace();
				appresult.setFlag(1);
				appresult.setReason("提现失败");
			}
			if (result == 0) {
				appresult.setFlag(1);
				appresult.setReason("余额不足 ");
			}else if (result == 1) {
				appresult.setFlag(1);
				appresult.setReason("银行卡异常 ");
			}else if (result == 2) {
				appresult.setFlag(0);
				appresult.setReason("提现成功");
			}else {
				appresult.setFlag(1);
				appresult.setReason("提现失败");
			}
		}
		HtmlUtil.writerJson(response, appresult);	
		}
	}

	
	@RequestMapping("/toWithdraws")
	public void toWithdraws(HttpServletResponse response, HttpServletRequest request,Long sellerId, Integer pageNum, Integer pageSize) throws Exception{
		AppResult result = new AppResult();
		verifyUser(response, result, sellerId);
		
		try {
			Page<CashSaveWithdraw> withdraws = cashSaveWithdrawService.queryCashSaveWithdrawPageBySellerId(sellerId,pageNum,pageSize);
			result.setFlag(0);
			result.setReason("查询成功！");
			JSONObject json = new JSONObject();
			json.put("list", withdraws);
			json.put("ishasmore", withdraws.getPages()>pageNum);
			result.setData(json);
		} catch (Exception e) {
			this.logger.error("查询提现记录异常：", e);
			result.setFlag(1);
			result.setReason("查询提现记录异常！");
		}
		HtmlUtil.writerJson(response, result);
	}
	
	@RequestMapping("/toCashLogs")
	public void toCashLogs(HttpServletResponse response, HttpServletRequest request,Long sellerId, Integer pageNum, Integer pageSize) throws Exception{
		AppResult result = new AppResult();
		verifyUser(response, result, sellerId);
		if (pageNum == null) {
			pageNum = 1;
		}
		try {
//			CashAccount cash = cashAccountService.queryCashAccount(sellerId, null);
			Page<CashLog> cashLogs = (Page<CashLog>) cashLogService.queryCashLog(sellerId,pageNum,pageSize);
			result.setFlag(0);
			result.setReason("查询成功！");
			JSONObject json = new JSONObject();
			json.put("list", cashLogs);
			json.put("ishasmore", cashLogs.getPages()>pageNum);
			result.setData(json);
		} catch (Exception e) {
			this.logger.error("查询资金流水异常：", e);
			result.setFlag(1);
			result.setReason("查询资金流水异常！");
		}
		HtmlUtil.writerJson(response, result);
	}
	
	@RequestMapping("/updatePosition")
	public void updatePosition(HttpServletResponse response, HttpServletRequest request,Long sellerId,Double longitude,Double latitude) throws Exception{
		AppResult result = new AppResult();
		verifyUser(response, result, sellerId);
		StoreInfo si = storeService.queryStoreInfoBySeller(sellerId);
		if (si == null) {
			result.setFlag(1);
			result.setReason("未查询到店铺！");
		}else{
			StoreCheck store = new StoreCheck();
			store.setStoreId(si.getId());
			store.setLongitude(longitude);
			store.setLatitude(latitude);
			boolean isSuccess = sellerAssignedService.updateStore(store);
			if (isSuccess) {
				result.setFlag(0);
				result.setReason("店铺经纬度上传成功！");
			}else{
				result.setFlag(1);
				result.setReason("店铺经纬度上传失败！");
			}
		}
		HtmlUtil.writerJson(response, result);
	}
	
	@RequestMapping("/turnover")
	@ResponseBody
	public AppResult turnover(HttpServletResponse response, HttpServletRequest request,Long sellerId,Integer year,Integer month,Integer day,Integer pageNum,Integer pageSize) throws Exception{
		AppResult result = new AppResult();
		verifyUser(response, result, sellerId);
		Page<FqThirdPay> page = fqThirdPayService.queryFqThirdPays(sellerId, year, month, day, pageNum, pageSize);
		result.setFlag(0);
		result.setReason("查询成功！");
		JSONObject json = new JSONObject();
		json.put("list", page);
		json.put("ishasmore", page.getPages()>pageNum);
		result.setData(json);
		return result;
	}
	
	@RequestMapping("/feedback")
	public void feedback(HttpServletRequest request, HttpServletResponse response,Long sellerId,String title,String content) throws Exception{
		AppResult result = new AppResult();
		verifyUser(response, result, sellerId);
		Feedback feedback = new Feedback();
		feedback.setTitle(title);
		feedback.setContent(content);
		feedback.setFeedbackTime(new Date());
		feedback.setSellerId(sellerId);
		int a = feedbackService.insertFeedback(feedback);
		if (a>0) {
			result.setFlag(0);
			result.setReason("反馈成功！");
		}else{
			result.setFlag(1);
			result.setReason("反馈失败！");
		}
		HtmlUtil.writerJson(response, result);
	}
	
	@RequestMapping("/sellerUserCount")
	@ResponseBody
	public AppResult sellerUser(HttpServletResponse response, HttpServletRequest request,Long sellerId) throws Exception{
		AppResult result = new AppResult();
		verifyUser(response, result, sellerId);
		SellerUserCountVo selleruser = sellerUserCountService.querySellerUserCountVo(sellerId);
		result.setFlag(0);
		result.setReason("查询成功！");
		result.setData((JSONObject) JSON.toJSON(selleruser));
		return result;
	}
	@RequestMapping("/sellerUserInfo")
	@ResponseBody
	public AppResult sellerUserInfo(HttpServletResponse response, HttpServletRequest request,Long sellerId,String orderBy ,String order,Integer pageNum,Integer pageSize) throws Exception{
		AppResult result = new AppResult();
		verifyUser(response, result, sellerId);
		Page<SellerUserInfoVo> page = sellerUserCountService.querySellerUserInfoVo(sellerId, orderBy, order, pageNum, pageSize);
		result.setFlag(0);
		result.setReason("查询成功！");
		JSONObject json = new JSONObject();
		json.put("list", page.getResult());
		json.put("ishasmore", page.getPages()>page.getPageNum());
		result.setData(json);
		return result;
	}
	@RequestMapping("/sellerDaliyCount")
	@ResponseBody
	public AppResult sellerDaliyCount(HttpServletResponse response, HttpServletRequest request,Long sellerId) throws Exception{
		AppResult result = new AppResult();
		verifyUser(response, result, sellerId);
		SellerUserInfoVo sellerUserInfoVo = sellerUserCountService.queryDayliyVo(sellerId, new Date());
		result.setFlag(0);
		result.setReason("查询成功！");
		JSONObject json = new JSONObject();
		json.put("daliyCount", sellerUserInfoVo.getConNum());
		json.put("daliySum", sellerUserInfoVo.getConMoney());
		result.setData(json);
		return result;
	}
	@RequestMapping("/indexAd")
	@ResponseBody
	public AppResult indexAd() throws Exception{
		AppResult result = new AppResult();
		result.setFlag(0);
		result.setReason("查询成功！");
		AdInfo ad = adInfoService.queryAdInfo(1L);
		JSONObject json = new JSONObject();
		json.put("adImage", ad.getImage());
		json.put("adLink", ad.getLink());
		result.setData(json);
		return result;
	}
	
	@RequestMapping("/supprotAd")
	@ResponseBody
	public AppResult supprotAd() throws Exception{
		AppResult result = new AppResult();
		result.setFlag(0);
		result.setReason("查询成功！");
		AdInfo ad = adInfoService.queryAdInfo(2L);
		JSONObject json = new JSONObject();
		json.put("adImage", ad.getImage());
		json.put("adLink", ad.getLink());
		result.setData(json);
		return result;
	}
	@RequestMapping("/beginAd")
	@ResponseBody
	public AppResult beginAd() throws Exception{
		AppResult result = new AppResult();
		result.setFlag(0);
		result.setReason("查询成功！");
		List<AdVo> ads = adInfoService.queryAdInfoVoList(3L, 1, 1);
		if (ads.size()>0) {
			AdVo ad = ads.get(0);
			JSONObject json = new JSONObject();
			json.put("adImage", ad.getImage());
			json.put("adLink", ad.getLink());
			json.put("closeTime", 5);
			result.setData(json);
		}
		
		return result;
	}
	@RequestMapping("/adIndex")
	@ResponseBody
	public AppResult adIndex() throws Exception{
		AppResult result = new AppResult();
		result.setFlag(0);
		result.setReason("查询成功！");
		List<AdVo> ads = adInfoService.queryAdInfoVoList(1L, 1, 3);
		JSONObject json = new JSONObject();
		json.put("list", ads);
		result.setData(json);
		return result;
	}
	
	@RequestMapping("/adSupprot")
	@ResponseBody
	public AppResult adSupprot() throws Exception{
		AppResult result = new AppResult();
		result.setFlag(0);
		result.setReason("查询成功！");
		List<AdVo> ads = adInfoService.queryAdInfoVoList(2L, 1, 3);
		JSONObject json = new JSONObject();
		json.put("list", ads);
		result.setData(json);
		return result;
	}
	
	
	@RequestMapping("/supprotJudge")
	@ResponseBody
	public AppResult supprotJudge(HttpServletResponse response, HttpServletRequest request,Long sellerId) throws Exception{
		AppResult result = new AppResult();
		verifyUser(response, result, sellerId);
		int a = fqUserInfoService.querySellerByRegis(sellerId);
		JSONObject json = new JSONObject();
		result.setFlag(0);
		result.setReason("查询成功！");
		if (a>0) {
			BigDecimal b = fqUserInfoService.querySellerThirdPaySum(sellerId);
			if (b.compareTo(new BigDecimal(1000))>0) {
				json.put("judge", 1);
			
			}else{
				json.put("judge", 0);
			}
		}else{
			json.put("judge", 0);
		}
		int financialQuota = fqThirdPayService.getFinancialQuota(sellerId);
		if (financialQuota>5000) {
			json.put("judge", 2);
			json.put("quota", financialQuota);
		}
		result.setData(json);
		return result;
	}
	
	
	
	/**
	 * 判断是否有微信店铺信息(有店铺信息进入管理页面,没有店铺信息进入申请页面)
	 * @param response
	 * @param request
	 * @param sellerId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryStoreInfo")
	@ResponseBody
	public AppResult queryStoreInfo(HttpServletResponse response, HttpServletRequest request,Long sellerId) throws Exception{
		AppResult result = new AppResult();
		verifyUser(response, result, sellerId);
		StoreInfo storeInfo = storeService.queryStoreInfoBySeller(sellerId);
		if (storeInfo.getOpenOrder()!=null &&storeInfo.getOpenOrder()==1) {
			result.setFlag(1);
			result.setReason("申请通过");
		}else{
			Page<FqStoreApply> fqStoreApply = storeService.queryFqStoreApply(sellerId, null, null);
			if(fqStoreApply != null && fqStoreApply.size() > 0){
				Integer status = fqStoreApply.get(0).getStatus();
				if(status == 0){
					result.setFlag(0);
					result.setReason("未处理");
				}else if(status == 1){
					result.setFlag(1);
					result.setReason("申请通过");
				}else if(status == 2){
					result.setFlag(2);
					result.setReason("申请失败");
				}
			}else{
				result.setFlag(3);
				result.setReason("请先申请店铺");
			}
		}
		return result;
	}
	
	/**
	 * 提交服务申请
	 * @param response
	 * @param request
	 * @param sellerId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/submitFqStoreApply")
	@ResponseBody
	public AppResult submitFqStoreApply(HttpServletResponse response, HttpServletRequest request,Long sellerId) throws Exception{
		AppResult result = new AppResult();
		verifyUser(response, result, sellerId);
		SellerInfo sellerInfo = sellerService.getSellerById(sellerId);
		StoreInfo storeInfo = storeService.queryStoreInfoBySeller(sellerId);
		int res = fqStoreApplyService.insertFqStoreApply(sellerInfo, storeInfo);
		if(res > 0){
			result.setFlag(0);
			result.setReason("已申请，请等待审核");
		}else{
			result.setFlag(1);
			result.setReason("申请失败，请重新申请");
		}
		return result;
	}
	
	/**
	 * 服务申请通过后调用
	 * @param response
	 * @param request
	 * @param sellerId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryFqStore")
	@ResponseBody
	public AppResult queryFqStore(HttpServletResponse response, HttpServletRequest request,Long sellerId) throws Exception{
		AppResult result = new AppResult();
		verifyUser(response, result, sellerId);
		FqStore fqStore = fqStoreService.getFqStoreBySellerId(sellerId);
		if (fqStore!=null) {
			if(fqStore.getStatus() == 0){
				result.setFlag(0);
				result.setReason("审核中国");
			}else if(fqStore.getStatus() == 1){
				result.setFlag(1);
				result.setReason("审核通过");
			}else if(fqStore.getStatus() == 2){
				result.setFlag(2);
				result.setReason("审核不通过");
			}
		}else{
			result.setFlag(3);
			result.setReason("请先申请店铺");
		}
		JSONObject json = new JSONObject();
		json.put("store", fqStore);
		result.setData(json);
		return result;
	}
	
	/**
	 * 提交微信店铺信息
	 * @param response
	 * @param request
	 * @param sellerId
	 * @param token
	 * @param storename
	 * @param storelogo
	 * @param activityinfo
	 * @param storeinfo
	 * @param address
	 * @param trafficBeginTime
	 * @param trafficEndTime
	 * @param phonenum
	 * @param proportion
	 * @param locationid
	 * @param relevantImage1
	 * @param relevantImage2
	 * @param relevantImage3
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/submitStoreInfo")
	@ResponseBody
	public AppResult submitStoreInfo(HttpServletResponse response, HttpServletRequest request,Long sellerId,
			String storename, String storelogo, String activityinfo, String storeinfo,
			String address, String trafficBeginTime, String trafficEndTime, String phonenum, Integer proportion,
			Long locationid, String relevantImage1, String relevantImage2, String relevantImage3) throws Exception{
		AppResult result = new AppResult();
		verifyUser(response, result, sellerId);
		FqStore fqStore = sellerWXService.queryFqStoreBySellerId(sellerId);
		SellerInfo sellerInfo = sellerService.getSellerById(sellerId);
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		Date begin = null;
		Date end = null;
		try {
			begin = format.parse(trafficBeginTime);
			end = format.parse(trafficEndTime);
		} catch (ParseException e) {
			result.setFlag(1);
			result.setReason("开始或者结束时间错误");
			return result;
		}
		if (begin.getTime() >= end.getTime()) {
			result.setFlag(1);
			result.setReason("结束时间必须大于开始时间");
			return result;
		}
		StringBuffer storeImages = new StringBuffer();
		if (!StringUtils.isEmpty(relevantImage1)){
			storeImages.append(relevantImage1 + ",");
		}
		if (!StringUtils.isEmpty(relevantImage2)){
			storeImages.append(relevantImage2 + ",");
		}
		if (!StringUtils.isEmpty(relevantImage3)){
			storeImages.append(relevantImage3 + ",");
		}
		if(null != fqStore){
			int updateFqStore = sellerWXService.updateFqStoreApp(fqStore.getId(), sellerId, sellerInfo.getUsername(), storename, storelogo, activityinfo, storeinfo,
					address, trafficBeginTime, trafficEndTime, phonenum, proportion, locationid, storeImages.toString());
			if (updateFqStore == 1) {
				result.setFlag(0);
				result.setReason("修改成功");
			} else {
				result.setFlag(1);
				result.setReason("修改失败");
			}
		}else{
			int saveFqStore = sellerWXService.saveFqStore(sellerId, storename, storelogo, activityinfo, storeinfo,
					address, trafficBeginTime, trafficEndTime, phonenum, proportion, locationid, null, storeImages.toString(), null);
			if (saveFqStore == 1) {
				result.setFlag(0);
				result.setReason("添加成功");
			} else {
				result.setFlag(1);
				result.setReason("添加失败");
			}
		}
		return result;
	}
	
	/**
	 * 申请通过后修改进的接口
	 * @param response
	 * @param request
	 * @param sellerId
	 * @param storename
	 * @param storelogo
	 * @param activityinfo
	 * @param storeinfo
	 * @param address
	 * @param trafficBeginTime
	 * @param trafficEndTime
	 * @param phonenum
	 * @param proportion
	 * @param locationid
	 * @param relevantImage1
	 * @param relevantImage2
	 * @param relevantImage3
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateStoreInfo")
	@ResponseBody
	public AppResult updateStoreInfo(HttpServletResponse response, HttpServletRequest request,Long sellerId,
			String storename, String storelogo, String activityinfo, String storeinfo,
			String address, String trafficBeginTime, String trafficEndTime, String phonenum, Integer proportion,
			Long locationid, String relevantImage1, String relevantImage2, String relevantImage3) throws Exception{
		AppResult result = new AppResult();
		verifyUser(response, result, sellerId);
		FqStore fqStore = sellerWXService.queryFqStoreBySellerId(sellerId);
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		Date begin = null;
		Date end = null;
		try {
			begin = format.parse(trafficBeginTime);
			end = format.parse(trafficEndTime);
		} catch (ParseException e) {
			result.setFlag(1);
			result.setReason("开始或者结束时间错误");
			return result;
		}
		if (begin.getTime() >= end.getTime()) {
			result.setFlag(1);
			result.setReason("结束时间必须大于开始时间");
			return result;
		}
		StringBuffer storeImages = new StringBuffer();
		if (!StringUtils.isEmpty(relevantImage1)){
			storeImages.append(relevantImage1 + ",");
		}
		if (!StringUtils.isEmpty(relevantImage2)){
			storeImages.append(relevantImage2 + ",");
		}
		if (!StringUtils.isEmpty(relevantImage3)){
			storeImages.append(relevantImage3 + ",");
		}
		int saveFqStore = sellerWXService.updateFqStoreApp(fqStore.getId(), storename, storelogo, activityinfo, storeinfo,
				address, trafficBeginTime, trafficEndTime, phonenum, proportion, locationid, storeImages.toString());
		if (saveFqStore == 1) {
			result.setFlag(0);
			result.setReason("修改成功");
		} else {
			result.setFlag(1);
			result.setReason("修改失败");
		}
		return result;
	}
	
	/**
	 * 提交修改后重新进去调用
	 * @param response
	 * @param request
	 * @param sellerId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateStoreInfoByStatus")
	@ResponseBody
	public AppResult updateStoreInfoByStatus(HttpServletResponse response, HttpServletRequest request,Long sellerId) throws Exception{
		AppResult result = new AppResult();
		verifyUser(response, result, sellerId);
		FqStore fqStore = sellerWXService.queryFqStoreBySellerId(sellerId);
		FqStoreCheck fqStoreCheck = fqStoreCheckService.queryFqStoreCheck(fqStore.getId());
		JSONObject json = new JSONObject();
		if(null != fqStoreCheck){
			if(fqStoreCheck.getStatus() == 0){
				result.setFlag(0);
				result.setReason("审核中");
				json.put("fqStoreCheck", fqStoreCheck);
			}else{
				result.setFlag(1);
				json.put("fqStore", fqStore);
			}
		}else{
			result.setFlag(1);
			json.put("fqStore", fqStore);
		}
		result.setData(json);
		return result;
	}
	
	/**
	 * 菜系列表
	 * @param response
	 * @param request
	 * @param sellerId
	 * @param name
	 * @param enable
	 * @param createStartTime
	 * @param createEndTime
	 * @param pageNum
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/productTypeList")
	@ResponseBody
	public AppResult productTypeList(HttpServletResponse response, HttpServletRequest request, Long sellerId,
			String name, Integer enable, String createStartTime, String createEndTime) throws Exception {
		AppResult result = new AppResult();
		try{
			verifyUser(response, result, sellerId);
			FqStore store = fqStoreService.getFqStoreBySellerId(sellerId);
			if (null == store) {
				result.setFlag(1);
				result.setReason("请先申请店铺");
				return result;
			}
			List<FqProductType> productTypes = fqProductService.queryproductTypePageByStore(store.getId(), name, enable,
					createStartTime, createEndTime);
			result.setFlag(0);
			result.setReason("查询成功");
			JSONObject json = new JSONObject();
			json.put("list", productTypes);
			result.setData(json);
			return result;
		}catch(Exception e){
			result.setFlag(1);
			result.setReason("查询失败");
			return result;
		}
	}
	
	/**
	 * 新增/修改菜系
	 * @param session
	 * @param token
	 * @param id
	 * @param typeName
	 * @param level
	 * @param enable
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addOrUpdateProductType")
	@ResponseBody
	public AppResult addOrUpdateProductType(HttpServletResponse response, HttpServletRequest request, 
			 Long sellerId, Long id, String typeName, Integer level, Integer enable) throws Exception {
		AppResult result = new AppResult();
		verifyUser(response, result, sellerId);
		FqStore store = fqStoreService.getFqStoreBySellerId(sellerId);
		if (null == store) {
			result.setFlag(1);
			result.setReason("请先申请店铺");
			return result;
		}
		if(null == enable){
			enable = 0;
		}
		Boolean bool = fqProductService.saveOrUpdateFqProductType(store.getId(), store.getStoreName(), id, typeName,level, enable);
		if (bool) {
			result.setFlag(0);
			result.setReason("成功");
		} else {
			result.setFlag(1);
			result.setReason("失败");
		}
		return result;
	}
	
	/**
	 * 删除菜系
	 * @param response
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteProductType")
	@ResponseBody
	public AppResult deleteProductType(HttpServletResponse response, HttpServletRequest request, 
			Long[] ids) throws Exception {
		AppResult result = new AppResult();
		if (fqProductService.deleteProductTypeById(ids)) {
			result.setFlag(0);
			result.setReason("删除成功");
		} else {
			result.setFlag(1);
			result.setReason("删除失败");
		}
		return result;
	}
	
	/**
	 * 菜系上下架
	 * @param response
	 * @param request
	 * @param sellerId
	 * @param ids
	 * @param enable
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/changeProductTypeStatus")
	@ResponseBody
	public AppResult changeProductTypeStatus(HttpServletResponse response, HttpServletRequest request, 
			Long sellerId, Long[] ids, Integer enable) throws Exception {
		AppResult result = new AppResult();
		verifyUser(response, result, sellerId);
		FqStore store = fqStoreService.getFqStoreBySellerId(sellerId);
		if (null == store) {
			result.setFlag(1);
			result.setReason("请先申请店铺");
			return result;
		}
		if (fqProductService.updateProductTypeStatusByStoreId(store.getId(), ids, enable)) {
			result.setFlag(0);
			result.setReason("成功");
		} else {
			result.setFlag(1);
			result.setReason("失败");
		}
		return result;
	}
	
	/**
	 * 菜系排序
	 * @param response
	 * @param request
	 * @param sellerId
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/productTypeSortByLevel")
	@ResponseBody
	public AppResult productTypeSortByLevel(HttpServletResponse response, HttpServletRequest request, 
			Long sellerId, Long[] ids) throws Exception {
		AppResult result = new AppResult();
		verifyUser(response, result, sellerId);
		FqStore store = fqStoreService.getFqStoreBySellerId(sellerId);
		if (null == store) {
			result.setFlag(1);
			result.setReason("请先申请店铺");
			return result;
		}
		List<FqProductType> fpt = fqProductService.updateProductTypeSortByLevel(ids, store.getId());
		if (null != fpt){
			result.setFlag(0);
			result.setReason("排序成功");
			JSONObject json = new JSONObject();
			json.put("list", fpt);
			result.setData(json);
		} else {
			result.setFlag(1);
			result.setReason("排序失败");
		}
		return result;
	}
	
	/**
	 * 菜品列表
	 * @param response
	 * @param request
	 * @param sellerId
	 * @param name
	 * @param enable
	 * @param createStartTime
	 * @param createEndTime
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/productList")
	@ResponseBody
	public AppResult productList(HttpServletResponse response, HttpServletRequest request, Long sellerId,
			Long productType, String name, Integer enable, String createStartTime, String createEndTime, 
			Integer pageNum, Integer pageSize) throws Exception {
		AppResult result = new AppResult();
		try{
			verifyUser(response, result, sellerId);
			if (null == pageNum || pageNum < 1)
				pageNum = 0;
			if (null == pageSize || pageSize < 10)
				pageSize = 10;
			FqStore fqStore = fqStoreService.getFqStoreBySellerId(sellerId);
			if (fqStore == null || fqStore.getStatus() != 1) {
				// 店铺不存在
				result.setFlag(1);
				result.setReason("店铺信息不存在或未通过审核");
				return result;
			}
			List<FqProductType> productTypes = fqProductService.queryproductTypePageByStore(fqStore.getId(), name, enable, createStartTime, createEndTime);
			if(null == productType){
				productType = productTypes.get(0).getId();
			}
			Page<FqProduct> productInfo = fqProductService.searchProductListByProductType(productType, fqStore.getId(), name, enable, createStartTime,
					createEndTime, pageNum, pageSize);
			result.setFlag(0);
			result.setReason("查询成功");
			JSONObject json = new JSONObject();
			json.put("productType", productTypes);
			json.put("list", productInfo.getResult());
			json.put("ishasmore", productInfo.getPages()>pageNum);
			result.setData(json);
			return result;
		}catch(Exception e){
			result.setFlag(1);
			result.setReason("查询失败");
			return result;
		}
	}
	
	/**
	 * 添加菜品
	 * @param response
	 * @param request
	 * @param session
	 * @param token
	 * @param sellerId
	 * @param productName
	 * @param price
	 * @param stock
	 * @param level
	 * @param productType
	 * @param productImages
	 * @param productInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addProduct")
	@ResponseBody
	public Object addProduct(HttpServletResponse response, HttpServletRequest request,
			Long sellerId, String productName, String price, Integer stock, Integer level, 
			Long productType, String productImages, String productInfo) throws Exception {
		AppResult result = new AppResult();
		verifyUser(response, result, sellerId);
		FqStore fqStore = fqStoreService.getFqStoreBySellerId(sellerId);
		try {
			if (fqStore == null || fqStore.getStatus() != 1) {
				result.setFlag(1);
				result.setReason("店铺信息不存在或未通过审核");
				return result;
			}
			FqProduct fqProduct = new FqProduct();
			fqProduct.setProductName(productName);
			fqProduct.setPrice(new BigDecimal(price));
			fqProduct.setStock(stock);
			fqProduct.setStatus(0);
			fqProduct.setLevel(0);
			fqProduct.setProductImages(productImages);
			fqProduct.setProductInfo(productInfo);
			fqProduct.setStoreId(fqStore.getId());
			fqProduct.setCreateTime(new Date());
			fqProduct.setProductType(productType);
			Boolean bl = false;
			try {
				bl = fqProductService.saveProduct(fqProduct);
			} catch (Exception e) {
				result.setFlag(1);
				result.setReason("添加菜品异常");
				return result;
			}
			if (bl) {
				result.setFlag(0);
				result.setReason("添加成功");
			} else {
				result.setFlag(1);
				result.setReason("添加失败");
			}
			return result;
		} catch (Exception e) {
			result.setFlag(1);
			result.setReason("添加菜品异常");
			return result;
		}
	}
	
	/**
	 * 修改菜品
	 * @param response
	 * @param request
	 * @param session
	 * @param token
	 * @param sellerId
	 * @param productName
	 * @param price
	 * @param stock
	 * @param level
	 * @param productType
	 * @param productImages
	 * @param productInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateProduct")
	@ResponseBody
	public AppResult updateProduct(HttpServletResponse response, HttpServletRequest request, 
			Long sellerId, Long prodId, String productName, String price, Integer stock, Integer level, 
			Long productType, String productImages, String productInfo) throws Exception {
		AppResult result = new AppResult();
		verifyUser(response, result, sellerId);
		FqStore fqStore = fqStoreService.getFqStoreBySellerId(sellerId);
		try {
			if (fqStore == null || fqStore.getStatus() != 1) {
				result.setFlag(1);
				result.setReason("店铺信息不存在或未通过审核");
				return result;
			}
			FqProduct fqProduct = fqProductService.getProductInfoById(fqStore.getId(), prodId);
			if (fqProduct == null) {
				result.setFlag(1);
				result.setReason("服务器忙,请稍候再试");
				return result;
			}
			fqProduct.setProductName(productName);
			fqProduct.setPrice(new BigDecimal(price));
			fqProduct.setStock(stock);
			fqProduct.setStatus(0);
			fqProduct.setLevel(level);
			fqProduct.setProductImages(productImages);
			fqProduct.setProductInfo(productInfo);
			fqProduct.setStoreId(fqStore.getId());
			fqProduct.setCreateTime(new Date());
			fqProduct.setProductType(productType);
			Boolean bl = false;
			try {
				bl = fqProductService.updateProduct(fqProduct);
			} catch (Exception e) {
				result.setFlag(1);
				result.setReason("修改菜品异常");
				return result;
			}
			if (bl) {
				result.setFlag(0);
				result.setReason("修改成功");
			} else {
				result.setFlag(1);
				result.setReason("修改失败");
			}
			return result;
		} catch (Exception e) {
			result.setFlag(1);
			result.setReason("修改菜品异常");
			return result;
		}
	}
	
	/**
	 * 删除菜品
	 * @param response
	 * @param request
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteProduct")
	@ResponseBody
	public AppResult deleteProduct(HttpServletResponse response, HttpServletRequest request, 
			Long[] ids) throws Exception {
		AppResult result = new AppResult();
		if (fqProductService.deleteProductByIds(ids)) {
			result.setFlag(0);
			result.setReason("删除成功");
		} else {
			result.setFlag(1);
			result.setReason("删除失败");
		}
		return result;
	}
	
	/**
	 * 菜品上下架
	 * @param ids
	 * @param status
	 * @return
	 */
	@RequestMapping("/changeProductStatus")
	@ResponseBody
	public AppResult changeProductStatus(HttpServletResponse response, HttpServletRequest request,
			Long[] ids, Integer status) {
		AppResult result = new AppResult();
		if (fqProductService.updateProductStatus(ids, status)) {
			result.setFlag(0);
			result.setReason("成功");
		} else {
			result.setFlag(1);
			result.setReason("失败");
		}
		return result;
	}
	
	/**
	 * 菜品排序
	 * @param response
	 * @param request
	 * @param sellerId
	 * @param prodId
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/productSortByLevel")
	@ResponseBody
	public AppResult productSortByLevel(HttpServletResponse response, HttpServletRequest request, 
			Long sellerId, Long prodType, Long[] ids) throws Exception {
		AppResult result = new AppResult();
		verifyUser(response, result, sellerId);
		FqStore fqStore = fqStoreService.getFqStoreBySellerId(sellerId);
		if (null == fqStore) {
			result.setFlag(1);
			result.setReason("请先申请店铺");
			return result;
		}
		List<FqProduct> fp = fqProductService.updateProductSortByLevel(ids, fqStore.getId(), prodType);
		if (null != fp) {
			result.setFlag(0);
			result.setReason("排序成功");
			JSONObject json = new JSONObject();
			json.put("list",fp);
			result.setData(json);
		} else {
			result.setFlag(1);
			result.setReason("排序失败");
		}
		return result;
	}
	
	/**
	 * 订单列表
	 * @param response
	 * @param request
	 * @param sellerId
	 * @param orderNo
	 * @param status
	 * @param createTimeBegin
	 * @param createTimeEnding
	 * @param bookTimeBegin 预定消费开始时间
	 * @param bookTimeEnding 预定消费结束时间
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/orderList")
	@ResponseBody
	public AppResult queryOrderList(HttpServletResponse response, HttpServletRequest request, Long sellerId,
			String orderNo, Integer status, String createTimeBegin, String createTimeEnding, String bookTimeBegin, 
			String bookTimeEnding,Integer pageNum, Integer pageSize) throws Exception {
		AppResult result = new AppResult();
		try{
			verifyUser(response, result, sellerId);
			if (pageNum == null || pageNum < 1)
				pageNum = 0;
			if (pageSize == null || pageSize < 1)
				pageSize = 10;
			boolean bool = false;
			if(null != createTimeBegin){
				bool = createTimeBegin.matches("^\\d{4}\\-\\d{2}\\-\\d{2}$");
				if(bool){
					createTimeEnding = createTimeBegin;
				}else{
					SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM"); 
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
					Date date = simpleDate.parse(createTimeBegin);
					Calendar cal = Calendar.getInstance();//获取当前日期 
					cal.setTime(date);
					cal.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
					createTimeBegin = format.format(cal.getTime());
					
					cal.set(Calendar.MONTH, date.getMonth()+1);    //加一个月
					cal.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
					createTimeEnding = format.format(cal.getTime());
				}
			}
			FqStore fqStore = fqStoreService.getFqStoreBySellerId(sellerId);
			if (fqStore == null || fqStore.getStatus() != 1 ) {
				// 店铺不存在 
				result.setFlag(1);
				result.setReason("店铺信息不存在或未通过审核");
				return result;
			}
			Page<FqOrder> orders = fqOrderService.searchOrderListByStoreId(fqStore.getId(), orderNo, status, 
					createTimeBegin, createTimeEnding, bookTimeBegin, bookTimeEnding, pageNum, pageSize);
			result.setFlag(0);
			result.setReason("查询成功");
			JSONObject json = new JSONObject();
			json.put("list", orders.getResult());
			json.put("ishasmore", orders.getPages()>pageNum);
			result.setData(json);
			return result;
		}catch(Exception e){
			e.printStackTrace();
			result.setFlag(1);
			result.setReason("查询失败");
			return result;
		}
	}
	
	/**
	 * 订单详情
	 * @param response
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/orderDetail")
	@ResponseBody
	public AppResult getOrderDetail(HttpServletResponse response, HttpServletRequest request, Long id){
		AppResult result = new AppResult();
		if(null != id){
			FqOrder order = fqOrderService.getFqOrder(id);
			if(null != order){
				List<FqOrderDetail> details = fqOrderService.queryOrderDetailByOrderId(order.getId());
				if(null != details){
					FqUserInfo user = fqUserInfoService.getFqUserInfoById(order.getUserId());
					if(null != user){
						result.setFlag(0);
						result.setReason("查询成功");
						JSONObject json = new JSONObject();
						json.put("order", order);
						json.put("details", details);
						json.put("user", user);
						result.setData(json);
						return result;
					}
				}
			}
		}
		result.setFlag(1);
		result.setReason("查询失败");
		return result;
	}
}

