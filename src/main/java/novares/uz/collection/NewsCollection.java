package novares.uz.collection;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public interface NewsCollection {

    Long getId();

    Long getAuthorId();

    Long getCategoryId();

    Long getFileId();

    String getStatus();

    Boolean getIsFeatured();

    LocalDateTime getPublishAt();

    LocalDateTime getUnpublishAt();

    LocalDateTime getDeletedAt();

    // news_translation table fields
    Long getTranslationId();

    Long getTranslationNewsId();

    String getTranslationLang();

    String getTranslationTitle();

    String getTranslationSlug();

    String getTranslationSummary();

    String getTranslationContent();

    String getTranslationMetaTitle();

    String getTranslationMetaDescription();
}
