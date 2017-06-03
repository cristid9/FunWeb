package serviceComponents;

import db.DBConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import serviceRepresentations.GameCharacter;
import serviceResources.CharacterDAO;

import java.util.List;

@RestController
@RequestMapping("/v1/character")
public class CharacterController {

    @Autowired
    DBConnector dbConnector;
    CharacterDAO characterDAO;

    /**
     * Endpoint for retrieveing a character attributes based on it's id.
     * @param id The id of the character.
     * @return 200 if user exists, 404 otherwise.
     */
    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GameCharacter> getCharacter(@PathVariable Long id) {
        characterDAO = new CharacterDAO(dbConnector);
        GameCharacter character = characterDAO.getCharacter(id);

        if (character == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(character, HttpStatus.OK);
    }

    /**
     * Endpoint for creating a user.
     * @param character The new character that needs to be added to the database.
     * @return BAD_REQUEST if the user data are invalid, CREATED otherwise.
     */
    @RequestMapping(
            value = "/",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> createCharacter(@RequestBody GameCharacter character) {
        characterDAO = new CharacterDAO(dbConnector);
        boolean status = characterDAO.createCharacter(character);

        // TODO: needs rethinking.
        // TODO: avoid creation of duplicate users
        if (status == false) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Endpoint for deleting a character.
     * @param id The deletion is done using the character's id.
     * @return OK if the deletion succeeded, NOT_FOUND otherwise.
     */
    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> removeCharacter(@PathVariable Long id) {
        // TODO: check edge cases
        characterDAO = new CharacterDAO(dbConnector);
        GameCharacter character = characterDAO.getCharacter(id);

        if (character == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        characterDAO.removeCharacter(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Endpoint for updating a character.
     * @param character A json with the new version of the character.
     * @return NOT_FOUND if the update failed, OK otherwise.
     */
    @RequestMapping(
            value = "/",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> updateCharacter(@RequestBody GameCharacter character) {
        characterDAO = new CharacterDAO(dbConnector);
        Boolean status = characterDAO.updateCharacter(character);

        if (status == Boolean.FALSE) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Endpoint for getting all characters.
     * @return OK so far.
     */
    @RequestMapping(
            value = "/all",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<GameCharacter>> getAllCharacters() {
        characterDAO = new CharacterDAO(dbConnector);

        // TODO: don't forget the security checks.

        List<GameCharacter> characters = characterDAO.getCharacters();

        return new ResponseEntity<>(characters, HttpStatus.OK);
    }
}
