package novares.uz.criteria.news;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import novares.uz.criteria.GenericCriteria;
import novares.uz.domain.news.News;
import novares.uz.specification.news.NewsSpecification;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.time.ZonedDateTime;

@Getter
@Setter
public class NewsCriteria extends GenericCriteria {

    private String status;
    private Long authorId;
    private Long categoryId;
    private String tag;
    private String lang;
    private ZonedDateTime from;
    private ZonedDateTime to;

    @Builder(builderMethodName = "childBuilder")
    public NewsCriteria(Long id, Integer page, Integer size, Sort.Direction direction, String sort) {
        super(id, page, size, direction, sort);
    }

    public Specification<News> toSpecification() {
        return new NewsSpecification(this);
    }

}
