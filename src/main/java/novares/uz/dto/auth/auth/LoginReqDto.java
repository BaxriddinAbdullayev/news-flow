package novares.uz.dto.auth.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginReqDto {

    @NotBlank(message = "Email or phone required")
    private String username;

    @NotBlank(message = "Password required")
    private String password;
}
