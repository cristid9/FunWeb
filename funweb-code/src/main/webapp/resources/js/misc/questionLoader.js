function loadNpcQuestions(npcId, questionNumber) {

    var questionToBeAdded = qPool.questions[npcId][questionNumber];
    var questionOptions = questionToBeAdded['options'];

    $('#questionsPaneQuestion').text(questionToBeAdded['enunciation']);
    $('#questionsPaneOption1').text(questionOptions[0].enunciation);
    $('#questionsPaneOption2').text(questionOptions[1].enunciation);
    $('#questionsPaneOption3').text(questionOptions[2].enunciation);

    $('#questionsPane').modal('show');
}

function isOptionCorrect(npcId, questionNumber, optionNumber) {
    return qPool.questions[npcId][questionNumber]['options'][optionNumber]['correctness'];
}

function hasNextQuestion(npcId, questionNumber) {
    console.log(qPool.questions[npcId].length > questionNumber);

    return (qPool.questions[npcId].length > questionNumber);
}

