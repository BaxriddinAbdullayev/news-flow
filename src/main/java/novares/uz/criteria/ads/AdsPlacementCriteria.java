package novares.uz.criteria.ads;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import novares.uz.criteria.GenericCriteria;
import novares.uz.domain.ads.AdsPlacement;
import novares.uz.specification.ads.AdsPlacementSpecification;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

@Getter
@Setter
public class AdsPlacementCriteria extends GenericCriteria {

    private String placementCode;
    private String lang;
    private Long categoryId;

    @Builder(builderMethodName = "childBuilder")
    public AdsPlacementCriteria(Long id, Integer page, Integer size, Sort.Direction direction, String sort) {
        super(id, page, size, direction, sort);
    }

    public Specification<AdsPlacement> toSpecification() {
        return new AdsPlacementSpecification(this);
    }

}
