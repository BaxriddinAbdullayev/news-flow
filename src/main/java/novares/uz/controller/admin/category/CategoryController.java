package novares.uz.controller.admin.category;

import lombok.AllArgsConstructor;
import novares.uz.controller.GenericCrudController;
import novares.uz.criteria.category.CategoryCriteria;
import novares.uz.domain.category.Category;
import novares.uz.dto.AppResponse;
import novares.uz.dto.category.CategoryCrudDto;
import novares.uz.dto.category.CategoryDto;
import novares.uz.mapper.category.CategoryMapper;
import novares.uz.service.category.CategoryService;
import novares.uz.service.message.ResourceBundleService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "${app.api.admin-path}", produces = "application/json")
public class CategoryController implements GenericCrudController<CategoryDto, CategoryCrudDto, CategoryCriteria> {

    private final CategoryService service;
    private final CategoryMapper mapper;
    private final ResourceBundleService bundleService;

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
    public ResponseEntity<AppResponse<CategoryDto>> get(@PathVariable(value = "id") Long id) {
        String message = Category.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("retrieved");
        return ResponseEntity.ok(AppResponse.success(service.getCategoryWithTranslation(id), message));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ResponseEntity<AppResponse<Page<CategoryDto>>> list(CategoryCriteria criteria) {
        String message = Category.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("retrieved");
        return ResponseEntity.ok(AppResponse.success(service.listCategoryWithTranslation(criteria), message));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    public ResponseEntity<AppResponse<CategoryDto>> create(@RequestBody CategoryCrudDto dto) {
        String message = Category.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("created");
        return new ResponseEntity<>(AppResponse.success(mapper.toDto(service.create(dto)), message), HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/categories/{id}", method = {RequestMethod.PUT, RequestMethod.POST})
    public ResponseEntity<AppResponse<CategoryDto>> edit(@PathVariable(value = "id") Long id, @RequestBody CategoryCrudDto dto) {
        String message = Category.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("updated");
        return ResponseEntity.ok(AppResponse.success(mapper.toDto(service.update(id, dto)), message));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/categories/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<AppResponse<Boolean>> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        String message = Category.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("deleted");
        return ResponseEntity.ok(AppResponse.success(true, message));
    }
}
