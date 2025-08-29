package novares.uz.controller.admin.file;

import lombok.AllArgsConstructor;
import novares.uz.controller.GenericController;
import novares.uz.criteria.file.ResourceFileCriteria;
import novares.uz.domain.file.ResourceFile;
import novares.uz.dto.AppResponse;
import novares.uz.dto.file.ResourceFileDto;
import novares.uz.mapper.file.ResourceFileMapper;
import novares.uz.service.file.ResourceFileService;
import novares.uz.service.message.ResourceBundleService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "${app.api.admin-path}", produces = "application/json")
public class ResourceFileController implements GenericController<ResourceFileDto, ResourceFileCriteria> {

    private final ResourceFileService service;
    private final ResourceFileMapper mapper;
    private final ResourceBundleService bundleService;

    @Override
    @RequestMapping(value = "/file/resource-file/{id}", method = RequestMethod.GET)
    public ResponseEntity<AppResponse<ResourceFileDto>> get(@PathVariable(value = "id") Long id) {
        String message = ResourceFile.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("retrieved");
        return ResponseEntity.ok(AppResponse.success(mapper.toDto(service.get(id)), message));
    }

    @Override
    @RequestMapping(value = "/file/resource-file", method = RequestMethod.GET)
    public ResponseEntity<AppResponse<Page<ResourceFileDto>>> list(ResourceFileCriteria criteria) {
        String message = ResourceFile.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("retrieved");
        return ResponseEntity.ok(AppResponse.success(service.list(criteria).map(mapper::toDto), message));
    }


}
