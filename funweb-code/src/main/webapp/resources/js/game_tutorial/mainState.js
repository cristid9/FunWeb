var mainState = {

    preload: function preload() {

       // this.game.load.image('mainPlayer', '/resources/assets/mainPlayer.png');
       //  this.game.load.image('backendQuestioner', '/resources/assets/backendQuestioner.png');
       //  this.game.load.image('frontendQuestioner', '/resources/assets/frontendQuestioner.png');
       //  this.game.load.image('sqlQuestioner', '/resources/assets/sqlQuestioner.png');
       //  this.game.load.spritesheet('trotinel', '/resources/assets/playerSprite.png', 80, 110, 24);
       //  this.game.load.image('noSqlQuestioner', '/resources/assets/noSqlQuestioner.png');
       //  this.game.load.image('acceptButton', '/resources/assets/accept.png');
       //  this.game.load.image('dismissButton', '/resources/assets/dismiss.png');
       //  this.game.load.image('questionerDialogPanel', '/resources/assets/questionerDialogPanel.png');
       //  this.game.load.tilemap('map', '/resources/assets/map.json', null, Phaser.Tilemap.TILED_JSON);
       //  this.game.load.image('tiles', '/resources/assets/terrain.png');
       //  this.game.load.image('tiles1', '/resources/assets/trees.png');

    },

    create: function create() {

        var map = this.game.add.tilemap('map');
        map.addTilesetImage('terrain', 'tiles');
        map.addTilesetImage('foliagePack_default', 'tiles1');

        var layer = map.createLayer(0);
        var layer2 = map.createLayer(1);
        layer.resizeWorld();

        this.cursors = this.game.input.keyboard.createCursorKeys();

        //build basic map
        console.log(game.width);


        this.game.camera.x = 700;
        this.game.camera.y = 550;
        this.player = this.game.add.sprite(730, 790, 'trotinel');
        this.player.animations.add('walk', [9, 10], 12, false);
        this.player.animations.add('idle', [0, 23] , 5, true);
        this.player.animations.add('up',   [22], 12, true);

        // Npc dialog setup
        this.currentNpc = '';
        this.acceptButton = this.game.add.button(10, 10, 'acceptButton', acceptHandler, this, 0, 0, 0);
        this.dismissButton = this.game.add.button(20, 10, 'dismissButton', dismissHandler, this, 0,0, 0);
        this.acceptButton.scale.setTo(0.3, 0.3);
        this.dismissButton.scale.setTo(0.3, 0.3);
        this.questionerDialogPanel = this.game.add.sprite(0, 0, 'questionerDialogPanel');

        this.acceptButton.visible = false;
        this.dismissButton.visible = false;
        this.questionerDialogPanel.visible = false;

        // backend questioner setup
        this.questioner1 = this.game.add.sprite(10, 200, 'backendQuestioner');
        this.questioner1BitmapText = this.game.add.text(7, 110, 'Intrebari de\n front-end?', {
            font: "12px Arial",
            fill: "#fff",
        });
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

     //   this.game.stage.backgroundColor = "#66CD00";

        this.physics.arcade.enable([
            this.player,
            this.questioner1,
            this.questioner2,
            this.sqlQuestioner,
            this.noSqlQuestioner,
        ]);

        function acceptHandler() {
            // stub
            this.state.start('questionsState');
        }

        function dismissHandler() {
            this.acceptButton.visible = false;
            this.dismissButton.visible = false;
            this.questionerDialogPanel.visible = false;
            this.player.x += 30;
        }
    },

    update: function update() {
        var downFlag = false;
        if (this.game.input.keyboard.isDown(Phaser.Keyboard.UP)) {
            this.player.y -= 2;
            downFlag = true;
            this.player.animations.play("up");

            // $('#modalNPC1').modal('hide');
            // $('#modalNPC2').modal('hide');
            // $('#modalNPC3').modal('hide');
            // $('#modalNPC4').modal('hide');

        }
        else if (this.game.input.keyboard.isDown(Phaser.Keyboard.DOWN)) {
            this.player.y += 2;
            downFlag = true;

            // $('#modalNPC1').modal('hide');
            // $('#modalNPC2').modal('hide');
            // $('#modalNPC3').modal('hide');
            // $('#modalNPC4').modal('hide');
        }
        else if (this.game.input.keyboard.isDown(Phaser.Keyboard.LEFT)) {
            this.player.x -= 2;
            downFlag = true;
            this.player.animations.play("walk");
            //
            // $('#modalNPC1').modal('hide');
            // $('#modalNPC2').modal('hide');
            // $('#modalNPC3').modal('hide');
            // $('#modalNPC4').modal('hide');
        }
        else if (this.game.input.keyboard.isDown(Phaser.Keyboard.RIGHT)) {
            downFlag = true;
            this.player.x += 2;
            this.player.animations.play("walk");

            // $('#modalNPC1').modal('hide');
            // $('#modalNPC2').modal('hide');
            // $('#modalNPC3').modal('hide');
            // $('#modalNPC4').modal('hide');
        }
        else {
            downFlag = false;
        }

        if(downFlag === false){
            this.player.animations.play('idle');
        }

        if(this.cursors.left.isDown){
            this.game.camera.x -= 2;
        } else if (this.cursors.right.isDown) {
            this.game.camera.x += 2;
        } else if (this.cursors.up.isDown) {
            this.game.camera.y -= 2;
        } else if (this.cursors.down.isDown) {
            this.game.camera.y += 2;
        }

        this.game.physics.arcade.overlap(this.player, this.questioner1, collisionHandler, null, this);
        this.game.physics.arcade.overlap(this.player, this.questioner2, collisionHandler2, null, this);
        this.game.physics.arcade.overlap(this.player, this.sqlQuestioner, collisionHandler3, null, this);
        this.game.physics.arcade.overlap(this.player, this.noSqlQuestioner, collisionHandler4, null, this);

        // needs a refactor, to much boilerplate code

        function collisionHandler(player, questionare) {

            $('#modalNPC1').modal('show');
            player.x += 5;
            player.y += 5;
        }

        function collisionHandler2(player, questionare2) {
            this.currentNpc = 'frontendQuestioner';

            $('#modalNPC2').modal('show');
            player.x += 5;
            player.y += 5;
        }

        function collisionHandler3(player, sqlQuestioner) {
            this.currentNpc = 'sqlQuestioner';

            $('#modalNPC3').modal('show');
            player.x += 5;
            player.y += 5;
        }

        function collisionHandler4(player, noSqlQuestioner) {
            this.currentNpc = 'noSqlQuestioner';

            $('#modalNPC4').modal('show');
            player.x += 5;
            player.y += 5;
        }
    }
};
