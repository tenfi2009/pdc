package com.matrix.ams.paycenter.model;

public enum QueryPayResultEnum {
	SUCCESS("0","付款成功"),
	DEALING("1","处理中"),
	QUERY_NULL("2","响应为空"),
	PAY_FAIL("3","支付失败");
	
	
	private String reCode;
	
	private String retFailureCause;
	
	private QueryPayResultEnum(String reCode, String retFailureCause) {
		this.reCode = reCode;
		this.retFailureCause = retFailureCause;
	}
	
	public String reCode(){
		return reCode;
	}
	
	public String retFailureCause(){
		return retFailureCause;
	}
}
