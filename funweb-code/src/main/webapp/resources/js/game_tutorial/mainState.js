var mainState = {

    preload: function preload() {

        this.game.load.image('mainPlayer', '/resources/assets/mainPlayer.png');
        this.game.load.image('backendQuestioner', '/resources/assets/backendQuestioner.png');
        this.game.load.image('frontendQuestioner', '/resources/assets/frontendQuestioner.png');
        this.game.load.image('sqlQuestioner', '/resources/assets/sqlQuestioner.png');
        this.game.load.spritesheet('trotinel', '/resources/assets/playerSprite.png', 80, 110, 24);
    },

    create: function create() {
        /*this.player = this.game.add.sprite(this.game.world.centerX,
                                           this.game.world.centerY,
                                           'mainPlayer');
        */

        this.player = this.game.add.sprite(100, 150, 'trotinel');
        this.player.animations.add('walk', [9, 10], 12, false);
        this.player.animations.add('idle', [0, 23] , 5, true);
        this.player.animations.add('up',   [22], 12, true);


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

        this.game.stage.backgroundColor = "#4488AA";

        this.physics.arcade.enable([
            this.player,
            this.questioner1,
            this.questioner2,
            this.sqlQuestioner,
        ]);
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
        this.game.physics.arcade.overlap(this.player, this.sqlQuestioner, collisionHandler3, null, null);

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
    }
};
