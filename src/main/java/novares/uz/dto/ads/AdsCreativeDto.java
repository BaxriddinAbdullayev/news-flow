package novares.uz.dto.ads;

import lombok.Getter;
import lombok.Setter;
import novares.uz.domain.ads.AdsCreativeTranslation;
import novares.uz.domain.file.ResourceFile;
import novares.uz.dto.GenericDto;
import novares.uz.enums.CreativeType;

import java.util.List;

@Getter
@Setter
public class AdsCreativeDto extends GenericDto {

    private AdsCampaignDto campaign;
    private CreativeType type;
    private String landingUrl;
    private ResourceFile file;
    private String htmlSnippet;
    private Boolean active;
    private List<AdsCreativeTranslationDto> translations;
}
