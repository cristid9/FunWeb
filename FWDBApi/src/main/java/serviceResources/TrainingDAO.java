package serviceResources;


import db.DBConnector;
import serviceRepresentations.Training;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainingDAO {
    private DBConnector connection;

    public TrainingDAO(DBConnector dbConnector) {
        this.connection = dbConnector;
    }

    /**
     * Inserts a new entry in the database with the attributes of `Training`.
     * @param entry The new Training object to be inserted in the database.
     * @return Returns the `id` of the new entry.
     */
    public Long createEntry(Training entry){
        Long id = Long.valueOf(-1);

        try {
            Connection conn = connection.getDBConnection();
            PreparedStatement statement =
                    conn.prepareStatement("INSERT INTO TRAINING(USER_NAME, SCORE) VALUES (?, ?)");

            statement.setString(1, entry.getUserName());
            statement.setInt(2, entry.getScore());

            statement.executeUpdate();

            return Long.valueOf(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * returns the avg score of a player with the name 'username'.
     * @param userName The name of the user
     * @return avg value scored in the db
     */

    public int getAvgScore(String userName){
        try{
            Connection conn = connection.getDBConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT NVL(AVG(SCORE), -1) FROM TRAINING WHERE USER_NAME = ?");
            statement.setString(1, userName);

            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
        return -1;
    }
}
