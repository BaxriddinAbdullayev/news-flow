package novares.uz.dto.news;

import lombok.Getter;
import lombok.Setter;
import novares.uz.domain.tag.Tag;
import novares.uz.dto.GenericCrudDto;
import novares.uz.dto.auth.user.UserDto;
import novares.uz.dto.category.CategoryDto;
import novares.uz.dto.file.ResourceFileDto;
import novares.uz.dto.tag.TagDto;
import novares.uz.enums.NewsStatus;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class NewsCrudDto extends GenericCrudDto {

    private UserDto author;
    private CategoryDto category;
    private ResourceFileDto file;
    private Set<TagDto> tags;
    private NewsStatus status;
    private Boolean isFeatured;
    private ZonedDateTime publishAt;
    private ZonedDateTime unpublishAt;
    private ZonedDateTime deletedAt;
    private List<NewsTranslationDto> translations;
}
