package novares.uz.mapper.ads;

import novares.uz.domain.ads.AdsPlacement;
import novares.uz.dto.ads.AdsPlacementCrudDto;
import novares.uz.dto.ads.AdsPlacementDto;
import novares.uz.mapper.BaseCrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdsPlacementMapper extends BaseCrudMapper<AdsPlacement, AdsPlacementDto, AdsPlacementCrudDto> {

}
