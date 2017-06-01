package serviceComponents;

import db.DBConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import serviceRepresentations.LoginDataCustom;
import serviceResources.LoginDataCustomDAO;

import java.util.List;

@RestController
@RequestMapping("/v1/custom")
public class LoginDataCustomController {

    @Autowired
    DBConnector dbConnector;
    LoginDataCustomDAO loginDataCustomDAO;

    /**
     * Rest endpoint for getting the password of an user.
     * @param userId
     * @return
     */
    @RequestMapping(
            value = "/{userId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> getPassword(
            @PathVariable(name = "userId") Long userId) {

        loginDataCustomDAO = new LoginDataCustomDAO(dbConnector);
        List<LoginDataCustom> entries = loginDataCustomDAO.getAllEntries(userId);

        if (entries.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(entries.get(0).getPassword(), HttpStatus.OK);
    }

    /**
     * Rest endpoint for updating the password of an user.
     * @param userId
     * @param newPassword
     * @return
     */
    @RequestMapping(
            value = "/",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> updatePassword(
            @RequestBody Long userId,
            @RequestBody String newPassword) {

        loginDataCustomDAO = new LoginDataCustomDAO(dbConnector);

        LoginDataCustom loginDataCustom = new LoginDataCustom();
        loginDataCustom.setUserId(userId);
        loginDataCustom.setPassword(newPassword);

        Boolean status = loginDataCustomDAO.updateEntry(loginDataCustom);

        if (status == Boolean.FALSE) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Rest endpoint for creating a password. Usually used when an user registers.
     * @param userId
     * @param password
     * @return
     */
    @RequestMapping(
            value = "/",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> createPassword(
            @RequestBody Long userId,
            @RequestBody String password) {

        loginDataCustomDAO = new LoginDataCustomDAO(dbConnector);

        LoginDataCustom loginDataCustom = new LoginDataCustom();
        loginDataCustom.setPassword(password);
        loginDataCustom.setUserId(userId);

        Long id = loginDataCustomDAO.createEntry(loginDataCustom);

        if (id == -1) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * This endpoint is usually used when an user deletes his account.
     * @param userId
     * @return
     */
    @RequestMapping(
            value = "/",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> deletePassword(@RequestBody Long userId) {
        loginDataCustomDAO = new LoginDataCustomDAO(dbConnector);

        // TODO: boundaries check
        Boolean status = loginDataCustomDAO.removeEntry(
                loginDataCustomDAO.getAllEntries(userId).get(0).getId());

        if (status == Boolean.FALSE) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
