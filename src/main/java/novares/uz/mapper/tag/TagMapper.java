package novares.uz.mapper.tag;

import novares.uz.domain.tag.Tag;
import novares.uz.dto.tag.TagCrudDto;
import novares.uz.dto.tag.TagDto;
import novares.uz.mapper.BaseCrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper extends BaseCrudMapper<Tag, TagDto, TagCrudDto> {

}
