<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Create Account</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
       .main{
            background-color: #f8f9fa;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }

        .form-container {
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            width: 80%;
            max-width: 600px;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg bg-info">
        <div class="container">
            <a class="navbar-brand text-white fw-bold" href="#">
                Paytm
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll"
                aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-between" id="navbarScroll">
                <div class="navbar-nav">
                    <!-- Add any additional navigation items here if needed -->
                </div>
                <div  class="d-flex gap-2">
                <a href="landingpage.jsp" type="button" class="btn btn-primary">
                        
                        Home</a>
                    <a href="createaccount.jsp" type="button" class="btn btn-primary">
                        
                        Create Account</a>
                    <a href="accessaccount.jsp" type="button" class="btn btn-success">Access Account</a>
                </div>
            </div>
        </div>
    </nav>
    <div class="main">
<div class="container form-container">
		<%
		String msg=(String)request.getAttribute("msg");
		if(msg!=null){%>
			<p Class="text-center h3 text-warning"><%=msg%> </P>
		<% }%>
		
        <h2 class="text-center">Access Your Account</h2>
        <form action="accessaccount" method="get">
            <div class="mb-3">
                <label for="mobile" class="form-label">Mobile Number</label>
                <input type="tel" class="form-control" id="mobile" name="mobile" required placeholder="Enter registered mobile number">
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</div>
<div class="bg-info mt-4" style="height: 50px;">
    <p class="text-white text-center pt-3 fw-bold">@all right reserved</p>
    </div>
<!-- Bootstrap JS and Popper.js (Optional) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
