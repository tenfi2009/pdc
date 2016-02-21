package com.matrix.basicData.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.matrix.core.model.BasicDataVO;
@SuppressWarnings("serial")
@Entity
@Table(name = "t_bd_fee")
public class Fee extends BasicDataVO<Long> {

	@Id
	@GenericGenerator(name = "integer_id_gen", strategy = "enhanced-table", parameters = {
			@Parameter(name = "table_name", value = "t_sys_id_gen"),
			@Parameter(name = "value_column_name", value = "next"),
			@Parameter(name = "segment_column_name", value = "property"),
			@Parameter(name = "segment_value", value = "com.matrix.basicData.model.Fee.id"),
			@Parameter(name = "increment_size", value = "1") })
	@GeneratedValue(generator = "integer_id_gen")
	@Column(name = "id")
	private Long id;
	
	/** 费用类型  1:前期费用,2:分期费用,3:逾期费用  */
	@Column
	private Integer type;
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
		
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
