package cn.qhjys.mall.service;

import java.util.List;

import cn.qhjys.mall.entity.OrderDetail;
import cn.qhjys.mall.entity.VolumeInfo;
import cn.qhjys.mall.vo.VolumeVo;

import com.github.pagehelper.Page;

/**
 * 消费卷管理
 * 
 * @author LiXiang
 *
 */
public interface VolumeService {

	/**
	 * 验证消费卷
	 * 
	 * @param sellerId
	 *            商家编号
	 * @param vCode
	 *            消费卷密码
	 * @return
	 */
	boolean updateVerifyVolume(Long sellerId, String vCode) throws Exception;

	/**
	 * 查询已使用消费卷
	 * 
	 * @param sellerId
	 *            商家编号
	 * @param pageNum
	 *            当前页数
	 * @param pageSize
	 *            每页记录
	 * @return
	 */
	Page<VolumeVo> queryUsedVolume(Long sellerId, Integer pageNum, Integer pageSize) throws Exception; 

	/***
	 * 根据消费劵查出订单详情
	 * 
	 * @param sellerId
	 *            商家编号
	 * @param volumeCode
	 *            验证码
	 * @return
	 */
	OrderDetail queryOrderDetail(Long sellerId, String volumeCode) throws Exception;

	/**
	 * 过期消费卷自动退款
	 * 
	 * @return
	 * @throws Exception
	 */
	boolean updateAutoRefundOrder() throws Exception;

	/**
	 * 退款超时订单自动退款
	 * 
	 * @return
	 * @throws Exception
	 */
	boolean updateOvertimeOrder() throws Exception;
	
	VolumeVo queryVolumeVo(Long sellerId, String volumeCode);
	
	List<VolumeInfo> listVolumeInfo(Long detailId);
	
}