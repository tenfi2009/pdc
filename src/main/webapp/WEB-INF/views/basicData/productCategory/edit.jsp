<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<form id="product_edit_form" class="form-horizontal" action="${ctx}/basicData/productCategory/save" method="post">
	<input type="hidden" name="id" value="${pro.id}"/>
	<input type="hidden" name="parent.id" value="${pro.parent.id}"/>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="form-input-readonly">上级产品分类：</label>
		<div class="col-sm-9">
			<input disabled="disabled" type="text" class="input-xlarge" id="form-input-readonly" value="${pro.parent.name }" /> 
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="code">产品分类编码：</label>
		<div class="col-sm-9">
			<input type="text" id="code" name="code" value="${pro.code }" class="input-xlarge" />
		</div>
	</div>

	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="name">产品分类名称：</label>
		<div class="col-sm-9">
			<input type="text" id="name" name="name" value="${pro.name }" class="input-xlarge" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="fullName">产品分类全称：</label>
		<div class="col-sm-9">
			<input type="text" id="fullName" name="fullName" value="${pro.fullName }" class="input-xlarge" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="sortNo">排序号：</label>
		<div class="col-sm-9">
			<input type="text" id="sortNo" name="sortNo" value="${pro.sortNo }" class="col-xs-10 col-sm-5" />
		</div>
	</div>
	<div>
		<label for="form-field-8">描述</label>
	</div>
</form>

<script type="text/javascript">
	$(function() {
		
	});
</script>