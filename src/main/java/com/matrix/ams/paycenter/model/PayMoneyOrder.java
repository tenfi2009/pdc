package com.matrix.ams.paycenter.model;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

public class PayMoneyOrder {
	
	private int id;
	/**订单id**/
	private String commonOrderId;
	/**用户id**/
	private int commonUid;
	/**付款金额**/
	private double amount;
	/**打款用户名**/
	private String userName;
	/**打款银行名称**/
	private String bankName;
	/**银行卡城市**/
	private String bankCity;
	/**银行卡号**/
	private String bankCard;
	/**开户行**/
	private String openBank;
	/**打款状态**/
	private int status;
	
	
	public final static int STATUS_WAIT_PAY = 0;
	public final static int STATUS_HAVE_PAY = 1;
	public final static int STATUS_FAIL_PAY = 2;
	public final static int STATUS_RE_PAY = 3;
	public final static int STATUS_SUCC_PAY = 4;
	
	/**打款订单号**/
	private String tradeOrderId;
	
	/**打款次数**/
	private int times;
	
	public final static int TIMES_INIT = 1;
	
	/**审核用户id**/
	private int auditorId;
	
	/**审核用户名**/
	private String auditorName;
	
	/**业务类型**/
	private int bizType;
	
	/**青客业务**/
	public final static int BIZ_TYPE_QINGKE = 1;
	
	/**
	 * 是否为批量付款任务
	 */
	private int isBatch;
	
	public final static int IS_BATCH_PAY_NO = 0;
	public final static int IS_BATCH_PAY_YES = 1;
	
	
	private Date batchDate;
	
	/**创建时间**/
	private Date createTime;
	
	/**付款时间**/
	private Date payTime;
	
	/**结果码**/
	private String resultCode;
	/**结果原因**/
	private String resultReason;
	
	private Date shouldPayDate;
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
	
	
	public int getTimes() {
		return times;
	}
	
	public void setTimes(int times) {
		this.times = times;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getBizType() {
		return bizType;
	}
	public void setBizType(int bizType) {
		this.bizType = bizType;
	}
	public int getIsBatch() {
		return isBatch;
	}
	public void setIsBatch(int isBatch) {
		this.isBatch = isBatch;
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
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getBatchDate() {
		return batchDate;
	}
	public void setBatchDate(Date batchDate) {
		this.batchDate = batchDate;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	
	public Date getShouldPayDate() {
		return DateUtils.addMonths(batchDate, 1);
	}
	public void setShouldPayDate(Date shouldPayDate) {
		this.shouldPayDate = shouldPayDate;
	}
}
