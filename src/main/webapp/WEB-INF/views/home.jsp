<!DOCTYPE HTML>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<script type="text/javascript">
    
    
    
    </script>

<c:set var="queryStr" value="?brandId=${brandId }&search=${search }&"/>


	<div class="catalogToolBar">
		<div class="form-inline">
		<form method="get">
			<label>
				<spring:message code="SelectBrand" />:		
				<select class="form-control" onchange="this.form.submit()" name="brandId">
					<option label=" --" value="" />
					<c:forEach items="${brands}" var="itemBrand">
						<option label="${itemBrand.brandName }" value="${itemBrand.id }" ${itemBrand.id == brandId ? "selected" : "" }/>
					</c:forEach>
				</select>			
    		</label>
		</form>
			
			<form method="get">
    		<input type="text" class="form-control" id="searchTXT" placeholder="<spring:message code="Search"/>..." name="search">
			<button type="submit" class="btn btn-default"><spring:message code="Search"/></button>
			</form>
  		</div>
  </div>

 	<!-- Page Content -->
    <div class="container">
            <div class="col-md-9">

                <div class="row carousel-holder">

                <div class="row">
					
					<c:forEach items="${devices}" var="itemDevice">
                    <div class="col-sm-4 col-lg-4 col-md-4">
                        <div class="thumbnail">
                            <img src="<c:url value="/getImage?id="/>${itemDevice.id}" />
                            <div class="caption">
                                <h4 class="pull-right">$${itemDevice.price }</h4>
                                <h4><a href="?brandId=${itemDevice.brand.id }" > <spring:message code="Brand"/>:&nbsp;&nbsp;<b>${itemDevice.brand.brandName }</b></a>
                                </h4>
                                <p> <spring:message code="Model"/>:&nbsp;&nbsp;&nbsp;&nbsp;<b>${itemDevice.model }</b></p>
                            </div>
                            
                            <security:authorize access="isAuthenticated()">
                            <div class="order">
                                <form class="form-inline orderForm"  >
                                <input class="form-control numbersOnly" type="text" name="amount" value="1" />
                                <button class="btn btn-danger" type="submit"  id="OrderBtn">
                                	<spring:message code="Order"/>
                                </button>
                                <input type="hidden" value="${itemDevice.id}" name="deviceId"/>
                                <input type="hidden" value="<security:authentication property="principal.username"/>" name="login" />
                                <%-- <input type="hidden" value="${brandId }" name="brandId"/>
                                <input type="hidden" value="${search }" name="search"/>
                                <input type="hidden" value="${currentIndex }" name="page"/> --%>
                                </form>
                            </div>
                            </security:authorize>
                            
                        </div>
                    </div>
                    </c:forEach>

                </div>

            </div>
            
            <div class="col-md-offset-5">
            
            	<tiles:insertDefinition name="pagination">
            		<tiles:putAttribute name="queryPrmtrs" 	value="${queryStr}" 	type="string"/>	
					<tiles:putAttribute name="beginIndex" 	value="${beginIndex}" 	type="string"/>	
					<tiles:putAttribute name="endIndex"   	value="${endIndex}"	 	type="string"/>	
					<tiles:putAttribute name="currentIndex" value="${currentIndex}"	type="string"/>	
					<tiles:putAttribute name="totalPages" 	value="${totalPages}"	type="string"/>	
				</tiles:insertDefinition>
				
    		</div>
    		
        </div>
    </div>
    
	
		
  		
	<div id="result"></div>
    
    <script type="text/javascript">
    
$('.orderForm').submit(function(e){
    	
    	e.preventDefault();
    	var postData = $(this).serializeArray();
    	 $.ajax({
             url : 'order',
             type: 'POST',
             data: postData,
             success : function(data) {            	 
                 $('#result').html(data);
                 $('#NotificationWindow').modal('show');
             }           
         });   	
    });
    
    //validation
    $('.numbersOnly').keyup(function () {
        if (this.value != this.value.replace(/[^0-9]/g, '')) {
           this.value = 1;
        }
    });
    
    $('.numbersOnly').focusout(function (){
    	if (this.value == '') {
            this.value = 1;
         }
    });
    </script>

  

    <!-- /.container -->

    

