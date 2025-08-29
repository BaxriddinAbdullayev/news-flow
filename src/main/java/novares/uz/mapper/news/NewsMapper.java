package novares.uz.mapper.news;

import novares.uz.domain.news.News;
import novares.uz.dto.news.NewsCrudDto;
import novares.uz.dto.news.NewsDto;
import novares.uz.mapper.BaseCrudMapper;
import novares.uz.mapper.auth.UserMapper;
import novares.uz.mapper.category.CategoryMapper;
import novares.uz.mapper.tag.TagMapper;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {
        UserMapper.class,
        TagMapper.class,
        CategoryMapper.class,
        NewsTranslationMapper.class,
})
public interface NewsMapper extends BaseCrudMapper<News, NewsDto, NewsCrudDto> {

    @Named("fullToDto")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "author", target = "author")
    @Mapping(source = "category", target = "category")
    @Mapping(source = "file", target = "file")
    @Mapping(source = "tags", target = "tags")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "isFeatured", target = "isFeatured")
    @Mapping(source = "publishAt", target = "publishAt")
    @Mapping(source = "unpublishAt", target = "unpublishAt")
    @Mapping(source = "deletedAt", target = "deletedAt")
    @BeanMapping(ignoreByDefault = true)
    NewsDto fullToDto(News news);

}
