package com.matrix.ams.paycenter.service.impl;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bill99.seashell.domain.dto.complatible.BankResponseBean;
import com.bill99.seashell.domain.dto.complatible.QueryResponseBean;
import com.matrix.ams.loan.model.LoanBill;
import com.matrix.ams.paycenter.model.PayMoneyOrder;
import com.matrix.ams.paycenter.model.PayResult;
import com.matrix.ams.paycenter.model.PayResultEnum;
import com.matrix.ams.paycenter.model.QueryPayResult;
import com.matrix.ams.paycenter.model.QueryPayResultEnum;
import com.matrix.ams.paycenter.service.PayMoneyFacadeService;
import com.renren.fenqi.bill99.pay.Bill99Query;

@Service
public class PayMoneyFacadeServiceImpl implements PayMoneyFacadeService {

	private static Logger logger = LoggerFactory.getLogger(PayMoneyFacadeServiceImpl.class);

	public PayResult submitPayMoney(LoanBill loanBill) {
		PayMoneyOrder order = new PayMoneyOrder();
		order.setAmount(loanBill.getAmount().doubleValue());
		order.setTradeOrderId(loanBill.getId());
		order.setBankCity(loanBill.getCity()); //城市信息
		order.setBankName(loanBill.getReceiveBank());
		order.setOpenBank(loanBill.getReceiveBranch());
		order.setUserName(loanBill.getReceiveName());
		order.setBankCard(loanBill.getReceiveCardNo());

		return payMoney(order);
	}

	/**
	 * 打款函数
	 * 
	 * @param oneOrder
	 * @return
	 */
	private PayResult payMoney(PayMoneyOrder oneOrder) {

		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.DOWN);
		String amount = df.format(oneOrder.getAmount());
		String tradeOrderId = oneOrder.getTradeOrderId();
		String bankCity = oneOrder.getBankCity();
		String bankName = oneOrder.getBankName();
		String userName = oneOrder.getUserName();
		String bankCard = oneOrder.getBankCard();
		String kaihuhang = oneOrder.getOpenBank();
		logger.info("人人分期放款：\t付款金额：" + amount + " 订单id：" + tradeOrderId + "城市:" + bankCity + " 银行名称: " + bankName + " 用户名：" + userName + " 卡号：" + bankCard);
		
		BankResponseBean response = null;
		//暂时不放开
		//Bill99Pay.doPay(bankCity, bankName, kaihuhang, userName, bankCard, amount, "人人分期打款", tradeOrderId);
		
		if (response == null) {
			logger.info("人人分期放款：\t 订单id：【" + tradeOrderId + "】 没有收到响应！");
			return new PayResult(PayResultEnum.RESPONSE_NULL);
		}

		logger.info("人人分期放款： 订单id：【" + tradeOrderId + "】 返回信息:\t 状态码：" + response.getFailureCause() + "\t 描述："
				+ ErrorDic.getErrorMsg(response.getFailureCause()));
		
		PayResult rs = new PayResult(response.getFailureCause(), ErrorDic.getErrorMsg(response.getFailureCause()));
		 rs.getOtherInfo().put("dealCharge", response.getDebitCharge());
		 rs.getOtherInfo().put("dealId", response.getDealId());
		 rs.getOtherInfo().put("amount", response.getAmount());
		 
		 return rs;
	}

	/**
	 * 查询订单的付款状态
	 * 
	 * @param orderId
	 * @return
	 */
	public QueryPayResult queryPayResult(String orderId) {
		QueryResponseBean response = Bill99Query.doQuery(null, null, "0", "bankPay", orderId);
		if (response == null) {
			return new QueryPayResult(QueryPayResultEnum.QUERY_NULL);
		}

		logger.info("查询订单的状态：\t orderId:" + orderId + "|当前返回状态：" + response.getDealStatus() + ", failureCouse:" + response.getFailureCause());
		// 付款成功
		if (("111").equals(response.getDealStatus())) {
			return new QueryPayResult(QueryPayResultEnum.SUCCESS);
		}
		// 付款失败
		if (("112").equals(response.getDealStatus()) || ("114").equals(response.getDealStatus())) {
			return new QueryPayResult(QueryPayResultEnum.PAY_FAIL.reCode(), response.getFailureCause());
		}
		return new QueryPayResult(QueryPayResultEnum.DEALING);
	}
}
