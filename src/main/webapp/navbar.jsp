<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light mb-2">

    <button class="navbar-toggler " type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>

    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">

            <li class="nav-item">
                <a class="nav-link" href="/">home</a>
            </li>
            <% Object s = session.getAttribute("nik");
            if (s != null) {%>
            <li class="nav-item">
                <a class="nav-link" href="/allproduct">allproduct</a>
            </li>
            <li class="nav-item" >
                <a class="nav-link" href = "/addproduct" > addproduct </a >
            </li><li class="nav-item" >
                <a class="nav-link" href = "/allclient" > listClients </a >
            </li>
            <%} %>
        </ul>
    </div>
    <div class="navbar-text mr-3">${nik}
        <c:if test="${nik==null}">
            Please,logIn
        </c:if>
        <%if (s != null) {%>
        <button class="btn btn-primary" type="button"onclick="location.href='/logout'"/>LogOut</button>
        <% } else { %>
            <button class="btn btn-primary" type="button"onclick="location.href='/login'"/>LogIn</button>
        <% } %>
    </div>
</nav>