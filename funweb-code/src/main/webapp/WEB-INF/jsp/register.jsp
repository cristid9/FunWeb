<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Fun Web</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>

        <div class="container-fluid">

            <div class="jumbotron">
                <h1 class="text-center">&lt; Fun Web &#92; &gt;</h1>
                <p class="text-center">&lt;p&gt; A new and exciting game! &lt;&#92;p&gt; </p>
            </div>


            <div class="col-sm-4"></div>
            <div class="col-sm-4"><h3>Register</h3>
                <form>
                    <div class="form-group">
                        <label for="email">E-mail:</label>
                        <input type="email" class="form-control" id="email" placeholder="Enter e-mail">
                    </div>

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

                    <button type="login" class="btn btn-success" href="/register">Register</button>
                    <button type="login" class="btn btn-primary">Register with FaceBook</button>
                    </a>


                </form>
            </div>

        </div>

        <div id="footer">
            <div class="container">
                <br><br><br>
                <p class="text-center">FunWeb &#9400; 2017
                <p>
            </div>
        </div>

        <script type="text/javascript">
            $(document).ready(function() {
                $("#username").tooltip("hide");
                $("#username").change(function () {
                    $.post("/checkUsernameAvailable", {
                        username: $("#username").val()
                    }, function (data) {

                        data = JSON.parse(data);

                        if (data.status === 'ok') {
                            $("#username").tooltip("hide");
                        } else if (data.status === "taken") {
                            $("#username").attr("title",
                                "The username is already taken, what about: " + data.suggestion);
                            $("#username").tooltip("show");
                            $("#username").trigger("mouseover");
                        }

                        // the tool tip should be displayed conditionally, depending on the server's output
                    });
                });
            });
        </script>

    </body>
</html>
