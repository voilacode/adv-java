<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Base64" %>
<html>
<head>
    <title>User Profile</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css">
</head>
<body class="bg-gray-200 h-screen flex items-center justify-center">

    <div class="bg-white p-8 rounded shadow-md w-2/3">
        <h2 class="text-2xl font-semibold mb-4">User Profile</h2>
			<%
			String id=(String)request.getAttribute("id");
			String name=(String)request.getAttribute("name");
			String email=(String)request.getAttribute("email");
			String  date=(String)request.getAttribute("dateofbirth");
			String  phone=(String)request.getAttribute("phone");
			String  username=(String)request.getAttribute("username");
			String  password=(String)request.getAttribute("password");
			String  gender=(String)request.getAttribute("gender");
			byte[] profileBytes = (byte[]) request.getAttribute("profile");
			String photoBase64 = Base64.getEncoder().encodeToString(profileBytes);
			%>
        <div class="flex mb-4">
            <div class="w-1/2 pr-4">
                <div class="flex items-center space-x-4 mb-4">
                    <img src="data:image/jpeg;base64,<%= photoBase64 %>" alt="Profile Photo" class="w-24 h-24 rounded-full">
                    <div>
                        <p class="text-lg font-semibold"><%=name %></p>
                        <p class="text-gray-500">Username: <%=name %></p>
                    </div>
                </div>

                <div class="mb-4">
                    <p><span class="font-semibold">ID:<%=id %></span> </p>
                    <p><span class="font-semibold">Date of Birth:<%=date %></span> </p>
                    <p><span class="font-semibold">Email:<%=email %></span></p>
                </div>
            </div>

            <div class="w-1/2 pl-4">
                <div class="mb-4">
                    <p><span class="font-semibold">Phone:<%=phone %></span> </p>
                    <p><span class="font-semibold">Gender:<%=gender %></span></p>
                    <p><span class="font-semibold">Password:<%=password %></span></p>
                </div>

                <button class="bg-blue-500 text-white px-4 py-2 rounded-full hover:bg-blue-600">Edit Profile</button>
            </div>
        </div>

    </div>

</body>
</html>
