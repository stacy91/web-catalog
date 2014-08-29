<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    
    <div class="managementContentWrapperWH">
    <form:form  commandName="brand" >
    <form:errors path="*" cssClass="bg-danger errorblock" element="div"></form:errors>
 	 <div class="form-group">
    	<label for="brandName"><spring:message code="Add" /> <spring:message code="Brand" /></label>
      <form:input path="brandName" class="form-control" id="brandName" />
  </div>
  <div class="form-group">
    <div class="row" style="margin:20px 0 0 16px; width:240px;float:right;">
      <input  type="submit" class="btn btn-default myButtons" name="action" value="<spring:message code="Add" />"/>
      <input  type="submit" class="btn btn-default myButtons" name="action" value="<spring:message code="Add" />"/>
      <input type="hidden" name="page" value="page"/>
    </div>
  </div>
</form:form>
</div>