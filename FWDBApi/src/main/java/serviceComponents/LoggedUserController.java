package serviceComponents;


import db.DBConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import serviceRepresentations.LoggedUser;
import serviceResources.LoggedUserDAO;
import sun.rmi.runtime.Log;

import java.util.List;

@RestController
@RequestMapping("/v1/loggeduser")
public class LoggedUserController {
    @Autowired
    DBConnector dbConnector;
    LoggedUserDAO loggedUserDAO;

    /**
     * Endpoint for retrieving a loggeduser's attributes based on it's name.
     * @param name The name of the user.
     * @return 200 if user exists, 404 otherwise.
     */
    @RequestMapping(
            value = "/{name}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<LoggedUser> getHint(@PathVariable String name) {
        loggedUserDAO = new LoggedUserDAO(dbConnector);
        LoggedUser loggedUser = loggedUserDAO.getLoggedUser(name);

        if (loggedUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(loggedUser, HttpStatus.OK);
    }

    /**
     * Endpoint for creating a loggedUser.
     * @param loggedUser The new loggedUser that needs to be added to the database.
     * @return BAD_REQUEST if the user data are invalid, CREATED otherwise.
     */
    @RequestMapping(
            value = "/",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> createHint(@RequestBody LoggedUser loggedUser) {
        loggedUserDAO = new LoggedUserDAO(dbConnector);
        Long id = loggedUserDAO.createEntry(loggedUser);

        // TODO: needs rethinking.
        // TODO: avoid creation of duplicate users
        if (id == -1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Endpoint for deleting a loggedUser.
     * @param name The deletion is done using the logged's name.
     * @return OK if the deletion succeeded, NOT_FOUND otherwise.
     */
    @RequestMapping(
            value = "/{name}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> removeHint(@PathVariable String name) {
        // TODO: check edge cases
        loggedUserDAO = new LoggedUserDAO(dbConnector);

        LoggedUser loggedUser = loggedUserDAO.getLoggedUser(name);

        if (loggedUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        loggedUserDAO.removeEntry(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Endpoint for updating a loggedUser.
     * @param loggedUser A json with the new version of the hint.
     * @return NOT_FOUND if the update failed, OK otherwise.
     */
    @RequestMapping(
            value = "/",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> updateHint(@RequestBody LoggedUser loggedUser) {
        loggedUserDAO = new LoggedUserDAO(dbConnector);
        Boolean status = loggedUserDAO.updateEntry(loggedUser);

        if (status == Boolean.FALSE) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Endpoint for getting all loggedUsers from database.
     * @return OK so far.
     */
    @RequestMapping(
            value = "/all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<LoggedUser>> getAllLoggedUsers() {
        loggedUserDAO = new LoggedUserDAO(dbConnector);

        // TODO: don't forget the security checks.
        List<LoggedUser> hints = loggedUserDAO.getAllEntries();

        return new ResponseEntity<>(hints, HttpStatus.OK);
    }


}
