package novares.uz.criteria.news;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import novares.uz.criteria.GenericCriteria;
import novares.uz.domain.news.NewsTranslation;
import novares.uz.specification.news.NewsTranslationSpecification;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

@Getter
@Setter
public class NewsTranslationCriteria extends GenericCriteria {

    @Builder(builderMethodName = "childBuilder")
    public NewsTranslationCriteria(Long id, Integer page, Integer size, Sort.Direction direction, String sort) {
        super(id, page, size, direction, sort);
    }

    public Specification<NewsTranslation> toSpecification() {
        return new NewsTranslationSpecification(this);
    }

}
