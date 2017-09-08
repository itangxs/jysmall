package cn.qhjys.mall.service;

import java.util.List;

import cn.qhjys.mall.entity.WeixinUserinfo;
import cn.qhjys.mall.entity.WeixinUserinfoExample;

public interface WeixinUserinfoService {
	public int insertWeixinUserinfo(WeixinUserinfo weixinUserinfo);
	public int deleteWeixinUserinfo(String openId);
	public WeixinUserinfo getWeixinUserinfo(String openId);
	public String insertWeixinUserinfoAccount(String accout, String password,String openId)throws Exception;
	public WeixinUserinfo getWeixinUserinfoByStoreId(Long storeid)throws Exception;
	 List<WeixinUserinfo> selectByExample(WeixinUserinfoExample example);
}
