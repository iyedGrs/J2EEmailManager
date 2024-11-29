<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Email</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1>Edit Email</h1>
    <form action="email?action=edit" method="post">
        <input type="hidden" name="oldEmail" value="${email}">
        <div class="mb-3">
            <label for="email" class="form-label">New Email:</label>
            <input type="email" class="form-control" id="email" name="email" value="${email}" required>
        </div>
        <button type="submit" class="btn btn-primary">Update Email</button>
    </form>
    <a href="index.jsp" class="btn btn-secondary mt-3">Back</a>
</div>
</body>
</html>
