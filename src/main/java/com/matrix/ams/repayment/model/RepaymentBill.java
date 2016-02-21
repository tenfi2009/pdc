package com.matrix.ams.repayment.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.matrix.core.model.Entity;

@SuppressWarnings("serial")
@javax.persistence.Entity
@Table(name = "t_repayment_bill")
public class RepaymentBill extends Entity<String> {
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator = "idGenerator")
	@Column(length = 36)
	private String id;

	/** 订单ID */
	@Column(length = 36, name = "order_id")
	private String orderId;

	/** 账单ID */
	@Column(length = 36, name = "account_bill_id")
	private String accountBillId;

	/** 还款账号 */
	@Column(length = 64, name = "repayment_account")
	private String repaymentAccount;

	/** 还款金额 */
	@Column(name = "amount", precision = 13, scale = 2)
	private BigDecimal amount;

	/** 还款时间 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "receipt_time")
	private Date receiptTime;

	/** 还款到账时间 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "in_account_time")
	private Date inAccountTime;

	/** 还款_支付渠道 */
	@Column(length = 64, name = "third_pay_type")
	private String thirdPayType;

	/** 还款_支付方订单号 */
	@Column(length = 128, name = "third_pay_trade_no")
	private String thirdPayTradeNo;

	/** 还款人 */
	@Column(length = 32, name = "person")
	private String person;

	/** 收款账号 */
	@Column(length = 64, name = "receive_account")
	private String receiveAccount;

	/** 备注 */
	@Column(length = 128, name = "remark")
	private String remark;

	/** 放款状态 */
	@Column
	private Integer status;

	/** 放款人 */
	@Column(length = 32)
	private String operator;

	/** 操作时间 */
	@Column(name = "biz_date")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date bizDate;

	public RepaymentBill() {

	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getAccountBillId() {
		return accountBillId;
	}

	public void setAccountBillId(String accountBillId) {
		this.accountBillId = accountBillId;
	}

	public String getRepaymentAccount() {
		return repaymentAccount;
	}

	public void setRepaymentAccount(String repaymentAccount) {
		this.repaymentAccount = repaymentAccount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getReceiptTime() {
		return receiptTime;
	}

	public void setReceiptTime(Date receiptTime) {
		this.receiptTime = receiptTime;
	}

	public Date getInAccountTime() {
		return inAccountTime;
	}

	public void setInAccountTime(Date inAccountTime) {
		this.inAccountTime = inAccountTime;
	}

	public String getThirdPayType() {
		return thirdPayType;
	}

	public void setThirdPayType(String thirdPayType) {
		this.thirdPayType = thirdPayType;
	}

	public String getThirdPayTradeNo() {
		return thirdPayTradeNo;
	}

	public void setThirdPayTradeNo(String thirdPayTradeNo) {
		this.thirdPayTradeNo = thirdPayTradeNo;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getReceiveAccount() {
		return receiveAccount;
	}

	public void setReceiveAccount(String receiveAccount) {
		this.receiveAccount = receiveAccount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getBizDate() {
		return bizDate;
	}

	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}
}
