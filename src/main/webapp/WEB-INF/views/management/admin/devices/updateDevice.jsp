<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="managementContentWrapperWH">
	<form:form modelAttribute="device" enctype="multipart/form-data">

		<label for="brandDD">Select Brand</label>
		<form:select path="brand" class="form-control">
			<form:option value="" class="form-control">Select</form:option>
			<form:options  items="${brands}" itemValue="id" itemLabel="brandName" />
		</form:select>

		<div class="form-group">
			<label for="modelTXT">Enter Model</label>
			<form:input path="model" class="form-control" id="modelTXT"
				placeholder="Enter model..." />
		</div>
		<div class="form-group">
			<label for="priceTXT">Price</label>
			<form:input path="price" class="form-control" id="priceTXT"
				placeholder="Enter price..." />
		</div>
		<div class="form-group">
			<label for="amountTXT">Amount</label> <form:input path="amountInStock"
				class="form-control" id="amountTXT" placeholder="Enter amount..."  disabled="true"/>
		</div>
		<div class="form-group">
			<label for="exampleInputFile">Upload Image</label> <input type="file"
				name="image">
			<!-- <p class="help-block">Example block-level help text here.</p> -->
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