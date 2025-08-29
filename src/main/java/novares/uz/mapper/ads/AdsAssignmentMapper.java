package novares.uz.mapper.ads;

import novares.uz.domain.ads.AdsAssignment;
import novares.uz.dto.ads.AdsAssignmentCrudDto;
import novares.uz.dto.ads.AdsAssignmentDto;
import novares.uz.mapper.BaseCrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdsAssignmentMapper extends BaseCrudMapper<AdsAssignment, AdsAssignmentDto, AdsAssignmentCrudDto> {

}
