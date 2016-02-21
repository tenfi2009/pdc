<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<form id="rate_form" class="form-horizontal" action="${ctx}/loan/product/save" method="post">
	<input type="hidden" name="id" value="${rate.id}"/>
	<div class="form-group col-md-6">
		<label class="col-md-4 control-label no-padding-right" for="rate">利率：</label>
		<div class="col-md-8">
			<input type="text" id="rate" name="rate" maxlength="32"
				class="input-xlarge" value="${rate.rate}"/>
		</div>
	</div>
	<div class="form-group col-md-4">
		<label class="col-md-6 control-label no-padding-righ" for="repayment">
			默认还款方式： </label>
		<div class="col-md-6">
			<select id="repayment" name="repayment" class="select2">
				<option value="1">默认还款方1</option>
				<option value="2">默认还款方2</option>
			</select>
		</div>
	</div>
	<div class="form-group col-md-12">
		<label class="col-md-2 control-label no-padding-right" for="remark">备注：</label>
		<div class="col-md-10">
			<textarea class="form-control" id="remark" name="remark">${rate.remark }</textarea>
		</div>
	</div>
	
	<div class="col-md-12 form-actions center">
		<button class="btn btn-default" type="button">取消</button>
        <button class="btn btn-primary" type="button" id="edit_dialog_save1">保存</button>
	</div>
	
</form>

<div class="col-xs-12">
	<table id="paymentWayTable"></table>
</div>

<script type="text/javascript">
	var grid_render_id = "#paymentWayTable";
	$(function() {
		$(grid_render_id).jqGrid({
			caption : "支持还款方式列表",
			height : 'auto',
			rowNum : 5,
			rowList : [ 15, 25, 50, 100 ],
			//url : "${ctx}/loan/product/listData",
			datatype : "json",
			colNames : [ 'ID', '还款方式名称', '还款方式参数值', '是否默认' ],
			colModel : [ {
				name : 'id',
				index : 'id',
				hidden : true
			}, {
				name : 'repayment.name',
				index : 'repayment',
				width : 220
			}, {
				name : 'paramValue',
				index : 'paramValue',
				width : 400
			}, {
				name : 'isDefault',
				index : 'isDefault',
				width : 200
			} ],
			viewrecords : true,
			altRows : true,
			sortname : "createTime",
			autowidth : true,
			rownumbers : true,
			multiselect : false,
			//multikey: "ctrlKey",
			multiboxonly : true,
			jsonReader : {
				root : "result",
				page : "currPage",
				total : "totalPage",
				records : "totalCount"
			},
			loadComplete : function() {
				var table = this;
				setTimeout(function() {
					$.jqGridExt.setStyleCheckbox(table);
					$.jqGridExt.updateActionIcons(table);
					$.jqGridExt.updatePagerIcons(table);
					$.jqGridExt.enableTooltips(table);
				}, 0);
			}
		});
	})
</script>