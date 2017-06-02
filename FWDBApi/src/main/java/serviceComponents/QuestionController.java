package serviceComponents;

import db.DBConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    /**
     * Endpoint for getting a question based on it's id in the database.
     * @param id The `id` in the database of the desired question.
     * @return A new `Question` object representing the entry in the DB with id `id`.
     */
    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Question> getQuestion(@PathVariable Long id) {
        questionDAO = new QuestionDAO(dbConnector);
        Question question = questionDAO.getQuestion(id);

        if (question == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    /**
     * Endpoint for updating a question.
     * @param question A json with the new version of the question.
     * @return NOT_FOUND if the update failed, OK otherwise.
     */
    @RequestMapping(
            value = "/",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> updateQuestion(@RequestBody Question question) {
        questionDAO = new QuestionDAO(dbConnector);
        Boolean status = questionDAO.updateQuestion(question);

        if (status == Boolean.FALSE) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Endpoint for removing a question.
     * @param id The id of the targeted question.
     * @return NOT_FOUND if the deletion failed, OK otherwise.
     */
    @RequestMapping(
            value = "/",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> removeQuestion(@RequestBody Long id) {
        // TODO: Edge cases and security checks.

        questionDAO = new QuestionDAO(dbConnector);

        Boolean status = questionDAO.removeQuestion(id);

        if (status == Boolean.FALSE) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
