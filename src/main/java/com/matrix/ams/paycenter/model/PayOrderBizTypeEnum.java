package com.matrix.ams.paycenter.model;
/**
 * 
 * 付款业务类型枚举
 * @author hu.tang
 *
 */
public enum PayOrderBizTypeEnum {
	BIZ_QINGKE(1,"青客业务");
	private int value;
	private String desc;
	PayOrderBizTypeEnum(int value, String desc){
		this.value = value;
		this.desc = desc;
	}
	public int value(){
		return this.value;
	}
	public String desc(){
		return this.desc;
	}
}
