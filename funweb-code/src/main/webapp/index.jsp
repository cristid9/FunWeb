<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>FunWeb</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="./resources/css/main.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class = "jumbotron">
    <h1 class = "text-center">&lt; Fun Web  &#92; &gt;</h1>
    <p class = "text-center">&lt;p&gt; A new and exciting game! &lt;&#92;p&gt; </p>
</div>



<div class="col-sm-4"></div>
<div class="col-sm-4"> <h3>Login </h3>
    <form>
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="username" class="form-control" id="username" placeholder="Enter username">
        </div>

        <div>
            <label for="password">Password:</label>
            <input type="password" class="form-control" id="password" placeholder="Enter password">
        </div>

        <div class="checkbox">
            <label><input type="checkbox"> Remember me</label>
        </div>

        <form>
            <button formaction="main_menu.html" type="login" class="btn btn-success"> Login </button>
        </form>
        <button type="login" class="btn btn-primary">Login with FaceBook</button>

        <br> <br>
        <p> Don't have an account? Register <a href= "register.html"> here! </a> </p>

    </form>
</div>


<div id="footer">
    <div class="container">
        <br><br><br>
        <p class="text-center">FunWeb &#9400; 2017 <p>
    </div>
</div>

</body>
</html>