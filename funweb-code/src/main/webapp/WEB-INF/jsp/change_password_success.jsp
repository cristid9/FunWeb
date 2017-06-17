
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
            <h1 id="title">The password has been changed successfuly</h1>
            <%--<h1 id="finalMessage"></h1>--%>


        </div>
       
    </div>
    <div class = "col-sm-2">
        <div class = "wrap">
            <p  id="oponentName" class="name"> Your opponent </p> :
            <h1 class="name" id="oponentScore"> 0 </h1>
        </div>
    </div>
</div>




</body>

</html>