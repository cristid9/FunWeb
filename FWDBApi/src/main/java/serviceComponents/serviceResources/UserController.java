package serviceComponents.serviceResources;

import daos.UserDAO;
import db.DBConnector;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import serviceRepresentations.User;

@RestController
@RequestMapping("/v1/user/")
public class UserController {

    DBConnector dbConnector = new DBConnector();
    UserDAO userDAO = new UserDAO(dbConnector);

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
        User user = userDAO.getUser(name);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
        int id = userDAO.createUser(user);

        // TODO: needs rethinking.
        if (id == -1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}