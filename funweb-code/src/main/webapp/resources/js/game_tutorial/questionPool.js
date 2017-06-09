function QuestionPool() {
    this.npcIds = [];
    this.questions = [];
};

QuestionPool.prototype.initPool = function(npcIds) {
    this.npcIds = npcIds;

    var self = this;
    for (var i = 0; i < npcIds.length; ++i) {
        $.get("http://localhost:8083/npcQuestions/" + i, self.npcIds[i], function (data) {
            var questions = JSON.parse(data);
            self.questions[i] = [];

            for (var i = 0; i < questions.length; ++i) {

                var q = new Question();
                q.initFields(questions[i]);



            }

        });
    }
};

