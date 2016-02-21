<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<form id="edit_form" class="form-horizontal" action="${ctx}/loan/bill/failSave" method="post">
	<input type="hidden" name="id" value="${bill.id}"/>
	<div class="row">
		<h4 class="header green clearfix col-md-12" >
			产品信息&nbsp;&nbsp;
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
			${bill.amount }元
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
			交易信息&nbsp;&nbsp;
		</h4>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right" for="receiveBank"><span style="color: red">*</span>收款方_开户银行：</label>
		<div class="col-md-8">
			<input type="text" id="receiveBank" name="receiveBank" maxlength="254" required class="input-xlarge"  value="${bill.receiveBank }"/>
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right" for="city"><span style="color: red">*</span>城市：</label>
		<div class="col-md-8">
			<input type="text" id="city" name="city" maxlength="50" required class="input-xlarge" value="${bill.city }"/>
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right" for="code"><span style="color: red">*</span>收款方_开户支行：</label>
		<div class="col-md-8">
			<input type="text" id="receiveBranch" name="receiveBranch" maxlength="254" required class="input-xlarge" value="${bill.receiveBranch }"/>
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right" for="receiveCardNo"><span style="color: red">*</span>收款方_银行卡号：</label>
		<div class="col-md-8">
			<input type="text" id="receiveCardNo" name="receiveCardNo" maxlength="32" required class="input-xlarge" value="${bill.receiveCardNo }"/>
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right" for="receiveName"><span style="color: red">*</span>收款方_姓名：</label>
		<div class="col-md-8">
			<input type="text" id="receiveName" name="receiveName" maxlength="64" required class="input-xlarge" value="${bill.receiveName }"/>
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right" for="contractNumber"><span style="color: red">*</span>合同编号：</label>
		<div class="col-md-8">
			<input type="text" id="contractNumber" name="contractNumber" required class="input-xlarge" value="${bill.contractNumber }"/>
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right" for="mobilePhone"><span style="color: red">*</span>客户手机号：</label>
		<div class="col-md-8">
			<input type="text" id="mobilePhone" name="mobilePhone" minlength="11" maxlength="24" required class="input-xlarge" value="${bill.mobilePhone }"/>
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right" for="payWay"><span style="color: red">*</span>放款方式：</label>
		<div class="col-md-8">
			<select id="payWay"  name="payWay" required class="input-xlarge" >
				<option value="2" >线下支付</option>
			</select>
		</div>
	</div>
	<div id="offLine">
		<div class="form-group col-md-6">
			<label class="col-md-4 control-label no-padding-right" for="receiveTime"><span style="color: red">*</span>到账时间：</label>
			<div class="col-md-8 input-group bootstrap-timepicker">
				<input id="receiveTime" name="receiveTime" type="text" class="form-control" required value="${fn:substring(bill.receiveTime,0,16) }"/>
				<span class="input-group-addon">
					<i class="fa fa-clock-o bigger-110"></i>
				</span>
			</div>
		</div>
		
		<div class="form-group col-md-6" >
			<label class="col-md-4 control-label no-padding-right" for="receiveAmount"><span style="color: red">*</span>到账金额：</label>
			<div class="col-md-8">
				<input type="text" id="receiveAmount" name="receiveAmount" number="true" required class="input-xlarge" value="${bill.receiveAmount }" style="width:90%"/>
				<span>元</span>
			</div>
		</div>
		<div class="form-group col-md-6">
			<label class="col-md-4 control-label no-padding-right" for="loanDate"><span style="color: red">*</span>放款时间：</label>
			<div class="col-md-8 input-group bootstrap-timepicker">
				<input id="loanDate" name="loanDate" type="text" class="form-control" required value="${fn:substring(bill.loanDate,0,16) }"/>
				<span class="input-group-addon">
					<i class="fa fa-clock-o bigger-110"></i>
				</span>
			</div>
		</div>
		<div class="form-group col-md-6" >
			<label class="col-md-4 control-label no-padding-right" for="accountNo"><span style="color: red">*</span>放款账号：</label>
			<div class="col-md-8">
				<input type="text" id="accountNo" name="accountNo" maxlength="32" required class="input-xlarge" value="${bill.accountNo }"/>
			</div>
		</div>
		<div class="form-group col-md-6" >
			<label class="col-md-4 control-label no-padding-right" for="tradeNumber"><span style="color: red">*</span>交易流水号：</label>
			<div class="col-md-8">
				<input type="text" id="tradeNumber" name="tradeNumber" maxlength="128" required class="input-xlarge" value="${bill.tradeNumber }"/>
			</div>
		</div>
	</div>
	<div class="row">
	</div>
</form>

<script type="text/javascript">
	$(function() {
		
		$('.bootstrap-timepicker').datetimepicker({format: 'YYYY-MM-DD HH:mm'}).next().on(ace.click_event, function(){
			$(this).prev().focus();
		});

		$("#edit_form").validate({
			
			rules: {
		    	receiveAmount: {
		    		min: 0
		    	}
			},
			errorElement: 'div',
			errorClass: 'help-block',
			focusInvalid: false,
			invalidHandler: function (event, validator) { //display error alert on form submit   
				$('.alert-danger', $('.login-form')).show();
			},
	
			highlight: function (e) {
				$(e).closest('.form-group').removeClass('has-info').addClass('has-error');
			},
	
			success: function (e) {
				$(e).closest('.form-group').removeClass('has-error').addClass('has-info');
				$(e).remove();
			},
	
			errorPlacement: function (error, element) {
				if(element.is(':checkbox') || element.is(':radio')) {
					var controls = element.closest('div[class*="col-"]');
					if(controls.find(':checkbox,:radio').length > 1) controls.append(error);
					else error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
				}
				else if(element.is('.select2')) {
					error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
				}
				else if(element.is('.chosen-select')) {
					error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)'));
				}
				else error.insertAfter(element.parent());
			},
	
			submitHandler: function (form) {
			}
		});
	});
</script>