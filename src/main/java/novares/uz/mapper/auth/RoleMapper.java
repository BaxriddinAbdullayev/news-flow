package novares.uz.mapper.auth;

import novares.uz.domain.auth.Role;
import novares.uz.dto.auth.role.RoleCrudDto;
import novares.uz.dto.auth.role.RoleDto;
import novares.uz.mapper.BaseCrudMapper;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper extends BaseCrudMapper<Role, RoleDto, RoleCrudDto> {

    @Named("fullToDto")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "roleName", source = "roleName")
    @Mapping(target = "displayName", source = "displayName")
    @BeanMapping(ignoreByDefault = true)
    RoleDto fullToDto(Role debt);

}
