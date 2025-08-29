package novares.uz.mapper.category;

import novares.uz.domain.category.CategoryTranslation;
import novares.uz.dto.category.CategoryTranslationCrudDto;
import novares.uz.dto.category.CategoryTranslationDto;
import novares.uz.mapper.BaseCrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {
        CategoryMapper.class
})
public interface CategoryTranslationMapper extends BaseCrudMapper<CategoryTranslation, CategoryTranslationDto, CategoryTranslationCrudDto> {

}
