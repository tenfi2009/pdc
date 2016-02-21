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
				caption : "正在还款列表",
				height : 'auto',
				rowNum : 15,
				rowList : [ 15, 25, 50, 100 ],
				url : '${ctx}/repayment/bill/listData',
				datatype : "json",
				colNames : [ 'ID','订单号','期数', '本金', '服务费','罚息','应还时间','当期应付','实际已付','账单应付金额','结算滞后差额','优惠券冲抵金额','还款状态','操作'],
				colModel : [ {name : 'id',index : 'id',hidden : true},
				             {name : 'orderId',index : 'orderId',width : 120},
				             {name : 'installmentNumber',index : 'installmentNumber', align:"right", width : 60},
				             {name : 'capital',index : 'capital',align:"right",width : 80},
				             {name : 'serviceFee',index : 'serviceFee', align:"right", width : 80},
				             {name : 'penaltyInterest',index : 'penaltyInterest', align:"right", width : 80},
				             {name : 'shouldPayTime',index : 'shouldPayTime',align:"center", width : 80},
				             {name : 'payables',index : 'payables', align:"right", width : 80},
				             {name : 'payAmount',index : 'payAmount', align:"right", width : 80},
				             {name : 'billAmount',index : 'billAmount', align:"right", width : 80},
				             {name : 'settleDiffAmount',index : 'settleDiffAmount', align:"right", width : 100},
				             {name : 'coupon',index : 'coupon', align:"right", width : 100},
				             {name : 'status',index : 'status', align:"center", width : 80,formatter: function (cellvalue, options, rowObject) {
				            	 if (cellvalue == 1) {
				            		 return "未还清";
				            	 } else if (cellvalue == 2){
				            		 return "已还清";
				            	 } else {
				            		 return "";
				            	 }
				             }},
				             {name : 'id', index: 'id', align:"center",width : 150,formatter: function (cellvalue, options, rowObject) {
				            	 var actions = "<a href='javascript:void(0)'  onclick=\"edit('" + cellvalue + "')\">当前清算</a>"
				            	 actions += "|<a href='javascript:void(0)'  onclick=\"edit('" + cellvalue + "')\">编辑</a>"
				            	 actions += "|<a href='javascript:void(0)'  onclick=\"view('" + cellvalue + "')\">查看</a>"
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
				add : false,
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
				view : false,
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
					$(grid_render_id).jqGrid().trigger("reloadGrid");
				} else if ("error" == data.status) {
					bootbox.alert(data.msg);
				}
			}, "json");
		}
	});
}



function getQueryFilter() {
	var rules = '';
	var form = document.getElementById("settleBill-form-search");
    for (var i = 0; i < form.elements.length; i++) {
        var e = form.elements[i];
        if("" == e.value){
    		continue;
    	}
        
        if(e.name == 'queryContent'){
			rules += '"queryContent":"'+e.value+'",';
		}else if(e.name == 'status'){
			rules += '"status":"'+e.value+'",';
		}else if(e.name == 'bizDate1'){
			rules += '"bizDateFrom":"'+e.value+'",';
		}else if(e.name == 'bizDate2'){
			rules += '"bizDateTo":"'+e.value+'",';
		}
    } 
    
    rules += '"queryCondition":"'+$('input:radio[name="queryCondition"]:checked',"#settleBill-form-search").val()+'"';
    
    return "{"+rules+"}";
};

function search(){
	$(grid_render_id).jqGrid('setGridParam',{postData:{"filters":getQueryFilter()}}).trigger("reloadGrid");
}

function edit(id) {
	loadPage('${ctx}/repayment/bill/edit?id=' + id);
}
function view(id) {
	$("#view_dialog_content").load('${ctx}/repayment/bill/view?id=' + id);
	$("#view_dialog").modal("show");
}

function repayment(id) {
	loadPage('${ctx}/repayment/bill/list/' + id);
}

function remove2(id) {
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
