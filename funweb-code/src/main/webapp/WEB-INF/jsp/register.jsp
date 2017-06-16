<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<head>
    <title>FunWeb </title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Oleo+Script:400,700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Teko:400,700" rel="stylesheet">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/lettering.js/0.7.0/jquery.lettering.min.js"> </script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/textillate/0.4.0/jquery.textillate.js"> </script>

    <link href="/resources/css/index_style.css" rel="stylesheet">
    <script src="/resources/js/pages/bounceText.js"> </script>
    <script src="/resources/js/pages/titleInAnimation.js"> </script>
</head>

<body>
<div class ="container-fluid" id="login">
    <div class="text-center">
        <h1> <span class="content-header wow fadeIn " data-wow-delay="0.2s" data-wow-duration="2s"> &lt; Fun Web  &#47; &gt;</span></h1>
        <h3 class="bounce">A new and exciting game!</h3>
    </div>



    <form class="items" action="/validateRegistration" method="post">

        <div class="col-md-3"> </div>
        <div class="col-md-6">
            <div class="form-group">
                <label for="email"> E-mail </label>
                <input type="text" class="form-control" id="email" name="email" placeholder=" Enter your e-mail">
            </div>
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" name="username" placeholder=" Enter Username">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" placeholder=" Enter password">
            </div>

            <div class="checkbox">
                <label><input type="checkbox"> Remember me</label>
            </div>

            <button type="submit" class="btn btn-success">Register</button>
            <button type="submit" onclick="javascript:login();" class="btn btn-primary">Register with FaceBook</button>



        </div>
    </form>
</div>

<footer id = "footer">
    <div class="container">
        <p class="text-center">Contact | FunWeb &#9400; 2017 <p>
    </div>
</footer>


<script type="text/javascript">
            $(document).ready(function() {
                $("#username").change(function () {
                    $("#username").tooltip("hide");
                    $.post("/checkUsernameAvailable", {
                        username: $("#username").val()
                    }, function (data) {

                        data = JSON.parse(data);

                        if (data.status === 'ok') {
                            $("#username").tooltip("hide");
                        } else if (data.status === "taken") {
                            $("#username").attr("title",
                                "The username is already taken, what about: " + data.suggestion);
                            $("#username").tooltip("fixTitleda");
                            $("#username").tooltip("show");
                            $("#username").trigger("mouseover");
                        }

                        // the tool tip should be displayed conditionally, depending on the server's output
                    });


                });

                $("#password").change(function () {
                    $("#password").tooltip("hide");

                    $.post("/checkPasswordStrength", {
                        password: $("#password").val()
                    }, function (data) {
                        data = JSON.parse(data);

                        var strengthString = null;

                        if (data.strength === 0) {
                            strengthString = "weak password";
                        } else if (data.strength === 1) {
                            strengthString = "medium password";
                        } else {
                            strengthString = "strong password";
                        }

                        $("#password").attr("title", strengthString);
                        $("#password").tooltip("fixTitle");
                        $("#password").tooltip("show");
                        $("#password").trigger("mouseover");

                    })
                });
            });
        </script>
        <script>
            window.fbAsyncInit = function() {
                FB.init({
                    appId            : '153766825168453',
                    autoLogAppEvents : true,
                    xfbml            : true,
                    version          : 'v2.9'
                });
                FB.AppEvents.logPageView();
            };

            (function(d, s, id){
                var js, fjs = d.getElementsByTagName(s)[0];
                if (d.getElementById(id)) {return;}
                js = d.createElement(s); js.id = id;
                js.src = "//connect.facebook.net/en_US/sdk.js";
                fjs.parentNode.insertBefore(js, fjs);
            }(document, 'script', 'facebook-jssdk'));
        </script>




    </body>
</html>
