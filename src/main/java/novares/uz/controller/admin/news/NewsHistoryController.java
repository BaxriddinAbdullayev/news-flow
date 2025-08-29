package novares.uz.controller.admin.news;

import lombok.AllArgsConstructor;
import novares.uz.controller.GenericCrudController;
import novares.uz.criteria.news.NewsCriteria;
import novares.uz.criteria.news.NewsHistoryCriteria;
import novares.uz.domain.news.News;
import novares.uz.domain.news.NewsHistory;
import novares.uz.dto.AppResponse;
import novares.uz.dto.news.NewsCrudDto;
import novares.uz.dto.news.NewsDto;
import novares.uz.dto.news.NewsHistoryCrudDto;
import novares.uz.dto.news.NewsHistoryDto;
import novares.uz.mapper.news.NewsHistoryMapper;
import novares.uz.mapper.news.NewsMapper;
import novares.uz.service.message.ResourceBundleService;
import novares.uz.service.news.NewsHistoryService;
import novares.uz.service.news.NewsService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "${app.api.admin-path}", produces = "application/json")
public class NewsHistoryController implements GenericCrudController<NewsHistoryDto, NewsHistoryCrudDto, NewsHistoryCriteria> {

    private final NewsHistoryService service;
    private final NewsHistoryMapper mapper;
    private final ResourceBundleService bundleService;

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/news-histories/{id}", method = RequestMethod.GET)
    public ResponseEntity<AppResponse<NewsHistoryDto>> get(@PathVariable(value = "id") Long id) {
        String message = NewsHistory.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("retrieved");
        return ResponseEntity.ok(AppResponse.success(mapper.toDto(service.get(id)), message));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/news-histories", method = RequestMethod.GET)
    public ResponseEntity<AppResponse<Page<NewsHistoryDto>>> list(NewsHistoryCriteria criteria) {
        String message = NewsHistory.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("retrieved");
        return ResponseEntity.ok(AppResponse.success(service.list(criteria).map(mapper::toDto), message));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/news-histories", method = RequestMethod.POST)
    public ResponseEntity<AppResponse<NewsHistoryDto>> create(@RequestBody NewsHistoryCrudDto dto) {
        String message = NewsHistory.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("created");
        return new ResponseEntity<>(AppResponse.success(mapper.toDto(service.create(dto)), message), HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/news-histories/{id}", method = {RequestMethod.PUT, RequestMethod.POST})
    public ResponseEntity<AppResponse<NewsHistoryDto>> edit(@PathVariable(value = "id") Long id, @RequestBody NewsHistoryCrudDto dto) {
        String message = NewsHistory.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("updated");
        return ResponseEntity.ok(AppResponse.success(mapper.toDto(service.update(id, dto)), message));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/news-histories/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<AppResponse<Boolean>> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        String message = NewsHistory.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("deleted");
        return ResponseEntity.ok(AppResponse.success(true, message));
    }
}
