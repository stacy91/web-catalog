<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="managementContentWrapperWH">
	<form:form modelAttribute="user" enctype="multipart/form-data">

		<label >Update your information</label>

		<form:input path="id" type="hidden"/>
		<div class="form-group">
			<label for="modelTXT">User Role</label>
			<input value="${user.role.name }" class="form-control" disabled/>
		</div>
		
		<div class="form-group">
			<label >Login</label>
			<form:input path="login" type="text" class="form-control" />
			<form:input path="password" type="hidden"/>
			<form:input path="id" type="hidden" />
		</div>
		
		<div class="form-group">
			<label >Old password</label>
			<input type="password" name="oldPassword" class="form-control" />
		</div>
		<div class="form-group">
			<label >New password</label>
			<input type="password" name="newPassword" class="form-control" />
		</div>
		<div class="form-group">
			<label >Repeat password</label>
			<input type="password" name="repeatPassword" class="form-control" />
		</div>
		
		<div class="form-group">
			<div class="row"
				style="margin: 20px 0 0 16px; width: 240px; float: right;">
				<form:button type="submit" class="btn btn-default myButtons"
					name="action" >Update</form:button>
				<button class="btn btn-default myButtons" name="action"
					value="cancel">Cancel</button>
			</div>
		</div>
		
	</form:form>
</div>