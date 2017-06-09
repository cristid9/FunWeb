function QuestionPool() {
    this.npcIds = [];
    this.questions = [];
};

QuestionPool.prototype.initPool = function(npcIds) {
    this.npcIds = npcIds;

    var self = this;
    for (var i = 0; i < npcIds.length; ++i) {
        $.get("http://localhost:8083/npcQuestions/" + self.npcIds[i], function (data) {
            var npcQuestions = JSON.parse(data);
            self.questions[i] = [];

            for (var j = 0; j < npcQuestions.length; ++j) {

                var q = new Question();
                q.initFields(npcQuestions[j]);

                $.get("http://localhost:8083/options/" + q.getQuestionId(), function (data2) {
                    var options = JSON.parse(data2);
                    q.initOptions(options);

                    self.questions[i].push(q);

                });

            }

        });
    }

    this.questions = self.questions;

};

QuestionPool.prototype.getQuestions = function (npcId) {
    return this.questions[npcId];
};

