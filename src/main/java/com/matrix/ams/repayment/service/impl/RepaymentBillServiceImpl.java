package com.matrix.ams.repayment.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.matrix.ams.loan.dao.BillDao;
import com.matrix.ams.loan.model.Bill;
import com.matrix.ams.loan.model.LoanRepaymentOperationLog;
import com.matrix.ams.loan.service.LoanRepaymentOperationLogService;
import com.matrix.ams.repayment.dao.RepaymentBillDao;
import com.matrix.ams.repayment.model.RepaymentBill;
import com.matrix.ams.repayment.model.SettleBillDTO;
import com.matrix.ams.repayment.service.RepaymentBillService;
import com.matrix.core.service.impl.BaseServiceImpl;
import com.matrix.core.util.Page;

@Service
public class RepaymentBillServiceImpl extends BaseServiceImpl<RepaymentBill, String> implements RepaymentBillService {

	@Autowired
	private RepaymentBillDao dao;
	
	@Autowired
	private BillDao billDao;
	
	@Autowired
	private LoanRepaymentOperationLogService logService;

	@Override
	public RepaymentBillDao getDao() {
		return dao;
	}

	/**
	 * 查询结算清单
	 * 
	 * @param page
	 * @param queryParams
	 * @return
	 */
	public Page<SettleBillDTO> findSettleBillPage(Page<SettleBillDTO> page, Map queryParams) {
		return dao.findSettleBillPage(page, queryParams);
	}
	
	/**
	 * 
	 * @param billId
	 * @return
	 */
	public SettleBillDTO findSettleBillDTOByBillId(Integer billId){
		return dao.findSettleBillDTOByBillId(billId);
	}
	
	/**
	 * 查询偿还订单
	 * @param billId
	 * @return
	 */
	public RepaymentBill findRepaymentBillByBillId(Integer billId){
		Search search = new Search(RepaymentBill.class);
		
		StringBuffer filters = new StringBuffer(" {accountBillId} = '").append(billId).append("'");
		search.addFilterCustom(filters.toString());
		List<RepaymentBill> repaymentBills = dao.search(search);
		if (!repaymentBills.isEmpty()) {
			return repaymentBills.get(0);
		}
		return null;
	}
	
	/**
	 * 保存偿还清算信息
	 * @param bill
	 * @param repaymentBill
	 */
	public void saveRepaymentSettleBill(Bill bill,RepaymentBill repaymentBill){
		Bill oldBill = billDao.find(bill.getId());
		oldBill.setCoupon(bill.getCoupon());
		oldBill.setExtraIncome(bill.getExtraIncome());
		oldBill.setSettleDiffAmount(bill.getSettleDiffAmount());
		billDao.save(oldBill);
		
		if (StringUtils.isNotEmpty(repaymentBill.getId())) {
			RepaymentBill oldRepaymentBill = dao.find(repaymentBill.getId());
			oldRepaymentBill.setReceiveAccount(repaymentBill.getReceiveAccount());
			oldRepaymentBill.setAmount(repaymentBill.getAmount());
			oldRepaymentBill.setInAccountTime(repaymentBill.getInAccountTime());
			oldRepaymentBill.setReceiptTime(repaymentBill.getReceiptTime());
			oldRepaymentBill.setRepaymentAccount(repaymentBill.getRepaymentAccount());
			dao.save(oldRepaymentBill);
		} else {
			repaymentBill.setOrderId(bill.getOrderId());
			repaymentBill.setAccountBillId(bill.getId().toString());
			repaymentBill.setOperator(getCurrentUser().getName());
			repaymentBill.setBizDate(new Date());
			dao.save(repaymentBill);
		}
		
		log("账单清算",String.valueOf(bill.getId()));
	}
	
	public void log(String content, String objectId) {
		LoanRepaymentOperationLog log = new LoanRepaymentOperationLog();
		log.setObjectType(2);//收款
		log.setOperationTime(new Date());
		log.setOperator(getCurrentUser().getName());
		log.setContent(content);
		log.setObjectId(objectId);
		logService.save(log);
	}
}
