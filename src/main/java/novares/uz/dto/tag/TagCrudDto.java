package novares.uz.dto.tag;

import lombok.Getter;
import lombok.Setter;
import novares.uz.dto.GenericCrudDto;

@Getter
@Setter
public class TagCrudDto extends GenericCrudDto {

    private String code;
    private Boolean active;
}
