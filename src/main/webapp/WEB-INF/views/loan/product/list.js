var grid_render_id = "#productTable";
var pager_render_id = "#productTablepager";
$(function() {
	$(grid_render_id).jqGrid(
			{
				caption : "贷款产品列表",
				height : 'auto',
				rowNum : 15,
				rowList : [ 15, 25, 50, 100 ],
				url : "${ctx}/loan/product/listData",
				datatype : "json",
				colNames : [ 'ID', '产品分类', '产品编码','产品名称','支持分期数', '状态', '是否配置','创建时间','操作'],
				colModel : [ {name : 'id',index : 'id',hidden : true},
				             {name : 'category.fullName',index : 'category',width : 120},
				             {name : 'code',index : 'code',width : 80},
				             {name : 'name',index : 'name',width : 80},
				             {name : 'instalments',index : 'instalments',width : 120},
				             {name : 'status',index : 'status',width : 60},
				             {name : 'config',index : 'config',width : 60},
				             {name : 'createTime',index : 'createTime',width : 120},
				             {name : 'id', index: 'id', align:"center",width : 120,formatter: function (cellvalue, options, rowObject) {
				            	 var actions = "<a href='javascript:void(0)'  onclick=\"config('" + cellvalue + "')\">配置</a>"
				            	 actions += "|<a href='javascript:void(0)'  onclick=\"publish('" + cellvalue + "')\">发布</a>"
				            	 actions += "|<a href='javascript:void(0)'  onclick=\"setInvalid('" + cellvalue + "')\">停用</a>"
				            	 return actions;
				             }}
				            ],
				viewrecords : true,
				pager : pager_render_id,
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
	//navButtons
	$(grid_render_id).jqGrid(
			'navGrid',
			pager_render_id,
			{ //navbar options
				alertcap : "消息",
				alerttext : "请您先选择要操作的记录！",
				edit : true,
				edittext : "修改",
				editicon : 'ace-icon fa fa-pencil blue',
				editfunc : edit,
				add : true,
				addtext : "新建",
				addicon : 'ace-icon fa fa-plus-circle purple',
				addfunc : addNew,
				del : true,
				deltext : "删除",
				delicon : 'ace-icon fa fa-trash-o red',
				delfunc : remove,
				search : false,
				searchtext : "查询",
				searchicon : 'icon-search orange',
				refresh : true,
				refreshtext : "刷新",
				refreshicon : 'ace-icon fa fa-refresh green',
				view : false,
				viewtext : "查看",
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
			
	).jqGrid('navButtonAdd',pager_render_id,{
		   caption:"提交", 
		   title : "提交产品信息",
		   buttonicon:"fa fa-check-circle", 
		   onClickButton: submit,
		   position:"before refresh_userTable"
		});
	
	function addNew() {
		$("#add_dialog_content").load('${ctx}/loan/product/addNew');
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
	
	function edit(id) {
		$("#edit_dialog_content").load('${ctx}/loan/product/edit?id=' + id);
		$("#edit_dialog").modal("show");
	}
	
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
	
	function remove(id) {
		bootbox.confirm("您确认要删除该产品吗?", function(result) {
			if (result) {
				$.get("${ctx}/loan/product/remove/" + id,
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
})

function config(id) {
//	bootbox.confirm("您确认要配置该产品吗?", function(result) {
//		if (result) {
//			$.get("${ctx}/loan/product/config/" + id,
//					function(data) {
//						if ("success" == data.status) {
//							$(grid_render_id).jqGrid().trigger("reloadGrid");
//						} else if ("error" == data.status) {
//							bootbox.alert(data.msg);
//						}
//					}, "json");
//		}
//	});
	
	$("#edit_dialog_content").load('${ctx}//loan/productInterestRate/list?id=' + id);
	$("#edit_dialog").modal("show");
	
	
}

function publish(id) {
	bootbox.confirm("您确认要发布该产品吗?", function(result) {
		if (result) {
			$.get("${ctx}/loan/product/publish/" + id,
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

function setInvalid(id) {
	bootbox.confirm("您确认要停用该产品信息吗?", function(result) {
		if (result) {
			$.get("${ctx}/loan/product/setInvalid/" + id,
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

function submit(id) {
	var selectedId = jQuery(grid_render_id).jqGrid('getGridParam','selrow');
	if(null == selectedId || "" == selectedId){
		bootbox.alert("请选择要提交的记录！");
		return;
	}
	bootbox.confirm("您确认要提交该产品信息吗?", function(result) {
		if (result) {
			$.get("${ctx}/loan/product/submit/" + selectedId,
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
	var form = document.getElementById("product-form-search");
    for (var i = 0; i < form.elements.length; i++) {
        var e = form.elements[i];
        if("" == e.value){
    		continue;
    	}
        
        if(e.name == 'category.id'){
        	rules += '{"t":"s","f":"category.id","op":"like","v":"'+e.value+'"},';
		}else if(e.name == 'code'){
			rules += '{"t":"s","f":"code","op":"like","v":"'+e.value+'"},';
		}else if(e.name == 'name'){
			rules += '{"t":"s","f":"name","op":"like","v":"'+e.value+'"},';
		}else if(e.name == 'status'){
			rules += '{"t":"com.matrix.core.common.enums.Status","f":"status","op":"eq","v":"'+e.value+'"},';
		}else if(e.name == 'config'){
			rules += '{"t":"b","f":"config","op":"eq","v":"'+e.value+'"},';
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
