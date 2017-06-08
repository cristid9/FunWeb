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

    <h2 class ="text-center"> Reset your password </h2>

    <form class="items" action="" method="post">

        <div class="col-md-3"> </div>
        <div class="col-md-6">
            <div class="form-group">
                <label for="new_password"> New password </label>
                <input type="text" class="form-control" id="new_password" name="new_password" placeholder="Enter the new password">

                <label for="new_password2"> New password </label>
                <input type="text" class="form-control" id="new_password2" name="new_password2" placeholder="Retype the new password">
            </div>

            <button type="submit" class="btn btn-success">Recover Password</button>

        </div>
    </form>
</div>
<br><br><br><br>

<footer id = "footer">
    <div class="container">
        <p class="text-center">Contact | FunWeb &#9400; 2017 <p>
    </div>
</footer>

</body>
</html>
