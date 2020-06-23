<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>
    Welcome ${nik}
    <c:if test="${nik==null}">
        guest
    </c:if>
</h1>



