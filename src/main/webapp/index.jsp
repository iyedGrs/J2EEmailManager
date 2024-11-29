<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Email Manager</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f4f6f9;
            font-family: 'Arial', sans-serif;
            color: #343a40;
        }
        .container {
            max-width: 600px;
            margin-top: 50px;
            padding: 30px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #007bff;
            margin-bottom: 30px;
        }
        .btn {
            width: 100%;
            padding: 12px;
            margin-bottom: 20px;
        }
        .btn-view {
            background-color: #007bff;
            color: white;
        }
        .btn-add {
            background-color: #28a745;
            color: white;
        }
        .btn:hover {
            opacity: 0.9;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Email Manager</h1>
    <button class="btn btn-view" onclick="window.location.href='email?action=view'">View Emails</button>
    <button class="btn btn-add" onclick="window.location.href='addEmail.jsp'">Add Email</button>
</div>
</body>
</html>
