package cn.qhjys.mall.vo;

import java.util.List;

import cn.qhjys.mall.entity.FqProduct;

public class FqTypeAndProductVo {
	
	private String typeName; // 类别名称
	
	private List<FqProduct> products;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public List<FqProduct> getProducts() {
		return products;
	}

	public void setProducts(List<FqProduct> products) {
		this.products = products;
	}
	

}
