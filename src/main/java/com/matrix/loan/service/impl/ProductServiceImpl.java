package com.matrix.loan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matrix.core.dao.BaseDao;
import com.matrix.core.service.impl.BasicDataServiceImpl;
import com.matrix.loan.dao.ProductDao;
import com.matrix.loan.model.LoanProduct;
import com.matrix.loan.service.ProductService;

@Service
public class ProductServiceImpl extends BasicDataServiceImpl<LoanProduct,String> implements ProductService{

	@Autowired
	private ProductDao dao;
	
	@Override
	public BaseDao getDao() {
		return dao;
	}

}
