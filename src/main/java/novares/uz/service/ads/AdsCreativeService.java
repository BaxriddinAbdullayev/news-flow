package novares.uz.service.ads;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import novares.uz.criteria.ads.AdsCreativeCriteria;
import novares.uz.domain.ads.AdsCampaign;
import novares.uz.domain.ads.AdsCreative;
import novares.uz.domain.ads.AdsCreativeTranslation;
import novares.uz.domain.category.Category;
import novares.uz.domain.category.CategoryTranslation;
import novares.uz.dto.ads.AdsCreativeCrudDto;
import novares.uz.dto.ads.AdsCreativeDto;
import novares.uz.dto.ads.AdsCreativeTranslationCrudDto;
import novares.uz.dto.ads.AdsCreativeTranslationDto;
import novares.uz.mapper.ads.AdsCampaignMapper;
import novares.uz.mapper.ads.AdsCreativeMapper;
import novares.uz.mapper.ads.AdsCreativeTranslationMapper;
import novares.uz.repository.ads.AdsCampaignRepository;
import novares.uz.repository.ads.AdsCreativeRepository;
import novares.uz.repository.ads.AdsCreativeTranslationRepository;
import novares.uz.service.GenericCrudService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdsCreativeService implements GenericCrudService<AdsCreative, AdsCreativeCrudDto, AdsCreativeCriteria> {

    private final AdsCreativeRepository repository;
    private final AdsCreativeMapper mapper;
    private final AdsCreativeTranslationRepository adsCreativeTranslationRepository;
    private final AdsCreativeTranslationMapper adsCreativeTranslationMapper;
    private final AdsCampaignRepository adsCampaignRepository;

    @Override
    @Transactional(readOnly = true)
    public AdsCreative get(Long id) {
        return repository.findByIdAndDeletedFalse(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
    }

    public AdsCreativeDto getCreativeWithTranslation(Long id) {
        AdsCreative adsCreative = get(id);
        AdsCreativeDto dto = mapper.toDto(adsCreative);

        List<AdsCreativeTranslation> allTranslation = adsCreativeTranslationRepository.findAllByCreativeIdAndDeletedFalse(adsCreative.getId());

        List<AdsCreativeTranslationDto> adsCreativeTranslationDtos = new ArrayList<>();
        for (AdsCreativeTranslation adsCreativeTranslation : allTranslation) {
            adsCreativeTranslationDtos.add(adsCreativeTranslationMapper.toDto(adsCreativeTranslation));
        }

        dto.setTranslations(adsCreativeTranslationDtos);
        return dto;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AdsCreative> list(AdsCreativeCriteria criteria) {
        return repository.findAll(criteria.toSpecification(),
                PageRequest.of(criteria.getPage(), criteria.getSize(), Sort.by(criteria.getDirection(), criteria.getSort())));
    }

    public Page<AdsCreativeDto> listCreativeWithTranslation(AdsCreativeCriteria criteria) {
        Pageable pageable = PageRequest.of(
                criteria.getPage(),
                criteria.getSize(),
                Sort.by(criteria.getDirection(), criteria.getSort())
        );

        Page<AdsCreative> allByDeletedFalse = repository.findAllByDeletedFalse(pageable);

        List<AdsCreativeDto> creativeDtoList = allByDeletedFalse.stream().map(creative -> {
            AdsCreativeDto dto = mapper.toDto(creative);

            List<AdsCreativeTranslationDto> tra = adsCreativeTranslationRepository.findAllByCreativeIdAndDeletedFalse(creative.getId())
                    .stream()
                    .map(adsCreativeTranslationMapper::toDto)
                    .toList();

            dto.setTranslations(tra);
            return dto;
        }).toList();

        return new PageImpl<>(creativeDtoList, pageable, allByDeletedFalse.getTotalElements());
    }

    @Override
    @Transactional
    public AdsCreative create(AdsCreativeCrudDto dto) {
        AdsCreative creative = repository.save(mapper.fromCreateDto(dto));
        List<AdsCreativeTranslationDto> translations = dto.getTranslations();

        for (AdsCreativeTranslationDto translation : translations) {
            AdsCreativeTranslationCrudDto translationCrudDto = new AdsCreativeTranslationCrudDto();
            translationCrudDto.setTitle(translation.getTitle());
            translationCrudDto.setCreative(mapper.toDto(creative));
            translationCrudDto.setLang(translation.getLang());
            translationCrudDto.setAltText(translation.getAltText());

            adsCreativeTranslationRepository.save(adsCreativeTranslationMapper.fromCreateDto(translationCrudDto));
        }

        return creative;
    }

    @Override
    @Transactional
    public AdsCreative update(Long id, AdsCreativeCrudDto dto) {
        AdsCreative creative = get(id);

        AdsCampaign adsCampaign = adsCampaignRepository.findByIdAndDeletedFalse(dto.getCampaign().getId())
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
        creative.setCampaign(adsCampaign);
        creative = repository.save(mapper.fromUpdate(dto, creative));


        List<AdsCreativeTranslationDto> creativeTranslationDtos = dto.getTranslations();
        for (AdsCreativeTranslationDto translation : creativeTranslationDtos) {

            AdsCreativeTranslationCrudDto translationCrudDto = new AdsCreativeTranslationCrudDto();
            translationCrudDto.setTitle(translation.getTitle());
            translationCrudDto.setCreative(mapper.toDto(creative));
            translationCrudDto.setLang(translation.getLang());
            translationCrudDto.setAltText(translation.getAltText());

            AdsCreativeTranslation adsCreativeTranslation = adsCreativeTranslationRepository.findByIdAndDeletedFalse(translation.getId())
                    .orElseThrow(() -> new EntityNotFoundException(String.valueOf(translation.getId())));
            adsCreativeTranslationMapper.fromUpdate(translationCrudDto, adsCreativeTranslation);
        }

        return creative;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        AdsCreative entity = get(id);
        entity.setDeleted(true);
        repository.save(entity);

        List<AdsCreativeTranslation> all = adsCreativeTranslationRepository.findAllByCreativeIdAndDeletedFalse(entity.getId());
        for (AdsCreativeTranslation adsCreativeTranslation : all) {
            adsCreativeTranslation.setDeleted(true);
            adsCreativeTranslationRepository.save(adsCreativeTranslation);
        }
    }
}
