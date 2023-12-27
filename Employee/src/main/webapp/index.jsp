<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Beautiful Table with Tailwind CSS</title>
    <!-- Include the Tailwind CSS styles -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css">
</head>
<body class="bg-gray-100">

    <div class="container mx-auto p-8">
        <h1 class="text-4xl font-bold mb-8">User Information Table</h1>

        <div class="overflow-x-auto">
            <table class="min-w-full bg-white border border-gray-300">
                <thead>
                    <tr>
                        <th class="px-6 py-4 border-b border-gray-300 bg-gray-200 text-gray-700">Id</th>
                        <th class="px-6 py-4 border-b border-gray-300 bg-gray-200 text-gray-700">Name</th>
                        <th class="px-6 py-4 border-b border-gray-300 bg-gray-200 text-gray-700">Email</th>
                        <th class="px-6 py-4 border-b border-gray-300 bg-gray-200 text-gray-700">Phone</th>
                        <th class="px-6 py-4 border-b border-gray-300 bg-gray-200 text-gray-700">Gender</th>
                        <th class="px-6 py-4 border-b border-gray-300 bg-gray-200 text-gray-700">Date of Birth</th>
                        <th class="px-6 py-4 border-b border-gray-300 bg-gray-200 text-gray-700">Username</th>
                    </tr>
                </thead>
                <%
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","8688744624");
                PreparedStatement pr=con.prepareStatement("select * from employee");
                ResultSet r=pr.executeQuery();
                while(r.next()){
       
                
                %>
                <tbody>
                    <!-- Add your dynamic data here -->
                    <tr>
                        <td class="px-6 py-4 border-b border-gray-300"><a href="Profile?id=<%=r.getString("id") %>"><%=r.getString("id") %></a></td>
                        <td class="px-6 py-4 border-b border-gray-300"><%=r.getString("name") %></td>
                        <td class="px-6 py-4 border-b border-gray-300"><%=r.getString("email") %></td>
                        <td class="px-6 py-4 border-b border-gray-300"><%=r.getString("phone") %></td>
                        <td class="px-6 py-4 border-b border-gray-300"><%=r.getString("gender") %></td>
                        <td class="px-6 py-4 border-b border-gray-300"><%=r.getString("dateofbirth") %></td>
                        <td class="px-6 py-4 border-b border-gray-300"><%=r.getString("username") %></td>
                    </tr>
                    <!-- Add more rows as needed -->
                </tbody>
                <%} %>
            </table>
        </div>
    </div>

</body>
</html>
