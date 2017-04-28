var game = new Phaser.Game(800, 600, Phaser.AUTO, 'funweb-game');

var gameState = {
    preload: function preload() {
        this.load.image('player_stage_normal', '../assets/player_stand.png');
    },

    create: function create() {
        this.player = this.game.add.sprite(0, 0, 'player_stage_normal');

        // this.game.add.image(0, 0, 'background');
        this.game.stage.backgroundColor = "#4488AA";
        // game.add.image(200, 300, 'player_stage_normal', 5);
    },

    update: function update() {

    }
};

game.state.add('gameState', gameState);
game.state.start('gameState');