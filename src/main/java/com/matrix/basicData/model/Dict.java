package com.matrix.basicData.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.GenericGenerator;

import com.matrix.core.model.TreeVO;

/**
 * @author Administrator
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_bd_dict")
public class Dict extends TreeVO<Long> implements Serializable {

	@Id
	@GenericGenerator(name = "integer_id_gen", strategy = "enhanced-table", parameters = {
			@Parameter(name = "table_name", value = "t_sys_id_gen"),
			@Parameter(name = "value_column_name", value = "next"),
			@Parameter(name = "segment_column_name", value = "property"),
			@Parameter(name = "segment_value", value = "com.matrix.basicData.model.Dict.id"),
			@Parameter(name = "increment_size", value = "1") })
	@GeneratedValue(generator = "integer_id_gen")
	@Column(name = "id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private Dict parent;

	public Dict() {
		super();
	}

	public Dict(Dict parent) {
		super();
		this.parent = parent;
	}

	public Dict getParent() {
		return parent;
	}

	public void setParent(Dict parent) {
		this.parent = parent;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
