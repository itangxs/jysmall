package cn.qhjys.mall.mapper;

import java.util.List;

import cn.qhjys.mall.entity.MsAuthentication;

public interface MsAuthenticationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MsAuthentication record);

    int insertSelective(MsAuthentication record);

    MsAuthentication selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MsAuthentication record);

    int updateByPrimaryKey(MsAuthentication record);
    
    MsAuthentication findByStoreId(Long storeId) throws Exception;
    
    List<MsAuthentication> listByAll() throws Exception;
}