package serviceResources;

import db.DBConnector;
import serviceRepresentations.GameCharacter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    /**
     * Returns a new `GameCharacter` object representing the NPC at that row.
     * @param id The `id` of the NPC.
     * @return A `GameCharacter`.
     */
    public GameCharacter getCharacter(Long id) {
        GameCharacter gameCharacter = null;

        try {
            Connection connection = dbConnector.getDBConnection();
            PreparedStatement statement =
                    connection.prepareStatement("SELECT " +
                            "NAME, " +
                            "PICTURE_PATH, " +
                            "QUESTION_NUMBER FROM CHARACTERS WHERE ID = ?");
            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();
            gameCharacter = new GameCharacter();
            gameCharacter.setId(id);
            gameCharacter.setName(rs.getString("NAME"));
            gameCharacter.setPicturePath(rs.getString("PICTURE_PATH"));
            gameCharacter.setQuestionsNumber(rs.getLong("QUESTION_NUMBER"));

            return gameCharacter;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gameCharacter;
    }

}
