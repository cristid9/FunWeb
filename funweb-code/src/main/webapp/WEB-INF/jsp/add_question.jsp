<%--
  Created by IntelliJ IDEA.
  User: Bogdanel
  Date: 09.06.2017
  Time: 1:47
  To change this template use File | Settings | File Templates.
--%>
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
    <script src="JS.js"> </script>
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
                <li><a href="/admin_menu">Users<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-user"></span></a></li>
                <li><a href="statistics.html">Statistics<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-list-alt"></span></a></li>
                <li><a href="#">Chat<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-envelope"></span></a></li>
                <li class="active" class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Map Editor<span class="caret"></span><span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-cog"></span></a>
                    <ul class="dropdown-menu forAnimate" role="menu">
                        <li><a href="#">Add new Character</a></li>
                        <li class="divider"></li>
                        <li><a href="edit_character.html">Edit Character</a></li>

                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="main">
    <h2 class="text-center" id="main-text"> Add new Question </h2>

    <div class="col-sm-2"> </div>
    <div class="col-sm-8">
        <form>
            <div class="form-group">
                <label for="inputEnunciation">Enunciation</label>
                <input type="enunciation" class="form-control" id="inputEnunciation" >
            </div>
            <div class="form-group">
                <label for="inputChapter">Chapter</label>
                <input type="chapter" class="form-control" id="inputChapter">
            </div>
            <div class="form-group">
                <label for="inputOptionA"> Option A </label>
                <input type="optionA" class="form-control" id="inputOptionA">
            </div>
            <div class="form-group">
                <label for="inputOptionB"> Option B </label>
                <input type="optionB" class="form-control" id="inputOptionB">
            </div>
            <div class="form-group">
                <label for="inputOptionC"> Option C </label>
                <input type="optionC" class="form-control" id="inputOptionC">
            </div>

            <div class="form-group">
                <label for="selector"> Correct Answer </label>
                <select class="selectpicker" id="selector">
                    <option>Option A</option>
                    <option>Option B</option>
                    <option>Option C</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>

        </form>
    </div>
    <div class="col-sm-2"> </div>

</div>
</body>
</html>