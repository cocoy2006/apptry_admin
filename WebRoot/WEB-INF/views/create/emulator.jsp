<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<script type="text/javascript">
		    function createEmulator() {
		    	var serialNumber = $("#serialNumber").val();
		    	var manufacturer = $("#manufacturer").val() === "" ? "+" : $("#manufacturer").val();
		    	var model = $("#model").val() === "" ? "+" : $("#model").val();
		    	var os = $("#os").val() === "" ? "+" : $("#os").val();
		    	var width = $("#width").val();
		    	var height = $("#height").val();
		    	if(serialNumber && width && height) {
		    		var url = "emulator/create/" + current_server_id + "/" + serialNumber + "/" 
			    		+ manufacturer + "/" + model + "/" + os + "/" + width + "/" + height;
			    	url = url.replace(/ /g, "+");
		    		$.ajax({
		    			url: url,
		    			dataType: "json",
		    			success: function(data) {
		    				alert("新建设备成功.");
		    				window.location.reload();
		    			}
		    		});
		    	} else {
		    		alert("设备序列号、屏幕宽度和屏幕高度不能为空.")
		    	}
		    }
		</script>
	</head>
	<body>	
		<form class="form-horizontal well">
			<div class="form-group">
				<label for="serialNumber" class="col-sm-2 control-label">
					* 序列号
				</label>
				<div class="col-sm-10">
					<input type="text" id="serialNumber" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label for="width" class="col-sm-2 control-label">
					* 屏幕宽度
				</label>
				<div class="col-sm-10">
					<input type="text" id="width" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label for="height" class="col-sm-2 control-label">
					* 屏幕高度
				</label>
				<div class="col-sm-10">
					<input type="text" id="height" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label for="os" class="col-sm-2 control-label">
					操作系统
				</label>
				<div class="col-sm-10">
					<input type="text" id="os" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label for="manufacturer" class="col-sm-2 control-label">
					厂商
				</label>
				<div class="col-sm-10">
					<input type="text" id="manufacturer" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label for="model" class="col-sm-2 control-label">
					型号
				</label>
				<div class="col-sm-10">
					<input type="text" id="model" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label"></label>
				<div class="col-sm-10">
					<button class="btn btn-primary" onclick="createEmulator()">
						提交
					</button>				
				</div>
			</div>
		</form>
	</body>
</html>
