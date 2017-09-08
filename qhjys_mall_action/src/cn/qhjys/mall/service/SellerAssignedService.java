package cn.qhjys.mall.service;

import java.util.List;
import cn.qhjys.mall.entity.BankInfo;
import cn.qhjys.mall.entity.CategoryInfo;
import cn.qhjys.mall.entity.CompanyInfo;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.entity.StoreAudit;
import cn.qhjys.mall.entity.StoreCheck;
import cn.qhjys.mall.entity.StoreInfo;

/*
 * 商城商家入驻流程接口
 */
public interface SellerAssignedService {

	/**
	 * 
	 * @Title: saveSellerInfo 注册卖家账户
	 * @param SellerInfo
	 * @return
	 * @throws Exception
	 * @return sellerId
	 */
	public Long saveSellerInfo(SellerInfo sellerInfo) throws Exception;

	/**
	 * 
	 * @Title: updateSellerInfo 修改卖家账户
	 * @param SellerInfo
	 * @return
	 * @throws Exception
	 * @return void
	 */
	public void updateSellerInfo(SellerInfo sellerInfo) throws Exception;

	/***
	 * 验证公司营业执照编号是否唯一
	 * 
	 * @param licenseNumber
	 *            营业执照编号
	 * @return
	 */
	public boolean validateCompanyByLicenseNumber(String licenseNumber) throws Exception;

	/***
	 * 验证公司组织机构代码证是否唯一
	 * 
	 * @param organizationCode
	 *            组织机构代码证
	 * @return
	 */
	public boolean validateCompanyByorganizationCode(String organizationCode) throws Exception;

	/**
	 * 
	 * @Title: saveCompanyInfo 添加卖家所属公司
	 * @param CompanyInfo
	 * @return
	 * @throws Exception
	 * @return void
	 */
	public void saveCompanyInfo(CompanyInfo companyInfo) throws Exception;
	
	public void updateCompanyInfo(CompanyInfo companyInfo) throws Exception;
	
	public CompanyInfo queryCompanyInfo(Long id) throws Exception;

	/**
	 * 
	 * @Title: saveBank 添加卖家银行卡信息
	 * @param bankInfo
	 * @return
	 * @throws Exception
	 * @return void
	 */
	public void saveBankInfo(BankInfo bankInfo) throws Exception;

	/**
	 * 
	 * @Title: saveStoreInfo 添加卖家店铺信息
	 * @param StoreInfo
	 * @return
	 * @throws Exception
	 * @return void
	 */
	public boolean saveStoreInfo(BankInfo bankInfo, StoreInfo storeInfo) throws Exception;
	
	public boolean updateStoreInfo(BankInfo bankInfo, StoreInfo storeInfo) throws Exception;

	/**
	 * 
	 * @Title: addCategory 完善卖家店铺信息
	 * @param storeInfo
	 * @return
	 * @throws Exception
	 * @return void
	 */
	public boolean addCategory(StoreInfo storeInfo) throws Exception;

	/***
	 * 根据商家编号获取店铺信息(以作修改)
	 * 
	 * @param sellerId
	 * @return
	 */
	public StoreInfo queryStoreInfo(Long sellerId) throws Exception;

	/***
	 * 修改店铺信息
	 * 
	 * @param store
	 * @return
	 */
	public boolean updateStore(StoreCheck store) throws Exception;

	/**
	 * 
	 * @Title: queryCategoryInfo 查詢分类信息
	 * @param
	 * @return
	 * @throws Exception
	 * @return CategoryInfo
	 */
	public List<CategoryInfo> queryCategoryInfo() throws Exception;

	/***
	 * 根据商家编号修改店铺正在进行的步骤
	 * 
	 * @param seller
	 * @return
	 */
	public boolean updateSellerById(SellerInfo seller) throws Exception;

	/***
	 * 根据商家编号查询商家正在进行的步骤
	 * 
	 * @param sellerId
	 * @return
	 */
	public SellerInfo querySellerById(Long sellerId) throws Exception;

	/***
	 * 根据店铺编号修改店铺创建时间信息
	 * 
	 * @param store
	 *            店铺对象
	 * @return
	 */
	public boolean updateStoreById(StoreInfo store) throws Exception;

	/***
	 * 根据店铺编号查询店铺审核信息
	 * 
	 * @param storeId
	 *            店铺编号
	 * @return
	 */
	public StoreAudit queryStoreAuditById(Long storeId) throws Exception;

	/***
	 * 根据商家编号查询店铺信息(提供给addBankInfo方法调用)
	 * 
	 * @param sellerId
	 * @return
	 */
	public StoreInfo queryStoreBySellerId(Long sellerId) throws Exception;
	
	public void updateBankInfo(BankInfo bankInfo);
	
	public BankInfo queryBankInfo(Long sellerId);
	
	public StoreInfo queryStoreByName(Long storeId,String storeName);
	
}
