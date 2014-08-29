<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>


<c:set var="beginIndexStr"> <tiles:insertAttribute name="beginIndex"/> </c:set>
<c:set var="endIndexStr"> <tiles:insertAttribute name="endIndex"/> </c:set>
<c:set var="currentIndexStr"> <tiles:insertAttribute name="currentIndex"/> </c:set>
<c:set var="totalPagesStr"> <tiles:insertAttribute name="totalPages"/> </c:set>

<%-- <fmt:parseNumber var="beginIndex" type="number" value="${beginIndexStr}" />
<fmt:parseNumber var="endIndex" type="number" value="${endIndexStr}" />
<fmt:parseNumber var="totalPages" type="number" value="${totalPagesStr}" />
<fmt:parseNumber var="currentIndex" type="number" value="${currentIndexStr}" /> --%>


<c:set var="firstUrl"> <tiles:insertAttribute name="queryPrmtrs"/><c:out value="page=1"/></c:set>
<c:set var="lastUrl"> <tiles:insertAttribute name="queryPrmtrs"/><c:out value="page=${endIndex}" /> </c:set>
<c:set var="prevUrl"> <tiles:insertAttribute name="queryPrmtrs"/><c:out value="page=${currentIndex - 1}" /> </c:set>
<c:set var="nextUrl"> <tiles:insertAttribute name="queryPrmtrs"/><c:out value="page=${currentIndex + 1}" /> </c:set>
<c:set var="pageUrl"> <tiles:insertAttribute name="queryPrmtrs"/><c:out value="page=" /> </c:set>

<c:url var="firstUrl" value="${firstUrl}"/>
<c:url var="lastUrl"  value="${lastUrl }"/>
<c:url var="prevUrl"  value="${prevUrl }"/>
<c:url var="nextUrl"  value="${nextUrl }" />


<c:if test="${totalPages gt 1}">
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
            
            <c:choose>
                <c:when test="${i == currentIndex}">
                    <li class="active"><a href="${pageUrl}${i}">${i}</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageUrl}${i}">${i}</a></li>
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
    </c:if>