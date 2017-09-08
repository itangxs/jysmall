package cn.qhjys.mall.service;

import java.util.List;

import cn.qhjys.mall.entity.MsAuthentication;

/**
 * 民生银行进件服务接口类
 * @author huangsy
 * @date 2017-07-15
 *
 */
public interface MsAuthenticationService {
	
	int deleteByPrimaryKey(Long id) throws Exception;

    int insert(MsAuthentication record) throws Exception;

    int insertSelective(MsAuthentication record) throws Exception;

    MsAuthentication selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(MsAuthentication record) throws Exception;

    int updateByPrimaryKey(MsAuthentication record) throws Exception;
    
    MsAuthentication findByStoreId(Long storeId) throws Exception;
    
    List<MsAuthentication> listByAll() throws Exception;
}
