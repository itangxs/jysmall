package cn.qhjys.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.entity.BankInfo;
import cn.qhjys.mall.entity.BankInfoExample;
import cn.qhjys.mall.entity.CategoryInfo;
import cn.qhjys.mall.entity.CategoryInfoExample;
import cn.qhjys.mall.entity.CompanyInfo;
import cn.qhjys.mall.entity.CompanyInfoExample;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.entity.StoreAudit;
import cn.qhjys.mall.entity.StoreAuditExample;
import cn.qhjys.mall.entity.StoreCheck;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.entity.StoreInfoExample;
import cn.qhjys.mall.entity.StoreInfoExample.Criteria;
import cn.qhjys.mall.mapper.AreaInfoMapper;
import cn.qhjys.mall.mapper.BankInfoMapper;
import cn.qhjys.mall.mapper.CategoryInfoMapper;
import cn.qhjys.mall.mapper.CityInfoMapper;
import cn.qhjys.mall.mapper.CompanyInfoMapper;
import cn.qhjys.mall.mapper.ProvinceInfoMapper;
import cn.qhjys.mall.mapper.SellerInfoMapper;
import cn.qhjys.mall.mapper.StoreAuditMapper;
import cn.qhjys.mall.mapper.StoreCheckMapper;
import cn.qhjys.mall.mapper.StoreInfoMapper;
import cn.qhjys.mall.service.SellerAssignedService;

@Service
public class SellerAssignedServiceImpl implements SellerAssignedService {

	@Autowired
	private SellerInfoMapper sellerInfoMapper;
	@Autowired
	private CompanyInfoMapper companyInfoMapper;
	@Autowired
	private BankInfoMapper bankInfoMapper;
	@Autowired
	private StoreInfoMapper storeInfoMapper;
	@Autowired
	private CategoryInfoMapper categoryInfoMapper;
	@Autowired
	private ProvinceInfoMapper provinceInfoMapper;
	@Autowired
	private CityInfoMapper cityInfoMapper;
	@Autowired
	private AreaInfoMapper areaInfoMapper;
	@Autowired
	private StoreCheckMapper storeCheckMapper;
	@Autowired
	private StoreAuditMapper storeAuditMapper;

	@Override
	public Long saveSellerInfo(SellerInfo sellerInfo) throws Exception {
		sellerInfoMapper.insertSelective(sellerInfo);
		return sellerInfo.getId();
	}

	@Override
	public void updateSellerInfo(SellerInfo sellerInfo) throws Exception {
		sellerInfoMapper.updateByPrimaryKeySelective(sellerInfo);
	}

	@Override
	public boolean validateCompanyByLicenseNumber(String licenseNumber) throws Exception {
		CompanyInfoExample example = new CompanyInfoExample();
		boolean isSuccess = true;
		example.createCriteria().andLicenseNumberEqualTo(licenseNumber);
		List<CompanyInfo> list = companyInfoMapper.selectByExample(example);
		if (list.size() > 0) {
			isSuccess = false;
		}
		return isSuccess;
	}

	@Override
	public boolean validateCompanyByorganizationCode(String organizationCode) throws Exception {
		CompanyInfoExample example = new CompanyInfoExample();
		boolean isSuccess = true;
		example.createCriteria().andOrganizationCodeEqualTo(organizationCode);
		List<CompanyInfo> list = companyInfoMapper.selectByExample(example);
		if (list.size() > 0) {
			isSuccess = false;
		}
		return isSuccess;
	}

	@Override
	public void saveCompanyInfo(CompanyInfo companyInfo) throws Exception {
		companyInfoMapper.insertSelective(companyInfo);
	}

	@Override
	public void saveBankInfo(BankInfo bankInfo) throws Exception {
		bankInfoMapper.insertSelective(bankInfo);
	}

	@Override
	public boolean saveStoreInfo(BankInfo bankInfo, StoreInfo storeInfo) throws Exception {
		int bankCount = bankInfoMapper.insertSelective(bankInfo);
		int storeCount = storeInfoMapper.insertSelective(storeInfo);
		int count = 0;
		if (bankCount > 0 && storeCount > 0) {
			count = 1;
		}
		return count > 0 ? true : false;
	}
	
	@Override
	public boolean updateStoreInfo(BankInfo bankInfo, StoreInfo storeInfo) throws Exception {
		int bankCount = bankInfoMapper.updateByPrimaryKeySelective(bankInfo);
		int storeCount = storeInfoMapper.updateByPrimaryKeySelective(storeInfo);
		int count = 0;
		if (bankCount > 0 && storeCount > 0) {
			count = 1;
		}
		return count > 0 ? true : false;
	}

	@Override
	public boolean addCategory(StoreInfo storeInfo) throws Exception {
		int row = storeInfoMapper.updateByPrimaryKeySelective(storeInfo);
		return row > 0 ? true : false;
	}

	@Override
	public StoreInfo queryStoreInfo(Long sellerId) throws Exception {
		StoreInfoExample example = new StoreInfoExample();
		example.createCriteria().andSellerIdEqualTo(sellerId).andStatusEqualTo(2);
		List<StoreInfo> list = storeInfoMapper.selectByExampleWithBLOBs(example);
		StoreInfo storeInfo = null;
		if (list.size() > 0) {
			storeInfo = list.get(0);
		}
		return storeInfo;
	}

	@Override
	public boolean updateStore(StoreCheck store) throws Exception {
		int row = storeCheckMapper.insertSelective(store);
		return row > 0 ? true : false;
	}

	@Override
	public List<CategoryInfo> queryCategoryInfo() throws Exception {
		CategoryInfoExample example = new CategoryInfoExample();
		example.createCriteria().andEnabledEqualTo(1);
		return categoryInfoMapper.selectByExample(example);
	}

	@Override
	public boolean updateSellerById(SellerInfo seller) throws Exception {
		int row = sellerInfoMapper.updateByPrimaryKeySelective(seller);
		return row > 0 ? true : false;
	}

	@Override
	public SellerInfo querySellerById(Long sellerId) throws Exception {
		return sellerInfoMapper.selectByPrimaryKey(sellerId);
	}

	@Override
	public boolean updateStoreById(StoreInfo store) throws Exception {
		int row = storeInfoMapper.updateByPrimaryKeySelective(store);
		return row > 0 ? true : false;
	}

	@Override
	public StoreAudit queryStoreAuditById(Long storeId) throws Exception {
		StoreAuditExample example = new StoreAuditExample();
		example.createCriteria().andStoreIdEqualTo(storeId);
		example.setOrderByClause("time desc");
		List<StoreAudit> list = storeAuditMapper.selectByExample(example);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public StoreInfo queryStoreBySellerId(Long sellerId) throws Exception {
		StoreInfoExample example = new StoreInfoExample();
		example.createCriteria().andSellerIdEqualTo(sellerId);
		List<StoreInfo> list = storeInfoMapper.selectByExampleWithBLOBs(example);
		StoreInfo storeInfo = null;
		if (list.size() > 0) {
			storeInfo = list.get(0);
		}
		return storeInfo;
	}

	@Override
	public CompanyInfo queryCompanyInfo(Long id) throws Exception {
		return companyInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateCompanyInfo(CompanyInfo companyInfo) throws Exception {
		companyInfoMapper.updateByPrimaryKeySelective(companyInfo);
	}

	@Override
	public void updateBankInfo(BankInfo bankInfo) {
		bankInfoMapper.updateByPrimaryKeySelective(bankInfo);
	}

	@Override
	public BankInfo queryBankInfo(Long sellerId) {
		BankInfoExample example = new BankInfoExample();
		example.createCriteria().andSellerIdEqualTo(sellerId);
		List<BankInfo> banks = bankInfoMapper.selectByExample(example);
		return banks.size()>0?banks.get(0):null;
	}

	@Override
	public StoreInfo queryStoreByName(Long storeId,String storeName) { 
		StoreInfoExample example = new StoreInfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(storeName);
		if (storeId != null) {
			criteria.andIdNotEqualTo(storeId);
		}
		List<StoreInfo> list = storeInfoMapper.selectByExampleWithBLOBs(example);
		StoreInfo storeInfo = null;
		if (list.size() > 0) {
			storeInfo = list.get(0);
		}
		return storeInfo;
	}

}