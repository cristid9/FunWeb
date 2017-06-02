package serviceResources;

import db.DBConnector;
import serviceRepresentations.GameCharacter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                    connection.prepareStatement("INSERT INTO CHARACTERS VALUES (?, ?, ?, ?)");

            statement.setLong(1,character.getId());
            statement.setString(2, character.getName());
            statement.setString( 3, character.getPicturePath());
            statement.setLong(4, character.getQuestionsNumber());

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

            rs.next();

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

    /**
     * Removes the NPC with the id `id` from the database.
     * @param id The `id` of the targeted NPC.
     * @return TRUE if success, FALSE otherwise.
     */
    public Boolean removeCharacter(Long id) {
        try {
            Connection connection = dbConnector.getDBConnection();
            PreparedStatement statement =
                    connection.prepareStatement("DELETE FROM CHARACTERS WHERE ID = ?");
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

    /**
     * Updates a character in the database.
     * @param character A character object. It should contain the id of the original
     *                 and the fields that won't be updated should be the same as
     *                 in the original.
     * @return TRUE if the update succeeded, FALSE otherwise. An update could fail if there
     *         was nothing to update.
     */
    public Boolean updateCharacter(GameCharacter character) {

        try {
            Connection connection = dbConnector.getDBConnection();
            PreparedStatement statement =
                    connection.prepareStatement("UPDATE CHARACTERS SET " +
                            "NAME = ?," +
                            "PICTURE_PATH = ?," +
                            "QUESTION_NUMBER = ? " +
                            " WHERE ID = ?");

            statement.setString(1, character.getName());
            statement.setString(2, character.getPicturePath());
            statement.setLong(3, character.getQuestionsNumber());
            statement.setLong(4,character.getId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0)
                return Boolean.FALSE;

            return Boolean.TRUE;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Spaghetti code ftw!
        return Boolean.FALSE;
    }


    /**
     * @return Returns a list with all the questions.
     */
    public List<GameCharacter> getCharacters() {

        // TODO: Security and edge cases.

        List<GameCharacter> characters = null;

        try {

            characters = new ArrayList<>();

            Connection connection = dbConnector.getDBConnection();
            PreparedStatement statement =
                    connection.prepareStatement("SELECT ID, " +
                            "NAME, " +
                            "PICTURE_PATH, " +
                            "QUESTION_NUMBER FROM CHARACTERS");

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                GameCharacter character = new GameCharacter();

                character.setId(rs.getLong("ID"));
                character.setName(rs.getString("NAME"));
                character.setPicturePath(rs.getString("PICTURE_PATH"));
                character.setQuestionsNumber(rs.getLong("QUESTION_NUMBER"));

                characters.add(character);

                rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return characters;
    }


}
