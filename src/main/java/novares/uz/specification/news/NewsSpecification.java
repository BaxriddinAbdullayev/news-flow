package novares.uz.specification.news;

import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import novares.uz.criteria.news.NewsCriteria;
import novares.uz.domain.news.News;
import novares.uz.domain.news.NewsTranslation;
import novares.uz.specification.GenericSpecification;

import java.util.Objects;

@RequiredArgsConstructor
    public class NewsSpecification extends GenericSpecification<News> {

    private final NewsCriteria criteria;

    @Override
    public Predicate toPredicate(Root<News> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        super.toPredicate(root, query, criteriaBuilder);


        if (!Objects.isNull(criteria.getStatus())) {
            predicates.add(criteriaBuilder.equal(root.get("status"), criteria.getStatus()));
        }

        if (criteria.getAuthorId() != null) {
            predicates.add(criteriaBuilder.equal(root.get("author").get("id"), criteria.getAuthorId()));
        }

        if (!Objects.isNull(criteria.getCategoryId())) {
            predicates.add(criteriaBuilder.equal(root.get("category").get("id"), criteria.getCategoryId()));
        }

        if (!Objects.isNull(criteria.getTag())) {
            predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.lower(root.join("tags").get("code")), criteria.getTag().toLowerCase()
            ));
        }

        if(Objects.nonNull(criteria.getLang())) {
            predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.lower(root.join("translations").get("lang")), criteria.getLang().toLowerCase()
            ));
        }

        if (!Objects.isNull(criteria.getFrom())) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("publishedAt"), criteria.getFrom()));
        }
        if (!Objects.isNull(criteria.getTo())) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("publishedAt"), criteria.getTo()));
        }

        return query
                .where(criteriaBuilder.and(predicates.toArray(new Predicate[0])))
                .getRestriction();
    }
}
