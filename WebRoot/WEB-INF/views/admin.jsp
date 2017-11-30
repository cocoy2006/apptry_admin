<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<script type="text/javascript" src="resources/jquery.ui/js/jquery-ui-1.8.16.custom.min.js"></script>
		<link rel="stylesheet" href="resources/dynaTree/skin-vista/ui.dynatree.css">
		<script type="text/javascript" src="resources/dynaTree/jquery.dynatree.js"></script>
		<script type="text/javascript" src="resources/js/apptry.js"></script>
		<script type="text/javascript">
			var current_server_id = 0, current_emulator_id = 0;
		    $(document).ready(function() {
		        loadServerTree();
		        loadAllOs();
		        loadAllDpi();
		    });
		</script>
	</head>
	<body>
		<div class="col-md-12">
			<button class="btn btn-primary" onclick="loadDetail('server/create')">
				新建服务器
			</button>
		</div>
		<div class="tree col-md-4"><!-- dynatree content --></div>
		<div class="detail col-md-8"><!-- details of server, emulator or application --></div>
	</body>
</html>
