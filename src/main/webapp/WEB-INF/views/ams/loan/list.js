var grid_render_id = "#billTable";
var pager_render_id = "#billTablepager";
$(function() {
	$('#bizDateFrom').datetimepicker({format: 'YYYY-MM-DD HH:mm'}).next().on(ace.click_event, function(){
		$(this).prev().focus();
	});
	
	$('#bizDateTo').datetimepicker({format: 'YYYY-MM-DD HH:mm'}).next().on(ace.click_event, function(){
		$(this).prev().focus();
	});
	
	$(grid_render_id).jqGrid(
			{
				caption : "放款单列表",
				height : 'auto',
				rowNum : 15,
				rowList : [ 15, 25, 50, 100 ],
				url : '${ctx}/loan/bill/listData?opType=success',
				datatype : "json",
				colNames : [ 'ID','订单ID','贷款总期数', '起息日', '放款金额','支付方式','还款方式','利率','利息配比','服务配比','合同编号','客户手机号','数据状态','放款描述','订单创建时间','操作'],
				colModel : [ {name : 'id',index : 'id',hidden : true},
				             {name : 'orderId',index : 'orderId', width : 120},
				             {name : 'installment',index : 'installment', align:"right", width : 60},
				             {name : 'valueDate',index : 'valueDate', align:"center", width : 80},
				             {name : 'amount',index : 'amount', align:"right", width : 80},
				             {name : 'payWay',index : 'payWay', align:"center", width : 80,formatter: function (cellvalue, options, rowObject) {
				            	 if (cellvalue == 1) {
				            		 return "线上支付";
				            	 } else if (cellvalue == 2) {
				            		 return "线下支付";
				            	 }
				             }},
				             {name : 'repayment',index : 'repayment', align:"center", width : 80,formatter: function (cellvalue, options, rowObject) {
				            	 if (cellvalue == 1) {
				            		 return "等本等息";
				            	 } else if (cellvalue == 2) {
				            		 return "等额本息";
				            	 } else if (cellvalue == 3) {
				            		 return "等额本金";
				            	 } else if (cellvalue == 4) {
				            		 return "一次性还本付息";
				            	 }
				             }},
				             {name : 'rate',index : 'rate', align:"right", width : 80},
				             {name : 'interestRatio',index : 'interestRatio', align:"right", width : 80},
				             {name : 'feeRatio',index : 'feeRatio', align:"right", width : 80},
				             {name : 'contractNumber',index : 'contractNumber', align:"right", width : 120},
				             {name : 'mobilePhone',index : 'mobilePhone', width : 120},
				             {name : 'dataStatus',index : 'dataStatus', align:"center", width : 120,formatter: function (cellvalue, options, rowObject) {
				            	 if (cellvalue == 1) {
				            		 return "未打款待提交";
				            	 } else if (cellvalue == 2){
				            		 return "已打款待提交";
				            	 } else if (cellvalue == 3){
				            		 return "已打款已提交";
				            	 } else {
				            		 return "";
				            	 }
				             }},
				             {name : 'remark',index : 'remark',width : 80},
				             {name : 'bizDate',index : 'bizDate', align:"center", width : 120},
				             {name : 'id', index: 'id', align:"center",width : 150,formatter: function (cellvalue, options, rowObject) {
				            	 var actions = "<a href='javascript:void(0)'  onclick=\"view('" + cellvalue + "')\">查看</a>"
				            	 if (rowObject.dataStatus==1||rowObject.dataStatus==2) {
				            		 actions += "|<a href='javascript:void(0)'  onclick=\"submit1('" + cellvalue + "')\">提交</a>";
				            		 actions += "|<a href='javascript:void(0)'  onclick=\"edit('" + cellvalue  + "')\">编辑</a>";
				            		 actions += "|<a href='javascript:void(0)'  onclick=\"remove2('" + cellvalue + "')\">删除</a>";
				            	 }
				            	 if (rowObject.dataStatus==3) {
				            		 actions += "|<a href='javascript:void(0)'  onclick=\"repayment('" + rowObject.orderId + "')\">还款计划详情</a>"
				            	 }
				            	 return actions;
				             }}
				            ],
				viewrecords : true,
				pager : pager_render_id,
				altRows : true,
				sortname : "loanDate",
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
	//navButtons
	$(grid_render_id).jqGrid(
			'navGrid',
			pager_render_id,
			{ //navbar options
				alertcap : "消息",
				alerttext : "请您先选择要操作的记录！",
				edit : false,
				edittext : "修改",
				editicon : 'ace-icon fa fa-pencil blue',
				editfunc : edit,
				add : true,
				addtext : "新建",
				addicon : 'ace-icon fa fa-plus-circle purple',
				addfunc : addNew,
				del : false,
				deltext : "删除",
				delicon : 'ace-icon fa fa-trash-o red',
				delfunc : remove2,
				search : false,
				searchtext : "查询",
				searchicon : 'icon-search orange',
				refresh : true,
				refreshtext : "刷新",
				refreshicon : 'ace-icon fa fa-refresh green',
				view : true,
				viewtext : "查看",
				viewfunc : view,
				viewicon : 'ace-icon fa fa-search-plus grey',
			},
			{
			//  default settings for edit
			},
			{ //  default settings for add
			},
			{}, // delete instead that del:false we need this
			{ // search options
				caption : "高级查询",
				recreateForm : true,
				afterShowSearch : function(e) {
					var form = $(e[0]);
					form.closest('.ui-jqdialog').find('.ui-jqdialog-title')
							.wrap('<div class="widget-header" />')
					$.jqGridExt.setStyleSearchForm(form);
				},
				afterRedraw : function() {
					$.jqGridExt.setStyleSearchFilters($(this));
				},
				showQuery : false,
				multipleSearch : true
			},
			{}/* view parameters*/
			
	);
	
	function addNew() {
		$("#add_dialog_content").load('${ctx}/loan/bill/addNew');
		$("#add_dialog").modal("show");
	}

	//var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');
	$("#add_dialog_save").on('click', function() {
		if(!$("#addNew_form").valid()){
			return
		}
		$("#addNew_form").ajaxSubmit({
			dataType : 'json',
			success : function(data) {
				if ("success" == data.status) {
					$('#add_dialog').modal("hide");
					$(grid_render_id).jqGrid().trigger("reloadGrid");
				} else {
					bootbox.alert(data.msg);
				}
			},
			error : function(data){
			}
		});
	});
	
	$("#edit_dialog_save").on('click', function() {
		if(!$("#edit_form").valid()){
			return
		}
		$("#edit_form").ajaxSubmit({
			dataType : 'json',
			success : function(data) {
				if ("success" == data.status) {
					$('#edit_dialog').modal("hide");
					$(grid_render_id).jqGrid().trigger("reloadGrid");
				} else {
					bootbox.alert(data.msg);
				}
			}
		});
	});
	
})


function submit(id) {
	var selectedId = jQuery(grid_render_id).jqGrid('getGridParam','selrow');
	if(null == selectedId || "" == selectedId){
		bootbox.alert("请选择要提交的记录！");
		return;
	}
	bootbox.confirm("您确认要提交该贷款单信息吗?", function(result) {
		if (result) {
			$.get("${ctx}/loan/bill/submit/" + selectedId,
					function(data) {
						if ("success" == data.status) {
							$(grid_render_id).jqGrid().trigger("reloadGrid");
						} else if ("error" == data.status) {
							bootbox.alert(data.msg);
						}
					}, "json");
		}
	});
}

function submit1(id) {
	bootbox.confirm("您确认要提交该贷款单信息吗?", function(result) {
		if (result) {
			$.get("${ctx}/loan/bill/submit/" + id,
					function(data) {
				if ("success" == data.status) {
					bootbox.alert(data.msg);
					$(grid_render_id).jqGrid().trigger("reloadGrid");
				} else if ("error" == data.status) {
					bootbox.alert(data.msg);
				}
			}, "json");
		}
	});
}



function getQueryFilter() {
	var rules = '{"t":"i","f":"status","op":"lt","v":"4"},';
	var form = document.getElementById("product-form-search");
    for (var i = 0; i < form.elements.length; i++) {
        var e = form.elements[i];
        if("" == e.value){
    		continue;
    	}
        
        if(e.name == 'orderId'){
        	rules += '{"t":"s","f":"orderId","op":"like","v":"'+e.value+'"},';
		}else if(e.name == 'receiveCardNo'){
			rules += '{"t":"s","f":"receiveCardNo","op":"like","v":"'+e.value+'"},';
		}else if(e.name == 'mobilePhone'){
			rules += '{"t":"s","f":"mobilePhone","op":"like","v":"'+e.value+'"},';
		}else if(e.name == 'dataStatus'){
			rules += '{"t":"i","f":"dataStatus","op":"eq","v":"'+e.value+'"},';
		}else if(e.name == 'productType'){
			rules += '{"t":"i","f":"productType","op":"eq","v":"'+e.value+'"},';
		}else if(e.name == 'payWay'){
			rules += '{"t":"i","f":"payWay","op":"eq","v":"'+e.value+'"},';
		}else if(e.name == 'bizDate1'){
			rules += '{"t":"d","pattern":"yyyy-MM-dd HH:mm:ss","f":"bizDate","op":"gt","v":"'+e.value+':00"},';
		}else if(e.name == 'bizDate2'){
			rules += '{"t":"d","pattern":"yyyy-MM-dd HH:mm:ss","f":"bizDate","op":"le","v":"'+e.value+':59"},';
		}
    } 
    
    if(0 == rules.length){
    	return "";
    }
    rules = rules.substring(0,rules.length-1);
    return "{\"groupOp\":\"AND\",\"rules\":["+rules+"]}";
};

function search(){
	$(grid_render_id).jqGrid('setGridParam',{postData:{"filters":getQueryFilter()}}).trigger("reloadGrid");
}

function edit(id) {
	
	var rowObj = $(grid_render_id).jqGrid("getRowData",id);
	if("已打款已提交" == rowObj.dataStatus){
		bootbox.alert("该订单已提交，不能再修改！");
		return null;
	}
	
	$("#edit_dialog_content").load('${ctx}/loan/bill/edit?id=' + id);
	$("#edit_dialog").modal("show");
}
function view(id) {
	$("#view_dialog_content").load('${ctx}/loan/bill/view?id=' + id);
	$("#view_dialog").modal("show");
}

function repayment(id) {
	loadPage('${ctx}/repayment/bill/list/' + id);
}

function remove2(id) {
	var rowObj = $(grid_render_id).jqGrid("getRowData",id);
	if("已打款已提交" == rowObj.dataStatus){
		bootbox.alert("该订单已提交，不能删除！");
		return null;
	}
	
	bootbox.confirm("您确认要删除该贷款单吗?", function(result) {
		if (result) {
			$.get("${ctx}/loan/bill/remove/" + id,
					function(data) {
						if ("success" == data.status) {
							$(grid_render_id).jqGrid().trigger("reloadGrid");
						} else if ("error" == data.status) {
							bootbox.alert(data.msg);
						}
					}, "json");
		}
	});
}

function changeQueryCondition(input) {
	var value = $(input).val();
	$("#queryContent").attr("name",value);
}
