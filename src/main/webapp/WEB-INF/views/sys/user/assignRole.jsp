<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
	<div class="col-xs-12">
			<table id="assign-role-table" class="table table-striped table-bordered table-hover" style="height: 100%;">
				<thead>
					<tr>
						<th width="50px" class="center">
							<label>选择</label>
						</th>
						<th width="20%">角色编码</th>
						<th width="40%">角色名称</th>
						<th class="hidden-480">角色描述</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="role" items="${roles}">
						<tr>
							<td class="center">
								<label>
									<c:choose>
    									<c:when test="${role.additional.isAssigned }">
    										<input type="checkbox" name="roleId" value="${role.id }" checked class="ace" />
    									</c:when>
    									<c:otherwise>
    										<input type="checkbox" name="roleId" value="${role.id }" class="ace" />
    									</c:otherwise>
									</c:choose>
									
									<span class="lbl"></span>
								</label>
							</td>
							<td>${role.code}</a></td>
							<td>${role.name}</td>
							<td class="hidden-480">${role.description}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
	</div>
	<!-- /span -->
</div>
<!-- /row -->
<script type="text/javascript">
	$(function() {
		//保存分配的角色
		$("#assign_role_dialog_save").off().on('click', function() {
			var sData ="";    
			$('input[name="roleId"]:checked',"#assign-role-table").each(function(i,item){
				if(0 != i){
					sData +=",";
				}
				sData += $(item).val(); 
			}); 
		
			$.post('sys/ur/save', {userId:"${userId}",roleIds:sData},function(data) {
				if ("success" == data.status) {
					$('#assign_role_dialog').modal("hide");
				} else {
					bootbox.alert(data.msg);
				}
			},"json");
		});
	})
</script>

