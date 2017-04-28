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

    <link href="../css/index_style.css" rel="stylesheet">

</head>

<body>
<div class ="container-fluid" id="login">
    <div class="text-center">
        <h1> <span class="content-header wow fadeIn " data-wow-delay="0.2s" data-wow-duration="2s"> &lt; Fun Web  &#47; &gt;</span></h1>
        <h3 class = "bounce" >A new and exciting game!</h3>
    </div >


    <form action = "/login" method="POST" class = "items" >

        <div class="col-md-3"> </div>
        <div class="col-md-6">
            <div class="form-group">
                <label for="username">Your name</label>
                <input type="text" class="form-control" id="username" name="username" placeholder=" Enter UserName">
            </div>
            <div class="form-group">
                <label for="password">Your password</label>
                <input type="password" class="form-control" id="password" name="password" placeholder=" Enter password">
            </div>

            <div class="checkbox">
                <label><input type="checkbox"> Remember me</label>
            </div>

            <button type="submit" class="btn btn-success">Login</button>
            <button type="submit" class="btn btn-primary">Login with FaceBook</button>

            <p> Don't have an account? Register <a href= "register.html"> here! </a> </p>


        </div>
    </form>
</div>

<footer id = "footer">
    <div class="container">
        <p class="text-center" > FunWeb &#9400; 2017 </p>
    </div>
</footer>


<script>
    $(document).ready(function(){
        $('.content-header').textillate({
            in: {
                shuffle: false,
                sync: true
            },
            out: {
                effect: 'FadeOutRightBig',
                shuffle: false,
                sync: true
            }
        });

    })
</script>

<script>
    $(document).ready(function(){
        $('.bounce').textillate({
            loop: true,
            in: {
                effect: 'tada',
                delayScale: 1,
                delay: 150,
                shuffle: true
            },
            out: {
                effect: 'flipOutY',
                reverse: true
            }
        });

    })
</script>



</body>
</html>