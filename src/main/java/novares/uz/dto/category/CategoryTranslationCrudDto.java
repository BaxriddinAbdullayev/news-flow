package novares.uz.dto.category;

import lombok.Getter;
import lombok.Setter;
import novares.uz.dto.GenericCrudDto;

@Getter
@Setter
public class CategoryTranslationCrudDto extends GenericCrudDto {

    private CategoryDto category;
    private String lang;
    private String title;
    private String slug;
    private String description;
}
