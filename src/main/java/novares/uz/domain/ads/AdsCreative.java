package novares.uz.domain.ads;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import novares.uz.domain.Auditable;
import novares.uz.domain.file.ResourceFile;
import novares.uz.enums.CreativeType;

@Getter
@Setter
@Entity
@Table(name = "ads_creative")
public class AdsCreative extends Auditable<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id", nullable = false)
    private AdsCampaign campaign;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private CreativeType type;

    @Column(name = "landing_url")
    private String landingUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private ResourceFile file;

    @Column(name = "html_snippet")
    private String htmlSnippet;

    @Column(name = "is_active")
    private Boolean active = Boolean.TRUE;
}
