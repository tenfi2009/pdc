package com.matrix.loan.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.matrix.basicData.model.ProductCategory;
import com.matrix.core.model.BasicDataVO;

@SuppressWarnings("serial")
@Entity
@Table(name = "t_loan_product")
public class LoanProduct extends BasicDataVO<String> implements Serializable{
	
	@Id
	@GenericGenerator(name="idGenerator",strategy="org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator="idGenerator")
	@Column(length=36)
	private String id;
	
	/** 产品分类 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private ProductCategory category;

	/** 出借本金公式 */
	@Column(length=512,name = "principal_formula")
	private String principalFormula;
	
	/** 出借本金公式名称 */
	@Column(length=1024,name = "principal_formula_name")
	private String principalFormulaName;
	
	/** 币种 */
	private Integer currency;
	
	/** 合同下限 */
	@Column(name = "min_limit")
	private BigDecimal minLimit;
	
	/** 合同上限 */
	@Column(name = "max_limit")
	private BigDecimal maxLimit;
	
	/** 开卖时间 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "sale_time")
	private Date saleTime;
	
	/** 截止时间 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "off_time")
	private Date offTime;
	
	/** 支持分期数 */
	@Column(length=64)
	private String instalments;
	
	/** 分期单位 */
	private Integer unit;
	
	/** 还款日确定方式 */
	@Column(name = "day_payment_way")
	private Integer dayPaymentWay;
	
	/** 自定义还款-可用还款日 */
	@Column(length=64,name = "useable_day_payment")
	private String useableDayPayment;
	
	/** 自定义还款-首期确定方式 */
	@Column(name = "first_period_way")
	private Integer firstPeriodWay;
	
	/** 自定义还款-首期计息方式	*/
	@Column(name = "first_period_interest_way")
	private Integer firstPeriodInterestWay;
	
	/** 是否已配置 */
	private Boolean config = false;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	public String getPrincipalFormula() {
		return principalFormula;
	}

	public void setPrincipalFormula(String principalFormula) {
		this.principalFormula = principalFormula;
	}

	public String getPrincipalFormulaName() {
		return principalFormulaName;
	}

	public void setPrincipalFormulaName(String principalFormulaName) {
		this.principalFormulaName = principalFormulaName;
	}

	public Integer getCurrency() {
		return currency;
	}

	public void setCurrency(Integer currency) {
		this.currency = currency;
	}

	public Date getSaleTime() {
		return saleTime;
	}

	public void setSaleTime(Date saleTime) {
		this.saleTime = saleTime;
	}

	public Date getOffTime() {
		return offTime;
	}

	public void setOffTime(Date offTime) {
		this.offTime = offTime;
	}

	public String getInstalments() {
		return instalments;
	}

	public void setInstalments(String instalments) {
		this.instalments = instalments;
	}

	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	public Integer getDayPaymentWay() {
		return dayPaymentWay;
	}

	public void setDayPaymentWay(Integer dayPaymentWay) {
		this.dayPaymentWay = dayPaymentWay;
	}

	public String getUseableDayPayment() {
		return useableDayPayment;
	}

	public void setUseableDayPayment(String useableDayPayment) {
		this.useableDayPayment = useableDayPayment;
	}

	public Integer getFirstPeriodWay() {
		return firstPeriodWay;
	}

	public void setFirstPeriodWay(Integer firstPeriodWay) {
		this.firstPeriodWay = firstPeriodWay;
	}

	public Integer getFirstPeriodInterestWay() {
		return firstPeriodInterestWay;
	}

	public void setFirstPeriodInterestWay(Integer firstPeriodInterestWay) {
		this.firstPeriodInterestWay = firstPeriodInterestWay;
	}

	public BigDecimal getMinLimit() {
		return minLimit;
	}

	public void setMinLimit(BigDecimal minLimit) {
		this.minLimit = minLimit;
	}

	public BigDecimal getMaxLimit() {
		return maxLimit;
	}

	public void setMaxLimit(BigDecimal maxLimit) {
		this.maxLimit = maxLimit;
	}

	public Boolean getConfig() {
		return config;
	}

	public void setConfig(Boolean config) {
		this.config = config;
	}

}
