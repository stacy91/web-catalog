<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div align="center" class="managementContentWrapper"> 
	<table class="table table-hover table-bordered">
		<th>No</th>
		<th>Brand Name</th>
		<th>Reference</th>
		<th>Manage <c:forEach var="brand" items="${brands}"
				varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${brand.brandName}</td>

					<td><a href="#">View devices</a></td>
					<td>
					
					<form:form action="${pageContext.request.contextPath}/management/deleteBrand" method="POST">
					<button value="${brand.id}" name="id" class="btn btn-default"> Delete</button>
					</form:form>
					
					<form:form action="${pageContext.request.contextPath}/management/addBrand" method="POST">
					<input type="button" class="btn btn-default" value="Change"/>
					</form:form>
					</td>
				</tr>
			</c:forEach>
	</table>

	<form:form action="${pageContext.request.contextPath}/management/addBrand" method="GET">
	<input type="submit" class="btn btn-success" style="width: 100px; float:left" value="Add new"/>
	</form:form>
</div>




