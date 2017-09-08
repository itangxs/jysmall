package cn.qhjys.mall.service;

import java.util.List;
import cn.qhjys.mall.entity.CategoryInfo;
import com.alibaba.fastjson.JSONArray;

/**
 * 商品类别管理
 * 
 * @author LiXiang
 *
 */
public interface CategoryService {

	/**
	 * 查询商品分类
	 * 
	 * @param id
	 *            分类编号
	 * @return
	 */
	public CategoryInfo queryCategory(long id) throws Exception;

	/**
	 * 根据父类别查询子分类
	 * 
	 * @param parentid
	 * @return
	 */
	public List<CategoryInfo> queryCategoryByParent(long parentid) throws Exception;

	/**
	 * 查询商品分类
	 * 
	 * @return
	 */
	public JSONArray queryCategoryList() throws Exception;

	/**
	 * 添加商品分类
	 * 
	 * @param info
	 * @return
	 */
	public boolean insertCategory(CategoryInfo info) throws Exception;

	/**
	 * 修改商品分类信息
	 * 
	 * @param info
	 *            分类信息
	 * @return
	 */
	public boolean updateCategory(CategoryInfo info) throws Exception;

	/**
	 * 删除商品分类
	 * 
	 * @param id
	 *            分类编号
	 * @return
	 */
	public boolean deleteCategory(long id) throws Exception;
	
	public List<CategoryInfo> queryAll();
	public List<CategoryInfo> queryAllSon();
}