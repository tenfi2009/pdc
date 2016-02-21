package com.matrix.ams.loan.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.matrix.ams.common.Status;
import com.matrix.ams.loan.dao.LoanBillDao;
import com.matrix.ams.loan.model.LoanBill;
import com.matrix.ams.paycenter.model.QueryPayResult;
import com.matrix.ams.paycenter.model.QueryPayResultEnum;
import com.matrix.core.dao.impl.BaseDaoImpl;

@Repository
public class LoanBillDaoImpl extends BaseDaoImpl<LoanBill, String> implements LoanBillDao {
	public List<String> getLoaningBillIds() {
		SQLQuery query = this.getSession().createSQLQuery("select id from t_loan_bill where status = ? ");
		query.setInteger(0, Status.LOANING.getSequence());
		List<String> ids = query.list();
		return ids;
	}
	
	/**
	 * 更新放款单支付状态
	 * @param id
	 * @param rs
	 */
	public void updateLoanStatus(String id,QueryPayResult rs){
		Integer status = null;
		if(QueryPayResultEnum.SUCCESS.reCode().equals(rs.getRetCode())){
			status = Status.SUCCESSLOAN.getSequence();
		}else if(QueryPayResultEnum.PAY_FAIL.reCode().equals(rs.getRetCode())){
			status = Status.FAILLOAN.getSequence();
		}else{
			return;
		}
		
		SQLQuery query = this.getSession().createSQLQuery("update table t_loan_bill set status = ? where id = ?");
		
		query.setInteger(0, status);
		query.setString(1, id);
	}
}
