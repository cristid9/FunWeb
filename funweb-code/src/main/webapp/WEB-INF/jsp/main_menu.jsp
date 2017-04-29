<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Fun Web</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script type="text/javascript"
                src="https://cdnjs.cloudflare.com/ajax/libs/phaser/2.6.0/phaser.js">
        </script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="main_menucss.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
        <script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
        <link rel="stylesheet" href="/resources/css/main_menu_style.css" />

    </head>

    <body>
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

        <br/>
        <br/>
        <br/>
        <br/>
        <div class="row">
            <div class="col-sm-3"> </div>
            <div class="col-sm-4">
                <div class="grid_6">
                    <div class="row">
                        <img src="/resources/assets/sqlQuestioner.png" width="30%" height="30%"/>
                    </div>
                    <div class="row">
                        <a id="arenaPlay" href="#"> Join Arena </a>
                    </div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="grid_6">
                    <div class="row">
                        <img src="/resources/assets/frontendQuestioner.png" width="30%" height="30%" />
                    </div>
                    <div class="row">
                        <a id="training" href="/arena"> Start training </a>
                    </div>
                </div>
            </div>
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