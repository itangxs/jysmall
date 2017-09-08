package cn.qhjys.mall.mapper.custom;

import java.util.List;

import cn.qhjys.mall.vo.system.StoreLoanVo;

public interface StoreLoanVoMapper {

	public List<StoreLoanVo> queryStoreLoanVoHistory();
	
	public List<StoreLoanVo> queryStoreLoanVoCurrent();
	
}
