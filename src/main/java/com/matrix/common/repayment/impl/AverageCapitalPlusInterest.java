package com.matrix.common.repayment.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.matrix.common.repayment.RepaymentMode;
import com.matrix.common.repayment.RepaymentSchedule;

/**
 * 等额本息还款方式
 * @author rongyang
 *
 */
public class AverageCapitalPlusInterest extends RepaymentMode {

	public AverageCapitalPlusInterest() {
		
	}

	public AverageCapitalPlusInterest(Map<String, Object> params) {
		
		super(params);
		
	}

	public List<RepaymentSchedule> calculate() {
		
		//等额本息计算公式：〔贷款本金×月利率×（1＋月利率）＾还款月数〕÷〔（1＋月利率）＾还款月数－1〕
		
		//计算 （1＋月利率）＾还款月数 为中间结果
		BigDecimal temp = rate.add(BigDecimal.ONE).pow(installment);
		
		//每月还款额
		BigDecimal m = amount.multiply(rate).multiply(temp).divide(temp.subtract(BigDecimal.ONE), DEFAULT_SCALE,RoundingMode.HALF_UP);
		
		RepaymentSchedule current = null;//当期
		
		RepaymentSchedule previous = null;//上期
		
		List<RepaymentSchedule> schedules = new ArrayList<RepaymentSchedule>(installment);
		
		for(int i = 0 ; i < installment; i++){
			current = new RepaymentSchedule();
			
			current.setInstallmentNo(i+1);
			current.setSumCapitalInterest(m);
			
			if(0 == i){
				//首期
				current.setInterest(amount.multiply(rate).setScale(DEFAULT_SCALE,RoundingMode.HALF_UP));
				current.setCapital(m.subtract(current.getInterest()));
				
				current.setRemainingCapital(amount.subtract(current.getCapital()));
				current.setTotalCapital(current.getCapital());
				current.setTotalInterest(current.getInterest());
			}else if(installment-1 == i){
				//末期
				previous = schedules.get(i-1);
				
				current.setInterest(previous.getRemainingCapital().multiply(rate).setScale(DEFAULT_SCALE,RoundingMode.HALF_UP));
				current.setCapital(previous.getRemainingCapital());
				
				current.setRemainingCapital(BigDecimal.ZERO);
				
				current.setTotalCapital(amount);
				current.setTotalInterest(previous.getTotalInterest().add(current.getInterest()));
				
				//修正本息合计
				current.setSumCapitalInterest(current.getCapital().add(current.getInterest()));
			}else{
				//上期的剩余应收本金
				previous = schedules.get(i-1);
				
				current.setInterest(previous.getRemainingCapital().multiply(rate).setScale(DEFAULT_SCALE,RoundingMode.HALF_UP));
				current.setCapital(m.subtract(current.getInterest()));
				
				current.setRemainingCapital(previous.getRemainingCapital().subtract(current.getCapital()));
				
				current.setTotalCapital(previous.getTotalCapital().add(current.getCapital()));
				current.setTotalInterest(previous.getTotalInterest().add(current.getInterest()));
			}
			
			schedules.add(i,current);
		}
		return schedules;
	}
}
