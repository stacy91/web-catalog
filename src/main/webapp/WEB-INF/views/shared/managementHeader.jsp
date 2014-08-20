<!DOCTYPE HTML>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>



		<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
								
				
				<a class="navbar-brand" href="${pageContext.request.contextPath}">Catalog</a>
			</div>
			<!-- Top Menu Items -->
			<security:authorize access="!isAuthenticated()">
			<form method="POST" action="${pageContext.request.contextPath}/j_spring_security_check"
			style="color:#999; float:right; margin:10px;" class="form-inline" >
  			<div class="form-group">
    			<input class="form-control" id="username" name="Login"  type="text" placeholder="Enter login">
  			</div>
  			<div class="form-group">
    			<input class="form-control" id="password" name="Password"  type="password" placeholder="Password">
  			</div>
  			
  <!-- <div class="checkbox">
    <label>
      <input type="checkbox"> Remember me
    </label>
  </div> -->
  			<button type="submit" class="btn btn-default" style="color:#333">Sign in</button>
  			<a href="" style="margin: 0 10px 0 10px;">Sign up</a>
			</form>
			</security:authorize>
			
			<ul class="nav navbar-right top-nav">
				
				<security:authorize access="isAuthenticated()">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><i class="fa fa-user"></i>
					Welcome, 
					 <security:authentication property="principal.username"/>
					  <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="#"><i class="fa fa-fw fa-user"></i> Profile</a>
						</li>
						<li class="divider"></li>
						<li><a href="${pageContext.request.contextPath}/j_spring_security_logout"><i class="fa fa-fw fa-power-off"></i> Log
								Out</a></li>
					</ul></li>
					</security:authorize>
			</ul>