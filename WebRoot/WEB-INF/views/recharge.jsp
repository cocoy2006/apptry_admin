<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
	<head>
		<script type="text/javascript">
			function query() {
				var username = $("#inputUsername").val();
				if(username) {
					$("#developerDetail").load("developer/" + username + "/");
				}
			}
		</script>
	</head>
	<body>
		<form class="form-horizontal" role="form">
			<div class="form-group">
				<label class="col-sm-1 control-label" for="inputUsername">用户名</label>
				<div class="col-sm-3">
					<input type="search" class="form-control" id="inputUsername" 
	    				placeholder="Enter username">
				</div>
				<div class="col-sm-3">
	    			<button type="button" class="btn btn-primary" onclick="query()">
						查询
					</button>
				</div>
			</div>
		</form>
		<div id="developerDetail">
			<p><span class="label label-info">请先查询并核实用户信息</span></p>
		</div>
	</body>
</html>