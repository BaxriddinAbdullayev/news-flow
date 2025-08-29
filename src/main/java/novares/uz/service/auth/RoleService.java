package novares.uz.service.auth;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import novares.uz.criteria.auth.RoleCriteria;
import novares.uz.domain.auth.Role;
import novares.uz.dto.auth.role.RoleCrudDto;
import novares.uz.mapper.auth.RoleMapper;
import novares.uz.repository.auth.RoleRepository;
import novares.uz.service.GenericCrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoleService implements GenericCrudService<Role, RoleCrudDto, RoleCriteria> {

    private final RoleRepository repository;
    private final RoleMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public Role get(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Role> list(RoleCriteria criteria) {
        return repository.findAll(criteria.toSpecification(),
                PageRequest.of(criteria.getPage(), criteria.getSize(), Sort.by(criteria.getDirection(), criteria.getSort())));
    }

    @Override
    @Transactional
    public Role create(RoleCrudDto dto) {
        return repository.save(mapper.fromCreateDto(dto));
    }

    @Override
    @Transactional
    public Role update(Long id, RoleCrudDto dto) {
        Role entity = get(id);
        return repository.save(mapper.fromUpdate(dto, entity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Role entity = get(id);
        entity.setDeleted(true);
    }
}
