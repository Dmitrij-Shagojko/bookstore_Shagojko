<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
            crossorigin="anonymous"
    />
</head>
<body>
<h1>Books</h1>
<table class="table table-striped table-hover">
    <tr>
        <th>##</th>
        <th>Title</th>
        <th>Author</th>
        <th>Details</th>
    </tr>
    <c:forEach items="${requestScope.books}" var="book" varStatus="counter">
        <tr>
            <td>${counter.count}</td>
            <td>${book.name}</td>
            <td>${book.author}</td>
            <td><a href="controller?command=book&id=${book.id}" target="_blank" class="btn btn-dark">Details</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
