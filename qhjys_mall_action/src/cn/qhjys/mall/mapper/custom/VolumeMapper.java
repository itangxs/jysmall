package cn.qhjys.mall.mapper.custom;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.qhjys.mall.vo.ExpiredOrderVo;
import cn.qhjys.mall.vo.VolumeVo;
import com.github.pagehelper.Page;

public interface VolumeMapper {

	Page<VolumeVo> getVolumeByCodeAndSeller(@Param("sellerId") Long sellerId, @Param("status") Integer status,
			@Param("vCode") String vCode);

	List<ExpiredOrderVo> queryExpiredVolumeOrder();

	List<ExpiredOrderVo> updateOvertimeOrder();
}