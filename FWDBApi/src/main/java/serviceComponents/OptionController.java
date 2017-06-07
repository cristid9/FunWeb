package serviceComponents;

import db.DBConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import serviceRepresentations.Option;
import serviceResources.OptionDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bogdanel on 28.05.2017.
 */
@RestController
@RequestMapping("/v1/option")
public class OptionController {
    @Autowired
    DBConnector dbConnector;
    OptionDAO optionDAO;

    /**
     * Endpoint for retrieveing an option based on it's id.
     * @param id The id of the option.
     * @return 200 if user exists, 404 otherwise.
     */
    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Option> getOption(@PathVariable Long id) {
        optionDAO = new OptionDAO(dbConnector);
        Option option = optionDAO.getOption(id);

        if (option == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(option, HttpStatus.OK);
    }


    /**
     * Endpoint for creating a new option.
     * @param option A JSON representing the new option to be inserted.
     * @return BAD_REQUEST if the question is invalid, OK otherwise.
     */
    @RequestMapping(
            value = "/",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Long> createOption(@RequestBody Option option) {
        optionDAO = new OptionDAO(dbConnector);
        Long id = optionDAO.createOption(option);

        // TODO: fields check
        // TODO: security

        if (id == -1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    /**
     * Endpoint for updating an option.
     * @param option A json with the new version of the question.
     * @return NOT_FOUND if the update failed, OK otherwise.
     */
    @RequestMapping(
            value = "/",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> updateOption(@RequestBody Option option) {
        optionDAO = new OptionDAO(dbConnector);
        Boolean status = optionDAO.updateOption(option);

        if (status == Boolean.FALSE) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Endpoint for removing an option.
     * @param id The id of the targeted question with the options
     * @return NOT_FOUND if the deletion failed, OK otherwise.
     */
    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> removeOption(@PathVariable Long id) {
        // TODO: Edge cases and security checks.

        optionDAO = new OptionDAO(dbConnector);

        Boolean status = optionDAO.deleteOption(id);

        if (status == Boolean.FALSE) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Endpoint for getting the options of a question
     * @return OK so far.
     */
    @RequestMapping(
            value = "/all/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Option>> getQuestionOptions(@PathVariable Long id) {
        optionDAO = new OptionDAO(dbConnector);

        // TODO: don't forget the security checks.

        ArrayList<Option> options = (ArrayList<Option>) optionDAO.getQuestionOptions(id);

        return new ResponseEntity<>(options, HttpStatus.OK);
    }


}
