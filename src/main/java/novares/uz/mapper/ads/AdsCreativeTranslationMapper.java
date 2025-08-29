package novares.uz.mapper.ads;

import novares.uz.domain.ads.AdsCreativeTranslation;
import novares.uz.dto.ads.AdsCreativeTranslationCrudDto;
import novares.uz.dto.ads.AdsCreativeTranslationDto;
import novares.uz.mapper.BaseCrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdsCreativeTranslationMapper extends BaseCrudMapper<AdsCreativeTranslation, AdsCreativeTranslationDto, AdsCreativeTranslationCrudDto> {

}
