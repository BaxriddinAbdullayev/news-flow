package novares.uz.specification.ads;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import novares.uz.criteria.ads.AdsPlacementCriteria;
import novares.uz.domain.ads.AdsPlacement;
import novares.uz.specification.GenericSpecification;

@RequiredArgsConstructor
public class AdsPlacementSpecification extends GenericSpecification<AdsPlacement> {

    private final AdsPlacementCriteria criteria;

    @Override
    public Predicate toPredicate(Root<AdsPlacement> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        super.toPredicate(root, query, criteriaBuilder);

        return query
                .where(criteriaBuilder.and(predicates.toArray(new Predicate[0])))
                .getRestriction();
    }
}
