<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Email List</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <style>
        body {
            background-color: #f4f6f9;
            font-family: 'Arial', sans-serif;
        }
        .container {
            max-width: 80%;
            margin: 30px auto;
        }
        h1 {
            color: #343a40;
            text-align: center;
            margin-bottom: 30px;
        }
        table {
            margin-bottom: 30px;
            border-collapse: collapse;
        }
        th, td {
            text-align: center;
            padding: 12px;
        }
        th {
            background-color: #007bff;
            color: white;
            border-radius: 8px;
        }
        td {
            background-color: #ffffff;
            border-radius: 8px;
        }
        button {
            background-color: #28a745;
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        button:hover {
            background-color: #218838;
        }
        .back-btn {
            display: block;
            margin: 20px auto;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            text-align: center;
            border-radius: 5px;
        }
        .back-btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Email List</h1>

    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th>Email</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <!-- Check if the emails list is empty -->
        <c:if test="${empty emails}">
            <tr>
                <td colspan="2" class="text-center">No emails in the database.</td>
            </tr>
        </c:if>
        <!-- JSTL Loop to Display Emails -->
        <c:forEach var="email" items="${emails}">
            <tr>
                <td>${email}</td> <!-- Displaying Email -->
                <td>
                    <!-- Edit and Delete Actions -->
                    <button onclick="window.location.href='email?action=edit&email=${email}'" class="btn btn-success">Edit</button>
                    <button onclick="return confirmDelete('${email}')" class="btn btn-danger">Delete</button> <!-- Added confirmation -->
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <script>
        function confirmDelete(email) {
            if (confirm("Are you sure you want to delete this email?")) {
                window.location.href = 'email?action=delete&email=' + email;
            }
            return false;  // Prevent the default action of the link
        }
    </script>

    <!-- Back Button -->
    <a href="index.jsp" class="back-btn">Back to Home</a>
</div>

<!-- Bootstrap JS and Popper.js (Optional for Bootstrap Components like dropdowns) -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>

</body>
</html>
