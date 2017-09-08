package cn.qhjys.mall.mapper.custom;

import org.apache.ibatis.annotations.Param;
import com.github.pagehelper.Page;
import cn.qhjys.mall.vo.system.StoreLotteryVo;

public interface StoreLotteryVoMapper {

	Page<StoreLotteryVo> selectByStoreLotteryVoByStatus(@Param("lstatus")Integer lstatus);

}
