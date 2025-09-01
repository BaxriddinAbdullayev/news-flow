package novares.uz.criteria.category;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import novares.uz.criteria.GenericCriteria;
import novares.uz.domain.category.Category;
import novares.uz.specification.category.CategorySpecification;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

@Getter
@Setter
public class CategoryCriteria extends GenericCriteria {

    private String lang;

    @Builder(builderMethodName = "childBuilder")
    public CategoryCriteria(Long id, Integer page, Integer size, Sort.Direction direction, String sort) {
        super(id, page, size, direction, sort);
    }

    public Specification<Category> toSpecification() {
        return new CategorySpecification(this);
    }

}
