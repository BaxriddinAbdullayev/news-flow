package novares.uz.specification.ads;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import novares.uz.criteria.ads.AdsCampaignCriteria;
import novares.uz.domain.ads.AdsCampaign;
import novares.uz.specification.GenericSpecification;

@RequiredArgsConstructor
public class AdsCampaignSpecification extends GenericSpecification<AdsCampaign> {

    private final AdsCampaignCriteria criteria;

    @Override
    public Predicate toPredicate(Root<AdsCampaign> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        super.toPredicate(root, query, criteriaBuilder);

        return query
                .where(criteriaBuilder.and(predicates.toArray(new Predicate[0])))
                .getRestriction();
    }
}
