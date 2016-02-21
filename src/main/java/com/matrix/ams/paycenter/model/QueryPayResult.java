package com.matrix.ams.paycenter.model;

public class QueryPayResult{
	/**
	 * 返回编码
	 */
	private String retCode;
	
	/**
	 * 错误原因
	 */
	private String retFailureCause;
	
	public QueryPayResult(){
		
	}
	
	public QueryPayResult(QueryPayResultEnum queryPayResultEnum){
		this.retCode = queryPayResultEnum.reCode();
		this.retFailureCause = queryPayResultEnum.retFailureCause();
	}
	
	public QueryPayResult(String retCode, String retFailureCause){
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
