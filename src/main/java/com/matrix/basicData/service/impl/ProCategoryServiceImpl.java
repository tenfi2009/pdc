package com.matrix.basicData.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matrix.basicData.dao.ProCategoryDao;
import com.matrix.basicData.model.ProductCategory;
import com.matrix.basicData.service.ProCategoryService;
import com.matrix.core.dao.BaseDao;
import com.matrix.core.service.impl.TreeServiceImpl;

/**
 * @author Administrator
 */
@Service
public class ProCategoryServiceImpl extends TreeServiceImpl<ProductCategory, Long> implements ProCategoryService{
	@Autowired
	private ProCategoryDao dao;
	
	@Override
	public ProCategoryDao getDao() {
		return dao;
	}
	
}
