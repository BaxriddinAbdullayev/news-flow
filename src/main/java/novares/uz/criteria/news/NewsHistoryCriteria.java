package novares.uz.criteria.news;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import novares.uz.criteria.GenericCriteria;
import novares.uz.domain.news.NewsHistory;
import novares.uz.specification.news.NewsHistorySpecification;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

@Getter
@Setter
public class NewsHistoryCriteria extends GenericCriteria {

    @Builder(builderMethodName = "childBuilder")
    public NewsHistoryCriteria(Long id, Integer page, Integer size, Sort.Direction direction, String sort) {
        super(id, page, size, direction, sort);
    }

    public Specification<NewsHistory> toSpecification() {
        return new NewsHistorySpecification(this);
    }

}
