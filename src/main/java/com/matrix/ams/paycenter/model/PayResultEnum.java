package com.matrix.ams.paycenter.model;

public enum PayResultEnum {
	REPEAT_PAY("-1","该付款已经提交打款,请勿重复操作"),
	ORDER_NOT_EXIT("-2","付款订单不存在"),
	RESPONSE_NULL("-3","响应为空");
	
	
	
	private String reCode;
	
	private String retFailureCause;
	
	private PayResultEnum(String reCode, String retFailureCause) {
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
