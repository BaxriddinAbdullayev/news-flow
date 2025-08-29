package novares.uz.domain.ads;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import novares.uz.domain.Auditable;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "ads_assignment", indexes = {
        @Index(name = "idx_ads_assignment_placement_id", columnList = "placement_id, is_active")
})
public class AdsAssignment extends Auditable<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "placement_id", nullable = false)
    private AdsPlacement placement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id", nullable = false)
    private AdsCampaign campaign;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creative_id", nullable = false)
    private AdsCreative creative;

    @Column(name = "weight")
    @Min(value = 1, message = "Weight must be at least 1")
    private Integer weight;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "lang_filter", columnDefinition = "jsonb")
    private JsonNode langFilter;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "category_filter", columnDefinition = "jsonb")
    private JsonNode categoryFilter;

    @Column(name = "start_at")
    private ZonedDateTime startAt;

    @Column(name = "end_at")
    private ZonedDateTime endAt;

    @Column(name = "is_active")
    private Boolean active = Boolean.TRUE;
}
