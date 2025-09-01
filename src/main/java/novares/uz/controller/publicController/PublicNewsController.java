package novares.uz.controller.publicController;

import lombok.AllArgsConstructor;
import novares.uz.collection.NewsCollection;
import novares.uz.criteria.news.NewsCriteria;
import novares.uz.domain.news.News;
import novares.uz.dto.AppResponse;
import novares.uz.mapper.news.NewsMapper;
import novares.uz.service.message.ResourceBundleService;
import novares.uz.service.news.NewsService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "${app.api.public-path}", produces = "application/json")
public class PublicNewsController {


    private final NewsService service;
    private final NewsMapper mapper;
    private final ResourceBundleService bundleService;

    @RequestMapping(value = "/news/{slug}", method = RequestMethod.GET)
    public ResponseEntity<AppResponse<Page<NewsCollection>>> getNewsBySlugAndLang(@PathVariable("slug") String slug,
                                                                  NewsCriteria criteria) {
        String message = News.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("retrieved");
        return ResponseEntity.ok(AppResponse.success(service.getNewsBySlugAndLang(slug,criteria), message));
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public ResponseEntity<AppResponse<Page<NewsCollection>>> getNewsFilteredForPublic(NewsCriteria criteria) {
        String message = News.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("retrieved");
        return ResponseEntity.ok(AppResponse.success(service.getNewsFilteredForPublic(criteria), message));
    }
}
