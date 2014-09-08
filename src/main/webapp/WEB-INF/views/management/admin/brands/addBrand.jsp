	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    
    <div class="managementContentWrapperWH">
    <form:form  modelAttribute="brand" method="POST">
    <form:errors path="*" cssClass="bg-danger errorblock" element="div"></form:errors>
 	 <div class="form-group">
    	<label for="brandName"><spring:message code="Add" /> <spring:message code="Brand" /></label>
      <form:input path="brandName" class="form-control" id="brandName" />
  </div>
  <div class="form-group">
    <div class="row" style="margin:20px 0 0 16px; width:240px;float:right;">
      <input  type="submit" class="btn btn-default myButtons" name="action" value="<spring:message code="Add" />"/>
      <button  type="submit" class="btn btn-default myButtons" name="action" value="cancel"><spring:message code="Cancel" /></button>
      <input type="hidden" name="page" value="page"/>
    </div>
  </div>
</form:form>
</div>