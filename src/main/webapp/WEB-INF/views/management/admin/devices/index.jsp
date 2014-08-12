<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title"></h3>
			</div>
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-bordered table-hover table-striped">
						<thead>
							<tr>
								<th>Brand</th>
								<th>Name</th>
								<th>Amount in Stock</th>
								<th>Price</th>
								<th>Image</th>
								<th>Manage</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${devices}" var="device">
								<tr>
									<td>${device.brand.brandName}</td>
									<td>${device.model}</td>
									<td>${device.amountInStock}</td>
									<td>${device.price}</td>
									<td>image href</td>
									<td><form:form
											action="${pageContext.request.contextPath}/management/deleteDevice"
											method="POST">
											<button value="${brand.id}" name="id"
												class="btn btn-default myButtons col-lg-5">Delete</button>
										</form:form> <form:form
											action="${pageContext.request.contextPath}/management/updateDevice"
											method="GET">
											<button value="${brand.id}" name="id"
												class="btn btn-default myButtons col-lg-5"
												style="margin-left: 20px">Update</button>
										</form:form></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="text-right">
					
				</div>
			</div>
		</div>
	</div>
</div>

<form:form action="${pageContext.request.contextPath}/management/addDevice" method="GET">
	<input type="submit" class="btn btn-success myButtons" style="float:left" value="Add new"/>
	</form:form>