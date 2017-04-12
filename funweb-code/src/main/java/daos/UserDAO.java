package daos;


import db.DBConnection;
import user.User;

import java.sql.Connection;
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

    public boolean updateUser(User user){
        return true;
    }

    public boolean removeUser(User user){
        return true;
    }

}