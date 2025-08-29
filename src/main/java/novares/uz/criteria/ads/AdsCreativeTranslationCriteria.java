package novares.uz.criteria.ads;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import novares.uz.criteria.GenericCriteria;
import novares.uz.domain.ads.AdsCreativeTranslation;
import novares.uz.specification.ads.AdsCreativeTranslationSpecification;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

@Getter
@Setter
public class AdsCreativeTranslationCriteria extends GenericCriteria {

    @Builder(builderMethodName = "childBuilder")
    public AdsCreativeTranslationCriteria(Long id, Integer page, Integer size, Sort.Direction direction, String sort) {
        super(id, page, size, direction, sort);
    }

    public Specification<AdsCreativeTranslation> toSpecification() {
        return new AdsCreativeTranslationSpecification(this);
    }

}
