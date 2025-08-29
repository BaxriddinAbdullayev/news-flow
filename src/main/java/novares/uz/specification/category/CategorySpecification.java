package novares.uz.specification.category;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import novares.uz.criteria.category.CategoryCriteria;
import novares.uz.domain.category.Category;
import novares.uz.specification.GenericSpecification;

@RequiredArgsConstructor
public class CategorySpecification extends GenericSpecification<Category> {

    private final CategoryCriteria criteria;

    @Override
    public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        super.toPredicate(root, query, criteriaBuilder);

        return query
                .where(criteriaBuilder.and(predicates.toArray(new Predicate[0])))
                .getRestriction();
    }
}
