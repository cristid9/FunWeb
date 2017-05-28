package serviceResources;


import db.DBConnector;
import serviceRepresentations.Hint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HintDAO {
    private DBConnector dbConnector;

    public HintDAO(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    /**
     * Returns a new `Hint` object populated with the associated values in db.
     * @param id The `Hint` it's selected by it's id.
     * @return  the hint with the id `id`.
     */
    public Hint getHint(Long id){
        Hint hint = null;
        try {
            hint = new Hint();
            Connection connection = dbConnector.getDBConnection();

            PreparedStatement statement = connection.prepareStatement("SELECT " +
                    "QUESTION_ID," +
                    "TEXT from HINTS where ID = ?");
            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();
            hint.setId(id);
            hint.setQuestionId(rs.getLong("QUESTION_ID"));
            hint.setText(rs.getString("TEXT"));

            return hint;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hint;
    }

    /**
     * Inserts a new entry in the database with the attributes of `Hint`.
     * @param hint The new hint to be inserted in the database.
     * @return Returns the `id` of the new entry.
     */
    public Long createHint(Hint hint){
        Long id = Long.valueOf(-1);

        try {
            Connection connection = dbConnector.getDBConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO HINTS VALUES (?, ?)");
            statement.setLong(1, hint.getQuestionId());
            statement.setString(2, hint.getText());

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            return Long.valueOf(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * Deletes a hint from the database.
     * @param id The `id` of the targeted hint.
     * @return TRUE if the deletion succeeded, FALSE otherwise.
     */

    public boolean removeHint(Long id){
        try {
            Connection connection = dbConnector.getDBConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM HINTS WHERE ID = ?");
            statement.setLong(1, id);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0)
                return Boolean.FALSE;
            return Boolean.TRUE;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }

    /**
     * Updates a hint in the database.
     * @param hint A hint object. It should contain the id of the original
     *                 and the fields that won't be updated should be the same as
     *                 in the original.
     * @return TRUE if the update succeeded, FALSE otherwise. An update could fail if there
     *         was nothing to update.
     */
    public boolean updateHint(Hint hint){

        try {
            Connection connection = dbConnector.getDBConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE HINTS SET " +
                    "QUESTION_ID = ?," +
                    "TEXT = ? WHERE ID = ?");

            statement.setLong(1, hint.getId());
            statement.setString(2, hint.getText());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0)
                return Boolean.FALSE;
            return Boolean.TRUE;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Boolean.FALSE;
    }

    /**
     * Returns the list with all the hints for a particular question.
     * @param questionId The question id whose hints we want.
     * @return A list of hints for that question.
     */
    public List<Hint> getQuestionHints(Long questionId){
        List<Hint> hints = null;

        try {
            hints = new ArrayList<>();

            Connection connection = dbConnector.getDBConnection();
            PreparedStatement statement =
                    connection.prepareStatement("SELECT ID, " +
                            "QUESTION_ID," +
                            "TEXT FROM HINTS");

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Hint hint = new Hint();
                hint.setId(rs.getLong("ID"));
                hint.setQuestionId(rs.getLong("QUESTION_ID"));
                hint.setText(rs.getString("TEXT"));

                hints.add(hint);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return hints;
    }

}
