package cn.qhjys.mall.mapper;

import java.util.List;
import java.util.Map;

import cn.qhjys.mall.entity.AuthenticationChnnel;

public interface AuthenticationChnnelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AuthenticationChnnel record);

    int insertSelective(AuthenticationChnnel record);

    AuthenticationChnnel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AuthenticationChnnel record);

    int updateByPrimaryKey(AuthenticationChnnel record);
    
    List<AuthenticationChnnel> listByState(Integer state);
    
    AuthenticationChnnel findByApplyId(String applyId) throws Exception;
    
    AuthenticationChnnel findByStoreIdAndBankIdAndPayChannelAndState(Map<String, Object> map) throws Exception;
    
    AuthenticationChnnel findByIsValidAuthentication(Map<String, Object> map) throws Exception;
    
    List<AuthenticationChnnel> findByStoreIdAndCountIsSuccess(Map<String, Object> map) throws Exception;
    
    List<AuthenticationChnnel> findByStoreId(Long storeId) throws Exception;
    
    AuthenticationChnnel findByXyAuthcationInfo(Long storeId) throws Exception;
    
    AuthenticationChnnel findByXyyqAuthcationInfo(Map<String, Object> map) throws Exception;
}