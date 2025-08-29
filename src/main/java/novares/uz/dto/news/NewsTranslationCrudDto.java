package novares.uz.dto.news;

import lombok.Getter;
import lombok.Setter;
import novares.uz.domain.news.News;
import novares.uz.dto.GenericCrudDto;

@Getter
@Setter
public class NewsTranslationCrudDto extends GenericCrudDto {

    private NewsDto news;
    private String lang;
    private String title;
    private String slug;
    private String summary;
    private String content;
    private String metaTitle;
    private String metaDescription;
}
