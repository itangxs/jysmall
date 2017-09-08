package cn.qhjys.mall.service.system;

import com.github.pagehelper.Page;

import cn.qhjys.mall.entity.StoreRebate;
import cn.qhjys.mall.vo.StoreRebateVo;

public interface StoreRebateService {
	
	public int insertStoreRebate(StoreRebate storeRebate);
	
	public int updateStoreRebate(StoreRebate storeRebate);
	
	public Page<StoreRebateVo> queryStoreRebateVos(String storeName,String rebateName,String benginTime,String endTime,Integer status,
			Integer pageNum ,Integer pageSize);
	
	public int updateRebateStatus(String ids,Integer status);
	
	public StoreRebate getStoreRebate(Long id);
	
	public StoreRebate getStoreRebateByStoreId(Long storeId);
	public int updateTimeOutRebate();
}
