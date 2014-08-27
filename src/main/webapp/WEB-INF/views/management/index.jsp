<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



	<ol class="breadcrumb">
                            <li class="active">
                                <i class="fa fa-dashboard"></i> <spring:message code="Dashboard" />
                            </li>
                        </ol>

	<div class="row">
		<div class="col-lg-12">
			<div class="alert alert-info alert-dismissable">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button>
				<i class="fa fa-info-circle"></i> <strong><spring:message code="Welcome" />!</strong> <spring:message code="TodayIs" />
				${date }
			</div>
		</div>
	</div>
	<!-- /.row -->

	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						<i class="fa fa-shopping-cart fa-fw"></i> <spring:message code="LatestOrders" />
				</div>
				<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-bordered table-hover table-striped">
						<thead>
							<tr>

								<th><spring:message code="Tables.Date" /></th>
								<th><spring:message code="Tables.brand/model" /></th>
								<th><spring:message code="Tables.amount" /></th>
								<th><spring:message code="Tables.price" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${orders}" var="order">
								<tr>
									<td>${order.time}</td>
									<td>${order.device.brand.brandName} / ${order.device.model}</td>						
									<td>${order.amount}</td>
									<td>${order.device.price}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="text-right">
					<a href="management/orders"><spring:message code="ViewOrders" /> <i class="fa fa-arrow-circle-right"></i></a>
				</div>
			</div>
			</div>
		</div>
	</div>
	<!--  row -->

	<div class="row"
		style="margin: 40px auto auto auto; padding: 25px 25px 0 25px">

		<div class="col-lg-3 col-md-6" style="margin-left: 13%">
			<div class="panel panel-red width200">
				<div class="panel-heading">
					<div class="row">
						<div class="col-xs-3">
							<i class="fa fa-support fa-5x"></i>
						</div>
						<div class="col-xs-9 text-right">
							<div class="huge">${availaCount }</div>
							<div><spring:message code="CheckAvailable" /></div>
						</div>
					</div>
				</div>
				<a href="management/orders?show=available">
					<div class="panel-footer">
						<span class="pull-left"><spring:message code="ViewDetails" /></span> <span
							class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
		</div>


		<div class="col-lg-3 col-md-6">
			<div class="panel panel-yellow width200">
				<div class="panel-heading">
					<div class="row">
						<div class="col-xs-3">
							<i class="fa fa-shopping-cart fa-5x"></i>
						</div>
						<div class="col-xs-9 text-right">
							<div class="huge">${ordersCount }</div>
							<div><spring:message code="ViewOrders" /></div>
						</div>
					</div>
				</div>
				<a href="management/orders">
					<div class="panel-footer">
						<span class="pull-left"><spring:message code="ViewDetails" /></span> <span
							class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
		</div>
		<div class="col-lg-3 col-md-6">
			<div class="panel panel-green width200">
				<div class="panel-heading">
					<div class="row">
						<div class="col-xs-3">
							<i class="fa fa-tasks fa-5x"></i>
						</div>
						<div class="col-xs-9 text-right">
							<div class="huge">${salesCount }</div>
							<div><spring:message code="BillingHistory" /></div>
						</div>
					</div>
				</div>
				<a href="management/sales">
					<div class="panel-footer">
						<span class="pull-left"><spring:message code="ViewDetails" /></span> <span
							class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
		</div>
	</div>
