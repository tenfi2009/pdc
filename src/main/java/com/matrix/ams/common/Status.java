package com.matrix.ams.common;

import com.matrix.core.common.enums.EnumType;

/**
 * 
 * <B>描述：</B><BR>
 * 值对象状态枚举 <BR>
 * 
 */
public enum Status implements EnumType {
	
	NOLOAN("未放款", 1),
	LOANING("放款中", 2),
	SUCCESSLOAN("放款成功", 3),
	FAILLOAN("放款失败", 4);

	private final String displayName;
	private final int sequence;

	private Status(String displayName, int sequence) {
		this.displayName = displayName;
		this.sequence = sequence;
	}

	@Override
	public String getDisplayName() {
		return displayName;
	}

	@Override
	public int getValue() {
		return ordinal();
	}

	@Override
	public int getSequence() {
		return sequence;
	}

	@Override
	public String toString() {
		StringBuffer s = new StringBuffer();
		s.append("{");
		s.append("\"name\":\"").append(name()).append("\"");
		s.append(",\"displayName\":\"").append(displayName).append("\"");
		s.append(",\"value\":\"").append(getValue()).append("\"");
		s.append(",\"sequence\":\"").append(sequence).append("\"");
		s.append("}");
		return s.toString();
	}
}
