package novares.uz.criteria.category;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import novares.uz.criteria.GenericCriteria;
import novares.uz.domain.category.CategoryTranslation;
import novares.uz.specification.category.CategoryTranslationSpecification;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

@Getter
@Setter
public class CategoryTranslationCriteria extends GenericCriteria {

    @Builder(builderMethodName = "childBuilder")
    public CategoryTranslationCriteria(Long id, Integer page, Integer size, Sort.Direction direction, String sort) {
        super(id, page, size, direction, sort);
    }

    public Specification<CategoryTranslation> toSpecification() {
        return new CategoryTranslationSpecification(this);
    }

}
