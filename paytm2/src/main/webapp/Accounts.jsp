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
            
        }
        .table-container {
            max-width: 800px;
            margin: auto;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            
        }
        .btn-profile {
            background-color: #17a2b8;
            color: #ffffff;
            border: none;
        }
        .btn-profile:hover {
            background-color: #117a8b;
        }
        .effect{
            transition: all 0.5s;
        }
        .effect:hover{
            
            transform: translateY(5px);
            cursor: pointer;
        }
    </style>
    <title>Account Table</title>
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
    <div class="container table-container">
    <h3 class="text-primary text-center ">Accounts</h3>
    <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">Sno</th>
                    <th scope="col">Account Holder's Name</th>
                    <th scope="col">Account Number</th>
                    <th scope="col">Balence</th>
                    <th scope="col">Actions</th>
                </tr>
            </thead>
    <%
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/paytm","root","8688744624");
	PreparedStatement pr=con.prepareStatement("select * from user");
	
	ResultSet r=pr.executeQuery();
	while(r.next()){
		
		int sno=r.getInt("sno");
		String name=r.getString("name");
		double balence=r.getDouble("balence");
		String account=r.getString("account");
		
	
	
	%>
        
            <tbody>
                <tr>
                    <th scope="row"><%=sno %></th>
                    <td><%=name %></td>
                    <td><%=account %></td>
                    <td><%=balence %></td>
                    
                    <td>
                    <a href="Deposit.jsp?account=<%=account %>" class="btn btn-success">Deposit</a>
                    <a href="Edit.jsp?account=<%=account %>"  class="btn btn-warning">Edit</a>
                    <a href="Delete?account=<%=account %>" class="btn btn-danger">Delete</a>
                    
                    </td>
                </tr>
                
            </tbody>
            <%} %>
        </table>
    </div>

   
</body>
</html>
