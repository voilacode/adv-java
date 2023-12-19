<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Country_Capital</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<div class="container mt-5">
<h3 class="text-center">Country-Capital</h3>
<div class="row justify-content-center">
<div class="col-md-6">
<form action="Validation" method="get" class="bg-primary p-4 rounded">
	<div class="form-group">
	<label for="country-name" class="fw-bold my-2">Country Name</label>
	<input type="text" id="country-name" name="country" placeholder="Enter any Country Name" class="form-control my-2">
	</div>
	<button class="btn bg-light my-2" type="submit">Get Capital</button>
</form>
</div>
</div>
</div>
</body>
</html>