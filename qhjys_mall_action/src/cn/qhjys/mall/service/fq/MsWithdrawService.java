package cn.qhjys.mall.service.fq;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.qhjys.mall.common.Query;
import cn.qhjys.mall.entity.MsWithdraw;
import cn.qhjys.mall.vo.system.MsWithdrawExportVo;
import cn.qhjys.mall.vo.system.MsWithdrawParmVo;

import com.github.pagehelper.Page;


/**
 * 
 * 标题：民生商户提现管理服务接口
 * 作者：huangjr
 * 描述：TODO
 * 版本：V1.0 
 * 创建时间：2017年7月15日 上午11:38:51
 * 修改时间：
 *
 */
public interface MsWithdrawService {
	
	/**
	 * 
	 * 描述: 根据ID查询单个对象
	 * @param id
	 * @return
	 */
	MsWithdraw getMsWithdrawById(Long id) throws Exception;
	
	/**
	 * 
	 * 描述: 统计前天23：00至昨天23：00民生的商户交易明细生成提现记录
	 */
	boolean addWithdrawByDealDetail() throws Exception;
    
    /**
     * 
     * 描述: 民生商户提现记录查询
     * @param map
     * @return
     */
    Page<Map<String,Object>> queryMsWithdrawPage(MsWithdrawParmVo msWithdrawParm,Query query) throws Exception;
    
    /**
     * 
     * 描述: 民生商户提现记录导出
     * @param map
     * @return
     */
    List<MsWithdrawExportVo> queryMsWithdrawExport(MsWithdrawParmVo msWithdrawParm) throws Exception;
    
    /**
     * 
     * 描述: 批量修改记录状态
     * @param map
     */
    boolean updateWithdraw(Map<String,Object> map) throws Exception;
    
    /**
     * 
     * 描述: 根据商户ID查询提现记录
     * @param map
     * @return
     */
    Page<MsWithdraw> queryMsWithdrawBySellerId(Long sellerId,Query query) throws Exception;
    
    /**
     * 
     * 描述: 根据商户ID查询提现记录导出报表
     * @param map
     * @return
     */
    List<MsWithdraw> queryMsWithdrawBySellerExport(Long sellerId) throws Exception;
}
