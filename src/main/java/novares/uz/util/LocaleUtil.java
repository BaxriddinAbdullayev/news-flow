package novares.uz.util;

import novares.uz.enums.AppLanguage;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

public class LocaleUtil {

    public static AppLanguage getAppLanguage() {
        Locale locale = LocaleContextHolder.getLocale();
        return AppLanguage.valueOf(locale.getLanguage().toUpperCase());
    }
}
