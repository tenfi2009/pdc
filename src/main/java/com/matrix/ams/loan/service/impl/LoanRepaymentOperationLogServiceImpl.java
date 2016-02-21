package com.matrix.ams.loan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.matrix.ams.loan.dao.LoanRepaymentOperationLogDao;
import com.matrix.ams.loan.model.LoanRepaymentOperationLog;
import com.matrix.ams.loan.service.LoanRepaymentOperationLogService;
import com.matrix.core.service.impl.BaseServiceImpl;

@Service
public class LoanRepaymentOperationLogServiceImpl extends BaseServiceImpl<LoanRepaymentOperationLog,String> implements LoanRepaymentOperationLogService{

	@Autowired
	private LoanRepaymentOperationLogDao dao;

	public LoanRepaymentOperationLogDao getDao() {
		return dao;
	}

	/**
	 * 根据业务对象ID查找日志
	 * @param objectId
	 * @return
	 */
	@Override
	public List<LoanRepaymentOperationLog> findLogByObjectId(String objectId) {
		Search search = new Search(LoanRepaymentOperationLog.class);
		
		StringBuffer filters = new StringBuffer(" {objectId} = '").append(objectId).append("'");
		search.addFilterCustom(filters.toString());
		
		return dao.search(search);
	}

	
}
