package com.matrix.ams.loan.model;

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
@Table(name = "t_loan_bill")
public class LoanBill extends Entity<String>{
	
	@Id
	@GenericGenerator(name="idGenerator",strategy="org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator="idGenerator")
	@Column(length=36)
	private String id;
	
	/** 订单ID */
	@Column(length=36,name="order_id")
	private String orderId;
	
	/*** 开户支行城市 */
	@Column(length=50)
	private String city;
	
	/** 收款方_开户银行 */
	@Column(length=254,name="receive_bank")
	private String receiveBank;
	
	/** 收款方_开户支行 */
	@Column(length=254,name="receive_branch")
	private String receiveBranch;
	
	/** 收款方_银行卡号 */
	@Column(length=32,name="receive_card_no")
	private String receiveCardNo;
	
	/** 收款方_姓名 */
	@Column(length=64,name="receive_name")
	private String receiveName;
	
	/** 放款账号 */
	@Column(length=32,name="account_no")
	private String accountNo;
	
	/** 放款金额 */
	private BigDecimal amount;
	
	/** 放款描述 */
	@Column(length=32)
	private String remark;
	
	/** 放款时间 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	@Column(name="loan_date")
	private Date loanDate;
	
	/** 
	 * 放款状态 
	 *  NOLOAN("未放款", 1),
		LOANING("放款中", 2),
		SUCCESSLOAN("放款成功", 3),
		FAILLOAN("放款失败", 4);
	 * */
	private Integer status = 1;
	
	/** 产品类型 */
	@Column(name="product_type")
	private Integer productType;
	
	/** 放款人员 */
	@Column(length=32)
	private String operator;
	
	/** 操作时间 */
	@Column(name="biz_date")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private Date bizDate;
	
	/** 放款人员所在组织Id */
	@Column(name="org_id")
	private String orgId;
	
	/** 放款人员所在组织名称 */
	@Column(length=64,name="org_name")
	private String orgName;
	
	/** 利率 */
	private String rate;
	
	/** 分期数 */
	private Integer installment;
	
	/** 还款方式 */
	private Integer repayment;
	
	/** 起息日 */
	@Column(name="value_date")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date valueDate;
	
	/** 客户手机号 */
	@Column(length=32,name="mobile_phone")
	public String mobilePhone;
	
	/** 合同号*/
	@Column(length=64,name="contract_number")
	public String contractNumber;
	
	/** 利息配比 */
	@Column(name="interest_ratio")
	private BigDecimal interestRatio;
	
	/** 服务费配比 */
	@Column(name="fee_ratio")
	private BigDecimal feeRatio;
	
	/** 放款方式 */
	@Column(name="pay_way")
	private Integer payWay;
	
	/** 到账时间 */
	@Column(name="receive_time")
	private Date receiveTime;
	
	/** 到账金额 */
	@Column(name="receive_amount")
	private BigDecimal receiveAmount;
	
	/** 交易流水号 */
	@Column(length=128,name="trade_number")
	private String tradeNumber;
	
	/** 
	 * 数据状态 
	 * 1.未打款待提交
	 * 2.已打款待提交
	 * 3.已打款已提交
	 * */
	@Column(name="data_status")
	private Integer dataStatus;
	
	/** 快钱交易费 */
	@Column(name="deal_charge")
	private BigDecimal dealCharge;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getReceiveBank() {
		return receiveBank;
	}

	public void setReceiveBank(String receiveBank) {
		this.receiveBank = receiveBank;
	}

	public String getReceiveBranch() {
		return receiveBranch;
	}

	public void setReceiveBranch(String receiveBranch) {
		this.receiveBranch = receiveBranch;
	}

	public String getReceiveCardNo() {
		return receiveCardNo;
	}

	public void setReceiveCardNo(String receiveCardNo) {
		this.receiveCardNo = receiveCardNo;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
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

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public Integer getInstallment() {
		return installment;
	}

	public void setInstallment(Integer installment) {
		this.installment = installment;
	}

	public Integer getRepayment() {
		return repayment;
	}

	public void setRepayment(Integer repayment) {
		this.repayment = repayment;
	}

	public Date getValueDate() {
		return valueDate;
	}

	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public BigDecimal getInterestRatio() {
		return interestRatio;
	}

	public void setInterestRatio(BigDecimal interestRatio) {
		this.interestRatio = interestRatio;
	}

	public BigDecimal getFeeRatio() {
		return feeRatio;
	}

	public void setFeeRatio(BigDecimal feeRatio) {
		this.feeRatio = feeRatio;
	}

	public Integer getPayWay() {
		return payWay;
	}

	public void setPayWay(Integer payWay) {
		this.payWay = payWay;
	}

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public BigDecimal getReceiveAmount() {
		return receiveAmount;
	}

	public void setReceiveAmount(BigDecimal receiveAmount) {
		this.receiveAmount = receiveAmount;
	}

	public String getTradeNumber() {
		return tradeNumber;
	}

	public void setTradeNumber(String tradeNumber) {
		this.tradeNumber = tradeNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Integer dataStatus) {
		this.dataStatus = dataStatus;
	}

	public BigDecimal getDealCharge() {
		return dealCharge;
	}

	public void setDealCharge(BigDecimal dealCharge) {
		this.dealCharge = dealCharge;
	}
	
}
