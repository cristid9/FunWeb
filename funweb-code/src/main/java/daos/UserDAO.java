package daos;


import db.DBConnection;
import user.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {

    //private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    // private JdbcTemplate jdbcTemplate;
    //private SimpleJdbcInsert insert;

    // add crud methods here
    private DBConnection connection;
    public UserDAO(DBConnection connection){
        this.connection = connection;
    }

    public User getUser(Long id){

       return null;
    }

    public boolean createUser(User user){
        Connection conn;
        Statement stmt = null;

        try {
            conn  = connection.getDBConnection();
            stmt = conn.createStatement();

            try {
                stmt.executeQuery("INSERT INTO users values (15001, 'vasile', 'user', 'mail', 'normal', 1, 10, 10, '/home')");
            } catch (SQLException e) {
                e.printStackTrace();
            }


            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean updateUserPassword(User user , String newPassword){
        Connection conn;
        Statement stmt = null;

        try {
            conn  = connection.getDBConnection();
            stmt = conn.createStatement();

            try {
                stmt.executeQuery("UPDATE LoginDataCustom SET password=" + newPassword + "where LoginDataCustom.user_id ="+user.getId() + ";");
            } catch (SQLException e) {
                e.printStackTrace();
            }


            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean removeUser(User user){
        return true;
    }

    public String checkIfValidUsername(String username){


        String suggestion = "";

        Connection conn;
        Statement stmt = null;

        try {
            conn  = connection.getDBConnection();
            stmt = conn.createStatement();

            try {
               ResultSet queryResult = stmt.executeQuery("SELECT user_package.existsUsername('"+username+"') as EXISTA from dual");
               queryResult.next();

               int exists = queryResult.getInt("EXISTA");

               if(exists == 0)
                   return null;

               else if (exists == 1)
               {
                    ResultSet newUsername = stmt.executeQuery("SELECT user_package.generateSuggestion('"+username+"') as SUGESTIE from dual");
                    newUsername.next();

                    suggestion = newUsername.getString("SUGESTIE");
               }


            } catch (SQLException e) {
                e.printStackTrace();
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return suggestion;
    }

}