/**
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 人人公司 http://www.renren-inc.com </p> 
 * <p>Description:  </p>
 * <p>Author:zcliu/刘子萃</p>
 * @Title: CommonOrderInfo.java
 * @Package com.renren.fenqi.component.model.workflow
 * @date 2015-4-4 下午4:57:23 
 */
package com.matrix.ams.loan.model;

import java.util.Date;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 人人公司 http://www.renren-inc.com</p> 
 * <p>Description: 通用流程订单信息 </p> 
 * <p>Author:zcliu/刘子萃</p>
 */

public class CommonOrderInfo {

	private int id;
	private String commonOrderId;
	private String contractCode;
	
	/**
	 * 对应的通用用户id
	 */
	private int commonUid;
	private String name;
	private double price;
	
	/**
	 * 
	 */
	private double capital;
	
	/**
	 * 属性信息
	 */
	private String attrInfo;
	
	private int stages;
	
	/**
	 * 还款方式
	 */
	private int repaymentType;
	private double originInterestRate;
	private double couponRate;
	private double interestRate;
	private int type;
	private int status;
	private Date createTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCommonOrderId() {
		return commonOrderId;
	}
	public void setCommonOrderId(String commonOrderId) {
		this.commonOrderId = commonOrderId;
	}
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	public int getCommonUid() {
		return commonUid;
	}
	public void setCommonUid(int commonUid) {
		this.commonUid = commonUid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getCapital() {
		return capital;
	}
	public void setCapital(double capital) {
		this.capital = capital;
	}
	public String getAttrInfo() {
		return attrInfo;
	}
	public void setAttrInfo(String attrInfo) {
		this.attrInfo = attrInfo;
	}
	public int getStages() {
		return stages;
	}
	public void setStages(int stages) {
		this.stages = stages;
	}
	public int getRepaymentType() {
		return repaymentType;
	}
	public void setRepaymentType(int repaymentType) {
		this.repaymentType = repaymentType;
	}
	public double getOriginInterestRate() {
		return originInterestRate;
	}
	public void setOriginInterestRate(double originInterestRate) {
		this.originInterestRate = originInterestRate;
	}
	public double getCouponRate() {
		return couponRate;
	}
	public void setCouponRate(double couponRate) {
		this.couponRate = couponRate;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
