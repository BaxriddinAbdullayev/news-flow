package novares.uz.dto.ads;

import lombok.Getter;
import lombok.Setter;
import novares.uz.dto.GenericCrudDto;

@Getter
@Setter
public class AdsPlacementCrudDto extends GenericCrudDto {

    private String code;
    private String title;
    private String description;
    private Boolean active;
}
