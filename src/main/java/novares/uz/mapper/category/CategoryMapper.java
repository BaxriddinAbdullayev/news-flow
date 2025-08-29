package novares.uz.mapper.category;

import novares.uz.domain.category.Category;
import novares.uz.domain.news.News;
import novares.uz.dto.category.CategoryCrudDto;
import novares.uz.dto.category.CategoryDto;
import novares.uz.dto.news.NewsDto;
import novares.uz.mapper.BaseCrudMapper;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {
        CategoryMapper.class
})
public interface CategoryMapper extends BaseCrudMapper<Category, CategoryDto, CategoryCrudDto> {

    @Named("fullToDto")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "parent", target = "parent", qualifiedByName = "fullToDto")
    @Mapping(source = "active", target = "active")
    @BeanMapping(ignoreByDefault = true)
    CategoryDto fullToDto(Category news);

}
