package com.matrix.ams.loan.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@SuppressWarnings("serial")
@javax.persistence.Entity
@Table(name = "t_loan_repayment_operation_log")
public class LoanRepaymentOperationLog extends com.matrix.core.model.Entity<String> {

	public static final int OBJECTTYPE_LOAN = 1;
	public static final int OBJECTTYPE_REPAYMENT = 2;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator = "idGenerator")
	@Column(length = 36)
	private String id;

	/**
	 * 对象类型 1:放款 2：收款
	 */
	@Column(name = "object_type")
	private Integer objectType;

	/**
	 * 业务对象ID
	 */
	@Column(name = "object_id", length = 36)
	private String objectId;

	/**
	 * 操作详情
	 */
	@Column(name = "content", length = 64)
	private String content;

	/** 操作人 */
	@Column(length = 32)
	private String operator;

	/** 操作时间 */
	@Column(name = "operation_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date operationTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getObjectType() {
		return objectType;
	}

	public void setObjectType(Integer objectType) {
		this.objectType = objectType;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}
}
