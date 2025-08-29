package novares.uz.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "News Flow", // Optional: Qo'shimcha ma'lumot berish uchun
                version = "1.0", // OpenAPI versiyasi noto'g'ri emas, faqat ma'lumot sifatida
                description = "MY News Flow application"
        ),
        servers = {
                @Server(url = "http://localhost:8081", description = "Local Server"),
                @Server(url = "https://", description = "{Production} Server")
        },
        security = @SecurityRequirement(name = "bearerAuth") // Apply globally
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT", // Optional: Defines the format (JWT)
        in = SecuritySchemeIn.HEADER,
        paramName = "Authorization"
)
@ConfigurationProperties(prefix = "swagger")
public class SwaggerConfig {

    @Bean
    public List<GroupedOpenApi> userApi() {
        record ApiGroup(String name, String path) {
        }

        List<ApiGroup> apiGroups = Arrays.asList(
                new ApiGroup("User Controller", "/api/v1/admin/users/**"),
                new ApiGroup("Role Controller", "/api/v1/admin/roles/**"),
                new ApiGroup("Auth Controller", "/api/v1/admin/auth/**"),
                new ApiGroup("Category Controller", "/api/v1/admin/categories/**"),
                new ApiGroup("CategoryTranslation Controller", "/api/v1/admin/category-translations/**"),
                new ApiGroup("Tag Controller", "/api/v1/admin/tags/**"),
                new ApiGroup("News Controller", "/api/v1/admin/news/**"),
                new ApiGroup("NewsHistory Controller", "/api/v1/admin/news-histories/**"),
                new ApiGroup("NewsTranslation Controller", "/api/v1/admin/news-translation/**"),
                new ApiGroup("AdsPlacement Controller", "/api/v1/admin/ads-placements/**"),
                new ApiGroup("AdsCampaign Controller", "/api/v1/admin/ads-campaigns/**"),
                new ApiGroup("AdsCreative Controller", "/api/v1/admin/ads-creatives/**"),
                new ApiGroup("AdsCreativeTranslation Controller", "/api/v1/admin/ads-creative-translations/**"),
                new ApiGroup("AdsAssignment Controller", "/api/v1/admin/ads-assignments/**"),
                new ApiGroup("Auth Controller", "/api/v1/admin/auth/**"),
                new ApiGroup("FileTempStorage Controller", "/api/v1/admin/file/resource-file/**"),
                new ApiGroup("NewsPublic Controller", "/api/v1/public/news/**")
        );

        return apiGroups.stream()
                .map(group -> GroupedOpenApi.builder()
                        .group(group.name())
                        .packagesToScan("novares.uz.controller")
                        .pathsToMatch(group.path())
                        .build())
                .toList();
    }

    // SECOND METHOD
//    private List<ApiGroup> groups;
//
//    public record ApiGroup(String name, String packageName, List<String> paths) {}
//
//    public void setGroups(List<ApiGroup> groups) {
//        this.groups = groups;
//    }
//
//    @Bean
//    public List<GroupedOpenApi> groupedOpenApis() {
//        return groups.stream()
//                .map(group -> GroupedOpenApi.builder()
//                        .group(group.name())
//                        .packagesToScan(group.packageName())
//                        .pathsToMatch(group.paths().toArray(new String[0]))
//                        .build())
//                .toList();
//    }
}
