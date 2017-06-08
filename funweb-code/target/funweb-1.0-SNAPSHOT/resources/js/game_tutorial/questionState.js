var questionsState = {
  preload: function () {
      this.game.load.image('board', '/resources/assets/questionPanel.png');
  },

  create: function () {
      this.board = this.game.add.sprite(this.world.centerX - 300, this.world.centerY - 200, 'board');
      this.board.scale.setTo(6, 4);
      this.errorMessage = this.game.add.text(450, 255, "Could not load questions from server!",{
          font: "35px arcadeFont",
          fill: "#fff",
      } );
  },

  update: function() {

  }
};