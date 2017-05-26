package serviceComponents;

import db.DBConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import serviceRepresentations.User;
import serviceResources.UserDAO;

@RestController
@RequestMapping("/v1/user/")
public class UserController {

    @Autowired
    DBConnector dbConnector;
    UserDAO userDAO;

    /**
     * Endpoint for retrieveing a user attributes based on it's name.
     * @param name The name, in the game, of this user.
     * @return 200 if user exists, 404 otherwise.
     */
    @RequestMapping(
            value = "/{name}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> getUser(@PathVariable(name = "name") String name) {
        userDAO = new UserDAO(dbConnector);
        User user = userDAO.getUser(name);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Endpoint for creating a user.
     * @param user The new user that needs to be added to the database.
     * @return BAD_REQUEST if the user data are invalid, CREATED otherwise.
     */
    @RequestMapping(
            value = "/",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        userDAO = new UserDAO(dbConnector);
        int id = userDAO.createUser(user);

        // TODO: needs rethinking.
        // TODO: avoid creation of duplicate users
        if (id == -1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Endpoint for deleting an user.
     * @param name The deletion is done using the user's name.
     * @return OK if the deletion succeeded, NOT_FOUND otherwise.
     */
    @RequestMapping(
            value = "/{name}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> removeUser(@PathVariable String name) {
        // TODO: check edge cases
        userDAO = new UserDAO(dbConnector);
        User user = userDAO.getUser(name);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userDAO.removeUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // TODO: Make it work for all the edge cases.
    /**
     * Registers a password for users that chose to sing in with the app's
     * internal register system.
     * @param id The id of the desired user.
     * @param password The password that needs to be registered for this user.
     * @return OK so far.
     */
    @RequestMapping(
            value = "/password",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> registerPassword(
            @RequestBody Long id,
            @RequestBody String password) {

       userDAO = new UserDAO(dbConnector);
       userDAO.registerPassword(id, password);

       return new ResponseEntity<>(HttpStatus.OK);
    }

}