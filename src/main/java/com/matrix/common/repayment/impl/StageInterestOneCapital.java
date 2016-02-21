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
 * 分期还息到期还本
 * @author rongyang
 *
 */
public class StageInterestOneCapital extends RepaymentMode {

	public StageInterestOneCapital() {
		
	}

	/**
	 * @param installment
	 * @param amount
	 * @param rate
	 */
	public StageInterestOneCapital(Map<String, Object> params) {
		super(params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rong.repayment.RepaymentModeCalculate#calculate()
	 */
	public List<RepaymentSchedule> calculate() {
		
		BigDecimal interest = amount.multiply(rate).setScale(DEFAULT_SCALE, RoundingMode.HALF_UP);
		
		RepaymentSchedule current = null;// 当期

		RepaymentSchedule previous = null;// 上期

		List<RepaymentSchedule> schedules = new ArrayList<RepaymentSchedule>(installment);

		for (int i = 0; i < installment; i++) {
			current = new RepaymentSchedule();

			current.setInstallmentNo(i + 1);
			current.setInterest(interest);

			if (0 == i) {
				// 首期
				current.setCapital(BigDecimal.ZERO);
				current.setRemainingCapital(amount);
				current.setSumCapitalInterest(interest);
				
				current.setTotalCapital(BigDecimal.ZERO);
				current.setTotalInterest(interest);
			} else if (installment - 1 == i) {
				// 末期
				previous = schedules.get(i - 1);

				current.setCapital(amount);
				current.setRemainingCapital(BigDecimal.ZERO);
				current.setSumCapitalInterest(amount.add(interest));
				
				current.setTotalCapital(amount);
				current.setTotalInterest(previous.getTotalInterest().add(interest));
				
			} else {
				// 上期的剩余应收本金
				previous = schedules.get(i - 1);

				current.setCapital(BigDecimal.ZERO);
				current.setRemainingCapital(amount);
				current.setSumCapitalInterest(interest);
				
				current.setTotalCapital(BigDecimal.ZERO);
				current.setTotalInterest(previous.getTotalInterest().add(interest));
			}

			schedules.add(i, current);
		}
		return schedules;
	}
}
