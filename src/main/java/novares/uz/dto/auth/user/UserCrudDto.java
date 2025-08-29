package novares.uz.dto.auth.user;

import lombok.Getter;
import lombok.Setter;
import novares.uz.dto.GenericCrudDto;
import novares.uz.dto.auth.role.RoleDto;
import novares.uz.enums.GeneralStatus;

import java.util.Set;

@Getter
@Setter
public class UserCrudDto extends GenericCrudDto {

    private String fullName;
    private String username;
    private String password;
    private GeneralStatus status;
    protected Set<RoleDto> roles;
}
