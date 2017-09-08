package cn.qhjys.mall.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.qhjys.mall.entity.FqClerk;
import cn.qhjys.mall.entity.FqClerkCount;
import cn.qhjys.mall.entity.FqClerkExample;
import cn.qhjys.mall.entity.FqClerkMonth;
import cn.qhjys.mall.entity.FqThirdPay;
import cn.qhjys.mall.entity.RebateOrder;
import cn.qhjys.mall.entity.RebateOrderExample;
import cn.qhjys.mall.entity.RebateOrderExample.Criteria;
import cn.qhjys.mall.mapper.FqClerkMapper;
import cn.qhjys.mall.mapper.FqThirdPayMapper;
import cn.qhjys.mall.mapper.RebateOrderMapper;
import cn.qhjys.mall.mapper.custom.RebateOrderVoMapper;
import cn.qhjys.mall.mapper.custom.ThirdPayMapper;
import cn.qhjys.mall.service.RebateOrderService;
import cn.qhjys.mall.vo.OrderCountVo;
import cn.qhjys.mall.vo.RebateOrderVo;
import cn.qhjys.mall.vo.StoreCountVo;

@Service
public class RebateOrderServiceImpl implements RebateOrderService {
	
	@Autowired
	private RebateOrderMapper rebateOrderMapper;
	@Autowired
	private RebateOrderVoMapper rebateOrderVoMapper;
	@Autowired
	private ThirdPayMapper thirdPayMapper;
	@Autowired
	private FqThirdPayMapper fqThirdPayMapper;
	@Autowired
	private FqClerkMapper fqClerkMapper;

	@Override
	public Page<RebateOrder> listRebateOrderByRebateId(Long rebateId,
			Integer status,Integer pageNum ,Integer pageSize, String startTime, String endTime) throws ParseException {
		RebateOrderExample example = new RebateOrderExample();
		Criteria criteria = example.createCriteria();
		criteria.andRebateIdEqualTo(rebateId).andStatusEqualTo(status);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (!StringUtils.isEmpty(startTime)) {
			criteria.andCreateTimeGreaterThanOrEqualTo(sdf.parse(startTime+" 00:00:00"));
		}
		if (!StringUtils.isEmpty(endTime)) {
			criteria.andCreateTimeLessThanOrEqualTo(sdf.parse(endTime+" 23:59:59"));
		}
		example.setOrderByClause("create_time desc");
		PageHelper.startPage(pageNum, pageSize);
		return (Page<RebateOrder>) rebateOrderMapper.selectByExample(example);
	}
	@Override
	public Page<RebateOrderVo> listRebateOrderVo(Long sellerId,String orderNo,
			String storeName, String nickname, String startTime, String endTime,
			Integer pageNum, Integer pageSize,Integer status,List<Integer> type,Integer sort) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sellerId", sellerId);
		if (!StringUtils.isEmpty(orderNo)) {
			map.put("orderNo", orderNo);
		}
		if (!StringUtils.isEmpty(storeName)) {
			map.put("storeName", storeName);
		}
		
		if (!StringUtils.isEmpty(startTime)) {
			map.put("startTime", startTime+" 00:00:00");
		}
		if (!StringUtils.isEmpty(endTime)) {
			map.put("endTime", endTime+" 23:59:59");
		}
		if (!StringUtils.isEmpty(type)) {
			map.put("type", type);
		}
		if (!StringUtils.isEmpty(nickname)) {
			map.put("openId", nickname);
		}
		if (!StringUtils.isEmpty(status)) {
			map.put("status", status);
		}
		if (!StringUtils.isEmpty(sort)) {
			map.put("sort", sort);
		}
		PageHelper.startPage(pageNum, pageSize);
		return rebateOrderVoMapper.queryRebateOrderVoByThird(map);
	}
	@Override
	public Page<RebateOrderVo> listRebateOrderVo(String orderNo,
			String rebateName, String storeName, Long sellerId,
			String benginTime, String endTime, String openId, Integer pageNum,
			Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", 2);
		if (!StringUtils.isEmpty(sellerId)) {
			map.put("sellerId", sellerId);
		}
		if (!StringUtils.isEmpty(orderNo)) {
			map.put("orderNo", orderNo);
		}
		if (!StringUtils.isEmpty(storeName)) {
			map.put("storeName", storeName);
		}
		if (!StringUtils.isEmpty(rebateName)) {
			map.put("rebateName", rebateName);
		}
		if (!StringUtils.isEmpty(openId)) {
			map.put("openId", openId);
		}
		if (!StringUtils.isEmpty(benginTime)) {
			map.put("pstartTime", benginTime+" 00:00:00");
		}
		if (!StringUtils.isEmpty(endTime)) {
			map.put("pendTime", endTime+" 23:59:59");
		}
		if (pageNum != null && pageSize != null) {
			PageHelper.startPage(pageNum, pageSize);
		}
		return rebateOrderVoMapper.queryRebateOrderVo(map);
	}
	
	@Override
	public Page<RebateOrderVo> listRebateOrderVoByThird(String orderNo,
			 String storeName, Long sellerId,
			String benginTime, String endTime, String openId,Integer bankType, Integer pageNum,
			Integer pageSize,List<Integer> type,Integer isCash,Integer payNum,Long storeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", 2);
		if (!StringUtils.isEmpty(sellerId)) {
			map.put("sellerId", sellerId);
		}
		if (!StringUtils.isEmpty(orderNo)) {
			map.put("orderNo", orderNo);
		}
		if (!StringUtils.isEmpty(storeName)) {
			map.put("storeName", storeName);
		}
		
		if (!StringUtils.isEmpty(openId)) {
			map.put("openId", openId);
		}
		if (!StringUtils.isEmpty(benginTime)) {
			map.put("pstartTime", benginTime+" 00:00:00");
		}
		if (!StringUtils.isEmpty(endTime)) {
			map.put("pendTime", endTime+" 23:59:59");
		}
		if (!StringUtils.isEmpty(bankType)) {
			map.put("bankType", bankType);
		}
		if (!StringUtils.isEmpty(type)) {
			map.put("type", type);
		}
		if (!StringUtils.isEmpty(isCash)) {
			map.put("isCash", isCash);
		}
		if (!StringUtils.isEmpty(payNum)) {
			map.put("payNum", payNum);
		}
		if (!StringUtils.isEmpty(storeId)) {
			map.put("storeId", storeId);
		}
		if (pageNum != null && pageSize != null) {
			PageHelper.startPage(pageNum, pageSize);
		}
		return rebateOrderVoMapper.queryRebateOrderVoByThird(map);
	}
	@Override
	public RebateOrder getRebateOrder(Long id) {
		return rebateOrderMapper.selectByPrimaryKey(id);
	}
	@Override
	public RebateOrder getLastOrder(String openId) {
		RebateOrderExample example = new RebateOrderExample();
		example.createCriteria().andOpenIdEqualTo(openId).andStatusEqualTo(2);
		example.setOrderByClause("pay_time desc");
		PageHelper.startPage(1, 1);
		List<RebateOrder> list = rebateOrderMapper.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}
	@Override
	public int insertRebateOrder(RebateOrder rebateOrder) {
		return rebateOrderMapper.insertSelective(rebateOrder);
	}
	@Override
	public RebateOrder getRebateOrderByOrderNo(String orderNo) {
		RebateOrderExample example = new RebateOrderExample();
		example.createCriteria().andOrderNoEqualTo(orderNo);
		List<RebateOrder> list = rebateOrderMapper.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}
	
	@Override
	public OrderCountVo queryOrderCountVo(Long sellerId,String orderNo ,String storeName,String openId,String startTime ,String endTime,
			Integer pageNum,Integer pageSize,Integer status,List<Integer> type,Integer sort) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(sellerId)) {
			map.put("sellerId", sellerId);
		}
		if (!StringUtils.isEmpty(orderNo)) {
			map.put("orderNo", orderNo);
		}
		if (!StringUtils.isEmpty(storeName)) {
			map.put("storeName", storeName);
		}
		
		if (!StringUtils.isEmpty(startTime)) {
			map.put("startTime", startTime+" 00:00:00");
		}
		if (!StringUtils.isEmpty(endTime)) {
			map.put("endTime", endTime+" 23:59:59");
		}
		if (!StringUtils.isEmpty(type)) {
			map.put("type", type);
		}
		if (!StringUtils.isEmpty(openId)) {
			map.put("openId", openId);
		}
		if (!StringUtils.isEmpty(status)) {
			map.put("status", status);
		}
		if (!StringUtils.isEmpty(sort)) {
			map.put("sort", sort);
		}
		return thirdPayMapper.queryOrderCountVo(map);
	}
	@Override
	public Integer updateOrderCash(String[] iscashs) {
		int a = 0;
		for (int i = 0; i < iscashs.length; i++) {
			String iscash = iscashs[i];
			String[] iscashstr = iscash.split("#");
			long id = Long.valueOf(iscashstr[0]);
			int cash = Integer.valueOf(iscashstr[1]);
			FqThirdPay fqThirdPay = new FqThirdPay();
			fqThirdPay.setId(id);
			fqThirdPay.setIsCash(cash);
			a += fqThirdPayMapper.updateByPrimaryKeySelective(fqThirdPay);
		}
		return a;
	}
	@Override
	public Page<StoreCountVo> queryStoreCountVo(String startTime,String endTime,Integer pageNum,Integer pageSize,Long storeId,
			String branchName,String teamName,String clerk,String storeName,Long categoryid,Integer isEffective) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(startTime)) {
			map.put("startTime", startTime+" 00:00:00");
		}
		if (!StringUtils.isEmpty(endTime)) {
			map.put("endTime", endTime+" 23:59:59");
		}
		if (!StringUtils.isEmpty(storeId)) {
			map.put("storeId", storeId);
		}
		if (!StringUtils.isEmpty(branchName)) {
			map.put("branchName", branchName);
		}
		if (!StringUtils.isEmpty(teamName)) {
			map.put("teamName", teamName);
		}
		if (!StringUtils.isEmpty(clerk)) {
			map.put("clerk", clerk);
		}
		if (!StringUtils.isEmpty(storeName)) {
			map.put("storeName", storeName);
		}
		if (!StringUtils.isEmpty(categoryid)) {
			map.put("categoryid", categoryid);
		}
		if (!StringUtils.isEmpty(isEffective)) {
			map.put("isEffective", isEffective);
		}
		if (pageNum != null && pageSize != null) {
			PageHelper.startPage(pageNum, pageSize);
		}
		return rebateOrderVoMapper.queryStoreCountVo(map);
	}
	@Override
	public Integer insertFqClerkCount(Date date) {
		Map<String, Object> map = new HashMap<String, Object>();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		map.put("endTime", c.getTime());
		c.add(Calendar.DAY_OF_MONTH, -1);
		map.put("beginTime", c.getTime());
		return rebateOrderVoMapper.insertFqClerkCount(map);
	}
	@Override
	public Page<FqClerkCount> queryFqClerkCountBySeller(Integer pageNum,Integer pageSize,String startDate,
			Long branchId, Long teamId, Long clerkId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(startDate)) {
			map.put("startDate", startDate);
		}
		if (!StringUtils.isEmpty(branchId)) {
			map.put("branchId", branchId);
		}
		if (!StringUtils.isEmpty(teamId)) {
			map.put("teamId", teamId);
		}
		if (!StringUtils.isEmpty(clerkId)) {
			map.put("clerkId", clerkId);
		}
		if (pageNum != null && pageSize != null) {
			PageHelper.startPage(pageNum, pageSize);
		}
		return rebateOrderVoMapper.queryFqClerkCountBySeller(map);
	}
	@Override
	public List<FqClerkMonth> countFqClerkMonth() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int nowday =  c.get(Calendar.DAY_OF_MONTH);
		int nowmonth =  c.get(Calendar.MONTH);
		int nowyear =  c.get(Calendar.YEAR);
		c.add(Calendar.MONTH, -1);
		FqClerkExample example = new FqClerkExample();
		example.createCriteria().andWorkDateLessThan(c.getTime());
		List<FqClerk> clerks = fqClerkMapper.selectByExample(example);
		for (int i = 0; i < clerks.size(); i++) {
			FqClerk clerk = clerks.get(i);
			Calendar ca = Calendar.getInstance();
			ca.setTime(clerk.getWorkDate());
			int workday = ca.get(Calendar.DAY_OF_MONTH);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("clerkId", clerk.getId());
			if (nowday == 9) {
				if (workday< 9) {
					ca.set(Calendar.MONTH, nowmonth);
					ca.add(Calendar.DAY_OF_MONTH, -1);
					map.put("endDate", ca.getTime());
					ca.add(Calendar.MONTH, -1);
					ca.add(Calendar.DAY_OF_MONTH, 1);
					map.put("beginDate", ca.getTime());
					FqClerkMonth monthCount = rebateOrderVoMapper.countFqClerkMonth(map);
					if (monthCount != null && !StringUtils.isEmpty(monthCount.getClerkId())) {
						monthCount.setClerkTime(clerk.getWorkDate());
						monthCount.setCountMonth(ca.get(Calendar.YEAR)+"/"+ca.get(Calendar.MONTH));
					}
				}
			}
			
		}
		
		return null;
	}

}
