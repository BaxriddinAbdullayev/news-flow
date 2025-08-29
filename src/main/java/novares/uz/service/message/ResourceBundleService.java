package novares.uz.service.message;

import lombok.RequiredArgsConstructor;
import novares.uz.enums.AppLanguage;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

import static novares.uz.util.LocaleUtil.getAppLanguage;


@Service
@RequiredArgsConstructor
public class ResourceBundleService {

    private final ResourceBundleMessageSource bundleMessage;

    public String getMessage(String code, AppLanguage lang) {
        return bundleMessage.getMessage(code, null, new Locale(lang.name()));
    }

    public String getMessage(String code) {
        AppLanguage lang = getAppLanguage();
        return bundleMessage.getMessage(code, null, new Locale(lang.name()));
    }

    public String getSuccessCrudMessage(String operation) {
        AppLanguage lang = getAppLanguage();
        return getMessage("operation.success." + operation, lang);
    }
}
