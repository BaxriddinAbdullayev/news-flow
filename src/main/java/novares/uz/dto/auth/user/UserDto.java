package novares.uz.dto.auth.user;

import lombok.Getter;
import lombok.Setter;
import novares.uz.dto.GenericDto;
import novares.uz.dto.auth.role.RoleDto;
import novares.uz.enums.GeneralStatus;

import java.util.Set;

@Getter
@Setter
public class UserDto extends GenericDto {

    private String fullName;
    private String username;
    private String password;
    private GeneralStatus status;
    private Set<RoleDto> roles;
    private boolean active;
}
