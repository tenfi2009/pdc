package com.matrix.ams.loan.service;

import java.util.List;

import com.matrix.ams.loan.model.LoanBill;
import com.matrix.ams.paycenter.model.QueryPayResult;
import com.matrix.core.service.BaseService;

public interface LoanBillService extends BaseService<LoanBill,String>{

	/**
	 * 生成付款单号
	 * @return
	 */
	public String generateBillNumber();
	
	/**
	 * 提交贷款单
	 */
	public void submit(String id);
	
	/**
	 * 保存正常的放款单
	 * @param bill
	 */
	public void saveLoanBill(LoanBill bill);
	
	/**
	 * 保存线上放款失败的放款单，放款状态并更新为线下放款
	 * @param bill
	 */
	public void saveFailLoanBill(LoanBill bill);
	
	/**
	 * 根据id删除放款单
	 * @param id
	 */
	public void deleteLoanBill(String id);
	
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
