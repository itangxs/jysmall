package cn.qhjys.mall.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.qhjys.mall.service.PhoneCodeService;
import cn.qhjys.mall.util.MessageUtil;

@Controller
@RequestMapping("/sendError")
public class SendErrorMessage {
	
	@Autowired
	private PhoneCodeService phoneCodeService;
	
	@RequestMapping("/sendSMS")
	@ResponseBody
	public boolean sendSMSCaptcha() throws Exception {
		
		Map<String, String> map = new HashMap<String, String>();
		boolean result = MessageUtil.sendSmsContent("18300043285", "服务器异常,请及时查看处理");
		 result = MessageUtil.sendSmsContent("15818553558", "服务器异常,请及时查看处理");//小林总
		 result = MessageUtil.sendSmsContent("18153860282", "服务器异常,请及时查看处理");//黄生友
		 result = MessageUtil.sendSmsContent("18218723905", "服务器异常,请及时查看处理");//李洪辉
		 result = MessageUtil.sendSmsContent("13924668452", "服务器异常,请及时查看处理");//刘然
		 result = MessageUtil.sendSmsContent("13088848583", "服务器异常,请及时查看处理");//曾庆华
		 result = MessageUtil.sendSmsContent("13480141154", "服务器异常,请及时查看处理");//吴嘉
		 result = MessageUtil.sendSmsContent("18680275399", "服务器异常,请及时查看处理");//余磊
		 result = MessageUtil.sendSmsContent("13265640904", "服务器异常,请及时查看处理");//苏甜甜
		return result;
	}
	@RequestMapping("/sendSMStest")
	@ResponseBody
	public boolean sendSMStest() throws Exception {
		
		Map<String, String> map = new HashMap<String, String>();
		boolean result = MessageUtil.sendSmsContent("18300043285", "服务器异常,请及时查看处理(测试短信)");
//		result = MessageUtil.sendSmsContent("15818553558", "服务器异常,请及时查看处理(测试短信)");//小林总
		result = MessageUtil.sendSmsContent("18153860282", "服务器异常,请及时查看处理(测试短信)");//黄生友
		result = MessageUtil.sendSmsContent("18218723905", "服务器异常,请及时查看处理(测试短信)");//李洪辉
		result = MessageUtil.sendSmsContent("13924668452", "服务器异常,请及时查看处理(测试短信)");//刘然
		result = MessageUtil.sendSmsContent("13088848583", "服务器异常,请及时查看处理(测试短信)");//曾庆华
//		result = MessageUtil.sendSmsContent("13480141154", "服务器异常,请及时查看处理(测试短信)");//吴嘉
//		result = MessageUtil.sendSmsContent("18680275399", "服务器异常,请及时查看处理(测试短信)");//余磊
		result = MessageUtil.sendSmsContent("13265640904", "服务器异常,请及时查看处理(测试短信)");//苏甜甜
		return result;
	}
}
