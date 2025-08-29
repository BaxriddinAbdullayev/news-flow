package novares.uz.mapper.file;

import novares.uz.domain.file.ResourceFile;
import novares.uz.dto.file.ResourceFileDto;
import novares.uz.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResourceFileMapper extends BaseMapper<ResourceFile, ResourceFileDto> {
}
