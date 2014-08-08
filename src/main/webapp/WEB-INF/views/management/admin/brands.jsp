<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>  
<head>  
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>All Brands</title>  
</head>  
<body>
  
	<div align="center">
		<h1>All Brands</h1>
		<table class="table table-hover table-bordered">
			<th>No</th>
			<th>Brand Name</th>
			<th>Reference</th>
				<c:forEach var="brand" items="${brands}" varStatus="status">
                <tr>
                    <td>${status.index + 1}</td>
                    <td>${brand.brandName}</td>
                    <td><a href="#">View devices</a></td>                           
                </tr>
                </c:forEach>


					

		</table>
		
		<form class="form-horizontal" role="form">
						<div class="form-group">
    						<label for="inputPassword" class="col-sm-2 control-label">Enter New Brand:</label>
    						<div class="col-sm-10">
      							<input type="text" class="form-control" id="inputPassword" placeholder="new brand..." 
      							style="width:85%; float:left;">
      							<button type="button" class="btn btn-success" style="width:100px">Add</button>
    						</div>
    						
    					</div>
    					
    					</form>
	</div> 
</body>  
</html>  