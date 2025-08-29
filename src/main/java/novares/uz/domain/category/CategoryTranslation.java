package novares.uz.domain.category;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import novares.uz.domain.Auditable;

@Getter
@Setter
@Entity
@Table(name = "category_translation",
uniqueConstraints = {
        @UniqueConstraint(name = "uk_category_category_lang", columnNames = {"category_id", "lang"}),
        @UniqueConstraint(name = "uk_category_slug_lang", columnNames = {"slug", "lang"})
})
public class CategoryTranslation extends Auditable<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "lang")
    private String lang;

    @Column(name = "title", nullable = false)
    @Size(min = 3, max = 200, message = "Title must be between 3 and 200 characters")
    private String title;

    @Column(name = "slug", nullable = false)
    @Size(min = 3, max = 200, message = "Slug must be between 3 and 200 characters")
    @Pattern(regexp = "^[a-z0-9-]+$", message = "Slug must contain only lowercase letters, numbers, and hyphens")
    private String slug;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}
