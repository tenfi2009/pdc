<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />		
<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<div class="row">
			<div class="col-sm-3 widget-container-span"
				style="padding-right: 0px;">
				<div class="widget-box" style="margin-top: 0px;">
					<div class="widget-header header-color-blue">
						<h6 class="bigger lighter">数据字典树</h6>
					</div>
					<div class="widget-body">
						<div class="widget-main no-padding">
							<div class="table-responsive">
								<div class="zTreeDemoBackground left">
									<ul id="dictTree" class="ztree" style="height:auto; overflow:auto;"></ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /span -->

			<div class="col-sm-9">
				<table id="dictTable"></table>
				<div id="dictTablepager"></div>
			</div>
		</div>

		<script type="text/javascript">
			//高度控制
			var _gridHeight = $(window).height()-$(".page-content").offset().top-200;
			
			var selectedOrgNode = null;
			var setting = {
					async: {
						enable: true,
						dataType: "json",
						url:"basicData/dict/getDictTree",
						autoParam:["id"]
					},
					data: {
						simpleData: {
							enable: true,
							idKey: "id",
							pIdKey: "pId",
							rootPId: null
						}
					},
					callback: {
						onClick: function (event, treeId, treeNode) {
							selectedOrgNode = treeNode;
							$("#dictTable").jqGrid('setGridParam',{postData:{"parentId":treeNode.id}}).trigger("reloadGrid");
						},
						onAsyncSuccess:function(){
							if(null != selectedOrgNode){
								$("#dictTable").jqGrid('setGridParam',{postData:{"parentId":selectedOrgNode.id}}).trigger("reloadGrid");
							}else{
								$("#dictTable").jqGrid().trigger("reloadGrid");
							}
						}
					}
			};
			$(function() {
				$.fn.zTree.init($("#dictTree"), setting);

				var grid_selector = "#dictTable";
				var pager_selector = "#dictTablepager";

				$(grid_selector).jqGrid({
					caption : "字典列表",
					height : 'auto',
					rowNum : 15,
					rowList : [ 15,25,50,100 ],
					url:"basicData/dict/listData",
					datatype : "json",
					colNames : [ 'ID', '字典编码', '字典名称', '描述','状态','创建时间','创建人'],
					colModel : [
					            {name : 'id',index : 'id',hidden:true},
					            {name : 'code',index : 'code',width : 80},
					            {name : 'name',index : 'name',width : 180},
					            {name : 'description',index : 'description',width : 200},
					            {name : 'status',index : 'status',width : 60},
					            {name : 'createTime',index : 'createTime',width : 120},
					            {name : 'creator',index : 'creator',width : 60}
							   ],
					viewrecords : true,
					postData : {"parentId" : function(){return selectedOrgNode == null ? "" : selectedOrgNode.id}},
					pager : pager_selector,
					altRows : true,
					sortname : "createTime",
					autowidth : true,
					rownumbers : true,
					multiselect : false,
					//multikey: "ctrlKey",
					multiboxonly : true,
					jsonReader : {
					      root:"result",
					      page: "currPage",
					      total: "totalPage",
					      records: "totalCount"
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
				$(grid_selector).jqGrid('navGrid',pager_selector,{ //navbar options
							alertcap : "消息",
							alerttext : "请您先选择要操作的记录！",
							edit : true,
							edittext : "修改",
							editicon : 'ace-icon fa fa-pencil blue',
							editfunc : function(id){
								$("#dict_edit_dialog_content").load('${ctx}/basicData/dict/edit?id='+id);
								$("#dict_edit_dialog").modal("show");
							},
							add : true,
							addtext : "新建",
							addicon : 'ace-icon fa fa-plus-circle purple',
							addfunc	: function () {
								var selectedOrgId = selectedOrgNode == null ? "" : selectedOrgNode.id;
								$("#dict_add_dialog_content").load('${ctx}/basicData/dict/addNew?parentId='+selectedOrgId);
								$("#dict_add_dialog").modal("show");
							},
							del : true,
							deltext : "删除",
							delicon : 'ace-icon fa fa-trash-o red',
							delfunc : function(id){
								bootbox.confirm("您确认要删除该字典吗?", function(result) {
									if(result) {
										$.get("${ctx}/basicData/dict/remove/"+id,function(data){
							            	if("success" == data.status){
							            		if(null != selectedOrgNode){
													$("#dictTable").jqGrid('setGridParam',{postData:{"parentId":selectedOrgNode.id}}).trigger("reloadGrid");
												}else{
													$("#dictTable").jqGrid().trigger("reloadGrid");
												}
							            	}else if("error" == data.status){
							            		bootbox.alert(data.msg);
							            	}
									 	},"json");
									}
								});
							},
							search : length,
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
						{},  // delete instead that del:false we need this
						{	// search options
							caption : "高级查询",
							recreateForm : true,
							afterShowSearch : function(e) {
								var form = $(e[0]);
								form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
								$.jqGridExt.setStyleSearchForm(form);
							},
							afterRedraw : function() {
								$.jqGridExt.setStyleSearchFilters($(this));
							},
							showQuery : false,
							multipleSearch:true
						}, 
						{} /* view parameters*/
					).jqGrid('navButtonAdd',pager_selector,{
						   caption:"提交", 
						   title : "提交",
						   buttonicon:"fa fa-check-circle", 
						   onClickButton : function(){
							    var selectedId = jQuery(grid_selector).jqGrid('getGridParam','selrow');
								if(null == selectedId || "" == selectedId){
									bootbox.alert("请选择要提交的记录！");
									return;
								}
								bootbox.confirm("您确认要提交该数据吗?", function(result) {
									if(result) {
										$.get("${ctx}/basicData/dict/submit/"+selectedId,function(data){
							            	if("success" == data.status){
							            		if(null != selectedOrgNode){
													$("#dictTable").jqGrid('setGridParam',{postData:{"parentId":selectedOrgNode.id}}).trigger("reloadGrid");
												}else{
													$("#dictTable").jqGrid().trigger("reloadGrid");
												}
							            	}else if("error" == data.status){
							            		bootbox.alert(data.msg);
							            	}
									 	},"json");
									}
								});
							}
						});
				
				//var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');
				//高度控制
				$(".ztree").parents(".widget-box").css("height",$(".ztree").parents(".row").height());
				$("#dict_add_dialog_save").on('click',function() {
						$("#dict_add_form").ajaxSubmit({
					        dataType:'json',
					        success:function (data){
					            if("success" == data.status){
					                $('#dict_add_dialog').modal("hide");
					                
					                var treeObj = $.fn.zTree.getZTreeObj("dictTree");
					                var nodes = treeObj.getSelectedNodes();
					                
					                if (nodes.length>0) {
					                	treeObj.reAsyncChildNodes(nodes[0], "refresh");
					                }else{
					                	treeObj.reAsyncChildNodes(null, "refresh");
					                }
					        	}else{
					        		bootbox.alert(data.msg);
					        	}
					        }
					      });
				});
				$("#dict_edit_dialog_save").on('click',function() {
					$("#dict_edit_form").ajaxSubmit({
				        dataType:'json',
				        success:function (data){
				            if("success" == data.status){
				            	var treeObj = $.fn.zTree.getZTreeObj("dictTree");
				                var nodes = treeObj.getSelectedNodes();
				                
				                if (nodes.length>0) {
				                	treeObj.reAsyncChildNodes(nodes[0], "refresh");
				                }else{
				                	treeObj.reAsyncChildNodes(null, "refresh");
				                }
				        	}else{
				        		bootbox.alert(data.msg);
				        	}
				        }
				      });
			});
				
			})
		</script>
		<!-- PAGE CONTENT ENDS -->
	</div>
	<!-- /.col -->
</div>
<!-- /.row -->
<!-- 新增字典 -->
<div class="modal fade" id="dict_add_dialog" tabindex="-1" role="dialog" aria-labelledby="dict_add_dialog_title" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="dict_add_dialog_title">新增-字典</h4>
      </div>
      <div id="dict_add_dialog_content" class="modal-body">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button id="dict_add_dialog_save" type="button" class="btn btn-primary">保存</button>
      </div>
    </div>
  </div>
</div>

<!-- 修改字典 -->
<div class="modal fade" id="dict_edit_dialog" tabindex="-1" role="dialog" aria-labelledby="dict_edit_dialog_title" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="dict_edit_dialog_title">修改-字典</h4>
      </div>
      <div id="dict_edit_dialog_content" class="modal-body">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button id="dict_edit_dialog_save" type="button" class="btn btn-primary">保存</button>
      </div>
    </div>
  </div>
</div>
