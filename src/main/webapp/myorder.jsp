<%@ page import="java.util.List" %>
<%@ page import="ru.vershinin.lesson24.pojo.Order" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Product list</h2>

<table class="table">
    <thead>
    <tr>

        <th>IdOrder</th>
        <th>NumberOrder</th>
        <th>first_name</th>
        <th>last_name</th>
        <th>username</th>
        <th>productName</th>
        <th>price</th>
        <th>present</th>

    </tr>
    </thead>
    <tbody>

    <c:forEach var="o" items="${order}">
        <tr>

            <td>${o.id}</td>
            <td>${o.numberOrder}</td>
            <td>${o.client.firstName}</td>
            <td>${o.client.lastName}</td>
            <td>${o.client.username}</td>
            <td>${o.product.productName}</td>
            <td>${o.product.price}</td>
            <td>${o.product.present}</td>
                <%--<li><c:out value="${o.client.username}" /></li>--%>

          </tr>
    </c:forEach>

    </tbody>
</table>

<form method="post" action="${pageContext.request.contextPath}/myorder" autocomplete="off">
    <button type="submit" value="submit" class="btn btn-primary">Submit</button>
</form>
