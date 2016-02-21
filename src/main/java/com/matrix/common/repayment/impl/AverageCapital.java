/**
 * 
 */
package com.matrix.common.repayment.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.matrix.common.repayment.RepaymentMode;
import com.matrix.common.repayment.RepaymentSchedule;

/**
 * 等额本金
 * @author rongyang
 *
 */
public class AverageCapital extends RepaymentMode {

	public AverageCapital() {
		
	}

	/**
	 * @param installment
	 * @param amount
	 * @param rate
	 */
	public AverageCapital(Map<String, Object> params) {
		super(params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rong.repayment.RepaymentModeCalculate#calculate()
	 */
	public List<RepaymentSchedule> calculate() {
		//计算每月应收本金
		BigDecimal principal = amount.divide(BigDecimal.valueOf(installment), DEFAULT_SCALE,RoundingMode.HALF_UP);
		
		//每月月供递减额=每月应还本金×月利率=贷款本金÷还款月数×月利率
		BigDecimal dtAmount = amount.divide(BigDecimal.valueOf(installment), DEFAULT_SCALE+2,RoundingMode.HALF_UP).multiply(rate).setScale(DEFAULT_SCALE,RoundingMode.HALF_UP);


		RepaymentSchedule current = null;// 当期

		RepaymentSchedule previous = null;// 上期

		List<RepaymentSchedule> schedules = new ArrayList<RepaymentSchedule>(installment);

		for (int i = 0; i < installment; i++) {
			current = new RepaymentSchedule();

			current.setInstallmentNo(i + 1);
			current.setCapital(principal);

			if (0 == i) {
				// 首期
				current.setInterest(amount.multiply(rate).setScale(DEFAULT_SCALE, RoundingMode.HALF_UP));
				
				current.setSumCapitalInterest(principal.add(current.getInterest()));

				current.setRemainingCapital(amount.subtract(current.getCapital()));
				
				current.setTotalCapital(current.getCapital());
				current.setTotalInterest(current.getInterest());
			} else if (installment - 1 == i) {
				// 末期
				previous = schedules.get(i - 1);

				//暂不考虑用总利息修正  总利息=〔(总贷款额÷还款月数+总贷款额×月利率)+总贷款额÷还款月数×(1+月利率)〕÷2×还款月数-总贷款额 
				current.setInterest(previous.getInterest().subtract(dtAmount));
				
				//修正本金
				current.setCapital(previous.getRemainingCapital());
				
				current.setSumCapitalInterest(current.getCapital().add(current.getInterest()));

				current.setRemainingCapital(BigDecimal.ZERO);

				current.setTotalCapital(amount);
				current.setTotalInterest(previous.getTotalInterest().add(current.getInterest()));
			} else {
				// 上期的剩余应收本金
				previous = schedules.get(i - 1);

				current.setInterest(previous.getInterest().subtract(dtAmount));
				
				current.setSumCapitalInterest(principal.add(current.getInterest()));


				current.setRemainingCapital(previous.getRemainingCapital().subtract(current.getCapital()));

				current.setTotalCapital(previous.getTotalCapital().add(current.getCapital()));
				current.setTotalInterest(previous.getTotalInterest().add(current.getInterest()));
			}

			schedules.add(i, current);
		}
		return schedules;
	}
}
