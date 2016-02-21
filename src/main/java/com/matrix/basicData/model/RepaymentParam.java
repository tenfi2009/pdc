package com.matrix.basicData.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 还款方式-参数
 * @author rong yang
 *
 */
@SuppressWarnings("serial")
@javax.persistence.Entity
@Table(name = "t_bd_repayment_param")
public class RepaymentParam extends com.matrix.core.model.Entity<String> implements Serializable {
	@Id
	@GenericGenerator(name="idGenerator",strategy="org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator="idGenerator")
	@Column(length=36)
	private String id;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="repayment_id")
	private Repayment repayment;
	
	/** 编码 */
	@Column(length = 32)
	private String code;

	/** 名称 */
	@Column(length = 64)
	private String name;

	/** 参数类型  1:输入参数,2:输出参数,3:中间参数  */
	@Column
	private Integer type;
	
	/** 参数的数据类型 */
	@Column(name = "data_type")
	private Integer dataType;
	
	/** 排序号 */
	@Column(name = "sort_no")
	private Integer sortNo;

	/** 描述 */
	@Column(length = 254)
	private String description;
	
	/** 公式 */
	@Column(length = 1024)
	private String formula;
	
	/** 公式名称 */
	@Column(length = 2048)
	private String formula_name;
	
	public RepaymentParam() {
		
	}
	
	public RepaymentParam(Repayment repayment) {
		this.repayment = repayment;
	}

	@Override
	public String getId() {
		
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public Repayment getRepayment() {
		return repayment;
	}

	public void setRepayment(Repayment repayment) {
		this.repayment = repayment;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getFormula_name() {
		return formula_name;
	}

	public void setFormula_name(String formula_name) {
		this.formula_name = formula_name;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
