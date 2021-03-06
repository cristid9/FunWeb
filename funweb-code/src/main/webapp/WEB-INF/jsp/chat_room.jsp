<%--
  Created by IntelliJ IDEA.
  User: Marius
  Date: 6/1/2017
  Time: 6:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="A front-end template that helps you build fast, modern mobile web apps.">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
    <title>Chat</title>

    <meta name="mobile-web-app-capable" content="yes">
    <link rel="icon" sizes="192x192" href="images/android-desktop.png">

    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-title" content="Material Design Lite">
    <link rel="apple-touch-icon-precomposed" href="images/ios-desktop.png">

    <meta name="msapplication-TileImage" content="images/touch/ms-touch-icon-144x144-precomposed.png">
    <meta name="msapplication-TileColor" content="#3372DF">

    <link rel="shortcut icon" href="images/favicon.png">

    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="/resources/css/chat_charset.css">
    <link rel="stylesheet" href="/resources/css/chat_style.css">
    <script src="/resources/js/chatBar.js"></script>
    <script src="/resources/js/chatMaterial.js"></script>
</head>
<body>
<div class="chat-layout mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header">
    <header class="chat-header mdl-layout__header mdl-color-text--white">
        <div class="mdl-layout__header-row">
            <span class="mdl-layout-title">#general <span class="users-count"><i class="material-icons">person</i>3</span></span>
            <div class="mdl-layout-spacer"></div>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--expandable is-upgraded" data-upgraded=",MaterialTextfield">
                <div class="mdl-textfield__expandable-holder">
                    <input class="mdl-textfield__input" type="text" id="search">
                    <label class="mdl-textfield__label" for="search">Enter your query...</label>
                </div>
            </div>
            <button class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon" id="hdrbtn" data-upgraded=",MaterialButton,MaterialRipple">
                <i class="material-icons">more_vert</i>
            </button>
            <ul class="mdl-menu mdl-js-menu mdl-js-ripple-effect mdl-menu--bottom-right" for="hdrbtn">
                <li class="mdl-menu__item">About</li>
                <li class="mdl-menu__item">Contact</li>
                <li class="mdl-menu__item">Legal information</li>
            </ul>
        </div>
        <p class="header-subtitle">Share your knowledge about Web Tehnologies<p>
        <div class="chat-notifications mdl-color--white">
            <button class="show-options mdl-button">
                <i class="material-icons" role="presentation">keyboard_arrow_down</i>
            </button>
        </div>
    </header>
    <div class="chat-drawer mdl-layout__drawer mdl-color--blue-grey-900 mdl-color-text--blue-grey-50">
        <div class="mdl-grid chat-sidebar-header">
            <img src="/resources/images/mariusolaru.jpg" class="chat-avatar">
            <div class="mdl-cell mdl-cell--6-col"><p class="no-margin">mariusolaru</p>
                <div class="chat-avatar-dropdown">
                    <span class="online-status"><i class="material-icons online-icon">fiber_manual_record</i> online</span>
                </div>
            </div>
        </div>
        <button class="add-channel-buttom mdl-button mdl-js-button mdl-js-ripple-effect mdl-js-button mdl-button--fab mdl-button--mini-fab">
            <i class="material-icons">add</i>
        </button>

        <nav class="chat-navigation mdl-navigation">
            <div class="menu-subtitle">
                CHANNELS
                <a class="mdl-navigation__link chat-navigation-link" href="">#general</a>

            </div>
            <div class="menu-subtitle">
                ONLINE USERS
                <a class="mdl-navigation__link chat-navigation-link" href=""><i class="material-icons online-icon">fiber_manual_record</i>bogdanboca</a>
                <a class="mdl-navigation__link chat-navigation-link" href=""><i class="material-icons online-icon">fiber_manual_record</i>cristidanila</a>
            </div>

        </nav>
    </div>
    <main class="mdl-layout__content mdl-color--grey-100" >
        <div class="mdl-grid chat-content" id="para">


            </div>

        <div class="chat-write-box mdl-color--white mdl-shadow--2dp mdl-cell--12-col mdl-grid">
            <div class="chat-icon mdl-cell--1-col-phone mdl-cell--1-col-tablet mdl-cell--1-col">
                <i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">camera_alt</i>
            </div>
            <div class="mdl-cell--2-col-phone mdl-cell--6-col-tablet mdl-cell--10-col">
                <input type="text" name="message" id="words" onKeyPress="return checkSubmit(event)">

            </div>
            <!--div class="chat-icon right mdl-cell--1-col-phone mdl-cell--1-col-tablet mdl-cell--1-col"-->
            <!--i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">tag_faces</i-->
            <form>
                <img src="/resources/images/flat-send.png" width="36" height="34" style="padding:6px;" onclick="getwords()" >

            </form>
            <!--/div-->
        </div>
</div>
</main>
</div>
<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" style="position: fixed; left: -1000px; height: -1000px;">
    <defs>
        <mask id="piemask" maskContentUnits="objectBoundingBox">
            <circle cx=0.5 cy=0.5 r=0.49 fill="white" />
            <circle cx=0.5 cy=0.5 r=0.40 fill="black" />
        </mask>
        <g id="piechart">
            <circle cx=0.5 cy=0.5 r=0.5 />
            <path d="M 0.5 0.5 0.5 0 A 0.5 0.5 0 0 1 0.95 0.28 z" stroke="none" fill="rgba(255, 255, 255, 0.75)" />
        </g>
    </defs>
</svg>
<svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 500 250" style="position: fixed; left: -1000px; height: -1000px;">
    <defs>
        <g id="chart">
            <g id="Gridlines">
                <line fill="#888888" stroke="#888888" stroke-miterlimit="10" x1="0" y1="27.3" x2="468.3" y2="27.3" />
                <line fill="#888888" stroke="#888888" stroke-miterlimit="10" x1="0" y1="66.7" x2="468.3" y2="66.7" />
                <line fill="#888888" stroke="#888888" stroke-miterlimit="10" x1="0" y1="105.3" x2="468.3" y2="105.3" />
                <line fill="#888888" stroke="#888888" stroke-miterlimit="10" x1="0" y1="144.7" x2="468.3" y2="144.7" />
                <line fill="#888888" stroke="#888888" stroke-miterlimit="10" x1="0" y1="184.3" x2="468.3" y2="184.3" />
            </g>
            <g id="Numbers">
                <text transform="matrix(1 0 0 1 485 29.3333)" fill="#888888" font-family="'Roboto'" font-size="9">500</text>
                <text transform="matrix(1 0 0 1 485 69)" fill="#888888" font-family="'Roboto'" font-size="9">400</text>
                <text transform="matrix(1 0 0 1 485 109.3333)" fill="#888888" font-family="'Roboto'" font-size="9">300</text>
                <text transform="matrix(1 0 0 1 485 149)" fill="#888888" font-family="'Roboto'" font-size="9">200</text>
                <text transform="matrix(1 0 0 1 485 188.3333)" fill="#888888" font-family="'Roboto'" font-size="9">100</text>
                <text transform="matrix(1 0 0 1 0 249.0003)" fill="#888888" font-family="'Roboto'" font-size="9">1</text>
                <text transform="matrix(1 0 0 1 78 249.0003)" fill="#888888" font-family="'Roboto'" font-size="9">2</text>
                <text transform="matrix(1 0 0 1 154.6667 249.0003)" fill="#888888" font-family="'Roboto'" font-size="9">3</text>
                <text transform="matrix(1 0 0 1 232.1667 249.0003)" fill="#888888" font-family="'Roboto'" font-size="9">4</text>
                <text transform="matrix(1 0 0 1 309 249.0003)" fill="#888888" font-family="'Roboto'" font-size="9">5</text>
                <text transform="matrix(1 0 0 1 386.6667 249.0003)" fill="#888888" font-family="'Roboto'" font-size="9">6</text>
                <text transform="matrix(1 0 0 1 464.3333 249.0003)" fill="#888888" font-family="'Roboto'" font-size="9">7</text>
            </g>
            <g id="Layer_5">
                <polygon opacity="0.36" stroke-miterlimit="10" points="0,223.3 48,138.5 154.7,169 211,88.5
                  294.5,80.5 380,165.2 437,75.5 469.5,223.3   "/>
            </g>
            <g id="Layer_4">
                <polygon stroke-miterlimit="10" points="469.3,222.7 1,222.7 48.7,166.7 155.7,188.3 212,132.7
                  296.7,128 380.7,184.3 436.7,125   "/>
            </g>
        </g>
    </defs>
</svg>

<script type="text/javascript">
    function getwords() {
        text = words.value;

        var currentdate = new Date();
        var datetime =
            currentdate.getHours() + ":"
            + currentdate.getMinutes();


        document.getElementById("para").innerHTML += '<div class="mdl-cell mdl-cell--12-col mdl-grid"> <div class="chat-user mdl-cell mdl-cell--1-col-phone mdl-cell--1-col-tablet mdl-cell--1-col"> <img src="http://famelocker.com.au/front_media/images/user.jpg"> </div>  <div class="chat-message mdl-cell mdl-color--white mdl-shadow--2dp mdl-cell--11-col mdl-cell--7-col-tablet mdl-cell--3-col-phone"> <h4>cristidanila<span>' + datetime + '</span></h4> <p>'+ text + '</p> </div>'
        document.getElementById("words").value = " "
        document.getElementById( 'para' ).scrollIntoView();
    }

    function checkSubmit(e) {
        if(e && e.keyCode == 13) {
            getwords();
        }
    }



</script>

</body>
</html>
