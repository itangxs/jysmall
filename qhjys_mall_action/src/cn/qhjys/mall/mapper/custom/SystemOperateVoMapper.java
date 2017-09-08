package cn.qhjys.mall.mapper.custom;

import java.util.Map;
import com.github.pagehelper.Page;
import cn.qhjys.mall.vo.system.SalesDetailVo;
import cn.qhjys.mall.vo.system.SalesofGoodsVo;
import cn.qhjys.mall.vo.system.ShopAreaVo;
import cn.qhjys.mall.vo.system.ShopSalesVo;

public interface SystemOperateVoMapper {

	public Page<SalesofGoodsVo> querySalesofGoodsList(Map<String, Object> map);

	public Page<SalesDetailVo> querySalesDetailList(Map<String, Object> map);

	public Page<ShopAreaVo> queryShopAreaList(Map<String, Object> map);

	public Page<ShopSalesVo> queryShopSalesList(Map<String, Object> map);

	public Page<ShopSalesVo> queryShopDaySalesList(Map<String, Object> map);

	public Page<ShopSalesVo> queryShopMonthSalesList(Map<String, Object> map);
}
