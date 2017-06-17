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
<div class="main">

    <div class ="row">
        <h2 class="text-center" id = "main-text"> Rankings </h2>
    </div>

    <div class = "row">
        <div class ="col-sm-2"> </div>
        <div class ="col-sm-8">

            <section>


                <br> <br>

                <div class="tbl-header">
                    <table cellpadding="0" cellspacing="0" border="0">
                        <thead>
                        <tr>
                            <th></th>
                            <th></th>
                        </tr>
                        </thead>
                    </table>
                </div>
                <div class="tbl-content">
                    <table cellpadding="0" cellspacing="0" border="0">
                        <tbody id="usersListMain">
                        <%
                            for (int i = 0; i < ((List) request.getAttribute("rankings")).size(); i += 1) {


                                out.print(
                                        "<tr><td> " + (i + 1) +
                                                "</td><td> " + ((List) request.getAttribute("rankings")).get(i) +
                                                "</td>"
                                );
                            }
                        %>






                        </tbody>
                    </table>
                </div>
            </section>

        </div>
        <div class="col-sm-2"> </div>

    </div>

</div>



</body>

</html>