<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
            crossorigin="anonymous"
    />
</head>
<body>
<div class="container text-center my-5">
    <h1>Book card</h1>
    <div class="card" style="width: 18rem;">
        <img src="..." class="card-img-top" alt="BOOK PHOTO">
        <div class="card-body">
            <h5 class="card-title">${requestScope.book.name}</h5>
            <p class="card-text">Author: ${requestScope.book.author}<br>
                Publisher: ${requestScope.book.publisher}<br>
                Publishment date: ${requestScope.book.publishmentDate}<br>
                private BigDecimal price;
                Paperback: ${requestScope.book.paperback}<br>
                ISBN-10: ${requestScope.book.ISBN10}<br>
                ISBN13: ${requestScope.book.ISBN13}<br>
                Lexile measure: ${requestScope.book.lexileMeasure}<br>
                Language: ${requestScope.book.language}<br>
                Bestsellers rank: ${requestScope.book.bestSellersRank}<br>
                Weight: ${requestScope.book.weight} gramm<br>
                Dimensions: ${requestScope.book.dimensions} mm<br></p>
            <a href="#" class="btn btn-danger">Buy: ${requestScope.book.price} $</a>
        </div>
    </div>
</div>
</body>
</html>
