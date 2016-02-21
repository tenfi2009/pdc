package com.matrix.ams.loan.dao;

import java.util.List;

import com.matrix.ams.loan.model.LoanBill;
import com.matrix.ams.paycenter.model.QueryPayResult;
import com.matrix.core.dao.BaseDao;


public interface LoanBillDao extends BaseDao<LoanBill,String>{
	/**
	 * 查询所有放款中的数据
	 * @return
	 */
	public List<String> getLoaningBillIds();
	
	/**
	 * 更新放款单支付状态
	 * @param id
	 * @param rs
	 */
	public void updateLoanStatus(String id,QueryPayResult rs);
}
