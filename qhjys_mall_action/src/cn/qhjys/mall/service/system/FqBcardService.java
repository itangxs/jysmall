package cn.qhjys.mall.service.system;

import java.util.List;

import com.github.pagehelper.Page;

import cn.qhjys.mall.entity.FqBcard;
import cn.qhjys.mall.entity.FqBcardPrize;
import cn.qhjys.mall.entity.FqBcardRule;
import cn.qhjys.mall.vo.system.FqBcardRuleNameVo;

public interface FqBcardService {
	
	boolean insertBcardAndPrizeAndRule(Long storeId,String storeName,String beginDate,String endDate,Integer validityDate,
			String cardDescript,Integer pushNum,String[] prizeName,String[] prizeLine,String[] prizeInfo, String[] imgs,
			Long[] city,Long[] district,Long[] area,Long[] industry,Long[] industryDetail) throws Exception;
	
	Page<FqBcard> queryBcardByStoreName(String storeName,Integer pageNum,Integer pageSize) throws Exception;

	boolean updateBcardStatusById(Long id,Integer status) throws Exception;
	
	boolean deleteBcardById(Long id) throws Exception;
	
	FqBcard queryBcard(Long id) throws Exception;
	
	List<FqBcardPrize> queryBcardPrize(Long bcardId) throws Exception;
	
	List<FqBcardRule> queryBcardRule(Long bcardId) throws Exception;
	
	List<FqBcardRuleNameVo> queryBcardRuleName(Long bcardId) throws Exception;
	
	boolean updateFqBcardAndPrizeAndRule(Long bcardId,Long storeId,String beginDate,String endDate,Integer validityDate,
			String cardDescript,Integer pushNum,Long[] prizeIds,String[] prizeName,
			String[] prizeLine,String[] prizeInfo, String[] imgs,
			Long[] ruleIds,Long[] city,Long[] district,Long[] area,Long[] industry,Long[] industryDetail,
			Long[] cityNew,Long[] distNew,Long[] areaNew,Long[] induNew,Long[] indudeNew) 
					throws Exception;
	
	
}
