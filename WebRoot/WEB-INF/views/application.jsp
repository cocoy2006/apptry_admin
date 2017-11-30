<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
	<head>
		<script type="text/javascript">
		    $(document).ready(function() {
		        $("#editButton").click(function() {
		        	$("#group1").hide();
		        	$("#group2").show();
		        	$(".form-control").removeAttr("disabled");
		        });
		        $("#cancelButton").click(function() {
		        	$("#group2").hide();
		        	$("#group1").show();
		        	$(".form-control").attr("disabled", "disabled");
		        	loadDetail("application/" + ${application.id});
		        });
		    });
		    function updateApplication() {
		    	var name = $("#name").val();
		    	var packageName = $("#packageName").val();
		    	var startActivity = $("#startActivity").val();
		    	if(packageName && startActivity) {
		    		var url = "application/update/" + ${application.id} + "/" + name + "/" + packageName + "/" + startActivity + "/";
		    		$.ajax({
		    			url: url,
		    			dataType: "json",
		    			success: function(data) {
		    				alert("应用信息更新成功.");
		    				window.location.reload();
		    			}
		    		});
		    	} else {
		    		alert("包名和启动活动名不能为空.")
		    	}
		    }
		</script>
	</head>
	<body>	
		<form class="form-horizontal well">
			<div class="form-group">
				<label for="name" class="col-sm-2 control-label">
					* 应用名称
				</label>
				<div class="col-sm-10">
					<input type="text" id="name" class="form-control"
						value="${application.name}" disabled>
				</div>
			</div>
			<div class="form-group">
				<label for="packageName" class="col-sm-2 control-label">
					* 包名
				</label>
				<div class="col-sm-10">
					<input type="text" id="packageName" class="form-control"
						value="${application.packageName}" disabled>
				</div>
				
			</div>
			<div class="form-group">
				<label for="startActivity" class="col-sm-2 control-label">
					* 启动活动名
				</label>
				<div class="col-sm-10">
					<input type="text" id="startActivity" class="form-control"
						value="${application.startActivity}" disabled>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label"></label>
				<div id="group1" class="col-sm-10">
					<button type="button" id="editButton" class="btn btn-default">
						编辑
					</button>
					<button type="button" class="btn btn-danger disabled" title="暂不可用">
						删除
					</button>
				</div>
				<div id="group2" class="col-sm-10" style="display: none;">
					<button type="button" class="btn btn-default" onclick="updateApplication()">
						提交
					</button>
					<button type="button" id="cancelButton" class="btn btn-link">
						返回
					</button>
				</div>
			</div>
		</form>
	</body>
</html>
