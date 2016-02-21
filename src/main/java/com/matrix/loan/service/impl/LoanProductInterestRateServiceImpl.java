package com.matrix.loan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matrix.core.dao.BaseDao;
import com.matrix.core.service.impl.BaseServiceImpl;
import com.matrix.loan.dao.LoanProductInterestRateDao;
import com.matrix.loan.model.LoanProductInterestRate;
import com.matrix.loan.service.LoanProductInterestRateService;

@Service
public class LoanProductInterestRateServiceImpl extends BaseServiceImpl<LoanProductInterestRate,String> implements LoanProductInterestRateService{

	@Autowired
	private LoanProductInterestRateDao dao;
	
	@SuppressWarnings("rawtypes")
	@Override
	public BaseDao getDao() {
		return dao;
	}



}
