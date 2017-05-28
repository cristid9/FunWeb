package serviceResources;


import db.DBConnector;
import serviceRepresentations.Chapter;
import serviceRepresentations.LoginDataSocial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginDataSocialDAO {

    private DBConnector dbConnector;

    public LoginDataSocialDAO(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    /**
     * Returns a new entry from the social login table.
     * @param id The `id` of the desired entry.
     * @return The entry at the id `id`.
     */
    public LoginDataSocial getEntry(Long id) {
        LoginDataSocial entry = null;
        try {
            entry = new LoginDataSocial();
            Connection connection = dbConnector.getDBConnection();

            PreparedStatement statement =
                    connection.prepareStatement("SELECT " +
                            "USER_ID, " +
                            "AUTH_HASH FROM LOGIN_DATA_SOCIAL WHERE ID = ?");
            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();
            entry.setId(id);
            entry.setAuthHash(rs.getString("AUTH_HASH"));
            entry.setUserId(rs.getLong("USER_ID"));

            return entry;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entry;
    }

    /**
     * Inserts a new entry in the database with the attributes of `LoginDataSocial`.
     * @param entry The new entry to be inserted in the database.
     * @return Returns the `id` of the new entry.
     */
    public Long createEntry(LoginDataSocial entry){
        Long id = Long.valueOf(-1);

        try {
            Connection connection = dbConnector.getDBConnection();
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO LOGIN_DATA_SOCIAL VALUES (?, ?)");
            statement.setString(1, entry.getAuthHash());
            statement.setLong(2, entry.getUserId());

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            return Long.valueOf(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * Updates a `LoginDataSocial` entry in the database.
     * @param entry A `LoginDataSocial` object. It should contain the id of the original
     *                 and the fields that won't be updated should be the same as
     *                 in the original.
     * @return TRUE if the update succeeded, FALSE otherwise. An update could fail if there
     *         was nothing to update.
     */
    public Boolean updateEntry(LoginDataSocial entry){

        try {
            Connection connection = dbConnector.getDBConnection();
            PreparedStatement statement =
                    connection.prepareStatement("UPDATE LOGIN_DATA_SOCIAL " +
                            "SET AUTH_HASH = ?, USER_ID = ? WHERE ID = ?");

            statement.setString(1, entry.getAuthHash());
            statement.setLong(2, entry.getUserId());
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
     * Returns the list with all the entries for a particular user.
     * @param userId The id of the user whose questions we want.
     * @return Returns a list with all the questions.
     */
    public List<LoginDataSocial> getAllEntries(Long userId) {
        List<LoginDataSocial> entries = null;

        try {
            entries = new ArrayList<>();

            Connection connection = dbConnector.getDBConnection();
            PreparedStatement statement =
                    connection.prepareStatement("SELECT ID," +
                            " AUTH_HASH FROM LOGIN_DATA_SOCIAL WHERE USER_ID = ?");

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                LoginDataSocial entry = new LoginDataSocial();

                entry.setId(rs.getLong("ID"));
                entry.setAuthHash(rs.getString("AUTH_HASH"));
                entry.setUserId(userId);

                entries.add(entry);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return entries;
    }

    /**
     * Deletes an entry from the database.
     * @param id The `id` of the targeted chapter.
     * @return TRUE if the deletion succeeded, FALSE otherwise.
     */
    public Boolean removeEntry(Long id){
        try {
            Connection connection = dbConnector.getDBConnection();
            PreparedStatement statement =
                    connection.prepareStatement("DELETE FROM LOGIN_DATA_SOCIAL WHERE ID = ?");
            statement.setLong(1, id);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0)
                return Boolean.FALSE;
            return Boolean.TRUE;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }
}
