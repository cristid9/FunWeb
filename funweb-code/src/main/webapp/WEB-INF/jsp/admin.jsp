<%@ page import="daos.UserDAO" %>
<%@ page import="factories.BidirectionalUserFactory" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mashape.unirest.http.exceptions.UnirestException" %>
<%@ page import="java.util.ArrayList" %>

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
                <li class="active"><a href="#">Users<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-user"></span></a></li>
                <li ><a href="statistics.html">Statistics<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-list-alt"></span></a></li>
                <li ><a href="/quick_chat">Chat<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-envelope"></span></a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Map Editor<span class="caret"></span><span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-cog"></span></a>
                    <ul class="dropdown-menu forAnimate" role="menu">
                        <li><a href="/add_question">Add new Question</a></li>
                        <li class="divider"></li>
                        <li><a href="edit_character.html">Edit Character</a></li>

                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="main">

    <div class ="row">
        <h2 class="text-center" id = "main-text"> USERS </h2>
    </div>

    <div class = "row">
        <div class ="col-sm-2"> </div>
        <div class ="col-sm-8">

            <section>

                <div id="custom-search-input">
                    <div class="input-group col-md-12">
                        <input type="text" class="form-control input-lg" placeholder="Search for users" />
                        <span class="input-group-btn">
						<button class="btn btn-info btn-lg" type="button">
							<i class="glyphicon glyphicon-search"></i>
						</button>
					</span>
                    </div>
                </div>
                <br> <br>

                <div class="tbl-header">
                    <table cellpadding="0" cellspacing="0" border="0">
                        <thead>
                        <tr>
                            <th>Username</th>
                            <th>Ban</th>
                        </tr>
                        </thead>
                    </table>
                </div>
                <div class="tbl-content">
                    <table cellpadding="0" cellspacing="0" border="0">
                        <tbody id="usersListMain">

                        </tbody>
                    </table>
                </div>
            </section>

        </div>
        <div class="col-sm-2"> </div>

    </div>

</div>


<script>
    $(document).ready(function() {
        var populateTable = function (users) {
            $("#usersListMain").html("");
            console.log("am intrat in functie");
            for (var i = 0; i < users.length; i++) {
                $("#usersListMain").append(
                    '<tr>' +
                    '<td>' + users[i].username + '</td>' +
                    '<td>' + '<button type="button" class="banIt" aria-label="Ban" id="' + users[i].username + '">' +
                    ' <span aria-hidden="true">&times;</span>' +
                    '</button>'
                );

            }

        }

        function getData() {
            $.post("/getUsersList", {}, function (data) {
                usersList = JSON.parse(data);

                populateTable(usersList);
            });
        }

        getData();
        $(document).on('click', ".banIt", function (e) {
            var targetedUsername = e.target.id;
            $.post("/banUser", {username: targetedUsername}, function (data) {
                getData();
            })
        });
    });
</script>

</body>

</html>