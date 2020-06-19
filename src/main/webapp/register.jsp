
<div align="center">
    <h1>Client Register Form</h1>
    <form method="post" action="${pageContext.request.contextPath}/register" autocomplete="off">

        <div class="form-group row">

             <label for="firstName" class="col-sm-2 col-form-label">FirstName</label>
            <div class="col-sm-7">
                <input type="text" class="form-control" id="firstname" name="firstName"
                       placeholder="Enter first name">
            </div>
        </div>

        <div class="form-group row">
            <label for="lastName" class="col-sm-2 col-form-label">LastName</label>
            <div class="col-sm-7">
                <input type="text" class="form-control" id="lastname" name="lastName"
                       placeholder="Enter last name">
            </div>
        </div>

        <div class=" form-group row">
           <label for="username" class="col-sm-2 col-form-label">Login</label>
            <div class="col-sm-7">
                <input type="text" class="form-control" id="username" name="username"
                       placeholder="Enter user name">
            </div>
        </div>

        <div class="form-group row">
            <label for="password" class="col-sm-2 col-form-label">Passwrod</label>
            <div class="col-sm-7">
                <input type="password" class="form-control" id="password" name="password"
                       placeholder="Enter Password">
            </div>
        </div>
     <input type="submit" value="Submit" />
    </form>
</div>

