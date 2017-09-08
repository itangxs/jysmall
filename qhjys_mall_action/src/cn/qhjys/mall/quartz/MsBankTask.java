package cn.qhjys.mall.quartz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.AuthenticationChnnel;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.mapper.StoreInfoMapper;
import cn.qhjys.mall.service.AuthenticationChnnelService;
import cn.qhjys.mall.service.fq.FqSellerStatementService;
import cn.qhjys.mall.service.fq.MsWithdrawService;
import cn.qhjys.mall.util.ms.BaseRequest;
import cn.qhjys.mall.util.ms.ConfigUtils;
import cn.qhjys.mall.util.ms.RequestNo;
import cn.qhjys.mall.weixin.util.SystemConstant;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * 标题：民生银行相关定时任务
 * 作者：huangjr
 * 描述：TODO
 * 版本：V1.0 
 * 创建时间：2017年7月15日 下午1:55:19
 * 修改时间：
 *
 */
public class MsBankTask extends Base {
	
	@Autowired
	private MsWithdrawService msWithdrawService;		//民生提现服务
	@Autowired
	private AuthenticationChnnelService authenticationChnnelService;  //商户进件渠道服务
	@Autowired
	private StoreInfoMapper storeInfoMapper;
	@Autowired
	private FqSellerStatementService fqSellerStatementService;	//商户结算服务
	/**
	 * 
	 * 描述: 统计前天23：00至昨天23：00民生的商户交易明细生成提现记录
	 */
	public void addMsWithdraw() {
		logger.info("*****************统计前天23：00至昨天23：00民生的商户交易明细生成提现记录--开始*****************");
		try {
			boolean flag = msWithdrawService.addWithdrawByDealDetail();
			if (flag) 
				logger.info("--提现记录生成成功--");
			else 
				logger.info("--没有需要生成提现的记录--");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("统计出现异常，异常信息：",e);
		}
		logger.info("*****************统计--结束*****************");
	}
	
	/**
	 * 
	 * 结算昨天23：00至今天23：00交易记录
	 */
	public void msStatement() {
		logger.info("*****************民生商户结算昨天23：00至今天23：00交易记录--开始*****************");
		try {
			boolean result = fqSellerStatementService.updateMsSellerStatementQuartz();
			if (result) {
				logger.info("-民生商家余额结算定时任务--SUCCESS");
			} else {
				logger.error("-没有需要结算的记录");
			}
		} catch (Exception e) {
			this.logger.error("民生商家余额结算定时任务异常", e);
		}
		logger.info("*****************民生商户昨天23：00至今天23：00交易记录--结束*****************");
	}
	
	/**
	 * 
	 * 验证昨天23：00至当天23：00结算数据
	 */
	public void msStatementVeri() {
		logger.info("*****************民生商户验证昨天23：00至当天23：00结算数据--开始*****************");
		try {
			boolean result = fqSellerStatementService.updateVeriMsSellerStatementQuartz();
			if (result) {
				logger.info("-民生商家验证结算定时任务--SUCCESS");
			} else {
				logger.error("-没有需要验证的记录");
			}
		} catch (Exception e) {
			this.logger.error("民生商家验证结算定时任务异常", e);
		}
		logger.info("*****************民生商户验证昨天23：00至当天23：00结算数据--结束*****************");
	}
	
	/**
	 * 进件信息查询结果 Task
	 * @throws Exception
	 */
	public void authenValidTask() throws Exception {
		try {
			logger.info("*****************进件信息查询开始*****************");
			List<AuthenticationChnnel> authenticationChnnelList = authenticationChnnelService.listByState(1);
			if (null != authenticationChnnelList && authenticationChnnelList.size() > 0) {
				logger.info("*****************authenticationChnnelList.size()*****************："+authenticationChnnelList.size());
				for (AuthenticationChnnel authenticationChnnel : authenticationChnnelList) {
					List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
			        nvps.add(new BasicNameValuePair("requestNo", RequestNo.request_no));
			        nvps.add(new BasicNameValuePair("version", "V2.0"));
			        nvps.add(new BasicNameValuePair("transId", "18"));//进件信息查询
			        nvps.add(new BasicNameValuePair("merNo", ConfigUtils.getProperty(SystemConstant.MS_MCH_ID)));
			        nvps.add(new BasicNameValuePair("applyType", "BANKSP")); //报件类型 ，同报件接口
			        nvps.add(new BasicNameValuePair("applyId", authenticationChnnel.getApplyId()));//报件信息唯一ID
			        Map<String, String> itemMap = BaseRequest.getSignToSend(nvps);
			        logger.info("*****************itemMap*****************：" + itemMap);
			        if (null != itemMap) {
			        	String json = itemMap.get("applyCont");//JSON格式，同报件接口，返回了最终进件状态
			        	String applyId = itemMap.get("applyId");//报件ID
			        	String respCode = itemMap.get("respCode");//响应码
			        	String respDesc = itemMap.get("respDesc");//响应信息
			        	if (null != respCode && "0000".equals(respCode)) {
			        		//查询成功
			        		JSONObject job = JSONObject.parseObject(json);
			        		String state = job.get("state").toString();
			        		String chnlState = job.get("chnlState").toString();
			        		AuthenticationChnnel authention = authenticationChnnelService.findByApplyId(applyId);
			        		if (null != authention) {
			        			if ("WAIT_AUTH".equals(chnlState) || "WAIT_ADD".equals(chnlState) || "ADD_DOING".equals(chnlState)) {
			        				continue;
			        			}
			        			Long storeId = authenticationChnnel.getStoreId();
			        			if ("VALID".equals(state) && ("ADD_SUCC".equals(chnlState) || "MOD_SUCC".equals(chnlState))) {
			        				authention.setState(2); //进件成功
			        				logger.info("****************查询民生状态和渠道状态成功*********************");
				        			StoreInfo storeInfo1 = new StoreInfo();
				        			storeInfo1.setId(storeId);
				        			String code = null;
				        			if (authention.getPayChannelId() == 1) {
				        				storeInfo1.setWxAuthState(2);
				        				code = "微信";
				        			} else if (authention.getPayChannelId() == 2) {
				        				storeInfo1.setZfbAuthState(2);
				        				code = "支付宝";
				        			}
				        			storeInfoMapper.updateByPrimaryKeySelective(storeInfo1);
				        			logger.info("****************修改storeInfo民生进件状态成功标识"+ code +"店铺ID为：" + storeId);
				        		} else {
				        			authention.setState(3); //进件失败
				        			logger.info("****民生查询返回进件失败****：" + state + "*****报件信息唯一ID*****：" + authenticationChnnel.getApplyId());
				        			StoreInfo storeInfo2 = new StoreInfo();
				        			storeInfo2.setId(storeId);
				        			String code1 = null;
				        			if (authention.getPayChannelId() == 1) {
				        				storeInfo2.setWxAuthState(3);
				        				code1 = "微信";
				        			} else if (authention.getPayChannelId() == 2) {
				        				storeInfo2.setZfbAuthState(3);
				        				code1 = "支付宝";
				        			}
				        			storeInfoMapper.updateByPrimaryKeySelective(storeInfo2);
				        			logger.info("****************修改storeInfo民生进件状态失败标识"+ code1 +"店铺ID为：" + storeId);
				        		}
			        			authention.setChnlState(chnlState);
			        			authention.setRespCode(respCode);
		        				authention.setRespDesc(respDesc);
			        			authenticationChnnelService.updateByPrimaryKeySelective(authention);
			        			
			        			//查询某一渠道进件成功后  找出当前店铺两边是否认证成功，修改storeInfo 是否走民生支付通道状态
			        			/*List<AuthenticationChnnel> list = authenticationChnnelService.findByStoreIdAndCountIsSuccess(storeId, 1, 2);
			        			logger.info("****************查询当前店铺认证成功的渠道记录条数：" + list.size());
			        			if (null != list && list.size() == 2) {
			        				//修改storeInfo  进件状态
			        				StoreInfo storeInfo = new StoreInfo();
			        				storeInfo.setId(storeId);
			        				storeInfo.setChannelValidation(1);
			        				storeInfoMapper.updateByPrimaryKeySelective(storeInfo);
			        				logger.info("****************修改storeInfo民生已进件标识，店铺ID为：" + storeId);
			        			}*/
			        			logger.info("****************进件渠道信息修改成功，报件唯一ID：" + authenticationChnnel.getApplyId());
			        		} else {
			        			logger.info("****查询成功*******没有找到报件信息唯一ID*****：" + authenticationChnnel.getApplyId());
			        		}
			        	} else {
			        		 logger.info("****查询失败****，响应码：" + respCode + "响应信息*******，" + respDesc +"*****报件信息唯一ID*****：" + authenticationChnnel.getApplyId());
			        	}
			        }
				}
			}
		} catch (Exception e) {
			logger.info("**************************查询异常，异常信息：", e);
			e.printStackTrace();
		}
	}
}
