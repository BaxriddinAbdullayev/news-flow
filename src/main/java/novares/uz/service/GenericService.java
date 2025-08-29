package novares.uz.service;

import novares.uz.criteria.GenericCriteria;
import org.springframework.data.domain.Page;

public interface GenericService<T, E extends GenericCriteria> {

    T get(Long id);

    Page<T> list(E criteria);
}
