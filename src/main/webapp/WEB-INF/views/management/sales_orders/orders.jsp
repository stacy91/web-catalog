<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:set var="queryStr" value="?show=${show }&"/>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
			<form method="get" class="form-inline">
			<input type="hidden" name="page" value="${1 }"/>
			<label>
				<spring:message code="Show"/>:		
				<select class="form-control" onchange="this.form.submit()" name="show" >
					<option label="<spring:message code="ShowAll"/>" value="all" ${ show == 'all' ? 'selected' : ''}/>
					<option label="<spring:message code="ShowAvailable"/>" value="available" ${ show == 'available' ? 'selected' : ''}/>
				</select>	
						
    		</label>
		</form>
			</div>
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-bordered table-hover table-striped">
						<thead>
							<tr>	
								<th><spring:message code="Tables.Date"/></th>
								<th><spring:message code="Tables.brand/model"/></th>
								<th><spring:message code="Tables.amount"/></th>
								<th><spring:message code="Tables.price"/></th>
								<th class="manage"><spring:message code="Tables.manage"/></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${orders}" var="order">
								<tr>
									<td>${order.timeOrdered}</td>
									<td>${order.device.brand.brandName} / ${order.device.model}</td>						
									<td>${order.amount}</td>
									<td>${order.device.price}$</td>
									<td><form:form
											action="${pageContext.request.contextPath}/management/deleteOrder"
											method="POST">
											<input type="hidden" name="show" value="${show }"/>
											<button value="${order.id}" name="id"
												class="btn btn-default myButtons col-lg-5"><spring:message code="Cancel"/></button>
												
												
												<input type="hidden" name="page" value="${page }" />
										</form:form> <form:form
											action="${pageContext.request.contextPath}/management/order"
											method="GET">
											<input type="hidden" name="show" value="${show }"/>
											
											<button value="${order.id}" name="id"
												class="btn btn-default myButtons col-lg-5"
												style="margin-left: 20px"><spring:message code="Buy"/></button>
												<input type="hidden" name="page" value="${page }" />
												
										</form:form></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="text-right">
					
				</div>
			</div>
			
			<div class="col-md-offset-5">
            	<tiles:insertDefinition name="pagination">
            		<tiles:putAttribute name="queryPrmtrs" 	value="${queryStr}" 	type="string"/>	
				</tiles:insertDefinition>
    		</div>
			
		</div>
	</div>
</div>