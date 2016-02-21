package com.matrix.ams.paycenter.service;

import com.matrix.ams.loan.model.LoanBill;
import com.matrix.ams.paycenter.model.PayResult;
import com.matrix.ams.paycenter.model.QueryPayResult;

public interface PayMoneyFacadeService {
	public PayResult submitPayMoney(LoanBill loanBill);
	
	/**
	 * 查询订单的付款状态
	 * 
	 * @param orderId
	 * @return
	 */
	public QueryPayResult queryPayResult(String orderId);
}
