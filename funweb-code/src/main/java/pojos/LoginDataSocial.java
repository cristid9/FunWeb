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
@Getter
@Setter
public class LoginDataSocial {
    public Long id; // TODO: the `id` field should be added in the database
    public String authHash;
    public Long userId;
}
