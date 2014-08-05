

<html>  
<head>  
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="jquery-1.2.6.min.js"></script>  
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
                    <td>${user.brandName}</td>                             
                </tr>
                </c:forEach>  
		</table>
	</div> 
</body>  
</html>  