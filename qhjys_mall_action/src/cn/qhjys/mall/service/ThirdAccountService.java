package cn.qhjys.mall.service;

import cn.qhjys.mall.entity.ThirdAccount;

public interface ThirdAccountService {
	/**
	 * 查询关联的第三方账号
	 * 
	 * @param accountId
	 * @param thirdCode
	 * @return
	 */
	public ThirdAccount queryThirdAccount(Long accountId, String thirdCode) throws Exception;

	public boolean insertThirdAccount(ThirdAccount thirdAccount) throws Exception;

}