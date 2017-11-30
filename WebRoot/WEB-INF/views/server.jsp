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
		        	loadDetail("server/" + ${server.id});
		        });
		    });
		    function updateServer() {
		    	var ipAddress = $("#ipAddress").val();
		    	var port = $("#port").val();
		    	if(ipAddress && port) {
		    		$.ajax({
		    			url: "server/update/" + ${server.id} + "/" + ipAddress + "/" + port,
		    			dataType: "json",
		    			success: function(data) {
		    				alert("服务器信息更新成功.");
		    				window.location.reload();
		    			}
		    		});
		    	} else {
		    		alert("IP地址和端口不能为空.")
		    	}
		    }
		    function createEmulator() {
		    	loadDetail("emulator/create");
		    }
		</script>
	</head>
	<body>	
		<form class="form-horizontal well">
			<div class="form-group">
				<label for="ipAddress" class="col-sm-2 control-label">
					* IP地址
				</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="ipAddress" 
						value="${server.ipAddress}" disabled>
				</div>
			</div>
			<div class="form-group">
				<label for="port" class="col-sm-2 control-label">
					* 端口
				</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="port" 
						value="${server.port}" disabled>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label"></label>
				<div id="group1" class="col-sm-10">
					<button type="button" id="editButton" class="btn btn-default">
						编辑
					</button>
					<button type="button" class="btn btn-danger" title="暂不可用">
						删除
					</button>
					<button type="button" class="btn btn-primary" onclick="createEmulator()"
						title="设备包含手机、Pad、模拟器等">
						新建设备
					</button>
				</div>
				<div id="group2" class="col-sm-10" style="display: none;">
					<button type="button" class="btn btn-default" onclick="updateServer()">
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
