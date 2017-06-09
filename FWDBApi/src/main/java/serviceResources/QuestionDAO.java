package serviceResources;

import db.DBConnector;
import serviceRepresentations.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {

    private DBConnector dbConnector;

    public QuestionDAO(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    /**
     * Returns a new `Question` object populated to the associated values in
     * the database.
     * @param id The question is selected based on it's id.
     * @return The question with id `id`.
     */
    public Question getQuestion(Long id) {
        Question question = null;

        try {
            Connection connection = dbConnector.getDBConnection();
            PreparedStatement statement =
                    connection.prepareStatement("SELECT " +
                            "ENUNCIATION, " +
                            "REWARD, " +
                            "CHARACTERS_ID, " +
                            "CHAPTER_ID FROM QUESTIONS WHERE QUESTION_ID = ?");
            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                question = new Question();
                question.setId(id);
                question.setEnunciation(rs.getString("ENUNCIATION"));
                question.setChapterId(rs.getLong("CHAPTER_ID"));
                question.setReward(rs.getLong("REWARD"));
                question.setCharacterId(rs.getLong("CHARACTERS_ID"));
            }
            return question;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return question;
    }

    /**
     * Returns the list with all the questions registered to a particular NPC.
     * @param npcId The NPC whose questions we want.
     * @return Returns a list with all the questions.
     */
    public List<Question> getNPCQuestions(Long npcId) {

        // TODO: Security and edge cases.

        List<Question> questions = null;

        try {

            questions = new ArrayList<>();

            Connection connection = dbConnector.getDBConnection();
            PreparedStatement statement =
                    connection.prepareStatement("SELECT QUESTION_ID, " +
                            "ENUNCIATION, " +
                            "REWARD, " +
                            "CHARACTERS_ID, " +
                            "CHAPTER_ID FROM QUESTIONS WHERE CHARACTERS_ID = ?");

            statement.setLong(1, npcId);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Question question = new Question();

                question.setId(rs.getLong("QUESTION_ID"));
                question.setReward(rs.getLong("REWARD"));
                question.setCharacterId(rs.getLong("CHARACTERS_ID"));
                question.setEnunciation(rs.getString("ENUNCIATION"));
                question.setChapterId(rs.getLong("CHAPTER_ID"));

                questions.add(question);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questions;
    }

    /**
     * Inserts a new entry in the database with the attributes of `question`.
     * @param question The new question to be inserted in the database.
     * @return Returns the `id` of the new entry.
     */
    public Long createQuestion(Question question) {
        // TODO: Don't forget about edge cases.
        Long id = Long.valueOf(-1);
        try {
            Connection connection = dbConnector.getDBConnection();

            String[] returnId = {"QUESTION_ID"};

            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO QUESTIONS(ENUNCIATION, REWARD, CHARACTERS_ID, CHAPTER_ID) VALUES" +
                            "(?, ?, ?, ?)", returnId);
            statement.setString(1, question.getEnunciation());
            statement.setLong(2, question.getReward());
            statement.setLong(3, question.getCharacterId());
            statement.setLong(4, question.getChapterId());

            int affectedRows = statement.executeUpdate();

            if(affectedRows == 0){
                return Long.valueOf(-1);
            }
            try(ResultSet rs = statement.getGeneratedKeys()){
                if(rs.next()){
                    return Long.valueOf(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    /**
     * Updates a question in the database.
     * @param question A question object. It should contain the id of the original
     *                 and the fields that won't be updated should be the same as
     *                 in the original.
     * @return TRUE if the update succeeded, FALSE otherwise. An update could fail if there
     *         was nothing to update.
     */
    public Boolean updateQuestion(Question question) {

        try {
            Connection connection = dbConnector.getDBConnection();
            PreparedStatement statement =
                    connection.prepareStatement("UPDATE QUESTIONS SET " +
                            "ENUNCIATION = ?," +
                            "REWARD = ?," +
                            "CHARACTERS_ID = ?," +
                            "CHAPTER_ID = ?" +
                            "WHERE QUESTION_ID = ?");

            statement.setString(1, question.getEnunciation());
            statement.setLong(2, question.getReward());
            statement.setLong(3, question.getCharacterId());
            statement.setLong(4, question.getChapterId());

            statement.setLong(5, question.getId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0)
                return Boolean.FALSE;

            return Boolean.TRUE;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Spaghetti code ftw!
        return Boolean.FALSE;
    }

    /**
     * Deletes a question from the database.
     * @param id The `id` of the targeted question.
     * @return TRUE if the deletion succeeded, FALSE otherwise.
     */
    public Boolean removeQuestion(Long id) {
        try {
            Connection connection = dbConnector.getDBConnection();
            // TODO: What about foreign keys?!
            PreparedStatement statement =
                    connection.prepareStatement("DELETE FROM QUESTIONS WHERE QUESTION_ID = ?");
            statement.setLong(1, id);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                return Boolean.FALSE;
            }

            return Boolean.TRUE;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Boolean.TRUE;
    }

}
