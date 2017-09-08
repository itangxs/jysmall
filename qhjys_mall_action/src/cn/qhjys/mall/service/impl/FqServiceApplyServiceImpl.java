package cn.qhjys.mall.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.qhjys.mall.entity.FqFinancialSupport;
import cn.qhjys.mall.entity.FqFinancialSupportExample;
import cn.qhjys.mall.entity.FqServiceApply;
import cn.qhjys.mall.entity.FqServiceApplyExample;
import cn.qhjys.mall.entity.FqServiceApplyExample.Criteria;
import cn.qhjys.mall.mapper.FqFinancialSupportMapper;
import cn.qhjys.mall.mapper.FqServiceApplyMapper;
import cn.qhjys.mall.service.FqServiceApplyService;
import cn.qhjys.mall.util.BaseUtil;

@Service("fqServiceApplyService")
public class FqServiceApplyServiceImpl implements FqServiceApplyService {
	
	@Autowired
	private FqServiceApplyMapper fqServiceApplyMapper;
	@Autowired
	private FqFinancialSupportMapper fqFinancialSuportMapper;
	
	@Override
	public Page<FqServiceApply> queryFqServiceApply(Map<String, Object> map) {
		FqServiceApplyExample example = new FqServiceApplyExample();
		Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(map.get("sellerId"))) {
			criteria.andSellerIdEqualTo(Long.valueOf(String.valueOf(map.get("sellerId"))));
		}
		if (!StringUtils.isEmpty(map.get("storeId"))) {
			criteria.andStoreIdEqualTo(Long.valueOf(String.valueOf(map.get("storeId"))));
		}
		if (!StringUtils.isEmpty(map.get("applyType"))) {
			criteria.andApplyTypeEqualTo(Integer.valueOf(String.valueOf(map.get("applyType"))));
		}
		if (!StringUtils.isEmpty(map.get("status"))) {
			criteria.andStatusEqualTo(Integer.valueOf(String.valueOf(map.get("status"))));
		}
		if (!StringUtils.isEmpty(map.get("storeName"))) {
			criteria.andStoreNameLike("%"+String.valueOf(map.get("storeName"))+"%");
		}
		if (!StringUtils.isEmpty(map.get("beginTime"))) {
			criteria.andCreateTimeGreaterThanOrEqualTo(BaseUtil.toDate(String.valueOf(map.get("beginTime"))+" 00:00:00"));
		}
		if (!StringUtils.isEmpty(map.get("endTime"))) {
			criteria.andCreateTimeLessThanOrEqualTo(BaseUtil.toDate(String.valueOf(map.get("endTime"))+" 23:59:59"));
		}
		example.setOrderByClause("create_time DESC");
		Integer pageNum = 1;
		if (!StringUtils.isEmpty(map.get("pageNum"))) {
			pageNum = Integer.valueOf(String.valueOf(map.get("pageNum")));
		}
		Integer pageSize = 10;
		if (!StringUtils.isEmpty(map.get("pageSize"))) {
			pageSize = Integer.valueOf(String.valueOf(map.get("pageSize")));
		}
		PageHelper.startPage(pageNum, pageSize);
		return (Page<FqServiceApply>) fqServiceApplyMapper.selectByExample(example);
	}

	@Override
	public int insertFqServiceApply(FqServiceApply fqServiceApply) {
		return fqServiceApplyMapper.insertSelective(fqServiceApply);
	}

	@Override
	public int updateFqServiceApplyStatus(Long id, Integer status) {
		FqServiceApply apply =  getFqServiceApplyById(id);
		if (apply == null) {
			return -5;//申请不存在
		}
		if (apply.getStatus()>status) {
			return -apply.getStatus();
		}
		apply.setStatus(status);
		int a = fqServiceApplyMapper.updateByPrimaryKeySelective(apply);
		return a;
	}

	@Override
	public FqServiceApply getFqServiceApplyById(Long id) {
		return fqServiceApplyMapper.selectByPrimaryKey(id);
	}

	@Override
	public FqFinancialSupport getFqFinancialSupportByApplyId(Long applyId) {
		FqFinancialSupportExample example = new FqFinancialSupportExample();
		example.createCriteria().andApplyIdEqualTo(applyId);
		List<FqFinancialSupport> list = fqFinancialSuportMapper.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}

	@Override
	public int updateFqServiceApplyStatus(String ids, Integer status) {
		if (StringUtils.isEmpty(ids) || status == null || status <1 || status >3) {
			return -1;
		}
		String [] aids = ids.split(",");
		int a = 0;
		for (int i = 0; i < aids.length; i++) {
			Long id = Long.valueOf(aids[i]);
			FqServiceApply apply =  getFqServiceApplyById(id);
			if (apply != null) {
				apply.setStatus(status);
				a+=fqServiceApplyMapper.updateByPrimaryKeySelective(apply);
			}
		}
		return a;
	}

	@Override
	public int insertSelective(FqFinancialSupport record) {
		// TODO Auto-generated method stub
		return fqFinancialSuportMapper.insertSelective(record);
	}

	@Override
	public int insertFinancialSupport(FqServiceApply fqServiceApply,
			FqFinancialSupport record) {
		int a = fqServiceApplyMapper.insertSelective(fqServiceApply);

		if (a>0) {
			record.setApplyId(fqServiceApply.getId());
			return insertSelective(record);
		} 
		return 0;

	}

}
