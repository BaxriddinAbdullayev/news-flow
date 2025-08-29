package novares.uz.specification.ads;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import novares.uz.criteria.ads.AdsCreativeTranslationCriteria;
import novares.uz.domain.ads.AdsCreativeTranslation;
import novares.uz.specification.GenericSpecification;

@RequiredArgsConstructor
public class AdsCreativeTranslationSpecification extends GenericSpecification<AdsCreativeTranslation> {

    private final AdsCreativeTranslationCriteria criteria;

    @Override
    public Predicate toPredicate(Root<AdsCreativeTranslation> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        super.toPredicate(root, query, criteriaBuilder);

        return query
                .where(criteriaBuilder.and(predicates.toArray(new Predicate[0])))
                .getRestriction();
    }
}
