package cn.qhjys.mall.mapper.custom;

import java.util.List;
import java.util.Map;
import cn.qhjys.mall.vo.system.AuditProductVo;

/***
 * 总后台商品审核
 * 
 * @author zengrong
 *
 */
public interface AuditProductMapper {

	public List<AuditProductVo> queryAuditProductList(Map<String, Object> map);

	public int updateAuditProduct(Map<String, Object> map);
}
