package com.matrix.ams.loan.service;

import java.util.List;

import com.matrix.ams.loan.dao.BillDao;
import com.matrix.ams.loan.model.Bill;
import com.matrix.ams.loan.model.LoanBill;
import com.matrix.core.service.BaseService;

public interface BillService extends BaseService<Bill,Long>{
	
	public BillDao getDao();
	
	/**
	 * 生成订单，并返回订单号
	 * @param loanBill
	 * @return
	 */
	public String saveOrder(LoanBill loanBill);
	
	/**
	 * 生成(账单)还款计划
	 * @param loanBill
	 */
	public void saveRepaymentPlan(LoanBill loanBill);
	
	/**
	 * 根据订单号查找账单
	 * @param orderId
	 * @return
	 */
	public List<Bill> findBillByOrderId(String orderId);
	
}
