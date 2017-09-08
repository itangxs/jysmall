package cn.qhjys.mall.service;

import cn.qhjys.mall.entity.RegisterActivity;
import com.github.pagehelper.Page;

public interface RegisterActivityService {

	public int insertRegisterActivity(RegisterActivity registerActivity);

	public int updateRegisterActivity(RegisterActivity registerActivity);

	public RegisterActivity getRegisterActivityByDate(Long storeId);

	public RegisterActivity getRegisterActivity(Long activeId);

	public Page<RegisterActivity> queryRegisterActiveByPage(int pageNum, int pageSize);
}
