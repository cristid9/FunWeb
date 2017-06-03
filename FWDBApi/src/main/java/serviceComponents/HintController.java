package serviceComponents;

import db.DBConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import serviceRepresentations.Hint;
import serviceResources.HintDAO;

import java.util.List;

@RestController
@RequestMapping("/v1/hint")
public class HintController {

    @Autowired
    DBConnector dbConnector;
    HintDAO hintDAO;

    /**
     * Endpoint for retrieving a hint's attributes based on it's id.
     * @param id The id of the character.
     * @return 200 if user exists, 404 otherwise.
     */
    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Hint> getHint(@PathVariable Long id) {
        hintDAO = new HintDAO(dbConnector);
        Hint hint = hintDAO.getHint(id);

        if (hint == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(hint, HttpStatus.OK);
    }

    /**
     * Endpoint for creating a hint.
     * @param hint The new character that needs to be added to the database.
     * @return BAD_REQUEST if the user data are invalid, CREATED otherwise.
     */
    @RequestMapping(
            value = "/",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> createHint(@RequestBody Hint hint) {
        hintDAO = new HintDAO(dbConnector);
        Long id = hintDAO.createHint(hint);

        // TODO: needs rethinking.
        // TODO: avoid creation of duplicate users
        if (id == -1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Endpoint for deleting a hint.
     * @param id The deletion is done using the hint's id.
     * @return OK if the deletion succeeded, NOT_FOUND otherwise.
     */
    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> removeHint(@PathVariable Long id) {
        // TODO: check edge cases
        hintDAO = new HintDAO(dbConnector);
        Hint hint = hintDAO.getHint(id);

        if (hint == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        hintDAO.removeHint(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Endpoint for updating a hint.
     * @param hint A json with the new version of the hint.
     * @return NOT_FOUND if the update failed, OK otherwise.
     */
    @RequestMapping(
            value = "/",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> updateHint(@RequestBody Hint hint) {
        hintDAO = new HintDAO(dbConnector);
        Boolean status = hintDAO.updateHint(hint);

        if (status == Boolean.FALSE) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Endpoint for getting all hints for a question.
     * @param id The targeted question.
     * @return OK so far.
     */
    @RequestMapping(
            value = "/all/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Hint>> getallQuestionHints(
            @PathVariable Long id) {
        hintDAO = new HintDAO(dbConnector);

        // TODO: don't forget the security checks.

        List<Hint> hints = hintDAO.getQuestionHints(id);

        return new ResponseEntity<>(hints, HttpStatus.OK);
    }
}
