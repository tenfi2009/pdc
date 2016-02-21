package com.matrix.basicData.service;

import java.util.List;

import com.matrix.basicData.model.ProductCategory;
import com.matrix.core.service.TreeService;

/**
 * @author Administrator
 */
public interface ProCategoryService extends TreeService<ProductCategory, Long>{
	public List<ProductCategory> getChildren(String parentId);
}
