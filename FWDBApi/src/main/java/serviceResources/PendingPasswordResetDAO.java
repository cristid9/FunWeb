package serviceResources;

import db.DBConnector;
import serviceRepresentations.Option;
import serviceRepresentations.PendingPasswordReset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PendingPasswordResetDAO {
    DBConnector dbConnector;

    public PendingPasswordResetDAO(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    /**
     * Returns a new entry populated with the associated values in
     * the database.
     * @param token It is guaranteed to be unique.
     */
    public PendingPasswordReset getEntry(String token){
        PendingPasswordReset entry = null;

        try {
            Connection connection = dbConnector.getDBConnection();

            PreparedStatement statement =
                    connection.prepareStatement("SELECT ID, " +
                            "USERNAME " +
                            "FROM PendingPasswordReset where TOKEN = ?");
            statement.setString(1, token);
            ResultSet rs = statement.executeQuery();

//            rs.next();
            if(rs.next()) {
                entry = new PendingPasswordReset();
                entry.setId(rs.getLong("ID"));
                entry.setToken(token);
                entry.setUsername(rs.getString("USERNAME"));
            }

            return entry;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entry;
    }

    /**
     * Inserts a pending password request in to the db.
     */
    public Long createEntry(PendingPasswordReset pendingPasswordReset){

        try {
            Connection connection = dbConnector.getDBConnection();

            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO PENDINGPASSWORDRESET VALUES (?, ?, ? )");
            statement.setLong(1, 0l); // Dummy
            statement.setString(2, pendingPasswordReset.getToken());
            statement.setString(3, pendingPasswordReset.getUsername());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                return Long.valueOf(-1);
            }

            return Long.valueOf(affectedRows);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Long.valueOf(-1);
    }

    /**
     * Deletes the entry with that `id` from the database.
     */
    public Boolean deleteEntry(Long id) {

        try {
            Connection connection = dbConnector.getDBConnection();

            PreparedStatement statement =
                    connection.prepareStatement("DELETE FROM PENDINGPASSWORDRESET WHERE ID = ?");
            statement.setLong(1, id);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                return Boolean.FALSE;
            }

            return Boolean.TRUE;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Boolean.FALSE;
    }
}
