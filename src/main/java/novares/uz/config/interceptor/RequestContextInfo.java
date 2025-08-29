package novares.uz.config.interceptor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import novares.uz.enums.DeviceType;


@Getter
@Setter
@AllArgsConstructor
public class RequestContextInfo {

    private DeviceType deviceType;
    private String appVersion;
    private String timezone;
}
