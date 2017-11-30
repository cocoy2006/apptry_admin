<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<script type="text/javascript">
		    function createServer() {
		    	var ipAddress = $("#ipAddress").val();
		    	var port = $("#port").val();
		    	if(ipAddress && port) {
		    		$.ajax({
		    			url: "server/create/" + ipAddress + "/" + port,
		    			dataType: "json",
		    			success: function(data) {
		    				alert("新建服务器成功.");
		    				window.location.reload();
		    			}
		    		});
		    	} else {
		    		alert("IP地址和端口不能为空.");
		    	}
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
					<input type="text" id="ipAddress" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label for="port" class="col-sm-2 control-label">
					* 端口
				</label>
				<div class="col-sm-10">
					<input type="text" id="port" class="form-control" value="8080">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label"></label>
				<div class="col-sm-10">
					<button class="btn btn-primary" onclick="createServer()">
						提交
					</button>
				</div>
			</div>
		</form>
	</body>
</html>
