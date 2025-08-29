package novares.uz.criteria.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import novares.uz.criteria.GenericCriteria;
import novares.uz.domain.auth.Role;
import novares.uz.specification.auth.RoleSpecification;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

@Getter
@Setter
public class RoleCriteria extends GenericCriteria {

    @Builder(builderMethodName = "childBuilder")
    public RoleCriteria(Long id, Integer page, Integer size, Sort.Direction direction, String sort) {
        super(id, page, size, direction, sort);
    }

    public Specification<Role> toSpecification() {
        return new RoleSpecification(this);
    }

}
