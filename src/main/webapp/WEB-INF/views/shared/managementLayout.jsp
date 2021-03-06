<!DOCTYPE HTML>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<html>
<head>

<c:set var="pageTitle"><tiles:getAsString name="pageTitle"/></c:set>
<title><spring:message code="${pageTitle}"></spring:message></title>

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

<script src="<c:url value="/resources/js/jquery/jquery-1.11.0.js" />"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js"/>"></script>


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
						<c:set var="wrapperTitle"><tiles:getAsString name="wrapperTitle"/></c:set>

							<spring:message code="Dashboard"/> <small><spring:message code="${wrapperTitle}"/></small>
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