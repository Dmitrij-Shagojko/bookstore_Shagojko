<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
            crossorigin="anonymous"
    />
</head>
<body>
<div class="container text-center my-5">
    <h1>User card</h1>
    <div class="card" style="width: 18rem;">
        <img src="..." class="card-img-top" alt="USER PHOTO">
        <div class="card-body">
            <h5 class="card-title">${requestScope.user.firstName} ${requestScope.user.lastName}</h5>
            <p class="card-text">Email: ${requestScope.user.email}<br>
                Role: ${requestScope.user.role}</p>
        </div>
    </div>
</div>
</body>
</html>