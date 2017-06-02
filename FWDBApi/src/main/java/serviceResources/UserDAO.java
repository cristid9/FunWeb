package serviceResources;


import db.DBConnector;
import serviceRepresentations.User;


import java.sql.*;
import java.util.ArrayList;

public class UserDAO {

    private DBConnector connection;
    public UserDAO(DBConnector connection){
        this.connection = connection;
    }

    // needs refactoring
    public void registerPassword(Long id, String password) {
        Connection conn;
        Statement stmt = null;

        try {
            conn = connection.getDBConnection();

            stmt = conn.createStatement();

            stmt.executeQuery("INSERT INTO logindatacustom VALUES('" + password + "', " + id  + ")");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUser(String name){
        User user = null;
        Connection conn;
        Statement stmt = null;

        try {
            conn = connection.getDBConnection();
            stmt = conn.createStatement();

            try {
                // todo : add level
                ResultSet rs = stmt.executeQuery("SELECT id, user_role, email, login_type, hints_left, gold_left, avatar_path from USERS where USERS.name ='" + name.replace("'", "\\'") + "'");
                rs.next();
                long id = rs.getLong("id");
                String userRole = rs.getString("user_role");
                String email = rs.getString("email");
                String loginType = rs.getString("login_type");

                int hintsLeft = rs.getInt("hints_left");
                int goldLeft = rs.getInt("gold_left");
                String avatarPath = rs.getString("avatar_path");

                user = new User(id, name, userRole, email, loginType, 0, hintsLeft, goldLeft, avatarPath);

            } catch (SQLException e){
                e.printStackTrace();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public int createUser(User user){
        Connection conn;
        try {

            conn  = connection.getDBConnection();
            PreparedStatement preparedStatement =
                    conn.prepareStatement("INSERT INTO users values (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            preparedStatement.setInt(1, 0);
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getUserRole());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getLoginType());
            preparedStatement.setInt(6, user.getHintsLeft());
            preparedStatement.setInt(7, user.getGoldLeft());
            preparedStatement.setString(8, user.getAvatarPath());
            preparedStatement.setInt(9, user.getLevel());

            try {
                return preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // replace with an exception
    }

    // needs refactoring
    public boolean updateUserPassword(User user , String newPassword){
        Connection conn;
        Statement stmt = null;

        try {
            conn  = connection.getDBConnection();
            stmt = conn.createStatement();

            try {
                stmt.executeQuery("UPDATE LoginDataCustom SET password='" + newPassword + "' where LoginDataCustom.user_id ="+user.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }


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
                stmt.executeQuery("delete from users where id = " + user.getId());
            } catch (SQLException e){
                e.printStackTrace();
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    public String weakestChapter(Long id){
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

            } catch (SQLException e) {
                e.printStackTrace();
            }

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

            ResultSet rs = stmt.executeQuery("select count(password) as valid from logindatacustom where user_id= " + user.getId() + " and password = '" + password + "'");


            if (rs == null) {
                return false; // the user doesn't even exists
            }

            rs.next();


            Integer matched = rs.getInt("valid");

            if (matched == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }

    public ArrayList<String> getAllUsers() {
        ArrayList<String> users = new ArrayList<String>();

        Connection conn;
        Statement stmt = null;

        try {
            conn = connection.getDBConnection();
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT name FROM users");

            while(rs.next()){
                String user = rs.getString("name");
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}