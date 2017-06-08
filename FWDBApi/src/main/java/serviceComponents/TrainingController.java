package serviceComponents;

import db.DBConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import serviceRepresentations.Training;
import serviceResources.QuestionDAO;
import serviceResources.TrainingDAO;

@RestController
@RequestMapping("/v1/training")
public class TrainingController {

    @Autowired
    DBConnector dbConnector;
    TrainingDAO trainingDAO;

    /**
     * Endpoint for creating a new insert in training table.
     * @param training A JSON representing the new training row to be inserted.
     * @return BAD_REQUEST if the question is invalid, OK otherwise.
     */
    @RequestMapping(
            value = "/",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Long> createTraining(@RequestBody Training training) {
        trainingDAO = new TrainingDAO(dbConnector);
        Long id = trainingDAO.createEntry(training);

        // TODO: fields check
        // TODO: security

        if (id == -1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    /**
     *Endpoint for getting avg score of a player
     * @param userName A string representing userName
     * @return BAD_REQUEST if the user is not found in the db , avg otherwise.
     */

    @RequestMapping(
            value = "/{userName}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public ResponseEntity<String> getScoreOf(@PathVariable String userName){
        trainingDAO = new TrainingDAO(dbConnector);

        int score = trainingDAO.getAvgScore(userName);
        if(score == -1){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(Integer.toString(score), HttpStatus.OK);
    }
}
