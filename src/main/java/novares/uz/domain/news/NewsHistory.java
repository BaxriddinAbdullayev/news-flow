package novares.uz.domain.news;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import novares.uz.domain.Auditable;
import novares.uz.domain.auth.User;
import novares.uz.enums.NewsStatus;

import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "news_history")
public class NewsHistory extends Auditable<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "news_id", nullable = false)
    private News news;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "changed_by")
    private User changedBy;

    @Column(name = "from_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private NewsStatus fromStatus;

    @Column(name = "to_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private NewsStatus toStatus;

    @Column(name = "diff_jsonn")
    private String diffJson; // JsonNode

    @Column(name = "changed_at")
    private ZonedDateTime changedAt;
}
