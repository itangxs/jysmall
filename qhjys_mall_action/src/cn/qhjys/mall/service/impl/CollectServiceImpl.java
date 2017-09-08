package cn.qhjys.mall.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.entity.CollectInfo;
import cn.qhjys.mall.entity.CollectInfoExample;
import cn.qhjys.mall.entity.ProductInfo;
import cn.qhjys.mall.mapper.CollectInfoMapper;
import cn.qhjys.mall.mapper.ProductInfoMapper;
import cn.qhjys.mall.mapper.custom.CollectVoMapper;
import cn.qhjys.mall.service.CollectService;
import cn.qhjys.mall.vo.CollectVo;

import com.github.pagehelper.PageHelper;

@Service("collectService")
public class CollectServiceImpl implements CollectService {
	@Autowired
	private CollectVoMapper collectVoMapper;
	@Autowired
	private CollectInfoMapper collectInfoMapper;
	@Autowired
	private ProductInfoMapper productInfoMapper;

	@Override
	public List<CollectVo> queryUserCollect(Long userId, Integer type, Integer pageNum, Integer pageSize)
			throws Exception {
		if (pageNum == null || pageNum < 1)
			pageNum = 1;
		if (pageSize == null || pageSize < 1)
			pageSize = 10;
		PageHelper.startPage(pageNum, pageSize);
		List<CollectVo> list = null;
		if (type == null || type == 1)
			list = collectVoMapper.selectProductCollectByUser(userId);
		else
			list = collectVoMapper.selectStoreCollectByUser(userId);
		return list;
	}

	@Override
	public boolean judgeProductIsCollect(Long userId, Long prodId) throws Exception {
		CollectInfoExample example = new CollectInfoExample();
		example.createCriteria().andUserIdEqualTo(userId).andProdIdEqualTo(prodId);
		List<CollectInfo> list = collectInfoMapper.selectByExample(example);
		return list != null && list.size() > 0;
	}

	@Override
	public boolean judgeStroeIsCollect(Long userId, Long storeId) throws Exception {
		CollectInfoExample example = new CollectInfoExample();
		example.createCriteria().andUserIdEqualTo(userId).andStoreIdEqualTo(storeId);
		List<CollectInfo> list = collectInfoMapper.selectByExample(example);
		return list != null && list.size() > 0;
	}

	@Override
	public void saveEvaluate(Long id, Long uid, Integer star, Integer evalute_lv, String evalute_explain, String imgurl)
			throws Exception {
	}

	@Override
	public boolean insertUserCollect(Long userId, Long prodId, Long storeId, String tag, String remark)
			throws Exception {
		if (userId == null || (prodId == null && storeId == null))
			return false;
		CollectInfo collect = new CollectInfo();
		collect.setUserId(userId);
		collect.setProdId(prodId);
		ProductInfo pi = null;
		if (prodId != null) {
			pi = productInfoMapper.selectByPrimaryKey(prodId);
		}
		collect.setStoreId(pi == null?storeId:pi.getStoreId());
		collect.setMarkTag(tag);
		collect.setRemark(remark);
		collect.setMarkTime(new Date());
		int result = collectInfoMapper.insertSelective(collect);
		return result > 0;
	}

	@Override
	public boolean deleteUserCollect(Long prodId, Long storeId, Long userId) throws Exception {
		CollectInfoExample example = new CollectInfoExample();
		if (prodId != null)
			example.createCriteria().andProdIdEqualTo(prodId).andUserIdEqualTo(userId);
		else
			example.createCriteria().andStoreIdEqualTo(storeId).andUserIdEqualTo(userId);
		int result = collectInfoMapper.deleteByExample(example);
		return result > 0 ? true : false;
	}

	@Override
	public CollectInfo queryCollectInfo(Long userId, Long productId) {
		CollectInfoExample example = new CollectInfoExample();
		example.createCriteria().andProdIdEqualTo(productId).andUserIdEqualTo(userId);
		List<CollectInfo> list = collectInfoMapper.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}
}