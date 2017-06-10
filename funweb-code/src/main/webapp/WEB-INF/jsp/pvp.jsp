
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <tile>FunWeb</tile>
    <link rel="stylesheet" type="text/css" href="/resources/css/pvp.css">
    <link href='https://fonts.googleapis.com/css?family=Lobster|Muli' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/socket.io/2.0.2/socket.io.js"></script>
</head>

<body>

<div class="container">
    <div class = "col-sm-2">
        <div class="wrap">
            <p class = "name"> ${username} </p> <h1  id="playerScore" class="name">0</h1>
        </div>
    </div>



    <div id="mainDiv" class = "col-sm-8">
        <div class = "row">
            <h1 id="title">Fight to see who is the best!</h1>
            <%--<h1 id="finalMessage"></h1>--%>

            <ul class = "name">

                <p class="name" id="questionsPaneQuestion">
                    looking for oponent
                </p>
                <br>
                <br>

                <div  id="progressMain" class="progress">
                    <div id="userProgress" class="progress-bar" role="progressbar" aria-valuenow="0"
                         aria-valuemin="0" aria-valuemax="100">

                    </div>
                </div>

                <li id="questionsPaneOption1" class = "answer"> ... </li>
                <li id="questionsPaneOption2" class = "answer"> ... </li>
                <li id="questionsPaneOption3" class = "answer"> ... </li>
            </ul>
        </div>
        <div class="row">
            <img src = "/resources/images/Swords.png" class="img-responsive" alt="swords" width="250" height="250">
        </div>
    </div>
    <div class = "col-sm-2">
        <div class = "wrap">
            <p  id="oponentName" class="name"> Your opponent </p> :
            <h1 class="name" id="oponentScore"> 0 </h1>
        </div>
    </div>
</div>

<script type="text/javascript">
    var playerName = "${username}"; // for the moment
    var playerScore = 0;
    var oponentScore = 0;
    var currentQuestion = 0;

</script>

<script src="/resources/js/misc/questionLoader.js"></script>
<script src="/resources/js/generic/question.js" ></script>
<script src="/resources/js/game_tutorial/questionPool.js"></script>
<script type="text/javascript">
    var qPool = new QuestionPool();
    qPool.initPool([2]);

    $(function(){
        var socket = io.connect("http://localhost:3000");

        setTimeout(function () {
            socket.emit('newPVPPlayer', playerName, function (data) {
                console.log(data);
            });
        }, 1000);

        socket.on('foundOponents', function(data) {
            console.log(data);

            for (var i = 0; i < data.length; ++i) {
                if (data[i] != playerName) {
                    $("#oponentName").text(data[i]);
                    $("#oponentScore").text('0');

                    try {
                        loadNpcQuestions(1, currentQuestion);
                    } catch (err) {
                        console.log(err);
                    }
                }
            }

        });

        socket.on('newScores', function (data) {
            for (var i = 0; i < data.length; ++i) {
                if (data[i].name != playerName) {
                    $('#oponentScore').text(data[i].score);
                }
            }
        });

        $('#questionsPaneOption1').on('click', function() {

            if (isOptionCorrect(1, currentQuestion, 0)) {
                $('#questionsPaneOption1').css({'color': 'green'});
                $('#questionsPaneOption2').css({'color': 'red'});
                $('#questionsPaneOption3').css({'color': 'red'});
                playerScore  += 1;
                $('#playerScore').text(playerScore);

                $('#userProgress').animate({width: 100  + "%"}, 200);

                var newData = [
                    {name: playerName, score: playerScore},
                    {name: $('#oponentName').text(), score: $('#oponentScore').text()}
                ];

                console.log('am trimis din optiunea 3 ');

                socket.emit('PVPScoreUpdate', newData);

                currentQuestion += 1;
                if (hasNextQuestion(1, currentQuestion)) {
                    $('#questionsPaneOption1').css({'color': 'black'});
                    $('#questionsPaneOption2').css({'color': 'black'});
                    $('#questionsPaneOption3').css({'color': 'black'});

                    try {
                        loadNpcQuestions(1, currentQuestion);
                    } catch (err) {
                        console.log(err);
                    }

                } else {
                    // afisam scor
                    console.log('Nu exista intrebarea urmatoare');

                    setTimeout(function () {
                        $('#questionsPaneQuestion').hide();
                        $('#questionsPaneOption1').hide();
                        $('#questionsPaneOption2').hide();
                        $('#questionsPaneOption3').hide();
                        $('#progressMain').hide();


                        $('#title').text('Cu fiecare intrebare devi un developer mai bun' +
                            ' deocamdata ai obtinut ' + playerScore + 'punct');
                    }, 3000);
                }

            } else if (isOptionCorrect(1, currentQuestion, 1)) {
                $('#questionsPaneOption1').css({'color': 'red'});
                $('#questionsPaneOption2').css({'color': 'green'});
                $('#questionsPaneOption3').css({'color': 'red'});

                currentQuestion += 1;
                if (hasNextQuestion(1, currentQuestion)) {
                    $('#questionsPaneOption1').css({'color': 'black'});
                    $('#questionsPaneOption2').css({'color': 'black'});
                    $('#questionsPaneOption3').css({'color': 'black'});

                    try {
                        loadNpcQuestions(1, currentQuestion);
                    } catch (err) {
                        console.log(err);
                    }

                } else {
                    // afisam scor
                    console.log('Nu exista intrebarea urmatoare');

                    setTimeout(function () {
                        $('#questionsPaneQuestion').hide();
                        $('#questionsPaneOption1').hide();
                        $('#questionsPaneOption2').hide();
                        $('#questionsPaneOption3').hide();
                        $('#progressMain').hide();

                        $('#title').text('Cu fiecare intrebare devi un developer mai bun' +
                            ' deocamdata ai obtinut ' + playerScore + 'punct');
                    }, 3000);
                }

            } else if (isOptionCorrect(1, currentQuestion, 2)) {
                $('#questionsPaneOption1').css({'color': 'red'});
                $('#questionsPaneOption2').css({'color': 'red'});
                $('#questionsPaneOption3').css({'color': 'green'});

                currentQuestion += 1;
                if (hasNextQuestion(1, currentQuestion)) {
                    $('#questionsPaneOption1').css({'color': 'black'});
                    $('#questionsPaneOption2').css({'color': 'black'});
                    $('#questionsPaneOption3').css({'color': 'black'});

                    try {
                        loadNpcQuestions(1, currentQuestion);
                    } catch (err) {
                        console.log(err);
                    }

                } else {
                    // afisam scor
                    console.log('Nu exista intrebarea urmatoare');

                    setTimeout(function () {
                        $('#questionsPaneQuestion').hide();
                        $('#questionsPaneOption1').hide();
                        $('#questionsPaneOption2').hide();
                        $('#questionsPaneOption3').hide();
                        $('#progressMain').hide();

                        $('#title').text('Cu fiecare intrebare devi un developer mai bun' +
                            ' deocamdata ai obtinut ' + playerScore + 'punct');
                    }, 3000);
                }
            }
        });

        $('#questionsPaneOption2').on('click', function() {

            if (isOptionCorrect(1, currentQuestion, 0)) {
                $('#questionsPaneOption1').css({'color': 'green'});
                $('#questionsPaneOption2').css({'color': 'red'});
                $('#questionsPaneOption3').css({'color': 'red'});

                currentQuestion += 1;
                if (hasNextQuestion(1, currentQuestion)) {
                    $('#questionsPaneOption1').css({'color': 'black'});
                    $('#questionsPaneOption2').css({'color': 'black'});
                    $('#questionsPaneOption3').css({'color': 'black'});

                    try {
                        loadNpcQuestions(1, currentQuestion);
                    } catch (err) {
                        console.log(err);
                    }

                } else {
                    // afisam scor
                    console.log('Nu exista intrebarea urmatoare');

                    setTimeout(function () {
                        $('#questionsPaneQuestion').hide();
                        $('#questionsPaneOption1').hide();
                        $('#questionsPaneOption2').hide();
                        $('#questionsPaneOption3').hide();
                        $('#progressMain').hide();

                        $('#title').text('Cu fiecare intrebare devi un developer mai bun' +
                            ' deocamdata ai obtinut ' + playerScore + 'punct');
                    }, 3000);
                }

            } else if (isOptionCorrect(1, currentQuestion, 1)) {
                $('#questionsPaneOption1').css({'color': 'red'});
                $('#questionsPaneOption2').css({'color': 'green'});
                $('#questionsPaneOption3').css({'color': 'red'});
                playerScore  += 1;
                $('#playerScore').text(playerScore);


                $('#userProgress').animate({width: (100 ) + "%"}, 200);

                var newData = [
                    {name: playerName, score: playerScore},
                    {name: $('#oponentName').text(), score: $('#oponentScore').text()}
                ];


                console.log('am trimis din optiunea 3 ');

                socket.emit('PVPScoreUpdate', newData);


                currentQuestion += 1;
                if (hasNextQuestion(1, currentQuestion)) {
                    $('#questionsPaneOption1').css({'color': 'black'});
                    $('#questionsPaneOption2').css({'color': 'black'});
                    $('#questionsPaneOption3').css({'color': 'black'});

                    try {
                        loadNpcQuestions(1, currentQuestion);
                    } catch( err ) {
                        console.log(err);
                    }
                } else {
                    // afisam scor
                    console.log('Nu exista intrebarea urmatoare');

                    setTimeout(function () {
                        $('#questionsPaneQuestion').hide();
                        $('#questionsPaneOption1').hide();
                        $('#questionsPaneOption2').hide();
                        $('#questionsPaneOption3').hide();
                        $('#progressMain').hide();

                        $('#title').text('Cu fiecare intrebare devi un developer mai bun' +
                            ' deocamdata ai obtinut ' + playerScore + 'punct');
                    }, 3000);
                }

            } else if (isOptionCorrect(1, currentQuestion, 2)) {
                $('#questionsPaneOption1').css({'color': 'red'});
                $('#questionsPaneOption2').css({'color': 'red'});
                $('#questionsPaneOption3').css({'color': 'green'});

                currentQuestion += 1;
                if (hasNextQuestion(1, currentQuestion)) {
                    $('#questionsPaneOption1').css({'color': 'black'});
                    $('#questionsPaneOption2').css({'color': 'black'});
                    $('#questionsPaneOption3').css({'color': 'black'});

                    try {
                        loadNpcQuestions(1, currentQuestion);
                    } catch (err) {
                        console.log(err);
                    }

                } else {
                    // afisam scor
                    console.log('Nu exista intrebarea urmatoare');

                    setTimeout(function () {
                        $('#mainDiv').hide();
                        $('#questionsPaneQuestion').hide();
                        $('#questionsPaneOption1').hide();
                        $('#questionsPaneOption2').hide();
                        $('#questionsPaneOption3').hide();

                        $('#title').text('Cu fiecare intrebare devi un developer mai bun' +
                            ' deocamdata ai obtinut ' + playerScore + 'punct');
                    }, 3000);
                }
            }

        });

        $('#questionsPaneOption3').on('click', function() {

            if (isOptionCorrect(1, currentQuestion, 0)) {
                $('#questionsPaneOption1').css({'color': 'green'});
                $('#questionsPaneOption2').css({'color': 'red'});
                $('#questionsPaneOption3').css({'color': 'red'});

                currentQuestion += 1;
                if (hasNextQuestion(1, currentQuestion)) {
                    $('#questionsPaneOption1').css({'color': 'black'});
                    $('#questionsPaneOption2').css({'color': 'black'});
                    $('#questionsPaneOption3').css({'color': 'black'});

                    try {
                        loadNpcQuestions(1, currentQuestion);
                    } catch (err) {
                        console.log(err);
                    }

                } else {
                    // afisam scor
                    console.log('Nu exista intrebarea urmatoare');

                    setTimeout(function () {
                        $('#questionsPaneQuestion').hide();
                        $('#questionsPaneOption1').hide();
                        $('#questionsPaneOption2').hide();
                        $('#questionsPaneOption3').hide();
                        $('#progressMain').hide();

                        $('#title').text('Cu fiecare intrebare devi un developer mai bun' +
                            ' deocamdata ai obtinut ' + playerScore + 'punct');
                    }, 3000);
                }

            } else if (isOptionCorrect(1, currentQuestion, 1)) {
                $('#questionsPaneOption1').css({'color': 'red'});
                $('#questionsPaneOption2').css({'color': 'green'});
                $('#questionsPaneOption3').css({'color': 'red'});

                currentQuestion += 1;
                if (hasNextQuestion(1, currentQuestion)) {
                    $('#questionsPaneOption1').css({'color': 'black'});
                    $('#questionsPaneOption2').css({'color': 'black'});
                    $('#questionsPaneOption3').css({'color': 'black'});

                    try {
                        loadNpcQuestions(1, currentQuestion);
                    } catch (err) {
                        console.log(err);
                    }

                } else {
                    // afisam scor
                    console.log('Nu exista intrebarea urmatoare');

                    setTimeout(function () {
                        $('#mainDiv').hide();
                        $('#questionsPaneQuestion').hide();
                        $('#questionsPaneOption1').hide();
                        $('#questionsPaneOption2').hide();
                        $('#questionsPaneOption3').hide();

                        $('#title').text('Cu fiecare intrebare devi un developer mai bun' +
                            ' deocamdata ai obtinut ' + playerScore + 'punct');
                    }, 3000);
                }

            } else if (isOptionCorrect(1, currentQuestion, 2)){
                $('#questionsPaneOption1').css({'color': 'red'});
                $('#questionsPaneOption2').css({'color': 'red'});
                $('#questionsPaneOption3').css({'color': 'green'});
                playerScore  += 1;
                $('#playerScore').text(playerScore);


                $('#userProgress').animate({width: (100 ) + "%"}, 200);

                var newData = [
                    {name: playerName, score: playerScore},
                    {name: $('#oponentName').text(), score: $('#oponentScore').text()}
                ];

                console.log('am trimis din optiunea 3 ');
                socket.emit('PVPScoreUpdate', newData);

                currentQuestion += 1;
                if (hasNextQuestion(1, currentQuestion)) {
                    $('#questionsPaneOption1').css({'color': 'black'});
                    $('#questionsPaneOption2').css({'color': 'black'});
                    $('#questionsPaneOption3').css({'color': 'black'});

                    try {
                        loadNpcQuestions(1, currentQuestion);
                    } catch (err) {
                        console.log(err);
                    }
                } else {
                    // afisam scor
                    console.log('Nu exista intrebarea urmatoare');

                    setTimeout(function () {
                        $('#questionsPaneQuestion').hide();
                        $('#questionsPaneOption1').hide();
                        $('#questionsPaneOption2').hide();
                        $('#questionsPaneOption3').hide();
                        $('#progressMain').hide();

                        $('#title').text('Cu fiecare intrebare devi un developer mai bun' +
                            ' deocamdata ai obtinut ' + playerScore + 'punct');
                    }, 3000);
                }

            }
        });

    });


</script>


</body>

</html>