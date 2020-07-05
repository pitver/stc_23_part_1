<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Вершинин Пётр
  Date: 19.06.2020
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
        <h1>Error details</h1>

<jsp:body>
    <c:if test="${statusCode != 500}">
        <strong>Status Code</strong>: ${statusCode}<br/>
        <strong>Requested URI</strong>: ${requestUri}
    </c:if>
    <c:if test="${statusCode == 500}">
        <ul>
            <li>Servlet Name: ${servletName} </li>
            <li>Exception Name: ${throwableName} </li>
            <li>Requested URI: ${requestUri} </li>
            <li>Exception Message: ${throwableMessage} </li>
        </ul>
        <br><br>
        <a href="/allproduct/">Main page</a>
    </c:if>
</jsp:body>
</html>
