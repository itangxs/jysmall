package cn.qhjys.mall.mapper.custom;

import org.apache.ibatis.annotations.Param;
import cn.qhjys.mall.vo.MessageVo;
import com.github.pagehelper.Page;

public interface MessageVoMapper {

	Page<MessageVo> queryOrderList(@Param("userId") Long userId, @Param("status") Long status);

}