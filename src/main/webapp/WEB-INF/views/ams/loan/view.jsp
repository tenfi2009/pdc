<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<form id="edit_form" class="form-horizontal" action="${ctx}/loan/bill/save" method="post">
	<div class="row">
		<h4 class="header green clearfix col-md-12" >
			金融信息&nbsp;&nbsp;
		</h4>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right" for="productType">产品类型：</label>
		<div class="col-md-8">
			<c:choose>
				<c:when test="${bill.productType==1}">
					3C
				</c:when>
				<c:when test="${bill.productType==2}">
					青客
				</c:when>
				<c:when test="${bill.productType==3}">
					现金贷
				</c:when>
				<c:when test="${bill.productType==4}">
					车贷
				</c:when>
			</c:choose>
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right" for="installment">贷款总期数：</label>
		<div class="col-md-8">
			${bill.installment }
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right" for="valueDate">起息日：</label>
		<div class="col-md-8">
			<div class="input-group">
				${fn:substring(bill.valueDate,0,10) }
			</div>
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right" for="amount">放款金额：</label>
		<div class="col-md-8">
			<c:if test="${not empty bill.amount }">
				${bill.amount }元
			</c:if>
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right" for="repayment">还款方式：</label>
		<div class="col-md-8">
			<c:choose>
				<c:when test="${bill.repayment==1}">
					等本等息
				</c:when>
				<c:when test="${bill.repayment==2}">
				 	等额本息
				</c:when>
				<c:when test="${bill.repayment==3}">
				 	等额本金
				</c:when>
				<c:when test="${bill.repayment==4}">
				 	一次性还本付息
				</c:when>
				<c:when test="${bill.repayment==5}">
				 	分期还息到期还本
				</c:when>
			</c:choose>
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right" for="rate">利率：</label>
		<div class="col-md-8">
			${bill.rate }
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right" for="interestRatio">利息配比：</label>
		<div class="col-md-8">
			${bill.interestRatio }
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right" for="feeRatio">服务费配比：</label>
		<div class="col-md-8">
			${bill.feeRatio }
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right" for="remark">备注：</label>
		<div class="col-md-8">
			${bill.remark }
		</div>
	</div>
	<div class="row">
		<h4 class="header green clearfix col-md-12" >
			放款信息录入&nbsp;&nbsp;
		</h4>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right" for="receiveBank">收款方_开户银行：</label>
		<div class="col-md-8">
			${bill.receiveBank }
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right" for="city">城市：</label>
		<div class="col-md-8">
			${bill.city }
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right" for="code">收款方_开户支行：</label>
		<div class="col-md-8">
			${bill.receiveBranch }
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right" for="receiveCardNo">收款方_银行卡号：</label>
		<div class="col-md-8">
			${bill.receiveCardNo }
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right" for="receiveName">收款方_姓名：</label>
		<div class="col-md-8">
			${bill.receiveName }
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right" for="contractNumber">合同编号：</label>
		<div class="col-md-8">
			${bill.contractNumber }
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right" for="mobilePhone">客户手机号：</label>
		<div class="col-md-8">
			${bill.mobilePhone }
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right" for="productType">放款方式：</label>
		<div class="col-md-8">
			<c:choose>
				<c:when test="${bill.payWay==1}">
					线上支付
				</c:when>
				<c:when test="${bill.payWay==2}">
				 	线下支付
				</c:when>
			</c:choose>
		</div>
	</div>
	<div id="offLine" <c:if test="${bill.payWay==2 }">class="show"</c:if> <c:if test="${bill.payWay==1 }">class="hide"</c:if>>
		<div class="form-group col-md-6">
			<label class="col-md-4 control-label no-padding-right" for="receiveTime">到账时间：</label>
			<div class="col-md-8 input-group bootstrap-timepicker">
				${fn:substring(bill.receiveTime,0,16) }
			</div>
		</div>
		
		<div class="form-group col-md-6" >
			<label class="col-md-4 control-label no-padding-right" for="receiveAmount">到账金额：</label>
			<div class="col-md-8">
				<c:if test="${not empty bill.receiveAmount }">
					${bill.receiveAmount }元
				</c:if>
			</div>
		</div>
		<div class="form-group col-md-6">
			<label class="col-md-4 control-label no-padding-right" for="loanDate">放款时间：</label>
			<div class="col-md-8 input-group bootstrap-timepicker">
				${fn:substring(bill.loanDate,0,16) }
			</div>
		</div>
		<div class="form-group col-md-6" >
			<label class="col-md-4 control-label no-padding-right" for="accountNo">放款账号：</label>
			<div class="col-md-8">
				${bill.accountNo }
			</div>
		</div>
		<div class="form-group col-md-6" >
			<label class="col-md-4 control-label no-padding-right" for="tradeNumber">交易流水号：</label>
			<div class="col-md-8">
				${bill.tradeNumber }
			</div>
		</div>
	</div>
	<div class="row">
		<h4 class="header green clearfix col-md-12" >
			操作信息&nbsp;&nbsp;
		</h4>
	</div>
	<c:forEach items="${logs }" var="log">
	
		<div class="form-group col-md-4" >
			<label class="col-md-4 control-label no-padding-right" for="operator">操作人：</label>
			<div class="col-md-8">
				${log.operator }
			</div>
		</div>
		<div class="form-group col-md-4" >
			<label class="col-md-4 control-label no-padding-right" for="operationTime">操作时间：</label>
			<div class="col-md-8">
				${fn:substring(log.operationTime,0,19) }
			</div>
		</div>
		<div class="form-group col-md-4" >
			<label class="col-md-4 control-label no-padding-right" for="content">操作详情：</label>
			<div class="col-md-8">
				${log.content }
			</div>
		</div>
	</c:forEach>
	<div class="row">
	</div>
</form> 
