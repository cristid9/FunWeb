var mainState = {

    preload: function preload() {

        this.game.load.image('mainPlayer', '/resources/assets/mainPlayer.png');
        this.game.load.image('backendQuestioner', '/resources/assets/backendQuestioner.png');
        this.game.load.image('frontendQuestioner', '/resources/assets/frontendQuestioner.png');
        this.game.load.image('sqlQuestioner', '/resources/assets/sqlQuestioner.png');
        this.game.load.spritesheet('trotinel', '/resources/assets/playerSprite.png', 80, 110, 24);
        this.game.load.image('noSqlQuestioner', '/resources/assets/noSqlQuestioner.png');
        this.game.load.image('acceptButton', '/resources/assets/accept.png');
        this.game.load.image('dismissButton', '/resources/assets/dismiss.png');
        this.game.load.image('questionerDialogPanel', '/resources/assets/questionerDialogPanel.png');
        this.game.load.image('path', '/resources/assets/path.png');
        this.game.load.image('border_up', '/resources/assets/path_border_up.png');
        this.game.load.image('copac1', '/resources/assets/copac1.png');
        this.game.load.image('copac2', '/resources/assets/copac2.png');
        this.game.load.image('copac3', '/resources/assets/copac3.png');
        this.game.load.image('piatra', '/resources/assets/piatra.png');
        this.game.load.image('floare', '/resources/assets/floare.png');


    },

    create: function create() {
        /*this.player = this.game.add.sprite(this.game.world.centerX,
                                           this.game.world.centerY,
                                           'mainPlayer');
        */

        //build basic map
        this.game.add.sprite(72, 160, 'border_up');
        this.game.add.sprite(72, 220, 'path');

        this.game.add.sprite(136, 160, 'border_up');
        this.game.add.sprite(136, 220, 'path');

        this.game.add.sprite(200, 160, 'border_up');
        this.game.add.sprite(200, 220, 'path');

        this.game.add.sprite(264, 160, 'border_up');
        this.game.add.sprite(264, 220, 'path');

        this.game.add.sprite(328, 160, 'border_up');
        this.game.add.sprite(328, 220, 'path');

        this.game.add.sprite(392, 160, 'border_up');
        this.game.add.sprite(392, 220, 'path');

        this.game.add.sprite(456, 160, 'border_up');
        this.game.add.sprite(456, 220, 'path');

        this.game.add.sprite(500, 160, 'border_up');
        this.game.add.sprite(500, 220, 'path');

        this.game.add.sprite(500, 274, 'path');
        this.game.add.sprite(500, 328, 'path');
        this.game.add.sprite(564, 328, 'path');
        this.game.add.sprite(612, 328, 'path');
        this.game.add.sprite(612, 382, 'path');
        this.game.add.sprite(612, 436, 'path');
        this.game.add.sprite(676, 328, 'path');
        this.game.add.sprite(740, 328, 'path');

        this.game.add.sprite(200, 360, 'copac1');
        this.game.add.sprite(1000, 203 , 'copac1');
        this.game.add.sprite(1200, 383, 'copac3');
        this.game.add.sprite(685, 106, 'copac2');
        this.game.add.sprite(345, 321, 'floare');
        this.game.add.sprite(600, 672, 'floare');
        this.game.add.sprite(236, 257, 'piatra');
        this.game.add.sprite(873, 444, 'piatra');




        console.log(game.width);



        this.player = this.game.add.sprite(100, 150, 'trotinel');
        this.player.animations.add('walk', [9, 10], 12, false);
        this.player.animations.add('idle', [0, 23] , 5, true);
        this.player.animations.add('up',   [22], 12, true);


        this.acceptButton = this.game.add.button(10, 10, 'acceptButton', acceptHandler, this, 0, 0, 0);
        this.acceptButton = this.game.add.button(20, 10, 'dismissHandler', dismissHandler, this, 0,0, 0);
        this.acceptButton.scale.setTo(0.3, 0.3);

        this.questionerDialogPanel = this.game.add.sprite(0, 0, 'questionerDialogPanel');


        // backend questioner setup
        this.questioner1 = this.game.add.sprite(10, 200, 'backendQuestioner');
        this.questioner1BitmapText = this.game.add.text(0, 100, 'Eu trebuia sa iti pun intrebari despre backend, \ndar nu merge severul.');
        this.questioner1BitmapText.visible = false;

        // frontend questioner setup
        this.questioner2 = this.game.add.sprite(400, 300, 'frontendQuestioner');
        this.questioner2BitmapText = this.game.add.text(300, 250, 'Nici la mine nu merge serverul');
        this.questioner2BitmapText.visible = false;

        // sql questioner setup
        this.sqlQuestioner = this.game.add.sprite(600, 450, 'sqlQuestioner');
        this.sqlQuestionerText = this.game.add.text(570, 420, 'Eu trebuie sa pun intrebari din SQL');
        this.sqlQuestionerText.visible = false;

        // no sql questioner setup
        this.noSqlQuestioner = this.game.add.sprite(800, 300, 'noSqlQuestioner');
        this.noSqlQuestionerText = this.game.add.text(790, 280, 'Eu iti pun intrebari din no sql');
        this.noSqlQuestionerText.visible = false;

        this.game.stage.backgroundColor = "#66CD00";

        this.physics.arcade.enable([
            this.player,
            this.questioner1,
            this.questioner2,
            this.sqlQuestioner,
            this.noSqlQuestioner,
        ]);


        function acceptHandler() {
            // stub
        }

        function dismissHandler() {
            // stub
        }
    },

    update: function update() {
        var downFlag = false;
        if (this.game.input.keyboard.isDown(Phaser.Keyboard.UP)) {
            this.player.y -= 2;
            downFlag = true;
            this.player.animations.play("up");
        }
        else if (this.game.input.keyboard.isDown(Phaser.Keyboard.DOWN)) {
            this.player.y += 2;
            downFlag = true;
        }
        else if (this.game.input.keyboard.isDown(Phaser.Keyboard.LEFT)) {
            this.player.x -= 2;
            downFlag = true;

            this.player.animations.play("walk");
        }
        else if (this.game.input.keyboard.isDown(Phaser.Keyboard.RIGHT)) {
            downFlag = true;
            this.player.x += 2;
            this.player.animations.play("walk");
        }
        else{
            downFlag = false;
        }

        if(downFlag === false){
            this.player.animations.play('idle');
        }

        this.game.physics.arcade.overlap(this.player, this.questioner1, collisionHandler, null, this);
        this.game.physics.arcade.overlap(this.player, this.questioner2, collisionHandler2, null, this);
        this.game.physics.arcade.overlap(this.player, this.sqlQuestioner, collisionHandler3, null, this);
        this.game.physics.arcade.overlap(this.player, this.noSqlQuestioner, collisionHandler4, null, this);

        // needs a refactor, to much boilerplate code

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

        function collisionHandler3(player, sqlQuestioner) {
            this.sqlQuestionerText.visible = true;

            var self = this;
            setTimeout(function () {
                self.sqlQuestionerText.visible = false;
            }, 1000);
        }

        function collisionHandler4(player, noSqlQuestioner) {
            this.noSqlQuestionerText.visible = true;

            var self = this;
            setTimeout(function() {
                self.noSqlQuestionerText.visible = false;
            }, 1000);
        }
    }
};
