package cn.qhjys.mall.service.fq.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.qhjys.mall.entity.FqStore;
import cn.qhjys.mall.entity.FqStoreExample;
import cn.qhjys.mall.entity.FqStoreExample.Criteria;
import cn.qhjys.mall.mapper.FqStoreMapper;
import cn.qhjys.mall.service.fq.FqStoreService;

@Service("fqStoreService")
public class FqStoreServiceImpl implements FqStoreService {
	
	@Autowired
	private FqStoreMapper fqStoreMapper;

	@Override
	public int insertFqStore(FqStore fqStore) {
		return fqStoreMapper.insertSelective(fqStore);
	}

	@Override
	public int updateFqStore(FqStore fqStore) {
		return fqStoreMapper.updateByPrimaryKeySelective(fqStore);
	}

	@Override
	public FqStore getFqStoreBySellerId(Long sellerId) {
		FqStoreExample example = new FqStoreExample();
		example.createCriteria().andSellerIdEqualTo(sellerId);
		List<FqStore> list = fqStoreMapper.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}

	@Override
	public Page<FqStore> queryFqStore(Map<String, Object> map) {
		FqStoreExample example = new FqStoreExample();
		Criteria criteria = example.createCriteria();
		example.setOrderByClause("create_time desc");
		if (!StringUtils.isEmpty(map.get("storeName"))) {
			criteria.andStoreNameLike(String.valueOf(map.get("storeName")));
		}
		Integer pageNum = 1;
		if (!StringUtils.isEmpty(map.get("pageNum"))) {
			pageNum = Integer.valueOf(String.valueOf(map.get("pageNum")));
		}
		Integer pageSize = 10;
		if (!StringUtils.isEmpty(map.get("pageSize"))) {
			pageSize = Integer.valueOf(String.valueOf(map.get("pageSize")));
		}
		PageHelper.startPage(pageNum, pageSize);
		return (Page<FqStore>) fqStoreMapper.selectByExample(example);
	}

	@Override
	public FqStore getFqStoreById(Long id) {
		return fqStoreMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<FqStore> listFqStoreByDaliy() {
		FqStoreExample example = new FqStoreExample();
		example.createCriteria().andStatusEqualTo(1).andClerkPhoneIsNotNull().andDaliyCreditGreaterThan(BigDecimal.ZERO);
		return  fqStoreMapper.selectByExample(example);
	}

}
