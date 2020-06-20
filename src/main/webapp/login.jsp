<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Main</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div align="center">
    <h1>Client Login Form</h1>
    <form method="post" action="${pageContext.request.contextPath}/login" autocomplete="off">

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
</body>
</html>

