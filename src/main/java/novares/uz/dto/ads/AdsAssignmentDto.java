package novares.uz.dto.ads;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;
import novares.uz.dto.GenericDto;

import java.time.ZonedDateTime;

@Getter
@Setter
public class AdsAssignmentDto extends GenericDto {

    private AdsPlacementDto placement;
    private AdsCampaignDto campaign;
    private AdsCreativeDto creative;
    private Integer weight;
    private JsonNode langFilter; // JsonNode
    private JsonNode categoryFilter; // JsonNode
    private ZonedDateTime startAt;
    private ZonedDateTime endAt;
    private Boolean active;
}
