package com.matrix.basicData.model;

import java.io.Serializable;

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
import org.hibernate.annotations.Parameter;

import com.matrix.core.model.TreeVO;

/**
 * 产品分类
 * 
 * @author rong yang
 *
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "t_bd_product_category")
public class ProductCategory extends TreeVO<Long> implements Serializable {

	@Id
	@GenericGenerator(name = "integer_id_gen", strategy = "enhanced-table", parameters = {
			@Parameter(name = "table_name", value = "t_sys_id_gen"),
			@Parameter(name = "value_column_name", value = "next"),
			@Parameter(name = "segment_column_name", value = "property"),
			@Parameter(name = "segment_value", value = "com.matrix.basicData.model.ProductCategory.id"),
			@Parameter(name = "increment_size", value = "1") })
	@GeneratedValue(generator = "integer_id_gen")
	@Column(name = "id")
	private Long id;

	/**
	 * 产品类型
	 */
	@Column
	private Integer type;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private ProductCategory parent;

	public ProductCategory() {
		super();

	}

	public ProductCategory(ProductCategory parent) {
		super();
		this.parent = parent;
	}

	@Override
	public ProductCategory getParent() {
		return this.parent;
	}

	public void setParent(ProductCategory parent) {
		this.parent = parent;
	}

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
