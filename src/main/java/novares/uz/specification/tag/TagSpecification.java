package novares.uz.specification.tag;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import novares.uz.criteria.tag.TagCriteria;
import novares.uz.domain.tag.Tag;
import novares.uz.specification.GenericSpecification;

@RequiredArgsConstructor
public class TagSpecification extends GenericSpecification<Tag> {

    private final TagCriteria criteria;

    @Override
    public Predicate toPredicate(Root<Tag> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        super.toPredicate(root, query, criteriaBuilder);

        return query
                .where(criteriaBuilder.and(predicates.toArray(new Predicate[0])))
                .getRestriction();
    }
}
