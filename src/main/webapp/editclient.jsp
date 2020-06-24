
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Edit client</h1>
<form method="post" action="${pageContext.request.contextPath}/editclient" autocomplete="off">
    <div class="form-group">
        <label for="firstName">FirstName</label>
        <input name="firstName" type="text" class="form-control" id="firstName" value="${client.firstName}">
    </div>
    <div class="form-group">
        <label for="lastName">LastName</label>
        <input name="lastName" type="text" class="form-control" id="lastName" value="${client.lastName}">
    </div>
    <div class="form-group">
        <label for="userName">login</label>
        <input name="userName" type="text" class="form-control" id="userName" value="${client.userName}">
    </div>
    <div class="form-group">
        <label for="password">password</label>
        <input name="password" type="text" class="form-control" id="password" value="${client.password}">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>


