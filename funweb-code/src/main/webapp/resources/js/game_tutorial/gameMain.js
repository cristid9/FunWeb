var game = new Phaser.Game(document.body.offsetWidth, document.body.offsetHeight, Phaser.AUTO, 'funweb-game');


game.state.add('loadingState', loadingState);
game.state.add('mainState', mainState);
game.state.add('questionsState', questionsState);

game.state.start('loadingState');
