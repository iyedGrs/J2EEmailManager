package com.example.jdbcemaifetchernew.servlet;

import com.example.jdbcemaifetchernew.util.DBUTIL;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteEmailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");

        try (Connection conn = DBUTIL.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM emails WHERE email = ?");
            stmt.setString(1, email);
            stmt.executeUpdate();
            resp.sendRedirect("email?action=view");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
