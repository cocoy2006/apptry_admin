<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<c:if test="${orders != null}">
	<ol>
	<c:forEach var="order" items="${orders}">
		<li>
			${order.key}
			<c:choose>
			    <c:when test="${order.value.product_id == 11}">
			       	VIP1(100RMB1000次)
			    </c:when>
			    <c:when test="${order.value.product_id == 12}">
			       	VIP2(500RMB6000次)
			    </c:when>
			    <c:when test="${order.value.product_id == 13}">
			       	VIP3(1000RMB14000次)
			    </c:when>
			    <c:otherwise>
			        OTHER
			    </c:otherwise>
			</c:choose>
		</li>
	</c:forEach>
	</ol>
</c:if>
<c:if test="${orders == null}">
	<span class="label label-info">用户暂无订单</span>
</c:if>