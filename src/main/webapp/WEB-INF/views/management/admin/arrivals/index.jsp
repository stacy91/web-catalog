<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
			
			</div>
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-bordered table-hover table-striped">
						<thead>
							<tr>							
								<th><spring:message code="Tables.Date" /></th>
								<th><spring:message code="Tables.user/role" /></th>
								<th><spring:message code="Tables.brand/model" /></th>
								<th><spring:message code="Tables.amount" /></th>
								<th><spring:message code="Tables.price" /></th>
								<th class="manage"><spring:message code="Tables.manage" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${arrivals}" var="arrival">
								<tr>
									<td>${arrival.time}</td>
									<td>${arrival.user.login} / ${arrival.user.role.name}</td>
									<td>${arrival.device.brand.brandName} / ${arrival.device.model}</td>						
									<td>${arrival.amount}</td>
									<td>${arrival.price}</td>
									<td><form:form
											action="${pageContext.request.contextPath}/management/deleteArrival"
											method="POST">
											<button value="${arrival.id}" name="id"
												class="btn btn-default myButtons col-lg-5"><spring:message code="Delete" /></button>
										</form:form> <form:form
											action="${pageContext.request.contextPath}/management/updateArrival"
											method="GET">
											<button value="${arrival.id}" name="id"
												class="btn btn-default myButtons col-lg-5"
												style="margin-left: 20px"><spring:message code="Update" /></button>
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
