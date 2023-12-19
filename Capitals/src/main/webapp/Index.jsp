<%@page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index Page</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
		<%
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/capitals","root","8688744624");
		PreparedStatement pr=con.prepareStatement("select * from demo");
		ResultSet r=pr.executeQuery();
		%>

<div class="container mt-4 ">
<div class="row">

<div class="col-6 mx-auto p-4 rounded bg-primary">
<h3 class="text-center">Country_capital</h3>
<table class="table border ">

  <thead>
    <tr>
      <th scope="col">S.No</th>
      <th scope="col">Country</th>
      <th scope="col">Capital</th>
      
    </tr>
  </thead>
  <tbody>
		<%while(r.next()){ %>
		
    <tr>
      <th scope="row"><%=r.getInt("S_NO") %></th>
      <td><%=r.getString("country") %></td>
      <td><%=r.getString("capital") %></td>
      
    </tr>
    <%} %>
  </tbody>
</table>
<a href="Form.jsp" class="btn btn-warning">Back</a>
</div>
</div>
</div>

</body>
</html>