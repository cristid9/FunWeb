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


    public String weakestChapter(int id){
        Connection conn;
        Statement stmt = null;

        try{
            conn = connection.getDBConnection();
            stmt = conn.createStatement();

            try{
                ResultSet rs = stmt.executeQuery("SELECT user_package.weakestChapter("+id+") as WEAKEST from dual");
                rs.next();

                String ret = rs.getString("WEAKEST");
                return ret;
            } catch (SQLException e) {
                e.printStackTrace();
            }

            conn.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
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

    public int checkPasswordStrengthness(String password){

        int returnValue = 0;
        Connection conn;
        Statement stmt = null;

        try {
            conn  = connection.getDBConnection();
            stmt = conn.createStatement();

            try {
                ResultSet queryResult = stmt.executeQuery("SELECT user_package.isWeak('"+password+"') as VERIFICAREPAROLA from dual");
                queryResult.next();

                returnValue = queryResult.getInt("VERIFICAREPAROLA");

                if(returnValue == 0){
                    //weak password -> red
                    System.out.println("red");
                }
                else if(returnValue == 1){
                    //medium password -> yellow
                    System.out.println("yellow");
                }
                else if(returnValue == 2){
                    //good password -> green
                    System.out.println("green");
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }
          
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return returnValue;
    }

    public boolean checkIfUserMatchesPassword(String username, String password) {
        User user = getUser(username);
        Connection conn;
        Statement stmt = null;

        try {
            conn = connection.getDBConnection();
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT password FROM logindatacustom WHERE user_id = '" + user.getId() + "'");


            if (rs == null) {
                return false; // the user doesn't even exists
            }

            rs.next();


            String retrievedPassword = rs.getString("password");

            if (retrievedPassword.equals(password)) {
                return true;
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }
}