var ready = false;

var loadingState = {
    preload: function () {
        this.game.load.image('mainPlayer', '/resources/assets/mainPlayer.png');
        this.game.load.image('backendQuestioner', '/resources/assets/backendQuestioner.png');
        this.game.load.image('frontendQuestioner', '/resources/assets/frontendQuestioner.png');
        this.game.load.image('sqlQuestioner', '/resources/assets/sqlQuestioner.png');
        this.game.load.spritesheet('trotinel', '/resources/assets/playerSprite.png', 80, 110, 24);
        this.game.load.image('noSqlQuestioner', '/resources/assets/noSqlQuestioner.png');
        this.game.load.image('acceptButton', '/resources/assets/accept.png');
        this.game.load.image('dismissButton', '/resources/assets/dismiss.png');
        this.game.load.image('questionerDialogPanel', '/resources/assets/questionerDialogPanel.png');
        this.game.load.tilemap('map', '/resources/assets/map.json', null, Phaser.Tilemap.TILED_JSON);
        this.game.load.image('tiles', '/resources/assets/terrain.png');
        this.game.load.image('tiles1', '/resources/assets/trees.png');

    },

    create: function () {
        this.game.stage.backgroundColor = '#aaa';

        this.guy = this.game.add.sprite(570, this.game.world.centerY, 'trotinel');
        this.guy.scale.setTo(1.5, 1.5);

        this.waitBubble = this.game.add.sprite(700, 200, 'questionerDialogPanel');
        this.waitBubble.scale.setTo(1.5, 1.5);
        this.waitText = this.game.add.text(700, 200, '\n   Loading \n    Game', {
            font: "25px Arial"
        });

        this.guy.animations.add('idle', [0, 23] , 3, true);
        this.guy.animations.play('idle');


        qPool.initPool([1, 2]);
    },

    update: function() {

        var self = this;
        setTimeout(function () {
            self.state.start('mainState');
        }, 7000);

    }
};
