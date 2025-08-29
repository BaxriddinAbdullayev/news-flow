package novares.uz.dto.ads;

import lombok.Getter;
import lombok.Setter;
import novares.uz.dto.GenericDto;

@Getter
@Setter
public class AdsCreativeTranslationDto extends GenericDto {

    private Long creativeId;
    private String lang;
    private String title;
    private String altText;
}
