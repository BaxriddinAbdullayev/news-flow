package novares.uz.controller.publicController;

import lombok.AllArgsConstructor;
import novares.uz.criteria.tag.TagCriteria;
import novares.uz.domain.tag.Tag;
import novares.uz.dto.AppResponse;
import novares.uz.dto.tag.TagDto;
import novares.uz.mapper.tag.TagMapper;
import novares.uz.service.message.ResourceBundleService;
import novares.uz.service.tag.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "${app.api.public-path}", produces = "application/json")
public class PublicTagController {

    private final TagService service;
    private final TagMapper mapper;
    private final ResourceBundleService bundleService;

    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public ResponseEntity<AppResponse<List<TagDto>>> getTagFilteredForPublic() {
        String message = Tag.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("retrieved");
        return ResponseEntity.ok(AppResponse.success(service.listForPublic().stream().map(mapper::toDto).toList(), message));
    }
}
