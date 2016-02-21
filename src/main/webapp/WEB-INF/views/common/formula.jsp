<div class="row">
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->
			<div class="row">
				<div class="col-sm-3 widget-container-span"
					style="padding-right: 0px;">
					<div class="widget-box" style="margin-top: 0px;">
						<div class="widget-header header-color-blue">
							<h6 class="bigger lighter">系统变量</h6>
						</div>
						<div class="widget-body">
							<div class="widget-main no-padding">
								<div class="table-responsive">
									<div class="zTreeDemoBackground left">
										<ul id="sysVarTree" class="ztree" overflow:auto;"></ul>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-sm-3 widget-container-span"
					style="padding-right: 0px;">
					<div class="widget-box" style="margin-top: 0px;">
						<div class="widget-header header-color-blue">
							<h6 class="bigger lighter">系统函数</h6>
						</div>
						<div class="widget-body">
							<div class="widget-main no-padding">
								<div class="table-responsive">
									<div class="zTreeDemoBackground left">
										<ul id="sysFunctionTree" class="ztree" overflow:auto;"></ul>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-sm-6 widget-container-span" style="padding-right: 0px;">
					<label class=" control-label no-padding-right" for="principalFormulaName"><span style="color: red">*</span>出借本金公式名称：</label>
					<textarea id="principalFormulaName" name="principalFormulaName" maxlength="1024" class="form-control" rows="4" cols="" required>${product.principalFormulaName }</textarea>
					<label for="form-field-9">函数说明</label>
				</div>
				
			</div>
	
			<script type="text/javascript">
				var sysVarSetting = {
						async: {
							enable: true,
							dataType: "json",
							url:"sys/org/getOrgTree",
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
								if (!treeNode.isParent) {
									$("#principalFormulaName").val($("#principalFormulaName").val()+treeNode.name);
								}
							},
							onAsyncSuccess: function(){
// 								$("#orgTable").jqGrid().trigger("reloadGrid");
							}
						}
				};
				var sysFunctionSetting = {
						async: {
							enable: true,
							dataType: "json",
							url:"sys/org/getOrgTree",
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
								if (!treeNode.isParent) {
									$("#principalFormulaName").val($("#principalFormulaName").val()+treeNode.name);
								}
							},
							onAsyncSuccess: function(){
// 								$("#orgTable").jqGrid().trigger("reloadGrid");
							}
						}
				};
				$(function() {
					$.fn.zTree.init($("#sysVarTree"), sysVarSetting);
					$.fn.zTree.init($("#sysFunctionTree"), sysFunctionSetting);
	
				})
			</script>
			<!-- PAGE CONTENT ENDS -->
		</div>
		<!-- /.col -->
	</div>