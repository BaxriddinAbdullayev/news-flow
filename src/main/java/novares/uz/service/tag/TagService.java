package novares.uz.service.tag;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import novares.uz.criteria.tag.TagCriteria;
import novares.uz.domain.tag.Tag;
import novares.uz.dto.tag.TagCrudDto;
import novares.uz.mapper.tag.TagMapper;
import novares.uz.repository.tag.TagRepository;
import novares.uz.service.GenericCrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TagService implements GenericCrudService<Tag, TagCrudDto, TagCriteria> {

    private final TagRepository repository;
    private final TagMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public Tag get(Long id) {
        return repository.findByIdAndDeletedFalse(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Tag> list(TagCriteria criteria) {
        return repository.findAll(criteria.toSpecification(),
                PageRequest.of(criteria.getPage(), criteria.getSize(), Sort.by(criteria.getDirection(), criteria.getSort()))
        );
    }

    @Override
    @Transactional
    public Tag create(TagCrudDto dto) {
        return repository.save(mapper.fromCreateDto(dto));
    }

    @Override
    @Transactional
    public Tag update(Long id, TagCrudDto dto) {
        Tag entity = get(id);
        return repository.save(mapper.fromUpdate(dto, entity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Tag entity = get(id);
        entity.setDeleted(true);
    }
}
