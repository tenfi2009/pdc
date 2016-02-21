package com.matrix.ams.repayment.dao;

import java.util.Map;

import com.matrix.ams.repayment.model.RepaymentBill;
import com.matrix.ams.repayment.model.SettleBillDTO;
import com.matrix.core.dao.BaseDao;
import com.matrix.core.util.Page;


public interface RepaymentBillDao extends BaseDao<RepaymentBill,String>{
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
}
