package cn.qhjys.mall.controller.fq;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.FqProduct;
import cn.qhjys.mall.entity.FqProductType;
import cn.qhjys.mall.entity.FqStore;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.service.fq.FqProductService;
import cn.qhjys.mall.service.fq.FqProductTypeService;
import cn.qhjys.mall.service.fq.FqStoreService;
import cn.qhjys.mall.util.Base64Util;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.SessionUtil;

@Controller
@RequestMapping("/managermall/seller/fqproducts")
public class SellerFqProductsController extends Base {

	@Autowired
	private FqStoreService fqStoreService;

	@Autowired
	private FqProductService fqProductService;

	@Autowired
	private FqProductTypeService fqProductTypeService;

	/**
	 * 
	 * type_manager 菜系管理
	 * 
	 * @param session
	 * @param page
	 * @param enable
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/type_manager")
	public Object type_manager(HttpSession session, @RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "enable", required = false) Integer enable,
			@RequestParam(value = "tjsjs", required = false) String tjsjs,
			@RequestParam(value = "tjsje", required = false) String tjsje,
			@RequestParam(value = "page", required = false) Integer page) throws Exception {
		if (null == page || page < 1)
			page = 0;
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		FqStore store = fqStoreService.getFqStoreBySellerId(seller.getId());
		if (null == store) {
			return super.goUrl("/managermall/seller/queryWXStoreInfo.do", "请先申请店铺");
		}
		Page<FqProductType> productTypes = fqProductService.queryproductTypePageByStore(store.getId(), name, enable,
				tjsjs, tjsje, page, 10);
		ModelAndView view = new ModelAndView("/seller/fqproduct/product_type_List");
		view.addObject("pageInfo", productTypes);
		view.addObject("page", page);
		view.addObject("enable", enable);
		view.addObject("name", name);
		view.addObject("tjsjs", tjsjs);
		view.addObject("tjsje", tjsje);
		return view;
	}

	@RequestMapping("/product_type_page")
	public Object product_type_page(HttpSession session, @RequestParam(value = "id", required = false) Long id)
			throws Exception {
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		FqStore store = fqStoreService.getFqStoreBySellerId(seller.getId());
		if (null == store) {
			return super.goUrl("/managermall/seller/queryWXStoreInfo.do", "请先申请店铺");
		}
		ModelAndView view = new ModelAndView("/seller/fqproduct/product_type");
		String type_pageTokne = UUID.randomUUID().toString();
		SessionUtil.setSession(session, "typePageTokne", type_pageTokne);
		if (null != id) {
			FqProductType productType = fqProductService.queryFqProductTypeBystorIdAndId(store.getId(), id);
			if (null != productType) {
				view.addObject("productType", productType);
			}
		}
		return view;
	}

	@RequestMapping("/product_type")
	public Object product_type(HttpSession session, @RequestParam(value = "token", required = true) String token,
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "typeName", required = true) String typeName,
			@RequestParam(value = "level", required = true) Integer level,
			@RequestParam(value = "enable", required = true) Integer enable) throws Exception {
		String stringToken = SessionUtil.getSession(session, "typePageTokne").toString();
		SessionUtil.removeSession(session, "typePageTokne");
		if (StringUtils.isEmpty(stringToken) | !stringToken.equals(token)) {
			return super.goUrl("/managermall/seller/fqproducts/type_manager.do", "请不要重复提交");
		}
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		FqStore store = fqStoreService.getFqStoreBySellerId(seller.getId());
		if (null == store) {
			return super.goUrl("/managermall/seller/queryWXStoreInfo.do", "请先申请店铺");
		}
		Boolean boolean1 = fqProductService.saveOrUpdateFqProductType(store.getId(), store.getStoreName(), id, typeName,
				level, enable);
		if (boolean1) {
			return super.goUrl("/managermall/seller/fqproducts/type_manager.do", "成功");
		} else {
			return super.goUrl("/managermall/seller/fqproducts/type_manager.do", "失败");
		}

	}

	@RequestMapping("/type_changeStatus")
	@ResponseBody
	public String type_changeStatus(HttpSession session, @RequestParam(value = "ids", required = true) Long[] ids,
			Integer status) throws Exception {
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		FqStore store = fqStoreService.getFqStoreBySellerId(seller.getId());
		if (null == store) {
			return "error";
		}
		if (fqProductService.updateProductTypeStatusByStoreId(store.getId(), ids, status)) {
			return "success";
		} else {
			return "error";
		}
	}

	/**
	 * 菜品管理
	 * 
	 * @param session
	 * @param page
	 * @param pageSize
	 * @param name
	 * @param tjsjs
	 * @param tjsje
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/manager")
	public Object pageList(HttpSession session, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "pagesize", required = false) Integer pageSize,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "enable", required = false) Integer enable,
			@RequestParam(value = "tjsjs", required = false) String tjsjs,
			@RequestParam(value = "tjsje", required = false) String tjsje) throws Exception {
		ModelAndView view = new ModelAndView("/seller/fqproduct/productList");
		if (null == page || page < 1)
			page = 0;
		if (null == pageSize || pageSize < 10)
			pageSize = 10;
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		FqStore fqStore = fqStoreService.getFqStoreBySellerId(seller.getId());
		if (fqStore == null || fqStore.getStatus() != 1) {
			// 店铺不存在
			return super.goUrl("/managermall/seller/queryWXStoreInfo.do", "店铺信息不存在或未通过审核");
		}
		Page<FqProduct> productInfo = fqProductService.searchProductListByStoreId(fqStore.getId(), name, enable, tjsjs,
				tjsje, page, pageSize);
		view.addObject("productInfo", productInfo);
		view.addObject("name", name);
		view.addObject("enable", enable);
		view.addObject("tjsjs", tjsjs);
		view.addObject("tjsje", tjsje);
		return view;
	}

	/**
	 * 编辑菜品
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/productModify")
	public Object modifyProduct(HttpSession session, String product) throws Exception {
		ModelAndView view = new ModelAndView("/seller/fqproduct/productModify");
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		FqStore fqStore = fqStoreService.getFqStoreBySellerId(seller.getId());
		if (fqStore == null || fqStore.getStatus() != 1) {
			// 店铺不存在 /managermall/seller/queryWXStoreInfo.do
			return super.goUrl("/managermall/seller/queryWXStoreInfo.do", "店铺信息不存在或未通过审核");
		}

		// 获取店铺产品分类
		List<FqProductType> productTypes = fqProductTypeService.queryEnableProductTypeByStoreId(fqStore.getId());
		// List<FqProductType> productTypes = new ArrayList<FqProductType>();
		// for (int i = 0; i < 5; i++) {
		// FqProductType fqProductType = new FqProductType();
		// fqProductType.setId(new Long(i+1));;
		// fqProductType.setTypeName("测试类别"+i);
		// productTypes.add(fqProductType);
		// }
		view.addObject("productTypes", productTypes);

		String token = UUID.randomUUID().toString();
		SessionUtil.setSession(session, "add_product", token);
		view.addObject("token", token);
		if (!StringUtils.isEmpty(product)) {
			view.addObject("product",
					JSON.parseObject(URLDecoder.decode(Base64Util.decode(product)), FqProduct.class));
		}
		return view;
	};

	/**
	 * 添加菜品
	 * 
	 * @param session
	 * @param token
	 * @param cpmc
	 *            菜品名称
	 * @param scj
	 *            市场价
	 * @param kc
	 *            库存
	 * @param xsyxj
	 *            显示优先级
	 * @param cptpdz
	 *            菜品图片
	 * @param cpxx
	 *            菜品信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/add")
	public Object add(HttpSession session, @RequestParam(value = "token", required = true) String token,
			@RequestParam(value = "cpmc", required = true) String cpmc,
			@RequestParam(value = "scj", required = true) String scj,
			@RequestParam(value = "kc", required = true) Integer kc,
			@RequestParam(value = "xsyxj", required = true) Integer xsyxj,
			@RequestParam(value = "cplb", required = true) Long cplb,
			@RequestParam(value = "imgs", required = false) String cptpdz,
			@RequestParam(value = "cpxx", required = false) String cpxx) throws Exception {
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		FqStore fqStore = fqStoreService.getFqStoreBySellerId(seller.getId());
		Object tokenSession;
		try {
			tokenSession = SessionUtil.getSession(session, "add_product");
			SessionUtil.removeSession(session, "add_product");

			if (fqStore == null || fqStore.getStatus() != 1) {
				// 店铺不存在 /managermall/seller/queryWXStoreInfo.do
				return super.goUrl("/managermall/seller/queryWXStoreInfo.do", "店铺信息不存在或未通过审核");
			}

			if (tokenSession != null && tokenSession.equals(token)) {
				FqProduct fqProduct = new FqProduct();
				fqProduct.setProductName(cpmc);
				fqProduct.setPrice(new BigDecimal(scj));
				fqProduct.setStock(kc);
				fqProduct.setStatus(0);
				fqProduct.setLevel(xsyxj);
				fqProduct.setProductImages(cptpdz);
				fqProduct.setProductInfo(cpxx);
				;
				fqProduct.setStoreId(fqStore.getId());
				fqProduct.setCreateTime(new Date());
				fqProduct.setProductType(cplb);

				Boolean bl = false;
				try {
					bl = fqProductService.saveProduct(fqProduct);
				} catch (Exception e) {
					this.logger.error("添加菜品异常", e);
				}

				if (bl) {
					return super.goUrl("/managermall/seller/fqproducts/manager.do", "添加成功");
					// return
					// super.goUrl("/managermall/seller/fqproducts/productModify.do?product="+Base64Util.encode(URLEncoder.encode(JSONObject.toJSONString(fqProduct))),
					// "添加失败");
				} else {
					return super.goUrl("/managermall/seller/fqproducts/productModify.do?product="
							+ Base64Util.encode(URLEncoder.encode(JSON.toJSONString(fqProduct))), "添加失败");
				}

			} else {
				// 提示不要重复添加
				return super.goUrl("/managermall/seller/fqproducts/manager.do", "请不要重复提交");
			}

		} catch (Exception e) {
			this.logger.error("添加菜品异常", e);
		}

		return super.goUrl("/managermall/seller/fqproducts/manager.do", "添加失败");
	}

	@RequestMapping("/productUpdate")
	public Object add(HttpSession session, @RequestParam(value = "prodId", required = true) Long prodId)
			throws Exception {
		ModelAndView view = new ModelAndView("/seller/fqproduct/productUpdate");
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		FqStore fqStore = fqStoreService.getFqStoreBySellerId(seller.getId());
		if (fqStore == null || fqStore.getStatus() != 1) {
			// 店铺不存在 /managermall/seller/queryWXStoreInfo.do
			return super.goUrl("/managermall/seller/queryWXStoreInfo.do", "店铺信息不存在或未通过审核");
		}
		// 获取店铺产品分类
		List<FqProductType> productTypes = fqProductTypeService.queryEnableProductTypeByStoreId(fqStore.getId());
		view.addObject("productTypes", productTypes);

		String token = UUID.randomUUID().toString();
		SessionUtil.setSession(session, "update_product", token);
		view.addObject("token", token);

		FqProduct product = fqProductService.getProductInfoById(fqStore.getId(), prodId);
		view.addObject("productInfo", product);
		return view;

	}

	/**
	 * 修改菜品
	 * 
	 * @param session
	 * @param token
	 * @param cpmc
	 *            菜品名称
	 * @param scj
	 *            市场价
	 * @param kc
	 *            库存
	 * @param xsyxj
	 *            显示优先级
	 * @param cptpdz
	 *            菜品图片
	 * @param cpxx
	 *            菜品信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/update")
	public Object update(HttpSession session, @RequestParam(value = "token", required = true) String token,
			@RequestParam(value = "prodId", required = true) Long prodId,
			@RequestParam(value = "cpmc", required = true) String cpmc,
			@RequestParam(value = "scj", required = true) String scj,
			@RequestParam(value = "kc", required = true) Integer kc,
			@RequestParam(value = "xsyxj", required = true) Integer xsyxj,
			@RequestParam(value = "cplb", required = true) Long cplb,
			@RequestParam(value = "imgs", required = false) String cptpdz,
			@RequestParam(value = "cpxx", required = false) String cpxx) throws Exception {
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		FqStore fqStore = fqStoreService.getFqStoreBySellerId(seller.getId());
		Object tokenSession;
		try {
			tokenSession = SessionUtil.getSession(session, "update_product");
			SessionUtil.removeSession(session, "update_product");

			if (fqStore == null || fqStore.getStatus() != 1) {
				// 店铺不存在
				return super.goUrl("/managermall/seller/queryWXStoreInfo.do", "店铺信息不存在或未通过审核");
			}

			if (tokenSession != null && tokenSession.equals(token)) {
				FqProduct fqProduct = fqProductService.getProductInfoById(fqStore.getId(), prodId);
				if (fqProduct == null) {
					return super.goUrl("/managermall/seller/fqproducts/manager.do", "服务器忙,请稍候再试");
				}

				fqProduct.setProductName(cpmc);
				fqProduct.setPrice(new BigDecimal(scj));
				fqProduct.setStock(kc);
				// 修改之后状态是下架的
				fqProduct.setStatus(0);
				fqProduct.setLevel(xsyxj);
				fqProduct.setProductImages(cptpdz);
				fqProduct.setProductInfo(cpxx);
				fqProduct.setStoreId(fqStore.getId());
				fqProduct.setProductType(cplb);

				Boolean bl = false;
				try {
					bl = fqProductService.updateProduct(fqProduct);
				} catch (Exception e) {
					this.logger.error("修改菜品异常", e);
				}

				if (bl) {
					return super.goUrl("/managermall/seller/fqproducts/manager.do", "修改成功");
				} else {
					return super.goUrl("/managermall/seller/fqproducts/manager.do", "修改失败");
				}

			} else {
				// 提示不要重复添加
				return super.goUrl("/managermall/seller/fqproducts/manager.do", "请不要重复提交");
			}

		} catch (Exception e) {
			this.logger.error("修改菜品异常", e);
		}

		return super.goUrl("/managermall/seller/fqproducts/manager.do", "修改失败");
	}

	@RequestMapping("/changeStatus")
	@ResponseBody
	public String changeStatus(@RequestParam(value = "ids", required = true) Long[] ids, Integer status) {
		if (fqProductService.updateProductStatus(ids, status)) {
			return "success";
		} else {
			return "error";
		}
	}

	@RequestMapping("/deleteByIds")
	@ResponseBody
	public String deleteByIds(Long[] ids) {
		if (fqProductService.deleteProductByIds(ids)) {
			return "success";
		} else {
			return "error";
		}
	}

}
