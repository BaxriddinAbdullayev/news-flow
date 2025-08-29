package novares.uz.dto.auth.role;

import lombok.Getter;
import lombok.Setter;
import novares.uz.dto.GenericCrudDto;

@Getter
@Setter
public class RoleCrudDto extends GenericCrudDto {

    private String roleName;
    private String displayName;
}
