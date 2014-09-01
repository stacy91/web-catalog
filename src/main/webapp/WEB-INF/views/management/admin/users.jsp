<!DOCTYPE HTML>
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
								<th><spring:message code="Login" /></th>
								<th><spring:message code="Role" /></th>
								<th class="manage"><spring:message code="Tables.manage" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${users}" var="item">
								<tr>
									<td>${item.login}</td>
									<td>${item.role.name}</td>
									<td >
									<form action="${pageContext.request.contextPath}/management/deleteUser"
											method="POST">
											<button value="${item.id}" name="id"
												class="btn btn-default myButtons col-lg-5"><spring:message code="Delete" /></button>
												<input type="hidden" name="page" value="${currentIndex }"/>
									</form> 
									
									<form action="${pageContext.request.contextPath}/management/changeRole"
											method="POST">
											<button value="${item.id}" name="id"
												class="btn btn-default myButtons col-lg-5" style="width:120px"><spring:message code="ChangeRole" /></button>
												
											<input type="hidden" name="page" value="${currentIndex }" />
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
			
			<tiles:insertDefinition name="pagination"/>
			
    		</div>
			
		</div>
	</div>
</div>