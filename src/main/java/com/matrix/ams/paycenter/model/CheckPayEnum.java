package com.matrix.ams.paycenter.model;

public enum CheckPayEnum {
	SUCCESS("0","检查成功"),
	NOT_PAY_TIME("1","没到打款期限"),
	MONEY_NOT_PATCH("2","金额不匹配");
	
	private String reCode;
	
	private String retFailureCause;
	
	private CheckPayEnum(String reCode, String retFailureCause) {
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
