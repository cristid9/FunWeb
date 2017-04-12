package daos;


import db.DBConnection;
import oracle.jdbc.pool.OracleConnectionCacheTimeOutThread;
import user.User;

import javax.xml.transform.Result;
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

    public User getUser(String name){
        User user = null;
        Connection conn;
        Statement stmt = null;

        try{
            conn = connection.getDBConnection();
            stmt = conn.createStatement();

            try{
                // todo : add level
                ResultSet rs = stmt.executeQuery("SELECT id, user_role, email, login_type, hints_left, gold_left, avatar_path from USERS where USERS.name ='" + name + "'");
                rs.next();
                long id = rs.getLong("id");
                String userRole = rs.getString("user_role");
                String email = rs.getString("email");
                String loginType = rs.getString("login_type");
               // int level = rs.getInt("level");
                int hintsLeft = rs.getInt("hints_left");
                int goldLeft = rs.getInt("gold_left");
                String avatarPath = rs.getString("avatar_path");

                user = new User(id, name, userRole, email, loginType, 0, hintsLeft, goldLeft, avatarPath);

            } catch (SQLException e){
                e.printStackTrace();
            }
            conn.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
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

<<<<<<< HEAD

=======
>>>>>>> 1889d9ffde0aa4bf7080b8bc6de3cafca4116629
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
<<<<<<< HEAD
=======

>>>>>>> 1889d9ffde0aa4bf7080b8bc6de3cafca4116629
        return true;
    }

    public boolean removeUser(User user){
        Connection conn;
        Statement stmt = null;

        try{
            conn = connection.getDBConnection();
            stmt = conn.createStatement();

            try{
                stmt.executeQuery("delete from users where id = " + user.getId() + ";");
            } catch (SQLException e){
                e.printStackTrace();
            }

            conn.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

}