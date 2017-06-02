package serviceRepresentations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * POJO for a chapter. A chapter is a logical collection of question.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Chapter {
    public Long id;
    public String chapterName;
}
