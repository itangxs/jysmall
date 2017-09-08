package cn.qhjys.mall.mapper;

import java.util.List;
import java.util.Map;

import cn.qhjys.mall.entity.MsWithdraw;
import cn.qhjys.mall.vo.system.MsWithdrawExportVo;
import cn.qhjys.mall.vo.system.MsWithdrawParmVo;

/**
 * 
 * 标题：民生商户提现管理
 * 作者：huangjr
 * 描述：TODO
 * 版本：V1.0 
 * 创建时间：2017年7月15日 上午11:12:50
 * 修改时间：
 *
 */
public interface MsWithdrawMapper {
	
	/**
	 * 
	 * 描述: 根据ID查询单个对象
	 * @param id
	 * @return
	 */
	MsWithdraw getMsWithdrawById(Long id);
	
	/**
	 * 
	 * 描述: 统计前天23：00至昨天23：00民生的商户交易明细生成提现记录
	 */
    Integer addWithdrawByDealDetail(Map<String,Object> map);
    
    /**
     * 
     * 描述: 民生商户提现记录查询
     * @param map
     * @return
     */
    List<Map<String,Object>> queryMsWithdrawPage(MsWithdrawParmVo msWithdrawParm);
    
    /**
     * 
     * 描述: 民生商户提现记录导出
     * @param map
     * @return
     */
    List<MsWithdrawExportVo> queryMsWithdrawExport(MsWithdrawParmVo msWithdrawParm);
    /**
     * 
     * 描述: 批量修改记录状态
     * @param map
     */
    Integer updateWithdraw(Map<String,Object> map);
    
    /**
     * 
     * 描述: 查询当前商家的提现记录
     * @param map
     * @return
     */
    List<MsWithdraw> queryMsWithdrawSellerPage(Map<String,Object> map);
}