package cn.qhjys.mall.service.fq.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.common.Query;
import cn.qhjys.mall.entity.MsWithdraw;
import cn.qhjys.mall.mapper.MsWithdrawMapper;
import cn.qhjys.mall.service.fq.MsWithdrawService;
import cn.qhjys.mall.util.DateUtils;
import cn.qhjys.mall.util.ms.DateUtil;
import cn.qhjys.mall.vo.system.MsWithdrawExportVo;
import cn.qhjys.mall.vo.system.MsWithdrawParmVo;

/**
 * 
 * 标题：民生商户提现管理服务
 * 作者：huangjr
 * 描述：TODO
 * 版本：V1.0 
 * 创建时间：2017年7月15日 上午11:38:51
 * 修改时间：
 *
 */
@Service("msWithdrawService")
public class MsWithdrawServiceImpl extends Base implements MsWithdrawService {
	
	@Autowired
	private MsWithdrawMapper msWithdrawMapper;
	
	/**
	 * 
	 * 描述: 统计前天23：00至昨天23：00民生的商户交易明细生成提现记录
	 */
	@Override
	public boolean addWithdrawByDealDetail() throws Exception{
		logger.info("--当前日期为："+DateUtil.formatTimesTampDate(new Date()));
		//得到前天23：00
		String startDate = DateUtil.formatTimesTampDate(DateUtil.getDateThree((DateUtil.getDateBefore(new Date(),2))));
		//得到昨天23：00
		String endDate = DateUtil.formatTimesTampDate(DateUtil.getDateThree((DateUtil.getDateBefore(new Date(),1))));
		logger.info("--执行"+startDate+"至"+endDate+"时间段的数据---");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sort", 99);//表示为民生的数据
		map.put("startDate", startDate);//昨天开始时间
		map.put("endDate", endDate);//昨天结束时间
		Integer count =msWithdrawMapper.addWithdrawByDealDetail(map);
		logger.info("---已生成"+count+"位商家的提现报表---");
		return count<=0 ? false : true;
	}

	/**
     * 
     * 描述: 民生商户提现记录查询
     * @param map
     * @return
     */
	@Override
	public Page<Map<String, Object>> queryMsWithdrawPage(
			MsWithdrawParmVo msWithdrawParm,Query query) throws Exception{
		PageHelper.startPage(query.getPageNum(), query.getPageSize());
		return (Page<Map<String, Object>>) msWithdrawMapper.queryMsWithdrawPage(msWithdrawParm);
	}

	/**
     * 
     * 描述: 批量修改记录状态
     * @param map
     */
	@Override
	public boolean updateWithdraw(Map<String,Object> map) throws Exception {
		Integer count = msWithdrawMapper.updateWithdraw(map);
		return count<=0 ? false : true;
	}

	/**
     * 
     * 描述: 民生商户提现记录导出
     * @param map
     * @return
     */
	@Override
	public List<MsWithdrawExportVo> queryMsWithdrawExport(
			MsWithdrawParmVo msWithdrawParm) throws Exception {
		return msWithdrawMapper.queryMsWithdrawExport(msWithdrawParm);
	}

	/**
	 * 根据ID查询单个对象
	 */
	@Override
	public MsWithdraw getMsWithdrawById(Long id) throws Exception {
		return msWithdrawMapper.getMsWithdrawById(id);
	}

	/**
     * 
     * 描述: 根据商户ID查询提现记录
     * @param map
     * @return
     */
	@Override
	public Page<MsWithdraw> queryMsWithdrawBySellerId(Long sellerId,Query query)
			throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sellerId", sellerId);
		PageHelper.startPage(query.getPageNum(), query.getPageSize());
		return (Page<MsWithdraw>) msWithdrawMapper.queryMsWithdrawSellerPage(map);
	}

	/**
     * 
     * 描述: 根据商户ID查询提现记录导出报表
     * @param map
     * @return
     */
	@Override
	public List<MsWithdraw> queryMsWithdrawBySellerExport(Long sellerId)
			throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sellerId", sellerId);
		return msWithdrawMapper.queryMsWithdrawSellerPage(map);
	}
}
