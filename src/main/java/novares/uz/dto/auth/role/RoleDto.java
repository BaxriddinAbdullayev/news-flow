package novares.uz.dto.auth.role;

import lombok.Getter;
import lombok.Setter;
import novares.uz.dto.GenericDto;

@Getter
@Setter
public class RoleDto extends GenericDto {

    private String roleName;
    private String displayName;
}
