package cn.qhjys.mall.service.system;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import cn.qhjys.mall.entity.DistrictInfo;
import cn.qhjys.mall.entity.FqBranch;
import cn.qhjys.mall.entity.FqClerk;
import cn.qhjys.mall.entity.FqCommissionRole;
import cn.qhjys.mall.entity.FqTeam;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.entity.SysImg;
import cn.qhjys.mall.entity.WebsiteImg;
import cn.qhjys.mall.vo.system.FqClerkVo;

import com.github.pagehelper.Page;

public interface QDManageService {
	
	/**
	 * 服务商列表
	 * @param createStart
	 * @param createEnd
	 * @param provinceId
	 * @param cityId
	 * @param branchName
	 * @param fwsId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page<FqBranch> queryFwsList(Date createStart, Date createEnd, Long provinceId, Long cityId, String branchName, Long fwsId, Integer pageNum, Integer pageSize) throws Exception;
	
	/**
	 * 删除服务商
	 * @param id
	 * @return
	 */
	public int deleteFqBranch(Long id) throws Exception;
	
	/**
	 * 根据服务商ID查团队长信息
	 * @param branchId
	 * @return
	 * @throws Exception
	 */
	public List<FqTeam> queryFqTeamByBranchId(Long branchId) throws Exception;
	
	/**
	 * 查看服务商详情
	 * @param id
	 * @return
	 */
	public FqBranch fqBranchDetail(Long id) throws Exception;
	
	/**
	 * 修改服务商信息
	 * @param id
	 * @return
	 */
	public int updateFqBranch(Long id, String principal, String phoneNum, Long provinceId, String provinceName, Long cityId, String cityName, String address)throws Exception;
	
	/**
	 * 新增服务商
	 * @param branchName
	 * @param principal
	 * @param phoneNum
	 * @param provinceId
	 * @param provinceName
	 * @param cityId
	 * @param cityName
	 * @param address
	 * @return
	 */
	public int addFqBranch(String branchName, String principal, String phoneNum, Long provinceId, String provinceName, Long cityId, String cityName, String address) throws Exception;
	
	/**
	 * 查询所有的服务商
	 * @return
	 * @throws Exception
	 */
	public List<FqBranch> queryAllFqBranch() throws Exception;
	
	/**
	 * 团队长信息列表
	 * @param createStart
	 * @param createEnd
	 * @param branchName
	 * @param id
	 * @param principal
	 * @param phoneNum
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page<FqTeam> queryFqTeamList(Date createStart, Date createEnd, String branchName, Long id, String principal, String phoneNum, Integer pageNum, Integer pageSize) throws Exception;
	
	/**
	 * 新增团队长信息
	 * @param principal
	 * @param phoneNum
	 * @param branchId
	 * @return
	 * @throws Exception
	 */
	public int addFqTeam(String principal, String phoneNum, Long branchId) throws Exception;
	
	/**
	 * 删除团队长信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int deleteFqTeam(Long id) throws Exception;
	
	/**
	 * 根据团队长Id查询业务员
	 * @param teamId
	 * @return
	 * @throws Exception
	 */
	public List<FqClerk> queryFqClerkByTeamId(Long teamId) throws Exception;
	
	/**
	 * 团队长信息详情
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public FqTeam fqTeamDetail(Long id) throws Exception;
	
	/**
	 * 修改团队长信息
	 * @param id
	 * @param principal
	 * @param phoneNum
	 * @param branchId
	 * @return
	 * @throws Exception
	 */
	public int updateFqTeam(Long id, String principal, String phoneNum, Long branchId) throws Exception;
	
	/**
	 * 查询所有branchId的团队长信息
	 * @param branchId
	 * @return
	 * @throws Exception
	 */
	public List<FqTeam> queryAllFqTeam(Long branchId) throws Exception;
	
	/**
	 * 业务员信息列表
	 * @param createStart
	 * @param createEnd
	 * @param branchName
	 * @param teamName
	 * @param id
	 * @param clerk
	 * @param phoneNum
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page<FqClerkVo> queryFqClerkList(Date createStart, Date createEnd, String branchName, String teamName, Long id, String clerk, String phoneNum, Integer pageNum, Integer pageSize) throws Exception;

	/**
	 * 删除业务员信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int deleteFqClerk(Long id) throws Exception;
	
	/**
	 * 根据业务员ID获得店铺信息
	 * @param clerkId
	 * @return
	 * @throws Exception
	 */
	public List<StoreInfo> getStoreInfoByClerkId(Long clerkId) throws Exception;
	
	/**
	 * 提成策略列表
	 * @return
	 * @throws Exception
	 */
	public List<FqCommissionRole> fqCommissionRoleList() throws Exception;
	
	/**
	 * 业务员信息详情
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public FqClerkVo fqClerkDetail(Long clerkId) throws Exception;
	
	/**
	 * 修改业务员信息
	 * @param id
	 * @param clerk
	 * @param phoneNum
	 * @param teamId
	 * @param teamName
	 * @param createTime
	 * @param commission
	 * @return
	 * @throws Exception
	 */
	public int updateFqClerk(Long id,String clerk,String phoneNum, Long teamId, String teamName, Date createTime,Long commission) throws Exception;
	
	/**
	 * 新增业务员信息
	 * @param clerk
	 * @param phoneNum
	 * @param teamId
	 * @param teamName
	 * @param createTime
	 * @param commission
	 * @return
	 * @throws Exception
	 */
	public int addFqClerk(String clerk,String phoneNum, Long teamId, String teamName, Date createTime,Long commission) throws Exception;

	/**
	 * 根据teamId查询所有的业务员信息
	 * @param teamId
	 * @return
	 * @throws Exception
	 */
	public List<FqClerk> queryAllFqClerk(Long teamId) throws Exception;
	
	public List<FqClerk> queryAllFqClerkByCommissionId(Long commissionId) throws Exception;
}
