package com.matrix.ams.repayment.service;

import java.util.Map;

import com.matrix.ams.loan.model.Bill;
import com.matrix.ams.repayment.model.RepaymentBill;
import com.matrix.ams.repayment.model.SettleBillDTO;
import com.matrix.core.service.BaseService;
import com.matrix.core.util.Page;

public interface RepaymentBillService extends BaseService<RepaymentBill,String>{
	/**
	 * 查询结算清单
	 * @param page
	 * @param queryParams
	 * @return
	 */
	public Page<SettleBillDTO> findSettleBillPage(Page<SettleBillDTO> page, Map queryParams);
	
	/**
	 * 
	 * @param billId
	 * @return
	 */
	public SettleBillDTO findSettleBillDTOByBillId(Integer billId);
	
	/**
	 * 查询偿还订单
	 * @param billId
	 * @return
	 */
	public RepaymentBill findRepaymentBillByBillId(Integer billId);
	
	/**
	 * 保存偿还清算信息
	 * @param bill
	 * @param repaymentBill
	 */
	public void saveRepaymentSettleBill(Bill bill,RepaymentBill repaymentBill);
	
}
