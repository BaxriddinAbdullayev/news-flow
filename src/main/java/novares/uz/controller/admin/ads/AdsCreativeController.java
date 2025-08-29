package novares.uz.controller.admin.ads;

import lombok.AllArgsConstructor;
import novares.uz.controller.GenericCrudController;
import novares.uz.criteria.ads.AdsCreativeCriteria;
import novares.uz.domain.ads.AdsCreative;
import novares.uz.dto.AppResponse;
import novares.uz.dto.ads.AdsCreativeCrudDto;
import novares.uz.dto.ads.AdsCreativeDto;
import novares.uz.mapper.ads.AdsCreativeMapper;
import novares.uz.service.ads.AdsCreativeService;
import novares.uz.service.message.ResourceBundleService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "${app.api.admin-path}", produces = "application/json")
public class AdsCreativeController implements GenericCrudController<AdsCreativeDto, AdsCreativeCrudDto, AdsCreativeCriteria> {

    private final AdsCreativeService service;
    private final AdsCreativeMapper mapper;
    private final ResourceBundleService bundleService;

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/ads-creatives/{id}", method = RequestMethod.GET)
    public ResponseEntity<AppResponse<AdsCreativeDto>> get(@PathVariable(value = "id") Long id) {
        String message = AdsCreative.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("retrieved");
        return ResponseEntity.ok(AppResponse.success(service.getCreativeWithTranslation(id), message));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/ads-creatives", method = RequestMethod.GET)
    public ResponseEntity<AppResponse<Page<AdsCreativeDto>>> list(AdsCreativeCriteria criteria) {
        String message = AdsCreative.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("retrieved");
        return ResponseEntity.ok(AppResponse.success(service.listCreativeWithTranslation(criteria), message));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/ads-creatives", method = RequestMethod.POST)
    public ResponseEntity<AppResponse<AdsCreativeDto>> create(@RequestBody AdsCreativeCrudDto dto) {
        String message = AdsCreative.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("created");
        return new ResponseEntity<>(AppResponse.success(mapper.toDto(service.create(dto)), message), HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/ads-creatives/{id}", method = {RequestMethod.PUT, RequestMethod.POST})
    public ResponseEntity<AppResponse<AdsCreativeDto>> edit(@PathVariable(value = "id") Long id, @RequestBody AdsCreativeCrudDto dto) {
        String message = AdsCreative.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("updated");
        return ResponseEntity.ok(AppResponse.success(mapper.toDto(service.update(id, dto)), message));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/ads-creatives/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<AppResponse<Boolean>> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        String message = AdsCreative.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("deleted");
        return ResponseEntity.ok(AppResponse.success(true, message));
    }
}
