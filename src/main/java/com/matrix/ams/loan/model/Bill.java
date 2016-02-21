package com.matrix.ams.loan.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@javax.persistence.Entity
@Table(name = "bill")
public class Bill extends com.matrix.core.model.Entity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 36)
	private Long id;

	@Column(name = "bill_id", length = 32)
	private String billId;

	/**
	 * 订单类型,分首付和分期
	 */
	@Column(name = "type", length = 32)
	private String type;

	@Column(name = "user_id", length = 32)
	private Integer userId;

	/**
	 * 期号,0表示是首付
	 */
	@Column(name = "installment_number", length = 32)
	private Integer installmentNumber;

	/**
	 * 订单id
	 */
	@Column(name = "order_id", length = 32)
	private String orderId;

	/**
	 * 支付状态,10未到还款时间,20未付款,100已付款
	 */
	@Column(name = "pay_status")
	private Integer payStatus;

	/**
	 * 运营后台操作的状态。0表示正常账单，1表示已经取消的账单
	 */
	@Column(name = "work_flow_status")
	private Integer workFlowStatus;

	/**
	 * 本金
	 */
	@Column(name = "capital")
	private Double capital;

	/**
	 * 剩余本金
	 */
	@Column(name = "remaining_capital")
	private Double remainingCapital;
	
	/**
	 * 利息
	 */
	@Column(name = "interest")
	private Double interest;
	
	/**
	 * 服务费
	 */
	@Column(name = "service_fee")
	private Double serviceFee;

	/**
	 * 滞纳金
	 */
	@Column(name = "late_fee")
	private Double lateFee;
	
	/**
	 * 罚息
	 */
	@Column(name = "penalty_interest")
	private Double penaltyInterest;
	
	/**
	 * 违约金
	 */
	@Column(name = "penalty")
	private Double penalty;
	
	/**
	 * 额外费用
	 */
	@Column(name = "extra_fee")
	private Double extraFee;
	

	/**
	 * 活动等获得的优惠
	 */
	@Column(name = "coupon")
	private Double coupon;

	/**
	 * 实际支付金额
	 */
	@Column(name = "pay_amount")
	private Double payAmount;
	
	/**
	 * 清算差额
	 */
	@Column(name = "settle_diff_amount")
	private Double settleDiffAmount;
	
	/**
	 * 营业处收入
	 */
	@Column(name = "extra_income")
	private Double extraIncome;

	/**
	 * 最迟还款时间
	 */
	@Column(name = "should_pay_time")
	private Date shouldPayTime;

	@Column(name = "pay_time")
	private Date payTime;

	@Column(name = "create_time")
	private Date createTime;

	@Column(name = "last_update_time")
	private Date lastUpdateTime;

	/**
	 * 支付第三方
	 */
	@Column(name = "third_pay_type", length = 64)
	private Date thirdPayType;

	/**
	 * 支付方订单号
	 */
	@Column(name = "third_pay_trade_no", length = 128)
	private Date thirdPayTradeNo;

	public Bill() {

	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getInstallmentNumber() {
		return installmentNumber;
	}

	public void setInstallmentNumber(Integer installmentNumber) {
		this.installmentNumber = installmentNumber;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Integer getWorkFlowStatus() {
		return workFlowStatus;
	}

	public void setWorkFlowStatus(Integer workFlowStatus) {
		this.workFlowStatus = workFlowStatus;
	}

	

	public Double getCapital() {
		return capital;
	}

	public void setCapital(Double capital) {
		this.capital = capital;
	}

	public Double getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(Double serviceFee) {
		this.serviceFee = serviceFee;
	}

	public Double getLateFee() {
		return lateFee;
	}

	public void setLateFee(Double lateFee) {
		this.lateFee = lateFee;
	}

	public Double getCoupon() {
		return coupon;
	}

	public void setCoupon(Double coupon) {
		this.coupon = coupon;
	}

	public Double getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(Double payAmount) {
		this.payAmount = payAmount;
	}

	public Date getShouldPayTime() {
		return shouldPayTime;
	}

	public void setShouldPayTime(Date shouldPayTime) {
		this.shouldPayTime = shouldPayTime;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Date getThirdPayType() {
		return thirdPayType;
	}

	public void setThirdPayType(Date thirdPayType) {
		this.thirdPayType = thirdPayType;
	}

	public Date getThirdPayTradeNo() {
		return thirdPayTradeNo;
	}

	public void setThirdPayTradeNo(Date thirdPayTradeNo) {
		this.thirdPayTradeNo = thirdPayTradeNo;
	}

	public Double getRemainingCapital() {
		return remainingCapital;
	}

	public void setRemainingCapital(Double remainingCapital) {
		this.remainingCapital = remainingCapital;
	}

	public Double getInterest() {
		return interest;
	}

	public void setInterest(Double interest) {
		this.interest = interest;
	}

	public Double getPenaltyInterest() {
		return penaltyInterest;
	}

	public void setPenaltyInterest(Double penaltyInterest) {
		this.penaltyInterest = penaltyInterest;
	}

	public Double getPenalty() {
		return penalty;
	}

	public void setPenalty(Double penalty) {
		this.penalty = penalty;
	}

	public Double getExtraFee() {
		return extraFee;
	}

	public void setExtraFee(Double extraFee) {
		this.extraFee = extraFee;
	}

	public Double getSettleDiffAmount() {
		return settleDiffAmount;
	}

	public void setSettleDiffAmount(Double settleDiffAmount) {
		this.settleDiffAmount = settleDiffAmount;
	}

	public Double getExtraIncome() {
		return extraIncome;
	}

	public void setExtraIncome(Double extraIncome) {
		this.extraIncome = extraIncome;
	}
	
	
}
