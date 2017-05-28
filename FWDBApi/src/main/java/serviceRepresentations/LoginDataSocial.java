package serviceRepresentations;

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
