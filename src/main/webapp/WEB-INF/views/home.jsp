<!DOCTYPE HTML>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



<c:url var="firstUrl" value="/?page=1" />
<c:url var="lastUrl" value="/?page=${totalPages}&brandId=${brandId }&search=${search }" />
<c:url var="prevUrl" value="/?page=${currentIndex - 1}&brandId=${brandId }&search=${search }" />
<c:url var="nextUrl" value="/?page=${currentIndex + 1}&brandId=${brandId }&search=${search }" />


	<div class="catalogToolBar">
		<div class="form-inline">
		<form method="get">
			<label>
				Select Brand:		
				<select class="form-control" onchange="this.form.submit()" name="brandId">
					<option label="" value="" />
					<c:forEach items="${brands}" var="itemBrand">
						<option label="${itemBrand.brandName }" value="${itemBrand.id }" ${itemBrand.id == brandId ? "selected" : "" }/>
					</c:forEach>
				</select>			
    		</label>
		</form>
			
			<form method="get">
    		<input type="text" class="form-control" id="searchTXT" placeholder="search..." name="search">
			<button type="submit" class="btn btn-default">Search</button>
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
                                <h4><a href="#" > Brand:&nbsp;&nbsp;<b>${itemDevice.brand.brandName }</b></a>
                                </h4>
                                <p> Model:&nbsp;&nbsp;&nbsp;&nbsp;<b>${itemDevice.model }</b></p>
                            </div>
                            <div class="order">
                                <form class="form-inline">
                                <input class="form-control" type="text" value="1"/>
                                <input class="btn btn-danger" type="submit" value="Order"/>
                                </form>
                            </div>
                        </div>
                    </div>
                    </c:forEach>

                   


                </div>

            </div>
            
            <div class="col-md-offset-5">
            <ul class="pagination">
        <c:choose>
            <c:when test="${currentIndex == 1}">
                <li class="disabled"><a>&lt;&lt;</a></li>
                <li class="disabled"><a>&lt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${firstUrl}">&lt;&lt;</a></li>
                <li><a href="${prevUrl}">&lt;</a></li>
            </c:otherwise>
        </c:choose>
        <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
            <c:url var="pageUrl" value="/?page=${i}&brandId=${brandId }&search=${search }" />
            <c:choose>
                <c:when test="${i == currentIndex}">
                    <li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:choose>
            <c:when test="${currentIndex == totalPages}">
                <li class="disabled"><a>&gt;</a></li>
                <li class="disabled"><a>&gt;&gt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${nextUrl}">&gt;</a></li>
                <li><a href="${lastUrl}">&gt;&gt;</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
    </div>

        </div>
    </div>
    

  

    <!-- /.container -->

    

