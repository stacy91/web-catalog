<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div align="center" class="managementContentWrapperH"> 
	<table class="table table-hover table-bordered">
		<th>No</th>
		<th>Brand Name</th>
		<th>Reference</th>
		<th class="manage">Manage <c:forEach var="brand" items="${brands}"
				varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${brand.brandName}</td>

					<td><a href="#">View devices</a></td>
					<td class="row" style="width:240px">
					
					<form:form action="${pageContext.request.contextPath}/management/deleteBrand" method="POST">
					<button value="${brand.id}" name="id" class="btn btn-default myButtons col-lg-5"> Delete</button>
					</form:form>
					
					<form:form action="${pageContext.request.contextPath}/management/updateBrand" method="GET">
					<button value="${brand.id}" name="id" class="btn btn-default myButtons col-sm-5" style="margin-left: 20px">Update</button>
					</form:form>
					</td>
				</tr>
			</c:forEach>
	</table>

	<form:form action="${pageContext.request.contextPath}/management/addBrand" method="GET">
	<input type="submit" class="btn btn-success myButtons" style="float:left" value="Add new"/>
	</form:form>
</div>




