package cn.qhjys.mall.mapper.custom;

import cn.qhjys.mall.vo.seller.SellerBankVo;




import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;


public interface SellerBankVoMapper {
   


	Page<SellerBankVo> getSellerBankVoPage(@Param("sellerId") Long sellerId);

	void updateByDefaullOff(@Param("sellerId")Long uid);

	int updateByDefaullOn(@Param("sellerId") Long sellerId,@Param("id") Long id);

  
}