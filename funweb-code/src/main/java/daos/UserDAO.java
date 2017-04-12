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

            ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS \"ID\" FROM users");
            rs.next();

            int id = 0;

            if (rs != null) {
                id = rs.getInt("ID") + 1;
            }

            try {
                stmt.executeQuery("INSERT INTO users values " +
                        "(" + id + ", " +
                        "'" + user.getName() +  "', " +
                        "'"  + user.getUserRole() +  "', " +
                        "'" + user.getUserRole() + "', " +
                        "'" + user.getLoginType() + "', " +
                        user.getLevel() +   ", " +
                        user.getHintsLeft() + ", " +
                         user.getGoldLeft() + ", " +
                        "'/home')");

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

}