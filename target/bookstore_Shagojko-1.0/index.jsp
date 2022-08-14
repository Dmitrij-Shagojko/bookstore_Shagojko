<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bookstore</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
            crossorigin="anonymous"
    />
</head>
<body>
<div class="container text-center my-5">
    <div class="row">
        <div class="col-lg-6 mx-auto">
            <a href="controller?command=books" target="_blank" class="btn btn-success">All books</a>
            <a href="controller?command=users" target="_blank" class="btn btn-info">All users</a>
        </div>
    </div>
</div>
</body>
</html>
