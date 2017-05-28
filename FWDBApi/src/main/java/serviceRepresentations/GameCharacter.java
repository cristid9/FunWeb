package serviceRepresentations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * POJO for a character in the game.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GameCharacter {
    public Long id;
    public String name;
    public String picturePath;
    /**
     * Number of questions for this NPC.
     */
    public Long questionsNumber;
}
