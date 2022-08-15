
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create User Form</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
            crossorigin="anonymous"
    />
</head>
<body>
<div class="container text-center my-5">
    <h3>Enter user data</h3>
</div>
<div class="container my-5">
<form method="post" action="controller?command=createUser">
    <div class="mb-3">
        <label for="exampleInputFirstName" class="form-label">First name</label>
        <input name="firstName" class="form-control" id="exampleInputFirstName">
    </div>
    <div class="mb-3">
        <label for="exampleInputLastName" class="form-label">Last name</label>
        <input name="lastName" class="form-control" id="exampleInputLastName">
    </div>
    <div class="mb-3">
        <label for="exampleInputEmail" class="form-label">Email</label>
        <input name="email" class="form-control" id="exampleInputEmail">
    </div>
    <div class="mb-3">
        <label for="exampleInputRole" class="form-label">Role</label>
        <select name="role" class="form-select" id="exampleInputRole">
        <option selected>Open this select menu and select a role</option>
        <option>ADMIN</option>
        <option>MANAGER</option>
        <option>CUSTOMER</option>
    </select>
    </div>
    <button type="submit" class="btn btn-dark">Submit</button>
</form>
    <a href="controller?command=users" class="btn btn-primary">Back</a>
</div>
</body>
</html>
