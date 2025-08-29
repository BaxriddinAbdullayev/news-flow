package novares.uz.dto.category;

import lombok.Getter;
import lombok.Setter;
import novares.uz.dto.GenericDto;

@Getter
@Setter
public class CategoryTranslationDto extends GenericDto {

    private String lang;
    private String title;
    private String slug;
    private String description;
}