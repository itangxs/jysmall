package cn.qhjys.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.entity.MsAuthentication;
import cn.qhjys.mall.mapper.MsAuthenticationMapper;
import cn.qhjys.mall.service.MsAuthenticationService;

@Service("msAuthenticationService")
public class MsAuthenticationServiceImpl implements MsAuthenticationService {

	@Autowired
	private MsAuthenticationMapper msAuthenticationMapper;

	@Override
	public int deleteByPrimaryKey(Long id) throws Exception {
		return msAuthenticationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(MsAuthentication record) throws Exception {
		return msAuthenticationMapper.insert(record);
	}

	@Override
	public int insertSelective(MsAuthentication record) throws Exception {
		return msAuthenticationMapper.insertSelective(record);
	}

	@Override
	public MsAuthentication selectByPrimaryKey(Long id) throws Exception {
		return msAuthenticationMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(MsAuthentication record)
			throws Exception {
		return msAuthenticationMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(MsAuthentication record) throws Exception {
		return msAuthenticationMapper.updateByPrimaryKey(record);
	}

	@Override
	public MsAuthentication findByStoreId(Long storeId) throws Exception {
		return msAuthenticationMapper.findByStoreId(storeId);
	}

	@Override
	public List<MsAuthentication> listByAll() throws Exception {
		return msAuthenticationMapper.listByAll();
	}
}