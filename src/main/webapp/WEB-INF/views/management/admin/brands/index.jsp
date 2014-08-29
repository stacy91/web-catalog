<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<div  class="managementContentWrapperH"> 
<div class="row">
<div class="col-lg-12">
<div class="panel panel-default">
<div class="panel-body">
<div class="table-responsive">
	<table class="table table-hover table-bordered table-striped">
	
	<tr>
		<th><spring:message code="Brand" /></th>
		<th class="manage"><spring:message code="Tables.manage" /> </th> 
	</tr>
		<c:forEach var="brand" items="${brands}">
				<tr>
					<td><a href="${pageContext.request.contextPath}?brandId=${brand.id }">${brand.brandName }</a></td>

					<td class="row" style="width:240px">
					
					<form action="${pageContext.request.contextPath}/management/deleteBrand" method="POST">
					<input type="submit" value="<spring:message code="Delete"/>" class="btn btn-default myButtons col-lg-5"/>
					<input type="hidden" value="${brand.id}" name="id"  /> 
					<input type="hidden" name="page" value="${currentIndex }"/>
					</form>
					
					<form action="${pageContext.request.contextPath}/management/updateBrand" method="GET">
					<input type="submit" value="<spring:message code="Update" />" class="btn btn-default myButtons col-lg-5"/>
					<input type="hidden" value="${brand.id}" name="id"  class="btn btn-default myButtons col-sm-5" style="margin-left: 20px"/>
					<input type="hidden" name="page" value="${currentIndex }"/>
					</form>
					</td>
				</tr>
			</c:forEach>
						
	</table>
	
    		</div>

				
	</div>
	
	<div class="col-md-offset-5">
            	<tiles:insertDefinition name="pagination">
					<tiles:putAttribute name="beginIndex" 	value="${beginIndex}" 	type="string"/>	
					<tiles:putAttribute name="endIndex"   	value="${endIndex}"	 	type="string"/>	
					<tiles:putAttribute name="currentIndex" value="${currentIndex}"	type="string"/>	
					<tiles:putAttribute name="totalPages" 	value="${totalPages}"	type="string"/>	
				</tiles:insertDefinition>	
    </div>
	
	</div>
	</div>
	</div>		

	<form action="${pageContext.request.contextPath}/management/addBrand" method="GET">
	<input type="submit" class="btn btn-success myButtons" style="float:left" value="<spring:message code="Add" />"/>
	<input type="hidden" name="page" value="${currentIndex }"/>
	</form>
</div>




