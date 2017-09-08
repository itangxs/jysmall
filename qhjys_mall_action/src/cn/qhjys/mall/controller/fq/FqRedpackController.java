package cn.qhjys.mall.controller.fq;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.entity.FqRedpackRecord;
import cn.qhjys.mall.service.fq.FqRedpackService;

@Controller
@RequestMapping("/user/fqRedpack")
public class FqRedpackController {
	@Autowired
	private FqRedpackService fqRedPackService;
	
	@RequestMapping("/index")
	public synchronized ModelAndView getRedpack(Long rpid){
		ModelAndView view = new ModelAndView("/weixin/redpack/redpack");
		FqRedpackRecord record = fqRedPackService.getredpackRecord(rpid);
		if (record.getStatus() == 0) {
			view.addObject("record", record);
		}
		return view;
	}
	@RequestMapping("/virify")
	public synchronized ModelAndView virifyRedpack(HttpSession session,Long rpid) throws Exception{
		ModelAndView view = new ModelAndView("/weixin/redpack/virify");
		String path = session.getServletContext().getRealPath("");
		int result = fqRedPackService.updateRedpackRecordStatus(rpid,path);
		view.addObject("result", result);
	
		return view;
	}
}
