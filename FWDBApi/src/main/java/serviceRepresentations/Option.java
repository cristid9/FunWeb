package serviceRepresentations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * POJO for a question's option.
 */
@AllArgsConstructor
@Getter
@Setter
public class Option {
    public Long id;
    public String enunciation;
    /**
     * Refers to the truth value of this question.
     */
    public Boolean correctness;
    public Long questionId;
}
