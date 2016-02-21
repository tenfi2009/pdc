<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="page-header">
	<h1>
		<input class="back" type="button" value="返回" />
	</h1>
	<br />
	<div class="row">
		<div class="col-xs-12">
			<div class="row">
				<div class="col-xs-12">
					<table id="simple-table"
						class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th class="center">订单号</th>
								<th>期数</th>
								<th>本金</th>
								<th>服务费</th>
								<th class="hidden-480">罚息</th>
								<th>应还时间</th>
								<th>当期应付</th>
								<th>实际已付</th>
								<th>还款状态</th>
							</tr>
						</thead>
						<tbody>

							<tr>
								<td class="center">${billDto.orderId }</td>
	
								<td>${billDto.installmentNumber }</td>
								<td>${billDto.capital }</td>
								<td>${billDto.serviceFee }</td>
								<td id="penaltyInterest">${billDto.penaltyInterest }</td>
								<td id="shouldPayTime">${fn:substring(billDto.shouldPayTime,0,10) }</td>
								<td>${billDto.payables }</td>
								<td>${billDto.payAmount }</td>
								<td><!--1 未还清，2：已还清 -->
									<c:choose>
										<c:when test="${billDto.status==1 }">未还清</c:when>
										<c:when test="${billDto.status==2 }">已还清</c:when>
										<c:otherwise></c:otherwise>
									</c:choose>
								</td>
							</tr>

						</tbody>
					</table>
				</div>
				<!-- /.span -->
			</div>
		</div>
	</div>
</div>


<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<form id="save_settleBill_form" class="form-horizontal" role="form" method="post" action="${ctx}/repayment/bill/save">
			<input type="hidden" value="${bill.id }" name="bill.id"/>
			<input type="hidden" value="${repaymentBill.id }" name="repaymentBill.id"/>
			<!-- #section:elements.form -->
			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right" for="repaymentAccount"> 打款账号： </label>
				<div class="col-sm-9">
					<input type="text" id="repaymentAccount" name="repaymentAccount" class="col-xs-10 col-sm-5" value="${repaymentBill.repaymentAccount }">
					<span>（若无，请填写“无”）</span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right" for="receiptTime"><span style="color: red">*</span> 打款时间： </label>
				<div class="col-sm-9">
					<div class="col-sm-5 input-group bootstrap-timepicker">
						<input type="text" id="receiptTime" data-date-format="yyyy-mm-dd" name="receiptTime" value="${fn:substring(repaymentBill.receiptTime,0,10) }" required class="input-xlarge form-control date-picker" />
						<span class="input-group-addon">
							<i class="fa fa-calendar bigger-110"></i>
						</span>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right" for="receiveAccount"><span style="color: red">*</span> 收款账号： </label>
				<div class="col-sm-9">
					<input type="text" id="receiveAccount" name="receiveAccount" class="col-xs-10 col-sm-5" required value="${repaymentBill.receiveAccount }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right" for="amount"><span style="color: red">*</span> 收款金额： </label>
				<div class="col-sm-9">
					<input type="text" id="amount" name="amount" class="col-xs-10 col-sm-5" required value="${repaymentBill.amount }" number="true" >
					<span>元</span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right" for="inAccountTime"><span style="color: red">*</span> 到账时间： </label>
				<div class="col-sm-9">
					<div class="col-sm-5 input-group bootstrap-timepicker">
						<input type="text" id="inAccountTime" data-date-format="yyyy-mm-dd" name="inAccountTime" value="${fn:substring(repaymentBill.inAccountTime,0,10) }" required class="input-xlarge form-control date-picker" />
						<span class="input-group-addon">
							<i class="fa fa-calendar bigger-110"></i>
						</span>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right" for=""> 账单应付金额： </label>
				<div class="col-sm-9">
					<input type="text" readonly="readonly" id="billAmount" value="${billDto.billAmount }" class="col-xs-10 col-sm-5" >
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right" for="settleDiffAmount"> 结算滞后差额： </label>
				<div class="col-sm-9">
					<input type="text" id="settleDiffAmount" readonly="readonly" name="settleDiffAmount" class="col-xs-10 col-sm-5" value="${bill.settleDiffAmount }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right" for="coupon"> 优惠券冲抵金额： </label>
				<div class="col-sm-9">
					<input type="text" id="coupon" name="coupon" class="col-xs-10 col-sm-5" value="${bill.coupon }">
					<span>元</span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right" for="extraIncome"> 营业外收入： </label>
				<div class="col-sm-9">
					<input type="text" id="extraIncome" name="extraIncome" class="col-xs-10 col-sm-5" value="${bill.extraIncome }">
					<span>元</span>
				</div>
			</div>

			<div class="clearfix form-actions">
				<div class="col-md-offset-3 col-md-9">
					<button class="btn btn-info" type="button" id="saveRepaymentSettleBill">
						<i class="ace-icon fa fa-check bigger-110"></i> 保存
					</button>

					&nbsp; &nbsp; &nbsp;
					<button class="btn back" type="reset">
						<i class="ace-icon fa fa-undo bigger-110"></i> 取消
					</button>
				</div>
			</div>
		</form>
	</div>
	<!-- PAGE CONTENT ENDS -->
	<!-- /.col -->
</div>
<script	type="text/javascript">
$(function() {
	$(".back").click(function() {
		$("a[href='/repayment/bill/list']","#sys-nav-list").click();
	});
	
	$('.date-picker').datepicker({
		autoclose: true,
		todayHighlight: true
	})
	
	$("#receiptTime").change(function() {
		var aj = $.ajax( {    
		    url:'${ctx}/repayment/bill/calculatePenalty',// 跳转到 action    
		    data:{    
		    	valueDate : "${fn:substring(billDto.shouldPayTime,0,10) }",    
		    	payDate : $("#receiptTime").val(),    
		    	penalty : "${billDto.penaltyInterest}",    
		    	capitalFee : "${billDto.capital+billDto.serviceFee }", 
		    	billAmount : "${billDto.billAmount }"
		    },    
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) { 
		    	$("#settleDiffAmount").val(data.data.dtPenalty);
		    	$("#billAmount").val(data.data.billAmount);
		     },    
		     error : function() {    
		    	 bootbox.alert(data.msg);    
		     }    
		});
	});
	
	$("#saveRepaymentSettleBill").on('click', function() {
		if(!$("#save_settleBill_form").validate()){
			return
		}
		$("#save_settleBill_form").ajaxSubmit({
			dataType : 'json',
			success : function(data) {
				if ("success" == data.status) {
					$("a[href='/repayment/bill/list']","#sys-nav-list").click();
				} else {
					bootbox.alert(data.msg);
				}
			}
		});
	});
	
	$("#save_settleBill_form").validate({
		rules: {
		    	amount: {
		   			min: 0
		    	},
		    	coupon: {
		    		number : true,
		   			min: 0
		    	},
		    	extraIncome: {
		    		number : true,
		   			min: 0
		    	}
		},
		errorElement: 'div',
		errorClass: 'help-block',
		focusInvalid: true,
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
})
</script>