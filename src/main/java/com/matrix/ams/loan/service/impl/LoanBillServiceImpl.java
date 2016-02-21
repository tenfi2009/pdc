package com.matrix.ams.loan.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matrix.ams.common.Status;
import com.matrix.ams.loan.dao.LoanBillDao;
import com.matrix.ams.loan.model.LoanBill;
import com.matrix.ams.loan.model.LoanRepaymentOperationLog;
import com.matrix.ams.loan.service.BillService;
import com.matrix.ams.loan.service.LoanBillService;
import com.matrix.ams.loan.service.LoanRepaymentOperationLogService;
import com.matrix.ams.paycenter.model.PayResult;
import com.matrix.ams.paycenter.model.QueryPayResult;
import com.matrix.ams.paycenter.service.PayMoneyFacadeService;
import com.matrix.core.exception.BizException;
import com.matrix.core.service.impl.BaseServiceImpl;

@Service
public class LoanBillServiceImpl extends BaseServiceImpl<LoanBill,String> implements LoanBillService{

	@Autowired
	private LoanBillDao dao;
	
	@Autowired
	private BillService billService;
	
	@Autowired
	private LoanRepaymentOperationLogService logService;
	
	@Autowired
	private PayMoneyFacadeService payMoneyFacadeService;
	

	public LoanBillDao getDao() {
		return dao;
	}

	public void setDao(LoanBillDao dao) {
		this.dao = dao;
	}

	
	public void setBillService(BillService billService) {
		this.billService = billService;
	}
	
	

	public void setPayMoneyFacadeService(PayMoneyFacadeService payMoneyFacadeService) {
		this.payMoneyFacadeService = payMoneyFacadeService;
	}

	/**
	 * 生成付款单号
	 * @return
	 */
	@Override
	public String generateBillNumber() {
		int count = dao.count(null) + 1;
		String date = new SimpleDateFormat("yyyyMMddHH").format(new Date());
		return date + StringUtils.leftPad("" + count, 6, "0");
	}
	
	public void log(String content, String objectId) {
		LoanRepaymentOperationLog log = new LoanRepaymentOperationLog();
		log.setObjectType(1);//放款
		log.setOperationTime(new Date());
		log.setOperator(getCurrentUser().getName());
		log.setContent(content);
		log.setObjectId(objectId);
		logService.save(log);
	}

	@Override
	public void submit(String id) {
		log("提交",id);
		
		LoanBill loanBill = this.get(id);
		
		String orderId = billService.saveOrder(loanBill);
		loanBill.setOrderId(orderId);
		
		//生成还款计划
		billService.saveRepaymentPlan(loanBill);
		
		if(loanBill.getPayWay() == 2){//线下放款
			loanBill.setStatus(Status.SUCCESSLOAN.getSequence());//放款成功
		}else if(loanBill.getPayWay() == 1){ //线上放款
			//调用支付接口
			PayResult rs = payMoneyFacadeService.submitPayMoney(loanBill);
			if(null != rs.getOtherInfo().get("dealCharge")){
				loanBill.setDealCharge(BigDecimal.valueOf((Double)rs.getOtherInfo().get("dealCharge")));
				loanBill.setTradeNumber((String)rs.getOtherInfo().get("dealId"));
			}
			
			loanBill.setStatus(Status.LOANING.getSequence());//放款中
		}else{
			throw new BizException("提交失败！放款方式不能为空。");
		}
		
		loanBill.setDataStatus(3);// 已打款已提交
		
		save(loanBill);
	}
	
	

	@Override
	public void saveLoanBill(LoanBill bill) {
		if (StringUtils.isEmpty(bill.getId())) {
			dao.save(bill);//为了日志记录ID
			log("保存",bill.getId());
		} else {
			log("修改",bill.getId());
		}
		if (bill.getDataStatus() == null) {//新建是更新，修改时不需要更新数据状态
			if (bill.getPayWay() == 1) {//线上
				bill.setDataStatus(1);//未打款待提交
			} else {
				bill.setDataStatus(2);//已打款待提交
			}
		}
		dao.save(bill);
	}
	
	/**
	 * 保存线上放款失败的放款单，放款状态并更新为线下放款
	 * @param bill
	 */
	public void saveFailLoanBill(LoanBill bill){
		if (!StringUtils.isEmpty(bill.getId())) {
			log("修改",bill.getId());
		} else {
			dao.save(bill);//为了日志记录ID
			log("保存",bill.getId());
		}
		bill.setDataStatus(2);//已打款待提交
		if(bill.getPayWay() == 2){//线下放款
			bill.setStatus(Status.SUCCESSLOAN.getSequence());//放款成功
		}
		dao.save(bill);
	}

	@Override
	public void deleteLoanBill(String id) {
		log("删除",id);
		removeById(id);
	}
	
	/**
	 * 查询所有放款中的数据
	 * @return
	 */
	public List<String> getLoaningBillIds(){
		return dao.getLoaningBillIds();
	}
	
	/**
	 * 更新放款单支付状态
	 * @param id
	 * @param rs
	 */
	public void updateLoanStatus(String id,QueryPayResult rs){
		dao.updateLoanStatus(id, rs);
	}
}
