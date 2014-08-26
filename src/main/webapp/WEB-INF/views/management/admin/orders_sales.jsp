<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
				Show:		
				<select class="form-control" onchange="this.form.submit()" name="show" >
					<option label="All"/>
					<option label="Orders" value="orders" ${ show == 'orders' ? 'selected' : ''}/>
					<option label="Sales" value="sales" ${ show == 'sales' ? 'selected' : ''}/>
				</select>	
						
    		</label>
		</form>
			</div>
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-bordered table-hover table-striped">
						<thead>
							<tr>
								<th>Type</th>
								<th>Date</th>
								<th>User / role</th>
								<th>Brand / model</th>
								<th>Amount</th>
								<th>Price</th>
								<th>Manage</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${o_s}" var="item">
								<tr>
									<td>${item.isSold == false ? 'Order' : 'Sale' }</td>
									<td>${item.time}</td>
									<td>${item.user.login} / ${item.user.role.name}</td>	
									<td>${item.device.brand.brandName} / ${item.device.model}</td>						
									<td>${item.amount}</td>
									<td>${item.device.price}</td>
									<td>
									<form
											action="${pageContext.request.contextPath}/management/deleteOS"
											method="POST">
											<button value="${item.id}" name="id"
												class="btn btn-default myButtons col-lg-5">Delete</button>
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