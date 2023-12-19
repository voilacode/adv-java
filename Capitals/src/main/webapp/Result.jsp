<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Result</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<div class="container mt-5">
<h3 class="text-center">Country-Capital</h3>
<div class="row justify-content-center">
<div class="col-md-6">
<form  class="bg-success p-4 rounded">
	<div class="form-group">
	<label  class="fw-bold my-2">Capital:</label>
	
	<h3 class="text-white"><%=request.getAttribute("capital") %></h3>
	</div>
	<a href="Index.jsp" class="btn btn-warning">Index</a>
</form>

</div>
</div>
</div>
</body>
</html>