package cn.qhjys.mall.service.fq;

import java.util.List;

import cn.qhjys.mall.entity.FqRandom;

public interface FqRandomService {
	public int insertFqRandom(List<FqRandom> fqRandoms);
	public int updateFqRandom(FqRandom fqRandom);
	public FqRandom getFqRandom();
}
