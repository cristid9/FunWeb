package serviceComponents;

import db.DBConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import serviceRepresentations.Question;
import serviceResources.QuestionDAO;

@RestController
@RequestMapping("/v1/question")
public class QuestionController {

    @Autowired
    DBConnector dbConnector;
    QuestionDAO questionDAO;

    /**
     * Endpoint for creating a new question.
     * @param question A JSON representing the new question to be inserted.
     * @return BAD_REQUEST if the question is invalid, OK otherwise.
     */
    @RequestMapping(
            value = "/",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Long> createQuestion(@RequestBody Question question) {
        questionDAO = new QuestionDAO(dbConnector);
        Long id = questionDAO.createQuestion(question);

        // TODO: fields check
        // TODO: security

        if (id == -1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
