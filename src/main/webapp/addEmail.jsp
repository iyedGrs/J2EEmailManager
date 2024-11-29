<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add a New Email</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f4f6f9;
            font-family: 'Arial', sans-serif;
            color: #343a40;
        }
        .container {
            max-width: 500px;
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
        .form-label {
            font-size: 1.1rem;
            margin-bottom: 10px;
        }
        .form-control {
            margin-bottom: 20px;
        }
        .btn-submit {
            width: 100%;
            padding: 12px;
            background-color: #28a745;
            color: white;
        }
        .btn-submit:hover {
            opacity: 0.9;
        }
        .btn-back {
            width: 100%;
            padding: 12px;
            background-color: #6c757d;
            color: white;
            margin-top: 10px;
        }
        .btn-back:hover {
            opacity: 0.9;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Add a New Email</h1>
    <form action="email" method="post">
        <input type="hidden" name="action" value="add">
        <div class="mb-3">
            <label for="email" class="form-label">Email:</label>
            <input type="email" id="email" name="email" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-submit">Add</button>
    </form>
    <button class="btn btn-back" onclick="window.location.href='index.jsp'">Back</button>
</div>
</body>
</html>
