package novares.uz.controller.admin.news;

import lombok.AllArgsConstructor;
import novares.uz.controller.GenericCrudController;
import novares.uz.criteria.news.NewsTranslationCriteria;
import novares.uz.domain.news.NewsTranslation;
import novares.uz.dto.AppResponse;
import novares.uz.dto.news.NewsTranslationCrudDto;
import novares.uz.dto.news.NewsTranslationDto;
import novares.uz.mapper.news.NewsTranslationMapper;
import novares.uz.service.message.ResourceBundleService;
import novares.uz.service.news.NewsTranslationService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "${app.api.admin-path}", produces = "application/json")
public class NewsTranslationController implements GenericCrudController<NewsTranslationDto, NewsTranslationCrudDto, NewsTranslationCriteria> {

    private final NewsTranslationService service;
    private final NewsTranslationMapper mapper;
    private final ResourceBundleService bundleService;

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/news-translation/{id}", method = RequestMethod.GET)
    public ResponseEntity<AppResponse<NewsTranslationDto>> get(@PathVariable(value = "id") Long id) {
        String message = NewsTranslation.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("retrieved");
        return ResponseEntity.ok(AppResponse.success(mapper.toDto(service.get(id)), message));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/news-translation", method = RequestMethod.GET)
    public ResponseEntity<AppResponse<Page<NewsTranslationDto>>> list(NewsTranslationCriteria criteria) {
        String message = NewsTranslation.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("retrieved");
        return ResponseEntity.ok(AppResponse.success(service.list(criteria).map(mapper::toDto), message));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/news-translation", method = RequestMethod.POST)
    public ResponseEntity<AppResponse<NewsTranslationDto>> create(@RequestBody NewsTranslationCrudDto dto) {
        String message = NewsTranslation.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("created");
        return new ResponseEntity<>(AppResponse.success(mapper.toDto(service.create(dto)), message), HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/news-translation/{id}", method = {RequestMethod.PUT, RequestMethod.POST})
    public ResponseEntity<AppResponse<NewsTranslationDto>> edit(@PathVariable(value = "id") Long id, @RequestBody NewsTranslationCrudDto dto) {
        String message = NewsTranslation.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("updated");
        return ResponseEntity.ok(AppResponse.success(mapper.toDto(service.update(id, dto)), message));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/news-translation/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<AppResponse<Boolean>> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        String message = NewsTranslation.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("deleted");
        return ResponseEntity.ok(AppResponse.success(true, message));
    }
}
