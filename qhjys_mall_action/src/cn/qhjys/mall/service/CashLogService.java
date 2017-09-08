package cn.qhjys.mall.service;

import java.util.List;

import com.github.pagehelper.Page;

import cn.qhjys.mall.entity.CashLog;

public interface CashLogService {
	public Page<CashLog> queryCashLog(Long accountId, Integer pageNum, Integer pageSize);
}
