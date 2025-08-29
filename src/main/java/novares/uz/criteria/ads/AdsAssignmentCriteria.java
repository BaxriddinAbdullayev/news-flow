package novares.uz.criteria.ads;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import novares.uz.criteria.GenericCriteria;
import novares.uz.domain.ads.AdsAssignment;
import novares.uz.specification.ads.AdsAssignmentSpecification;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

@Getter
@Setter
public class AdsAssignmentCriteria extends GenericCriteria {

    @Builder(builderMethodName = "childBuilder")
    public AdsAssignmentCriteria(Long id, Integer page, Integer size, Sort.Direction direction, String sort) {
        super(id, page, size, direction, sort);
    }

    public Specification<AdsAssignment> toSpecification() {
        return new AdsAssignmentSpecification(this);
    }

}
