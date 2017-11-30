<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<c:if test="${developer != null}">
	<table class="table table-bordered">
		<tr>
			<td>
				用户名
			</td>
			<td>
				${developer.username}
			</td>
		</tr>
		<tr>
			<td>
				邮箱
			</td>
			<td>
				${developer.email}
			</td>
		</tr>
		<tr>
			<td>
				剩余点击次数
			</td>
			<td>
				${developer.leftClicks}
			</td>
		</tr>
		<tr>
			<td>
				充值记录
			</td>
			<td id="orderDetail">
				加载中...
			</td>
		</tr>
	</table>
	<script type="text/javascript">
		$("#orderDetail").load("order/loadAll/" + ${developer.id} + "/");
		function recharge() {
			var productId = $("select").val();
			if(productId) {
				$.ajax({
					url: "order/create/",
					data: "productId=" + productId + "&developerId=" + ${developer.id},
					dataType: "json",
					type: "POST",
					success: function(data) {
						query();
					}
				});
			}
		}
	</script>
	<form class="form-horizontal" role="form">
		<div class="form-group">
			<label class="col-sm-1 control-label" for="selectProduct">套餐</label>
			<div class="col-sm-3">
				<select class="form-control">
		      		<option value="11">VIP1(100RMB1000次)</option>
		      		<option value="12">VIP2(500RMB6000次)</option>
		      		<option value="13">VIP3(1000RMB14000次)</option>      		
		      	</select>
			</div>
			<div class="col-sm-3">
    			<button type="button" class="btn btn-success" onclick="recharge()">
					充值
				</button>
			</div>
		</div>
	</form>
</c:if>
<c:if test="${developer == null}">
	<p><span class="label label-danger">此用户不存在</span></p>
</c:if>