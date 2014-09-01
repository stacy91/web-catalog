<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>



<div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-green">
                            <div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-bar-chart-o"></i> <spring:message code="Management.statistics.arechart"/></h3>
                            </div>
                            <div class="panel-body">
                                <div id="area-example"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.row -->

                <div class="row">
                    <div class="col-lg-4">
                        <div class="panel panel-yellow">
                            <div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-long-arrow-right"></i> <spring:message code="Management.statistics.donutChart"/></h3>
                            </div>
                            <div class="panel-body">
                                <div id="donut-chart"></div>
                                <div class="text-right">
                                    <!-- <a href="#">View Details <i class="fa fa-arrow-circle-right"></i></a> -->
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="panel panel-red">
                            <div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-long-arrow-right"></i> <spring:message code="Management.statistics.lineChart"/></h3>
                            </div>
                            <div class="panel-body">
                                <div id="line-chart"></div>
                                <div class="text-right">
                                    <!-- <a href="#">View Details <i class="fa fa-arrow-circle-right"></i></a> -->
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 ">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-long-arrow-right"></i> <spring:message code="Management.statistics.barChart"/></h3>
                            </div>
                            <div class="panel-body">
                                <div id="bar-chart" ></div>
                                <div class="text-right">
                                    <!-- <a href="#">View Details <i class="fa fa-arrow-circle-right"></i></a> -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                


<script src="<c:url value="/resources/js/morris/morris.min.js"/>"></script>
<script src="<c:url value="/resources/js/morris/morris-data.js"/>"></script>
<script src="<c:url value="/resources/js/morris/raphael.min.js"/>"></script>




<script>
Morris.Area({
    element: 'area-example',
    data: [
	<c:forEach items="${areaChart.keySet()}" var="item">
      { y: '${item}', a: ${areaChart.get(item).orders }, b: ${areaChart.get(item).sales } },
	</c:forEach>
    ],
    xkey: 'y',
    ykeys: ['a', 'b'],
    labels: ['<spring:message code="Orders"/>', '<spring:message code="Purchases"/>'],

    parseTime:false,
    behaveLikeLine:true,
    resize: true
  });
  
Morris.Donut({
	  element: 'donut-chart',
	  data: [
		<c:forEach items="${donutChart.keySet()}" var="item">
	    {value: ${donutChart.get(item)}, label: '<spring:message code="${item}"/>'},
	    </c:forEach>
	  ],
	  backgroundColor: '#ccc',
	  labelColor: '#060',
	  formatter: function (x) { return x},
	  resize: true,
	});


Morris.Line({
	  element: 'line-chart',
	  data: [
		<c:forEach items="${graphChart.keySet()}" var="item">
	    {y: ${graphChart.get(item)}, x: '${item}'},
	    </c:forEach>
	  ],
	xkey: 'x',
	ykeys: ['y'],
	labels:['<spring:message code="Arrivals"/>'],
	parseTime:false,
	resize: true,
	});
	
	
Morris.Bar({
	  element: 'bar-chart',
	  data: [
		<c:forEach items="${barChart.keySet() }" var="item">      
	    {x: '${item}', y: '${barChart.get(item)}'},
	    </c:forEach>	    
	    ],
	  xkey: 'x',
	  ykeys: ['y'],
	  labels: ['<spring:message code="Tables.amount"/>'],
	  barRatio: 0.4,
	  resize: true,
	});
	
</script>            