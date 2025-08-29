package novares.uz.controller.admin.ads;

import lombok.AllArgsConstructor;
import novares.uz.controller.GenericCrudController;
import novares.uz.criteria.ads.AdsPlacementCriteria;
import novares.uz.criteria.category.CategoryCriteria;
import novares.uz.domain.ads.AdsPlacement;
import novares.uz.domain.category.Category;
import novares.uz.dto.AppResponse;
import novares.uz.dto.ads.AdsPlacementCrudDto;
import novares.uz.dto.ads.AdsPlacementDto;
import novares.uz.dto.category.CategoryCrudDto;
import novares.uz.dto.category.CategoryDto;
import novares.uz.mapper.ads.AdsPlacementMapper;
import novares.uz.mapper.category.CategoryMapper;
import novares.uz.service.ads.AdsPlacementService;
import novares.uz.service.category.CategoryService;
import novares.uz.service.message.ResourceBundleService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "${app.api.admin-path}", produces = "application/json")
public class AdsPlacementController implements GenericCrudController<AdsPlacementDto, AdsPlacementCrudDto, AdsPlacementCriteria> {

    private final AdsPlacementService service;
    private final AdsPlacementMapper mapper;
    private final ResourceBundleService bundleService;

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/ads-placements/{id}", method = RequestMethod.GET)
    public ResponseEntity<AppResponse<AdsPlacementDto>> get(@PathVariable(value = "id") Long id) {
        String message = AdsPlacement.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("retrieved");
        return ResponseEntity.ok(AppResponse.success(mapper.toDto(service.get(id)), message));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/ads-placements", method = RequestMethod.GET)
    public ResponseEntity<AppResponse<Page<AdsPlacementDto>>> list(AdsPlacementCriteria criteria) {
        String message = AdsPlacement.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("retrieved");
        return ResponseEntity.ok(AppResponse.success(service.list(criteria).map(mapper::toDto), message));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/ads-placements", method = RequestMethod.POST)
    public ResponseEntity<AppResponse<AdsPlacementDto>> create(@RequestBody AdsPlacementCrudDto dto) {
        String message = AdsPlacement.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("created");
        return new ResponseEntity<>(AppResponse.success(mapper.toDto(service.create(dto)), message), HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/ads-placements/{id}", method = {RequestMethod.PUT, RequestMethod.POST})
    public ResponseEntity<AppResponse<AdsPlacementDto>> edit(@PathVariable(value = "id") Long id, @RequestBody AdsPlacementCrudDto dto) {
        String message = AdsPlacement.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("updated");
        return ResponseEntity.ok(AppResponse.success(mapper.toDto(service.update(id, dto)), message));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/ads-placements/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<AppResponse<Boolean>> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        String message = AdsPlacement.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("deleted");
        return ResponseEntity.ok(AppResponse.success(true, message));
    }
}
