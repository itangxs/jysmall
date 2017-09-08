package cn.qhjys.mall.service.impl;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.qhjys.mall.entity.PhoneCode;
import cn.qhjys.mall.entity.PhoneCodeExample;
import cn.qhjys.mall.mapper.PhoneCodeMapper;
import cn.qhjys.mall.service.PhoneCodeService;

@Service
public class PhoneCodeServiceImpl implements PhoneCodeService {
	@Autowired
	private PhoneCodeMapper phoneCodeMapper;

	@Override
	public boolean canDoPhoneCode(String phone) throws Exception {
		long time = new Date().getTime();
		PhoneCodeExample example = new PhoneCodeExample();
		example.createCriteria().andPhoneEqualTo(phone).andCreateTimeGreaterThan(time - 60000);
		int phoneCount = phoneCodeMapper.countByExample(example);
		return phoneCount > 0 ? false : true;
	}

	@Override
	public boolean insertPhondCode(String phone, String captcha, String ip) throws Exception {
		long time = new Date().getTime();
		PhoneCodeExample example = new PhoneCodeExample();
		example.createCriteria().andPhoneEqualTo(phone).andCreateTimeGreaterThan(time - 60000);
		int phoneCount = phoneCodeMapper.countByExample(example);
		if (phoneCount > 0)
			return false;
		PhoneCode phoneCode = new PhoneCode();
		phoneCode.setPhone(phone);
		phoneCode.setCaptcha(captcha);
		phoneCode.setIp(ip);
		phoneCode.setCreateTime(time);
		int result = phoneCodeMapper.insertSelective(phoneCode);
		return result > 0 ? true : false;
	}
}