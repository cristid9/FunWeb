<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
        <script type="text/javascript"
                src="https://cdnjs.cloudflare.com/ajax/libs/phaser/2.6.0/phaser.js">
        </script>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://fonts.googleapis.com/css?family=Oleo+Script:400,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Teko:400,700" rel="stylesheet">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/lettering.js/0.7.0/jquery.lettering.min.js"> </script>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css" rel="stylesheet">
        <link href="/resources/css/arena_style.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/textillate/0.4.0/jquery.textillate.js"> </script>
        <script src="/resources/js/game_tutorial/mainState.js"> </script>
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
                                <li class="active">
                                    <a href="/main_menu" class="glyphicon glyphicon-home"></a>
                                </li>

                                <li id="myBtn" >
                                    <a class="glyphicon glyphicon-question-sign" href="#"></a>
                                </li>
                                <li>
                                    <a class="glyphicon glyphicon-comment" href="/quick_chat"></a>
                                </li>
                                <li class="notifications">
                                    <a class="glyphicon glyphicon-exclamation-sign" href="#"></a>
                                </li>

                            </ul>
                            <ul class="nav navbar-nav pull-right">
                                <li class=" dropdown"><a href="#" class="dropdown-toggle active glyphicon glyphicon-wrench" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> <span class="caret"></span></a>
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

        <div class="container">

            <!-- Trigger the modal with a button -->

            <!-- Modal -->
            <div class="modal fade" id="myModal" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">

                        <div class="modal-body">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h1 id="title">Test your Web Technologies skills!</h1>

                            <ul class = "question"> What is HTML?
                                <li class = "answer"> Lorem ipsum </li>
                                <li class = "answer"> Lorem ipsum </li>
                                <li class = "answer"> Lorem ipsum </li>
                            </ul>

                            <div id="yourscore"><p>Score: <span id="score"></span></p></div>
                        </div>

                    </div>

                </div>
            </div>

        </div>

        <script type="text/javascript">
            $(window).on('load',function(){
                $('#myModal').modal('show');
            });
        </script>

        <script src="/resources/js/generic/question.js"></script>
        <script src="/resources/js/game_tutorial/questionPool.js"> </script>
        <script src="/resources/js/game_tutorial/initLogic.js"></script>
        <script src="/resources/js/game_tutorial/loadingState.js"></script>
        <script src="/resources/js/game_tutorial/questionState.js"> </script>
        <script src="/resources/js/game_tutorial/mainState.js"> </script>
        <script type="text/javascript" src="/resources/js/game_tutorial/gameMain.js">
        </script>

    </body>
</html>
