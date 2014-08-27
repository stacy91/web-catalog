<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="managementContentWrapperWH">
	<form:form modelAttribute="device" enctype="multipart/form-data">

		<label for="brandDD"><spring:message code="SelectBrand"/></label>
		<form:select path="brand" id="brandDD" class="form-control">
			<form:option value="" class="form-control">-- <spring:message code="SelectBrand"/></form:option>
			<form:options  items="${brands}" itemLabel="brandName" itemValue="id"/>
		</form:select>

		<div class="form-group">
			<label for="modelTXT"><spring:message code="Model"/></label>
			<form:input path="model" class="form-control" id="modelTXT" />
		</div>
		<div class="form-group">
			<label for="priceTXT"><spring:message code="Tables.price"/></label>
			<form:input path="price" class="form-control" id="priceTXT" />
		</div>
		<div class="form-group">
			<label for="amountTXT"><spring:message code="Tables.amount"/></label> <form:input path="amountInStock"
				class="form-control" id="amountTXT" value="0" disabled="true" />
		</div>
		<div class="form-group">
			<label for="imgU" class="btn btn-default"><spring:message code="UploadImage"/></label>
			<input type="file" name="image" id="imgU" style="visibility:hidden">
			
		</div>
		<div class="form-group">
			<div class="row"
				style="margin: 20px 0 0 16px; width: 240px; float: right;">
				<form:button type="submit" class="btn btn-default myButtons"
					name="action"><spring:message code="Add"/></form:button>
				<button class="btn btn-default myButtons" name="action" translate="no"
					value="cancel"><spring:message code="Cancel"/></button>
			</div>
		</div>
	</form:form>
</div>