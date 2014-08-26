<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                    <li class="active">
                       <a href="${contextPath}/management"><i class="fa fa-fw fa-dashboard"></i> Dashboard</a>
                    </li>
                    
                    
                    <li>
                        <a href="${contextPath}/management/sales"><i class="fa fa-fw fa-money"></i>View Purchases</a>
                    </li>
                    <li>
                        <a href="${contextPath}/management/orders"><i class="fa fa-fw fa-shopping-cart"></i>View Orders</a>
                    </li>
                   
                    <li>
                        <a href="${contextPath}/management/arrivals"><i class="fa fa-fw fa-cubes"></i> Arrivals </a>
                    </li>
                     <li>
                        <a href="javascript:;" data-toggle="collapse" data-target="#demo"><i class="fa fa-fw fa-caret-down"></i> Goods <i class="fa fa-fw"></i></a>
                        <ul id="demo" class="collapse">
                            <li>
                                <a href="${contextPath}/management/brands">Brands</a>
                            </li>
                            <li>
                                <a href="${contextPath}/management/devices">Devices</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
