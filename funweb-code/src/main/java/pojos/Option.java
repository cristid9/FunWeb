package pojos;

/**
 * Created by Marius on 6/3/2017.
 */
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * POJO for a question's option.
 */
@AllArgsConstructor
@NoArgsConstructor
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
