package novares.uz.dto.tag;

import lombok.Getter;
import lombok.Setter;
import novares.uz.dto.GenericDto;

@Getter
@Setter
public class TagDto extends GenericDto {

    private String code;
    private Boolean active;
}
