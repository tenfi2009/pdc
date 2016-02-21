package com.matrix.ams.repayment.model;

import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SettleBillDTO {

	private Integer id;
	private String orderId;
	private Integer installmentNumber;
	private Double capital;
	private Double serviceFee;
	private Double penaltyInterest;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Timestamp shouldPayTime;
	private Double payables; // 当期应付
	private Double payAmount;
	private Double billAmount;// 账单应付金额
	private Double coupon;
	private Integer status; // 还款状态
	
	//清算差额
	private Double settleDiffAmount;

	public SettleBillDTO() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getInstallmentNumber() {
		return installmentNumber;
	}

	public void setInstallmentNumber(Integer installmentNumber) {
		this.installmentNumber = installmentNumber;
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

	public Double getPenaltyInterest() {
		return penaltyInterest;
	}

	public void setPenaltyInterest(Double penaltyInterest) {
		this.penaltyInterest = penaltyInterest;
	}

	public Timestamp getShouldPayTime() {
		return shouldPayTime;
	}

	public void setShouldPayTime(Timestamp shouldPayTime) {
		this.shouldPayTime = shouldPayTime;
	}

	public Double getPayables() {
		return payables;
	}

	public void setPayables(Double payables) {
		this.payables = payables;
	}

	public Double getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(Double payAmount) {
		this.payAmount = payAmount;
	}

	public Double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(Double billAmount) {
		this.billAmount = billAmount;
	}

	public Double getCoupon() {
		return coupon;
	}

	public void setCoupon(Double coupon) {
		this.coupon = coupon;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Double getSettleDiffAmount() {
		return settleDiffAmount;
	}

	public void setSettleDiffAmount(Double settleDiffAmount) {
		this.settleDiffAmount = settleDiffAmount;
	}

	
}
