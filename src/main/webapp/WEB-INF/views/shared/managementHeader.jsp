<!DOCTYPE HTML>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

		<c:set var="langStr" value="${pageContext.response.locale}" />
		<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
								
				
				<a class="navbar-brand" href="${pageContext.request.contextPath}"><spring:message code="Catalog"/></a>
			</div>
			<!-- Top Menu Items -->
			
			<div style="width:200px; float:right; margin:10px 10px 0 10px">
			<p style="float:left; margin: 6px 20px 0 0; color:white"><spring:message code="Lang"/>:</p>
			<form method="get">
			<select class="form-control" style="width:110px;" name="lang" onchange="this.form.submit()">
  				<option value="en" ${ langStr == 'en' ? 'selected' : ''}><spring:message code="Lang.en"/></option>
  				<option value="ru" ${ langStr == 'ru' ? 'selected' : ''}><spring:message code="Lang.ru"/></option>
			</select>
			</form>
			
			<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><i class="fa fa-user"></i>
					<spring:message code="Welcome"/>,
					  <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="?lang=en&${requestScope['javax.servlet.forward.query_string']}">"><i class="fa fa-fw fa-user"></i> English</a>
						</li>
						<li class="divider"></li>
						<li><a href="?lang=en&${requestScope['javax.servlet.forward.query_string']}">"><i class="fa fa-fw fa-power-off"></i> Russian</a></li>
					</ul></li>
			
			</div>
			
			<ul class="nav navbar-right top-nav">
				
				<security:authorize access="isAuthenticated()">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><i class="fa fa-user"></i>
					<spring:message code="Welcome"/>, 
					 <security:authentication property="principal.username"/>
					  <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="#"><i class="fa fa-fw fa-user"></i> <spring:message code="Profile"/></a>
						</li>
						<li class="divider"></li>
						<li><a href="${pageContext.request.contextPath}/j_spring_security_logout"><i class="fa fa-fw fa-power-off"></i> <spring:message code="LogOut"/></a></li>
					</ul></li>
					</security:authorize>
			</ul>