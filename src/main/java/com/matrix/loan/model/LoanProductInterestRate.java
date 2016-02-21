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
@Table(name = "t_loan_product_interest_rate")
public class LoanProductInterestRate extends Entity<String>{

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

	/** 利率 */
	@Column(length=32)
	private String rate;
	
	/** 默认还款方式 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="repayment_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private Repayment repayment;
	
	/** 备注 */
	@Column(length=254)
	private String remark;

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

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public Repayment getRepayment() {
		return repayment;
	}

	public void setRepayment(Repayment repayment) {
		this.repayment = repayment;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
