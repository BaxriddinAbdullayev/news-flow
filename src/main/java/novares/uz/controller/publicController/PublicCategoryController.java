package novares.uz.controller.publicController;

import lombok.AllArgsConstructor;
import novares.uz.collection.CategoryCollection;
import novares.uz.criteria.category.CategoryCriteria;
import novares.uz.domain.category.Category;
import novares.uz.dto.AppResponse;
import novares.uz.mapper.category.CategoryMapper;
import novares.uz.service.category.CategoryService;
import novares.uz.service.message.ResourceBundleService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "${app.api.public-path}", produces = "application/json")
public class PublicCategoryController {

    private final CategoryService service;
    private final CategoryMapper mapper;
    private final ResourceBundleService bundleService;


    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ResponseEntity<AppResponse<Page<CategoryCollection>>> getNewsBySlugAndLang(CategoryCriteria criteria) {
        String message = Category.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("retrieved");
        return ResponseEntity.ok(AppResponse.success(service.getNewsBySlugAndLang(criteria), message));
    }
}
