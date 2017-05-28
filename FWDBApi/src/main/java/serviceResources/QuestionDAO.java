package serviceResources;

import db.DBConnector;
import serviceRepresentations.Question;

import java.sql.*;
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
            question = new Question();
            Connection connection = dbConnector.getDBConnection();
            PreparedStatement statement =
                    connection.prepareStatement("SELECT " +
                            "ENUNCIATION, " +
                            "REWARD, " +
                            "CHARCTERS_ID, " +
                            "CHAPTER FROM QUESTIONS WHERE QUESTION_ID = ?");
            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();
            question.setId(id);
            question.setEnunciation(rs.getString("ENUNCIATION"));
            question.setChapterId(rs.getLong("CHAPTER"));
            question.setReward(rs.getLong("REWARD"));
            question.setCharacterId(rs.getLong("CHARACTERS_ID"));

            return question;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return question;
    }

    /**
     *
     * @return
     */
    public List<Question> getNPCQuestions(Long npcId) {
        return null;
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
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO QUESTIONS VALUES" +
                            "?, ?, ?, ?");
            statement.setString(1, question.getEnunciation());
            statement.setLong(2, question.getReward());
            statement.setLong(3, question.getCharacterId());
            statement.setLong(4, question.getCharacterId());

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            return Long.valueOf(rs.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
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
