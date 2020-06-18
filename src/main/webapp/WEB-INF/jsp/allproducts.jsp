<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

