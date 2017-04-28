<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
        <script type="text/javascript"
                src="https://cdnjs.cloudflare.com/ajax/libs/phaser/2.6.0/phaser.js">
        </script>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://fonts.googleapis.com/css?family=Oleo+Script:400,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Teko:400,700" rel="stylesheet">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/lettering.js/0.7.0/jquery.lettering.min.js"> </script>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css" rel="stylesheet">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/textillate/0.4.0/jquery.textillate.js"> </script>


        <style type="text/css">
            <!-- dirty, but i'll be changed soon -->




            body{
                background: #8999A8;
            }
            .navbar, .dropdown-menu{
                background:rgba(255,255,255,0.25);
                border: none;

            }

            .nav>li>a, .dropdown-menu>li>a:focus, .dropdown-menu>li>a:hover, .dropdown-menu>li>a, .dropdown-menu>li{
                border-bottom: 3px solid transparent;
            }
            .nav>li>a:focus, .nav>li>a:hover,.nav .open>a, .nav .open>a:focus, .nav .open>a:hover, .dropdown-menu>li>a:focus, .dropdown-menu>li>a:hover{
                border-bottom: 3px solid transparent;
                background: none;
            }
            .navbar a, .dropdown-menu>li>a, .dropdown-menu>li>a:focus, .dropdown-menu>li>a:hover, .navbar-toggle{
                color: #fff;
            }
            .dropdown-menu{
                -webkit-box-shadow: none;
                box-shadow:none;
            }

            .nav li:hover:nth-child(8n+1), .nav li.active:nth-child(8n+1){
                border-bottom: #C4E17F 3px solid;
            }
            .nav li:hover:nth-child(8n+2), .nav li.active:nth-child(8n+2){
                border-bottom: #F7FDCA 3px solid;
            }
            .nav li:hover:nth-child(8n+3), .nav li.active:nth-child(8n+3){
                border-bottom: #FECF71 3px solid;
            }
            .nav li:hover:nth-child(8n+4), .nav li.active:nth-child(8n+4){
                border-bottom: #F0776C 3px solid;
            }
            .nav li:hover:nth-child(8n+5), .nav li.active:nth-child(8n+5){
                border-bottom: #DB9DBE 3px solid;
            }
            .nav li:hover:nth-child(8n+6), .nav li.active:nth-child(8n+6){
                border-bottom: #C49CDE 3px solid;
            }
            .nav li:hover:nth-child(8n+7), .nav li.active:nth-child(8n+7){
                border-bottom: #669AE1 3px solid;
            }
            .nav li:hover:nth-child(8n+8), .nav li.active:nth-child(8n+8){
                border-bottom: #62C2E4 3px solid;
            }

            .navbar-toggle .icon-bar{
                color: #fff;
                background: #fff;
            }

        </style>
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
                                <li class="active"><a href="#" class="">Home</a></li>

                                <li><a href="#">Help</a></li>
                                <li><a href="#">Chat</a></li>
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



        <script type="text/javascript">
            // no time for clean code

            (function () {
                var game = new Phaser.Game(document.body.offsetWidth, document.body.offsetHeight, Phaser.AUTO, 'funweb-game');


//                WebFontConfig = {
//
//                    active: function() {
//                        game.time.events.add(Phaser.Timer.SECOND, createText, this);
//                    },
//
//                    google: {
//                        families: ['Revalia']
//                    }
//                };

                var gameState = {
                    // how lame is this? I've uploaded the images on imgur =)))))))
                    preload: function preload() {
//                        this.game.load.script('webfont', '//ajax.googleapis.com/ajax/libs/webfont/1.4.7/webfont.js');

                        this.game.load.baseURL = 'http://i.imgur.com/';
                        this.game.load.crossOrigin = 'anonymous';

                        this.game.load.image('IfosDEc.png', sessionStorage.getItem('IfosDEc.png'));
                        this.game.load.image('JPIRRqM.png', sessionStorage.getItem('JPIRRqM.png'));
                        this.game.load.image('4sS7xA4.png', sessionStorage.getItem('4sS7xA4.png'));
                    },

                    create: function create() {
                        this.player = this.game.add.sprite(this.game.world.centerX,
                            this.game.world.centerY,
                            'IfosDEc.png');

                        this.questioner1 = this.game.add.sprite(10, 200, 'JPIRRqM.png');
                        this.questioner1BitmapText = this.game.add.text(0, 100, 'Eu trebuia sa iti pun intrebari despre backend, \ndar nu merge severul.');
                        this.questioner1BitmapText.visible = false;

                        this.questioner2 = this.game.add.sprite(400, 300, '4sS7xA4.png');
                        this.questioner2BitmapText = this.game.add.text(300, 250, 'Nici la mine nu merge serverul');
                        this.questioner2BitmapText.visible = false;


                        this.game.stage.backgroundColor = "#4488AA";

                        this.physics.arcade.enable([this.player, this.questioner1, this.questioner2]);
                    },

                    update: function update() {
                        if (this.game.input.keyboard.isDown(Phaser.Keyboard.UP)) {
                            this.player.y -= 2;
                        }

                        if (this.game.input.keyboard.isDown(Phaser.Keyboard.DOWN)) {
                            this.player.y += 2;
                        }

                        if (this.game.input.keyboard.isDown(Phaser.Keyboard.LEFT)) {
                            this.player.x -= 2;
                        }

                        if (this.game.input.keyboard.isDown(Phaser.Keyboard.RIGHT)) {
                            this.player.x += 2;
                        }

                        this.game.physics.arcade.overlap(this.player, this.questioner1, collisionHandler, null, this);
                        this.game.physics.arcade.overlap(this.player, this.questioner2, collisionHandler2, null, this);

                        function collisionHandler(player, questionare) {
                            this.questioner1BitmapText.visible = true;

                            var self = this;
                            setTimeout(function () {
                                self.questioner1BitmapText.visible = false
                            }, 1000);
                        }

                        function collisionHandler2(player, questionare2) {
                            this.questioner2BitmapText.visible = true;

                            var self = this;
                            setTimeout(function () {
                                self.questioner2BitmapText.visible = false
                            }, 1000);

                        }
                    }
                };

                game.state.add('gameState', gameState);
                game.state.start('gameState');

            })();

        </script>



    </body>
</html>
