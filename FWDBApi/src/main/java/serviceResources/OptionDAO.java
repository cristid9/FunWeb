package serviceResources;

import com.sun.org.apache.xpath.internal.operations.Bool;
import db.DBConnector;
import serviceRepresentations.Option;
import sun.text.resources.no.CollationData_no;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bogdanel on 28.05.2017.
 */
public class OptionDAO {
    private DBConnector dbConnector;

    public OptionDAO(DBConnector dbConnector){
        this.dbConnector = dbConnector;
    }

    /**
     * Returns a new `Qption` object populated to the associated values in
     * the database.
     * @param id The option is selected based on it's id.
     * @return The Option with id `id`.
     */
    public Option getOption(Long id){
        Option option = null;

        try{
            Connection connection = dbConnector.getDBConnection();

            PreparedStatement statement = connection.prepareStatement("SELECT ENUNCIATION, CORRECTNESS, QUESTIONS_QUESTION_ID FROM OPTIONS where ID = ?");
            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                option = new Option();
                option.setId(id);
                option.setEnunciation(rs.getString("ENUNCIATION"));
                option.setQuestionId(rs.getLong("QUESTIONS_QUESTION_ID"));

                int correct = rs.getInt("CORRECTNESS");
                if (correct == 1)
                    option.setCorrectness(Boolean.TRUE);
                else
                    option.setCorrectness(Boolean.FALSE);
            }
            return option;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return option;
    }

    /**
     * Returns the list with all the options registered to a particular Question.
     * @param questionId The Question whose options we want.
     * @return Returns a list with all the options
     */
    public List<Option> getQuestionOptions(Long questionId){
        List<Option> options = null;

        try{
            options = new ArrayList<>();
            Connection connection = dbConnector.getDBConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT ID, ENUNCIATION, CORRECTNESS FROM OPTIONS WHERE QUESTIONS_QUESTION_ID = ?");
            statement.setLong(1, questionId);

            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                Option option = new Option();
                option.setId(rs.getLong("ID"));
                option.setEnunciation(rs.getString("ENUNCIATION"));
                int correct = rs.getInt("CORRECTNESS");
                if(correct == 1)
                    option.setCorrectness(Boolean.TRUE);
                else
                    option.setCorrectness(Boolean.FALSE);
                option.setQuestionId(questionId);
                options.add(option);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return options;
    }

    /**
     * Inserts a new entry in the database with the attributes of `option`.
     * @param option The new question to be inserted in the database.
     * @return Returns the `id` of the new entry.
     */
    public Long createOption(Option option) {
        Long id = Long.valueOf(-1);

        try{
            Connection connection = dbConnector.getDBConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO OPTIONS(ENUNCIATION, CORRECTNESS, QUESTIONS_QUESTION_ID) VALUES (?,?,?)");
            statement.setString(1, option.getEnunciation());
            if(option.getCorrectness() == true)
                statement.setInt(2, 1);
            else
                statement.setInt(2, 0);
            statement.setLong(3, option.getQuestionId());


            statement.executeUpdate();
            return Long.valueOf(1);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return id;
    }

    /**
     * Updates an option in the database.
     * @param option A option object. It should contain the id of the original
     *                 and the fields that won't be updated should be the same as
     *                 in the original.
     * @return TRUE if the update succeeded, FALSE otherwise. An update could fail if there
     *         was nothing to update.
     */
    public  Boolean updateOption(Option option){
        try{
            Connection connection = dbConnector.getDBConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE OPTIONS SET "+
                                                        "ENUNCIATION = ?," +
                                                        "CORRECTNESS = ?," +
                                                        "QUESTIONS_QUESTION_ID = ? WHERE ID = ?");
            statement.setString(1, option.getEnunciation());
            if(option.getCorrectness())
                statement.setInt(2, 1);
            else
                statement.setInt(2, 0);
            statement.setLong(3, option.getQuestionId());
            statement.setLong(4, option.getId());

            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0)
                return Boolean.FALSE;
            return Boolean.TRUE;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }

    /**
     * Deletes an option from the database.
     * @param id The `id` of the targeted option.
     * @return TRUE if the deletion succeeded, FALSE otherwise.
     */
    public Boolean removeOption(Long id){
        try{
            Connection connection = dbConnector.getDBConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM OPTIONS WHERE ID = ?");
            statement.setLong(1, id);

            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0)
                return Boolean.FALSE;
            return Boolean.TRUE;

        } catch (SQLException e){
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }
}
