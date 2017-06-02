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
import java.util.Map;

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
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public ResponseEntity<String> getEntry(@PathVariable Long userId) {

        loginDataSocialDAO = new LoginDataSocialDAO(dbConnector);

        List<LoginDataSocial> entries = loginDataSocialDAO.getAllEntries(userId);

        if (entries.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(entries.get(0).getAuthHash(), HttpStatus.OK);
    }

    /**
     * Rest endpoint for adding a new entry, for social login/register.
     * @param params The id of the user and the hash.
     * @return
     */
    @RequestMapping(
            value = "",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> createEntry(
            @RequestBody Map<String, String> params) {

        loginDataSocialDAO = new LoginDataSocialDAO(dbConnector);
        LoginDataSocial loginDataSocial = new LoginDataSocial();
        loginDataSocial.setUserId(Long.parseLong(params.get("userId")));
        loginDataSocial.setAuthHash(params.get("authHash"));

        Long id = loginDataSocialDAO.createEntry(loginDataSocial);

        if (id == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Endpoint for updating the hash key for a user.
     * @param params the user_id and the authhash
     * @return
     */
    @RequestMapping(
            value = "/",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> updateEntry(
            @RequestBody Map<String, String> params) {

        loginDataSocialDAO = new LoginDataSocialDAO(dbConnector);
        LoginDataSocial loginDataSocial = new LoginDataSocial();
        loginDataSocial.setAuthHash(params.get("authHash"));
        loginDataSocial.setUserId(Long.parseLong(params.get("userId")));

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
            value = "/{userId}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> deleteEntry(
            @PathVariable Long userId) {

        loginDataSocialDAO = new LoginDataSocialDAO(dbConnector);
        // TODO: Check possible empty returned lists
        List<LoginDataSocial> logins =
                loginDataSocialDAO.getAllEntries(userId);
        if(logins.size() == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Boolean status = loginDataSocialDAO.removeEntry(logins.get(0).getId());

        if (status == Boolean.FALSE) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
