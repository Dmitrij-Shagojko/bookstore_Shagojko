<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .center {
            position: absolute;
            width: 800px;
            height: 800px;
            left: 50%;
            top: 50%;
        }
    </style>
</head>
<body>
<div class="center">
    <h1>Opps....</h1>
    <h3>Sorry, an error occurred:</h3>
    <p>Status: ${requestScope.errorStatus}</p>
    <p>Message: ${requestScope.message}</p>
</div>
</body>
</html>
