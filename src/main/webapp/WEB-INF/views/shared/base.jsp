<!DOCTYPE HTML>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>  

<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><tiles:insertAttribute name="pageTitle"></tiles:insertAttribute></title>  
<link
	href="<c:url value="/resources/css/management/bootstrap.min.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/management/sb-admin.css"/>"
	rel="stylesheet">
<link
	href="<c:url value="/resources/css/management/font-awesome-4.1.0/css/font-awesome.min.css"/>"
	rel="stylesheet" type="text/css">
<link href="<c:url value="/resources/css/style.css"/>"
	rel="stylesheet" type="text/css">	

<script src="<c:url value="/resources/js/jquery/jquery-1.11.0.js" />"></script>
<script src="<c:url value="/resources/js/jquery/jquery-ui.js" />"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js"/>"></script>
</head> 

<body style="background-color: white;">
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    	<tiles:insertAttribute name="header"></tiles:insertAttribute>
    </nav>
    <tiles:insertAttribute name="rightCatalog"></tiles:insertAttribute>
    <tiles:insertAttribute name="content"></tiles:insertAttribute>
    <tiles:insertAttribute name="footer"></tiles:insertAttribute>
    

</body>
</html>