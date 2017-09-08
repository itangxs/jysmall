package cn.qhjys.mall.service.system.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.qhjys.mall.entity.RebateCash;
import cn.qhjys.mall.entity.RebateCashExample;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.entity.StoreRebate;
import cn.qhjys.mall.entity.StoreRebateExample;
import cn.qhjys.mall.mapper.RebateCashMapper;
import cn.qhjys.mall.mapper.StoreInfoMapper;
import cn.qhjys.mall.mapper.StoreRebateMapper;
import cn.qhjys.mall.mapper.custom.StoreRebateVoMapper;
import cn.qhjys.mall.service.system.StoreRebateService;
import cn.qhjys.mall.vo.StoreRebateVo;

@Service
public class StoreRebateServiceImpl implements StoreRebateService {
	@Autowired
	private StoreRebateMapper storeRebateMapper;
	@Autowired
	private StoreRebateVoMapper storeRebateVoMapper;
	@Autowired
	private StoreInfoMapper storeInfoMapper;
	@Autowired
	private RebateCashMapper rebateCashMapper;

	@Override
	public int insertStoreRebate(StoreRebate storeRebate) {
		int a = storeRebateMapper.insertSelective(storeRebate);
		StoreInfo store = storeInfoMapper.selectByPrimaryKey(storeRebate.getStoreId());
		RebateCash cash = new RebateCash();
		cash.setIntegralTotal(BigDecimal.ZERO);
		cash.setNeedTotal(BigDecimal.ZERO);
		cash.setRealTotal(BigDecimal.ZERO);
		cash.setRebateId(storeRebate.getId());
		cash.setSellerId(store.getSellerId());
		cash.setStoreId(storeRebate.getStoreId());
		cash.setTotamtTotal(BigDecimal.ZERO);
		a += rebateCashMapper.insertSelective(cash);
		return a;
	}

	@Override
	public int updateStoreRebate(StoreRebate storeRebate) {
		
		StoreInfo store = storeInfoMapper.selectByPrimaryKey(storeRebate.getStoreId());
		RebateCashExample example = new RebateCashExample();
		example.createCriteria().andRebateIdEqualTo(storeRebate.getId());
		RebateCash cash = rebateCashMapper.selectByExample(example).get(0);
		cash.setSellerId(store.getSellerId());
		cash.setStoreId(storeRebate.getStoreId());
		rebateCashMapper.insertSelective(cash);
		return storeRebateMapper.updateByPrimaryKeySelective(storeRebate);
	}

	@Override
	public Page<StoreRebateVo> queryStoreRebateVos(String storeName,
			String rebateName, String benginTime, String endTime, Integer status,Integer pageNum ,Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(storeName)) {
			map.put("storeName", storeName);
		}
		if (!StringUtils.isEmpty(rebateName)) {
			map.put("rebateName", rebateName);
		}
		if (!StringUtils.isEmpty(benginTime)) {
			map.put("benginTime", benginTime+" 00:00:00");
		}
		if (!StringUtils.isEmpty(endTime)) {
			map.put("endTime", endTime+" 23:59:59");
		}
		if (status != null && status >-1) {
			map.put("status", status);
		}
		PageHelper.startPage(pageNum, pageSize);
		return storeRebateVoMapper.queryStoreRebateVo(map);
	}

	@Override
	public int updateRebateStatus(String ids, Integer status) {
		String [] idarray = ids.split(",");
		for (int i = 0; i < idarray.length; i++) {
			Long id = Long.valueOf(idarray[i]);
			StoreRebate rebate = storeRebateMapper.selectByPrimaryKey(id);
			if (status == 1 && rebate.getStatus() != 1) {
				StoreRebateExample example = new StoreRebateExample();
				example.createCriteria().andStoreIdEqualTo(rebate.getStoreId()).andStatusEqualTo(1);
				int a = storeRebateMapper.countByExample(example);
				if (a>0) {
					return 1;
				}
			}
			rebate.setStatus(status);
			int a = storeRebateMapper.updateByPrimaryKeySelective(rebate);
			if (a <= 0) {
				return 2;
			}
		}
		return 0;
	}

	@Override
	public StoreRebate getStoreRebate(Long id) {
		return storeRebateMapper.selectByPrimaryKey(id);
	}

	@Override
	public StoreRebate getStoreRebateByStoreId(Long storeId) {
		StoreRebateExample example = new StoreRebateExample();
		example.createCriteria().andStoreIdEqualTo(storeId).andStatusEqualTo(1);
		List<StoreRebate> list = storeRebateMapper.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}

	@Override
	public int updateTimeOutRebate() {
		return storeRebateVoMapper.updateTimeOutRebate();
	}

}
