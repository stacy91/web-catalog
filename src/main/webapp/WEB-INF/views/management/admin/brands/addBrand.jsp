<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
    <div align="center" class="managementContentWrapperWH form-horizontal">
    <form:form  commandName="brand" class="form-horizontal">
    <form:errors path="*" cssClass="bg-danger errorblock" element="div"></form:errors>
  <div class="form-group">
    <label for="brandName" class="col-sm-2 control-label">New brand</label>
    <div class="col-sm-10">
      <form:input path="brandName" class="form-control" id="brandName" placeholder="brand..."/>
    </div>
  </div>
  
  <div class="form-group">
    <div class="row" style="margin:20px 0 0 16px; width:240px;float:right;">
      <form:button type="submit" class="btn btn-default myButtons" name="action">Add</form:button>
      <button class="btn btn-default myButtons" name="action" value="cancel">Cancel</button>
    </div>
  </div>
</form:form>
</div>