package novares.uz.domain.ads;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import novares.uz.domain.Auditable;
import novares.uz.enums.CampaignStatus;

import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "ads_campaign", indexes = {
        @Index(name = "idx_ads_campaign_status_start_at", columnList = "status,start_at")
})
public class AdsCampaign extends Auditable<Long> {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "advertiser")
    private String advertiser;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CampaignStatus status;

    @Column(name = "start_at")
    private ZonedDateTime startAt;

    @Column(name = "end_at")
    private ZonedDateTime endAt;

    @Column(name = "daily_cap_impressions")
    private Integer dailyCapImpressions;

    @Column(name = "daily_cap_clicks")
    private Integer dailyCapClicks;
}
