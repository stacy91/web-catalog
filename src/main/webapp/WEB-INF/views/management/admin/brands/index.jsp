<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div align="center" class="managementContentWrapperH"> 
	<table class="table table-hover table-bordered">
	
	<tr>
		<th><spring:message code="Brand" /></th>
		<th class="manage"><spring:message code="Tables.manage" /> </th> 
	</tr>
		<c:forEach var="brand" items="${brands}">
				<tr>
					<td><a href="${pageContext.request.contextPath}?brandId=${brand.id }">${brand.brandName }</a></td>

					<td class="row" style="width:240px">
					
					<form:form action="${pageContext.request.contextPath}/management/deleteBrand" method="POST">
					<button value="${brand.id}" name="id" class="btn btn-default myButtons col-lg-5"> <spring:message code="Delete" /></button>
					</form:form>
					
					<form:form action="${pageContext.request.contextPath}/management/updateBrand" method="GET">
					<button value="${brand.id}" name="id" class="btn btn-default myButtons col-sm-5" style="margin-left: 20px"><spring:message code="Update" /></button>
					</form:form>
					</td>
				</tr>
			</c:forEach>
	</table>

	<form:form action="${pageContext.request.contextPath}/management/addBrand" method="GET">
	<input type="submit" class="btn btn-success myButtons" style="float:left" value="<spring:message code="Add" />"/>
	</form:form>
</div>




