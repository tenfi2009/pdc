package com.matrix.ams.loan.service;

import java.util.List;

import com.matrix.ams.loan.model.LoanRepaymentOperationLog;
import com.matrix.core.service.BaseService;

public interface LoanRepaymentOperationLogService extends BaseService<LoanRepaymentOperationLog,String>{

	/**
	 * 根据业务对象ID查找日志
	 * @param objectId
	 * @return
	 */
	public List<LoanRepaymentOperationLog> findLogByObjectId(String objectId);
}
