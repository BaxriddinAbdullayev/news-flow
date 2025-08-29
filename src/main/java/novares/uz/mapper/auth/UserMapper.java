package novares.uz.mapper.auth;

import novares.uz.domain.auth.User;
import novares.uz.dto.auth.user.UserCrudDto;
import novares.uz.dto.auth.user.UserDto;
import novares.uz.mapper.BaseCrudMapper;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {
        RoleMapper.class
})
public interface UserMapper extends BaseCrudMapper<User, UserDto, UserCrudDto> {

    @Named("fullToDto")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "active", target = "active")
    @Mapping(source = "roles", target = "roles", qualifiedByName = "fullToDto")
    @BeanMapping(ignoreByDefault = true)
    UserDto fullToDto(User news);
}
