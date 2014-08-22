<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="managementContentWrapperWH">
	<form:form modelAttribute="arrival" action="updateArrival" method="POST">


		<div class="form-group">
			<label for="userTXT">${arrival.user.login} / ${arrival.user.role.name}</label>
			<form:input path="id" type="hidden"/>
		</div>
		
		<div class="form-group">
			<label for="deviceTXT">${arrival.device.brand.brandName} / ${arrival.device.model}</label>
		</div>
		
		<div class="form-group">
			<label for="amountTXT">Amount</label> <form:input path="amount"
				class="form-control" id="amountTXT" placeholder="Enter amount..."/>
		</div>
		
		<div class="form-group">
			<label for="amountTXT">Price</label> <form:input path="price"
				class="form-control" id="amountTXT" placeholder="Enter price..."/>
		</div>
		
		<div class="form-group">
			<div class="row"
				style="margin: 20px 0 0 16px; width: 240px; float: right;">
				<form:button type="submit" class="btn btn-default myButtons"
					name="action">Update</form:button>
				<button class="btn btn-default myButtons" name="action"
					value="cancel">Cancel</button>
			</div>
		</div>
	</form:form>
</div>