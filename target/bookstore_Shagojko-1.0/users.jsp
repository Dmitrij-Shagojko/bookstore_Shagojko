<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
            crossorigin="anonymous"
    />
</head>
<body>
<h1>Users</h1>
<table class="table table-striped table-hover">
    <tr>
        <th>##</th>
        <th>Name</th>
        <th>Last name</th>
        <th>Details</th>
    </tr>
    <c:forEach items="${requestScope.users}" var="user" varStatus="counter">
        <tr>
            <td>${counter.count}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td><a href="controller?command=user&id=${user.id}" target="_blank" class="btn btn-dark">Details</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
