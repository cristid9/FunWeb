<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
        <script type="text/javascript"
                src="https://cdnjs.cloudflare.com/ajax/libs/phaser/2.6.0/phaser.js">
        </script>

        <style type="text/css">
            #nextQuestion {
                display: none;
            }
        </style>

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
            <div class="modal fade" id="questionsPane" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">

                        <div class="modal-body">
                            <button type="button" id="questionPaneClose" class="close" data-dismiss="modal">&times;</button>
                            <h1 id="questionsPaneTopic">Test your Web Technologies skills!</h1>

                            <ul class = "question">
                                <h1 id="questionsPaneQuestion"> replace me </h1>
                                <li id="questionsPaneOption1" class="answer">
                                    replace me
                                </li>
                                <li id="questionsPaneOption2" class="answer">
                                    replace me
                                </li>
                                <li id="questionsPaneOption3" class="answer">
                                    replace me
                                </li>
                            </ul>

                            <button type="button" id="nextQuestion">
                                next
                            </button>
                            <div id="yourscore"><p>Score: <span id="score"></span></p></div>
                        </div>

                    </div>

                </div>
            </div>


            <div class="modal fade" id="scorePane" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">

                        <div class="modal-body">
                            <h1 id="scorePaneMain">  </h1>
                        </div>

                    </div>

                </div>
            </div>



        </div>


        <div id="npcsModal">

            <div class="modal fade" id="modalNPC1" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Modal Header</h4>
                        </div>
                        <div class="modal-body">
                            <p>Some text in the modal.</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" id="NPC1Start" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>

                </div>
            </div>

            <div class="modal fade" id="modalNPC2" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Modal Header</h4>
                        </div>
                        <div class="modal-body">
                            <p>Some text in the modal.</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" id="NPC2Start" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>

                </div>
            </div>

            <div class="modal fade" id="modalNPC3" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Modal Header</h4>
                        </div>
                        <div class="modal-body">
                            <p>Some text in the modal.</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" id="NPC3Start" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>

                </div>
            </div>

            <div class="modal fade" id="modalNPC4" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Modal Header</h4>
                        </div>
                        <div class="modal-body">
                            <p>Some text in the modal.</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" id="NPC4Start" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>

                </div>
            </div>

        </div>

        <script type="text/javascript">
//            $(window).on('load',function(){
//                $('#myModal').modal('show');
//            });
        </script>

        <script src="/resources/js/generic/question.js"></script>
        <script src="/resources/js/game_tutorial/questionPool.js"> </script>
        <script src="/resources/js/game_tutorial/initLogic.js"></script>
        <script src="/resources/js/game_tutorial/loadingState.js"></script>
        <script src="/resources/js/game_tutorial/questionState.js"> </script>
        <script src="/resources/js/game_tutorial/mainState.js"> </script>
        <script type="text/javascript" src="/resources/js/game_tutorial/gameMain.js"></script>
        <script src="/resources/js/misc/questionLoader.js"></script>
        <script type="text/javascript">
            // questions answering logic required here

            var selectedNPC = null;
            var selectedNPCQuestion = null;
            var currentScore = 0;

            $(document).ready(function() {
                $('#NPC1Start').on('click', function() {
                    selectedNPC = 1;
                    selectedNPCQuestion = 0;
                    loadNpcQuestions(selectedNPC, selectedNPCQuestion);
                });

                $('#NPC2Start').on('click', function() {
                    selectedNPC = 2;
                    selectedNPCQuestion = 0;
                    loadNpcQuestions(selectedNPC, selectedNPCQuestion);
                });

                $('#NPC3Start').on('click', function() {
                    selectedNPC = 3;
                    selectedNPCQuestion = 0;
                    loadNpcQuestions(selectedNPC, selectedNPCQuestion);
                });

                $('#NPC4Start').on('click', function() {
                    selectedNPC = 4;
                    selectedNPCQuestion = 0;
                    loadNpcQuestions(selectedNPC, selectedNPCQuestion);
                });

                $('#questionsPaneOption1').on('click', function() {

                    console.log('Question 1');

                    if (isOptionCorrect(selectedNPC, selectedNPCQuestion, 0)) {
                        $('#questionsPaneOption1').css({'color': 'green'});
                        $('#questionsPaneOption2').css({'color': 'red'});
                        $('#questionsPaneOption3').css({'color': 'red'});
                        currentScore += 1;
                    } else if (isOptionCorrect(selectedNPC, selectedNPCQuestion, 1)) {
                        $('#questionsPaneOption1').css({'color': 'red'});
                        $('#questionsPaneOption2').css({'color': 'green'});
                        $('#questionsPaneOption3').css({'color': 'red'});
                    } else if (isOptionCorrect(selectedNPC, selectedNPCQuestion, 2)) {
                        $('#questionsPaneOption1').css({'color': 'red'});
                        $('#questionsPaneOption2').css({'color': 'red'});
                        $('#questionsPaneOption3').css({'color': 'green'});
                    }
                    $('#nextQuestion').show();
                });

                $('#questionsPaneOption2').on('click', function() {

                    console.log('question 2');

                    if (isOptionCorrect(selectedNPC, selectedNPCQuestion, 0)) {
                        $('#questionsPaneOption1').css({'color': 'green'});
                        $('#questionsPaneOption2').css({'color': 'red'});
                        $('#questionsPaneOption3').css({'color': 'red'});
                    } else if (isOptionCorrect(selectedNPC, selectedNPCQuestion, 1)) {
                        $('#questionsPaneOption1').css({'color': 'red'});
                        $('#questionsPaneOption2').css({'color': 'green'});
                        $('#questionsPaneOption3').css({'color': 'red'});
                        currentScore += 1;

                    } else if (isOptionCorrect(selectedNPC, selectedNPCQuestion, 2)) {
                        $('#questionsPaneOption1').css({'color': 'red'});
                        $('#questionsPaneOption2').css({'color': 'red'});
                        $('#questionsPaneOption3').css({'color': 'green'});
                    }
                    $('#nextQuestion').show();
                });

                $('#questionsPaneOption3').on('click', function() {

                    console.log('question 3');

                    if (isOptionCorrect(selectedNPC, selectedNPCQuestion, 0)) {
                        $('#questionsPaneOption1').css({'color': 'green'});
                        $('#questionsPaneOption2').css({'color': 'red'});
                        $('#questionsPaneOption3').css({'color': 'red'});
                    } else if (isOptionCorrect(selectedNPC, selectedNPCQuestion, 1)) {
                        $('#questionsPaneOption1').css({'color': 'red'});
                        $('#questionsPaneOption2').css({'color': 'green'});
                        $('#questionsPaneOption3').css({'color': 'red'});
                    } else if (isOptionCorrect(selectedNPC, selectedNPCQuestion, 2)){
                        $('#questionsPaneOption1').css({'color': 'red'});
                        $('#questionsPaneOption2').css({'color': 'red'});
                        $('#questionsPaneOption3').css({'color': 'green'});
                        currentScore += 1;
                    }
                    $('#nextQuestion').show();
                });

                $('#questionPaneClose').on('click', function() {
                    $('#questionsPaneOption1').css({'color': 'black'});
                    $('#questionsPaneOption2').css({'color': 'black'});
                    $('#questionsPaneOption3').css({'color': 'black'});
                    currentScore = 0;
                });

                $('#nextQuestion').on('click', function() {
                    selectedNPCQuestion += 1;
                    if (hasNextQuestion(selectedNPC, selectedNPCQuestion)) {
                        loadNpcQuestions(selectedNPC, selectedNPCQuestion);
                        $('#nextQuestion').hide();
                    } else {

                        $('#scorePaneMain').text(currentScore);

                        $('#scorePane').modal('show');
                        $('#questionsPane').modal('hide');
                    }

                    $('#questionsPaneOption1').css({'color': 'black'});
                    $('#questionsPaneOption2').css({'color': 'black'});
                    $('#questionsPaneOption3').css({'color': 'black'});

                });

            });
        </script>
    </body>
</html>
