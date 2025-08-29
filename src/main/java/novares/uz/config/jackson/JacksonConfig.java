package novares.uz.config.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import novares.uz.config.jackson.deserializer.ZonedDateTimeDeserializer;
import novares.uz.config.jackson.serializer.ZonedDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZonedDateTime;

@Configuration
public class JacksonConfig {

    @Bean
    public SimpleModule zoneDateTimeModule() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(ZonedDateTime.class, new ZonedDateTimeSerializer());
        module.addDeserializer(ZonedDateTime.class, new ZonedDateTimeDeserializer());
        return module;
    }
}
