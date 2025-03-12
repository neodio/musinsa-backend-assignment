package com.musinsa.global.config;


import com.musinsa.MusinsaBackendAssignmentApplication;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.properties.SpringDocConfigProperties;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;


@Configuration
public class SwaggerConfig {

    /**
     * @see <a href="https://springdoc.org/#swagger-ui-properties">springdoc - swagger-ui properties</a>
     * @see <a href="https://swagger.io/docs/open-source-tools/swagger-ui/usage/configuration/">swagger - swagger configuration</a>
     */
    @Primary
    @Bean
    public SwaggerUiConfigProperties swaggerUiConfig(SwaggerUiConfigProperties properties) {
        properties.setDocExpansion("none"); // tag 에 대한 기본 확장 방식
        properties.setFilter("true"); // tag filter
        properties.setDefaultModelsExpandDepth(-1); // 기본 모델 disabled (페이지 하단 schema)
        properties.setDefaultModelExpandDepth(2); // end-point schema 정보 기본 expand depth
        properties.setTagsSorter("alpha"); // tag 정렬 방식
        properties.setOperationsSorter("alpha"); // Operation 정렬 방식
        properties.setTryItOutEnabled(true);
        properties.setDisplayRequestDuration(true); // try it out duration 표시
        properties.setDisableSwaggerDefaultUrl(false); // disabled default petstore
        properties.setDeepLinking(true); // operation deep link
        properties.getSyntaxHighlight().setActivated(true);
        properties.getSyntaxHighlight().setTheme("agate");

        return properties;
    }


    /**
     * @see <a href="https://springdoc.org/#springdoc-openapi-core-properties">springdoc - springdoc-openapi core properties</a>
     */
    @Bean
    public SpringDocConfigProperties springDocConfigProperties() {
        SpringDocConfigProperties properties = new SpringDocConfigProperties();
        final String applicationJsonValue = MediaType.APPLICATION_JSON_VALUE;
        properties.setDefaultConsumesMediaType(applicationJsonValue);
        properties.setDefaultProducesMediaType(applicationJsonValue);

        SpringDocConfigProperties.Webjars webJars = new SpringDocConfigProperties.Webjars();
        webJars.setPrefix("springdoc");  // uri prefix 제거
        properties.setWebjars(webJars);

        properties.setOverrideWithGenericResponse(false); // @ControllerAdvice 을 통한 response 자동 생성 disabled
        properties.setWriterWithDefaultPrettyPrinter(true); // pretty print.. :)
        return properties;
    }

    @Bean
    public GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder()
                .displayName("All")
                .group("all")
                .packagesToScan(MusinsaBackendAssignmentApplication.class.getPackage().getName())
                .addOpenApiMethodFilter(method -> method.isAnnotationPresent(Operation.class))
                .build();
    }
}