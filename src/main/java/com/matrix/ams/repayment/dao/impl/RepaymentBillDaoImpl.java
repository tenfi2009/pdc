package com.matrix.ams.repayment.dao.impl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.web.util.HtmlUtils;

import com.google.gson.Gson;
import com.matrix.ams.repayment.dao.RepaymentBillDao;
import com.matrix.ams.repayment.model.RepaymentBill;
import com.matrix.ams.repayment.model.SettleBillDTO;
import com.matrix.core.dao.impl.BaseDaoImpl;
import com.matrix.core.util.Page;

@Repository
public class RepaymentBillDaoImpl extends BaseDaoImpl<RepaymentBill, String> implements RepaymentBillDao {
	
	/**
	 * 查询结算清单
	 * @param page
	 * @param queryParams
	 * @return
	 */
	public Page<SettleBillDTO> findSettleBillPage(Page<SettleBillDTO> page, Map queryParams) {
		
		//基本查询
		StringBuffer sql = new StringBuffer(getBaseSelect());
		
		//先统计总数
		StringBuffer countSql = new StringBuffer("select count(B.id) from bill B ");
				
		//查询条件
		StringBuffer where = new StringBuffer(" where 1=1 ");
		if(null != queryParams && StringUtils.isNotEmpty((String)queryParams.get("queryConditions"))){
			Gson gson = new Gson();
			String queryConditions = HtmlUtils.htmlUnescape((String)queryParams.get("queryConditions"));
			Map  conditions = gson.fromJson(queryConditions,Map.class);
			
			if("1".equals(conditions.get("status"))){//未还清
				where.append(" and B.pay_amount+coupon < " + SQL_PAYABLES + "-B.settle_diff_amount");
			}else if("2".equals(conditions.get("status"))){//已还清
				where.append(" and B.pay_amount+coupon >= " + SQL_PAYABLES + "-B.settle_diff_amount");
			}
			
			if(StringUtils.isNotEmpty((String)conditions.get("bizDateFrom"))){
				where.append(" and B.create_time >= '").append((String)conditions.get("bizDateFrom")).append(":00' ");
			}
			
			if(StringUtils.isNotEmpty((String)conditions.get("bizDateTo"))){
				where.append(" and B.create_time <= '").append((String)conditions.get("bizDateTo")).append(":59' ");
			}
			
			//条件查询
			if(StringUtils.isNotEmpty((String)conditions.get("queryContent")) && StringUtils.isNotEmpty((String)conditions.get("queryCondition"))){
				String queryContent = (String)conditions.get("queryContent");
				String queryCondition = (String)conditions.get("queryCondition");
				
				if("1".equals(queryCondition)){//订单号
					where.append(" and B.bill_id = '").append(queryContent).append("' ");
				}else if("2".equals(queryCondition)){//用户ID
					where.append(" and B.user_id = ").append(queryContent).append(" ");
				}else if("3".equals(queryCondition)){//注册手机号
					//查询需要关联到fenqi_user表
					sql.append(",fenqi_user T2 ");
					countSql.append(",fenqi_user T2 ");
					
					where.append(" and T2.mobile_phone = '").append(queryContent).append("' ");
					where.append(" and T2.id = B.user_id ");
				}else if("4".equals(queryCondition)){//身份证号
					//查询需要关联到 fenqi_user_info
					sql.append(",fenqi_user_info T2 ");
					countSql.append(",fenqi_user_info T2 ");
					where.append(" and T2.id_number = '").append(queryContent).append("' ");
					where.append(" and T2.user_id = B.user_id ");
				}
			}
		}
		
		
		
		//查询总记录数
		SQLQuery query = this.getSession().createSQLQuery(countSql.append(where).toString());
		List<BigInteger> totalCounts = query.list();
		page.setTotalCount(totalCounts.get(0).intValue());
		
		//查询具体数据
		sql.append(where).append(" limit ").append(page.getCurrPage()-1).append(",").append(page.getPageSize());
		SQLQuery billQuery = this.getSession().createSQLQuery(sql.toString());
		List<Object[]> result = billQuery.list();
		List<SettleBillDTO> SettleBillDTOs = null;
		if(result.isEmpty()){
			SettleBillDTOs = new ArrayList<SettleBillDTO>();
		}else{
			SettleBillDTO dto = null;
			SettleBillDTOs = new ArrayList<SettleBillDTO>(result.size());
			for(Object[] row : result){//转化成DTO
				dto = getSettleBillDTOFromBill(row);
				SettleBillDTOs.add(dto);
			}
		}
		
		page.setResult(SettleBillDTOs);
		
		return page;
	}
	
	//当期应付=本+息+费+滞纳金+罚息+违约金
	private static final String SQL_PAYABLES = "B.capital+B.interest+B.service_fee+B.late_fee+B.penalty_interest+B.penalty";
	
	public String getBaseSelect(){
		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append("B.id,");
		sql.append("B.order_id,");
		sql.append("B.installment_number,");
		sql.append("B.capital,");
		sql.append("B.service_fee,");
		sql.append("B.penalty_interest,");
		sql.append("B.should_pay_time,");
		sql.append(SQL_PAYABLES).append(" as payables,");//当期应付=本+息+费+'滞纳金+罚息+违约金
		sql.append("B.pay_amount,");
		sql.append(SQL_PAYABLES).append("-B.settle_diff_amount as bill_amount,");//账单应付金额 = 本+息+费+'滞纳金+罚息+违约金
		sql.append("B.settle_diff_amount,");
		sql.append("B.coupon,");
		sql.append("case when B.pay_amount+coupon >=" + SQL_PAYABLES + "-B.settle_diff_amount then 2 else 1 end status");//还款状态 ：1 未还清，2：已还清
     
		sql.append(" from bill B");
		
		return sql.toString();
	}
	
	public SettleBillDTO findSettleBillDTOByBillId(Integer billId){
		
		StringBuffer sql = new StringBuffer(getBaseSelect());
		sql.append(" where B.id = ? ");
		SQLQuery billQuery = this.getSession().createSQLQuery(sql.toString());
		billQuery.setInteger(0, billId);
		
		List<Object[]> result = billQuery.list();
		
		if(result.isEmpty()){
			return null;
		}else{
			return getSettleBillDTOFromBill(result.get(0));
		}
	}
	
	private SettleBillDTO getSettleBillDTOFromBill(Object[] row){
		SettleBillDTO dto = new SettleBillDTO();
		
		dto.setId(((BigInteger)row[0]).intValue());
		dto.setOrderId((String)row[1]);
		dto.setInstallmentNumber((Integer)row[2]);
		dto.setCapital((Double)row[3]);
		dto.setServiceFee((Double)row[4]);
		dto.setPenaltyInterest((Double)row[5]);
		dto.setShouldPayTime((Timestamp)row[6]);
		dto.setPayables((Double)row[7]);
		dto.setPayAmount((Double)row[8]);
		dto.setBillAmount((Double)row[9]);
		dto.setSettleDiffAmount((Double)row[10]);
		dto.setCoupon((Double)row[11]);
		dto.setStatus(((BigInteger)row[12]).intValue());
		
		return dto;
	}
}
