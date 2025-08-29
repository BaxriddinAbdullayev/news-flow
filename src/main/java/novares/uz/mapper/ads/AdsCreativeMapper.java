package novares.uz.mapper.ads;

import novares.uz.domain.ads.AdsCreative;
import novares.uz.dto.ads.AdsCreativeCrudDto;
import novares.uz.dto.ads.AdsCreativeDto;
import novares.uz.mapper.BaseCrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdsCreativeMapper extends BaseCrudMapper<AdsCreative, AdsCreativeDto, AdsCreativeCrudDto> {

}
