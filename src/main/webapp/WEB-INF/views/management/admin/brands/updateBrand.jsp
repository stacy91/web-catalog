<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    
    <div class="managementContentWrapperWH">
    <form:form  commandName="brand">
    	<form:errors path="*" cssClass="bg-danger errorblock" element="div"></form:errors>
  	<div class="form-group">
  	
    <label for="brandName"><spring:message code="Update" /> <spring:message code="Brand" /></label>
    
    <div class="form-group">
      <form:input path="brandName" class="form-control" id="brandName"/>
    </div>
  </div>
  
  <div class="form-group">
    <div class="row" style="margin:20px 0 0 0; width:240px;float:right;">
      <form:button type="submit" class="btn btn-default myButtons" name="action"><spring:message code="Update" /></form:button>
      <button class="btn btn-default myButtons" name="action" value="cancel"><spring:message code="Cancel" /></button>
    </div>
  </div>
</form:form>
</div>