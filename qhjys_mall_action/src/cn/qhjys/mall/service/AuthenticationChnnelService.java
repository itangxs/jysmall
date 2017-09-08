package cn.qhjys.mall.service;

import java.util.List;

import cn.qhjys.mall.entity.AuthenticationChnnel;

/**
 * 民生银行进件通道服务接口类
 * @author huangsy
 * @date 2017-07-15
 *
 */
public interface AuthenticationChnnelService {

	int deleteByPrimaryKey(Long id) throws Exception;

    int insert(AuthenticationChnnel record) throws Exception;

    int insertSelective(AuthenticationChnnel record) throws Exception;

    AuthenticationChnnel selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(AuthenticationChnnel record) throws Exception;

    int updateByPrimaryKey(AuthenticationChnnel record) throws Exception;
    
    List<AuthenticationChnnel> listByState(Integer state) throws Exception;
    
    AuthenticationChnnel findByApplyId(String applyId) throws Exception;
    
    AuthenticationChnnel findByStoreIdAndBankIdAndPayChannelAndState(Long storeId, Integer bankId, Integer payChannel, Integer state) throws Exception;

    AuthenticationChnnel findByIsValidAuthentication(Long storeId, Integer bankId, Integer payChannel, Integer state, Integer state1) throws Exception;

    List<AuthenticationChnnel> findByStoreIdAndCountIsSuccess(Long storeId, Integer bankId, Integer state) throws Exception;

    List<AuthenticationChnnel> findByStoreId(Long storeId) throws Exception;
    
    AuthenticationChnnel findByXyAuthcationInfo(Long storeId) throws Exception;
    
    AuthenticationChnnel findByXyyqAuthcationInfo(Long storeId, Integer bankId, Integer state) throws Exception;
}
