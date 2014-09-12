<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:set var="queryStr" value="?show=${show }&"/>
<spring:message code="Tables.Order"  var="order"/>
<spring:message code="Tables.Sale"  var="sale"/>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
			<form method="get" class="form-inline">
			<input type="hidden" name="page" value="${1}"/>
			<label>
				<spring:message code="Show" />:		
				<select class="form-control" onchange="this.form.submit()" name="show" >
					<option label="<spring:message code="ShowAll" />"/>
					<option label="<spring:message code="ShowOrders" />" value="orders" ${ show == 'orders' ? 'selected' : ''}/>
					<option label="<spring:message code="ShowSales" />" value="sales" ${ show == 'sales' ? 'selected' : ''}/>
				</select>			
    		</label>
		</form>
		
		
			</div>
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-bordered table-hover table-striped">
						<thead>
							<tr>							
								<th><spring:message code="Tables.type" /></th>
								<th><spring:message code="Tables.Date" /></th>
								<th><spring:message code="Tables.user/role" /></th>
								<th><spring:message code="Tables.brand/model" /></th>
								<th><spring:message code="Tables.amount" /></th>
								<th><spring:message code="Tables.price" /></th>
								<th><spring:message code="Tables.manage" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${o_s}" var="item">
								<tr>
									<%-- <spring:message c="<c:out value="${com.helpers.Roles.getType(item.id)}" />" var="role"></spring:message> --%>
									<td>${item.isSold == false ? order : sale }</td>
									<td>${item.isSold == false ? item.timeOrdered : item.timeSold}</td>
									<td>${item.user.login} / ${item.user.role.name}</td>	
									<td>${item.device.brand.brandName} / ${item.device.model}</td>						
									<td>${item.amount}</td>
									<td>${item.device.price}</td>
									<td>
									<form
											action="${pageContext.request.contextPath}/management/deleteOS"
											method="POST">
											<button value="${item.id}" name="id"
												class="btn btn-default myButtons col-lg-5"><spring:message code="Delete" /></button>
												<input type="hidden" name="page" value="${currentIndex }"/>
												<input type="hidden" name="show" value="${show }"/>
										</form> 
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