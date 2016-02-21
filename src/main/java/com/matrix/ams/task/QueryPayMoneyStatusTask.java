package com.matrix.ams.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.matrix.ams.loan.service.LoanBillService;
import com.matrix.ams.paycenter.model.QueryPayResult;
import com.matrix.ams.paycenter.service.PayMoneyFacadeService;

public class QueryPayMoneyStatusTask {
	
	@Autowired
	private LoanBillService loanBillService;
	
	@Autowired
	private PayMoneyFacadeService payMoneyFacadeService;
	
	public void setLoanBillService(LoanBillService loanBillService) {
		this.loanBillService = loanBillService;
	}

	public void setPayMoneyFacadeService(PayMoneyFacadeService payMoneyFacadeService) {
		this.payMoneyFacadeService = payMoneyFacadeService;
	}



	public QueryPayMoneyStatusTask() {
		
	}

	public void task(){
		List<String> billIds = loanBillService.getLoaningBillIds();
		if(null == billIds || billIds.size() == 0){
			return;
		}
		
		for(String orderId : billIds){
			QueryPayResult rs = payMoneyFacadeService.queryPayResult(orderId);
			loanBillService.updateLoanStatus(orderId, rs);
		}
	}
}
