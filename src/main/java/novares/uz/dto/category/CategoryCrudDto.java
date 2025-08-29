package novares.uz.dto.category;

import lombok.Getter;
import lombok.Setter;
import novares.uz.dto.GenericCrudDto;

import java.util.List;

@Getter
@Setter
public class CategoryCrudDto extends GenericCrudDto {

    private CategoryDto parent;
    private Boolean active;
    private List<CategoryTranslationDto> translation;
}
