package novares.uz.mapper.ads;

import novares.uz.domain.ads.AdsCampaign;
import novares.uz.dto.ads.AdsCampaignCrudDto;
import novares.uz.dto.ads.AdsCampaignDto;
import novares.uz.mapper.BaseCrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdsCampaignMapper extends BaseCrudMapper<AdsCampaign, AdsCampaignDto, AdsCampaignCrudDto> {

}
