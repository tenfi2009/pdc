<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!-- /section:basics/sidebar -->
<div class="main-content">
	<div class="main-content-inner">
		<!-- /section:basics/content.breadcrumbs -->
		<div class="page-content">
			<div class="page-header">
				<h1>产品名称</h1>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->
					<div class="row">
						<div class="col-sm-12">
							<!-- #section:elements.tab -->
							<div class="tabbable">
								<ul class="nav nav-tabs" id="myTab">
									<li class="active"><a data-toggle="tab"
										href="#interestRate"> <i
											class="green ace-icon fa fa-home bigger-120"></i> 利率设置
									</a></li>

									<li><a data-toggle="tab" href="#cost"> 费用设置 <span
											class="badge badge-danger">4</span>
									</a></li>
								</ul>

								<div class="tab-content">
									<div id="interestRate" class="tab-pane fade in active">
										<fieldset>

											<div class="form-group col-md-4">
												<label class="col-md-4 control-label no-padding-right" for="remark"></label>
												<div class="col-md-8">
													<button id="btn-prev" class="btn btn-prev">
														<i class="ace-icon fa fa-arrow-left"></i>
														上一分期数
													</button>
												</div>
											</div>
											<div class="form-group col-md-4">
												<label class="col-md-6 control-label no-padding-righ">
													当前分期数： </label>
												<div class="col-md-6">
													<select id="rate" name="rate" class="select2">
														<option value="1" selected="selected">3期</option>
														<option value="2">6期</option>
													</select>
												</div>
											</div>
											<div class="form-group col-md-4">
												<div class="col-md-6">
													<button id="btn-next" class="btn btn-success btn-next" data-last="Finish">
														下一分期数
														<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
													</button>
												</div>
											</div>
											<div id="rate_div"></div>
										</fieldset>

									</div>

									<div id="cost" class="tab-pane fade">
										<p>Food truck fixie locavore, accusamus mcsweeney's marfa
											nulla single-origin coffee squid.</p>
									</div>

								</div>
							</div>

							<!-- /section:elements.tab -->
						</div>
						<!-- /.col -->

					</div>
					<!-- /.row -->

					<!-- PAGE CONTENT ENDS -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /.page-content -->
	</div>
</div>
<script type="text/javascript">
$(function(){
	$("#rate_div").load('${ctx}/loan/productInterestRate/loadRatePage');
	
	$("#rate").change(function() {
		$(this).children('option:selected').val()
	});
	
	$("#btn-prev").click(function() {
		$("#rate").children('option:selected').removeAttr("selected").prev().attr("selected","selected");
		$("#rate").change();
	});
	
	$("#btn-next").click(function() {
		$("#rate").children('option:selected').removeAttr("selected").next().attr("selected","selected");
		$("#rate").change();
	});
});
</script>
