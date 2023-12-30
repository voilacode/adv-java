<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <style>
        body {
            background-color: #f8f9fa;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        form {
            max-width: 600px;
            margin: 0 auto;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }
        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }
    </style>
    <title>Create Account Form</title>
</head>
<body>
    <div class="container">
    	<%
    	String number=request.getParameter("account");
    	
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/paytm","root","8688744624");
	PreparedStatement pr=con.prepareStatement("select * from user where account=?");
	pr.setString(1,number);
	ResultSet r=pr.executeQuery();
	while(r.next()){
		String account=r.getString("account");
		String name=r.getString("name");
		String mobile=r.getString("mobile");
		String email=r.getString("email");
		String age=r.getString("gender");
		double balence=r.getDouble("balence");
	
	
	%>
    	
        <form action="Update" method="get">
            <div class="form-group">
                <label for="accountNumber">Account Number:</label>
                <input type="text" class="form-control" id="accountNumber" name="accountNumber" value="<%=account%>" readonly>
            </div>
            <div class="form-group">
                <label for="accountHolderName">Account Holder Name:</label>
                <input type="text" class="form-control" id="accountHolderName" name="accountHolderName" value="<%=name%>">
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="age">Gender:</label>
                        <input type="text" class="form-control" id="age" name="age" value="<%=age%>">
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="email" class="form-control" id="email" name="email" value="<%=email%>">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="phoneNumber">Phone Number:</label>
                <input type="tel" class="form-control" id="phoneNumber" name="phoneNumber" value="<%=mobile%>">
            </div>
            
            <button  type="submit" class="btn btn-primary">Update</button>
            <%} %>
        </form>
    </div>

    
</body>
</html>
