package novares.uz.collection;

public interface CategoryCollection {

    Long getId();
    Long getParentId();
    Boolean getIsActive();
    Long getCategoryId();
    String getLang();
    String getSlug();
    String getTitle();
    String getDescription();
}
