package serviceResources;

import db.DBConnector;
import serviceRepresentations.Question;

import java.util.List;

public class QuestionDAO {

    private DBConnector dbConnector;

    public QuestionDAO(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    /**
     * --
     * @param id
     * @return
     */
    public Question getQuestion(Long id) {
        return null;
    }

    /**
     *
     * @return
     */
    public List<Question> getNPCQuestions(Long npcId) {
        return null;
    }

    /**
     *
     * @param question
     * @return
     */
    public Long createQuestion(Question question) {
        return null;
    }

    /**
     *
     * @param question
     * @return
     */
    public Boolean updateQuestion(Question question) {
        return null;
    }
    
}
