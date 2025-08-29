package novares.uz.service;

import novares.uz.criteria.GenericCriteria;
import novares.uz.domain.Auditable;
import novares.uz.dto.GenericCrudDto;
import org.springframework.data.domain.Page;

public interface GenericCrudService<T extends Auditable<Long>, C extends GenericCrudDto, E extends GenericCriteria> {

    T get(Long id);

    Page<T> list(E criteria);

    T create(C dto);

    T update(Long id, C dto);

    void delete(Long id);
}
