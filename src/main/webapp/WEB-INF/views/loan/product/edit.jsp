<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<form id="edit_form" class="form-horizontal" action="${ctx}/loan/product/save" method="post">
	<input type="hidden" name="id" value="${product.id}"/>
	<div class="form-group col-md-4">
		<label class="col-md-4 control-label no-padding-right" for="category"><span style="color: red">*</span>产品分类：</label>
		<div class="col-md-8 input-group">
			<input type="hidden" id="category" name="category.id" value="17">
			<input class="form-control input-large" type="text" id="category" name="category.fullName" value="${product.category.fullName}" required />
			<span id="orgFullName_addon" class="input-group-addon"><i class="icon-search"></i></span>
		</div>
	</div>
	<div class="form-group col-md-4" >
		<label class="col-md-4 control-label no-padding-right" for="code"><span style="color: red">*</span>产品编码：</label>
		<div class="col-md-8">
			<input type="text" id="code" name="code" maxlength="32" value="${product.code }" required class="input-xlarge" />
		</div>
	</div>
	<div class="form-group col-md-4">
		<label class="col-md-4 control-label no-padding-right" for="name"><span style="color: red">*</span>产品名称：</label>
		<div class="col-md-8">
			<input type="text" id="name" name="name" maxlength="16" class="input-xlarge" value="${product.name }"  required/> 
		</div>
	</div>
	<div class="form-group col-md-8">
		<label style="width: 100px;" class="col-md-2 control-label no-padding-right" for="instalments">支持分期数：</label>
		<div class="col-md-10 no-padding-left">
			<input type="text" class="form-control" id="instalments" name="instalments" maxlength="64" value="${product.instalments }"/> 
		</div>
	</div>
	<div class="form-group col-md-4">
		<label class="col-md-4 control-label no-padding-right" for="unit">分期单位：</label>
		<div class="col-md-8">
			<input type="text" class="input-xlarge" id="unit" name="unit" value="${product.unit }"/> 
		</div>
	</div>
	<div class="form-group col-md-4">
		<label class="col-md-4 control-label no-padding-righ"> 币种： </label>
			<div class="col-md-8">
				<select id="currency" name="currency" class="select2">
					<option <c:if test="${product.currency == 1}">selected</c:if> value="1">人民币</option>
					<option <c:if test="${product.currency == 2}">selected</c:if> value="2">美元</option>
				</select>
			</div>
	</div>
	<div class="form-group col-md-4">
		<label class="col-md-4 control-label no-padding-right" for="minLimit">合同下限：</label>
		<div class="col-md-8">
			<input type="text" class="input-xlarge" id="minLimit" name="minLimit" value="${product.minLimit }"/> 
		</div>
	</div>
	<div class="form-group col-md-4">
		<label class="col-md-4 control-label no-padding-right" for="maxLimit">合同上限：</label>
		<div class="col-md-8">
			<input type="text" class="input-xlarge" id="maxLimit" name="maxLimit" value="${product.maxLimit }"/> 
		</div>
	</div>
	<div class="form-group col-md-12" >
		<label class="col-md-2 control-label no-padding-right" for="principalFormula"><span style="color: red">*</span>出借本金公式：</label>
		<div class="col-md-10">
			<input type="text" id="principalFormula" name="principalFormula" maxlength="512" class="form-control" value="${product.principalFormula }" required/>
		</div>
	</div>
	<div class="form-group col-md-12">
		<label class="col-md-2 control-label no-padding-right" for="dayPaymentWay">还款日确定方式：</label>
		<div class="radio">
			<label>
				<input id="dayPaymentWay" name="dayPaymentWay" type="radio" value="1" <c:if test="${product.dayPaymentWay == 1}">checked</c:if>/>
				<span class="lbl">起息日+分期</span>
			</label>
			<label>
				<input name="dayPaymentWay" type="radio" value="2" <c:if test="${product.dayPaymentWay == 2}">checked</c:if>/>
				<span class="lbl">自定义还款</span>
			</label>
		</div>
	</div>
	<div id="cusDayPaymentWay" <c:if test="${product.dayPaymentWay==2 }">class="show"</c:if> <c:if test="${product.dayPaymentWay==1 }">class="hide"</c:if> >
		<div class="form-group col-md-4">
			<label class="col-md-4 control-label no-padding-right" for="useableDayPayment">可用还款日：</label>
			<div class="col-md-8">
				<input type="text" class="input-xlarge" id="useableDayPayment" name="useableDayPayment" maxlength="64" value="${product.useableDayPayment }"/> 
			</div>
		</div>
		<div class="form-group col-md-4">
			<label class="col-md-4 control-label no-padding-right" for="firstPeriodWay">首期确定方式：</label>
			<div class="radio">
				<label>
					<input name="firstPeriodWay" type="radio" value="1" <c:if test="${product.firstPeriodWay == 1}">checked</c:if>/>
					<span class="lbl">靠上档</span>
				</label>
				<label>
					<input name="firstPeriodWay" type="radio" value="2" <c:if test="${product.firstPeriodWay == 2}">checked</c:if> />
					<span class="lbl">靠下档</span>
				</label>
			</div>
		</div>
		<div class="form-group col-md-4">
			<label class="col-md-4 control-label no-padding-right" for="firstPeriodInterestWay">首期计息方式：</label>
			<div class="radio">
				<label>
					<input name="firstPeriodInterestWay" type="radio" value="1" <c:if test="${product.firstPeriodInterestWay == 1}">checked</c:if>/>
					<span class="lbl">按期计算</span>
				</label>
				<label>
					<input name="firstPeriodInterestWay" type="radio" value="2" <c:if test="${product.firstPeriodInterestWay == 2}">checked</c:if> />
					<span class="lbl">按日计算</span>
				</label>
			</div>
		</div>
	</div>
	<div class="form-group col-md-6">
		<label class="col-md-4 control-label no-padding-right" for="saleTime">开卖时间：</label>
		<div class="col-md-8 input-group bootstrap-timepicker">
			<input id="saleTime" name="saleTime" type="text" class="form-control"  value="${fn:substring(product.saleTime, 0, 16)}"/>
			<span class="input-group-addon">
				<i class="fa fa-clock-o bigger-110"></i>
			</span>
		</div>
	</div>
	<div class="form-group col-md-6">
		<label class="col-md-4 control-label no-padding-right" for="offTime">截止时间：</label>
		<div class="col-md-8 input-group bootstrap-timepicker">
			<input id="offTime" name="offTime" type="text" class="form-control" value="${fn:substring(product.offTime, 0, 16)}"/>
			<span class="input-group-addon">
				<i class="fa fa-clock-o bigger-110"></i>
			</span>
		</div>
	</div>
	<div>
		<label style="width: 500px;" for="description">备注</label>
		<textarea class="form-control" id="description" name="description">${product.description }</textarea>
	</div>
</form>

<script type="text/javascript">
	$(function() {
		$("input[name='dayPaymentWay']").click(function(){
			if ($(this).val() == 2) {
				$("#cusDayPaymentWay").attr("class", "show");
			} else {
				$("#cusDayPaymentWay").attr("class", "hide");
				$("#useableDayPayment").val("");
				$("#cusDayPaymentWay :input:radio").each(function(){
					$(this).attr("checked",false);
				});
			}
			
		});
		
		$('#saleTime').datetimepicker({format: 'YYYY-MM-DD HH:mm'}).next().on(ace.click_event, function(){
			$(this).prev().focus();
		});
		
		$('#offTime').datetimepicker({format: 'YYYY-MM-DD HH:mm'}).next().on(ace.click_event, function(){
			$(this).prev().focus();
		});
		
		$("#addNew_form").validate({
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