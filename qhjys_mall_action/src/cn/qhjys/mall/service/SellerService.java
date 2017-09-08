package cn.qhjys.mall.service;

import java.util.Date;
import java.util.List;

import cn.qhjys.mall.entity.SellerExpand;
import cn.qhjys.mall.entity.SellerInfo;

import com.github.pagehelper.Page;

/***
 * 商家基本信息接口
 * 
 * @author zengrong
 *
 */
public interface SellerService {

	/**
	 * 获取商家基本信息
	 * 
	 * @param sellerId
	 *            商家编号
	 * @return
	 */
	SellerInfo getSellerById(Long sellerId) throws Exception;

	/**
	 * 获取商家扩展信息
	 * 
	 * @param sellerId
	 *            商家编号
	 * @return
	 */
	SellerExpand getSellerExpandById(Long sellerId) throws Exception;

	/**
	 * 设置商家扩展信息
	 * 
	 * @param expand
	 * @return
	 */
	boolean updateSellerExpand(SellerExpand expand) throws Exception;

	/**
	 * 判断商家是否存在
	 * 
	 * @param username
	 *            用户名
	 * @param phone
	 *            电话
	 * @return
	 */
	boolean querySeller(String username, String phone) throws Exception;

	/**
	 * 修改上级基本信息
	 * 
	 * @param seller
	 *            商家信息
	 * @return
	 */
	boolean updateSellerById(SellerInfo seller) throws Exception;

	/**
	 * 添加商家帐号
	 * 
	 * @param seller
	 *            商家信息
	 * @return
	 */
	boolean addSellerInfo(SellerInfo seller) throws Exception;

	/**
	 * 根据用户名和手机号获取商家信息
	 * 
	 * @param username
	 *            用户名
	 * @param phone
	 *            手机号
	 * @return
	 * @throws Exception
	 */
	SellerInfo getSeller(String username) throws Exception;

	/**
	 * 
	 * selectSllerInfoBySystem 系统后台要到的分页
	 * 
	 * @param account
	 *            账号
	 * @param phone
	 *            手机
	 * @param date
	 *            创建时间 开始
	 * @param date2
	 *            创建时间结束
	 * @param pageNum
	 *            页数
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	Page<SellerInfo> selectSllerInfoBySystem(String account, String phone, Date date, Date date2, Integer pageNum,
			Integer pageSize) throws Exception;

	/***
	 * 
	 * updateSellerEnabledBySystem 系统修改 商家的启用状态
	 * 
	 * @param l
	 *            系统用户
	 * @param strlist
	 *            商家ID集合
	 * @param staus
	 *            状态 0关闭 1开启
	 * @return
	 */
	Boolean updateSellerEnabledBySystem(long l, List<Long> strlist, Integer staus) throws Exception;

	/**
	 * 
	 * updateSellerPassWordById 修改商家密码
	 * 
	 * @param seller
	 *            商家
	 * @param getMD5Code
	 *            已经加好密码的
	 * @return
	 * @throws Exception
	 */
	Boolean updateSellerPassWordById(SellerInfo seller, String getMD5Code) throws Exception;
	
	SellerInfo getSellerInfoByInvite(Integer invite);
	
	boolean addInviteTomat(int inviteCode,int totamt) throws Exception;
	
	boolean updateSellerWithdrawStatus(Long sellerId,Integer status) throws Exception;
}