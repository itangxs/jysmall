package cn.qhjys.mall.weixin.util;



/**
 * 系统常量
* @Description: 
* @author liuliwei  
* @date 2016-03-07
 */
public class SystemConstant {
	
	/**待支付*/
	public final static Integer PAY_NOPAY=1;
	/**已支付*/
	public final static Integer PAY_ISPAY=2;
	/**已取消*/
	public final static Integer PAY_CANCLEPAY=3;
	
	public final static Integer PAY_WAITPAY=0;
	
	
	
	/**不存在*/
	public final static String NO_EXIST="2000";
	/**参数不存在*/
	public final static String NO_PARAMS="2001";
	/**数据不一致*/
	public final static String DATA_DIFF="2002";
	/**数据失效*/
	public final static String DATA_CANCLE="2003";
	/**成功*/
	public final static String SUCCESS="0000";
	/**失败*/
	public final static String ERROR="1000";
	/**微信支付方式*/
	public final static String WX_PAY_TYPE="WX";
	public final static String ALI_PAY_TYPE="AL";
	//浦发微信用户扫码支付
	public final static String PF_WX_PAY_TYPE="PW";
	//浦发微信用户扫码支付
	public final static String SZ_PF_WX_PAY_TYPE="SP";
	public final static String SZ_PF_WX_APP_PAY_TYPE="SA";
	public final static String SZ_PF_WX_DESK_PAY_TYPE="SD";
	//浦发支付宝用户扫码支付
	public final static String PF_ALI_PAY_TYPE="PA";
	//兴业支付宝公众号支付
	public final static String XY_ALI_PAY_TYPE="XA";
	//兴业微信公众号支付
	public final static String XY_WX_PAY_TYPE="XW";
	
	//光大支付宝用户扫码支付
	public final static String GD_ALI_PAY_TYPE="GA";
	
	//浦发支付QQ扫码支付
	public final static String PF_QQ_PAY_TYPE="PQ";
	public final static String WFT_PAY_TYPE="WF";
	public final static String QQ_PAY_TYPE="QQ";
	public final static String GD_PAY_TYPE="GD";
	public final static String WX_MICOR_PAY_TYPE="WS";
	public final static String ALI_MICOR_PAY_TYPE="AS";
	public final static String GD_MICOR_PAY_TYPE="GS";
	public final static String QQ_MICOR_PAY_TYPE="QS";
	
	public final static String GD_WX_MICOR_PAY_TYPE="GW";
	public final static String GD_ALI_MICOR_PAY_TYPE="GI";
	
	//浦发微信扫码枪支付
	public final static String PF_WX_MICOR_PAY_TYPE="PX";
	//浦发支付宝扫码枪支付
	public final static String PF_ALI_MICOR_PAY_TYPE="PI";
	/**兴业建联start */
	//兴业二清微信APP刷卡支付
	public final static String XY_WX_MICOR_PAY_TYPE="XX";
	//兴业二清支付宝APP刷卡支付
	public final static String XY_ALI_MICOR_PAY_TYPE="XI";
	//兴业微信二清扫码
	public final static String XY_WX_MICOR_EQPAY_TYPE="EX";
	//兴业支付宝二清扫码
	public final static String XY_ALI_MICOR_EQPAY_TYPE="EA";
	//兴业微信二清
	public final static String XY_WX_EQPAY_TYPE="XE";
	//兴业支付宝二清
	public final static String XY_ALI_EQPAY_TYPE="AE";
	/**兴业建联end */
	//旺POS机 支付
	public final static String W_POS_MICOR_PAY_TYPE="OS";
	
	//第三方帐号类型 微信
	public final static String TYPE_WECHAT="wechat";
	//第三方帐号类型 qq
	public final static String TYPE_QQ="qq";
	//第三方帐号类型 微博
	public final static String TYPE_WEIBO="weibo";
	
	/**禁止登陆*/
	public final static String FORBIT="3000";
	/**禁止操作*/
	public final static String FORBIT_OPERATE="3001";
	
	/**微信用户id session key*/
	public final static String SESSION_WECHAT_USER_ID="wechat_user_id";
	/**店铺idsession key*/
	public final static String SESSION_STORE_ID ="store_id";
	
	/**微信公众号 token*/
	public final static String WECHAT_TOKEN ="weixin_token";
	/**微信公众号 appId*/
	public final static String WECHAT_APPID ="weixin_appid";
	/**微信公众号 appsecret*/
	public final static String WECHAT_APPSECRET ="weixin_appsecret";
	/**微信oauth2  默认获取code url*/
	public final static String WECHAT_SNSAPI_BASE_URL="wechat_snsapi_base_url";
	/**微信oauth2 用户确认获取code url*/
	public final static String WECHAT_SNSAPI_USERINFO_URL="wechat_snsapi_userinfo_url";
	/**微信oauth2 获取 access_token 和 openId url*/
	public final static String WECHAT_ACCESS_TOKEN_URL="wechat_access_token_url";
	/**微信oauth2 获取 用户信息 url*/
	public final static String WECHAT_USERINFO_URL="wechat_userinfo_url";
	/**缓存 微信oauth2 code*/
	public final static String WX_OAUTH2_CODE="wx_oauth2_code";
	/**缓存 微信oauth2 access_token*/
	public final static String WX_OAUTH2_ACCESS_TOKEN="wx_oauth2_access_token";
	/**缓存 微信oauth2 openId*/
	public final static String WX_OAUTH2_OPENID="wx_oauth2_openId";
	/**缓存 微信用户信息对象*/
	public final static String WX_USERINFO_JSON="wx_userinfo_json";
	/**本地域名*/
	public final static String LOCAL_HOST="";
	
	/** 微信js支付api url*/
	public final static String WECHAT_PAY_JSAPI_URL="wechat_pay_jsapi_url";
	/** 微信js支付key*/
	public final static String WECHAT_PAY_PKEY="wechat_pay_pkey";
	/** 微信js支付partner */
	public final static String WECHAT_PAY_PARTNER="wechat_pay_partner";
	/** 微信js支付回调url */
	public final static String WECHAT_PAY_NOTIFYURL="wechat_pay_notifyurl";
	public final static String WECHAT_PAY_NOTIFYURLYF="wechat_pay_notifyurlyf";
	public final static String WECHAT_PAY_NOTIFYURLWK="wechat_pay_notifyurlwk";
	
	public final static String WFT_MCH_ID="wft_pay_mch_id";
	public final static String WFT_PAY_KEY="wft_pay_md5_key";
	public final static String GD_MCH_ID="gd_pay_mch_id";
	public final static String GD_PAY_KEY="gd_pay_md5_key";
	
	//深圳浦发
	public final static String SZ_PF_MCH_ID="sz_pf_pay_mch_id";
	public final static String SZ_PF_WX_MCH_ID="sz_pf_wx_mch_id";
	public final static String SZ_PF_ALI_MCH_ID="sz_pf_ali_mch_id";
	public final static String SZ_PF_PAY_WX_NOTIFYURL="sz_pf_pay_wx_notifyurl";
	public final static String SZ_PF_PAY_WX_RETURNURL="sz_pf_pay_wx_returnurl";
	
	public final static String SZ_PF_PAY_WX_MC_NOTIFYURL="z_pf_pay_wx_mc_notifyurl";
	
	
	//浦发银行
	public final static String PF_MCH_ID="pf_pay_mch_id";
	public final static String PF_PAY_KEY="pf_pay_md5_key";
	public final static String PF_PAY_AL_NOTIFYURL="pf_pay_al_notifyurl";//用户扫码 阿里异步回调
	public final static String PF_PAY_WX_NOTIFYURL="pf_pay_wx_notifyurl";//用户扫码 微信异步回调
	public final static String PF_PAY_QQ_NOTIFYURL="pf_pay_qq_notifyurl";//用户扫码 QQ异步回调
	
	//兴业银行
	public final static String XY_MCH_ID="xy_pay_mch_id";
	public final static String XY_PAY_AL_NOTIFYURL="xy_pay_al_notifyurl";//用户扫码 阿里异步回调
	public final static String XY_PAY_WX_NOTIFYURL="xy_pay_wx_notifyurl";//用户扫码 微信异步回调
	
	public final static String WFT_PAY_NOTIFYURLYF="wft_pay_notifyurlyf";
	public final static String WFT_PAY_NOTIFYURL="wft_pay_notifyurl";
	public final static String GD_PAY_NOTIFYURLYF="gd_pay_notifyurlyf";
	public final static String GD_PAY_NOTIFYURL="gd_pay_notifyurl";
	
	public final static String GD_PAY_AL_NOTIFYURL="gd_pay_al_notifyurl";
	
	public final static String PF_PAY_NOTIFYURLYF="pf_pay_notifyurlyf";
	
	/** QQ支付相关参数配置key  */
	public final static String QQ_MCH_ID="qq_pay_mch_id";
	public final static String QQ_PAY_CERT_FILE="qq_pay_cert_file";
	public final static String QQ_PAY_CA_FILE="qq_pay_ca_file";
	public final static String QQ_PAY_NOTIFYURL="qq_pay_notifyurl";
	public final static String QQ_MICRO_PAY_NOTIFYURL="qq_micro_pay_notifyurl";
	public final static String QQ_PAY_API_KEY="qq_pay_api_key";
	
	/** 旺pos 相关参数配置 */
	public final static String WANG_POS_API_KEY="wang_pos_api_key";
	public final static String WANG_POS_TOKEN="wang_pos_api_token";
	
	/**	民生银行相关 start*/
	//服务商在民生银行的商户号
	public final static String MS_MCH_ID="ms_pay_mch_id";
	//异步通知地址
	public final static String MS_PAY_ALL_NOTIFYURL="ms_pay_all_notifyurl"; 
	//测试地址
	public final static String MS_PAY_TEST_URL="ms_pay_test_url";
	//生产地址
	public final static String MS_PAY_FORMAL_URL="ms_pay_formal_url";
	//页面通知地址
	public final static String MS_PAY_RETURNURL="ms_pay_returnUrl";
	//民生微信
	public final static String MS_WX_PAY_TYPE="MW";
	//民生微信店铺点餐订单
	public final static String MS_WX_STROE_PAY_TYPE="WM";
	//民生支付宝
	public final static String MS_ALI_PAY_TYPE="MA";
	//民生QQ
	public final static String MS_QQ_PAY_TYPE="MQ";
	//民生微信扫码枪支付
	public final static String MS_WX_MICOR_PAY_TYPE="MX";
	//民生支付宝扫码枪支付
	public final static String MS_ALI_MICOR_PAY_TYPE="MI";
	//民生支付宝扫码枪支付
	public final static String MS_QQ_MICOR_PAY_TYPE="MT";
	
	public final static String MS_PUBLIC_KEY_PATH="public_key_path";
	public final static String MS_PRIVATE_KEY_PATH="private_key_path";
	
	
	//微信公众号支付授权目录
	public final static String MS_AUTH_PAYDIR="auth_paydir";
	//微信APPID
	public final static String MS_WEIXIN_APPID="ms_weixin_appid";
	//微信渠道号
	public final static String MS_WEIXIN_CHANNEL_ID="wei_xin_channel_id";
	//支付宝进件民生PID
	public final static String MS_ZFB_BANK_ACCEPT_APPID="bank_accept_appid";
	/**	民生银行相关 end*/
}
