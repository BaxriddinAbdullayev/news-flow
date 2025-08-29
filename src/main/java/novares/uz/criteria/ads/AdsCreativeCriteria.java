package novares.uz.criteria.ads;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import novares.uz.criteria.GenericCriteria;
import novares.uz.domain.ads.AdsCreative;
import novares.uz.specification.ads.AdsCreativeSpecification;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

@Getter
@Setter
public class AdsCreativeCriteria extends GenericCriteria {

    @Builder(builderMethodName = "childBuilder")
    public AdsCreativeCriteria(Long id, Integer page, Integer size, Sort.Direction direction, String sort) {
        super(id, page, size, direction, sort);
    }

    public Specification<AdsCreative> toSpecification() {
        return new AdsCreativeSpecification(this);
    }

}
