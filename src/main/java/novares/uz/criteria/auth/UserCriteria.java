package novares.uz.criteria.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import novares.uz.criteria.GenericCriteria;
import novares.uz.domain.auth.User;
import novares.uz.specification.auth.UserSpecification;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

@Getter
@Setter
public class UserCriteria extends GenericCriteria {

    @Builder(builderMethodName = "childBuilder")
    public UserCriteria(Long id, Integer page, Integer size, Sort.Direction direction, String sort) {
        super(id, page, size, direction, sort);
    }

    public Specification<User> toSpecification() {
        return new UserSpecification(this);
    }

}
