<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="row">
	<div class="col-xs-12">
		<div class="widget-box">
			<div class="widget-header widget-header-small">
				<h5 class="lighter">贷款单查询</h5>
			</div>
			<div class="widget-body">
				<div class="widget-main">
					<form id="product-form-search" class="form-search">
					<fieldset>
					
						<label class="control-label bolder blue">查询条件：</label>
						<label>
							<input name="queryCondition" checked="checked" value="orderId" onclick="changeQueryCondition(this)" type="radio" class="ace" />
							<span class="lbl"> 订单号</span>
						</label>
						<label>
							<input name="queryCondition" value="mobilePhone" onclick="changeQueryCondition(this)" type="radio" class="ace" />
							<span class="lbl"> 客户手机号</span>
						</label>
						<label>
							<input name="queryCondition" value="receiveCardNo" onclick="changeQueryCondition(this)" type="radio" class="ace" />
							<span class="lbl"> 收款卡号</span>
						</label>
						<label>
							<span class="lbl"> 查询内容：</span>
							<input type="text" id ="queryContent" name ="orderId"/>&nbsp;&nbsp;
						</label>
					
						<label>订单创建时间：</label>
						<input type="text" id="bizDateFrom" name="bizDate1"/>&nbsp;&nbsp;至
						<label></label>
						<input type="text" id="bizDateTo" name="bizDate2"/>&nbsp;&nbsp;
						<label>产品类型：</label>
						<select name="productType">
							<option value=""></option>
							<option value="1" >3C</option>
							<option value="2" >青客</option>
							<option value="3" >现金贷</option>
							<option value="4" >车贷</option>
						</select>&nbsp;&nbsp;
						<label>数据状态</label>
						<select name="dataStatus">
							<option value=""></option>
							<option value="1">未打款待提交</option>
							<option value="2">已打款待提交</option>
							<option value="3">已打款已提交</option>
						</select>&nbsp;&nbsp;
						
						<button onclick="search();" type="button" class="btn btn-purple btn-sm">
							查询 <i class="icon-search icon-on-right bigger-110"></i>
						</button>							
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="col-xs-12">
		<table id="billTable"></table>
		<div id="billTablepager"></div>
	</div>
	<!-- /.col -->
</div>
<!-- /.row -->

<!-- 修改贷款单 -->
<div class="modal fade" id="edit_dialog" tabindex="-1" role="dialog" aria-labelledby="edit_dialog_title" aria-hidden="true">
  <div class="modal-dialog" style="width: 970px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="edit_dialog_title">修改-贷款单</h4>
      </div>
      <div id="edit_dialog_content" class="modal-body">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button id="edit_dialog_save" type="button" class="btn btn-primary">保存</button>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="view_dialog" tabindex="-1" role="dialog" aria-labelledby="view_dialog_title" aria-hidden="true">
  <div class="modal-dialog" style="width: 970px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="view_dialog_title">查看-贷款单</h4>
      </div>
      <div id="view_dialog_content" class="modal-body">
      </div>
    </div>
  </div>
</div>

<script type="text/javascript" charset="utf-8">
<%@ include file="failList.js" %>
</script>