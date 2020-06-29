<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Product list</h2>

<table class="table">
    <thead>
    <tr>
        <th>ID</th>
        <th>first_name</th>
        <th>last_name</th>
        <th>username</th>
        <th>roles</th>
        <th>edit</th>
        <th>delete</th>

    </tr>
    </thead>
    <tbody>
    <c:forEach var="c" items="${client}">
        <tr>
            <td>${c.id}</td>
            <td>${c.firstName}</td>
            <td>${c.lastName}</td>
            <td>${c.username}</td>
            <td>${c.roles}</td>
           <td><a href="${pageContext.request.contextPath}/editclient?id=${c.id}">Edit</a></td>
            <td><a href="${pageContext.request.contextPath}/deleteclient?id=${c.id}">Delete</a></td>
          </tr>
    </c:forEach>
    </tbody>
</table>

