package cn.qhjys.mall.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.entity.AuthenticationChnnel;
import cn.qhjys.mall.mapper.AuthenticationChnnelMapper;
import cn.qhjys.mall.service.AuthenticationChnnelService;

@Service("authenticationChnnelService")
public class AuthenticationChnnelServiceImpl implements AuthenticationChnnelService {
	@Autowired
	private AuthenticationChnnelMapper authenticationChnnelMapper;

	@Override
	public int deleteByPrimaryKey(Long id) throws Exception {
		return authenticationChnnelMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(AuthenticationChnnel record) throws Exception {
		return authenticationChnnelMapper.insert(record);
	}

	@Override
	public int insertSelective(AuthenticationChnnel record) throws Exception {
		return authenticationChnnelMapper.insertSelective(record);
	}

	@Override
	public AuthenticationChnnel selectByPrimaryKey(Long id) throws Exception {
		return authenticationChnnelMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(AuthenticationChnnel record)
			throws Exception {
		return authenticationChnnelMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AuthenticationChnnel record) throws Exception {
		return authenticationChnnelMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<AuthenticationChnnel> listByState(Integer state)  throws Exception{
		return authenticationChnnelMapper.listByState(state);
	}

	@Override
	public AuthenticationChnnel findByApplyId(String applyId) throws Exception {
		return authenticationChnnelMapper.findByApplyId(applyId);
	}

	@Override
	public AuthenticationChnnel findByStoreIdAndBankIdAndPayChannelAndState(
			Long storeId, Integer bankId, Integer payChannel, Integer state)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("storeId", storeId);
		map.put("bankNameId", bankId);
		map.put("payChannelId", payChannel);
		map.put("state", state);
		return authenticationChnnelMapper.findByStoreIdAndBankIdAndPayChannelAndState(map);
	}

	@Override
	public AuthenticationChnnel findByIsValidAuthentication(Long storeId,
			Integer bankId, Integer payChannel, Integer state, Integer state1)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("storeId", storeId);
		map.put("bankNameId", bankId);
		map.put("payChannelId", payChannel);
		map.put("state", state);
		map.put("state1", state1);
		return authenticationChnnelMapper.findByIsValidAuthentication(map);
	}

	@Override
	public List<AuthenticationChnnel> findByStoreIdAndCountIsSuccess(
			Long storeId, Integer bankId, Integer state) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("storeId", storeId);
		map.put("bankNameId", bankId);
		map.put("state", state);
		return authenticationChnnelMapper.findByStoreIdAndCountIsSuccess(map);
	}

	@Override
	public List<AuthenticationChnnel> findByStoreId(Long storeId)
			throws Exception {
		return authenticationChnnelMapper.findByStoreId(storeId);
	}

	@Override
	public AuthenticationChnnel findByXyAuthcationInfo(Long storeId)
			throws Exception {
		return authenticationChnnelMapper.findByXyAuthcationInfo(storeId);
	}

	@Override
	public AuthenticationChnnel findByXyyqAuthcationInfo(Long storeId,
			Integer bankId, Integer state) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("storeId", storeId);
		map.put("bankNameId", bankId);
		map.put("state", state);
		return authenticationChnnelMapper.findByXyyqAuthcationInfo(map);
	}
}