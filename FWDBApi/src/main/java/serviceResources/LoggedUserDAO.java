package serviceResources;

import db.DBConnector;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import serviceRepresentations.LoggedUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bogdanel on 06.06.2017.
 */
public class LoggedUserDAO {
    private DBConnector connection;
    public LoggedUserDAO(DBConnector dbConnector){
        this.connection = dbConnector;
    }

    /**
     * Returns a new `LoggedUser` object populated with the associated values in db.
     * @param name The `LoggedUser` it's selected by user's name
     * @return  The LoggedUser with the name specified.
     */

    public LoggedUser getLoggedUser(String name){
        LoggedUser entry = null;
        try {
            Connection conn = connection.getDBConnection();

            PreparedStatement statement =
                    conn.prepareStatement("SELECT " +
                            "ID, " +
                            "USER_NAME FROM LOGGED_USERS WHERE USER_NAME = ?");
            statement.setString(1, name);

            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                entry = new LoggedUser();
                entry.setId(rs.getLong(1));
                entry.setUserName(name);
            }
            return entry;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entry;
    }

    /**
     * Inserts a new entry in the database with the attributes of `LoggedUser`.
     * @param entry The new loggedUser to be inserted in the database.
     * @return Returns the `id` of the new entry.
     */
    public Long createEntry(LoggedUser entry){
        Long id = Long.valueOf(-1);

        try {
            Connection conn = connection.getDBConnection();
            PreparedStatement statement =
                    conn.prepareStatement("INSERT INTO LOGGED_USERS(USER_NAME) VALUES (?)");

            statement.setString(1, entry.getUserName());

            statement.executeUpdate();

            return Long.valueOf(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * Updates an entry in the database.
     * @param entry A `LoggedUser' object. It should contain the id of the original
     *                 and the fields that won't be updated should be the same as
     *                 in the original.
     * @return TRUE if the update succeeded, FALSE otherwise. An update could fail if there
     *         was nothing to update.
     */
    public boolean updateEntry(LoggedUser entry){

        try {
            Connection conn = connection.getDBConnection();
            PreparedStatement statement =
                    conn.prepareStatement("UPDATE LOGGED_USERS SET " +
                            "USER_NAME = ? WHERE ID = ?");

            statement.setString(1, entry.getUserName());
            statement.setLong(2, entry.getId());

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
     * Deletes an entry from the database.
     * @param name The `name` of the targeted entry.
     * @return TRUE if the deletion succeeded, FALSE otherwise.
     */
    public boolean removeEntry(String name){
        try {
            Connection conn = connection.getDBConnection();
            PreparedStatement statement =
                    conn.prepareStatement("DELETE FROM LOGGED_USERS WHERE USER_NAME = ?");
            statement.setString(1, name);

            int affectedRows = statement.executeUpdate();

            if(affectedRows == 0)
                return Boolean.FALSE;
            return Boolean.TRUE;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }

    /**
     * @return Returns a list with all the entries from this table.
     */
    public List<LoggedUser> getAllEntries() {
        List<LoggedUser> entries = null;

        try {
            entries = new ArrayList<>();

            Connection conn = connection.getDBConnection();
            PreparedStatement statement =
                    conn.prepareStatement("SELECT ID, USER_NAME FROM LOGGED_USERS");

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                LoggedUser entry = new LoggedUser();

                entry.setId(rs.getLong("ID"));
                entry.setUserName(rs.getString("USER_NAME"));
                entries.add(entry);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return entries;
    }

}
