package serviceRepresentations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * POJO for a character in the game.
 */
@AllArgsConstructor
@Getter
@Setter
public class Character {
    public Long id;
    public String name;
    public String picturePath;
    /**
     * Number of questions for this NPC.
     */
    public Long questionsNumber;
}
