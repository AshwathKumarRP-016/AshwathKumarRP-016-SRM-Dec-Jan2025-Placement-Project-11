<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            background: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            text-align: center;
        }
        h1 {
            color: #4CAF50;
            margin-bottom: 30px;
        }
        .user-info {
            background-color: #e8f5e9;
            padding: 20px;
            border-radius: 5px;
            margin: 20px 0;
            text-align: left;
        }
        .nav-links {
            margin-top: 30px;
        }
        .nav-links a {
            display: inline-block;
            background-color: #008CBA;
            color: white;
            padding: 12px 25px;
            text-decoration: none;
            border-radius: 5px;
            margin: 0 10px;
        }
        .nav-links a:hover {
            background-color: #007B9A;
        }
        .logout-btn {
            background-color: #f44336 !important;
        }
        .logout-btn:hover {
            background-color: #d32f2f !important;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome, ${employeeName}!</h1>
        <p>You have successfully logged in to the Employee Attendance System.</p>
        
        <div class="user-info">
            <h3>Your Information:</h3>
            <p><strong>Employee ID:</strong> ${employeeId}</p>
            <p><strong>Name:</strong> ${employeeName}</p>
            <p><strong>User name:</strong> ${username}</p>
            <p><strong>Department:</strong> ${department}</p>
        </div>
        
        <div class="nav-links">
            <a href="dashboard.jsp">Go to DashBoard</a>
            <a href="register.html">Register Another Employee</a>
            <a href="EmployeeServlet?action=logout" class="logout-btn">Logout</a>
        </div>
    </div>
</body>
</html>