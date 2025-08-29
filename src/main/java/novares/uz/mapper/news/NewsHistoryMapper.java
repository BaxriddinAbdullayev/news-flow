package novares.uz.mapper.news;

import novares.uz.domain.news.NewsHistory;
import novares.uz.dto.news.NewsHistoryCrudDto;
import novares.uz.dto.news.NewsHistoryDto;
import novares.uz.mapper.BaseCrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NewsHistoryMapper extends BaseCrudMapper<NewsHistory, NewsHistoryDto, NewsHistoryCrudDto> {

}
