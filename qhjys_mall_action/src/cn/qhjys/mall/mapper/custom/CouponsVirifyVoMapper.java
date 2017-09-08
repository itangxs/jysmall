package cn.qhjys.mall.mapper.custom;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.qhjys.mall.vo.system.CouponsVirifyVo;


public interface CouponsVirifyVoMapper {
	public List<CouponsVirifyVo> getCouponsVirifyVoByOpenId(@Param("openid") String openid)throws Exception;
 }
