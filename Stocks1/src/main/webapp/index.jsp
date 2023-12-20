<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<title>Stock App</title>
</head>
<body>
	
	<div class="container mt-5 border border-primary p-5 rounded shadow bg-white" style="transform: translateY(15%); width: 60vw;">
		<h1 class="text-primary text-center">Stock App</h1>
	
    	<form action="Stock" method=get>
			
			<div class="mb-3">
				<label class="form-label d-block">Select Stock Indices</label>
				<select name="stock" class="form-select">
					<option value="NIFTY%2050">NIFTY 50</option>
					<option value="NIFTY%20NEXT%2050">NIFTY NEXT 50</option>
					<option value="NIFTY%20100">NIFTY 100</option>
					<option value="NIFTY%20200">NIFTY 200</option>
					<option value="NIFTY%20PHARMA">NIFTY Pharma</option>
					<option value="NIFTY%20PVT%20BANK">Private  Bank</option>
					<option value="NIFTY%20PSU%20BANK">Public Bank</option>
					
					
				</select>
			</div>
			
			<button type="submit" class="btn btn-primary d-block mx-auto px-4 py-2">Find</button>
			
		</form>
		
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>