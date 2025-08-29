package novares.uz.dto.ads;

import lombok.Getter;
import lombok.Setter;
import novares.uz.dto.GenericDto;

@Getter
@Setter
public class AdsPlacementDto extends GenericDto {

    private String code;
    private String title;
    private String description;
    private Boolean active;
}
