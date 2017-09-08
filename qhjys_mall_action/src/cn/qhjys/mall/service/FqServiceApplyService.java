package cn.qhjys.mall.service;

import java.util.Map;

import cn.qhjys.mall.entity.FqFinancialSupport;
import cn.qhjys.mall.entity.FqServiceApply;

import com.github.pagehelper.Page;

public interface FqServiceApplyService {
  public Page<FqServiceApply> queryFqServiceApply(Map<String, Object> map);
  public int insertFqServiceApply(FqServiceApply fqServiceApply);
  public int updateFqServiceApplyStatus(Long id,Integer status);
  public FqServiceApply getFqServiceApplyById(Long id);
  public int updateFqServiceApplyStatus(String ids,Integer status);
  public int insertSelective(FqFinancialSupport record);
  public int insertFinancialSupport(FqServiceApply fqServiceApply,FqFinancialSupport record);
  public FqFinancialSupport getFqFinancialSupportByApplyId(Long applyId);
}
