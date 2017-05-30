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
    public Long createChapter(LoginDataCustom entry){
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
}
