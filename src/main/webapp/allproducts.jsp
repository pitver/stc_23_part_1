<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Product list</h2>

<table class="table">
    <thead>
    <tr>
        <th>ID</th>
        <th>productName</th>
        <th>price</th>
        <th>present</th>
        <th>edit</th>
        <th>delete</th>
        <th>add to bascket</th>

    </tr>
    </thead>
    <tbody>
    <c:forEach var="p" items="${product}">
        <tr>
            <td>${p.id}</td>
            <td>${p.productName}</td>
            <td>${p.price}</td>
            <td>${p.present}</td>
            <td><a href="${pageContext.request.contextPath}/edit?id=${p.id}">Edit</a></td>
            <td><a href="${pageContext.request.contextPath}/delete?id=${p.id}">Delete</a></td>
            <td>
                <div>
                    <a class="btn btn-primary"
                       href="${pageContext.request.contextPath}/addtoorder?id=${p.id}">
                        add
                    </a>
                </div>
            </td>

        </tr>
    </c:forEach>
    </tbody>
</table>

