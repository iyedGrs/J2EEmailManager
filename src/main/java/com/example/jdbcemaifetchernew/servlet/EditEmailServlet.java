package com.example.jdbcemaifetchernew.servlet;

import com.example.jdbcemaifetchernew.util.DBUTIL;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditEmailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");

        // Check if the email parameter is passed correctly
        if (email != null && !email.isEmpty()) {
            try (Connection conn = DBUTIL.getConnection()) {
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM emails WHERE email = ?");
                stmt.setString(1, email);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    // Forward the data (email) to the JSP for editing
                    req.setAttribute("email", rs.getString("email"));
                    req.getRequestDispatcher("/editEmail.jsp").forward(req, resp);
                } else {
                    // Handle the case where the email is not found
                    resp.sendRedirect("index.jsp");
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error.");
            }
        } else {
            resp.sendRedirect("index.jsp");
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oldEmail = req.getParameter("oldEmail");
        String newEmail = req.getParameter("email");


        try (Connection conn = DBUTIL.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE emails SET email = ? WHERE email = ?");
            stmt.setString(1, newEmail);
            stmt.setString(2, oldEmail);
            stmt.executeUpdate();
            resp.sendRedirect("email?action=view");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
