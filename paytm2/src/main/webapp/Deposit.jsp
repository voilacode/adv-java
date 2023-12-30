<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <title>Money Deposit Form</title>
    <style>
        .effect{
            transition: all 0.5s;
        }
        .effect:hover{
            
            transform: translateY(5px);
            cursor: pointer;
        }
</style>
</head>
<body class="bg-light">
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
                <div class="d-flex gap-2">
                <a href="landingpage.jsp" type="button" class="btn btn-primary">
                        
                        Home</a>
                    <a href="createaccount.jsp" type="button" class="btn btn-primary">
                        
                        Create Account</a>
                    <a href="accessaccount.jsp" type="button" class="btn btn-success">Access Account</a>
                </div>
            </div>
        </div>
    </nav>

	<%
			String account=request.getParameter("account");
			%>
<div class="container d-flex align-items-center justify-content-center" style="height: 100vh;">
    <div class="card text-center" style="width: 18rem;">
        <div class="card-body">
            <h5 class="card-title">Money Deposit Form</h5>

            <form action="Deposit" method="get" class="needs-validation" novalidate>
                <div class="form-group">
                    <label for="accountNumberInput">Account Number:</label>
                    <input type="text" class="form-control" id="accountNumberInput" name="accountNumber" required value="<%=account %>" readonly>
                    
                </div>

                <div class="form-group">
                    <label for="moneyInput">Enter Amount:</label>
                    <input type="text" class="form-control" id="moneyInput" name="money" placeholder="Enter amount" required>
                    <div class="invalid-feedback">Please enter a valid amount.</div>
                </div>

                <button type="submit" class="btn btn-primary btn-block mt-1">Deposit</button>
            </form>
        </div>
    </div>
</div>

<div class="bg-info mt-4" style="height: 50px;">
    <p class="text-white text-center pt-3 fw-bold">@all right reserved</p>
    </div>
</body>
</html>
