
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
    <link rel="stylesheet" href="/resources/css/admin_style.css">
    <link href="https://fonts.googleapis.com/css?family=Oleo+Script:400,700" rel="stylesheet">

</head>

<body>
<nav class="navbar navbar-inverse sidebar" role="navigation" id = "test">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-sidebar-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">FunWeb</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-sidebar-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="/adminPanel">Users<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-user"></span></a></li>
                <li><a href="statistics.html">Statistics<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-list-alt"></span></a></li>
                <li><a href="#">Chat<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-envelope"></span></a></li>
                <li class="active" class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Map Editor<span class="caret"></span><span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-cog"></span></a>
                    <ul class="dropdown-menu forAnimate" role="menu">
                        <li><a href="add_question.html">Add new Question</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Edit Question</a></li>

                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="main">
    <h2 class="text-center" id="main-text"> Edit Questions </h2>


    <table class="table">
        <thead>
        <tr>
            <th>Actions</th>
            <th>Enunciation</th>
        </tr>
        </thead>
        <tbody id="questionsList">
       <!-- <tr data-toggle="collapse" data-target=".order1">
            <td>
                <a href="#">
                    <span class="glyphicon glyphicon-asterisk"></span>
                </a>

            </td>
            <td> intreebarea 1</td>
        </tr>
        <tr class="collapse order1">
            <td>1</td>
            <td>Option 1</td>
        </tr>
        <tr class="collapse order1">
            <td>1</td>
            <td>Option 2</td>
        </tr>
        <tr class="collapse order1">
            <td>1</td>
            <td> Option3 </td>
        </tr>

        <tr data-toggle="collapse" data-target=".order2">
            <td>&gt;</td>
            <td>intrebarea 2</td>
        </tr>
        <tr class="collapse order2">
            <td>2</td>
            <td>Item</td>
        </tr>
        <tr class="collapse order2">
            <td>2</td>
            <td>Item</td>
        </tr>
        <tr class="collapse order2">
            <td>2</td>
            <td>Item</td>
            </td>
        </tr>

        -->
        </tbody>
    </table>

</div>

<script>
    $(document).ready(function(){

        var populateTable = function(questions){
            $("#questionsList").html("");
            for(var i = 0; i < questions.length; i++){
                console.log(questions[i]);

                var options;

                $.post("http://localhost:8083/getQuestionOptions", {id : questions[i].id}, function(data){
                       options = JSON.parse(data);

                    $("#questionsList").append(

                        '<tr class="collapse order1">' +
                        '<td>1</td>'+
                        '<td>' + options[0].enunciation + '</td>' +
                        '</tr>' +
                        '<tr class="collapse order1">' +
                        '<td>1</td>'+
                        '<td>' + options[1].enunciation +'</td>'+
                        '</tr>' +
                        '<tr class="collapse order1">'+
                        '<td>1</td>'+
                        '<td>' + options[2].enunciation + '</td>'+
                        '</tr>'
                    );
                });
                $("#questionsList").append(
                    ' <tr data-toggle="collapse" data-target=".order1">'+
                    '<td>'+
                    '<a href="#">' +
                    '<span class="glyphicon glyphicon-asterisk"></span>' +
                    '</a>' +

                    '</td>' +
                    '<td>' + questions[i].enunciation + '</td>' +
                    '</tr>'
                );

            }
        }

        function getData(){
            $.post("http://localhost:8083/getQuestionsList", function (data) {
                console.log(data);
                questionsList = JSON.parse(data);
                console.log(questionsList);
                populateTable(questionsList);
            });
        }
        getData();
    });
</script>

</body>
</html>
