package novares.uz.controller.publicController;

import lombok.AllArgsConstructor;
import novares.uz.collection.AdsPlacementCollection;
import novares.uz.criteria.ads.AdsPlacementCriteria;
import novares.uz.domain.tag.Tag;
import novares.uz.dto.AppResponse;
import novares.uz.mapper.ads.AdsPlacementMapper;
import novares.uz.service.ads.AdsPlacementService;
import novares.uz.service.message.ResourceBundleService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "${app.api.public-path}", produces = "application/json")
public class PublicAdsPlacementController {

    private final AdsPlacementService service;
    private final AdsPlacementMapper mapper;
    private final ResourceBundleService bundleService;

    @RequestMapping(value = "/ads/{placementCode}", method = RequestMethod.GET)
    public ResponseEntity<AppResponse<Page<AdsPlacementCollection>>> getAdsFilteredForPublic(AdsPlacementCriteria criteria) {
        String message = Tag.class.getSimpleName() + " " + bundleService.getSuccessCrudMessage("retrieved");
        return ResponseEntity.ok(AppResponse.success(service.getAdsFilteredForPublic(criteria), message));
    }
}
