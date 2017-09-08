package cn.qhjys.mall.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.AdminUser;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.entity.UserInfo;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.FileState;
import cn.qhjys.mall.util.FileUtil;
import cn.qhjys.mall.util.HtmlUtil;

import com.alibaba.fastjson.JSONObject;

@Controller
public class FileUploadController extends Base {
	private static final String accountPath = "account/";
	private static final String sellerPath = "seller/";
	private static final String systemPath = "system/";

	/**
	 * 单文件上传
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/fileUpload")
	public void fileUpload(HttpSession session, MultipartHttpServletRequest multipartRequest,
			HttpServletResponse response, String delPath) throws Exception {
		logger.info("-------进入上传-------------");
		JSONObject json = new JSONObject();
		String match = null, path = session.getServletContext().getRealPath("") + FileUtil.path;
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		AdminUser adminUser = (AdminUser) session.getAttribute(ConstantsConfigurer.getProperty("system_key"));
		if (user == null && seller == null&& adminUser==null) {
			json.put("flag", FileState.UPLOAD_NOT_LOGIN.getFlag());
			json.put("state", FileState.UPLOAD_NOT_LOGIN.getState());
			HtmlUtil.writerJson(response, json);
			return;
		} else if (adminUser !=null)
			match = systemPath + adminUser.getId() + "/";
		else if (seller != null)
			match = sellerPath +  seller.getId() + "/";
		else if ( user!= null)
			match = accountPath + user.getId()+ "/";
		path += match;
		logger.info("-------path-------------"+path);
		MultipartFile file = multipartRequest.getFile("file");
		if (file.isEmpty()) {
			json.put("flag", FileState.UPLOAD_ZEROSIZE.getFlag());
			json.put("state", FileState.UPLOAD_ZEROSIZE.getState());
		} else {
			logger.info(file.getOriginalFilename());
			String fileName = FileUtil.getNumberName(file.getOriginalFilename());
			try {
				FileState state = FileUtil.upload4Stream(fileName, path, file.getInputStream());
				// 替换文件时，删除原文件
				if (!BaseUtil.judgeNull(delPath) && delPath.indexOf(match) > -1) {
					String delFile = delPath.substring(delPath.lastIndexOf("/") + 1);
					FileUtil.removeFile(path + delFile);
				}
				json.put("flag", state.getFlag());
				json.put("state", state.getState());
				json.put("src", FileUtil.path + match + fileName);
				logger.info("文件上传成功, path:" + path + fileName);
			} catch (Exception e) {
				json.put("flag", FileState.UPLOAD_FAILURE.getFlag());
				json.put("state", FileState.UPLOAD_FAILURE.getState());
				logger.error("保存文件异常!", e);
			}
		}
		HtmlUtil.writerJson(response, json);
	}

	/**
	 * 删除文件
	 * 
	 * @param session
	 * @param response
	 * @param request
	 * @param filePath
	 *            文件相对路径
	 */
	@RequestMapping("/fileDelete")
	public void fileDelete(HttpSession session, HttpServletResponse response, HttpServletRequest request,
			String filePath) {
		filePath = filePath.substring(filePath.lastIndexOf("/") + 1);
		JSONObject json = new JSONObject();
		if (BaseUtil.judgeNull(filePath)) {
			json.put("flag", FileState.UPLOAD_NOTFOUND.getFlag());
			json.put("state", FileState.UPLOAD_NOTFOUND.getState());
			HtmlUtil.writerJson(response, json);
			return;
		}
		String match = null, path = session.getServletContext().getRealPath("") + FileUtil.path;
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		AdminUser adminUser = (AdminUser) session.getAttribute(ConstantsConfigurer.getProperty("system_key"));
		if (user == null && seller == null && adminUser ==null) {
			json.put("flag", FileState.UPLOAD_NOT_LOGIN.getFlag());
			json.put("state", FileState.UPLOAD_NOT_LOGIN.getState());
			HtmlUtil.writerJson(response, json);
			return;
		} else if (adminUser != null) {
			match = systemPath + adminUser.getId() + "/";
			path += match;
			if (filePath.indexOf(match) > -1 && FileUtil.removeFile(path + filePath)) {
				json.put("flag", FileState.UPLOAD_SUCCSSS.getFlag());
				json.put("state", FileState.UPLOAD_SUCCSSS.getState());
			} else {
				json.put("flag", FileState.UPLOAD_NOT_AUTH.getFlag());
				json.put("state", FileState.UPLOAD_NOT_AUTH.getState());
			}
		} else if(seller != null){
			match = sellerPath + seller.getId() + "/";
			path += match;
			if (filePath.indexOf(match) > -1 && FileUtil.removeFile(path + filePath)) {
				json.put("flag", FileState.UPLOAD_SUCCSSS.getFlag());
				json.put("state", FileState.UPLOAD_SUCCSSS.getState());
			} else {
				json.put("flag", FileState.UPLOAD_NOT_AUTH.getFlag());
				json.put("state", FileState.UPLOAD_NOT_AUTH.getState());
			}
		}else{
			match = accountPath + user.getId()+ "/";
			path += match;
			if (filePath.indexOf(match) > -1 && FileUtil.removeFile(path + filePath)) {
				json.put("flag", FileState.UPLOAD_SUCCSSS.getFlag());
				json.put("state", FileState.UPLOAD_SUCCSSS.getState());
			} else {
				json.put("flag", FileState.UPLOAD_NOT_AUTH.getFlag());
				json.put("state", FileState.UPLOAD_NOT_AUTH.getState());
			}
		}
		HtmlUtil.writerJson(response, json);
	}
	
	
}