package com.matrix.basicData.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.matrix.core.model.BasicDataVO;

/**
 * 
 * 还款方式
 * @author rong yang
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_bd_repayment")
public class Repayment extends BasicDataVO<Long> implements Serializable{

	@Id
	@GenericGenerator(name = "integer_id_gen", strategy = "enhanced-table", parameters = {
			@Parameter(name = "table_name", value = "t_sys_id_gen"),
			@Parameter(name = "value_column_name", value = "next"),
			@Parameter(name = "segment_column_name", value = "property"),
			@Parameter(name = "segment_value", value = "com.matrix.basicData.model.Repayment.id"),
			@Parameter(name = "increment_size", value = "1") })
	@GeneratedValue(generator = "integer_id_gen")
	@Column(name = "id")
	private Long id;
	
	/**
	 * 是否内置
	 */
	@Column(name = "is_built")
	private Boolean isBuilt;
	
	/**
	 * 实现类全称
	 */
	@Column(name = "calss_name",length=254)
	private String calssName;
	
	/**
	 * 参数
	 */
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="repayment")
	private Set<RepaymentParam> params = new HashSet<RepaymentParam>();
	
	public Repayment() {
	
	}

	@Override
	public Long getId() {
		
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
		
	}

	public Boolean getIsBuilt() {
		return isBuilt;
	}

	public void setIsBuilt(Boolean isBuilt) {
		this.isBuilt = isBuilt;
	}

	public String getCalssName() {
		return calssName;
	}

	public void setCalssName(String calssName) {
		this.calssName = calssName;
	}

	public Set<RepaymentParam> getParams() {
		return params;
	}

	public void setParams(Set<RepaymentParam> params) {
		this.params = params;
	}

	
}
