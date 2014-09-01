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
								<th><spring:message code="Tables.Date"/></th>
								<th><spring:message code="Tables.brand/model"/></th>
								<th><spring:message code="Tables.amount"/></th>
								<th><spring:message code="Tables.price"/></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${sales}" var="sale">
								<tr>
									<td>${sale.timeSold}</td>
									<td>${sale.device.brand.brandName} / ${sale.device.model}</td>						
									<td>${sale.amount}</td>
									<td>${sale.device.price}$</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="text-right">
					
				</div>
			</div>
			
			<div class="col-md-offset-5">
            	<tiles:insertDefinition name="pagination"/>
   		   </div>
    
		</div>
	</div>
</div>