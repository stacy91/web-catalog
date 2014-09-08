<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                    <li class="active">
                       <a href="${contextPath}/management"><i class="fa fa-fw fa-dashboard"></i> <spring:message code="Dashboard"/></a>
                    </li>
                    
                    
                    <li>
                        <a href="${contextPath}/management/sales"><i class="fa fa-fw fa-money"></i> <spring:message code="Purchases"/></a>
                    </li>
                    <li>
                        <a href="${contextPath}/management/orders"><i class="fa fa-fw fa-shopping-cart"></i> <spring:message code="Orders"/></a>
                    </li>
                    
                    <security:authorize access="hasRole('ROLE_ADMIN')">
                    <li>
                        <a href="${contextPath}/management/statistics"><i class="fa fa-fw fa-bar-chart-o"></i> <spring:message code="Statistics"/> </a>
                    </li>
                    <li>
                        <a href="${contextPath}/management/allOS"><i class="fa fa-fw  fa-asterisk"></i> <spring:message code="Orders"/> / <spring:message code="Purchases"/> </a>
                    </li>
                   
                    <li>
                        <a href="${contextPath}/management/arrivals"><i class="fa fa-fw fa-cubes"></i> <spring:message code="Arrivals"/> </a>
                    </li>
                    <li>
                        <a href="${contextPath}/management/users"><i class="fa fa-fw fa-group"></i> <spring:message code="Users"/> </a>
                    </li>
                     <li>
                        <a href="javascript:;" data-toggle="collapse" data-target="#demo"><i class="fa fa-fw fa-caret-down"></i><spring:message code="Goods"/> <i class="fa fa-fw"></i></a>
                        <ul id="demo" class="collapse">
                            <li>
                                <a href="${contextPath}/management/brands"><spring:message code="Brands"/></a>
                            </li>
                            <li>
                                <a href="${contextPath}/management/devices"><spring:message code="Devices"/></a>
                            </li>
                        </ul>
                    </li>
                    </security:authorize>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
