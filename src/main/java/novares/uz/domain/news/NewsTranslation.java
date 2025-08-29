package novares.uz.domain.news;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import novares.uz.domain.Auditable;

@Getter
@Setter
@Entity
@Table(name = "news_translation",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_news_translation_news_lang", columnNames = {"news_id", "lang"}),
                @UniqueConstraint(name = "uk_news_translation_slug_lang", columnNames = {"slug", "lang"})
        },
        indexes = {
                @Index(name = "idx_news_translation_slug_lang", columnList = "slug,lang"),
                @Index(name = "idx_news_translation_search", columnList = "title,summary,content")
        })
public class NewsTranslation extends Auditable<Long> {

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "news_id", nullable = false)
        private News news;

        @Column(name = "lang", length = 2, nullable = false)
        private String lang;

        @Column(name = "title")
        @Size(min = 3, max = 200, message = "Title must be between 3 and 200 characters")
        private String title;

        @Column(name = "slug", nullable = false)
        @NotBlank(message = "Slug must not be blank")
        @Size(min = 3, max = 200, message = "Slug must be between 3 and 200 characters")
        @Pattern(regexp = "^[a-z0-9-]+$", message = "Slug must contain only lowercase letters, numbers, and hyphens")
        private String slug;

        @Column(name = "summary", length = 5000)
        @Size(max = 5000, message = "Summary must not exceed 5000 characters")
        private String summary;

        @Column(name = "content", columnDefinition = "TEXT")
        private String content;

        @Column(name = "meta_title", length = 200)
        @Size(max = 200, message = "Meta title must not exceed 200 characters")
        private String metaTitle;

        @Column(name = "meta_description", columnDefinition = "TEXT")
        private String metaDescription;
}
