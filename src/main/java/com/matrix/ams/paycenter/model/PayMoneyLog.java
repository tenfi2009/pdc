package com.matrix.ams.paycenter.model;

import java.util.Date;

/**
 * 支付log
 * @author hu.tang
 *
 */
public class PayMoneyLog {
	private int id;
	private String commonOrderId;
	private int commonUid;
	private String userName;
	private String bankName;
	private String bankCity;
	private String bankCard;
	private String openBank;
	private String tradeOrderId;
	private String payLog;
	private int auditorId;
	private String auditorName;
	private int bizType;
	private String resultCode;
	private String resultReason;
	private String dealId;
	private Date createTime;
	private double debitCharge;
	private double creditCharge;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCommonOrderId() {
		return commonOrderId;
	}
	public void setCommonOrderId(String commonOrderId) {
		this.commonOrderId = commonOrderId;
	}
	public int getCommonUid() {
		return commonUid;
	}
	public void setCommonUid(int commonUid) {
		this.commonUid = commonUid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankCity() {
		return bankCity;
	}
	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}
	public String getBankCard() {
		return bankCard;
	}
	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}
	public String getOpenBank() {
		return openBank;
	}
	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}
	public String getTradeOrderId() {
		return tradeOrderId;
	}
	public void setTradeOrderId(String tradeOrderId) {
		this.tradeOrderId = tradeOrderId;
	}
	public String getPayLog() {
		return payLog;
	}
	public void setPayLog(String payLog) {
		this.payLog = payLog;
	}
	public int getAuditorId() {
		return auditorId;
	}
	public void setAuditorId(int auditorId) {
		this.auditorId = auditorId;
	}
	public String getAuditorName() {
		return auditorName;
	}
	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}
	
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultReason() {
		return resultReason;
	}
	public void setResultReason(String resultReason) {
		this.resultReason = resultReason;
	}
	public String getDealId() {
		return dealId;
	}
	public void setDealId(String dealId) {
		this.dealId = dealId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getBizType() {
		return bizType;
	}
	public void setBizType(int bizType) {
		this.bizType = bizType;
	}
	public double getDebitCharge() {
		return debitCharge;
	}
	public void setDebitCharge(double debitCharge) {
		this.debitCharge = debitCharge;
	}
	public double getCreditCharge() {
		return creditCharge;
	}
	public void setCreditCharge(double creditCharge) {
		this.creditCharge = creditCharge;
	}
}
