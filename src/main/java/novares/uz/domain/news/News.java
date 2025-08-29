package novares.uz.domain.news;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import novares.uz.domain.Auditable;
import novares.uz.domain.auth.User;
import novares.uz.domain.category.Category;
import novares.uz.domain.file.ResourceFile;
import novares.uz.domain.tag.Tag;
import novares.uz.enums.NewsStatus;

import java.time.ZonedDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "news", indexes = {
        @Index(name = "idx_news_status_publish_at", columnList = "status,publish_at DESC")
})
public class News extends Auditable<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private ResourceFile file;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "news_tag",
            joinColumns = {@JoinColumn(name = "news_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "id")})
    private Set<Tag> tags;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private NewsStatus status;

    @Column(name = "isFeatured")
    private Boolean isFeatured = Boolean.FALSE;

    @Column(name = "publish_at")
    private ZonedDateTime publishAt;

    @Column(name = "unpublish_at")
    private ZonedDateTime unpublishAt;

    @Column(name = "deleted_at")
    private ZonedDateTime deletedAt;
}
