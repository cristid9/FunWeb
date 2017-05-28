package serviceResources;

import db.DBConnector;
import serviceRepresentations.GameCharacter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CharacterDAO {
    private DBConnector dbConnector;

    public CharacterDAO(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    /**
     * Inserts a new entry in characters table.
     * @param character The new character that will be inserted.
     * @return TRUE if action succeeded, FALSE otherwise.
     */
    public Boolean createCharacter(GameCharacter character) {
        // TODO: Needs testing.
        try {
            Connection connection = dbConnector.getDBConnection();
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO CHARACTERS VALUES (?, ?, ?)");

            statement.setString(1, character.getName());
            statement.setString( 2, character.getPicturePath());
            statement.setLong(3, character.getQuestionsNumber());

            int affectedRowss = statement.executeUpdate();

            if (affectedRowss == 0) {
                return  Boolean.FALSE; // not sure about this
            }
            return Boolean.TRUE;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Boolean.TRUE;
    }

}
