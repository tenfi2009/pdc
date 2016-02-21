package com.matrix.ams.paycenter.model;

import java.util.HashMap;
import java.util.Map;

public class PayResult{
	/**
	 * 返回编码
	 */
	private String retCode;
	
	/**
	 * 错误原因
	 */
	private String retFailureCause;
	
	Map otherInfo = new HashMap();
	
	public PayResult(){
		
	}
	
	public PayResult(PayResultEnum payResultEnum){
		this.retCode = payResultEnum.reCode();
		this.retFailureCause = payResultEnum.retFailureCause();
	}
	
	public PayResult(String retCode, String retFailureCause){
		this.retCode = retCode;
		this.retFailureCause = retFailureCause;
	}
	
	public String getRetCode() {
		return retCode;
	}
	
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	
	public String getRetFailureCause() {
		return retFailureCause;
	}
	
	public void setRetFailureCause(String retFailureCause) {
		this.retFailureCause = retFailureCause;
	}

	public Map getOtherInfo() {
		return otherInfo;
	}
}
