package novares.uz.specification.news;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import novares.uz.criteria.news.NewsHistoryCriteria;
import novares.uz.domain.news.NewsHistory;
import novares.uz.specification.GenericSpecification;

@RequiredArgsConstructor
public class NewsHistorySpecification extends GenericSpecification<NewsHistory> {

    private final NewsHistoryCriteria criteria;

    @Override
    public Predicate toPredicate(Root<NewsHistory> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        super.toPredicate(root, query, criteriaBuilder);

        return query
                .where(criteriaBuilder.and(predicates.toArray(new Predicate[0])))
                .getRestriction();
    }
}
