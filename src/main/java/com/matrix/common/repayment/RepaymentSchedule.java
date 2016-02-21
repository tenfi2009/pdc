package com.matrix.common.repayment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * 还款方案
 * 
 * @author rongyang
 *
 */
public class RepaymentSchedule implements Serializable {
	private static final String format = "yyyy-MM-dd";

	/**
	 * 当前分期数
	 */
	private int installmentNo;

	/**
	 * 当期应还本金
	 */
	private BigDecimal capital;

	/**
	 * 当期应还利息
	 */
	private BigDecimal interest;

	/**
	 * 当期应还本息合计
	 */
	private BigDecimal sumCapitalInterest;

	/**
	 * 剩余应还本金
	 */
	private BigDecimal remainingCapital;

	/**
	 * 累计应还本金
	 */
	private BigDecimal totalCapital = BigDecimal.ZERO;

	/**
	 * 累计应还利息
	 */
	private BigDecimal totalInterest = BigDecimal.ZERO;
	
	/**
	 * 费用总计
	 */
	private BigDecimal totalFee;
	
	/**
	 * 账单日
	 */
	private Date billingDate;
	

	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	public RepaymentSchedule() {
		super();
	}

	/**
	 * 获取当前分期数
	 */
	public int getInstallmentNo() {
		return installmentNo;
	}

	/**
	 * 设置当前分期数
	 */
	public void setInstallmentNo(int installmentNo) {
		this.installmentNo = installmentNo;
	}

	/**
	 * 获取当期应还本金
	 */
	public BigDecimal getCapital() {
		return capital;
	}

	/**
	 * 设置当期应还本金
	 */
	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	/**
	 * 获取当期应还利息
	 */
	public BigDecimal getInterest() {
		return interest;
	}

	/**
	 * 设置当期应还利息
	 */
	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	/**
	 * 获取当期应还本息合计
	 */
	public BigDecimal getSumCapitalInterest() {
		return sumCapitalInterest;
	}

	/**
	 * 设置当期应还本息合计
	 */
	public void setSumCapitalInterest(BigDecimal sumCapitalInterest) {
		this.sumCapitalInterest = sumCapitalInterest;
	}

	/**
	 * 获取剩余应还本金
	 */
	public BigDecimal getRemainingCapital() {
		return remainingCapital;
	}

	/**
	 * 设置剩余应还本金
	 */
	public void setRemainingCapital(BigDecimal remainingCapital) {
		this.remainingCapital = remainingCapital;
	}

	/**
	 * 获取累计应还本金
	 */
	public BigDecimal getTotalCapital() {
		return totalCapital;
	}

	/**
	 * 设置累计应还本金
	 */
	public void setTotalCapital(BigDecimal totalCapital) {
		this.totalCapital = totalCapital;
	}

	/**
	 * 获取累计应还利息
	 */
	public BigDecimal getTotalInterest() {
		return totalInterest;
	}

	/**
	 * 设置累计应还利息
	 */
	public void setTotalInterest(BigDecimal totalInterest) {
		this.totalInterest = totalInterest;
	}
	
	public Date getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return "RepaymentSchedule [installmentNo=" + installmentNo
				+ ", billingDate=" + sdf.format(billingDate) 
				+ ", capital=" + capital + ", interest=" + interest
				+ ", sumCapitalInterest=" + sumCapitalInterest
				+ ", remainingCapital=" + remainingCapital
				+ ", totalCapital=" + totalCapital + ", totalInterest="
				+ totalInterest + "]";
	}
}
