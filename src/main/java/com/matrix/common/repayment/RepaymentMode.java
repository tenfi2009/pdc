package com.matrix.common.repayment;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public abstract class RepaymentMode implements RepaymentModeCalculate {
	
	public static final String PARAM_INSTALLMENT = "installment";
	
	public static final String PARAM_AMOUNT = "amount";
	
	public static final String PARAM_RATE = "rate";
	
	/**
	 * 默认精度
	 */
	protected static final int DEFAULT_SCALE = 2;
	/**
	 * 还款期数
	 */
	protected int installment;

	/**
	 * 还款金额
	 */
	protected BigDecimal amount;

	/**
	 * 贷款利率
	 */
	protected BigDecimal rate;
	
	//还款方式参数
	protected Map<String, Object> params;


	public RepaymentMode() {

	}

	public RepaymentMode(Map<String, Object> params) {
		super();
		this.params = params;
		initParams(params);
	}

	protected void initParams(Map<String, Object> params) {
		this.installment = (Integer)params.get(PARAM_INSTALLMENT);
		this.amount = (BigDecimal)params.get(PARAM_AMOUNT);
		this.rate = (BigDecimal)params.get(PARAM_RATE);
	}

	public int getInstallment() {
		return installment;
	}

	

	public BigDecimal getAmount() {
		return amount;
	}

	

	public BigDecimal getRate() {
		return rate;
	}


	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
		initParams(params);
	}
}
