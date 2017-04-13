<%--
  Created by IntelliJ IDEA.
  User: Marius
  Date: 4/12/2017
  Time: 9:24 PM
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
</head>
<body>

<div class="container-fluid">

    <p> go <a href="#"> back </a> <p>
    <h1 class="text-center">Admin menu</h1>

    <div class="row">

        <div class="col-sm-6">
            <p class="text-center"> Lista paginata cu useri </p>
            <table id="users" class="table table-striped table-bordered" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th> Name </th>
                    <th> Remove </th>
                </tr>
                </thead>

                <tbody id="usersListMain">

                </tbody>
            </table>
            <div class="text-center">
                <ul class="pagination pagination-lg pager" id="myPager">
                    <li id="prevPage" class="btn btn-success"> prev </li>
                    <li id="nextPage" class="btn btn-success"> next </li>
                </ul>
            </div>

        </div>

        <div class="col-sm-6">
            <p class="text-center"> Intrebari triviale </p>

            <p class="text-center"> Introdu id-ul unei intrebari pentru a testa daca e triviala </p>

            <form>
                <div class="form-group">
                    <label for="id">Id-ul intrebarii:</label>
                    <input type="id" class="form-control" id="id" placeholder="Introdu id-ul:">
                </div>
                <button type="check" class="btn btn-success">Check</button>

            </form>

        </div>

    </div>

</div>
    <script type="text/javascript">

        $(document).ready(function() {

            var currentPage = 0;
            var usersList = null;
            var itemsPerPage = 15;
            var current = 0;

            var populateTable = function(users, page, itemsPerPage) {
                $("#usersListMain").html("");
                for (var i = page * itemsPerPage; i < page * itemsPerPage + itemsPerPage && i < users.length; ++i) {
                    $("#usersListMain").append(
                        '<tr>' +
                        '<td>' + users[i].username + '</td>' +
                            '<td> <button type="button" id="' + users[i].username + '" class="btn btn-danger banIt"> Ban </button> </td>' +
                        '</tr>'
                    );
                }
            }

            function getData() {
                $.post("/getUsersList", {}, function (data) {
                    usersList = JSON.parse(data);

                    populateTable(usersList, current, itemsPerPage);
                });
            }
            getData();

            $("#nextPage").on('click', function(e) {
                console.log(current);
                console.log(usersList.length / itemsPerPage);

                // refactor o be easier to understand
                if ((current + 1) < usersList.length / itemsPerPage) {

                    current++;
                    populateTable(usersList, current, itemsPerPage)
                }
            });

            $("#prevPage").on('click', function(e) {

                if (current > 0) {
                    current--;
                    populateTable(usersList, current, itemsPerPage)
                }
            });

            $(document).on('click', ".banIt", function(e) {
               var targetedUsername = e.target.id;

               $.post("/banUser", {username: targetedUsername}, function (data) {
                    alert("The user " + targetedUsername + " was banned!");
                    getData();
               });
            });

        });
    </script>
</body>
</html>
