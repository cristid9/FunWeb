package daos;

import db.DBConnection;

public class QuestionDAO {

    private DBConnection dbConnection;

    public QuestionDAO(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }
    
    /**
     * Wrapper over `isRelevant` pl/sql function. Checks if a question is relevant.
     * @param id The id of the question
     * @return
     */
    public boolean isRelevant(Long id) {
        return true;
    }

}
