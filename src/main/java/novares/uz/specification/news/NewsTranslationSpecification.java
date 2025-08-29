package novares.uz.specification.news;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import novares.uz.criteria.news.NewsTranslationCriteria;
import novares.uz.domain.news.NewsTranslation;
import novares.uz.specification.GenericSpecification;

@RequiredArgsConstructor
public class NewsTranslationSpecification extends GenericSpecification<NewsTranslation> {

    private final NewsTranslationCriteria criteria;

    @Override
    public Predicate toPredicate(Root<NewsTranslation> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        super.toPredicate(root, query, criteriaBuilder);

        return query
                .where(criteriaBuilder.and(predicates.toArray(new Predicate[0])))
                .getRestriction();
    }
}
