<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="managementContentWrapperWH">
	<form:form modelAttribute="order" action="order" method="POST">
	
		<c:choose>
    		<c:when test="${not empty message}">
        		<p class="bg-danger errorblock"><spring:message code="${message}"></spring:message></p>
    		</c:when>    
		</c:choose>
	
		<div class="form-group">
			<form:input path="id" type="hidden"/>
		</div>
		
		<div class="form-group">
			<label for="deviceTXT"><spring:message code="Tables.brand/model"/></th></label>
			<input value="${order.device.brand.brandName} / ${order.device.model}"
				class="form-control" id="amountTXT" disabled/>
		</div>
		
		<div class="form-group">
			<label for="amountTXT"><spring:message code="Tables.amount"/></label> <form:input path="amount"
				class="form-control" id="amountTXT" placeholder="Enter amount..." disabled="true"/>
		</div>
		
		<div class="form-group">
			<label for="amountTXT"><spring:message code="Tables.price"/></label> <input value="${order.device.price}"
				class="form-control" id="amountTXT" disabled/>
		</div>
		
		<div class="form-group">
			<div class="row"
				style="margin: 20px 0 0 16px; width: 240px; float: right;">
				<form:button type="submit" class="btn btn-default myButtons"
					name="action"><spring:message code="Buy"/></form:button>
				<button class="btn btn-default myButtons" name="action"
					value="cancel"><spring:message code="Cancel"/></button>
			</div>
		</div>
	</form:form>
</div>