
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Fun Web</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="main_menucss.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
    <script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
    <style type="text/css">
        p.detail { color:#4C4C4C;font-weight:bold;font-family:Comic;font-size:19 }
        span.name { color:#696969;font-weight:bold;font-family:Tahoma;font-size:18 }
        span.title { color:#00061a;font-weight:bold;font-family:Tahoma;font-size:30 }
        p.gold { color:#EAC117;font-weight:bold;font-family:Comic;font-size:19 }
    </style>
</head>

<body background="http://i.imgur.com/QbmsCVq.jpg">
<div class="navbar-wrapper">
    <div class="container-fluid">
        <nav class="navbar navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">FunWeb</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="#" class="">Home</a></li>

                        <li class="notifications"><a href="#">Notifications</a>
                            <div class="messages">
                                <a href="/arena" class="message">Giani invited you to play in Arena</a>
                            </div>
                        </li>
                        <li><a href="#">Chat</a></li>
                        <li id="myBtn"><a href="#">Help</a></li>
                        <div id="myModal" class="modal">
                            <div class="modal-content">
                                <span class="close">&times;</span>
                                <p><h2>Help</h2>
                                Bun venit pe FunWeb! Acumuleaza cat mai multe cunostiinte despre Tehnologii Web si intrece-te cu ceilalti!</p>
                            </div>

                        </div>
                    </ul>
                    <ul class="nav navbar-nav pull-right">
                        <li class=" dropdown"><a href="#" class="dropdown-toggle active" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Options <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Change Password</a></li>
                                <li><a href="#">Admin Menu</a></li>
                            </ul>
                        </li>
                        <li class=""><a href="#">Logout</a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
</div>


<div class="row">
    <div class="col-sm-4">
        <div class="row">
            <div class="col-sm-1"> </div>

            <div class="col-sm-4">
                <img id="avatar" src="http://i.imgur.com/Oj65hZF.pnghttp://i.imgur.com/Oj65hZF.png" alt="avatar" width="150" height="150">
            </div>
            <div class="verticalLine">
                <div id = "atributes" class="col-sm-6">

                    <p class="detail"> Name: ${Username} </p>
                    <p class="detail"> Level: ${Level} </p>
                    <p class="detail"> Title: ${Title} </p>
                    <p class="gold"> Coins: ${Gold} </p>
                </div>
            </div>
        </div>
    </div>
    <div class="col-sm-7"> </div>
</div>
<hr><br>
<h2 id="choose" class="text-center"><span class="title"> Welcome to FunWeb !</span></h2>
<br>
<div class="row">
    <div class="col-sm-1"> </div>
    <div class="col-sm-5">
        <div class="grid_6">
            <div class="p1_box right cl4">
                <div class="type"></div>
                <a href="#" class="bot" align="left" id="arena">Arena</a>
            </div>
        </div>
    </div>
    <div class="col-sm-5">
        <div class="grid_6">
            <div class="p1_box right cl4">
                <div class="type"></div>
                <a href="/arena" class="bot" align="left" id="tutorial">Tutorial Mode</a>
            </div>
        </div>
    </div>
    <div class="col-sm-1"> </div>
</div>



<div id="footer">
    <div class="container">
        <br><br><br>
        <p class="text-center"><span class="name">FunWeb &#9400; 2017 </span><p>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {

        $("#helpModalButton").on('click', function(e) {
            $.post("/weakestChapter", {}, function(data) {
                data = JSON.parse(data);

                $("#helpModalContent").html(data.weakestChapter);
            });

        });


    });

    $(function() {
        $(".notifications .messages").hide();
        $(".notifications").click(function() {
            if ($(this).children(".messages").children().length > 0) {
                $(this).children(".messages").fadeToggle(300);
            }
        });
    });

    var modal = document.getElementById('myModal');

    // Get the button that opens the modal
    var btn = document.getElementById("myBtn");

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

    // When the user clicks on the button, open the modal
    btn.onclick = function() {
        modal.style.display = "block";
    }

    // When the user clicks on <span> (x), close the modal
    span.onclick = function() {
        modal.style.display = "none";
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }

</script>
</body>

</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Fun Web</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="main_menucss.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
    <script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
    <style type="text/css">
        p.detail { color:#4C4C4C;font-weight:bold;font-family:Comic;font-size:19 }
        span.name { color:#696969;font-weight:bold;font-family:Tahoma;font-size:18 }
        span.title { color:#00061a;font-weight:bold;font-family:Tahoma;font-size:30 }
        p.gold { color:#EAC117;font-weight:bold;font-family:Comic;font-size:19 }

        @import url(http://fonts.googleapis.com/css?family=Lato);

        .p1_box {
            position: relative;
            padding: 1px;
            margin-bottom: 36px;
            border: 2px solid #ff6765;
        }

        .p1_box a.bot {
            margin-top: 1px;
            padding: 22px 0 20px 42px;
            border: 2px solid #ff6765;
            display: block;
            position: relative;
            background-color: #ff6765;
            color: #2a394e;
            font: 27px/27px 'Economica', sans-serif;
        }

        .p1_box a.bot:hover {
            background-color: #fff;
        }

        .p1_box.left {
            margin-right: 23px;
        }

        .p1_box.right {
            margin-left: 23px;
        }

        .p1_box.right:after {
            top: 126px;
            left: -12px;
            width: 0px;
            height: 0px;
            border-style: solid;
            border-width: 10px 12px 10px 0;
            border-color: transparent #ff6360 transparent transparent;

        }

        .cl4 {
            border-color: #2a394e;
        }

        .cl4 a.bot {
            background-color: #f3f6f8;
            border-color: #f3f6f8;
            padding-left: 70px;
            padding-top: 75px;
            margin-top: 0;
            padding-bottom: 59px;
            font-size: 36px;
            line-height: 36px;

        }

        #arena{
            background-image: url("http://i.imgur.com/OT41iNM.jpg");
            background-size: 500px 180px;

        }

        #tutorial{
            background-image: url("http://i.imgur.com/B2mqEZb.jpg");
            background-size: 525px 200px;
        }

        .cl4 a.bot:hover {
            color: #fff;
            background-color: #2a394e;
            border-color: #2a394e;
        }

        .cl4:before {
            top: 57px !important;
            background-color: #2a394e;

        }

        .cl4.right:after {
            top: 52px;
            border-color: transparent #2a394e transparent transparent;
        }


        .navbar, .dropdown-menu{
            background:rgba(255,255,255,0.25);
            border: none;

        }

        .nav>li>a, .dropdown-menu>li>a:focus, .dropdown-menu>li>a:hover, .dropdown-menu>li>a, .dropdown-menu>li{
            border-bottom: 3px solid transparent;
        }
        .nav>li>a:focus, .nav>li>a:hover,.nav .open>a, .nav .open>a:focus, .nav .open>a:hover, .dropdown-menu>li>a:focus, .dropdown-menu>li>a:hover{
            border-bottom: 3px solid transparent;
            background: none;
        }
        .navbar a, .dropdown-menu>li>a, .dropdown-menu>li>a:focus, .dropdown-menu>li>a:hover, .navbar-toggle{
            color: black;
        }
        .dropdown-menu{
            -webkit-box-shadow: none;
            box-shadow:none;
        }

        .nav li:hover:nth-child(8n+1), .nav li.active:nth-child(8n+1){
            border-bottom: #C4E17F 3px solid;
        }
        .nav li:hover:nth-child(8n+2), .nav li.active:nth-child(8n+2){
            border-bottom: #F7FDCA 3px solid;
        }
        .nav li:hover:nth-child(8n+3), .nav li.active:nth-child(8n+3){
            border-bottom: #FECF71 3px solid;
        }
        .nav li:hover:nth-child(8n+4), .nav li.active:nth-child(8n+4){
            border-bottom: #F0776C 3px solid;
        }
        .nav li:hover:nth-child(8n+5), .nav li.active:nth-child(8n+5){
            border-bottom: #DB9DBE 3px solid;
        }
        .nav li:hover:nth-child(8n+6), .nav li.active:nth-child(8n+6){
            border-bottom: #C49CDE 3px solid;
        }
        .nav li:hover:nth-child(8n+7), .nav li.active:nth-child(8n+7){
            border-bottom: #669AE1 3px solid;
        }
        .nav li:hover:nth-child(8n+8), .nav li.active:nth-child(8n+8){
            border-bottom: #62C2E4 3px solid;
        }

        .navbar-toggle .icon-bar{
            color: #fff;
            background: #fff;
        }

        #atributes{
            margin-top: 80px;
        }

        body{
            width: 98%;
        }

        a {
            font-size: 19px;
            color: #808080;
        }

        a.navbar-brand {
            font-size: 27px;
            font-weight:bold;
            font-family:Times;
            color: #2F4F4F;
        }

        hr {
            display: block;
            height: 1px;
            border: 0;
            border-top: 1px solid #ccc;
            margin: 1em 0;
            padding: 0;
        }

        img {
            margin-top: 80px;
        }



        html {
            font:12pt Lato, sans-serif;
            min-height:100%;
        }

        .notifications .messages {
            display:block;
            position:absolute;
            left:-40px;
            width:300px;
            max-height:240px;
        }

        .notifications .messages:before {
            content:'.';
            display:block;
            position:absolute;
            margin-left:-10px;
            left:50%;
            top:-18px;
            width:0;
            height:0;
            color:transparent;
            border:10px solid black;
            border-color:transparent transparent white;
        }

        .notifications .messages .message {
            display:block;
            position:relative;
            padding:10px;
            background-color:#FAFAD2;
            color:black;
            text-decoration:none;
        }

        .notifications .messages .message + .message {
            border-top:1px solid #e0e0e0;
        }

        .verticalLine {
            border-left: thick solid #ff0000;
        }

        .modal {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 1; /* Sit on top */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgb(0,0,0); /* Fallback color */
            background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
        }

        /* Modal Content/Box */
        .modal-content {
            background-color: #fefefe;
            margin: 15% auto; /* 15% from the top and centered */
            padding: 20px;
            border: 1px solid #888;
            width: 30%; /* Could be more or less, depending on screen size */
            height: 40%;
        }

        /* The Close Button */
        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }

    </style>
</head>

<body background="images/backgrmenu.jpg">
<div class="navbar-wrapper">
    <div class="container-fluid">
        <nav class="navbar navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">FunWeb</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="#" class="">Home</a></li>

                        <li class="notifications"><a href="#">Notifications</a>
                            <div class="messages">
                                <a href="#" class="message">Giani invited you to play in Arena</a>
                            </div>
                        </li>
                        <li><a href="#">Chat</a></li>
                        <li id="myBtn"><a href="#">Help</a></li>
                        <div id="myModal" class="modal">
                            <div class="modal-content">
                                <span class="close">&times;</span>
                                <p><h2>Help</h2>
                                Bun venit pe FunWeb! Acumuleaza cat mai multe cunostiinte despre Tehnologii Web si intrece-te cu ceilalti!</p>
                            </div>

                        </div>
                    </ul>
                    <ul class="nav navbar-nav pull-right">
                        <li class=" dropdown"><a href="#" class="dropdown-toggle active" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Options <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Change Password</a></li>
                                <li><a href="#">Admin Menu</a></li>
                            </ul>
                        </li>
                        <li class=""><a href="#">Logout</a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
</div>


<div class="row">
    <div class="col-sm-4">
        <div class="row">
            <div class="col-sm-1"> </div>

            <div class="col-sm-4">
                <img id="avatar" src="images/logoFunWeb.png" alt="avatar" width="150" height="150">
            </div>
            <div class="verticalLine">
                <div id = "atributes" class="col-sm-6">

                    <p class="detail"> Name: ${Username} </p>
                    <p class="detail"> Level: ${Level} </p>
                    <p class="detail"> Title: ${Title} </p>
                    <p class="gold"> Coins: ${Gold} </p>
                </div>
            </div>
        </div>
    </div>
    <div class="col-sm-7"> </div>
</div>
<hr><br>
<h2 id="choose" class="text-center"><span class="title"> Welcome to FunWeb !</span></h2>
<br>
<div class="row">
    <div class="col-sm-1"> </div>
    <div class="col-sm-5">
        <div class="grid_6">
            <div class="p1_box right cl4">
                <div class="type"></div>
                <a href="#" class="bot" align="left" id="arena">Arena</a>
            </div>
        </div>
    </div>
    <div class="col-sm-5">
        <div class="grid_6">
            <div class="p1_box right cl4">
                <div class="type"></div>
                <a href="/arena" class="bot" align="left" id="tutorial">Tutorial Mode</a>
            </div>
        </div>
    </div>
    <div class="col-sm-1"> </div>
</div>



<div id="footer">
    <div class="container">
        <br><br><br>
        <p class="text-center"><span class="name">FunWeb &#9400; 2017 </span><p>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {

        $("#helpModalButton").on('click', function(e) {
            $.post("/weakestChapter", {}, function(data) {
                data = JSON.parse(data);

                $("#helpModalContent").html(data.weakestChapter);
            });

        });


    });

    $(function() {
        $(".notifications .messages").hide();
        $(".notifications").click(function() {
            if ($(this).children(".messages").children().length > 0) {
                $(this).children(".messages").fadeToggle(300);
            }
        });
    });

    var modal = document.getElementById('myModal');

    // Get the button that opens the modal
    var btn = document.getElementById("myBtn");

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

    // When the user clicks on the button, open the modal
    btn.onclick = function() {
        modal.style.display = "block";
    }

    // When the user clicks on <span> (x), close the modal
    span.onclick = function() {
        modal.style.display = "none";
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }

</script>
</body>

</html>