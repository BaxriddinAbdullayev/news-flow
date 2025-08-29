package novares.uz.controller.admin.ads;

import lombok.AllArgsConstructor;
import novares.uz.controller.GenericCrudController;
import novares.uz.criteria.ads.AdsCampaignCriteria;
import novares.uz.domain.ads.AdsCampaign;
import novares.uz.dto.AppResponse;
import novares.uz.dto.ads.AdsCampaignCrudDto;
import novares.uz.dto.ads.AdsCampaignDto;
import novares.uz.mapper.ads.AdsCampaignMapper;
import novares.uz.service.ads.AdsCampaignService;
import novares.uz.service.message.ResourceBundleService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "${app.api.admin-path}", produces = "application/json")
public class AdsCampaignController implements GenericCrudController<AdsCampaignDto, AdsCampaignCrudDto, AdsCampaignCriteria> {

    private final AdsCampaignService service;
    private final AdsCampaignMapper mapper;
    private final ResourceBundleService bundleService;

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/ads-campaigns/{id}", method = RequestMethod.GET)
    public ResponseEntity<AppResponse<AdsCampaignDto>> get(@PathVariable(value = "id") Long id) {
        String message = AdsCampaign.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("retrieved");
        return ResponseEntity.ok(AppResponse.success(mapper.toDto(service.get(id)), message));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/ads-campaigns", method = RequestMethod.GET)
    public ResponseEntity<AppResponse<Page<AdsCampaignDto>>> list(AdsCampaignCriteria criteria) {
        String message = AdsCampaign.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("retrieved");
        return ResponseEntity.ok(AppResponse.success(service.list(criteria).map(mapper::toDto), message));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/ads-campaigns", method = RequestMethod.POST)
    public ResponseEntity<AppResponse<AdsCampaignDto>> create(@RequestBody AdsCampaignCrudDto dto) {
        String message = AdsCampaign.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("created");
        return new ResponseEntity<>(AppResponse.success(mapper.toDto(service.create(dto)), message), HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/ads-campaigns/{id}", method = {RequestMethod.PUT, RequestMethod.POST})
    public ResponseEntity<AppResponse<AdsCampaignDto>> edit(@PathVariable(value = "id") Long id, @RequestBody AdsCampaignCrudDto dto) {
        String message = AdsCampaign.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("updated");
        return ResponseEntity.ok(AppResponse.success(mapper.toDto(service.update(id, dto)), message));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/ads-campaigns/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<AppResponse<Boolean>> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        String message = AdsCampaign.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("deleted");
        return ResponseEntity.ok(AppResponse.success(true, message));
    }
}
