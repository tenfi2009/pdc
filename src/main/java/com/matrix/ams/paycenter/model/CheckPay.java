package com.matrix.ams.paycenter.model;

public class CheckPay {
	/**
	 * 返回编码
	 */
	private String retCode;
	
	/**
	 * 错误原因
	 */
	private String retFailureCause;
	
	public CheckPay(){
		
	}
	
	public CheckPay(CheckPayEnum checkPayResultEnum){
		this.retCode = checkPayResultEnum.reCode();
		this.retFailureCause = checkPayResultEnum.retFailureCause();
	}
	
	public CheckPay(String retCode, String retFailureCause){
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
}
