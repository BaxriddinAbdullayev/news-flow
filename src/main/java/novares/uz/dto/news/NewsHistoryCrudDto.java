package novares.uz.dto.news;

import lombok.Getter;
import lombok.Setter;
import novares.uz.dto.GenericCrudDto;
import novares.uz.dto.auth.user.UserDto;
import novares.uz.enums.NewsStatus;

import java.time.ZonedDateTime;

@Getter
@Setter
public class NewsHistoryCrudDto extends GenericCrudDto {

    private NewsDto news;
    private UserDto changedBy;
    private NewsStatus fromStatus;
    private NewsStatus toStatus;
    private String diffJson;
    private ZonedDateTime changedAt;
}
