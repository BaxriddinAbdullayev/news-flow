package novares.uz.service.ads;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import novares.uz.collection.AdsPlacementCollection;
import novares.uz.collection.NewsCollection;
import novares.uz.criteria.ads.AdsPlacementCriteria;
import novares.uz.criteria.news.NewsCriteria;
import novares.uz.domain.ads.AdsPlacement;
import novares.uz.dto.ads.AdsPlacementCrudDto;
import novares.uz.mapper.ads.AdsPlacementMapper;
import novares.uz.repository.ads.AdsPlacementRepository;
import novares.uz.service.GenericCrudService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdsPlacementService implements GenericCrudService<AdsPlacement, AdsPlacementCrudDto, AdsPlacementCriteria> {

    private final AdsPlacementRepository repository;
    private final AdsPlacementMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public AdsPlacement get(Long id) {
        return repository.findByIdAndDeletedFalse(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AdsPlacement> list(AdsPlacementCriteria criteria) {
        return repository.findAll(criteria.toSpecification(),
                PageRequest.of(criteria.getPage(), criteria.getSize(), Sort.by(criteria.getDirection(), criteria.getSort())));
    }

    @Override
    @Transactional
    public AdsPlacement create(AdsPlacementCrudDto dto) {
        return repository.save(mapper.fromCreateDto(dto));
    }

    @Override
    @Transactional
    public AdsPlacement update(Long id, AdsPlacementCrudDto dto) {
        AdsPlacement entity = get(id);
        return repository.save(mapper.fromUpdate(dto, entity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        AdsPlacement entity = get(id);
        entity.setDeleted(true);
    }

    @Cacheable(
            value = "publicAds",
            key = "#criteria.placementCode + '_' + (#criteria.categoryId ?: 'all') + '_' + (#criteria.lang ?: 'default')"
    )
    public Page<AdsPlacementCollection> getAdsFilteredForPublic(AdsPlacementCriteria criteria) {
        return repository.getAdsFilteredForPublic(
                criteria.getPlacementCode(),
                criteria.getCategoryId(),
                criteria.getLang(),
                PageRequest.of(criteria.getPage(), criteria.getSize(), Sort.by(criteria.getDirection(), criteria.getSort()))
        );
    }
}
