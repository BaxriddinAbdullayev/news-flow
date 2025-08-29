package novares.uz.controller.admin.category;

import lombok.AllArgsConstructor;
import novares.uz.controller.GenericCrudController;
import novares.uz.criteria.category.CategoryTranslationCriteria;
import novares.uz.domain.category.CategoryTranslation;
import novares.uz.dto.AppResponse;
import novares.uz.dto.category.CategoryTranslationCrudDto;
import novares.uz.dto.category.CategoryTranslationDto;
import novares.uz.mapper.category.CategoryTranslationMapper;
import novares.uz.service.category.CategoryTranslationService;
import novares.uz.service.message.ResourceBundleService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "${app.api.admin-path}", produces = "application/json")
public class CategoryTranslationController implements GenericCrudController<CategoryTranslationDto, CategoryTranslationCrudDto, CategoryTranslationCriteria> {

    private final CategoryTranslationService service;
    private final CategoryTranslationMapper mapper;
    private final ResourceBundleService bundleService;

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/category-translations/{id}", method = RequestMethod.GET)
    public ResponseEntity<AppResponse<CategoryTranslationDto>> get(@PathVariable(value = "id") Long id) {
        String message = CategoryTranslation.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("retrieved");
        return ResponseEntity.ok(AppResponse.success(mapper.toDto(service.get(id)), message));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/category-translations", method = RequestMethod.GET)
    public ResponseEntity<AppResponse<Page<CategoryTranslationDto>>> list(CategoryTranslationCriteria criteria) {
        String message = CategoryTranslation.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("retrieved");
        return ResponseEntity.ok(AppResponse.success(service.list(criteria).map(mapper::toDto), message));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/category-translations", method = RequestMethod.POST)
    public ResponseEntity<AppResponse<CategoryTranslationDto>> create(@RequestBody CategoryTranslationCrudDto dto) {
        String message = CategoryTranslation.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("created");
        return new ResponseEntity<>(AppResponse.success(mapper.toDto(service.create(dto)), message), HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/category-translations/{id}", method = {RequestMethod.PUT, RequestMethod.POST})
    public ResponseEntity<AppResponse<CategoryTranslationDto>> edit(@PathVariable(value = "id") Long id, @RequestBody CategoryTranslationCrudDto dto) {
        String message = CategoryTranslation.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("updated");
        return ResponseEntity.ok(AppResponse.success(mapper.toDto(service.update(id, dto)), message));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/category-translations/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<AppResponse<Boolean>> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        String message = CategoryTranslation.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("deleted");
        return ResponseEntity.ok(AppResponse.success(true, message));
    }
}
