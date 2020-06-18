
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <title>Title</title>
</head>
<body>

<jsp:useBean id="product" class="ru.vershinin.lesson21.pojo.Product" />
<c:set target="${product}" property="productName" value="product" />

<h1>Adding a new product</h1>
<form method="post" action="${pageContext.request.contextPath}/addproduct" autocomplete="off">
    <div class="form-group">
        <label for="productName">ProductName</label>
        <input name="productName" type="text" class="form-control" id="productName" value="<jsp:getProperty name="product" property="productName" />">
    </div>
    <div class="form-group">
        <label for="price">Price</label>
        <input name="price" type="text" class="form-control" id="price" value="<jsp:getProperty name="product" property="price" />">
    </div>
    <div class="form-group">
        <label for="present">Present</label>
        <input name="present" type="text" class="form-control" id="present" value="<jsp:getProperty name="product" property="present" />">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>

</body>
</html>
