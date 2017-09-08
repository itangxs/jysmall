package cn.qhjys.mall.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.qhjys.mall.entity.FqClerkCount;
import cn.qhjys.mall.entity.FqClerkMonth;
import cn.qhjys.mall.entity.RebateOrder;
import cn.qhjys.mall.vo.OrderCountVo;
import cn.qhjys.mall.vo.RebateOrderVo;
import cn.qhjys.mall.vo.StoreCountVo;

import com.github.pagehelper.Page;

public interface RebateOrderService {
	
	public Page<RebateOrder> listRebateOrderByRebateId(Long rebateId,Integer status,Integer pageNum ,Integer pageSize, String startTime, String endTime)throws ParseException;
	
	public Page<RebateOrderVo> listRebateOrderVo(Long sellerId,String orderNo ,String storeName,String openId,String startTime ,String endTime,
			Integer pageNum,Integer pageSize,Integer status,List<Integer> type,Integer sort);
	
	public Page<RebateOrderVo> listRebateOrderVo(String orderNo,String rebateName,String storeName,
			Long sellerId,String benginTime,String endTime,String openId,Integer pageNum,Integer pageSize);
	
	public Page<RebateOrderVo> listRebateOrderVoByThird(String orderNo,String storeName,
			Long sellerId,String benginTime,String endTime,String openId,Integer bankType,Integer pageNum,Integer pageSize,List<Integer> type,Integer isCash,Integer payNum,Long storeId);
	
	public RebateOrder getRebateOrder(Long id);
	
	public RebateOrder getLastOrder(String openId);
	
	public int insertRebateOrder(RebateOrder rebateOrder);
	
	public RebateOrder getRebateOrderByOrderNo(String orderNo);
	
	public OrderCountVo queryOrderCountVo(Long sellerId,String orderNo ,String storeName,String openId,String startTime ,String endTime,
			Integer pageNum,Integer pageSize,Integer status,List<Integer> type,Integer sort);
	
	public Integer updateOrderCash(String[] iscashs);
	
	public Page<StoreCountVo> queryStoreCountVo(String startTime,String endTime,Integer pageNum,Integer pageSize,Long storeId,
			String branchName,String teamName,String clerk,String storeName,Long categoryid,Integer isEffective);
	
	public Integer insertFqClerkCount(Date date);
	
	public Page<FqClerkCount> queryFqClerkCountBySeller(Integer pageNum,Integer pageSize,String startDate,Long branchId,Long teamId,Long clerkId);
	
	public List<FqClerkMonth> countFqClerkMonth();
}
