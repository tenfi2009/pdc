package com.matrix.common.repayment;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.matrix.common.repayment.impl.AverageCapital;
import com.matrix.common.repayment.impl.AverageCapitalInterest;
import com.matrix.common.repayment.impl.AverageCapitalPlusInterest;
import com.matrix.common.repayment.impl.StageInterestOneCapital;

/**
 * 还款方式工厂
 * 
 * @author rong yang
 *
 */
public class RepaymentFactory {
	private static Logger logger = LoggerFactory.getLogger(RepaymentFactory.class);

	public static RepaymentMode getRepaymentMode(int type) {
		RepaymentMode repaymentMode = null;
		switch (type) {
		case 1:// 等本等息
			repaymentMode = new AverageCapitalInterest();

			break;
		case 2:// 等额本息
			repaymentMode = new AverageCapitalPlusInterest();

			break;
		case 3:// 等额本金
			repaymentMode = new AverageCapital();

			break;
		case 4:// 分期还息到期还本(先息后本)
			repaymentMode = new StageInterestOneCapital();
			break;
		default:
			logger.error("系统暂时不支持还款方式：" + type);
			break;
		}

		return repaymentMode;
	}
	
	public static void main(String[] args) {
		RepaymentMode repaymentMode = RepaymentFactory.getRepaymentMode(4);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(RepaymentMode.PARAM_INSTALLMENT,3);
		params.put(RepaymentMode.PARAM_AMOUNT,new BigDecimal("10000"));
		params.put(RepaymentMode.PARAM_RATE,new BigDecimal("0.12"));
		
		repaymentMode.setParams(params);
		
		List<RepaymentSchedule> schedules = repaymentMode.calculate();
		
		
		Date valueDate = new Date();
		String format = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		DateTime dateTime = DateTimeFormat.forPattern(format).parseDateTime("2015-05-31");
		//DateTime dateTime = new DateTime(valueDate);
		
		//计算账单日
		for(int i = 0; i < schedules.size(); i++){
			schedules.get(i).setBillingDate(dateTime.plusMonths(i+1).toDate());
		}
		
	    System.out.println(Arrays.toString(schedules.toArray())); 
		
		
	}

}
