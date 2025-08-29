package novares.uz.domain.ads;

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
@Table(name = "ads_creative_translation",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_creative_lang", columnNames = {"creative_id", "lang"})
        },
        indexes = {
                @Index(name = "idx_ads_creative_translation_creative_id_lang", columnList = "creative_id,lang")
        })
public class AdsCreativeTranslation extends Auditable<Long> {

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "creative_id")
        private AdsCreative creative;

        @Column(name = "lang")
        private String lang;

        @Column(name = "title")
        @Size(min = 3, max = 200, message = "Title must be between 3 and 200 characters")
        private String title;

        @Column(name = "alt_text", columnDefinition = "TEXT")
        private String altText;
}
