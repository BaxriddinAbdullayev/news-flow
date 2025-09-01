package novares.uz.service.ads;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import novares.uz.criteria.ads.AdsCampaignCriteria;
import novares.uz.domain.ads.AdsCampaign;
import novares.uz.dto.ads.AdsCampaignCrudDto;
import novares.uz.mapper.ads.AdsCampaignMapper;
import novares.uz.repository.ads.AdsCampaignRepository;
import novares.uz.service.GenericCrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdsCampaignService implements GenericCrudService<AdsCampaign, AdsCampaignCrudDto, AdsCampaignCriteria> {

    private final AdsCampaignRepository repository;
    private final AdsCampaignMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public AdsCampaign get(Long id) {
        return repository.findByIdAndDeletedFalse(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AdsCampaign> list(AdsCampaignCriteria criteria) {
        return repository.findAll(criteria.toSpecification(),
                PageRequest.of(criteria.getPage(), criteria.getSize(), Sort.by(criteria.getDirection(), criteria.getSort())));
    }

    @Override
    @Transactional
    public AdsCampaign create(AdsCampaignCrudDto dto) {
        return repository.save(mapper.fromCreateDto(dto));
    }

    @Override
    @Transactional
    public AdsCampaign update(Long id, AdsCampaignCrudDto dto) {
        AdsCampaign entity = get(id);
        return repository.save(mapper.fromUpdate(dto, entity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        AdsCampaign entity = get(id);
        entity.setDeleted(true);
    }
}
