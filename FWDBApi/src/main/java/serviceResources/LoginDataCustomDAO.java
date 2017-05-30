package serviceResources;

import db.DBConnector;
import serviceRepresentations.Chapter;
import serviceRepresentations.LoginDataCustom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDataCustomDAO {

    DBConnector dbConnector;

    public LoginDataCustomDAO(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    /**
     * Returns a new entry from the custom login table.
     * @param id The `id` of the desired entry.
     * @return The entry at the id `id`.
     */
    public LoginDataCustom getEntry(Long id) {
        LoginDataCustom entry = null;
        try {
            entry = new LoginDataCustom();
            Connection connection = dbConnector.getDBConnection();

            PreparedStatement statement =
                    connection.prepareStatement("SELECT " +
                            "USER_ID, " +
                            "PASSWORD FROM LOGINDATACUSTOM WHERE ID = ?");
            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();
            entry.setId(id);
            entry.setPassword(rs.getString("AUTH_HASH"));
            entry.setUserId(rs.getLong("USER_ID"));

            return entry;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entry;
    }

    /**
     * Inserts a new entry in the database with the attributes of `LoginDataCustom`.
     * @param entry The new chapter to be inserted in the database.
     * @return Returns the `id` of the new entry.
     */
    public Long createEntry(LoginDataCustom entry){
        Long id = Long.valueOf(-1);

        try {
            Connection connection = dbConnector.getDBConnection();
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO LoginDataCustom VALUES (?, ?)");

            statement.setLong(1, entry.getUserId());
            statement.setString(2, entry.getPassword());

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            return Long.valueOf(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }


    /**
     * Updates an entry in the database.
     * @param entry A `LoginDataCustom` object. It should contain the id of the original
     *                 and the fields that won't be updated should be the same as
     *                 in the original.
     * @return TRUE if the update succeeded, FALSE otherwise. An update could fail if there
     *         was nothing to update.
     */
    public boolean updateEntry(LoginDataCustom entry){

        try {
            Connection connection = dbConnector.getDBConnection();
            PreparedStatement statement =
                    connection.prepareStatement("UPDATE LoginDataSocial SET " +
                            "USER_ID = ?," +
                            "PASSWORD = ? WHERE ID = ?");


            statement.setLong(1, entry.getUserId());
            statement.setString(1, entry.getPassword());
            statement.setLong(3, entry.getId());

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
     * @param id The `id` of the targeted entry.
     * @return TRUE if the deletion succeeded, FALSE otherwise.
     */
    public boolean removeEntry(Long id){
        try {
            Connection connection = dbConnector.getDBConnection();
            PreparedStatement statement =
                    connection.prepareStatement("DELETE FROM LoginDataSocial WHERE ID = ?");
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
