<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="managementContentWrapperWH">
	<form:form modelAttribute="arrival" action="updateArrival" method="POST">


		<div class="form-group">
			<label for="userTXT"><spring:message code="Tables.user/role" /></label>
			<input	class="form-control" disabled value="${arrival.user.login} / ${arrival.user.role.name}"/>
			<form:input path="id" type="hidden"/>
			<form:input path="device" type="hidden"/>
			<form:input path="user" type="hidden"/>
		</div>
		
		<div class="form-group">
			<label for="userTXT"><spring:message code="Tables.brand/model" /></label>
			<input	class="form-control" disabled value="${arrival.device.brand.brandName} / ${arrival.device.model}"/>
		</div>
		
		<div class="form-group">
			<label for="amountTXT"><spring:message code="Tables.amount" /></label> <form:input path="amount"
				class="form-control" id="amountTXT" />
		</div>
		
		<div class="form-group">
			<label for="amountTXT"><spring:message code="Tables.price" /></label> <form:input path="price"
				class="form-control" id="amountTXT" />
		</div>
		
		<div class="form-group">
			<div class="row"
				style="margin: 20px 0 0 16px; width: 240px; float: right;">
				<button type="submit" class="btn btn-default myButtons"
					name="action"><spring:message code="Update" /></button>
				<button class="btn btn-default myButtons" name="action"
					value="cancel"><spring:message code="Cancel" /></button>
				<input type="hidden" name="page" value="${page}"/>
			</div>
		</div>
	</form:form>
</div>