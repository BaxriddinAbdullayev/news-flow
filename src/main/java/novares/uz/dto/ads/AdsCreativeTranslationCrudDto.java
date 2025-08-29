package novares.uz.dto.ads;

import lombok.Getter;
import lombok.Setter;
import novares.uz.domain.file.ResourceFile;
import novares.uz.dto.GenericCrudDto;
import novares.uz.enums.CreativeType;

@Getter
@Setter
public class AdsCreativeTranslationCrudDto extends GenericCrudDto {

    private AdsCreativeDto creative;
    private String lang;
    private String title;
    private String altText;
}
