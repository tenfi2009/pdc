package com.matrix.ams.loan.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.matrix.ams.common.BillIdUtil;
import com.matrix.ams.common.OrderIdGenerateUtil;
import com.matrix.ams.loan.dao.BillDao;
import com.matrix.ams.loan.model.Bill;
import com.matrix.ams.loan.model.CommonOrderInfo;
import com.matrix.ams.loan.model.GoodsOrder;
import com.matrix.ams.loan.model.LoanBill;
import com.matrix.ams.loan.service.BillService;
import com.matrix.common.repayment.RepaymentFactory;
import com.matrix.common.repayment.RepaymentMode;
import com.matrix.common.repayment.RepaymentSchedule;
import com.matrix.core.exception.BizException;
import com.matrix.core.service.impl.BaseServiceImpl;

@Service
public class BillServiceImpl extends BaseServiceImpl<Bill,Long> implements BillService{
	private static Logger logger = LoggerFactory.getLogger(BillServiceImpl.class);

	@Autowired
	private BillDao dao;

	public BillDao getDao() {
		return dao;
	}

	public void setDao(BillDao dao) {
		this.dao = dao;
	}
	
	/**
	 * 生成订单，并返回订单号
	 * @param loanBill
	 * @return
	 */
	public String saveOrder(LoanBill loanBill){
		
		if(StringUtils.isNotEmpty(loanBill.getOrderId())){
			throw new BizException("访放款单已提交过，并生成了订单，不能再次提交！");
		}
		
		String mobilePhone = loanBill.getMobilePhone();
		if(StringUtils.isEmpty(mobilePhone)){
			logger.info("用户手机号为空！不能创建订单！");
			throw new BizException("用户手机号为空！不能创建订单！");
		}
		
		Integer userId = dao.getUserId(mobilePhone);
		if(null == userId){
			dao.insertUser(mobilePhone);
			logger.info("新增用户："+ mobilePhone);
			userId = dao.getUserId(mobilePhone);
			
			//同时插入用户信息表
			dao.insertUserInfo(userId, mobilePhone);
		}
		
		CommonOrderInfo order = convertToOrder(loanBill);
		order.setCommonUid(userId);
		order.setCommonOrderId(OrderIdGenerateUtil.genOrderId(userId));
		
		dao.insertOrder(order);
		
		return order.getCommonOrderId();
	}
	
	/**
	 * 生成(账单)还款计划
	 * @param loanBill
	 */
	public void saveRepaymentPlan(LoanBill loanBill){
		String orderId = loanBill.getOrderId();
		Integer billCount = dao.findBillByOrderId(orderId);
		if(billCount > 0){
			throw new BizException("还款计划已生成，不能重新生成！");
		}
		
		RepaymentMode repaymentMode = RepaymentFactory.getRepaymentMode(loanBill.getRepayment());
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(RepaymentMode.PARAM_INSTALLMENT,loanBill.getInstallment());
		params.put(RepaymentMode.PARAM_AMOUNT,loanBill.getAmount());
		params.put(RepaymentMode.PARAM_RATE,new BigDecimal(loanBill.getRate()));
		
		repaymentMode.setParams(params);
		
		List<RepaymentSchedule> schedules = repaymentMode.calculate();
		
		
		
		String format = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		
		DateTime dateTime = new DateTime(loanBill.getValueDate());
		RepaymentSchedule period = null;
		//计算账单日,费用,利息
		for(int i = 0; i < schedules.size(); i++){
			period = schedules.get(i);
			period.setBillingDate(dateTime.plusMonths(i+1).toDate());
			
			//费用，利息拆分
			period.setTotalFee(period.getInterest().multiply(loanBill.getFeeRatio()).setScale(2, RoundingMode.HALF_UP));
			period.setInterest(period.getInterest().subtract(period.getTotalFee()));
			
			//调整  本息合计，累计应还利息
			period.setSumCapitalInterest(period.getSumCapitalInterest().subtract(period.getTotalFee()));
			period.setTotalInterest(period.getTotalInterest().subtract(period.getTotalFee()));
		}
		
	    logger.info((Arrays.toString(schedules.toArray()))); 
	    
	    //还款计划生成账单
	    Integer userId = dao.getUserId(loanBill.getMobilePhone());
	    
	    List<Bill> bills = new ArrayList<Bill>(schedules.size());
	    for(RepaymentSchedule schedule : schedules){
	    	bills.add(convertToBill(loanBill,userId,schedule));
	    }
	    
	    //保存所有的账单
	    save(bills);
		
	}
	
	/**
	 * 根据订单号查找账单
	 * @param orderId
	 * @return
	 */
	public List<Bill> findBillByOrderId(String orderId){
		Search search = new Search(Bill.class);
		
		StringBuffer filters = new StringBuffer(" {orderId} = '").append(orderId).append("'");
		search.addFilterCustom(filters.toString());
		search.addSortAsc("installmentNumber");
		
		return dao.search(search);
	}
	
	private Bill convertToBill(LoanBill loanBill,Integer userId, RepaymentSchedule schedule){
		Bill bill = new Bill();
		
		bill.setOrderId(loanBill.getOrderId());
		bill.setBillId(BillIdUtil.generateBillId(loanBill.getOrderId(), schedule.getInstallmentNo()));
		bill.setCapital(schedule.getCapital().doubleValue());
		bill.setCoupon(new Double(0));
		bill.setCreateTime(new Date());
		bill.setInstallmentNumber(schedule.getInstallmentNo());
		bill.setInterest(schedule.getInterest().doubleValue());
		bill.setLastUpdateTime(new Date());
		bill.setLateFee(new Double(0));
		bill.setPayAmount(new Double(0));
		bill.setPayStatus(10);//未到还款时间
		bill.setPenalty(new Double(0));
		bill.setPenaltyInterest(new Double(0));
		bill.setRemainingCapital(schedule.getRemainingCapital().doubleValue());
		bill.setServiceFee(schedule.getTotalFee().doubleValue());
		bill.setShouldPayTime(schedule.getBillingDate());
		bill.setType("installment");
		bill.setUserId(userId);
		bill.setWorkFlowStatus(0); //'运营后台操作的状态。0表示正常账单，1表示已经取消的账单'
		bill.setExtraFee(new Double(0));
		
		return bill;
	}
	
	private CommonOrderInfo convertToOrder(LoanBill loanBill){
		CommonOrderInfo order = new CommonOrderInfo();
		order.setAttrInfo(loanBill.getRemark());
		order.setCapital(0);
		order.setContractCode(loanBill.getContractNumber());
		order.setInterestRate(Double.valueOf(loanBill.getRate()));
		order.setCouponRate(order.getInterestRate());
		order.setInterestRate(order.getInterestRate());
		order.setName(null == loanBill.getProductType() ? "" : loanBill.getProductType().toString());
		order.setPrice(loanBill.getAmount().doubleValue());
		order.setRepaymentType(loanBill.getRepayment());
		order.setStages(loanBill.getInstallment());
		order.setType(GoodsOrder.TYPE_MONEY_ORDER);
		order.setStatus(GoodsOrder.STATUS_PAYING);
		return order;
	}
}
