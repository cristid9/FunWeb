var game = new Phaser.Game(document.body.offsetWidth, document.body.offsetHeight, Phaser.AUTO, 'funweb-game');


game.state.add('mainState', mainState);
game.state.start('mainState');
