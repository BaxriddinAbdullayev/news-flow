package novares.uz.specification.auth;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import novares.uz.criteria.auth.RoleCriteria;
import novares.uz.domain.auth.Role;
import novares.uz.specification.GenericSpecification;

@RequiredArgsConstructor
public class RoleSpecification extends GenericSpecification<Role> {

    private final RoleCriteria criteria;

    @Override
    public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        super.toPredicate(root, query, criteriaBuilder);

        return query
                .where(criteriaBuilder.and(predicates.toArray(new Predicate[0])))
                .getRestriction();
    }
}
