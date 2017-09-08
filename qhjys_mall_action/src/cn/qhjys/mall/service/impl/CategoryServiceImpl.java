package cn.qhjys.mall.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.qhjys.mall.entity.CategoryInfo;
import cn.qhjys.mall.entity.CategoryInfoExample;
import cn.qhjys.mall.mapper.CategoryInfoMapper;
import cn.qhjys.mall.service.CategoryService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryInfoMapper categoryMapper;

	@Override
	public CategoryInfo queryCategory(long id) throws Exception {
		return categoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<CategoryInfo> queryCategoryByParent(long parentid) throws Exception {
		CategoryInfoExample example = new CategoryInfoExample();
		example.createCriteria().andParentIdEqualTo(parentid).andEnabledEqualTo(1);
		return categoryMapper.selectByExample(example);
	}

	@Override
	public JSONArray queryCategoryList() throws Exception {
		CategoryInfoExample example = new CategoryInfoExample();
		example.createCriteria().andEnabledEqualTo(1);
		List<CategoryInfo> list = categoryMapper.selectByExample(example);
		JSONArray array = new JSONArray();
		JSONObject obj = null;
		if (list == null || list.size() == 0)
			return array;
		for (CategoryInfo info : list) {
			if (info.getParentId() == 0) {
				obj = new JSONObject();
				obj.put("id", info.getId());
				obj.put("text", info.getName());
				obj.put("childs", getCategoryTree(list, info.getId()));
				array.add(obj);
			}
		}
		return array;
	}

	private JSONArray getCategoryTree(List<CategoryInfo> list, Long parent) throws Exception {
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
		for (CategoryInfo info : list) {
			if (parent == info.getParentId()) {
				obj = new JSONObject();
				obj.put("id", info.getId());
				obj.put("text", info.getName());
				obj.put("childs", getCategoryTree(list, info.getId()));
				array.add(obj);
			}
		}
		return array;
	}

	@Override
	public boolean insertCategory(CategoryInfo info) throws Exception {
		return false;
	}

	@Override
	public boolean updateCategory(CategoryInfo info) throws Exception {
		return false;
	}

	@Override
	public boolean deleteCategory(long id) throws Exception {
		return false;
	}

	@Override
	public List<CategoryInfo> queryAll() {
		CategoryInfoExample example = new CategoryInfoExample();
		return categoryMapper.selectByExample(example);
	}

	@Override
	public List<CategoryInfo> queryAllSon() {
		CategoryInfoExample example = new CategoryInfoExample();
		example.createCriteria().andParentIdGreaterThan(0L);
		return categoryMapper.selectByExample(example);
	}
}