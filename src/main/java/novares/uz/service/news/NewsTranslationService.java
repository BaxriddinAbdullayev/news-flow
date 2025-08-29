package novares.uz.service.news;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import novares.uz.criteria.news.NewsTranslationCriteria;
import novares.uz.domain.news.NewsTranslation;
import novares.uz.dto.news.NewsTranslationCrudDto;
import novares.uz.mapper.news.NewsTranslationMapper;
import novares.uz.repository.news.NewsTranslationRepository;
import novares.uz.service.GenericCrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NewsTranslationService implements GenericCrudService<NewsTranslation, NewsTranslationCrudDto, NewsTranslationCriteria> {

    private final NewsTranslationRepository repository;
    private final NewsTranslationMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public NewsTranslation get(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NewsTranslation> list(NewsTranslationCriteria criteria) {
        return repository.findAll(criteria.toSpecification(),
                PageRequest.of(criteria.getPage(), criteria.getSize(), Sort.by(criteria.getDirection(), criteria.getSort())));
    }

    @Override
    @Transactional
    public NewsTranslation create(NewsTranslationCrudDto dto) {
        return repository.save(mapper.fromCreateDto(dto));
    }

    @Override
    @Transactional
    public NewsTranslation update(Long id, NewsTranslationCrudDto dto) {
        NewsTranslation entity = get(id);
        return repository.save(mapper.fromUpdate(dto, entity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        NewsTranslation entity = get(id);
        entity.setDeleted(true);
    }
}
