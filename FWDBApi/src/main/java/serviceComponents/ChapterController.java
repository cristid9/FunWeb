package serviceComponents;

import db.DBConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import serviceRepresentations.Chapter;
import serviceResources.ChapterDAO;

import java.util.List;
import java.util.Map;

// TODO: What if an user adds duplicate chapters? Plm

@RestController
@RequestMapping("/v1/chapter/")
public class ChapterController {

    @Autowired
    DBConnector dbConnector;
    ChapterDAO chapterDAO;

    /**
     * Endpoint for creating a new chapter.
     * @param chapter A JSON representing the new question to be inserted.
     * @return BAD_REQUEST if the question is invalid, OK otherwise.
     */
    @RequestMapping(
            value = "/",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Long> createChapter(@RequestBody Chapter chapter) {
        chapterDAO = new ChapterDAO(dbConnector);
        Long id = chapterDAO.createChapter(chapter);

        // TODO: fields check
        // TODO: security

        if (id == -1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    /**
     * Endpoint for getting a chapter based on it's id in the database.
     * @param id The `id` in the database of the desired chapter.
     * @return A new `Chapter` object representing the entry in the DB with id `id`.
     */
    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Chapter> getChapter(@PathVariable Long id) {
        chapterDAO = new ChapterDAO(dbConnector);
        Chapter chapter = chapterDAO.getChapter(id);

        if (chapter == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(chapter, HttpStatus.OK);
    }

    /**
     * Endpoint for updating a chapter.
     * @param chapter A json with the new version of the chapter.
     * @return NOT_FOUND if the update failed, OK otherwise.
     */
    @RequestMapping(
            value = "/",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> updateChapter(@RequestBody Chapter chapter) {
        chapterDAO = new ChapterDAO(dbConnector);
        Boolean status = chapterDAO.updateChapter(chapter);

        if (status == Boolean.FALSE) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Endpoint for removing a chapter.
     * @param id The id of the targeted chapter.
     * @return NOT_FOUND if the deletion failed, OK otherwise.
     */
    @RequestMapping(
            value = "/",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> removeChapter(@RequestBody Map<String, Long> params) {
        // TODO: Edge cases and security checks.

        Long id = params.get("id"); // TODO: Some check may be added here

        chapterDAO = new ChapterDAO(dbConnector);

        Boolean status = chapterDAO.removeChapter(id);

        if (status == Boolean.FALSE) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Endpoint for getting all chapters.
     * @return OK so far.
     */
    @RequestMapping(
            value = "/all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Chapter>> getAllChapters() {
        chapterDAO = new ChapterDAO(dbConnector);

        // TODO: don't forget the security checks.

        List<Chapter> chapters = chapterDAO.getAllChapters();

        return new ResponseEntity<>(chapters, HttpStatus.OK);
    }

}
