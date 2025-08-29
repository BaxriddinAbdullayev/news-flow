package novares.uz.service.ads;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import novares.uz.criteria.ads.AdsCreativeTranslationCriteria;
import novares.uz.domain.ads.AdsCreativeTranslation;
import novares.uz.domain.category.Category;
import novares.uz.domain.category.CategoryTranslation;
import novares.uz.dto.ads.AdsCreativeTranslationCrudDto;
import novares.uz.dto.ads.AdsCreativeTranslationDto;
import novares.uz.dto.category.CategoryDto;
import novares.uz.dto.category.CategoryTranslationDto;
import novares.uz.mapper.ads.AdsCreativeTranslationMapper;
import novares.uz.repository.ads.AdsCreativeTranslationRepository;
import novares.uz.service.GenericCrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdsCreativeTranslationService implements GenericCrudService<AdsCreativeTranslation, AdsCreativeTranslationCrudDto, AdsCreativeTranslationCriteria> {

    private final AdsCreativeTranslationRepository repository;
    private final AdsCreativeTranslationMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public AdsCreativeTranslation get(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AdsCreativeTranslation> list(AdsCreativeTranslationCriteria criteria) {
        return repository.findAll(criteria.toSpecification(),
                PageRequest.of(criteria.getPage(), criteria.getSize(), Sort.by(criteria.getDirection(), criteria.getSort())));
    }

    @Override
    @Transactional
    public AdsCreativeTranslation create(AdsCreativeTranslationCrudDto dto) {
        return repository.save(mapper.fromCreateDto(dto));
    }

    @Override
    @Transactional
    public AdsCreativeTranslation update(Long id, AdsCreativeTranslationCrudDto dto) {
        AdsCreativeTranslation entity = get(id);
        return repository.save(mapper.fromUpdate(dto, entity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        AdsCreativeTranslation entity = get(id);
        entity.setDeleted(true);
    }
}
