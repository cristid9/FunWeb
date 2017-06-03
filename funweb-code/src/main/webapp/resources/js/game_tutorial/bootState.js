var bootState = {
    preload: function () {
        this.game.load.image('mainPlayer', '/resources/assets/mainPlayer.png');
    },

    create: function () {
        this.game.stage.backgroundColor = '#fff';
    },

    update: function() {
        this.state.start('loadingState');
    }
};
