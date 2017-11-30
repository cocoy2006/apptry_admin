<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>系统管理员</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" type="text/css" href="resources/bootstrap-3.0.3/css/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="resources/bootstrap-3.0.3/css/bootstrap-theme.css">
		<script type="text/javascript" src="resources/js/jquery.js"></script>
		<script type="text/javascript" src="resources/bootstrap-3.0.3/js/bootstrap.js"></script>
		<script type="text/javascript">
		    $(document).ready(function() {
				loadPage('mainContent', 'admin', 'emulatorNav');
		    });
		    function loadPage(id, page, parentId) {
		    	$("#" + id).load(page);
		    	$(".active").removeClass("active");
		    	$("#" + parentId).addClass("active");
		    }
		</script>
	</head>
	<body style="background-color: #B0E0E6;">
		<div class="navbar navbar-default navbar-fixed-top">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Admin,欢迎使用本系统</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li id="emulatorNav" class="active">
						<a href="javascript:void(0);" onclick="loadPage('mainContent', 'admin', 'emulatorNav')">设备管理</a>
					</li>
					<li class="divider-vertical"></li>
					<li id="userNav" class="dropdown">
					    <a class="dropdown-toggle" data-toggle="dropdown" href="javascript:void(0);">
				        	会员管理
				        	<b class="caret"></b>
				      	</a>
					    <ul class="dropdown-menu">
					      	<li>
								<a href="javascript:void(0);" onclick="loadPage('mainContent', 'recharge', 'userNav')">充值</a>
							</li>
					    </ul>
					</li>
					<li class="divider-vertical"></li>
					<li id="statisticsNav" class="active">
						<a href="javascript:void(0);" onclick="loadPage('mainContent', 'statistics', 'statisticsNav')">统计报表</a>
					</li>
					<li class="divider-vertical"></li>
					<li><a href="javascript:void(0);">注销</a></li>
				</ul>
			</div>
		</div>
		<div style="margin-top: 60px;">
			<div id="mainContent" class="container well" style="background-color: white;">
				<!-- main content -->
			</div>
		</div>
	</body>
</html>
