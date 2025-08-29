package novares.uz.controller.admin.tag;

import lombok.AllArgsConstructor;
import novares.uz.controller.GenericCrudController;
import novares.uz.criteria.tag.TagCriteria;
import novares.uz.domain.tag.Tag;
import novares.uz.dto.AppResponse;
import novares.uz.dto.tag.TagCrudDto;
import novares.uz.dto.tag.TagDto;
import novares.uz.mapper.tag.TagMapper;
import novares.uz.service.message.ResourceBundleService;
import novares.uz.service.tag.TagService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "${app.api.admin-path}", produces = "application/json")
public class TagController implements GenericCrudController<TagDto, TagCrudDto, TagCriteria> {

    private final TagService service;
    private final TagMapper mapper;
    private final ResourceBundleService bundleService;

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/tags/{id}", method = RequestMethod.GET)
    public ResponseEntity<AppResponse<TagDto>> get(@PathVariable(value = "id") Long id) {
        String message = Tag.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("retrieved");
        return ResponseEntity.ok(AppResponse.success(mapper.toDto(service.get(id)), message));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public ResponseEntity<AppResponse<Page<TagDto>>> list(TagCriteria criteria) {
        String message = Tag.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("retrieved");
        return ResponseEntity.ok(AppResponse.success(service.list(criteria).map(mapper::toDto), message));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/tags", method = RequestMethod.POST)
    public ResponseEntity<AppResponse<TagDto>> create(@RequestBody TagCrudDto dto) {
        String message = Tag.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("created");
        return new ResponseEntity<>(AppResponse.success(mapper.toDto(service.create(dto)), message), HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/tags/{id}", method = {RequestMethod.PUT, RequestMethod.POST})
    public ResponseEntity<AppResponse<TagDto>> edit(@PathVariable(value = "id") Long id, @RequestBody TagCrudDto dto) {
        String message = Tag.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("updated");
        return ResponseEntity.ok(AppResponse.success(mapper.toDto(service.update(id, dto)), message));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/tags/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<AppResponse<Boolean>> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        String message = Tag.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("deleted");
        return ResponseEntity.ok(AppResponse.success(true, message));
    }
}
