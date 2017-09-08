package cn.qhjys.mall.service.system;

import java.math.BigDecimal;
import cn.qhjys.mall.vo.system.AuditProductVo;
import com.github.pagehelper.Page;

/***
 * 总后台商品审核接口
 * 
 * @author zengrong
 *
 */
public interface AuditProductService {

	/***
	 * 总后台商品审核列表
	 * 
	 * @param productName
	 *            商品名
	 * @param storeName
	 *            商家名
	 * @param startPrice
	 *            搜索开始价格
	 * @param endPrice
	 *            搜索结束价格
	 * @param startTime
	 *            搜索开始时间
	 * @param endTime
	 *            搜索介绍时间
	 * @param status
	 *            商品状态
	 * @param pageNum
	 *            分页条件
	 * @param pageSize
	 *            分页条件
	 * @return
	 */
	public Page<AuditProductVo> queryAuditProductList(String productName, String storeName, BigDecimal startPrice,
			BigDecimal endPrice, String startTime, String endTime, Long status, Integer pageNum, Integer pageSize)
			throws Exception;;

	/***
	 * 总后台的商品审核状态修改以及添加商品审核表审核记录
	 * 
	 * @param ids
	 * @param status审核状态
	 *            (1不通过，2通过)
	 * @param adminId
	 *            审核人编号
	 * @return
	 */
	public boolean updateAuditProduct(String[] ids, int status, Long adminId) throws Exception;;

}
