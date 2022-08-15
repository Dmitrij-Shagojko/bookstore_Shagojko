<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Done</title>
    <style>
        .done {
            color: #00FF00;
            font-size: 130%;
        }

        .button1 {
            background-color: #FFFFFF;
            color: #000000;
            padding: 12px 28px;
        }
    </style>
</head>
<body>
<p>First name: ${param.firstName}</p>
<p>Last Name: ${param.lastName}</p>
<p>Email: ${param.email}</p>
<p>Role: ${param.role}</p>
<p><span class="done">New user created</span></p>
<a href="controller?command=users" class="button1">Back</a>
</body>
</html>
