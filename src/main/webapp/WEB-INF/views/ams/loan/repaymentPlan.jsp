<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="page-header">
		<h1>
			<input id="back" class="back" type="button" value="返回"/> <small> <i class="ace-icon fa fa-angle-double-right"></i>
				订单号：${orderId }
			</small>
		</h1>
	</div>
	<form id="edit_form" class="form-horizontal" action="${ctx}/repayment/bill/saveExtraFee" method="post">
	<div class="row">
		<div class="col-xs-12">
			<div class="row">
				<div class="col-xs-12">
					<table id="simple-table"
						class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th class="center">分期数</th>
								<th>计划还款日</th>
								<th>应还本金</th>
								<th class="hidden-480">应还利息</th>

								<th>应还服务费</th>
								<th class="hidden-480">应还罚息</th>

								<th>应还滞纳金</th>
								<th>应还违约金</th>
								<th>额外费用</th>
								<th>应还合计</th>
								<th>剩余本金</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${bills }" var="bill">
							
								<tr>
									<td class="center">${bill.installmentNumber }</td>
	
									<td>${fn:substring(bill.shouldPayTime,0,10) }</td>
									<td>${bill.capital }</td>
									<td class="hidden-480">${bill.interest }</td>
									<td>${bill.serviceFee }</td>
	
									<td class="hidden-480">${bill.penaltyInterest }</td>
	
									<td>${bill.lateFee }</td>
									<td>${bill.penalty }</td>
									<td>
										<input name="extraFee_${bill.id }" value="${bill.extraFee }" number="true" onkeyup="recalculate(this)" />
										<input type="hidden" value="${bill.capital+bill.interest+bill.serviceFee+bill.penaltyInterest+bill.lateFee+bill.penalty }"/>
									</td>
									<td class="total">
										${bill.capital+bill.interest+bill.serviceFee+bill.penaltyInterest+bill.lateFee+bill.penalty+bill.extraFee }
									</td>
									<td>${bill.remainingCapital }</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
				<!-- /.span -->
			</div>
			<div class="col-md-12 form-actions center">
				<button type="button" class="btn btn-default back">取消</button>
		        <button id="edit_dialog_save" type="button" class="btn btn-primary">保存</button>
			</div>
		</div>
	</div>
	</form>
</body>
	<script type="text/javascript">
		function recalculate(input) {
			var extraFee = Number($(input).val());
			var total = Number($(input).next().val());
			total = total + extraFee;
			$(input).parent().next().html(total.toFixed(1));
		}
		
		$(function(){
			
			$(".back").click(function() {
				$("a[href='/loan/bill/list']","#sys-nav-list").click();
			});
			
			$("#edit_dialog_save").click(function() {
				$("#edit_form").ajaxSubmit({
					dataType : 'json',
					success : function(data) {
						if ("success" == data.status) {
							$("a[href='/loan/bill/list']","#sys-nav-list").click();
						} else {
							bootbox.alert(data.msg);
						}
					}
				});
			})
		})
	</script>
</html>