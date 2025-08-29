package novares.uz.controller.admin.ads;

import lombok.AllArgsConstructor;
import novares.uz.controller.GenericCrudController;
import novares.uz.criteria.ads.AdsCreativeTranslationCriteria;
import novares.uz.domain.ads.AdsCreativeTranslation;
import novares.uz.dto.AppResponse;
import novares.uz.dto.ads.AdsCreativeTranslationCrudDto;
import novares.uz.dto.ads.AdsCreativeTranslationDto;
import novares.uz.mapper.ads.AdsCreativeTranslationMapper;
import novares.uz.service.ads.AdsCreativeTranslationService;
import novares.uz.service.message.ResourceBundleService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "${app.api.admin-path}", produces = "application/json")
public class AdsCreativeTranslationController implements GenericCrudController<AdsCreativeTranslationDto, AdsCreativeTranslationCrudDto, AdsCreativeTranslationCriteria> {

    private final AdsCreativeTranslationService service;
    private final AdsCreativeTranslationMapper mapper;
    private final ResourceBundleService bundleService;

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/ads-creative-translations/{id}", method = RequestMethod.GET)
    public ResponseEntity<AppResponse<AdsCreativeTranslationDto>> get(@PathVariable(value = "id") Long id) {
        String message = AdsCreativeTranslation.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("retrieved");
        return ResponseEntity.ok(AppResponse.success(mapper.toDto(service.get(id)), message));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/ads-creative-translations", method = RequestMethod.GET)
    public ResponseEntity<AppResponse<Page<AdsCreativeTranslationDto>>> list(AdsCreativeTranslationCriteria criteria) {
        String message = AdsCreativeTranslation.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("retrieved");
        return ResponseEntity.ok(AppResponse.success(service.list(criteria).map(mapper::toDto), message));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/ads-creative-translations", method = RequestMethod.POST)
    public ResponseEntity<AppResponse<AdsCreativeTranslationDto>> create(@RequestBody AdsCreativeTranslationCrudDto dto) {
        String message = AdsCreativeTranslation.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("created");
        return new ResponseEntity<>(AppResponse.success(mapper.toDto(service.create(dto)), message), HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/ads-creative-translations/{id}", method = {RequestMethod.PUT, RequestMethod.POST})
    public ResponseEntity<AppResponse<AdsCreativeTranslationDto>> edit(@PathVariable(value = "id") Long id, @RequestBody AdsCreativeTranslationCrudDto dto) {
        String message = AdsCreativeTranslation.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("updated");
        return ResponseEntity.ok(AppResponse.success(mapper.toDto(service.update(id, dto)), message));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/ads-creative-translations/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<AppResponse<Boolean>> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        String message = AdsCreativeTranslation.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("deleted");
        return ResponseEntity.ok(AppResponse.success(true, message));
    }
}
