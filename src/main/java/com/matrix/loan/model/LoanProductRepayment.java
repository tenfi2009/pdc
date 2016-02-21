package com.matrix.loan.model;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.matrix.basicData.model.Repayment;
import com.matrix.core.model.Entity;

@SuppressWarnings("serial")
@javax.persistence.Entity
@Table(name="t_loan_product_repayment")
public class LoanProductRepayment extends Entity<String>{

	@Id
	@GenericGenerator(name="idGenerator",strategy="org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator="idGenerator")
	@Column(length=36)
	private String id;
	
	/** 所属产品 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private LoanProduct product;
	
	/** 所属分期 */
	private Integer instalment;

	/** 还款方式 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="repayment_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private Repayment repayment;
	
	/** 还款方式 参数*/
	@Column(length=512,name="param_value")
	private Integer paramValue;
	
	/** 是否默认还款方式 */
	@Column(name="is_default")
	private Boolean isDefault;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LoanProduct getProduct() {
		return product;
	}

	public void setProduct(LoanProduct product) {
		this.product = product;
	}

	public Integer getInstalment() {
		return instalment;
	}

	public void setInstalment(Integer instalment) {
		this.instalment = instalment;
	}

	public Repayment getRepayment() {
		return repayment;
	}

	public void setRepayment(Repayment repayment) {
		this.repayment = repayment;
	}

	public Integer getParamValue() {
		return paramValue;
	}

	public void setParamValue(Integer paramValue) {
		this.paramValue = paramValue;
	}

}
