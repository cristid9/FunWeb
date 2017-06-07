package serviceComponents;

import db.DBConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import serviceRepresentations.PendingPasswordReset;
import serviceResources.PendingPasswordResetDAO;

import java.util.Map;

@RestController
@RequestMapping("v1/pendingpasswordreset")
public class PendingPasswordResetController {

    @Autowired
    DBConnector dbConnector;
    PendingPasswordResetDAO pendingPasswordResetDAO;

    /**
     * Endpoint for creating a new entry.
     * @param pendingPasswordReset A JSON representing the new entry to be inserted.
     * @return BAD_REQUEST if the question is invalid, OK otherwise.
     */
    @RequestMapping(
            value = "/",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Long> createEntry(
            @RequestBody PendingPasswordReset pendingPasswordReset) {

        pendingPasswordResetDAO = new PendingPasswordResetDAO(dbConnector);
        Long id = pendingPasswordResetDAO.createEntry(pendingPasswordReset);

        // TODO: fields check
        // TODO: security

        if (id == -1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    /**
     * Endpoint for deleting an existing  entry.
     * @param params The id of the targeted entry.
     * @return BAD_REQUEST if the question is invalid, OK otherwise.
     */
    @RequestMapping(
            value = "/",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> deleteEntry(
            @RequestBody Map<String, String> params) {

        pendingPasswordResetDAO = new PendingPasswordResetDAO(dbConnector);
        Long id = Long.parseLong(params.get("id"));

        // TODO: fields check
        // TODO: security

        if (!pendingPasswordResetDAO.deleteEntry(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Retrieves the entry for that user.
     * @param username The username whose entry we want.
     * @return BAD_REQUEST if the question is invalid, OK otherwise.
     */
    @RequestMapping(
            value = "/{username}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PendingPasswordReset> getEntry(
            @PathVariable String username) {

        pendingPasswordResetDAO = new PendingPasswordResetDAO(dbConnector);
        PendingPasswordReset pendingPasswordReset = pendingPasswordResetDAO.getEntry(username);

        // TODO: fields check
        // TODO: security

        if (pendingPasswordReset == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(pendingPasswordReset, HttpStatus.OK);
    }
}
