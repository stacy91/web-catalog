
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
	<div class="managementContentWrapper">
    <p>Application has encountered an error. You are trying to enter already existing
    name in database.</p>

    <!--
    Failed URL: ${url}
    Exception:  ${message}
        <c:forEach items="${exceptionStackTrace}" var="ste">    
        ${ste} 
    	</c:forEach>
    -->
    </div>