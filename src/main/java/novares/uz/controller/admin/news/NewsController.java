package novares.uz.controller.admin.news;

import lombok.AllArgsConstructor;
import novares.uz.collection.NewsCollection;
import novares.uz.controller.GenericCrudController;
import novares.uz.criteria.category.CategoryCriteria;
import novares.uz.criteria.news.NewsCriteria;
import novares.uz.domain.category.Category;
import novares.uz.domain.news.News;
import novares.uz.dto.AppResponse;
import novares.uz.dto.category.CategoryCrudDto;
import novares.uz.dto.category.CategoryDto;
import novares.uz.dto.news.NewsCrudDto;
import novares.uz.dto.news.NewsDto;
import novares.uz.mapper.category.CategoryMapper;
import novares.uz.mapper.news.NewsMapper;
import novares.uz.service.category.CategoryService;
import novares.uz.service.message.ResourceBundleService;
import novares.uz.service.news.NewsService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "${app.api.admin-path}", produces = "application/json")
public class NewsController implements GenericCrudController<NewsDto, NewsCrudDto, NewsCriteria> {

    private final NewsService service;
    private final NewsMapper mapper;
    private final ResourceBundleService bundleService;

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/news/{id}", method = RequestMethod.GET)
    public ResponseEntity<AppResponse<NewsDto>> get(@PathVariable(value = "id") Long id) {
        String message = News.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("retrieved");
        return ResponseEntity.ok(AppResponse.success(mapper.toDto(service.get(id)), message));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public ResponseEntity<AppResponse<Page<NewsDto>>> list(NewsCriteria criteria) {
        String message = News.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("retrieved");
        return ResponseEntity.ok(AppResponse.success(service.list(criteria).map(mapper::toDto), message));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/news/filter", method = RequestMethod.GET)
    public ResponseEntity<AppResponse<Page<NewsCollection>>> getNewsFiltered(NewsCriteria criteria) {
        String message = News.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("retrieved");
        return ResponseEntity.ok(AppResponse.success(service.getNewsFiltered(criteria), message));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/news", method = RequestMethod.POST)
    public ResponseEntity<AppResponse<NewsDto>> create(@RequestBody NewsCrudDto dto) {
        String message = News.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("created");
        return new ResponseEntity<>(AppResponse.success(mapper.toDto(service.create(dto)), message), HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/news/{id}", method = {RequestMethod.PUT, RequestMethod.POST})
    public ResponseEntity<AppResponse<NewsDto>> edit(@PathVariable(value = "id") Long id, @RequestBody NewsCrudDto dto) {
        String message = News.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("updated");
        return ResponseEntity.ok(AppResponse.success(mapper.toDto(service.update(id, dto)), message));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/news/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<AppResponse<Boolean>> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        String message = News.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("deleted");
        return ResponseEntity.ok(AppResponse.success(true, message));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/news/{id}/restore", method = RequestMethod.POST)
    public ResponseEntity<AppResponse<Boolean>> restore(@PathVariable(value = "id") Long id) {
        service.restore(id);
        return ResponseEntity.ok(AppResponse.success(true, "News restored"));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/news/{id}/hard", method = RequestMethod.DELETE)
    public ResponseEntity<AppResponse<Boolean>> hardDelete(@PathVariable(value = "id") Long id) {
        service.hardDelete(id);
        return ResponseEntity.ok(AppResponse.success(true, "News hard deleted"));
    }

}
