package com.matrix.common.repayment;

import java.util.List;

/**
 * 还款方案计算
 * 
 * @author rongyang
 *
 */
public interface RepaymentModeCalculate {
	/**
	 * 计算还款方案
	 * @return List<RepaymentSchedule>
	 */
	public List<RepaymentSchedule> calculate();
}
