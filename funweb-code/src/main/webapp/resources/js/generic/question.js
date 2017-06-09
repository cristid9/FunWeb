function Question() {
    this.reward = null;
    this.chapterId = null;
    this.enunciation = null;
    this.characterId = null;
    this.questionId = null;
    this.options = [];
};

Question.prototype.initFields = function(fields) {
    this.reward = fields['reward'];
    this.chapterId = fields['chapterId'];
    this.enunciation = fields['enunciation'];
    this.characterId = fields['characterId'];
    this.questionId = fields['questionId'];
};

Question.prototype.getReward = function () {
    return this.reward;
}

Question.prototype.getChapterId = function () {
    return this.chapterId;
};

Question.prototype.getEnunciation = function () {
    return this.enunciation;
};

Question.prototype.getCharacterId = function () {
    return this.characterId;
};

Question.prototype.getQuestionId = function () {
    return this.questionId;
};

Question.prototype.initOptions = function(options) {
    this.options = options;
};

Question.prototype.getOptions = function () {
    return this.options;
};


