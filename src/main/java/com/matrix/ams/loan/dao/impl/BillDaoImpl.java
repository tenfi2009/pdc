package com.matrix.ams.loan.dao.impl;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.matrix.ams.loan.dao.BillDao;
import com.matrix.ams.loan.model.Bill;
import com.matrix.ams.loan.model.CommonOrderInfo;
import com.matrix.core.dao.impl.BaseDaoImpl;
@Repository
public class BillDaoImpl extends BaseDaoImpl<Bill, Long> implements BillDao{

	public BillDaoImpl() {
		
	}

	
	/**
	 * 根据手机号查询用户
	 * @param mobilePhone
	 * @return
	 */
	public Integer getUserId(String mobilePhone){
		SQLQuery query = this.getSession().createSQLQuery("select id from fenqi_user where  mobile_phone = ? ");
		query.setString(0, mobilePhone);
		List<Integer> ids = query.list();
		if(null != ids && ids.size() > 0){
			return ids.get(0);
		}
		
		return null;
		
	}
	
	/**
	 * 新增用户
	 * @param mobilePhone
	 * @return
	 */
	public int insertUser(String mobilePhone){
		SQLQuery query = this.getSession().createSQLQuery("insert into fenqi_user(mobile_phone,pwd,reg_time) values(?,?,?)");
		query.setString(0, mobilePhone);
		query.setString(1, "");//默认密码
		query.setDate(2, new Date());
		return query.executeUpdate();
	}
	
	/**
	 * 插入用户信息
	 * @param userId
	 * @param mobilePhone
	 * @return
	 */
	public int insertUserInfo(Integer userId,String mobilePhone){
		SQLQuery query = this.getSession().createSQLQuery("insert into fenqi_user_info( user_id, mobile_phone) values(?,?)");
		query.setInteger(0, userId);
		query.setString(1, mobilePhone);
		return query.executeUpdate();
	}
	/*
	CREATE TABLE fenqi_test.common_order_info
	(
	   id                    INT            NOT NULL,
	   common_order_id       VARCHAR(30)    DEFAULT '' NOT NULL COMMENT '订单id',
	   contract_no           VARCHAR(50)    DEFAULT '' NOT NULL COMMENT '合同编号',
	   common_uid            INT            DEFAULT 0 NOT NULL COMMENT '对应的通用用户id',
	   name                  VARCHAR(100)   DEFAULT '' NOT NULL COMMENT '产品名称',
	   price                 DOUBLE         DEFAULT 0.00 NOT NULL COMMENT '价格',
	   capital               DOUBLE         DEFAULT 0.00 NOT NULL COMMENT '首付',
	   attr_info             VARCHAR(200)   DEFAULT '' NOT NULL COMMENT '属性信息',
	   stages                INT            DEFAULT 0 NOT NULL COMMENT '分期数',
	   repayment_type        INT            DEFAULT 0 NOT NULL COMMENT '还款方式',
	   repayment_type_desc   VARCHAR(20)    DEFAULT '' NOT NULL COMMENT '还款方式描述',
	   origin_interest_rate  DOUBLE         DEFAULT 0 NOT NULL COMMENT '原利率',
	   coupon_rate           DOUBLE         DEFAULT 0 NOT NULL COMMENT '优惠利率',
	   interest_rate         DOUBLE         DEFAULT 0 NOT NULL COMMENT '计算中使用的利率',
	   type                  TINYINT        DEFAULT 0 NOT NULL COMMENT '业务类型',
	   status                TINYINT        DEFAULT 0 NOT NULL COMMENT '订单状态'
	)
	ENGINE=InnoDB;
	
	*/
	
	/**
	 * 根据订单号查询生成的账账单数量
	 * @param orderId
	 * @return
	 */
	public int findBillByOrderId(String orderId){
		SQLQuery query = this.getSession().createSQLQuery("select count(*) from bill where order_id = ? ");
		query.setString(0, orderId);
		List<BigInteger> ids = query.list();
		return ids.get(0).intValue();
	}
	
	/**
	 * 新增订单
	 * @param order
	 * @return
	 */
	public int insertOrder(CommonOrderInfo order){
		String sql = "insert into common_order_info(common_order_id,"
				+ "contract_no,"
				+ "common_uid,"
				+ "name,"
				+ "price,"
				+ "capital,"
				+ "attr_info,"
				+ "stages,"
				+ "repayment_type,"
				+ "origin_interest_rate,"
				+ "coupon_rate,"
				+ "interest_rate,"
				+ "type,"
				+ "status"
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.setString(0, order.getCommonOrderId());
		query.setString(1, order.getContractCode());
		query.setInteger(2, order.getCommonUid());
		query.setString(3, order.getName());
		query.setDouble(4, order.getPrice());
		query.setDouble(5, order.getCapital());
		query.setString(6, order.getAttrInfo());
		query.setInteger(7, order.getStages());
		query.setInteger(8, order.getRepaymentType());
		query.setDouble(9, order.getOriginInterestRate());
		query.setDouble(10, order.getCouponRate());
		query.setDouble(11, order.getInterestRate());
		query.setInteger(12, order.getType());
		query.setInteger(13, order.getStatus());
		
		return query.executeUpdate();
	}
}
