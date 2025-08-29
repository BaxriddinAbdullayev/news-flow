package novares.uz.dto.auth.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResDto {

    private String accessToken;
    private String refreshToken;
    private long expiresIn;
}
