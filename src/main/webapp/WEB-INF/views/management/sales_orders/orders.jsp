<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:url var="firstUrl" value="?page=1" />
<c:url var="lastUrl" value="?page=${totalPages}&show=${show }" />
<c:url var="prevUrl" value="?page=${currentIndex - 1}&show=${show }" />
<c:url var="nextUrl" value="?page=${currentIndex + 1}&show=${show }" />


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
									<td>${order.time}</td>
									<td>${order.device.brand.brandName} / ${order.device.model}</td>						
									<td>${order.amount}</td>
									<td>${order.device.price}$</td>
									<td><form:form
											action="${pageContext.request.contextPath}/management/deleteOrder"
											method="POST">
											<button value="${order.id}" name="id"
												class="btn btn-default myButtons col-lg-5"><spring:message code="Cancel"/></button>
										</form:form> <form:form
											action="${pageContext.request.contextPath}/management/order"
											method="GET">
											<button value="${order.id}" name="id"
												class="btn btn-default myButtons col-lg-5"
												style="margin-left: 20px"><spring:message code="Buy"/></button>
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
            <ul class="pagination">
        <c:choose>
            <c:when test="${currentIndex == 1}">
                <li class="disabled"><a>&lt;&lt;</a></li>
                <li class="disabled"><a>&lt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${firstUrl}">&lt;&lt;</a></li>
                <li><a href="${prevUrl}">&lt;</a></li>
            </c:otherwise>
        </c:choose>
        <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
            <c:url var="pageUrl" value="?page=${i}&show=${show }" />
            <c:choose>
                <c:when test="${i == currentIndex}">
                    <li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:choose>
            <c:when test="${currentIndex == totalPages}">
                <li class="disabled"><a>&gt;</a></li>
                <li class="disabled"><a>&gt;&gt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${nextUrl}">&gt;</a></li>
                <li><a href="${lastUrl}">&gt;&gt;</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
    </div>
			
		</div>
	</div>
</div>