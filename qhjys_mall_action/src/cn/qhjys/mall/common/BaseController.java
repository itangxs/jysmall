package cn.qhjys.mall.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;


/**
 * 基础视图控制器
 * 
 * @author llw
 *
 */
public class BaseController {
	
	/**
	 * 日志初始化
	 */
	protected final Log log = LogFactory.getLog(getClass());

	/**
	 * 返回数据（正确的）
	 */
	protected JSONResult data;

	/**
	 * 返回视图
	 */
	protected ModelAndView mav = new ModelAndView();
	
	/**
	 * 当前页
	 */
	protected int pageNo = 1;

	@ModelAttribute
	protected void initParams(HttpServletRequest request) {
		request.setAttribute("publicCSS", "1");
		request.setAttribute("publicJS", "1");
		data = new JSONResult();
	}


	/**
	 * 返回错误码
	 * 
	 * @param error
	 * @return
	 */
	protected JSONResult returnError(ErrorCode error) {
		return new JSONResult(error);
	}

	/**
	 * 返回错误码,和提示
	 * 
	 * @param error
	 * @return
	 */
	protected JSONResult returnError(ErrorCode error, String msg) {
		return new JSONResult(error, msg);
	}

	/** 通用返回视图和数据+key */
	public ModelAndView goToModelAndView(String url, String valueKey, Object valueObj) {
		mav.setViewName(url);
		if (valueKey != null && valueObj != null) {
			mav.addObject(valueKey, valueObj);
		}
		return mav;
	}

	/** 获取当前页 */
	public int getPageNo(HttpServletRequest request) {
		// 不为空
		if (request.getParameter("pageNo") != null && !request.getParameter("pageNo").equals("0")) {
			pageNo = Integer.parseInt(request.getParameter("pageNo").toString());
		} else {
			pageNo = 1;
		}
		return pageNo;
	}
	
	/**
     *  组合关键字查询条件
     */
    protected String makeKeywordLikeQueryString(String columns,String keyword,boolean needAnd) throws Exception{
    	StringBuffer sb=new StringBuffer();
		try {
			if(needAnd){
				sb.append(" and ");
			}
			sb.append("(");
			String[] strs=columns.split(",");
			for(int i=0,iSize=strs.length;i<iSize;i++){
				if(i>0){
					sb.append(" or ");
				}
				sb.append(strs[i]+" like '%"+keyword.trim()+"%'");
			}
			sb.append(")");
		} catch (Exception e) {
			log.error("组合关键字查询条件时出错",e);
			throw e;
		}
    	return sb.toString();
    }

}
