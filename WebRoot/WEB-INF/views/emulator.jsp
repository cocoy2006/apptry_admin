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
		        	loadDetail("emulator/" + ${emulator.id});
		        });
		    });
		    function updateEmulator() {
		    	var serialNumber = $("#serialNumber").val();
		    	var manufacturer = $("#manufacturer").val() === "" ? "+" : $("#manufacturer").val();
		    	var model = $("#model").val() === "" ? "+" : $("#model").val();
		    	var os = $("#os").val() === "" ? "+" : $("#os").val();
		    	var width = $("#width").val();
		    	var height = $("#height").val();
		    	if(serialNumber && width && height) {
		    		var url = "emulator/update/" + ${emulator.id} + "/" + serialNumber
			    		+ "/" + manufacturer + "/" + model + "/" + os + "/" + width + "/" + height;
			    	url = url.replace(/ /g, "+");
		    		$.ajax({
		    			url: url,
		    			dataType: "json",
		    			success: function(data) {
		    				alert("设备信息更新成功.");
		    				window.location.reload();
		    			}
		    		});
		    	} else {
		    		alert("设备序列号、屏幕宽度和屏幕高度不能为空.")
		    	}
		    }
		    function createApplication() {
		    	loadDetail("application/create");
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
					<input type="text" id="serialNumber" class="form-control"
						value="${emulator.serialNumber}" disabled>
				</div>
			</div>
			<div class="form-group">
				<label for="width" class="col-sm-2 control-label">
					* 屏幕宽度
				</label>
				<div class="col-sm-10">
					<input type="text" id="width" class="form-control"
						value="${emulator.width}" disabled>
				</div>
			</div>
			<div class="form-group">
				<label for="height" class="col-sm-2 control-label">
					* 屏幕高度
				</label>
				<div class="col-sm-10">
					<input type="text" id="height" class="form-control"
						value="${emulator.height}" disabled>
				</div>
			</div>
			<div class="form-group">
				<label for="state" class="col-sm-2 control-label">
					* 状态
				</label>
				<div class="col-sm-10">
					<c:choose>
					    <c:when test="${emulator.state == 0}">
					       	<c:set var="state" value="忙碌"></c:set>
					    </c:when>
					    <c:when test="${emulator.state == 1}">
					       	<c:set var="state" value="空闲"></c:set>
					    </c:when>
					    <c:otherwise>
					        <c:set var="state" value="其他"></c:set>
					    </c:otherwise>
					</c:choose>
					<input type="text" id="state" class="form-control"
						value="${state}" disabled>
				</div>
			</div>
			<div class="form-group">
				<label for="os" class="col-sm-2 control-label">
					操作系统
				</label>
				<div class="col-sm-10">
					<input type="text" id="os" class="form-control"
						value="${emulator.os}" disabled>
				</div>
			</div>
			<div class="form-group">
				<label for="manufacturer" class="col-sm-2 control-label">
					厂商
				</label>
				<div class="col-sm-10">
					<input type="text" id="manufacturer" class="form-control"
						value="${emulator.manufacturer}" disabled>
				</div>
			</div>
			<div class="form-group">
				<label for="model" class="col-sm-2 control-label">
					型号
				</label>
				<div class="col-sm-10">
					<input type="text" id="model" class="form-control"
						value="${emulator.model}" disabled>
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
					<button type="button" class="btn btn-default" onclick="updateEmulator()">
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
