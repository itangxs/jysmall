package cn.qhjys.mall.service.system.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.entity.FqBranch;
import cn.qhjys.mall.entity.FqBranchExample;
import cn.qhjys.mall.entity.FqBranchExample.Criteria;
import cn.qhjys.mall.entity.FqClerk;
import cn.qhjys.mall.entity.FqClerkExample;
import cn.qhjys.mall.entity.FqCommissionRole;
import cn.qhjys.mall.entity.FqCommissionRoleExample;
import cn.qhjys.mall.entity.FqTeam;
import cn.qhjys.mall.entity.FqTeamExample;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.entity.StoreInfoExample;
import cn.qhjys.mall.mapper.FqBranchMapper;
import cn.qhjys.mall.mapper.FqClerkMapper;
import cn.qhjys.mall.mapper.FqClerkVoMapper;
import cn.qhjys.mall.mapper.FqCommissionRoleMapper;
import cn.qhjys.mall.mapper.FqTeamMapper;
import cn.qhjys.mall.mapper.StoreInfoMapper;
import cn.qhjys.mall.service.system.QDManageService;
import cn.qhjys.mall.vo.system.FqClerkVo;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class QDManageServiceImpl implements QDManageService{
	
	@Autowired
	private FqBranchMapper fqBranchMapper;
	@Autowired
	private FqTeamMapper fqTeamMapper;
	@Autowired
	private FqClerkMapper fqClerkMapper;
	@Autowired
	private FqClerkVoMapper fqClerkVoMapper;
	@Autowired
	private StoreInfoMapper storeInfoMapper;
	@Autowired
	private FqCommissionRoleMapper fqCommissionRoleMapper;
	
	public Page<FqBranch> queryFwsList(Date createStart, Date createEnd, Long provinceId, Long cityId, String branchName, Long fwsId, Integer pageNum, Integer pageSize) throws Exception{
		FqBranchExample example = new FqBranchExample();
		Criteria criteria = example.createCriteria();
		if(null != createStart && null != createEnd){
			criteria.andCreateTimeBetween(createStart, createEnd);
		}
		if(null != provinceId){
			criteria.andProvinceIdEqualTo(provinceId);	
		}
		if(null != cityId){
			criteria.andCityIdEqualTo(cityId);
		}
		if(null != fwsId){
			criteria.andIdEqualTo(fwsId);
		}
		if(null != branchName){
			criteria.andBranchNameEqualTo(branchName);
		}
		example.setOrderByClause("create_time desc");
		PageHelper.startPage(pageNum, pageSize);
		return (Page<FqBranch>) fqBranchMapper.selectByExample(example);
	}

	@Override
	public int deleteFqBranch(Long id) throws Exception {
		if(null != id && !id.equals("")){
			return fqBranchMapper.deleteByPrimaryKey(id);
		}
		return -1;
	}
	
	@Override
	public List<FqTeam> queryFqTeamByBranchId(Long branchId) throws Exception {
		FqTeamExample example = new FqTeamExample();
		example.createCriteria().andBranchIdEqualTo(branchId);
		List<FqTeam> list = fqTeamMapper.selectByExample(example);
		return list.size() > 0 ? list : null;
	}

	@Override
	public FqBranch fqBranchDetail(Long id) throws Exception{
		if(null != id){
			return fqBranchMapper.selectByPrimaryKey(id);
		}
		return null;
	}

	@Override
	public int updateFqBranch(Long id, String principal, String phoneNum, Long provinceId, 
			String provinceName, Long cityId, String cityName, String address) throws Exception{
		FqBranch fqBranch = fqBranchMapper.selectByPrimaryKey(id);
		fqBranch.setPrincipal(principal);
		fqBranch.setPhoneNum(phoneNum);
		fqBranch.setProvinceId(provinceId);
		fqBranch.setProvinceName(provinceName);
		fqBranch.setCityId(cityId);
		fqBranch.setCityName(cityName);
		fqBranch.setAddress(address);
		return  fqBranchMapper.updateByPrimaryKeySelective(fqBranch);
	}

	@Override
	public int addFqBranch(String branchName, String principal, String phoneNum, Long provinceId, 
			String provinceName, Long cityId, String cityName, String address) throws Exception{
		FqBranch fqBranch = new FqBranch();
		fqBranch.setBranchName(branchName);
		fqBranch.setProvinceId(provinceId);
		fqBranch.setProvinceName(provinceName);
		fqBranch.setCityName(cityName);
		fqBranch.setCityId(cityId);
		fqBranch.setPrincipal(principal);
		fqBranch.setPhoneNum(phoneNum);
		fqBranch.setAddress(address);
		fqBranch.setCreateTime(new Date());
		return fqBranchMapper.insertSelective(fqBranch);
	}
	
	@Override
	public List<FqBranch> queryAllFqBranch() throws Exception {
		FqBranchExample example = new FqBranchExample();
		example.createCriteria();
		return fqBranchMapper.selectByExample(example);
	}

	@Override
	public Page<FqTeam> queryFqTeamList(Date createStart, Date createEnd,
			String branchName, Long id, String principal, String phoneNum,
			Integer pageNum, Integer pageSize) throws Exception {
		FqTeamExample example = new FqTeamExample();
		cn.qhjys.mall.entity.FqTeamExample.Criteria criteria = example.createCriteria();
		if(null != createStart && null != createEnd){
			criteria.andCreateTimeBetween(createStart, createEnd);
		}
		if(null != id){
			criteria.andIdEqualTo(id);
		}
		if(null != branchName){
			criteria.andBranchNameEqualTo(branchName);
		}
		if(null != principal){
			criteria.andPrincipalEqualTo(principal);
		}
		if(null != phoneNum){
			criteria.andPhoneNumEqualTo(phoneNum);
		}
		example.setOrderByClause("create_time desc");
		PageHelper.startPage(pageNum, pageSize);
		return (Page<FqTeam>) fqTeamMapper.selectByExample(example);
	}

	@Override
	public int addFqTeam(String principal, String phoneNum, Long branchId)
			throws Exception {
		FqBranch fqBranch = fqBranchMapper.selectByPrimaryKey(branchId);
		FqTeam fqTeam = new FqTeam();
		fqTeam.setPrincipal(principal);
		fqTeam.setPhoneNum(phoneNum);
		fqTeam.setBranchId(branchId);
		fqTeam.setBranchName(fqBranch.getBranchName());
		fqTeam.setCreateTime(new Date());
		return fqTeamMapper.insertSelective(fqTeam);
	}

	@Override
	public int deleteFqTeam(Long id) throws Exception {
		if(null != id && !id.equals("")){
			return fqTeamMapper.deleteByPrimaryKey(id);
		}
		return -1;
	}
	
	@Override
	public List<FqClerk> queryFqClerkByTeamId(Long teamId) throws Exception {
		FqClerkExample example = new FqClerkExample();
		example.createCriteria().andTeamIdEqualTo(teamId);
		List<FqClerk> list = fqClerkMapper.selectByExample(example);
		return list.size() > 0 ? list : null;
	}

	@Override
	public FqTeam fqTeamDetail(Long id) throws Exception{
		if(null != id){
			return fqTeamMapper.selectByPrimaryKey(id);
		}
		return null;
	}
	
	@Override
	public int updateFqTeam(Long id, String principal, String phoneNum, Long branchId)
			throws Exception {
		FqBranch fqBranch = fqBranchMapper.selectByPrimaryKey(branchId);
		FqTeam fqTeam = fqTeamMapper.selectByPrimaryKey(id);
		fqTeam.setPrincipal(principal);
		fqTeam.setPhoneNum(phoneNum);
		fqTeam.setBranchId(branchId);
		fqTeam.setBranchName(fqBranch.getBranchName());
		return fqTeamMapper.updateByPrimaryKeySelective(fqTeam);
	}
	
	@Override
	public List<FqTeam> queryAllFqTeam(Long branchId) throws Exception {
		FqTeamExample example = new FqTeamExample();
		example.createCriteria().andBranchIdEqualTo(branchId);
		return fqTeamMapper.selectByExample(example);
	}

	@Override
	public Page<FqClerkVo> queryFqClerkList(Date createStart, Date createEnd,
			String branchName, String teamName, Long id, String clerk,
			String phoneNum, Integer pageNum, Integer pageSize)
			throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("branchName", branchName);
		map.put("teamName", teamName);
		map.put("startTime", createStart);
		map.put("endTime", createEnd);
		map.put("clerk", clerk);
		map.put("phoneNum", phoneNum);
		PageHelper.startPage(pageNum, pageSize);
		return (Page<FqClerkVo>) fqClerkVoMapper.queryFqClerkVo(map);
	}

	@Override
	public int deleteFqClerk(Long id) throws Exception {
		if(null != id && !id.equals("")){
			return fqClerkMapper.deleteByPrimaryKey(id);
		}
		return -1;
	}
	
	@Override
	public List<StoreInfo> getStoreInfoByClerkId(Long clerkId) throws Exception {
		StoreInfoExample example = new StoreInfoExample();
		example.createCriteria().andClerkIdEqualTo(clerkId);
		List<StoreInfo> list = storeInfoMapper.selectByExample(example);
		return list.size() > 0 ? list : null;
	}
	
	@Override
	public List<FqCommissionRole> fqCommissionRoleList() throws Exception {
		FqCommissionRoleExample example = new FqCommissionRoleExample();
		example.createCriteria();
		return fqCommissionRoleMapper.selectByExample(example);
	}

	@Override
	public FqClerkVo fqClerkDetail(Long clerkId) throws Exception {
		if(null != clerkId){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("clerkId", clerkId);
			return fqClerkVoMapper.getFqClerkVo(map);
		}
		return null;
	}

	@Override
	public int updateFqClerk(Long id, String clerk, String phoneNum, Long teamId, String teamName, Date workDate,
			Long commission) throws Exception {
		FqClerk fqClerk = fqClerkMapper.selectByPrimaryKey(id);
		fqClerk.setClerk(clerk);
		fqClerk.setPhoneNum(phoneNum);
		fqClerk.setTeamId(teamId);
		fqClerk.setTeamName(teamName);
		fqClerk.setWorkDate(workDate);
		fqClerk.setCommission(commission);
		return fqClerkMapper.updateByPrimaryKeySelective(fqClerk);
	}

	@Override
	public int addFqClerk(String clerk, String phoneNum, Long teamId, String teamName,
			 Date workDate, Long commission) throws Exception {
		FqClerk fqClerk = new FqClerk();
		fqClerk.setClerk(clerk);
		fqClerk.setPhoneNum(phoneNum);
		fqClerk.setTeamId(teamId);
		fqClerk.setTeamName(teamName);
		fqClerk.setCreateTime(new Date());
		fqClerk.setWorkDate(workDate);
		fqClerk.setCommission(commission);
		return fqClerkMapper.insertSelective(fqClerk);
	}

	@Override
	public List<FqClerk> queryAllFqClerk(Long teamId) throws Exception {
		FqClerkExample example = new FqClerkExample();
		example.createCriteria().andTeamIdEqualTo(teamId);
		return fqClerkMapper.selectByExample(example);
	}
	
	@Override
	public List<FqClerk> queryAllFqClerkByCommissionId(Long commissionId) throws Exception {
		FqClerkExample example = new FqClerkExample();
		example.createCriteria().andCommissionEqualTo(commissionId);
		return fqClerkMapper.selectByExample(example);
	}
}
