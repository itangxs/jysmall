package cn.qhjys.mall.controller.system;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.model.io.HWPFOutputStream;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.common.Query;
import cn.qhjys.mall.entity.BankInfo;
import cn.qhjys.mall.entity.MsWithdraw;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.service.BankInfoService;
import cn.qhjys.mall.service.fq.FqSellerStatementService;
import cn.qhjys.mall.service.fq.MsWithdrawService;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.ExportExcelUtil;
import cn.qhjys.mall.util.ms.BaseRequest;
import cn.qhjys.mall.util.ms.ConfigUtils;
import cn.qhjys.mall.util.ms.DateUtil;
import cn.qhjys.mall.util.ms.ExcelUtil;
import cn.qhjys.mall.util.ms.Gzip;
import cn.qhjys.mall.util.ms.MsConstant;
import cn.qhjys.mall.util.ms.RequestNo;
import cn.qhjys.mall.util.ms.interfaces.MsAccountRequest;
import cn.qhjys.mall.vo.ms.AccountFileExcelVo;
import cn.qhjys.mall.vo.system.MsWithdrawExportVo;
import cn.qhjys.mall.vo.system.MsWithdrawParmVo;
import cn.qhjys.mall.weixin.util.SystemConstant;


/**
 * 
 * 标题：民生商户提现管理
 * 作者：huangjr
 * 描述：TODO
 * 版本：V1.0 
 * 创建时间：2017年7月15日 下午3:07:02
 * 修改时间：
 *
 */
@Controller
@RequestMapping("/managermall/systemmall/msWithdraw")
public class MsWithdrawController extends Base{
	
	@Autowired
	private MsWithdrawService msWithdrawService;		//民生提现服务
	
	@Autowired
	private BankInfoService bankInfoService;		//商户银行卡信息服务
	
	@Autowired
	private FqSellerStatementService fqSellerStatementService;	//商户结算服务
	
	/**
	 * 
	 * 描述: 商户提现分页查询
	 * @param msWithdrawParm
	 * @param query
	 * @return
	 */
	@RequestMapping("/withdraw_list")
	public ModelAndView getMsWithdrawList(MsWithdrawParmVo msWithdrawParm,Query query){
		ModelAndView view = new ModelAndView("/system/finance/ms_withdraw_list");
		try {
			//查询商户余额
			Map<String,Object> userMap = MsAccountRequest.getBalanceInfo(ConfigUtils.getProperty(SystemConstant.MS_MCH_ID));
	        //查询列表内容
			Page<Map<String,Object>> pageList = msWithdrawService.queryMsWithdrawPage(msWithdrawParm, query);
			view.addObject("page", pageList);
			view.addObject("msWithdraw", msWithdrawParm);
			view.addObject("sumMoney", userMap.get("sumMoney"));
			view.addObject("allFeeMoney", userMap.get("allFeeMoney"));
			view.addObject("notFeeMoney", userMap.get("notFeeMoney"));
			view.addObject("pageSize", query.getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("--商户提现查询出现异常--",e);
		}
		return view;
	}
	
	/**
	 * 
	 * 描述: 商户提现导出
	 * @param msWithdrawParm
	 * @param query
	 * @return
	 */
	@RequestMapping("/withdraw_export")
	public String getMsWithdrawExport(MsWithdrawParmVo msWithdrawParm, HttpServletResponse response){
		try {
			List<MsWithdrawExportVo> list = msWithdrawService.queryMsWithdrawExport(msWithdrawParm);
			ExportExcelUtil<MsWithdrawExportVo> excelUtil = new ExportExcelUtil<MsWithdrawExportVo>();
			String[] headersName = new String[] {"订单号", "创建时间", "店铺ID", "店铺名称", "交易金额", "未扣费金额", "已扣费金额", "手续费", "提现金额",
					"状态", "开户人", "开户银行", "银行卡号"};
			String[] headersId = new String[] {"orderNo", "createDate", "storeId", "storeName", "payMoney", "notFeeMoney", "allFeeMoney", "rateFee",
					"withdrawMoeny", "state", "cardholder", "bankName", "bankCard" };
			SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HHmmss");
			excelUtil.exportExcel("商户提现报表 " + format.format(new Date()), headersName, headersId, list, response);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("--商户提现导出出现异常--",e);
		}
		return null;
	}
	
	/**
	 * 
	 * 描述: 商户下载对账文件
	 * @param id 提现记录ID
	 * @param endDate 对账日期
	 * @return
	 */
	@RequestMapping("/account_file_export")
	public String getAccountFileExport(@RequestParam(value = "endDate", required = true)String endDate,
									   HttpServletResponse response){
		try {
			if (DateUtil.parseDefaultDate(endDate).getTime() > new Date().getTime()) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter writer = response.getWriter();
				writer.write("<script>alert('没有该日期的对账文件！');</script>");
				writer.close();
				writer.flush();
			}
			
			logger.info("----下载"+endDate+"民生对账文件开始---");
			List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
			nvps.add(new BasicNameValuePair("requestNo", RequestNo.request_no));
			nvps.add(new BasicNameValuePair("version", "V1.1"));
			nvps.add(new BasicNameValuePair("transId", "14"));//下载对账文件
			nvps.add(new BasicNameValuePair("merNo", ConfigUtils.getProperty(SystemConstant.MS_MCH_ID)));
			nvps.add(new BasicNameValuePair("postingDate", endDate.replaceAll("-", "")));
			
			Map<String,String> fileMap = BaseRequest.getSignToSend(nvps);
			if (null != fileMap) {
				String respCode = fileMap.get("respCode");//响应码
	        	String respDesc = fileMap.get("respDesc");//响应信息
	        	if (MsConstant.SUCCEE.equals(respCode)) {
	        		String fileContent = fileMap.get("filecontent");//Base64编码后文件内容
	        		byte[] fileText = Base64.decode(fileContent);//解密后的内容
	        		List<String> logText = new ArrayList<String>();//txt对账文件内容
	        		
	        		//读取解密后文件每一行的字符串
	        		try {
	        			InputStreamReader read = new InputStreamReader(new ByteArrayInputStream(fileText),"UTF-8");
	        			BufferedReader bufferedReader = new BufferedReader(read);
						String lineTxt = null;
						while((lineTxt = bufferedReader.readLine()) != null){
							logText.add(lineTxt);
			            }
						read.close();
					} catch (IOException ioe) {  
			        	ioe.printStackTrace();
				    } catch (Exception e) {  
				        e.printStackTrace();
				    }
	        		
	        		List<String> modelNames = new ArrayList();	//第一行字段名
					List<String> modelNames2 = new ArrayList();	//第二行字段名
					
					//第二行字段名称
					modelNames2.add("记账日期");
					modelNames2.add("父级商户号");
					modelNames2.add("商户号");
					modelNames2.add("支付产品");
					modelNames2.add("交易类型");
					modelNames2.add("商品订单号");
					modelNames2.add("交易时间");
					modelNames2.add("交易金额");
					modelNames2.add("交易手续费");
					modelNames2.add("清算金额");
					modelNames2.add("原商品订单号");
					modelNames2.add("原交易时间");
					modelNames2.add("备注");
					
					//字段名集合
					List<List> modelNamesList = new ArrayList();
					//字段名属性
					List beanNames = new ArrayList();	
					beanNames.add("jzrq");
					beanNames.add("fjshh");
					beanNames.add("shh");
					beanNames.add("zfcp");
					beanNames.add("jylx");
					beanNames.add("spddh");
					beanNames.add("jysj");
					beanNames.add("jyje");
					beanNames.add("jysxf");
					beanNames.add("qsje");
					beanNames.add("yspddh");
					beanNames.add("yjjsj");
					beanNames.add("bz");
					
					ExcelUtil excelUtil = new ExcelUtil();
					AccountFileExcelVo accountFileExcelVo = null;
					List<AccountFileExcelVo> accountFileExcelVoList = new ArrayList<AccountFileExcelVo>();
					for(int i=0;i<logText.size();i++){
						String[] textArray = logText.get(i).split("\\|");
						if(i==0){	//	第一行 记账日期、成功总笔数、成功总金额、成功总手续费、清算金额
							modelNames.add("记账日期开始："+textArray[0]);
							modelNames.add("记账日期结束："+textArray[1]);
							modelNames.add("成功总笔数："+textArray[2]);
							modelNames.add("成功总金额："+textArray[3]);
							modelNames.add("成功总手续费："+textArray[4]);
							modelNames.add("清算金额："+textArray[5]);
						}else{
							accountFileExcelVo = new AccountFileExcelVo();
							accountFileExcelVo.setJzrq(textArray[0]);
							accountFileExcelVo.setFjshh(textArray[1]);
							accountFileExcelVo.setShh(textArray[2]);
							accountFileExcelVo.setZfcp(textArray[3]);
							accountFileExcelVo.setJylx(textArray[4]);
							accountFileExcelVo.setSpddh(textArray[5]);
							accountFileExcelVo.setJysj(textArray[6]);
							accountFileExcelVo.setJyje(textArray[7]);
							accountFileExcelVo.setJysxf(textArray[8]);
							accountFileExcelVo.setQsje(textArray[9]);
							accountFileExcelVo.setYspddh(textArray[10]);
							accountFileExcelVo.setYjjsj(textArray[11]);
							accountFileExcelVo.setBz(textArray[12]);
							accountFileExcelVoList.add(accountFileExcelVo);
						}
					}
					
					modelNamesList.add(modelNames);
					modelNamesList.add(modelNames2);
					HSSFWorkbook hssfWorkbook = excelUtil.createWorkbook();
				    excelUtil.addWorksheet("Sheet1",hssfWorkbook, accountFileExcelVoList, modelNamesList, beanNames);
				    HWPFOutputStream out = new HWPFOutputStream();
			        hssfWorkbook.write(out);
			        out.close();
			        
			        //设置下载
			        response.setContentType("application/octet-stream");  
					response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(endDate+"_商户对账_"+".xls", "UTF-8"));
					byte[] b=new byte[1024]; 
					OutputStream os=null;
					InputStream is=null;  
					try{  
					    is=new ByteArrayInputStream(out.toByteArray());  
					    os=response.getOutputStream();  
					    int len=0;  
					    while((len=is.read(b))>0){  
					        os.write(b,0,len);  
					    }  
					    response.setStatus( response.SC_OK );  
					    is.close();  
					}catch(IOException e){  
					    e.printStackTrace();  
					} 
					logger.info("--下载成功--");
	        	} else {
	        		logger.info("--下载对账文件接口返回状态失败--");
	        		logger.info("响应码："+respCode);
	        		logger.info("响应描述："+respDesc);
	        		return respDesc;
	        	}
			} else {
	        	logger.info("--商户下载对账文件接口请求失败--");
	        }
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("--商户下载对账文件出现异常--",e);
		}
		return null;
	}
	
	/***
	 * 系统审核商家提现
	 * @param id 提现编号
	 */
	@RequestMapping(value = "/auditSellerCash", method = RequestMethod.POST)
	@ResponseBody
	public String auditSellerCashSaveWithd(@RequestParam(value = "id", required = true) String id){
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("ids", id.split(","));
			map.put("state", 0);//只更新状态为未审核的数据
			map.put("setState", 1);//更新状态为已审核
			map.put("examineDate", new Date());//添加审核时间
			boolean flag = msWithdrawService.updateWithdraw(map);
			if (flag) 
				return "审核成功！";
			else 
				return "审核失败，没有受影响的记录！";
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("--系统审核商家提现出现异常--",e);
			return "审核出现异常，请联系技术人员";
		}
	}
	
	/***
	 * 系统申请商家提现
	 * @param id 提现编号
	 */
	@RequestMapping(value = "/update_withdraw", method = RequestMethod.POST)
	@ResponseBody
	public Object getUpdateWithdraw(@RequestParam(value = "id", required = true) String id){
		//------------------信息变量声明--开始----------------
		String productId = "0201";//默认为普通代付，根据周期有所改变
		Long transAmt = 0L;//代付金额,必须为Long类型，已分为单位
		String customerName = "";//付款账户名称
		String acctNo = "";//代付银行账号
		String note = "";//备注信息
		int count = 0;//总执行数
		int success_count = 0;//处理成功的笔数
		int error_count = 0;//处理失败的笔数
		List<Map<String,Object>> msgListMap = new ArrayList<Map<String,Object>>();//异常信息定义
		Map<String,Object> msgMap = new HashMap<String,Object>();
		String msError = "msError";//银行异常信息，Map对应的Key值
		String storeId = "storeId";//记录哪一条数据出现异常
		//------------------信息变量声明--结束----------------
		
		try {
			logger.info("---------------执行民生提现表Id为："+id+"提现操作 开始-----------------");
			/*int week = DateUtil.getWeek(new Date());	//获取当前是星期几
			if (week == 1 || week == 7) {
				//周一 或者 周日时，走额度代付
				productId = "0203";//额度代付
			}*/
			String[] ids = id.split(",");
			for (int i=0; i<ids.length; i++) {
				if (StringUtils.isNotBlank(ids[i])) {
					logger.info("------------------------执行"+ids[i]+"--开始-----------------------");
					count++;//记录总执行数
					MsWithdraw msWithdraw = msWithdrawService.getMsWithdrawById(Long.valueOf(ids[i]));
					msgMap = new HashMap<String,Object>();
					msgMap.put(storeId, msWithdraw.getStoreId());//店铺ID，用来记录响应的异常信息
					
					/***************************出现异常操作---开始**********************************/
					//当状态为已审核或提现失败 可执行提现操作
					if (msWithdraw.getState() != 1 && msWithdraw.getState() != 3) {
						//其中有数据状态不正确。
						logger.info("-----民生提现表ID为："+msWithdraw.getId()+"的状态不正确，状态为："+msWithdraw.getState()+"，不能进行提现操作！");
						msgMap.put(msError, "提现状态不正确");//保存系统异常信息
						error_count++;//记录失败次数
						msgListMap.add(msgMap);//添加异常信息
						continue;
					}
					BankInfo bankInfo = bankInfoService.queryBankInfoBySellerId(msWithdraw.getSellerId());
					if (null == bankInfo) {
						//未找到商户银行卡信息处理
						logger.info("--未找到该商户ID为："+msWithdraw.getSellerId()+"银行卡信息，已跳出该商户的提现！--");
						msgMap.put(msError, "未找到商户银行卡信息");//保存系统异常信息
						error_count++;//记录失败次数
						msgListMap.add(msgMap);//添加异常信息
						continue;
					}
					/***************************出现异常操作---结束**********************************/
					
					customerName = bankInfo.getCardholder();
					acctNo = bankInfo.getCarkNum();
					transAmt = msWithdraw.getWithdrawMoeny().multiply(new BigDecimal(100)).longValue();
					note = "给商户ID为："+bankInfo.getSellerId()+"打款,金额为："+msWithdraw.getWithdrawMoeny();
							
					logger.info("提现表ID："+msWithdraw.getId());
					logger.info("商户ID："+msWithdraw.getSellerId());
					logger.info("店铺ID："+msWithdraw.getStoreId());
					logger.info("提现金额："+msWithdraw.getWithdrawMoeny());
					logger.info("状态："+msWithdraw.getState());
					logger.info("提现的账户名："+customerName);
					logger.info("提现的银行卡号："+acctNo);
					logger.info("--------------------------提现接口调用-开始----------------------------");
					logger.info("当前执行时间为："+DateUtil.formatTimesTampDate(new Date()));
					logger.info("执行接口产品类型："+ (productId.equals("0201") ? "普通代付" : "额度代付"));
							
					//拼接接口请求参数
					List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
					String requestNo = RequestNo.getRequestNo();//请求流水号
					logger.info("请求流水号："+requestNo);
					nvps.add(new BasicNameValuePair("requestNo", requestNo));
			        nvps.add(new BasicNameValuePair("version", "V1.0"));
			        nvps.add(new BasicNameValuePair("productId", productId));// 0201-非垫支,0203-垫支(T0)
			        nvps.add(new BasicNameValuePair("transId", "07"));
			        nvps.add(new BasicNameValuePair("merNo", ConfigUtils.getProperty(SystemConstant.MS_MCH_ID)));
			        nvps.add(new BasicNameValuePair("orderDate", new SimpleDateFormat("yyyyMMdd").format(new Date())));
			        String orderNo = RequestNo.getRequestNo();//交易订单号
			        logger.info("交易订单号："+orderNo);
			        nvps.add(new BasicNameValuePair("orderNo", orderNo));//订单号
			        nvps.add(new BasicNameValuePair("notifyUrl", "http://localhost:8081/managermall/systemmall/msWithdraw/update_withdraw?id=100"));
			        nvps.add(new BasicNameValuePair("transAmt", transAmt.toString()));
			        nvps.add(new BasicNameValuePair("isCompay", "0"));// 0-对私,1-对公
			        nvps.add(new BasicNameValuePair("customerName", customerName));
			        nvps.add(new BasicNameValuePair("acctNo", acctNo));
			        nvps.add(new BasicNameValuePair("note", note));
			        
			        //更新提现状态为提现中
    	    		Map<String,Object> updateMap = new HashMap<String,Object>();
    	    		updateMap.put("id", msWithdraw.getId());
    	    		updateMap.put("setState", 2);//更新状态为提现中
    	    		updateMap.put("settDate", new Date());//添加提现时间
    	    		updateMap.put("bankCard", acctNo);//添加提现银行卡
    	    		updateMap.put("requestNo", requestNo);//请求流水号
    	    		updateMap.put("orderNo", orderNo);//提现订单号
    				msWithdrawService.updateWithdraw(updateMap);
    				
			        Map<String,String> withdrawMap = BaseRequest.getSignToSend(nvps);
			        logger.info("当前接口返回结果："+withdrawMap);
			        
			        if (null != withdrawMap) {
			        	String respCode = withdrawMap.get("respCode");//响应码
			        	String respDesc = withdrawMap.get("respDesc");//响应信息
			        	if (MsConstant.SUCCEE.equals(respCode)) {
			        		logger.info("=====接口响应成功！=======");
			        		//更新提现状态为提现成功
			        		updateMap = new HashMap<String,Object>();
		    	    		updateMap.put("id", msWithdraw.getId());
		    	    		updateMap.put("setState", 4);//更新状态为提现成功
		    	    		updateMap.put("respCode", respCode);
		    	    		updateMap.put("respDesc", respDesc);
		    				boolean flag = msWithdrawService.updateWithdraw(updateMap);
		    				if (flag) {
		    					success_count++; //记录执行成功的交易笔数
				        		msgMap.put(msError, "执行成功");//保存系统返回的异常信息
				        		
				        		//修改结算表为已出账
				        		fqSellerStatementService.updateMsSellerWithdraw(msWithdraw.getSellerId(), 
				        				DateUtil.getDateThree((DateUtil.getDateBefore(msWithdraw.getCreateDate(),2))),
				        				DateUtil.getDateThree((DateUtil.getDateBefore(msWithdraw.getCreateDate(),1))));
		    				}
			        	} else {
			        		logger.error("接口状态返回错误，响应码为："+respCode+"---响应信息："+respDesc);
			        		error_count++;//记录失败次数
			        		msgMap.put(msError, respDesc);//保存银行返回的异常信息
			        		//更新状态为提现失败
			        		updateMap = new HashMap<String,Object>();
		    	    		updateMap.put("id", msWithdraw.getId());
		    	    		updateMap.put("setState", 3);//更新状态为提现失败
		    	    		updateMap.put("respCode", respCode);
		    	    		updateMap.put("respDesc", respDesc);
		    				msWithdrawService.updateWithdraw(updateMap);
			        	}
			        } else {
			        	logger.error("提现接口请求异常！！！");
			        	error_count++;//记录失败次数
			        	msgMap.put(msError, "提现接口调用异常");//保存系统异常信息
			        	//更新状态为提现失败
		        		updateMap = new HashMap<String,Object>();
	    	    		updateMap.put("id", msWithdraw.getId());
	    	    		updateMap.put("setState", 3);//更新状态为提现失败
	    	    		updateMap.put("respCode", "-1");
	    	    		updateMap.put("respDesc", "请求异常");
	    				msWithdrawService.updateWithdraw(updateMap);
			        }
			        logger.info("--------------------------提现接口调用-结束----------------------------");
			        msgListMap.add(msgMap);//添加异常信息
			        logger.info("------------------------执行"+ids[i]+"--结束-----------------------");
				}
			}
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("sumCount", count);//总记录数
			map.put("successCount", success_count);//成功的记录数
			map.put("errorCount", error_count);//失败的记录数
			map.put("textInfo", msgListMap);//异常信息
			logger.info("执行记录："+map.toString());
			logger.info("当前执行操作数："+count);
			logger.info("执行成功的操作数："+success_count);
			logger.info("执行失败的操作数："+error_count);
			logger.info("---------------执行民生提现表Id为："+id+"提现操作 结束-----------------");
			return JSON.toJSON(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("--系统申请商家提现出现异常--",e);
		}
		return null;
	}
}
