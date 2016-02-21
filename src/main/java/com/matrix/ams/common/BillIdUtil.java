package com.matrix.ams.common;

public class BillIdUtil {

	/**
	 * 产生订单号
	 * 
	 * @param userId
	 * @param installmentNumber
	 * @return
	 */
	public static String generateBillId(String orderId, int installmentNumber) {
		int len = orderId.length();
		String uid = orderId.substring(len - 2); // 订单号后两位是用户id
		String oid = orderId.substring(0, len - 2);

		long num = 1000 + installmentNumber; // 之后3位是分期号,这个长度30年房贷都够了
		String billId = oid;
		billId += (num + "").substring(1); // 干掉最前面的1
		billId += uid;
		return billId;
	}

	public static void main(String[] args) {
		System.out.println(generateBillId("abc24", 2));
	}

}
