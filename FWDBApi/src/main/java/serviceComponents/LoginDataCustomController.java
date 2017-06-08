package serviceComponents;

import com.sun.org.apache.xpath.internal.operations.Bool;
import db.DBConnector;
import org.springframework.batch.core.jsr.launch.support.BatchPropertyBeanPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import serviceRepresentations.LoginDataCustom;
import serviceResources.LoginDataCustomDAO;

import java.util.List;
import java.util.Map;

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
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
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
     * Rest endpoint for creating a password. Usually used when an user registers.
     * @param params : userid and the password
     * @return
     */
    @RequestMapping(
            value = "/",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> createPassword(
            @RequestBody Map<String, String> params) {

        loginDataCustomDAO = new LoginDataCustomDAO(dbConnector);

        LoginDataCustom loginDataCustom = new LoginDataCustom();
        loginDataCustom.setPassword(params.get("newPassword"));
        loginDataCustom.setUserId(Long.parseLong(params.get("userId")));

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
            value = "/{userId}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> deletePassword(@PathVariable Long userId) {
        loginDataCustomDAO = new LoginDataCustomDAO(dbConnector);

        // TODO: boundaries check
        List<LoginDataCustom> logins = loginDataCustomDAO.getAllEntries(userId);
        if(logins.size() == 0)
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

        Boolean status = loginDataCustomDAO.removeEntry(logins.get(0).getId());

        if (status == Boolean.FALSE) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
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
            @RequestBody Map<String, String> params) {

        loginDataCustomDAO = new LoginDataCustomDAO(dbConnector);

        LoginDataCustom loginDataCustom = new LoginDataCustom();
        loginDataCustom.setUserId(Long.parseLong(params.get("userId")));
        loginDataCustom.setPassword(params.get("newPassword"));

        Boolean status = loginDataCustomDAO.updateEntry(loginDataCustom);

        if (status == Boolean.FALSE) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
