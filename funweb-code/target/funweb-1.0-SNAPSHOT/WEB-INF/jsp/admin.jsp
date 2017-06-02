<%@ page import="daos.UserDAO" %>
<%@ page import="user.User" %>

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
                        <li><a href="#" class="">Home</a></li>

                        <li><a href="#">Help</a></li>
                        <li><a href="/chat_room">Chat</a></li>
                        <li><a href="#">Notifications</a></li>

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

<div class="container-fluid" id="menu">

    <h1 class="text-center" id = "titlu" > Admin options </h1>
    <br> <br> <br>

    <div class="col-sm-3"> </div>
    <div class="col-sm-6">
        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">

            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingOne">
                    <h4 class="panel-title">
                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                            <i class="more-less glyphicon glyphicon-plus"></i>
                            Ban an user
                        </a>
                    </h4>
                </div>

                <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                    <div class="panel-body">

                        <div class="form-group">
                            <div class="fieldwrapper">
                                <input type="text" class="form-control" id="username" name="username" placeholder=" Enter UserName">
                            </div>
                        </div>

                    </div>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingTwo">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                            <i class="more-less glyphicon glyphicon-plus"></i>
                            Another admin option
                        </a>
                    </h4>
                </div>
                <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                    <div class="panel-body">
                        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                    </div>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingThree">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                            <i class="more-less glyphicon glyphicon-plus"></i>
                            See statistics
                        </a>
                    </h4>
                </div>
                <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                    <div class="panel-body">
                        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                    </div>
                </div>
            </div>

        </div>

    </div>
</div>

<script>
    function toggleIcon(e) {
        $(e.target)
            .prev('.panel-heading')
            .find(".more-less")
            .toggleClass('glyphicon-plus glyphicon-minus');
    }
    $('.panel-group').on('hidden.bs.collapse', toggleIcon);
    $('.panel-group').on('shown.bs.collapse', toggleIcon);

</script>
</body>
</html>