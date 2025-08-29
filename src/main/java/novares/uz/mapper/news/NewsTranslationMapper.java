package novares.uz.mapper.news;

import novares.uz.domain.news.NewsTranslation;
import novares.uz.dto.news.NewsTranslationCrudDto;
import novares.uz.dto.news.NewsTranslationDto;
import novares.uz.mapper.BaseCrudMapper;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NewsTranslationMapper extends BaseCrudMapper<NewsTranslation, NewsTranslationDto, NewsTranslationCrudDto> {

}
