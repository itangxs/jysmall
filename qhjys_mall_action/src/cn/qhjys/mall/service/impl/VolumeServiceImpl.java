package cn.qhjys.mall.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.entity.OrderDetail;
import cn.qhjys.mall.entity.OrderInfo;
import cn.qhjys.mall.entity.VolumeInfo;
import cn.qhjys.mall.entity.VolumeInfoExample;
import cn.qhjys.mall.mapper.OrderDetailMapper;
import cn.qhjys.mall.mapper.OrderInfoMapper;
import cn.qhjys.mall.mapper.VolumeInfoMapper;
import cn.qhjys.mall.mapper.custom.VolumeMapper;
import cn.qhjys.mall.service.PaymentService;
import cn.qhjys.mall.service.VolumeService;
import cn.qhjys.mall.vo.ExpiredOrderVo;
import cn.qhjys.mall.vo.VolumeVo;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class VolumeServiceImpl implements VolumeService {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private VolumeInfoMapper volumeInfoMapper;
	@Autowired
	private VolumeMapper volumeMapper;
	@Autowired
	private OrderDetailMapper detailMapper;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private OrderInfoMapper orderInfoMapper;

	@Override
	public boolean updateVerifyVolume(Long sellerId, String vCode) throws Exception {
		List<VolumeVo> list = volumeMapper.getVolumeByCodeAndSeller(sellerId, 1, vCode);
		int result = 0;
		if (list == null || list.size() < 1)
			return false;
		VolumeVo vo = list.get(0);
		VolumeInfo info = new VolumeInfo();
		info.setId(vo.getId());
		info.setStatus(2);
		info.setUseDate(new Date());
		OrderDetail orderDeatil = new OrderDetail();
		orderDeatil.setId(list.get(0).getDetailId());
		// 修改订单状态为已消费
		orderDeatil.setStatus(3);
		int row = detailMapper.updateByPrimaryKeySelective(orderDeatil);
		if (row == 0)
			throw new Exception("修改订单异常");
		result = volumeInfoMapper.updateByPrimaryKeySelective(info);
		if (result == 0)
			throw new Exception("验证消费卷异常");
		OrderDetail detail = detailMapper.selectByPrimaryKey(list.get(0).getDetailId());
		OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(detail.getOrderId());
		BigDecimal totamt = detail.getTotalPrice();
		String detailNo = detail.getDetailNo();
		Long userId = orderInfo.getUserId();
		paymentService.orderSuccessBySeller(userId, sellerId, detailNo, totamt);
		return result > 0 ? true : false;
	}

	@Override
	public Page<VolumeVo> queryUsedVolume(Long sellerId, Integer pageNum, Integer pageSize) throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		Page<VolumeVo> page = volumeMapper.getVolumeByCodeAndSeller(sellerId, 2, null);
		return page;
	}

	@Override
	public OrderDetail queryOrderDetail(Long sellerId, String volumeCode) throws Exception {
		List<VolumeVo> list = volumeMapper.getVolumeByCodeAndSeller(sellerId, null, volumeCode);
		logger.info("---------------list.size()---" + list.size());
		logger.info("---------------list.size()---" + sellerId);
		logger.info("---------------list.size()---" + volumeCode);
		if (list == null || list.size() < 1)
			return null;
		OrderDetail orderDetail = detailMapper.selectByPrimaryKey(list.get(0).getDetailId());
		return orderDetail;
	}

	@Override
	public boolean updateAutoRefundOrder() throws Exception {
		List<ExpiredOrderVo> list = volumeMapper.queryExpiredVolumeOrder();
		VolumeInfo volume = new VolumeInfo();
		volume.setStatus(3);
		VolumeInfoExample example = new VolumeInfoExample();
		OrderDetail detail;
		for (ExpiredOrderVo order : list) {
			try {
				// 1. 更新订单状态
				detail = new OrderDetail();
				detail.setId(order.getDetailId());
				detail.setStatus(9);
				detailMapper.updateByPrimaryKeySelective(detail);
				// 2. 更新资金账户
				BigDecimal totamt = order.getTotalPric();
				// 3. 计算应退积分和金额
				paymentService.orderRefundByUser(order.getUserId(), order.getDetailNo(), totamt);
				// 4. 更新消费卷状态
				example.clear();
				example.createCriteria().andDetailIdEqualTo(order.getDetailId());
				volumeInfoMapper.updateByExampleSelective(volume, example);
			} catch (Exception e) {
				this.logger.error("过期消费卷自动退款异常，订单编号——" + order.getDetailId(), e);
				throw new Exception("过期消费卷自动退款异常，订单编号——" + order.getDetailId(), e);
			}
		}
		return true;
	}

	@Override
	public boolean updateOvertimeOrder() throws Exception {
		List<ExpiredOrderVo> list = volumeMapper.updateOvertimeOrder();
		OrderDetail detail;
		for (ExpiredOrderVo order : list) {
			try {
				// 1. 更新订单状态
				detail = new OrderDetail();
				detail.setId(order.getDetailId());
				detail.setStatus(6);
				detailMapper.updateByPrimaryKeySelective(detail);
				// 2. 更新资金账户
				BigDecimal totamt = order.getTotalPric();
				// 3. 计算应退积分和金额
				paymentService.orderRefundByUser(order.getUserId(), order.getDetailNo(), totamt);
			} catch (Exception e) { 
				this.logger.error("退款超时订单退款异常，订单编号——" + order.getDetailId(), e);
				throw new Exception("退款超时订单退款异常，订单编号——" + order.getDetailId(), e);
			}
		}
		return true;
	}

	@Override
	public VolumeVo queryVolumeVo(Long sellerId, String volumeCode) {
		List<VolumeVo> list = volumeMapper.getVolumeByCodeAndSeller(sellerId, null, volumeCode);
		return list.size()>0?list.get(0):null;
	}

	@Override
	public List<VolumeInfo> listVolumeInfo(Long detailId) {
		VolumeInfoExample example = new VolumeInfoExample();
		example.createCriteria().andDetailIdEqualTo(detailId);
		return volumeInfoMapper.selectByExample(example);
	}
}