package com.example.jdbcemaifetchernew.servlet;

import com.example.jdbcemaifetchernew.util.DBUTIL;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "view":
                viewEmails(request, response);
                break;
            case "edit":
                editEmail(request, response);
                break;
            case "delete":
                deleteEmail(request, response);
                break;
            default:
                response.sendRedirect("index.jsp");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            addEmail(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }
    private void viewEmails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> emails = new ArrayList<>();
        try (Connection connection = DBUTIL.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM emails")) {
            while (rs.next()) {
//                System.out.println("Fetched Email: " + rs.getString("email")); // Debugging
                emails.add(rs.getString("email"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.setAttribute("emails", emails);
        request.getRequestDispatcher("viewEmails.jsp").forward(request, response);
    }
    private void addEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        try (Connection connection = DBUTIL.getConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO emails (email) VALUES (?)")) {
            ps.setString(1, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("email?action=view");
    }
    private void deleteEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try (Connection connection = DBUTIL.getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM emails WHERE id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        response.sendRedirect("email?action=view");
    }
    private void editEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Add your edit logic here
    }
}
