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
		<label class="col-md-4 control-label no-padding-right">订单号：</label>
		<div class="col-md-8" style="padding-top: 7px;">
			<label>${bill.orderId }</label>
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right">期数：</label>
		<div class="col-md-8" style="padding-top: 7px;">
			<label>${bill.installmentNumber }</label>
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right">本金：</label>
		<div class="col-md-8" style="padding-top: 7px;">
			<label>${bill.capital }元</label>
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right">服务费：</label>
		<div class="col-md-8" style="padding-top: 7px;">
			<label>
				<c:if test="${not empty bill.serviceFee }">
					${bill.serviceFee }元
				</c:if>
			</label>
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right">罚息：</label>
		<div class="col-md-8" style="padding-top: 7px;">
			<label>
				<c:if test="${not empty bill.penaltyInterest }">
					${bill.penaltyInterest }元
				</c:if>
			</label>
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right">应还时间：</label>
		<div class="col-md-8" style="padding-top: 7px;">
			<label>${fn:substring(bill.shouldPayTime,0,10) }</label>
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right">当期应付：</label>
		<div class="col-md-8" style="padding-top: 7px;">
			<label>
				<c:if test="${not empty settleBillDTO.billAmount }">
					${settleBillDTO.billAmount }元
				</c:if>
			</label>
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right">实际已付：</label>
		<div class="col-md-8" style="padding-top: 7px;">
			<label>
				<c:if test="${not empty bill.payAmount }">
					${bill.payAmount }元
				</c:if>
			</label>
		</div>
	</div>
	<div class="row">
		<h4 class="header green clearfix col-md-12" >
			结算详情&nbsp;&nbsp;
		</h4>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right">打款账号：</label>
		<div class="col-md-8" style="padding-top: 7px;">
			<label>${repaymentBill.repaymentAccount }</label>
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right">打款时间：</label>
		<div class="col-md-8" style="padding-top: 7px;">
			<label>${fn:substring(repaymentBill.receiptTime,0,10) }</label>
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right">收款帐号：</label>
		<div class="col-md-8" style="padding-top: 7px;">
			<label>${repaymentBill.receiveAccount }</label>
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right">收款金额：</label>
		<div class="col-md-8" style="padding-top: 7px;">
			<label>
				<c:if test="${not empty repaymentBill.amount }">
					${repaymentBill.amount }元
				</c:if>
			</label>
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right">到账时间：</label>
		<div class="col-md-8" style="padding-top: 7px;">
			<label>${fn:substring(repaymentBill.inAccountTime,0,10) }</label>
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right">账单应付金额：</label>
		<div class="col-md-8" style="padding-top: 7px;">
			<label>
				<c:if test="${not empty settleBillDTO.billAmount }">
					${settleBillDTO.billAmount }元
				</c:if>
			</label>
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right">结算滞后差额：</label>
		<div class="col-md-8" style="padding-top: 7px;">
			<label>
				<c:if test="${not empty bill.settleDiffAmount }">
					${bill.settleDiffAmount }元
				</c:if>
			</label>
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right">优惠券冲抵金额：</label>
		<div class="col-md-8" style="padding-top: 7px;">
			<label>
				<c:if test="${not empty bill.coupon }">
					${bill.coupon }元
				</c:if>
			</label>
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right">营业外收入：</label>
		<div class="col-md-8" style="padding-top: 7px;">
			<label>
				<c:if test="${not empty bill.extraIncome }">
					${bill.extraIncome }元
				</c:if>
			</label>
		</div>
	</div>
	<div class="row">
		<h4 class="header green clearfix col-md-12" >
			操作信息&nbsp;&nbsp;
		</h4>
	</div>
	<c:forEach items="${logs }" var="log">
		<div class="form-group col-md-4" >
			<label class="col-md-4 control-label no-padding-right">操作人：</label>
			<div class="col-md-8" style="padding-top: 7px;">
				<label>${log.operator }</label>
			</div>
		</div>
		<div class="form-group col-md-4" >
			<label class="col-md-4 control-label no-padding-right">操作时间：</label>
			<div class="col-md-8" style="padding-top: 7px;">
				<label>${fn:substring(log.operationTime,0,19) }</label>
			</div>
		</div>
		<div class="form-group col-md-4" >
			<label class="col-md-4 control-label no-padding-right">操作详情：</label>
			<div class="col-md-8" style="padding-top: 7px;">
				<label>${log.content }</label>
			</div>
		</div>
	</c:forEach>
	
	<div class="row">
	</div>
</form> 
