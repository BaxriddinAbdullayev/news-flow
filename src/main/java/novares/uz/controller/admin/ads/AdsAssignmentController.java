package novares.uz.controller.admin.ads;

import lombok.AllArgsConstructor;
import novares.uz.controller.GenericCrudController;
import novares.uz.criteria.ads.AdsAssignmentCriteria;
import novares.uz.domain.ads.AdsAssignment;
import novares.uz.dto.AppResponse;
import novares.uz.dto.ads.AdsAssignmentCrudDto;
import novares.uz.dto.ads.AdsAssignmentDto;
import novares.uz.mapper.ads.AdsAssignmentMapper;
import novares.uz.service.ads.AdsAssignmentService;
import novares.uz.service.message.ResourceBundleService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "${app.api.admin-path}", produces = "application/json")
public class AdsAssignmentController implements GenericCrudController<AdsAssignmentDto, AdsAssignmentCrudDto, AdsAssignmentCriteria> {

    private final AdsAssignmentService service;
    private final AdsAssignmentMapper mapper;
    private final ResourceBundleService bundleService;

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/ads-assignments/{id}", method = RequestMethod.GET)
    public ResponseEntity<AppResponse<AdsAssignmentDto>> get(@PathVariable(value = "id") Long id) {
        String message = AdsAssignment.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("retrieved");
        return ResponseEntity.ok(AppResponse.success(mapper.toDto(service.get(id)), message));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/ads-assignments", method = RequestMethod.GET)
    public ResponseEntity<AppResponse<Page<AdsAssignmentDto>>> list(AdsAssignmentCriteria criteria) {
        String message = AdsAssignment.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("retrieved");
        return ResponseEntity.ok(AppResponse.success(service.list(criteria).map(mapper::toDto), message));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/ads-assignments", method = RequestMethod.POST)
    public ResponseEntity<AppResponse<AdsAssignmentDto>> create(@RequestBody AdsAssignmentCrudDto dto) {
        String message = AdsAssignment.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("created");
        return new ResponseEntity<>(AppResponse.success(mapper.toDto(service.create(dto)), message), HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/ads-assignments/{id}", method = {RequestMethod.PUT, RequestMethod.POST})
    public ResponseEntity<AppResponse<AdsAssignmentDto>> edit(@PathVariable(value = "id") Long id, @RequestBody AdsAssignmentCrudDto dto) {
        String message = AdsAssignment.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("updated");
        return ResponseEntity.ok(AppResponse.success(mapper.toDto(service.update(id, dto)), message));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_AUTHOR')")
    @RequestMapping(value = "/ads-assignments/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<AppResponse<Boolean>> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        String message = AdsAssignment.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("deleted");
        return ResponseEntity.ok(AppResponse.success(true, message));
    }
}
