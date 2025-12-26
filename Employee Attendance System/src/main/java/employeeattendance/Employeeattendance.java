package employeeattendance;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.DriverManager;

@WebServlet(urlPatterns = {"/EmployeeServlet", "/EmployeeLog"})
public class Employeeattendance extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection connection;
    
    // Database configuration
    private String Url = "jdbc:mysql://localhost:3306/employee_attendance";
    private String Username = "root";
    private String Password = "root";
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(Url, Username, Password);
            System.out.println("Database connection established successfully.");
           
             createEmployeeTable();
        } catch (ClassNotFoundException e) {
            throw new ServletException("JDBC Driver not found.", e);
        } catch (SQLException e) {
            throw new ServletException("Database connection failed.", e);
        }
    }
    
    private void createEmployeeTable() throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS employees (" +
                "id VARCHAR(20) PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL, " +
                "username VARCHAR(50) UNIQUE NOT NULL, " +
                "password VARCHAR(100) NOT NULL, " +
                "department VARCHAR(50) NOT NULL" +
                ")";
        
        try (PreparedStatement stmt = connection.prepareStatement(createTableSQL)) {
            stmt.executeUpdate();
            System.out.println("Employee table created/verified.");
        }
    }
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("register".equals(action)) {
            handleRegistration(request, response);
        } else if ("login".equals(action)) {
            handleLogin(request, response);
        } else if ("logout".equals(action)) {
            handleLogout(request, response);
        } else {
            response.sendRedirect("dashboard.jsp");
        }
    }
    
    private void handleRegistration(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String department = request.getParameter("department");
        
        try {
            
            String checkSQL = "SELECT * FROM employees WHERE username = ?";
            try (PreparedStatement checkStmt = connection.prepareStatement(checkSQL)) {
                checkStmt.setString(1, username);
                ResultSet rs = checkStmt.executeQuery();
                
                if (rs.next()) {
                    
                    response.setContentType("text/html");
                    PrintWriter out = response.getWriter();
                    out.println("<html><body>");
                    out.println("<h2>Registration Failed</h2>");
                    out.println("<p>Username already exists. Please choose a different username.</p>");
                    out.println("<a href='register.html'>Try Again</a> | ");
                    out.println("<a href='dashboard.jsp'>Dashboard</a>");
                    out.println("</body></html>");
                    return;
                }
            }
            
          
            String insertSQL = "INSERT INTO employees (id, name, username, password, department) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement insertStmt = connection.prepareStatement(insertSQL)) {
                insertStmt.setString(1, id);
                insertStmt.setString(2, name);
                insertStmt.setString(3, username);
                insertStmt.setString(4, password);
                insertStmt.setString(5, department);
                
                int rowsInserted = insertStmt.executeUpdate();
                
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<html><body>");
                
                if (rowsInserted > 0) {
                    out.println("<h2 style='color:green;'>Registration Successful!</h2>");
                    out.println("<h3>Employee Details:</h3>");
                    out.println("<p><strong>ID:</strong> " + id + "</p>");
                    out.println("<p><strong>Name:</strong> " + name + "</p>");
                    out.println("<p><strong>Username:</strong> " + username + "</p>");
                    out.println("<p><strong>Department:</strong> " + department + "</p>");
                } else {
                    out.println("<h2 style='color:red;'>Registration Failed</h2>");
                }
                
                out.println("<br><br>");
                out.println("<a href='register.html'>Register Another</a> | ");
                out.println("<a href='login.html'>Login</a> | ");
                out.println("<a href='dashboard.jsp'>Dashboard</a>");
                out.println("</body></html>");
            }
        } catch (SQLException e) {
            throw new ServletException("Database error during registration.", e);
        }
    }
    
    private void handleLogin(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        try {
            String sql = "SELECT * FROM employees WHERE username = ? AND password = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                
                ResultSet rs = stmt.executeQuery();
                
                if (rs.next()) {
                    
                    HttpSession session = request.getSession();
                    session.setAttribute("employeeId", rs.getString("id"));
                    session.setAttribute("employeeName", rs.getString("name"));
                    session.setAttribute("username", username);
                    session.setAttribute("department", rs.getString("department"));
                    
                    
                    request.getRequestDispatcher("welcome.jsp").forward(request, response);
                } else {
                    
                    response.setContentType("text/html");
                    PrintWriter out = response.getWriter();
                    out.println("<html><body>");
                    out.println("<h2 style='color:red;'>Login Failed</h2>");
                    out.println("<p>Invalid username or password. Please try again.</p>");
                    out.println("<a href='login.html'>Try Again</a> | ");
                    out.println("<a href='register.html'>Register</a> | ");
                    out.println("<a href='dashboard.jsp'>Dashboard</a>");
                    out.println("</body></html>");
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Database error during login.", e);
        }
    }
    
    private void handleLogout(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("dashboard.jsp");
    }
    
    @Override
    public void destroy() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing database connection: " + e.getMessage());
        }
    }

}
