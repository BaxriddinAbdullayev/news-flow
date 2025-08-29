package novares.uz.dto.ads;

import lombok.Getter;
import lombok.Setter;
import novares.uz.dto.GenericDto;
import novares.uz.enums.CampaignStatus;

import java.time.ZonedDateTime;

@Getter
@Setter
public class AdsCampaignDto extends GenericDto {

    private String name;
    private String advertiser;
    private CampaignStatus status;
    private ZonedDateTime startAt;
    private ZonedDateTime endAt;
    private Integer dailyCapImpressions;
    private Integer dailyCapClicks;
}
