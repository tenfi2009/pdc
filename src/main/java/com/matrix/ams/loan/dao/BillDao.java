package com.matrix.ams.loan.dao;

import com.matrix.ams.loan.model.Bill;
import com.matrix.ams.loan.model.CommonOrderInfo;
import com.matrix.core.dao.BaseDao;

public interface BillDao extends BaseDao<Bill,Long>{

	/**
	 * 根据手机号查询用户
	 * @param mobilePhone
	 * @return
	 */
	public Integer getUserId(String mobilePhone);
	
	/**
	 * 新增用户
	 * @param mobilePhone
	 * @return
	 */
	public int insertUser(String mobilePhone);
	
	/**
	 * 插入用户信息
	 * @param userId
	 * @param mobilePhone
	 * @return
	 */
	public int insertUserInfo(Integer userId,String mobilePhone);
	
	/**
	 * 新增订单
	 * @param order
	 * @return
	 */
	public int insertOrder(CommonOrderInfo order);
	
	/**
	 * 根据订单号查询生成的账账单数量
	 * @param orderId
	 * @return
	 */
	public int findBillByOrderId(String orderId);
}
