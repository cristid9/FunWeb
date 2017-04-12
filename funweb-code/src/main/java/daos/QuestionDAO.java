package daos;

import db.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        Connection conn;
        Statement stmt = null;

        try {
            conn  = dbConnection.getDBConnection();
            stmt = conn.createStatement();

            try {
                ResultSet rs = stmt.executeQuery("select questions_package.isRelevant('"+ id + "') as RELEV from dual");
                rs.next();

                if(rs.getInt("RELEV") == 0){
                    return false;
                }else{
                    return true;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return true;
    }

}
