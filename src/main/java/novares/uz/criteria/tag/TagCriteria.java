package novares.uz.criteria.tag;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import novares.uz.criteria.GenericCriteria;
import novares.uz.domain.tag.Tag;
import novares.uz.specification.tag.TagSpecification;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

@Getter
@Setter
public class TagCriteria extends GenericCriteria {

    @Builder(builderMethodName = "childBuilder")
    public TagCriteria(Long id, Integer page, Integer size, Sort.Direction direction, String sort) {
        super(id, page, size, direction, sort);
    }

    public Specification<Tag> toSpecification() {
        return new TagSpecification(this);
    }

}
