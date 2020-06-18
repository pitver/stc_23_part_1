<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<h2>Product list</h2>

<table class="table">
    <thead>
    <tr>
        <th>ID</th>
        <th>productName</th>
        <th>price</th>
        <th>present</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="p" items="${product}">
        <tr>
            <td>${p.id}</td>
            <td>${p.productName}</td>
            <td>${p.price}</td>
            <td>${p.present}</td>
          </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>