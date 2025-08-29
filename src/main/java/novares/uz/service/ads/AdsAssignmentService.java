package novares.uz.service.ads;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import novares.uz.criteria.ads.AdsAssignmentCriteria;
import novares.uz.domain.ads.AdsAssignment;
import novares.uz.domain.ads.AdsCampaign;
import novares.uz.domain.ads.AdsCreative;
import novares.uz.domain.ads.AdsPlacement;
import novares.uz.dto.ads.AdsAssignmentCrudDto;
import novares.uz.mapper.ads.AdsAssignmentMapper;
import novares.uz.repository.ads.AdsAssignmentRepository;
import novares.uz.repository.ads.AdsCampaignRepository;
import novares.uz.repository.ads.AdsCreativeRepository;
import novares.uz.repository.ads.AdsPlacementRepository;
import novares.uz.service.GenericCrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdsAssignmentService implements GenericCrudService<AdsAssignment, AdsAssignmentCrudDto, AdsAssignmentCriteria> {

    private final AdsAssignmentRepository repository;
    private final AdsAssignmentMapper mapper;
    private final AdsCampaignRepository adsCampaignRepository;
    private final AdsCreativeRepository adsCreativeRepository;
    private final AdsPlacementRepository adsPlacementRepository;

    @Override
    @Transactional(readOnly = true)
    public AdsAssignment get(Long id) {
        return repository.findByIdAndDeletedFalse(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AdsAssignment> list(AdsAssignmentCriteria criteria) {
        return repository.findAll(criteria.toSpecification(),
                PageRequest.of(criteria.getPage(), criteria.getSize(), Sort.by(criteria.getDirection(), criteria.getSort())));
    }

    @Override
    @Transactional
    public AdsAssignment create(AdsAssignmentCrudDto dto) {
        return repository.save(mapper.fromCreateDto(dto));
    }

    @Override
    @Transactional
    public AdsAssignment update(Long id, AdsAssignmentCrudDto dto) {
        AdsAssignment entity = get(id);

        AdsCampaign adsCampaign = adsCampaignRepository.findByIdAndDeletedFalse(dto.getCampaign().getId())
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
        entity.setCampaign(adsCampaign);
        AdsCreative adsCreative = adsCreativeRepository.findByIdAndDeletedFalse(dto.getCreative().getId())
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
        entity.setCreative(adsCreative);
        AdsPlacement adsPlacement = adsPlacementRepository.findByIdAndDeletedFalse(dto.getPlacement().getId())
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
        entity.setPlacement(adsPlacement);

        return repository.save(mapper.fromUpdate(dto, entity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        AdsAssignment entity = get(id);
        entity.setDeleted(true);
    }
}
