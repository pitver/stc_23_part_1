
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Edit product</h1>
<form method="post" action="${pageContext.request.contextPath}/edit" autocomplete="off">
    <div class="form-group">
        <label for="productName">ProductName</label>
        <input name="productName" type="text" class="form-control" id="productName" value="${product.productName}">
    </div>
    <div class="form-group">
        <label for="price">Price</label>
        <input name="price" type="text" class="form-control" id="price" value="${product.price}">
    </div>
    <div class="form-group">
        <label for="present">Present</label>
        <input name="present" type="text" class="form-control" id="present" value="${product.present}">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>


