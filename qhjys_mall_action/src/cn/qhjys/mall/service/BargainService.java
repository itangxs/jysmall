package cn.qhjys.mall.service;

import java.util.List;
import cn.qhjys.mall.entity.Bargain;
import cn.qhjys.mall.entity.BargainInfo;
import cn.qhjys.mall.entity.UserBargain;

public interface BargainService {
	public int insertBargain(Bargain bargain);
	public int updateBargain(Bargain bargain);
	
	public Bargain getBargain(Long id);
	
	public Bargain getLastBargain();
	
	public UserBargain getUserBargainByOpenid(String openId,Long bargainId);
	public UserBargain getUserBargain(Long id);
	
	public int insertUserBargain(UserBargain userBargain);
	
	public List<BargainInfo> listBargainInfos(Long userBargainId);
	
	public BargainInfo getBargainInfoByOpenId(String openId,Long userBargainId);
	
	public int insertBargainInfo(BargainInfo bargainInfo)throws Exception;
	public int updateUserBargain(UserBargain userBargain);
	public int insertBargainInfo1(BargainInfo info)throws Exception;
}
