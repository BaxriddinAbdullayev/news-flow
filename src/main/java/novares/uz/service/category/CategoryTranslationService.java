package novares.uz.service.category;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import novares.uz.criteria.category.CategoryTranslationCriteria;
import novares.uz.domain.category.CategoryTranslation;
import novares.uz.dto.category.CategoryTranslationCrudDto;
import novares.uz.mapper.category.CategoryTranslationMapper;
import novares.uz.repository.category.CategoryTranslationRepository;
import novares.uz.service.GenericCrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryTranslationService implements GenericCrudService<CategoryTranslation, CategoryTranslationCrudDto, CategoryTranslationCriteria> {

    private final CategoryTranslationRepository repository;
    private final CategoryTranslationMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public CategoryTranslation get(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CategoryTranslation> list(CategoryTranslationCriteria criteria) {
        return repository.findAll(criteria.toSpecification(),
                PageRequest.of(criteria.getPage(), criteria.getSize(), Sort.by(criteria.getDirection(), criteria.getSort())));
    }

    @Override
    @Transactional
    public CategoryTranslation create(CategoryTranslationCrudDto dto) {
        return repository.save(mapper.fromCreateDto(dto));
    }

    @Override
    @Transactional
    public CategoryTranslation update(Long id, CategoryTranslationCrudDto dto) {
        CategoryTranslation entity = get(id);
        return repository.save(mapper.fromUpdate(dto, entity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        CategoryTranslation entity = get(id);
        entity.setDeleted(true);
    }
}
