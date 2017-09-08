/*package cn.qhjys.mall.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.service.StoreService;
import com.github.pagehelper.Page;
import cn.qhjys.mall.vo.StoreDetaileVo;
import cn.qhjys.mall.vo.StoreVo;

@Controller
@RequestMapping("/store")
public class StoreController {
	private final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private StoreService storeService;

	*//***
	 * @param storeName商铺名称
	 * @param page分页参数
	 *            (页数)
	 * @param pageSize分页参数
	 *            (每页多少)
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	@RequestMapping(value = "/findSearchStores", method = RequestMethod.POST)
	public Page<StoreVo> findSearchStores(String storeName, Integer page, Integer pageSize, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Page<StoreVo> pages = new Page<StoreVo>();
		pages.setPageNum(page);
		pages.setPageSize(pageSize);
		return storeService.searchStores(storeName, pages);
	}

	*//***
	 * 根据商品id获取商家信息
	 * 
	 * @param id
	 *            商品编号
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	@RequestMapping(value = "/getSellerByPId", method = RequestMethod.POST)
	public StoreVo getSellerByPId(Long pid, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return storeService.getSellerByProdId(pid);
	}

	*//***
	 * 修改店铺信息
	 * 
	 * @param store
	 * @param request
	 * @param response
	 * @throws Exception
	 *//*
	@RequestMapping(value = "/updateStore", method = RequestMethod.POST)
	public void updateStore(StoreInfo info, HttpServletRequest request, HttpServletResponse response) throws Exception {
		storeService.updateStore(info);
	}

	*//***
	 * 查询店铺详情信息
	 * 
	 * @param storeId
	 *            店铺编号
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	@RequestMapping(value = "/updateStore", method = RequestMethod.POST)
	public StoreDetaileVo findStoreDetaileList(Long storeId, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return storeService.getStoreDetaile(storeId);
	}

}*/