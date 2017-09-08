package cn.qhjys.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.entity.FqUserInfo;
import cn.qhjys.mall.entity.FqUserInfoExample;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.entity.SellerInfoExample;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.entity.StoreInfoExample;
import cn.qhjys.mall.entity.WeixinUserinfo;
import cn.qhjys.mall.entity.WeixinUserinfoExample;
import cn.qhjys.mall.mapper.FqUserInfoMapper;
import cn.qhjys.mall.mapper.SellerInfoMapper;
import cn.qhjys.mall.mapper.StoreInfoMapper;
import cn.qhjys.mall.mapper.WeixinUserinfoMapper;
import cn.qhjys.mall.service.WeixinUserinfoService;

@Service("weixinUserinfoService")
public class WeixinUserinfoServiceImpl implements WeixinUserinfoService {

	@Autowired
	private WeixinUserinfoMapper weixinUserinfoMapper;
	@Autowired
	private StoreInfoMapper storeInfoMapper;
	@Autowired
	private  SellerInfoMapper sellerInfoMapper;
	@Autowired
	private  FqUserInfoMapper fqUserInfoMapper;
	
	
	@Override
	public int insertWeixinUserinfo(WeixinUserinfo weixinUserinfo) {
		return weixinUserinfoMapper.insertSelective(weixinUserinfo);
	}

	@Override
	public int deleteWeixinUserinfo(String openId) {
		WeixinUserinfoExample example = new WeixinUserinfoExample();
		example.createCriteria().andOpenIdEqualTo(openId);
		return weixinUserinfoMapper.deleteByExample(example);
	}

	@Override
	public WeixinUserinfo getWeixinUserinfo(String openId) {
		WeixinUserinfoExample example = new WeixinUserinfoExample();
		example.createCriteria().andOpenIdEqualTo(openId);
		List<WeixinUserinfo> list = weixinUserinfoMapper.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}

	@Override  //0001 账号或者密码错误  0002 没有店铺  0003 已经绑定了 0000 绑定成功
	public String insertWeixinUserinfoAccount(String accout, String password,String openId) throws Exception {
		SellerInfoExample example = new SellerInfoExample();
		example.createCriteria().andUsernameEqualTo(accout).andPasswordEqualTo(password);
		List<SellerInfo> list = sellerInfoMapper.selectByExample(example );
		if(list==null||list.size()<1){
			return "0001";
		}
		SellerInfo sellerInfo = list.get(0);
		
		
		StoreInfoExample example11 = new StoreInfoExample();
		example11.createCriteria().andSellerIdEqualTo(sellerInfo.getId());
		List<StoreInfo> selectByExample = storeInfoMapper.selectByExample(example11 );
		if(null==selectByExample||selectByExample.size()<1){
			return "0002";
		}
		StoreInfo storeInfo = selectByExample.get(0);
		
		
		
	/*	WeixinUserinfoExample example22 = new WeixinUserinfoExample();
		example22.createCriteria().andStoreIdEqualTo(storeInfo.getId());
		List<WeixinUserinfo> list2 = weixinUserinfoMapper.selectByExample(example22 );
		if(null!=list2&&list2.size()>0){
			return "0003";
		}*/
		
		WeixinUserinfoExample example2t=new WeixinUserinfoExample();
		example2t.createCriteria().andOpenIdEqualTo(openId);
		List<WeixinUserinfo> weixinUserinfos = weixinUserinfoMapper.selectByExample(example2t );
		if(null!=weixinUserinfos&&weixinUserinfos.size()>0){
			/*return "0004";*/
			WeixinUserinfo weixinUserinfo = weixinUserinfos.get(0);
			weixinUserinfo.setStoreId(storeInfo.getId());
			weixinUserinfoMapper.updateByPrimaryKey(weixinUserinfo);
		}else{
			WeixinUserinfo record = new WeixinUserinfo();
			record.setStoreId(storeInfo.getId());
			record.setOpenId(openId);
			weixinUserinfoMapper.insert(record );
		}
		FqUserInfoExample example2 = new FqUserInfoExample();
		example2.createCriteria().andOpenIdEqualTo(openId);
		List<FqUserInfo> list2= fqUserInfoMapper.selectByExample(example2);
		if (list2.size()>0) {
			FqUserInfo fquser = list2.get(0);
			fquser.setStoreId(storeInfo.getSellerId());
			fqUserInfoMapper.updateByPrimaryKeySelective(fquser);
		}else{
			FqUserInfo fquser = new FqUserInfo();
			fquser.setOpenId(openId);
			fquser.setStoreId(storeInfo.getSellerId());
			fqUserInfoMapper.insertSelective(fquser);
		}
		return "0000";
	}

	@Override
	public WeixinUserinfo getWeixinUserinfoByStoreId(Long storeid) throws Exception {
		WeixinUserinfoExample example = new WeixinUserinfoExample();
		example.createCriteria().andStoreIdEqualTo(storeid);
		List<WeixinUserinfo> selectByExample = weixinUserinfoMapper.selectByExample(example);
		return selectByExample.size()>0?selectByExample.get(0):null;
	}

	@Override
	public List<WeixinUserinfo> selectByExample(WeixinUserinfoExample example) {
		return weixinUserinfoMapper.selectByExample(example);
	}

}
