<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h2>Product list</h2>


<ul>
    <c:forEach items="${product}" var="product">
        <li>  ${product.productName} - ${product.price} - ${product.present} </li>
    </c:forEach>
</ul>

</body>
</html>