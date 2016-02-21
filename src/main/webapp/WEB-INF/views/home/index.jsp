<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.SecurityUtils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>人人分期 - 产品中心</title>

		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="${ctx}/static/assets/css/bootstrap.css" />
		<link rel="stylesheet" href="${ctx}/static/assets/css/font-awesome.css" />

		<!-- page specific plugin styles -->
		<!-- user start -->
		<link rel="stylesheet" href="${ctx}/static/zTree/css/zTreeStyle/zTreeStyle.css" />
		<link rel="stylesheet" href="${ctx}/static/styles/jquery.loadmask.css" />
		<link rel="stylesheet" href="${ctx}/static/jquery-validation/1.11.1/validate.css" />
		<link rel="stylesheet" href="${ctx}/static/assets/css/jquery-ui.css" />
		<link rel="stylesheet" href="${ctx}/static/assets/css/datepicker.css" />
		<link rel="stylesheet" href="${ctx}/static/assets/css/ui.jqgrid.css" />
		<link rel="stylesheet" href="${ctx}/static/assets/css/bootstrap-timepicker.css" />
		<link rel="stylesheet" href="${ctx}/static/assets/css/bootstrap-datetimepicker.css" />
		
		<!-- user end -->

		<!-- text fonts -->
		<link rel="stylesheet" href="${ctx}/static/assets/css/ace-fonts.css" />

		<!-- ace styles -->
		<link rel="stylesheet" href="${ctx}/static/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />

		
		<!--[if lte IE 9]>
			<link rel="stylesheet" href="${ctx}/static/assets/css/ace-part2.css" class="ace-main-stylesheet" />
		<![endif]-->

		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="${ctx}/static/assets/css/ace-ie.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- ace settings handler -->
		<script src="${ctx}/static/assets/js/ace-extra.js"></script>

		<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

		<!--[if lte IE 8]>
		<script src="${ctx}/static/assets/js/html5shiv.js"></script>
		<script src="${ctx}/static/assets/js/respond.js"></script>
		<![endif]-->
	</head>

	<body class="no-skin">
		<!-- #section:basics/navbar.layout -->
		<div id="navbar" class="navbar navbar-default">
			<script type="text/javascript">
				try{ace.settings.check('navbar' , 'fixed')}catch(e){}
			</script>

			<div class="navbar-container" id="navbar-container">
				<!-- #section:basics/sidebar.mobile.toggle -->
				<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
					<span class="sr-only">Toggle sidebar</span>

					<span class="icon-bar"></span>

					<span class="icon-bar"></span>

					<span class="icon-bar"></span>
				</button>

				<!-- /section:basics/sidebar.mobile.toggle -->
				<div class="navbar-header pull-left">
					<!-- #section:basics/navbar.layout.brand -->
					<a href="#" class="navbar-brand">
						<small>
							<i class="fa fa-renren"></i>
							产品中心
						</small>
					</a>

					<!-- /section:basics/navbar.layout.brand -->

					<!-- #section:basics/navbar.toggle -->

					<!-- /section:basics/navbar.toggle -->
				</div>

				<!-- #section:basics/navbar.dropdown -->
				<div class="navbar-buttons navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
						<!-- #section:basics/navbar.user_menu -->
						<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<img class="nav-user-photo" src="${ctx}/static/assets/avatars/user.jpg" alt="用户照片" />
								<span class="user-info">
									<small>欢迎,</small>
									<%= SecurityUtils.getSubject().getPrincipal().toString() %>
								</span>

								<i class="ace-icon fa fa-caret-down"></i>
							</a>

							<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
							    <li>
									<a href="javascript:void(0)" onclick="editPw('${userId}');">
										<i class="ace-icon fa fa-cog"></i>
										密码修改
									</a>
								</li>

								<li>
									<a href="javascript:void(0)" onclick="editProfile('${userId}');">
										<i class="ace-icon fa fa-user"></i>
										个人信息
									</a>
								</li>

								<li class="divider"></li>

								<li>
									<a href="${ctx}/logout">
										<i class="ace-icon fa fa-power-off"></i>
										退出
									</a>
								</li>
							</ul>
						</li>

						<!-- /section:basics/navbar.user_menu -->
					</ul>
				</div>

				<!-- /section:basics/navbar.dropdown -->
			</div><!-- /.navbar-container -->
		</div>

		<!-- /section:basics/navbar.layout -->
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<!-- #section:basics/sidebar -->
			<div id="sidebar" class="sidebar                  responsive">
				<script type="text/javascript">
					try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
				</script>

				<div class="sidebar-shortcuts" id="sidebar-shortcuts">
					<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
						<button class="btn btn-success">
							<i class="ace-icon fa fa-signal"></i>
						</button>

						<button class="btn btn-info">
							<i class="ace-icon fa fa-pencil"></i>
						</button>

						<!-- #section:basics/sidebar.layout.shortcuts -->
						<button class="btn btn-warning">
							<i class="ace-icon fa fa-users"></i>
						</button>

						<button class="btn btn-danger">
							<i class="ace-icon fa fa-cogs"></i>
						</button>

						<!-- /section:basics/sidebar.layout.shortcuts -->
					</div>

					<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
						<span class="btn btn-success"></span>

						<span class="btn btn-info"></span>

						<span class="btn btn-warning"></span>

						<span class="btn btn-danger"></span>
					</div>
				</div><!-- /.sidebar-shortcuts -->

				<ul id="sys-nav-list" class="nav nav-list">
					<li class="">
						<a href="#" class="dropdown-toggle">
							<i class="menu-icon fa fa-desktop"></i>
							<span class="menu-text"> 系统管理 </span>

							<b class="arrow fa fa-angle-down"></b>
						</a>

						<b class="arrow"></b>

						<ul class="submenu">
							<li class="">
								<a href="/sys/org/list" target="center">
									<i class="menu-icon fa fa-caret-right"></i>
									组织管理
								</a>

								<b class="arrow"></b>
							</li>

							<li class="">
								<a href="/sys/user/list" target="center">
									<i class="menu-icon fa fa-caret-right"></i>
									用户管理
								</a>

								<b class="arrow"></b>
							</li>
							<li class="">
								<a href="/sys/res/list" target="center">
									<i class="menu-icon fa fa-caret-right"></i>
									资源管理
								</a>

								<b class="arrow"></b>
							</li>
							<li class="">
								<a href="/sys/role/list" target="center">
									<i class="menu-icon fa fa-caret-right"></i>
									角色管理
								</a>

								<b class="arrow"></b>
							</li>
						</ul>
					</li>
					
					<li class="">
						<a href="#" class="dropdown-toggle">
							<i class="menu-icon fa fa-list"></i>
							<span class="menu-text"> 基础数据 </span>

							<b class="arrow fa fa-angle-down"></b>
						</a>

						<b class="arrow"></b>

						<ul class="submenu">
							<li class="">
								<a href="/basicData/dict/list" target="center">
									<i class="menu-icon fa fa-caret-right"></i>
									数据字典管理
								</a>
								<b class="arrow"></b>
							</li>
							
							<li class="">
								<a href="/basicData/productCategory/list" target="center">
									<i class="menu-icon fa fa-caret-right"></i>
									产品分类管理
								</a>
								<b class="arrow"></b>
							</li>
						</ul>
					</li>
					
					<li class="">
						<a href="/loan/product/list" target="center">
							<i class="menu-icon fa fa-pencil-square-o"></i>
							<span class="menu-text"> 贷款产品管理 </span>
						</a>

						<b class="arrow"></b>
					</li>
					
					<li class="">
						<a href="#" class="dropdown-toggle">
							<i class="menu-icon fa fa-list"></i>
							<span class="menu-text"> 订单管理  </span>

							<b class="arrow fa fa-angle-down"></b>
						</a>

						<b class="arrow"></b>

						<ul class="submenu">
							<li class="">
								<a href="/loan/bill/list" target="center">
									<i class="menu-icon fa fa-caret-right"></i>
									订单创建管理
								</a>
								<b class="arrow"></b>
							</li>
							
							<li class="">
								<a href="/loan/bill/failList" target="center">
									<i class="menu-icon fa fa-caret-right"></i>
									放款失败管理
								</a>
								<b class="arrow"></b>
							</li>
						</ul>
					</li>
					<li class="">
						<a href="/repayment/bill/list" target="center">
							<i class="menu-icon fa fa-pencil-square-o"></i>
							<span class="menu-text"> 账单清算管理 </span>
						</a>

						<b class="arrow"></b>
					</li>
					
				</ul><!-- /.nav-list -->

				<!-- #section:basics/sidebar.layout.minimize -->
				<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
					<i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
				</div>

				<!-- /section:basics/sidebar.layout.minimize -->
				<script type="text/javascript">
					try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
				</script>
			</div>

			<!-- /section:basics/sidebar -->
			<div class="main-content">
				<div class="main-content-inner">
					<!-- #section:basics/content.breadcrumbs -->
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

						<ul id="sys-nav-path" class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="#">Home</a>
							</li>
						</ul><!-- /.breadcrumb -->

						

						<!-- /section:basics/content.searchbox -->
					</div>

					<!-- /section:basics/content.breadcrumbs -->
					<div class="page-content">
						<!-- #section:settings.box -->
						<div class="ace-settings-container" id="ace-settings-container">
							<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
								<i class="ace-icon fa fa-cog bigger-130"></i>
							</div>

							<div class="ace-settings-box clearfix" id="ace-settings-box">
								<div class="pull-left width-50">
									<!-- #section:settings.skins -->
									<div class="ace-settings-item">
										<div class="pull-left">
											<select id="skin-colorpicker" class="hide">
												<option data-skin="no-skin" value="#438EB9">#438EB9</option>
												<option data-skin="skin-1" value="#222A2D">#222A2D</option>
												<option data-skin="skin-2" value="#C6487E">#C6487E</option>
												<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
											</select>
										</div>
										<span>&nbsp; 切换主题</span>
									</div>

									<!-- /section:settings.skins -->

									<!-- #section:settings.navbar -->
									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-navbar" />
										<label class="lbl" for="ace-settings-navbar"> 固定顶部</label>
									</div>

									<!-- /section:settings.navbar -->

									<!-- #section:settings.sidebar -->
									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-sidebar" />
										<label class="lbl" for="ace-settings-sidebar"> 固定边栏</label>
									</div>

									<!-- /section:settings.sidebar -->

									<!-- #section:settings.breadcrumbs -->
									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-breadcrumbs" />
										<label class="lbl" for="ace-settings-breadcrumbs"> 固定框图</label>
									</div>

									<!-- /section:settings.breadcrumbs -->

									<!-- #section:settings.rtl -->
									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl" />
										<label class="lbl" for="ace-settings-rtl"> 左右切换(RTL)</label>
									</div>

									<!-- /section:settings.rtl -->

									<!-- #section:settings.container -->
									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container" />
										<label class="lbl" for="ace-settings-add-container">
											内嵌容器浏览
										</label>
									</div>

									<!-- /section:settings.container -->
								</div><!-- /.pull-left -->

								<div class="pull-left width-50">
									<!-- #section:basics/sidebar.options -->
									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-hover" />
										<label class="lbl" for="ace-settings-hover"> 子菜单悬停</label>
									</div>

									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-compact" />
										<label class="lbl" for="ace-settings-compact"> 收缩边栏</label>
									</div>

									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-highlight" />
										<label class="lbl" for="ace-settings-highlight"> Alt. Active Item</label>
									</div>

									<!-- /section:basics/sidebar.options -->
								</div><!-- /.pull-left -->
							</div><!-- /.ace-settings-box -->
						</div><!-- /.ace-settings-container -->

						<!-- /section:settings.box -->
						<div class="row">
							<div id="page-content-area" class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								

								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->

			

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->
		
		<div class="modal fade" id="pwd_edit_dialog" tabindex="-1" role="dialog" aria-labelledby="pwd_edit_dialog_title" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="pwd_edit_dialog_title">修改-密码</h4>
					</div>
					<div id="pwd_edit_dialog_content" class="modal-body"></div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button id="pwd_edit_dialog_save" type="button" class="btn btn-primary">确定</button>
					</div>
				</div>
			</div>
		</div>
		
		<div class="modal fade" id="profile_edit_dialog" tabindex="-1" role="dialog" aria-labelledby="profile_edit_dialog_title" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="profile_edit_dialog_title">修改-个人信息</h4>
					</div>
					<div id="pwd_edit_dialog_content" class="modal-body"></div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button id="profile_edit_dialog_save" type="button" class="btn btn-primary">确定</button>
					</div>
				</div>
			</div>
		</div>

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='${ctx}/static/assets/js/jquery.js'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='${ctx}/static/assets/js/jquery1x.js'>"+"<"+"/script>");
</script>
<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='${ctx}/static/assets/js/jquery.mobile.custom.js'>"+"<"+"/script>");
		</script>
		<script src="${ctx}/static/assets/js/bootstrap.js"></script>

		<!-- page specific plugin scripts -->

		<!--[if lte IE 8]>
		  <script src="../assets/js/excanvas.js"></script>
		<![endif]-->
		<script src="${ctx}/static/assets/js/jquery-ui.custom.js"></script>
		<script src="${ctx}/static/assets/js/jquery.ui.touch-punch.js"></script>
		<script src="${ctx}/static/assets/js/jquery.easypiechart.js"></script>
		<script src="${ctx}/static/assets/js/jquery.sparkline.js"></script>
		<script src="${ctx}/static/assets/js/flot/jquery.flot.js"></script>
		<script src="${ctx}/static/assets/js/flot/jquery.flot.pie.js"></script>
		<script src="${ctx}/static/assets/js/flot/jquery.flot.resize.js"></script>

		<!-- ace scripts -->
		<script src="${ctx}/static/assets/js/ace/elements.scroller.js"></script>
		<script src="${ctx}/static/assets/js/ace/elements.colorpicker.js"></script>
		<script src="${ctx}/static/assets/js/ace/elements.fileinput.js"></script>
		<script src="${ctx}/static/assets/js/ace/elements.typeahead.js"></script>
		<script src="${ctx}/static/assets/js/ace/elements.wysiwyg.js"></script>
		<script src="${ctx}/static/assets/js/ace/elements.spinner.js"></script>
		<script src="${ctx}/static/assets/js/ace/elements.treeview.js"></script>
		<script src="${ctx}/static/assets/js/ace/elements.wizard.js"></script>
		<script src="${ctx}/static/assets/js/ace/elements.aside.js"></script>
		<script src="${ctx}/static/assets/js/ace/ace.js"></script>
		<script src="${ctx}/static/assets/js/ace/ace.ajax-content.js"></script>
		<script src="${ctx}/static/assets/js/ace/ace.touch-drag.js"></script>
		<script src="${ctx}/static/assets/js/ace/ace.sidebar.js"></script>
		<script src="${ctx}/static/assets/js/ace/ace.sidebar-scroll-1.js"></script>
		<script src="${ctx}/static/assets/js/ace/ace.submenu-hover.js"></script>
		<script src="${ctx}/static/assets/js/ace/ace.widget-box.js"></script>
		<script src="${ctx}/static/assets/js/ace/ace.settings.js"></script>
		<script src="${ctx}/static/assets/js/ace/ace.settings-rtl.js"></script>
		<script src="${ctx}/static/assets/js/ace/ace.settings-skin.js"></script>
		<script src="${ctx}/static/assets/js/ace/ace.widget-on-reload.js"></script>
		<script src="${ctx}/static/assets/js/ace/ace.searchbox-autocomplete.js"></script>
		<script src="${ctx}/static/assets/js/fuelux/fuelux.tree.js"></script>
		
		<!-- ace ext -->
		<script src="${ctx}/static/assets/js/jquery-ui.custom.js"></script>
		<script src="${ctx}/static/assets/js/jqGrid/jquery.jqGrid.src.js"></script>
		<script src="${ctx}/static/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>
		<script src="${ctx}/static/assets/js/date-time/bootstrap-datepicker.js"></script>
		<script src="${ctx}/static/assets/js/bootbox.js"></script>
		<script src="${ctx}/static/assets/js/date-time/bootstrap-timepicker.js"></script>
		<script src="${ctx}/static/assets/js/date-time/moment.js"></script>
		<script src="${ctx}/static/assets/js/date-time/bootstrap-datetimepicker.js"></script>
		
		<!-- user custom -->
		<script src="${ctx}/static/js/jquery.loadmask.js"></script>
		<script src="${ctx}/static/js/dropDownTree.js"></script>
		<script src="${ctx}/static/js/jqGridExt.js"></script>
		<script src="${ctx}/static/js/jquery.form.js"></script>
		<script src="${ctx}/static/js/jquery.placeholder.min.js"></script>
		<script src="${ctx}/static/zTree/js/jquery.ztree.all-3.5.min.js"></script>
		<script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js"></script>
		<script src="${ctx}/static/jquery-validation/1.11.1/messages_bs_zh.js"></script>
		
		
		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			jQuery(function($) {
			  	$.ajaxSetup ({
					cache: false //关闭AJAX相应的缓存 
				});
			  	
			  	$(".dropdown-toggle","#sys-nav-list").on('click', function() {
					if (!$(this).parent().hasClass("active")) {
						$(".active","sys-nav-list").removeClass("active");
						$(this).parent().addClass("active");
						return false;
					}
				});
				
				$("a[target='center']","#sys-nav-list").off("click").on('click', function() {
					$("#page-content-area").mask("正在加载...");
					//样式设置
					if (!$(this).parent().hasClass("active")) {
						$(".active","#sys-nav-list").removeClass("active");
						$(this).parent().addClass("active");
					}
					
					var parentTitle = null;
					if($(this).parent().parent().hasClass("submenu")){
						$(this).parent().parent().parent().addClass("active");
						parentTitle = $(":first>span",$(this).parent().parent().parent()).html();
					}
					
					//设置导航栏
					var htmlPath = '<li><i class="ace-icon fa fa-home home-icon"></i><a href="#">Home</a></li><li class="active">'+ $(this).text() +'</li>';
					if(parentTitle){
						htmlPath = '<li><i class="ace-icon fa fa-home home-icon"></i><a href="#">Home</a></li><li><a href="#">'+ parentTitle +'</a></li><li class="active">'+ $(this).text() +'</li>';
					}
					
					$("ul > li","#breadcrumbs").remove();
					$("#sys-nav-path").append(htmlPath);
					
					$("#page-content-area").load("${ctx}" + $(this).attr('href')+"?_="+Math.random(),function(){
						$("#page-content-area").unmask();
					});
					
					return false;
				});
				
				
				
				$("#pwd_edit_dialog_save").on('click',function(){
           			$("#pwd_edit_form").ajaxSubmit({
           				dataType:'json',
           				success:function(data){
                            if(data.status=='success'){
                            	bootbox.alert("密码修改成功,系统重新登录！",function(){
                            		window.location.href="${ctx}/logout";
                            	});
                            }else{
                            	bootbox.alert(data.msg);
                            }
           				}
           			});
	            });
			})
			
		   //密码修改
		   function editPw(userId){
				console.info($("#pwd_edit_dialog"));
		 		$("#pwd_edit_dialog_content").load("${ctx}/sys/user/editPwd?id="+userId);
				$("#pwd_edit_dialog").modal("show");
	       }
			//修改个人信息
			function editProfile(userId){
				//$("#profile_edit_dialog_content").load('/cm/UserController/editPhone?userId='+userId+'');
				$('#profile_edit_dialog').modal("show");
			}
			
			//程序加载页面到当前窗口
			function loadPage(url){
				$("#page-content-area").mask("正在加载...");
				$("#page-content-area").load(url,function(response,status,xhr){
					$("#page-content-area").unmask();
				});
				return false;
			}
			
			//加载首页
			function loadIndex(){
				$("li:first > a","#sys-nav-list").click();
			}
			
			//导航到指定页面
			function navToMenu(url){
				$("a[href='"+url+"']","#sys-nav-list").click();
			}
		</script>

	</body>
</html>
