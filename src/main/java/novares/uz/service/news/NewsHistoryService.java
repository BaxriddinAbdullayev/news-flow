package novares.uz.service.news;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import novares.uz.criteria.news.NewsHistoryCriteria;
import novares.uz.domain.news.NewsHistory;
import novares.uz.dto.news.NewsHistoryCrudDto;
import novares.uz.mapper.news.NewsHistoryMapper;
import novares.uz.repository.news.NewsHistoryRepository;
import novares.uz.service.GenericCrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NewsHistoryService implements GenericCrudService<NewsHistory, NewsHistoryCrudDto, NewsHistoryCriteria> {

    private final NewsHistoryRepository repository;
    private final NewsHistoryMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public NewsHistory get(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NewsHistory> list(NewsHistoryCriteria criteria) {
        return repository.findAll(criteria.toSpecification(),
                PageRequest.of(criteria.getPage(), criteria.getSize(), Sort.by(criteria.getDirection(), criteria.getSort())));
    }

    @Override
    @Transactional
    public NewsHistory create(NewsHistoryCrudDto dto) {
        return repository.save(mapper.fromCreateDto(dto));
    }

    @Override
    @Transactional
    public NewsHistory update(Long id, NewsHistoryCrudDto dto) {
        NewsHistory entity = get(id);
        return repository.save(mapper.fromUpdate(dto, entity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        NewsHistory entity = get(id);
        entity.setDeleted(true);
    }
}
