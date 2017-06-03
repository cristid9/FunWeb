package pojos;

/**
 * Created by Marius on 6/3/2017.
 */
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LoginDataCustom {
    public Long id; // TODO: add this field
    public Long userId;
    public String password;
}
