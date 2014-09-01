<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

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
								<th><spring:message code="Brand"/></th>
								<th><spring:message code="Tables.name"/></th>
								<th><spring:message code="Tables.amount"/></th>
								<th><spring:message code="Tables.price"/></th>
								<th><spring:message code="Tables.image"/></th>
								<th class="manage"><spring:message code="Tables.manage"/></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${devices}" var="device">
								<tr>
									<td>${device.brand.brandName}</td>
									<td>${device.model}</td>
									<td>${device.amountInStock}, 
									<a href="${pageContext.request.contextPath}/management/addArrival?deviceId=${device.id}&page=${currentIndex}">  (<spring:message code="Management.devices.addArrival"/>)
									</a></td>
									<td>${device.price}</td>
									<td >
										<img class="imageClip" src="<c:url value="/getImage?id="/>${device.id}" />
									</td>
									<td><form
											action="${pageContext.request.contextPath}/management/deleteDevice"
											method="POST">
											<button value="${device.id}" name="id"
												class="btn btn-default myButtons col-lg-5"><spring:message code="Delete"/>
											</button>
											<input type="hidden" name="page" value="${currentIndex }"/>
										</form> <form
											action="${pageContext.request.contextPath}/management/updateDevice"
											method="GET">
											<button value="${device.id}" name="id"
												class="btn btn-default myButtons col-lg-5"
												style="margin-left: 20px"><spring:message code="Update"/></button>
												<input type="hidden" name="page" value="${currentIndex }"/>
										</form></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="col-md-offset-5">
					<tiles:insertDefinition name="pagination"/>
				</div>
			</div>
		</div>
	</div>
</div>

<form:form action="${pageContext.request.contextPath}/management/addDevice" method="GET">
	<input type="submit" class="btn btn-success myButtons" style="float:left" value="<spring:message code="Add"/>"/>
	<input type="hidden" name="page" value="${currentIndex }"/>
	</form:form>