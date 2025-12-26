<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>DashBoard - Employee Attendance System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 40px;
        }
        h2 {
            color: #555;
            text-align: center;
            margin-bottom: 30px;
        }
        .dashboard-links {
            display: flex;
            justify-content: center;
            gap: 30px;
            flex-wrap: wrap;
            margin-top: 40px;
        }
        .card {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 40px 30px;
            border-radius: 10px;
            text-align: center;
            width: 300px;
            text-decoration: none;
            transition: transform 0.3s ease;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        }
        .card:hover {
            transform: translateY(-5px);
            box-shadow: 0 6px 12px rgba(0,0,0,0.15);
        }
        .card h3 {
            margin-top: 0;
            font-size: 24px;
        }
        .card p {
            margin-bottom: 0;
            font-size: 16px;
        }
        .register-card {
            background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
        }
        .login-card {
            background: linear-gradient(135deg, #4facfe 0%, #00f2fe 100%);
        }
        .system-info {
            background-color: #f8f9fa;
            padding: 20px;
            border-radius: 10px;
            margin-top: 40px;
            border-left: 4px solid #007bff;
        }
        .system-info h3 {
            color: #007bff;
            margin-top: 0;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Employee Attendance System</h1>
        <h2>Welcome to the DashBoard</h2>
        
        <div class="dashboard-links">
            <a href="register.html" class="card register-card">
                <h3>Register Employee</h3>
                <p>Register new employees to the system</p>
            </a>
            
            <a href="login.html" class="card login-card">
                <h3>Employee Login</h3>
                <p>Login for attendance tracking</p>
            </a>
        </div>
        
        <div class="system-info">
            <h3>System Features:</h3>
            <ul>
                <li>Employee Registration with ID, Name, User name, Password, and Department</li>
                <li>Secure Login System</li>
                <li>Database-driven authentication</li>
                <li>JDBC Database Connectivity</li>
                
            </ul>
        </div>
    </div>
</body>
</html>