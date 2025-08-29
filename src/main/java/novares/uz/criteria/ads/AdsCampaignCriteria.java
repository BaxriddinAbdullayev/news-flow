package novares.uz.criteria.ads;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import novares.uz.criteria.GenericCriteria;
import novares.uz.domain.ads.AdsCampaign;
import novares.uz.specification.ads.AdsCampaignSpecification;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

@Getter
@Setter
public class AdsCampaignCriteria extends GenericCriteria {

    @Builder(builderMethodName = "childBuilder")
    public AdsCampaignCriteria(Long id, Integer page, Integer size, Sort.Direction direction, String sort) {
        super(id, page, size, direction, sort);
    }

    public Specification<AdsCampaign> toSpecification() {
        return new AdsCampaignSpecification(this);
    }

}
