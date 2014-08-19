<!DOCTYPE HTML>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><tiles:insertAttribute name="pageTitle" /></title>

<link
	href="<c:url value="/resources/css/management/bootstrap.min.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/management/sb-admin.css"/>"
	rel="stylesheet">
<link
	href="<c:url value="/resources/css/management/font-awesome-4.1.0/css/font-awesome.min.css"/>"
	rel="stylesheet" type="text/css">
<link
	href="<c:url value="/resources/css/style.css"/>"
	rel="stylesheet" type="text/css">	

<script src="<c:url value="/resources/js/jquery-1.11.0.js" />"></script>
<script src="<c:url value="/resources/js/jquery-ui.js" />"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>

</head>
<body>

	<div id="wrapper">
		
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<tiles:insertAttribute name="header"></tiles:insertAttribute>
			<tiles:insertAttribute name="left"></tiles:insertAttribute>
		</nav>
		
		<div id="page-wrapper">

			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							Dashboard <small><tiles:insertAttribute
									name="wrapperTitle" /></small>
						</h1>

					</div>
				</div>

				<tiles:insertAttribute name="body"></tiles:insertAttribute>

			</div>
			<div style="float: right; height: 100%; width: 100%;"></div>
		</div>
	</div>
</body>
</html>