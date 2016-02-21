package com.matrix.loan.dao.impl;

import org.springframework.stereotype.Repository;

import com.matrix.core.dao.impl.BaseDaoImpl;
import com.matrix.loan.dao.ProductDao;
import com.matrix.loan.model.LoanProduct;

@Repository
public class ProductDaoImpl extends BaseDaoImpl<LoanProduct, String> implements ProductDao {

}
