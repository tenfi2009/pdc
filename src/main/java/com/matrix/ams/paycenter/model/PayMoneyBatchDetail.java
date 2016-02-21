package com.matrix.ams.paycenter.model;

import java.util.Date;

/**
 * 
 * 批量打款详情
 * @author hu.tang
 *
 */
public class PayMoneyBatchDetail {
	private int id;
	private String commonOrderId;
	private String orderId;
	private int userId;
	private String userName;
	private double amount;
	private int auditorId;
	private String auditorName;
	private int bizType;
	private Date createTime;
	private Date batchDate;
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
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
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
	public int getBizType() {
		return bizType;
	}
	public void setBizType(int bizType) {
		this.bizType = bizType;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getBatchDate() {
		return batchDate;
	}
	public void setBatchDate(Date batchDate) {
		this.batchDate = batchDate;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
