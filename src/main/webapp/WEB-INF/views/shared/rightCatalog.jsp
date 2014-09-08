<!DOCTYPE HTML>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<security:authorize access="!isAuthenticated()">
			
			<form method="POST" action="${pageContext.request.contextPath}/j_spring_security_check"
			 class="login" >
  			<div class="form-group">
  			<p><spring:message code="RegisterToOrder"/></p>
  			<c:if test="${ not empty errorMsg}">
  				<p class="error" ><spring:message code="${errorMsg}"/></p>
  			</c:if>
    			<input class="form-control" id="username" name="Login"  type="text" placeholder="<spring:message code="Login"/>...">
  			</div>
  			<div class="form-group">
    			<input class="form-control" id="password" name="Password"  type="password" placeholder="<spring:message code="Password"/>...">
  			</div>
  			

  			<button type="submit" class="btn btn-default" style="color:#333" ><spring:message code="SignIn"/></button>
  			<a href="${pageContext.request.contextPath}/register" style="margin: 0 10px 0 5px;"><spring:message code="SignUp"/></a>
			</form>
</security:authorize>
<security:authorize access="isAuthenticated()">
	<div class="login">
		<ol class="breadcrumb bgWhite">
                            <li class="">
        <i class="fa fa-dashboard header"><a href="management"><spring:message code="Dashboard"/> </a> </i> 
        <i class="fa fa-shopping-cart"><a href="${pageContext.request.contextPath}/management/orders"><spring:message code="ViewOrders"/></a> </i> 
        <i class="fa fa-money"><a href="${pageContext.request.contextPath}/management/sales"><spring:message code="ViewPurchases"/></a> </i> 
        </li>
        </ol>
			 

	</div>
	
</security:authorize>