<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>  
<head>  
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>All Brands</title>  
</head>  
<body>
  
	<div align="center">
		<h1>All Brands</h1>
		<table border="1">
			<th>No</th>
			<th>Brand Name</th>
				<c:forEach var="brand" items="${brands}" varStatus="status">
                <tr>
                    <td>${status.index + 1}</td>
                    <td>${brand.brandName}</td>                             
                </tr>
                </c:forEach>  
		</table>
	</div> 
</body>  
</html>  