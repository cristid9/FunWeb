
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Fun Web</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
    <script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>

</head>

<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
    </div>

    <div class="navbar-collapse collapse">
        <ul class="nav navbar-nav">
            <li class="navbar-left"><a href="#">Fun Web</a></li>
            <li data-toggle="modal" data-target="#myModal" id="helpModalButton"><a href="#">Help</a></li>
            <div class="modal fade" id="myModal" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Help</h4>
                        </div>
                        <div class="modal-body">
                            <h3>Tips:</h3>
                            <p>You should learn more from this chapter: </p>
                            <p id="helpModalContent"> </p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>

            <li><a href="#">Notifications</a></li>
            <li><a href="#">Chat</a></li>

            <li class="dropdown" id="right">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Options <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <form>
                        <li><a href="#">Sounds</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">Suport</a></li>
                        <li role="separator" class="divider"></li>
                        <li class="admin-options"><a href="/adminPannel">Admin Options </a> </li>
                        <li class="admin-options" role="separator" class="divider"></li>
                        <li><a href="#">Logout</a></li>
                    </form>
                </ul>
            </li>
        </ul>
    </div>
</nav>


<div class="row">
    <div class="col-sm-4">
        <div class="row">
            <div class="col-sm-1"> </div>

            <div class="col-sm-4">
                <img id="avatar" src="path" alt="avatar" width="150" height="150">
                <input type="text" class="form-control" name="newPassword" id="newPassword"/>
                <button id="updatePasswordButton" type="button" class="btn btn-default">
                    update password
                </button>
            </div>
            <div id = "atributes" class="col-sm-6">
                <br>
                <p> Name: ${Username} </p>
                <p> Level: ${Level} </p>
                <p> Title: ${Title} </p>
                <p> Gold: ${Gold} </p>
            </div>
        </div>
    </div>
    <div class="col-sm-7"> </div>
</div>

<h1 id="choose"	class="text-center">Choose a room!</h1>
<br>

<div class="row">


    <div class="col-sm-6">
        <h2 class="text-center"> Training room </h2>
    </div>


    <div class="col-sm-6">
        <h2 class="text-center"> Pvp room </h2>
    </div>
</div>



<div id="footer">
    <div class="container">
        <br><br><br>
        <p class="text-center">FunWeb &#9400; 2017 <p>
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

            $("#updatePasswordButton").on('click', function (e) {
                $.post("/updatePassword", {newPassword: $("#newPassword").val()}, function (data) {
                    alert("password was updated")
                })
            })
        });
    </script>
</body>

</html>