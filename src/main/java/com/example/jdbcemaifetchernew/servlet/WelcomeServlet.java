package com.example.jdbcemaifetchernew.servlet;

import com.example.jdbcemaifetchernew.util.DBUTIL;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        try(Connection conn = DBUTIL.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from emails")){
            out.println("<h1>Welcome</h1>");
            out.println("<h1>Emails</h1>");
            while(rs.next()){
                out.println("<li>"+rs.getString("email")+"</li>");
            }
            out.println("</ul>");
            out.println("<a href='addEmail.html'>Add Email</a>");
            out.println("</body></html>");
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
