package serviceComponents;

import db.DBConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import serviceRepresentations.LoginDataSocial;
import serviceResources.LoginDataSocialDAO;

import java.util.List;

@RestController
@RequestMapping("/v1/social")
public class LoginDataSocialController {

    // TODO: add implementation
    @Autowired
    DBConnector dbConnector;
    LoginDataSocialDAO loginDataSocialDAO;

    /**
     * Rest endpoint for getting the auth hash of an user.
     * @param userId The id of the targeted user.
     * @return
     */
    @RequestMapping(
            value = "/{userId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> getEntry(
            @PathVariable(name = "userId") Long userId) {

        loginDataSocialDAO = new LoginDataSocialDAO(dbConnector);

        List<LoginDataSocial> entries = loginDataSocialDAO.getAllEntries(userId);

        if (entries.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(entries.get(0).getAuthHash(), HttpStatus.OK);
    }

    /**
     * Rest endpoint for adding a new entry, for social login/register.
     * @param userId The id of the user adding the new entry.
     * @param authHash ...
     * @return
     */
    @RequestMapping(
            value = "",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> createEntry(
            @RequestBody Long userId,
            @RequestBody String authHash) {

        loginDataSocialDAO = new LoginDataSocialDAO(dbConnector);
        LoginDataSocial loginDataSocial = new LoginDataSocial();
        loginDataSocial.setUserId(userId);
        loginDataSocial.setAuthHash(authHash);

        Long id = loginDataSocialDAO.createEntry(loginDataSocial);

        if (id == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Endpoint for updating the hash key for a user.
     * @param userId
     * @param newHash
     * @return
     */
    @RequestMapping(
            value = "/",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> updateEntry(
            @RequestBody Long userId,
            @RequestBody String newHash) {

        loginDataSocialDAO = new LoginDataSocialDAO(dbConnector);
        LoginDataSocial loginDataSocial = new LoginDataSocial();
        loginDataSocial.setAuthHash(newHash);
        loginDataSocial.setUserId(userId);

        Boolean status = loginDataSocialDAO.updateEntry(loginDataSocial);

        if (status == Boolean.FALSE) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * Endpoint for deleting a social login credentials for a user.
     * @param userId
     * @return
     */
    @RequestMapping(
            value = "/",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> deleteEntry(
            @RequestBody Long userId) {

        loginDataSocialDAO = new LoginDataSocialDAO(dbConnector);
        // TODO: Check possible empty returned lists
        LoginDataSocial loginDataSocial =
                loginDataSocialDAO.getAllEntries(userId).get(0);

        Boolean status = loginDataSocialDAO.removeEntry(loginDataSocial.getId());

        if (status == Boolean.FALSE) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
