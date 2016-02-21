package com.matrix.ams.loan.dao.impl;

import org.springframework.stereotype.Repository;

import com.matrix.ams.loan.dao.LoanRepaymentOperationLogDao;
import com.matrix.ams.loan.model.LoanRepaymentOperationLog;
import com.matrix.core.dao.impl.BaseDaoImpl;

@Repository
public class LoanRepaymentOperationLogDaoImpl extends BaseDaoImpl<LoanRepaymentOperationLog, String> implements LoanRepaymentOperationLogDao{

}
