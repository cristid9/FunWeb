package serviceComponents;

import db.DBConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import serviceRepresentations.User;
import serviceResources.UserDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/user")
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
        Boolean status = userDAO.removeUser(name);

        if (status == Boolean.FALSE) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Returns a string representing the weakest chapter for the desired user.
     * @param id The is of the desired user.
     * @return A string representing the weakest chapter.
     */
    @Deprecated
    @RequestMapping(
            value = "/weakestChapter/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> getWeakestChapter(@PathVariable Long id) {
        userDAO = new UserDAO(dbConnector);
        String chapter = userDAO.weakestChapter(id);

        // TODO: Cover edge cases.
        // TODO: Some dummy entries need to be created in order for this endpoint to work.

        return new ResponseEntity<>(chapter, HttpStatus.OK);
    }

    /**
     * Checks if a username is valid or returns a suggestion, if not.
     * @param name The candidate name.
     * @return An empty string if the name is valid, a suggestion otherwise.
     */
    @Deprecated
    @RequestMapping(
            value = "/checkValidUsername",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> checkIfValidUsername(@RequestBody String name) {
        userDAO = new UserDAO(dbConnector);
        String suggestion = userDAO.checkIfValidUsername(name);

        return new ResponseEntity<>(suggestion, HttpStatus.OK);
    }

    /**
     * Checks if a password matches a name. This endpoint is available only for
     * users that chose to register using the internal register system.
     * name The name of the candidate user.
     * password The candidate password.
     * @return Not fully defined yet.
     */
    @RequestMapping(
            value = "/checkPasswordMatch",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Boolean> checkIfUserMatchesPassword(
            @RequestBody Map<String, String> params) {

        String name = params.get("name");
        String password = params.get("password");

        // TODO: What about password hashing?
        // TODO: What about handling edge cases.

        userDAO = new UserDAO(dbConnector);
        Boolean status = userDAO.checkIfUserMatchesPassword(name, password);

        return new ResponseEntity<>(status, HttpStatus.OK);
    }


    /**
     * Endpoint for checking the straightness of a password.
     * @param password The candidate password.
     * @return OK so far.
     */
    @Deprecated
    @RequestMapping(
            value = "/checkPasswordStraightness",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Integer> checkPasswordStrengthness(@RequestBody String password) {
        userDAO = new UserDAO(dbConnector);

        Integer strength = userDAO.checkPasswordStrengthness(password);

        return new ResponseEntity<>(strength, HttpStatus.OK);
    }

    /**
     * Endpoint for getting the names of all the users.
     * @return OK so far.
     */
    @RequestMapping(
            value = "/all",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<String>> getAllUsersNames() {
        userDAO = new UserDAO(dbConnector);

        // TODO: don't forget the security checks.

        ArrayList<String> users = userDAO.getAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}