<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="flex items-center my-12">
    <div class="w-6/12 mx-auto border border-gray-200">
        <h1 class="px-4 py-3 mx-4 my-2 text-2xl font-semibold text-start">Registration Form</h1>
        <div class="px-4 py-4 bg-white rounded shadow-xl">
            <form action="Registration" class="" method="post" enctype="multipart/form-data">
                <div class="grid grid-cols-2 py-2">
                    <div class="px-2 mx-4 ">
                        <label for="id1" class="block text-lg font-semibold">ID:</label>
                        <input type="text" name="id" id="id1"
                            class="w-full px-2 py-1 border border-blue-200 rounded focus:border-blue-300 focus:outline-none focus-within:ring-2">
                    </div>
                    <div class="px-2 mx-4 ">
                        <label for="Name" class="block text-lg font-semibold">Name:</label>
                        <input type="text" name="Name" id="Name"
                            class="w-full px-2 py-1 border border-blue-200 rounded focus:border-blue-300 focus:outline-none focus-within:ring-2">
                    </div>
                </div>

                <div class="grid grid-cols-2 py-2">
                    <div class="px-2 mx-4">
                        <label for="DateOfBirth" class="block text-lg font-semibold">DateOfBirth:</label>
                        <input type="date" name="DateOfBirth" id="DateOfBirth"
                            class="w-full px-2 py-1 border border-blue-200 rounded focus:border-blue-300 focus:outline-none focus-within:ring-2">
                    </div>
                    <div class="px-2 mx-4">
                        <label class="block text-lg font-semibold">Gender:</label>
                        <label for="male">male</label>
                        <input type="radio" name="Gender" id="male" value="male"
                            class="px-2 py-1 border border-blue-200 rounded focus:border-blue-300 focus:outline-none focus-within:ring-2">
                        <label for="female">female</label>
                        <input type="radio" name="Gender" id="female" value="female"
                            class="px-2 py-1 border border-blue-200 rounded focus:border-blue-300 focus:outline-none focus-within:ring-2">
                    </div>
                </div>

                <div class="grid grid-cols-2 py-2">
                    <div class="px-2 mx-4">
                        <label for="Email" class="block text-lg font-semibold">Email:</label>
                        <input type="email" name="Email" id="Email"
                            class="w-full px-2 py-1 border border-blue-200 rounded focus:border-blue-300 focus:outline-none focus-within:ring-2">
                    </div>
                    <div class="px-2 mx-4">
                        <label for="Phone" class="block text-lg font-semibold">Phone:</label>
                        <input type="text" name="Phone" id="Phone"
                            class="w-full px-2 py-1 border border-blue-200 rounded focus:border-blue-300 focus:outline-none focus-within:ring-2">
                    </div>
                </div>

                <div class="grid grid-cols-2 py-2">
                    <div class="px-2 mx-4">
                        <label for="Username" class="block text-lg font-semibold">Username:</label>
                        <input type="text" name="Username" id="Username"
                            class="w-full px-2 py-1 border border-blue-200 rounded focus:border-blue-300 focus:outline-none focus-within:ring-2">
                    </div>
                    <div class="relative px-2 mx-4">
                        <label for="Password" class="block text-lg font-semibold">Password:</label>
                        <input type="Password" name="Password" id="Password"
                            class="w-full px-2 py-1 border border-blue-200 rounded focus:border-blue-300 focus:outline-none focus-within:ring-2">
                            <span class="absolute duration-200 cursor-pointer right-4" onmouseover="togglePasswordVisibility()" onmouseleave="togglePasswordhiding() ">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="justify-center w-5 h-5 mt-2 ">
                                <path stroke-linecap="round" stroke-linejoin="round" d="M2.036 12.322a1.012 1.012 0 010-.639C3.423 7.51 7.36 4.5 12 4.5c4.638 0 8.573 3.007 9.963 7.178.07.207.07.431 0 .639C20.577 16.49 16.64 19.5 12 19.5c-4.638 0-8.573-3.007-9.963-7.178z" />
                                <path stroke-linecap="round" stroke-linejoin="round" d="M15 12a3 3 0 11-6 0 3 3 0 016 0z" />
                              </svg>
                            </span>
                    </div>
                </div>
                <div class="grid grid-cols-2 py-2">
                    <div class="relative px-2 mx-4">
                        <label for="ConfirmPassword" class="block text-lg font-semibold">ConfirmPassword:</label>
                        <input type="Password" name="ConfirmPassword" id="ConfirmPassword"
                            class="w-full px-2 py-1 border border-blue-200 rounded focus:border-blue-300 focus:outline-none focus-within:ring-2">
                            <span class="absolute duration-200 cursor-pointer right-4" onmouseover="togglePasswordVisibility()" onmouseleave="togglePasswordhiding() ">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="justify-center w-5 h-5 mt-2 ">
                                <path stroke-linecap="round" stroke-linejoin="round" d="M2.036 12.322a1.012 1.012 0 010-.639C3.423 7.51 7.36 4.5 12 4.5c4.638 0 8.573 3.007 9.963 7.178.07.207.07.431 0 .639C20.577 16.49 16.64 19.5 12 19.5c-4.638 0-8.573-3.007-9.963-7.178z" />
                                <path stroke-linecap="round" stroke-linejoin="round" d="M15 12a3 3 0 11-6 0 3 3 0 016 0z" />
                              </svg>
                            </span>
                    </div>
                    <div class="px-2 mx-4">
                        <label for="photo"class="block text-lg font-semibold">Upload your profile photo</label>
                        <input type="file" name="profile"  id="photo" class="px-3 py-2 ">
                        
                    </div>
                </div>

                <div class="grid grid-cols-2 py-2">
                   
                    <div class="px-2 pt-5 mx-4">
                        <input type="submit" value="submit" class="px-3 py-2 text-white bg-blue-500 rounded cursor-pointer hover:bg-blue-600">
                        <input type="reset" value="Reset" class="px-3 py-2 text-white bg-yellow-500 rounded cursor-pointer hover:bg-yellow-600">
                    </div>
                    <div class="pt-5"><span class="text-blue-400 text-large">Already have account?</span><a href="">Login</a></div>
                </div>
                
            </form>
        </div>
    </div>

    <script>
        function togglePasswordVisibility() {
            var passwordInput = document.getElementById("Password");

            if (passwordInput.type === "password") {
                passwordInput.type = "text";
            } else {
                passwordInput.type = "password";
            }
        }

        function togglePasswordhiding() {
            var passwordInput = document.getElementById("Password");

            if (passwordInput.type === "text") {
                passwordInput.type = "password";
            } else {
                passwordInput.type = "password";
            }
        }

    </script>
</body>
</html>