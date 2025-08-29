package novares.uz.dto.news;

import lombok.Getter;
import lombok.Setter;
import novares.uz.dto.GenericDto;

@Getter
@Setter
public class NewsTranslationDto extends GenericDto {

    private Long newsId;
    private String lang;
    private String title;
    private String slug;
    private String summary;
    private String content;
    private String metaTitle;
    private String metaDescription;
}
