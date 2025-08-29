package novares.uz.criteria.file;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import novares.uz.criteria.GenericCriteria;
import novares.uz.domain.file.ResourceFile;
import novares.uz.specification.file.ResourceFileSpecification;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

@Getter
@Setter
public class ResourceFileCriteria extends GenericCriteria {

    private String origenName;

    @Builder(builderMethodName = "childBuilder")
    public ResourceFileCriteria(Long id, Integer page, Integer size, Sort.Direction direction, String sort) {
        super(id, page, size, direction, sort);
    }

    public Specification<ResourceFile> toSpecification() {
        return new ResourceFileSpecification(this);
    }
}
