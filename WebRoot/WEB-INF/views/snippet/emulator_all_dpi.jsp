<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<h5 class="well">
	<input type="checkbox" id="allDpi" onclick="checkAll(this, 'dpi')"/>
	分辨率
	<!--  
	<a class="btn btn-link" href="javascript:;" onclick="selectAll('dpi')">[全选]</a>
	<span class="divider">|</span> 
	<a class="btn btn-link" href="javascript:;" onclick="deselectAll('dpi')">[全不选]</a>
	-->
</h5>
<c:if test="${list != null}">
	<c:set var="count" value="1" />
	<div class="row">
	<c:forEach var="dpi" items="${list}">
		<div class="span2">
			<label class="checkbox">
			  	<input type="checkbox" name="dpi" value="${dpi}" onclick="cancelCheckAll('allDpi', 'dpi')"/>
			  	${dpi}
			</label>
		</div>
	</c:forEach>
	</div>
</c:if>
<c:if test="${list == null}">
	<c:import url="../template/building.html" charEncoding="UTF-8"/>
</c:if>