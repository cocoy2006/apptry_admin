<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<h5 class="well">
	<!--  
	<a class="btn btn-link" href="javascript:;" onclick="selectAll('os')">[全选]</a>
	<span class="divider">|</span>
	<a class="btn btn-link" href="javascript:;" onclick="deselectAll('os')">[全不选]</a>
	-->
	<input type="checkbox" id="allOs" onclick="checkAll(this, 'os')"/>
	系统版本
</h5>
<c:if test="${list != null}">
	<c:set var="count" value="1" />
	<div class="row">
	<c:forEach var="os" items="${list}">
		<div class="span2">
			<label class="checkbox">
			  	<input type="checkbox" name="os" value="${os}" onclick="cancelCheckAll('allOs', 'os')"/>
			  	${os}
			</label>
		</div>
	</c:forEach>
	</div>
</c:if>