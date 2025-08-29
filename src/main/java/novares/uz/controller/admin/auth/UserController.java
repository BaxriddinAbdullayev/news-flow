package novares.uz.controller.admin.auth;

import lombok.AllArgsConstructor;
import novares.uz.controller.GenericCrudController;
import novares.uz.criteria.auth.UserCriteria;
import novares.uz.domain.auth.User;
import novares.uz.dto.AppResponse;
import novares.uz.dto.auth.user.UserCrudDto;
import novares.uz.dto.auth.user.UserDto;
import novares.uz.mapper.auth.UserMapper;
import novares.uz.service.auth.UserService;
import novares.uz.service.message.ResourceBundleService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "${app.api.admin-path}", produces = "application/json")
public class UserController implements GenericCrudController<UserDto, UserCrudDto, UserCriteria> {

    private final UserService service;
    private final UserMapper mapper;
    private final ResourceBundleService bundleService;

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<AppResponse<UserDto>> get(@PathVariable(value = "id") Long id) {
        String message = User.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("retrieved");
        return ResponseEntity.ok(AppResponse.success(mapper.toDto(service.get(id)), message));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<AppResponse<Page<UserDto>>> list(UserCriteria criteria) {
        String message = User.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("retrieved");
        return ResponseEntity.ok(AppResponse.success(service.list(criteria).map(mapper::toDto), message));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<AppResponse<UserDto>> create(@RequestBody UserCrudDto dto) {
        String message = User.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("created");
        return new ResponseEntity<>(AppResponse.success(mapper.toDto(service.create(dto)), message), HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/users/{id}", method = {RequestMethod.PUT, RequestMethod.POST})
    public ResponseEntity<AppResponse<UserDto>> edit(@PathVariable(value = "id") Long id, @RequestBody UserCrudDto dto) {
        String message = User.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("updated");
        return ResponseEntity.ok(AppResponse.success(mapper.toDto(service.update(id, dto)), message));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<AppResponse<Boolean>> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        String message = User.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("deleted");
        return ResponseEntity.ok(AppResponse.success(true, message));
    }
}
