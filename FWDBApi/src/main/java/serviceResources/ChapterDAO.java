package serviceResources;

import db.DBConnector;
import serviceRepresentations.Chapter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChapterDAO {
    private DBConnector dbConnector;

    public ChapterDAO(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    /**
     * Returns a new Chapter object populated with the associated values in db
     * @param id The Chapter it's selected by it's id
     * @return  the chapter with the id `id`
     */
    public Chapter getChapter(Long id) {
        Chapter chapter = null;
        try {
            chapter = new Chapter();
            Connection connection = dbConnector.getDBConnection();

            PreparedStatement statement = connection.prepareStatement("SELECT CHAPTER_NAME from CHAPTERS where ID = ?");
            statement.setInt(1, id.intValue());

            ResultSet rs = statement.executeQuery();

            if (!rs.next()) {
                return chapter;
            }

            chapter.setId(id);
            chapter.setChapterName(rs.getString("CHAPTER_NAME"));

            return chapter;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chapter;
    }

    /**
     * Inserts a new entry in the database with the attributes of `chapter`.
     * @param chapter The new chapter to be inserted in the database.
     * @return Returns the `id` of the new entry.
     */
    public Long createChapter(Chapter chapter){
        Long id = Long.valueOf(-1);

        try {
            Connection connection = dbConnector.getDBConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO CHAPTERS VALUES (?, ?)");
            statement.setInt(1, 0); // dummy index
            statement.setString(2, chapter.getChapterName());

            return Long.valueOf(statement.executeUpdate());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * Updates a question in the database.
     * @param chapter A chapter object. It should contain the id of the original
     *                 and the fields that won't be updated should be the same as
     *                 in the original.
     * @return TRUE if the update succeeded, FALSE otherwise. An update could fail if there
     *         was nothing to update.
     */
    public boolean updateChapter(Chapter chapter){

        try{
            Connection connection = dbConnector.getDBConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE CHAPTERS SET CHAPTER_NAME = ? WHERE ID = ?");

            statement.setString(1, chapter.getChapterName());
            statement.setLong(2, chapter.getId());

            int affectedRows = statement.executeUpdate();

            if(affectedRows == 0)
                return Boolean.FALSE;
            return Boolean.TRUE;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Boolean.FALSE;
    }

    /**
     * Deletes a chapter from the database.
     * @param id The `id` of the targeted chapter.
     * @return TRUE if the deletion succeeded, FALSE otherwise.
     */

    public boolean removeChapter(Long id){
        try{
            Connection connection = dbConnector.getDBConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM CHAPTERS WHERE ID=?");
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

    /**
     * Returns the list with all the chapters
     * @return Returns a list with all the questions.
     */
    public List<Chapter> getAllChapters(){
        List<Chapter> chapters = null;

        try{
            chapters = new ArrayList<>();

            Connection connection = dbConnector.getDBConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT ID, CHAPTER_NAME FROM CHAPTERS");

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Chapter chapter = new Chapter();
                chapter.setId(rs.getLong("ID"));
                chapter.setChapterName(rs.getString("CHAPTER_NAME"));
                chapters.add(chapter);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return chapters;
    }
}
