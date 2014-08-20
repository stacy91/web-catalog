<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="managementContentWrapperWH">
	<form:form modelAttribute="user">

		<div class="form-group">
			<label for="priceTXT">Login</label>
			<form:input path="login" class="form-control" id="priceTXT"
				placeholder="Enter login..." />
		</div>
		<div class="form-group">
			<label for="amountTXT">Password</label> <form:input path="password" type="password"
				class="form-control" id="amountTXT" placeholder="Enter password..."/>
		</div>
		<div class="form-group">
			<label for="amountTXT">Confirm password</label>
		<input type="input" type="password"
				class="form-control"  placeholder="Confirm password..."name="confirmPas">
		</div>
		<div class="form-group">
			<div class="row"
				style="margin: 20px 0 0 16px; width: 240px; float: right;">
				<form:button type="submit" class="btn btn-default myButtons"
					name="action">Add</form:button>
				<button class="btn btn-default myButtons" name="action"
					value="cancel">Cancel</button>
			</div>
		</div>
	</form:form>
</div>