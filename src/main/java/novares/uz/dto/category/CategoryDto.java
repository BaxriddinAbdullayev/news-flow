package novares.uz.dto.category;

import lombok.Getter;
import lombok.Setter;
import novares.uz.dto.GenericDto;

import java.util.List;

@Getter
@Setter
public class CategoryDto extends GenericDto {

    private CategoryDto parent;
    private Boolean active;
    private List<CategoryTranslationDto> translation;
}
