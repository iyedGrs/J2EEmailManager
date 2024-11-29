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
        System.out.println(action);


        switch (action) {
            case "view":
                viewEmails(request, response);
                break;
            case "edit":
                showEditForm(request, response);
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
        System.out.println("Action: " + action);  // Debugging output

        if ("add".equals(action)) {
            addEmail(request, response);
        }else if ("edit".equals(action)) {
            updateEmail(request, response);
        }
        else {
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
    private void deleteEmail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");

        System.out.println("Email to delete: " + email); // Add logging for debugging

        if (email != null && !email.isEmpty()) {
            try (Connection conn = DBUTIL.getConnection()) {
                // Prepare SQL query to delete the email from the database
                String query = "DELETE FROM emails WHERE email = ?";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, email);  // Set the email to delete

                    int rowsDeleted = stmt.executeUpdate();

                    if (rowsDeleted > 0) {
                        System.out.println("Email successfully deleted.");
                        resp.sendRedirect("email?action=view");  // Redirect to view emails after deleting
                    } else {
                        System.out.println("Email not found for deletion.");
                        resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Email not found.");
                    }
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error.");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid email data.");
        }
    }

    private void updateEmail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String oldEmail = req.getParameter("oldEmail");
        String newEmail = req.getParameter("email");

        System.out.println("Old Email: " + oldEmail); // Add logging for debugging
        System.out.println("New Email: " + newEmail); // Add logging for debugging

        // Validate input data
        if (oldEmail != null && !oldEmail.isEmpty() && newEmail != null && !newEmail.isEmpty()) {
            try (Connection conn = DBUTIL.getConnection()) {
                // Prepare SQL statement to update the email in the database
                String query = "UPDATE emails SET email = ? WHERE email = ?";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, newEmail);  // Set new email
                    stmt.setString(2, oldEmail);  // Set old email (to find the correct record)

                    int rowsUpdated = stmt.executeUpdate();

                    if (rowsUpdated > 0) {
                        System.out.println("Email successfully updated.");
                        resp.sendRedirect("email?action=view"); // Redirect to view emails after updating
                    } else {
                        System.out.println("Email not found for update.");
                        resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Email not found.");
                    }
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error.");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid email data.");
        }
    }
    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");  // Get the email from the request

        if (email != null && !email.isEmpty()) {
            // Pass the email to the edit form (editEmail.jsp)
            req.setAttribute("email", email);
            req.getRequestDispatcher("/editEmail.jsp").forward(req, resp);  // Forward to the edit page
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email not found.");
        }
    }
}
