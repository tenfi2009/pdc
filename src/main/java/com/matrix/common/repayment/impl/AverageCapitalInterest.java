package com.matrix.common.repayment.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.matrix.common.repayment.RepaymentMode;
import com.matrix.common.repayment.RepaymentSchedule;

public class AverageCapitalInterest extends RepaymentMode {
	/**
	 * 等本等息还款方式
	 * 
	 * @author rongyang
	 *
	 */
	public AverageCapitalInterest() {

	}

	public AverageCapitalInterest(Map<String, Object> params) {

		super(params);

	}

	@Override
	public List<RepaymentSchedule> calculate() {
		/**
		 * 等本等息计算公式
		 * 每期本金 = 金额/期数
		 * 每期利息= 金额*利率
		 */

		BigDecimal capital = amount.divide(BigDecimal.valueOf(installment), 2,  RoundingMode.HALF_UP);
		BigDecimal interest = amount.multiply(rate).setScale(2, RoundingMode.HALF_UP);
		
		RepaymentSchedule current = null;// 当期

		RepaymentSchedule previous = null;// 上期

		List<RepaymentSchedule> schedules = new ArrayList<RepaymentSchedule>(installment);

		for (int i = 0; i < installment; i++) {
			current = new RepaymentSchedule();

			current.setInstallmentNo(i + 1);
			current.setCapital(capital);
			current.setInterest(interest);

			if (0 == i) {
				// 首期
				current.setRemainingCapital(amount.subtract(current.getCapital()));
				current.setSumCapitalInterest(current.getCapital().add(current.getInterest()));
				current.setTotalCapital(current.getCapital());
				current.setTotalInterest(current.getInterest());
			} else if (installment - 1 == i) {
				// 末期
				previous = schedules.get(i - 1);

				current.setCapital(previous.getRemainingCapital());
				
				current.setRemainingCapital(BigDecimal.ZERO);

				current.setTotalCapital(amount);
				current.setTotalInterest(previous.getTotalInterest().add(current.getInterest()));

				// 修正本息合计
				current.setSumCapitalInterest(current.getCapital().add(current.getInterest()));
			} else {
				// 上期的剩余应收本金
				previous = schedules.get(i - 1);
				
				current.setRemainingCapital(previous.getRemainingCapital().subtract(current.getCapital()));
				current.setSumCapitalInterest(current.getCapital().add(current.getInterest()));
				current.setTotalCapital(previous.getTotalCapital().add(current.getCapital()));
				current.setTotalInterest(previous.getTotalInterest().add(current.getInterest()));
			}

			schedules.add(i, current);
		}
		return schedules;
	}
}
